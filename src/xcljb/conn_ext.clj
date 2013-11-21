(ns xcljb.conn-ext
  (:require [clojure.tools.logging :as log]
            [gloss.io :as gio]
            (xcljb [common :as common]
                   [gen-common :as gen-common])
            (xcljb.gen [xproto :as xproto]
                       [xproto-types :as xproto-types])))

(defn- update-ext-cache! [conn ext-name]
  (let [ext (xproto/query-extension conn (count ext-name) ext-name)]
    (if (:present @ext)
      (let [{:keys [exts event-bases error-bases]} (:ext-cache conn)]
        (dosync
         (commute exts assoc ext-name @ext)
         (commute event-bases assoc (:first-event @ext) ext-name)
         (commute error-bases assoc (:first-error @ext) ext-name)
         @ext))
      (throw (Exception.)))))

(defn- get-ext! [conn ext-name]
  (if-let [ext (@(get-in conn [:ext-cache :exts]) ext-name)]
    ext
    (update-ext-cache! conn ext-name)))

(defn- request->frame [request spec major-opcode]
  (concat [(gen-common/->frame xproto-types/CARD8 major-opcode)
           (gen-common/->frame xproto-types/CARD8 (:opcode spec))
           (gen-common/->frame xproto-types/CARD16 nil) ; length
           ]
          (gen-common/->frame spec request)
          ;; Paddings.
          (repeat (common/padding (gen-common/sizeof spec request))
                  (gen-common/->frame xproto-types/BYTE nil))))

(defn- request->value [request spec major-opcode]
  (let [size (gen-common/sizeof spec request)]
    (concat [major-opcode
             (:opcode spec)
             (int (Math/ceil (/ size 4)))]
            (gen-common/->value spec request)
            (repeat (common/padding size)
                    0))))

(defn send [conn ext-name spec request]
  ;; get-ext! might have to call send, so lock after retrieving extension.
  (let [major-opcode (:major-opcode (get-ext! conn ext-name))]
    (locking (:conn-lock conn)
      (let [seq-num (first @(:seq-nums conn))
            opcode (:opcode spec)
            reply-promise (promise)
            resp {:seq-num seq-num
                  :ext-name ext-name
                  :opcode opcode
                  :reply reply-promise}]
        (log/debug "REQUEST" ext-name
                   "Opcode:" major-opcode "/" opcode
                   "Sequence Number:" seq-num)

        (swap! (:seq-nums conn) rest)
        (.put (:replies conn) resp)
        (.write (:ch conn)
                (gio/contiguous
                 (gio/encode (request->frame request spec major-opcode)
                             (request->value request spec major-opcode))))
        reply-promise))))
