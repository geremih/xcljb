(ns xcljb.conn
  (:require [clojure.tools.logging :as log]
            [gloss.core :as gcore]
            [gloss.io :as gio]
            [xcljb.auth]
            [xcljb.common :as common]
            [xcljb.gen.xproto-internal :as xproto-internal]
            [xcljb.gen.xproto-types :as xproto-types]
            ;; Extensions (read-reply/event/error).
            xcljb.gen.xc-misc-internal)
  (:import [java.net InetSocketAddress]
           [java.nio ByteOrder]
           [java.nio.channels SocketChannel]
           [java.util.concurrent LinkedBlockingQueue]))

(def ^:private PROTOCOL-MAJOR-VERSION 11)
(def ^:private PROTOCOL-MINOR-VERSION 0)

(def ^:private BYTE-ORDER
  (condp = (ByteOrder/nativeOrder)
    ByteOrder/LITTLE_ENDIAN (int \l)
    ByteOrder/BIG_ENDIAN (int \B)))

(defn- parse-display-string [disp-str]
  (let [[_ display screen] (re-matches #":(\d+).?(\d*)" disp-str)]
    {:display (Integer/parseInt display)
     :screen (if (empty? screen)
               0
               (Integer/parseInt screen))}))

(defn- setup-request-codec [auth-name auth-data]
  (let [name-len (count auth-name)
        data-len (count auth-data)]
    (gcore/compile-frame
     [:ubyte :byte
      :uint16 :uint16
      :uint16 :uint16 :int16
      (gcore/string :ascii :length name-len)
      (repeat (common/padding name-len) :byte)
      (repeat data-len :ubyte)
      (repeat (common/padding data-len) :byte)])))

(defn- make-setup-request [auth-name auth-data]
  (let [name-len (count auth-name)
        data-len (count auth-data)]
    (gio/contiguous
     (gio/encode (setup-request-codec auth-name auth-data)
                 ;; Network byte order (big-endian), since data goes through TCP.
                 [(int \B) 0
                  PROTOCOL-MAJOR-VERSION PROTOCOL-MINOR-VERSION
                  name-len data-len 0
                  auth-name
                  (repeat (common/padding name-len) 0)
                  auth-data
                  (repeat (common/padding data-len) 0)]))))

(defn- handle-setup-failed-reply [ch]
  (let [reason-len (common/read-bytes ch 1)
        protocol-major-version (common/read-bytes ch 2)
        protocol-minor-version (common/read-bytes ch 2)
        length (common/read-bytes ch 2)
        reason (common/read-string ch reason-len)
        _ (common/read-pad ch (- (* length 4) reason-len))]
    (binding [*out* *err*]
      (println "Connection setup failed:" reason)
      (println "Protocol version: major" protocol-major-version
               "minor" protocol-minor-version))
    (System/exit 1)))

(defn- handle-setup-authenticate-reply [ch]
  (let [_ (common/read-pad ch 5)
        length (common/read-bytes ch 2)
        reason (common/read-string ch (* length 4))]
    (binding [*out* *err*]
      (println "Connection setup requires additional authentication:" reason))
    (System/exit 2)))

(defn- get-setup-success-reply [ch]
  (let [_ (common/read-pad ch 1)
        protocol-major-version (common/read-bytes ch 2)
        protocol-minor-version (common/read-bytes ch 2)
        length (common/read-bytes ch 2)
        release-number (common/read-bytes ch 4)
        resource-id-base (common/read-bytes ch 4)
        resource-id-mask (common/read-bytes ch 4)
        motion-buffer-size (common/read-bytes ch 4)
        vendor-len (common/read-bytes ch 2)
        maximum-request-length (common/read-bytes ch 2)
        roots-len (common/read-bytes ch 1)
        pixmap-formats-len (common/read-bytes ch 1)
        image-byte-order (common/read-bytes ch 1)
        bitmap-format-bit-order (common/read-bytes ch 1)
        bitmap-format-scanline-unit (common/read-bytes ch 1)
        bitmap-format-scanline-pad (common/read-bytes ch 1)
        min-keycode (.read-type xproto-types/KEYCODE ch)
        max-keycode (.read-type xproto-types/KEYCODE ch)
        _ (common/read-pad ch 4)
        vendor (common/read-string ch vendor-len)
        pixmap-formats (doall (repeatedly pixmap-formats-len #(xproto-internal/read-Format ch)))
        roots (doall (repeatedly roots-len #(xproto-internal/read-Screen ch)))]
    {:protocol-major-version protocol-major-version
     :protocol-minor-version protocol-minor-version
     :release-number release-number
     :resource-id-base resource-id-base
     :resource-id-mask resource-id-mask
     :motion-buffer-size motion-buffer-size
     :maximum-request-length maximum-request-length
     :image-byte-order image-byte-order
     :bitmap-format-bit-order bitmap-format-bit-order
     :bitmap-format-scanline-unit bitmap-format-scanline-unit
     :bitmap-format-scanline-pad bitmap-format-scanline-pad
     :min-keycode min-keycode
     :max-keycode max-keycode
     :vendor vendor
     :pixmap-formats pixmap-formats
     :roots roots}))

(defn- handle-setup-reply [ch]
  (case (common/read-bytes ch 1)
    0 (handle-setup-failed-reply ch)
    1 (get-setup-success-reply ch)
    2 (handle-setup-authenticate-reply ch)))

(defn- setup [ch]
  (let [auth (xcljb.auth/get-auth)
        req (make-setup-request (:name auth) (:data auth))]
    (.write ch req)
    (handle-setup-reply ch)))

(defn- clear-old-replies [replyq seq-num]
  (when-let [{seqn :seq-num, reply :reply} (.peek replyq)]
    (when (not= seqn seq-num)
      (.take replyq)
      (deliver reply nil)
      (recur replyq seq-num))))

(defn- get-response [resp-q seq-num]
  (clear-old-replies resp-q seq-num)
  (let [{seqn :seq-num, :as r} (.peek resp-q)]
    (assert r "Sequence number corresponds to no request.")
    (assert (= seqn seq-num) "Sequence number greater than all requests.")
    (.take resp-q)))

(defn- first<= [s-map n]
  (->> s-map
       (rseq)
       (filter #(<= (first %) n))
       (first)))

(defn- get-event-number [event-num ext-cache]
  (if (<= 64 event-num 127)             ; extension
    (let [[event-base ext-name] (first<= (:event-bases @ext-cache) event-num)]
      {:event-num (- event-num event-base)
       :ext-name ext-name})
    {:event-num event-num
     :ext-name nil}))

(defn- get-error-number [error-num ext-cache]
  (if (<= 128 error-num 255)            ; extension
    (let [[error-base _] (first<= (:error-bases @ext-cache) error-num)]
      (- error-num error-base))
    error-num))

(defn- handle-error [ch replyq ext-cache]
  (let [code (common/read-bytes ch 1)
        seq-n (common/read-bytes ch 2)
        resp (get-response replyq seq-n)
        err (common/read-error (:ext-name resp)
                               (get-error-number code ext-cache)
                               ch)]
    (log/error err)
    (deliver (:reply resp) err)))

(defn- handle-reply [ch replyq]
  (let [val (common/read-bytes ch 1) ; always unsigned for xproto-1.8
        seq-n (common/read-bytes ch 2)
        len (common/read-bytes ch 4)
        resp (get-response replyq seq-n)
        reply (common/read-reply (:ext-name resp) (:opcode resp) ch len val)]
    (log/debug reply)
    (deliver (:reply resp) reply)))

(defn- handle-event [ch event-num replyq eventq ext-cache]
  (let [{:keys [event-num ext-name]} (get-event-number event-num ext-cache)
        {:keys [seq-num event]} (common/read-event ext-name event-num ch)]
    (log/debug event)
    (when seq-num                       ; not KeymapNotify
      (clear-old-replies replyq seq-num))
    (.put eventq event)))

(defn- read-channel [ch replyq eventq ext-cache]
  (while (.isOpen ch)
    (try
      (let [type-or-event (common/read-bytes ch 1)]
        (case type-or-event
          ;; Error.
          0 (handle-error ch replyq ext-cache)
          ;; Reply.
          1 (handle-reply ch replyq)
          ;; Event.
          (handle-event ch type-or-event replyq eventq ext-cache)))

      (catch java.nio.channels.AsynchronousCloseException e
        ;; Channel closed; do nothing.
        ))))

(defn- setup->xids [setup]
  (let [base (:resource-id-base setup)
        mask (:resource-id-mask setup)
        shifts (.getLowestSetBit (BigInteger/valueOf mask))]
    (assert (not (neg? shifts)))
    (map #(bit-or (bit-shift-left % shifts) base)
         (range (inc (bit-shift-right mask shifts))))))

(defn connect
  "Connects to the X server at the given host and port and returns a
  connection object. Defaults to localhost and port 6000.

  The connection object should be taken as an opaque object, i.e. do
  not modify or inspect the connection object."
  [& {:keys [host port]
      :or {host "localhost" port 6000}}]
  (let [ch (SocketChannel/open (InetSocketAddress. host port))
        ;; Disable Nagle's algorithm.
        _ (-> ch (.socket) (.setTcpNoDelay true))
        _ (.finishConnect ch)
        setup-reply (future (setup ch))
        replyq (LinkedBlockingQueue.)
        eventq (LinkedBlockingQueue.)
        ext-cache {:exts (ref {})
                   :event-bases (ref (sorted-map))
                   :error-bases (ref (sorted-map))}
        ch-reader (Thread. #(read-channel ch replyq eventq ext-cache))]
    ;; Wait for setup-reply to finish.
    @setup-reply
    (log/debug "Connection setup finished.")
    (.start ch-reader)
    {:conn-lock (Object.)
     :ch ch
     :setup @setup-reply
     :seq-nums (atom (cycle (range 1 0x10000)))
     :xids (ref (setup->xids @setup-reply))
     :replies replyq
     :events eventq
     :ch-reader ch-reader
     :ext-cache ext-cache}))

(defn disconnect
  "Disconnects from the X server. Connection conn will no longer be
  valid after this function returns."
  [conn]
  (.close (:ch conn))
  (log/debug "Disconnected.")
  nil)
