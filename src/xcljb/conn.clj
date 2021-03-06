(ns xcljb.conn
  (:require [clojure.tools.logging :as log]
            [gloss.core :as gcore]
            [gloss.io :as gio]
            [clojure.java.io :as io]
            [xcljb.auth]
            [xcljb.common :as common]
            [xcljb gen-common]
            [xcljb.gen.xproto-internal :as xproto-internal]
            [xcljb.gen.xproto-types :as xproto-types]
            ;; Extensions (read-reply/event/error).
            [xcljb.gen
             bigreq-internal
             composite-internal
             damage-internal
             dpms-internal
             dri2-internal
             ge-internal
             glx-internal
             randr-internal
             record-internal
             render-internal
             res-internal
             screensaver-internal
             shape-internal
             shm-internal
             sync-internal
             xc-misc-internal
             xevie-internal
             xf86dri-internal
             xf86vidmode-internal
             xfixes-internal
             xinerama-internal
             xinput-internal
             xprint-internal
             xselinux-internal
             xtest-internal
             xv-internal
             xvmc-internal])
  (:import [java.nio ByteBuffer ByteOrder]
           [jnr.unixsocket UnixSocketAddress UnixSocketChannel]
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

(defn- handle-setup-failed-reply [buf]
  (let [{:keys [protocol-major-version protocol-minor-version reason]} (xcljb.gen-common/deserialize xcljb.gen.xproto-types/SetupFailed buf nil)]
    (binding [*out* *err*]
      (println "Connection setup failed:" reason)
      (println "Protocol version (major/minor):"
               protocol-major-version "/" protocol-minor-version))
    (System/exit 1)))

(defn- handle-setup-success-reply [buf]
  (xcljb.gen-common/deserialize xcljb.gen.xproto-types/Setup buf nil))

(defn- handle-setup-authenticate-reply [buf]
  (let [{:keys [reason]} (xcljb.gen-common/deserialize xcljb.gen.xproto-types/SetupAuthenticate buf nil)]
    (binding [*out* *err*]
      (println "Connection setup requires additional authentication:" reason))
    (System/exit 2)))

(defn- handle-setup-reply [ch]
  (let [header-buf (ByteBuffer/allocate 8)
        _ (.read ch header-buf)
        _ (-> header-buf (.limit 1) (.position 0))
        status (gloss.io/decode :ubyte header-buf)
        _ (-> header-buf (.limit 8) (.position 6))
        len (gloss.io/decode :uint16 header-buf)
        reply-buf (ByteBuffer/allocate (+ 8 (* len 4)))
        _ (.clear header-buf)
        _ (.put reply-buf header-buf)
        _ (.read ch reply-buf)
        ro-reply-buf (.asReadOnlyBuffer reply-buf)
        _ (.rewind ro-reply-buf)]
    (case status
      0 (handle-setup-failed-reply ro-reply-buf)
      1 (handle-setup-success-reply ro-reply-buf)
      2 (handle-setup-authenticate-reply ro-reply-buf))))

(defn- setup [ch]
  (let [auth (xcljb.auth/get-auth)
        req (make-setup-request (:name auth) (:data auth))]
    (.write ch req)
    (handle-setup-reply ch)))

(defn- read-error-buf [ch header-buf]
  (let [_ (-> header-buf (.limit 2) (.position 1))
        error-num (gloss.io/decode :ubyte header-buf)
        _ (-> header-buf (.limit 4) (.position 2))
        seq-num (gloss.io/decode :uint16 header-buf)
        error-buf (ByteBuffer/allocate 32)]
    (.clear header-buf)
    (.put error-buf header-buf)
    (.read ch error-buf)
    {:error-num error-num
     :seq-num seq-num
     :buf (.asReadOnlyBuffer error-buf)}))

(defn- read-reply-buf [ch header-buf]
  (let [_ (-> header-buf (.limit 4) (.position 2))
        seq-num (gloss.io/decode :uint16 header-buf)
        len-buf (ByteBuffer/allocate 4)
        _ (.read ch len-buf)
        _ (.flip len-buf)
        reply-len (gloss.io/decode :uint32 len-buf)
        reply-buf (ByteBuffer/allocate (+ 32 (* reply-len 4)))]
    (.clear header-buf)
    (.clear len-buf)
    (.put reply-buf header-buf)
    (.put reply-buf len-buf)
    (.read ch reply-buf)
    {:seq-num seq-num
     :len reply-len
     :buf (.asReadOnlyBuffer reply-buf)}))

(defn- read-event-buf [ch header-buf]
  (let [_ (-> header-buf (.limit 1) (.position 0))
        tmp-event-num (gloss.io/decode :ubyte header-buf)
        event-num (bit-and tmp-event-num 0x7F)
        from-sendevent (not (zero? (bit-and tmp-event-num 0x80)))
        event-buf (ByteBuffer/allocate 32)]
    (.clear header-buf)
    (.put event-buf header-buf)
    (.read ch event-buf)
    {:event-num event-num
     :from-sendevent from-sendevent
     :buf (.asReadOnlyBuffer event-buf)}))

(defn- clear-old-replies [replyq seq-num]
  (when-let [{seqn :seq-num, reply :reply} (.peek replyq)]
    (when (not= seqn seq-num)
      (.take replyq)
      (deliver reply nil)
      (recur replyq seq-num))))

(defn- get-response [resp-q seq-num]
  (clear-old-replies resp-q seq-num)
  (let [{seqn :seq-num, :as r} (.peek resp-q)]
    (assert r (str "Sequence number corresponds to no request: " seq-num))
    (assert (= seqn seq-num) (str "Sequence number greater than all requests: " seq-num))
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

(defn- handle-error [err-buf replyq ext-cache]
  (let [resp (get-response replyq (:seq-num err-buf))
        err (common/read-error (:ext-name resp)
                               (get-error-number (:error-num err-buf) ext-cache)
                               err-buf)]
    (log/error "ERROR" err)
    (deliver (:reply resp) err)))

(defn- handle-reply [reply-buf replyq]
  (let [resp (get-response replyq (:seq-num reply-buf))
        reply (common/read-reply (:ext-name resp)
                                 (:opcode resp)
                                 reply-buf)]
    (log/debug "REPLY" reply)
    (deliver (:reply resp) reply)))

(defn- handle-event [evt-buf replyq eventq ext-cache]
  (let [{:keys [event-num ext-name]} (get-event-number (:event-num evt-buf)
                                                       ext-cache)
        evt (common/read-event ext-name event-num evt-buf)]
    (log/debug "EVENT" evt)
    (when-let [seq-num (:xcljb/sequence-number evt)] ; not KeymapNotify
      (clear-old-replies replyq seq-num))
    (.put eventq evt)))

(defn- read-channel [ch replyq eventq ext-cache]
  (while (.isOpen ch)
    (try
      (let [header-buf (ByteBuffer/allocate 4)
            _ (.read ch header-buf)
            _ (-> header-buf (.limit 1) (.position 0))
            type (gloss.io/decode :ubyte header-buf)]
        (case type
          0 (handle-error (read-error-buf ch header-buf)
                          replyq
                          ext-cache)
          1 (handle-reply (read-reply-buf ch header-buf) replyq)
          (handle-event (read-event-buf ch header-buf)
                        replyq
                        eventq
                        ext-cache)))

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
" Connects to Unix domain socket for given display. 
  The connection object should be taken as an opaque object, i.e. do
  not modify or inspect the connection object."
  [& {:keys [display]
      :or {display 0}}]
  (let [ SOCKET_PATH  (str "/tmp/.X11-unix/X" display)
        ch (. UnixSocketChannel open
           (new UnixSocketAddress (io/file SOCKET_PATH)))


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
