(ns xcljb.conn
  (:require [clojure.tools.logging :as log]
            [gloss.core :as gcore]
            [gloss.io :as gio]
            [xcljb.auth]
            [xcljb.gen-common :as gen-common]
            [xcljb.gen.xproto-internal :as xproto-internal]
            [xcljb.gen.xproto-types :as xproto-types])
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

(defn- padding [n]
  (rem (- 4 (rem n 4))
       4))

(defn- setup-request-codec [auth-name auth-data]
  (let [name-len (count auth-name)
        data-len (count auth-data)]
    (gcore/compile-frame
     [:ubyte :byte
      :uint16 :uint16
      :uint16 :uint16 :int16
      (gcore/string :ascii :length name-len)
      (repeat (padding name-len) :byte)
      (repeat data-len :ubyte)
      (repeat (padding data-len) :byte)])))

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
                  (repeat (padding name-len) 0)
                  auth-data
                  (repeat (padding data-len) 0)]))))

(defn- handle-setup-failed-reply [ch]
  (let [reason-len (gen-common/read-bytes ch 1)
        protocol-major-version (gen-common/read-bytes ch 2)
        protocol-minor-version (gen-common/read-bytes ch 2)
        length (gen-common/read-bytes ch 2)
        reason (gen-common/read-string ch reason-len)
        _ (gen-common/read-pad ch (- (* length 4) reason-len))]
    (binding [*out* *err*]
      (println "Connection setup failed:" reason)
      (println "Protocol version: major" protocol-major-version
               "minor" protocol-minor-version))
    (System/exit 1)))

(defn- handle-setup-authenticate-reply [ch]
  (let [_ (gen-common/read-pad ch 5)
        length (gen-common/read-bytes ch 2)
        reason (gen-common/read-string ch (* length 4))]
    (binding [*out* *err*]
      (println "Connection setup requires additional authentication:" reason))
    (System/exit 2)))

(defn- get-setup-success-reply [ch]
  (let [_ (gen-common/read-pad ch 1)
        protocol-major-version (gen-common/read-bytes ch 2)
        protocol-minor-version (gen-common/read-bytes ch 2)
        length (gen-common/read-bytes ch 2)
        release-number (gen-common/read-bytes ch 4)
        resource-id-base (gen-common/read-bytes ch 4)
        resource-id-mask (gen-common/read-bytes ch 4)
        motion-buffer-size (gen-common/read-bytes ch 4)
        vendor-len (gen-common/read-bytes ch 2)
        maximum-request-length (gen-common/read-bytes ch 2)
        roots-len (gen-common/read-bytes ch 1)
        pixmap-formats-len (gen-common/read-bytes ch 1)
        image-byte-order (gen-common/read-bytes ch 1)
        bitmap-format-bit-order (gen-common/read-bytes ch 1)
        bitmap-format-scanline-unit (gen-common/read-bytes ch 1)
        bitmap-format-scanline-pad (gen-common/read-bytes ch 1)
        min-keycode (.read-type xproto-types/KEYCODE ch)
        max-keycode (.read-type xproto-types/KEYCODE ch)
        _ (gen-common/read-pad ch 4)
        vendor (gen-common/read-string ch vendor-len)
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
  (case (gen-common/read-bytes ch 1)
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
    (when (< seqn seq-num)
      (.take replyq)
      (deliver reply nil)
      (recur replyq seq-num))))

(defn- deliver-reply [reply seq-num replyq]
  (clear-old-replies replyq seq-num)
  (let [{seqn :seq-num, opcode :opcode, r :reply, :as v} (.peek replyq)]
    (assert v "Sequence number corresponds to no request.")
    (assert (= seqn seq-num) "Sequence number greater than all requests.")
    (.take replyq)
    (deliver r reply)))

(defn- get-read-reply [replyq seq-num]
  (clear-old-replies replyq seq-num)
  (let [{seqn :seq-num, opcode :opcode, :as r} (.peek replyq)]
    (assert r "Sequence number corresponds to no request.")
    (assert (= seqn seq-num) "Sequence number greater than all requests.")
    (xproto-internal/read-reply opcode)))

(defn- handle-error [ch replyq]
  (let [code (gen-common/read-bytes ch 1)
        seq-n (gen-common/read-bytes ch 2)
        err ((xproto-internal/read-error code) ch)]
    (log/error err)
    (deliver-reply err seq-n replyq)))

(defn- handle-reply [ch replyq]
  (let [val (gen-common/read-bytes ch 1) ; always unsigned for xproto-1.8
        seq-n (gen-common/read-bytes ch 2)
        len (gen-common/read-bytes ch 4)
        read-reply (get-read-reply replyq seq-n)
        reply (read-reply ch len val)]
    (log/debug reply)
    (deliver-reply reply seq-n replyq)))

(defn- handle-event [ch event-num replyq eventq]
  (let [{:keys [seq-num event]} ((xproto-internal/read-event event-num) ch)]
    (log/debug event)
    (when seq-num                       ; not KeymapNotify
      (clear-old-replies replyq seq-num))
    (.put eventq event)))

(defn- read-channel [ch replyq eventq]
  (while (.isOpen ch)
    (try
      (let [type-or-event (gen-common/read-bytes ch 1)]
        (case type-or-event
          ;; Error.
          0 (handle-error ch replyq)
          ;; Reply.
          1 (handle-reply ch replyq)
          ;; Event.
          (handle-event ch type-or-event replyq eventq)))

      (catch java.nio.channels.AsynchronousCloseException e
        ;; Channel closed; do nothing.
        ))))

(defn connect
  "Connects to the X server at the given host and port and returns a
  connection object. Defaults to localhost and port 6000.

  The connection object should be taken as an opaque object, i.e. do
  not modify or inspect the connection object."
  ([] (connect "localhost" 6000))
  ([host port]
     (let [ch (SocketChannel/open (InetSocketAddress. host port))
           ;; Disable Nagle's algorithm.
           _ (-> ch (.socket) (.setTcpNoDelay true))
           _ (.finishConnect ch)
           setup-reply (future (setup ch))
           replyq (LinkedBlockingQueue.)
           eventq (LinkedBlockingQueue.)
           ch-reader (Thread. #(read-channel ch replyq eventq))]
       ;; Wait for setup-reply to finish.
       @setup-reply
       (log/debug "Connection setup finished.")
       (.start ch-reader)
       (atom
        {:conn-lock (Object.)
         :ch ch
         :setup @setup-reply
         :seq-num 0
         :res-id 0
         :replies replyq
         :events eventq
         :ch-reader ch-reader}))))

(defn disconnect
  "Disconnects from the X server. Connection conn will no longer be
  valid after this function returns."
  [conn]
  (.close (:ch @conn))
  (swap! conn (constantly nil))
  (log/debug "Disconnected.")
  nil)
