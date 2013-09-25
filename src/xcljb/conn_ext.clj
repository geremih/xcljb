(ns xcljb.conn-ext
  (:require [clojure.tools.logging :as log]
            [gloss.io :as gio]
            [xcljb.common :as common]
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

(defn- request->frame [request]
  (concat [(.to-frame xproto-types/CARD8)] ; major opcode
          [(.to-frame xproto-types/CARD8)] ; minor opcode
          [(.to-frame xproto-types/CARD16)] ; length
          (.to-frame request)
          ;; Paddings.
          (repeat (common/padding (.sizeof request))
                  (.to-frame xproto-types/BYTE))))

(defn- request->value [request major-opcode]
  (let [size (.sizeof request)]
    (concat [major-opcode]
            [(.opcode request)]
            [(int (Math/ceil (/ size 4)))]
            (.to-value request)
            (repeat (common/padding size)
                    0))))

(defn send [conn request ext-name]
  ;; get-ext! might have to call xcljb.conn-internal/send, so lock after
  ;; retrieving extension.
  (let [major-opcode (:major-opcode (get-ext! conn ext-name))]
    (locking (:conn-lock conn)
      (let [next-seq-n (first @(:seq-nums conn))
            reply-promise (promise)
            resp {:seq-num next-seq-n
                  :ext-name ext-name
                  :opcode (.opcode request)
                  :reply reply-promise}]
        (log/debug "REQUEST" ext-name
                   "Opcode:" major-opcode "/" (.opcode request)
                   "Sequence Number:" next-seq-n)

        (swap! (:seq-nums conn) rest)
        (.put (:replies conn) resp)
        (.write (:ch conn)
                (gio/contiguous
                 (gio/encode (request->frame request)
                             (request->value request major-opcode))))
        reply-promise))))
