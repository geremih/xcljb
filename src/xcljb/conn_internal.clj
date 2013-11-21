(ns xcljb.conn-internal
  (:require [clojure.tools.logging :as log]
            [gloss.io :as gio]
            (xcljb [common :as common]
                   [gen-common :as gen-common])
            [xcljb.gen.xproto-types :as xproto-types]))

(defn- request->frame [request spec]
  (let [raw-frame (gen-common/->frame spec request)]
    (concat [(gen-common/->frame xproto-types/CARD8 (:opcode spec))
             ;; request might be empty, e.g. QueryKeymap.
             (if (empty? raw-frame)
               (gen-common/->frame xproto-types/BYTE nil) ; pad
               (first raw-frame))
             (gen-common/->frame xproto-types/CARD16 nil) ; length
             ]
            (rest raw-frame)
            ;; Paddings.
            (repeat (common/padding (gen-common/sizeof spec request))
                    (gen-common/->frame xproto-types/BYTE nil)))))

(defn- request->value [request spec]
  (let [raw-value (gen-common/->value spec request)
        size (gen-common/sizeof spec request)]
    (concat [(:opcode spec)
             (if (empty? raw-value)
               0
               (first raw-value))
             (int (Math/ceil (/ size 4)))]
            (rest raw-value)
            (repeat (common/padding size)
                    0))))

(defn send [conn spec request]
  (locking (:conn-lock conn)
    (let [seq-num (first @(:seq-nums conn))
          opcode (:opcode spec)
          reply-promise (promise)
          resp {:seq-num seq-num
                :opcode opcode
                :reply reply-promise}]
      (log/debug "REQUEST Opcode:" opcode
                 "Sequence Number:" seq-num)

      (swap! (:seq-nums conn) rest)
      (.put (:replies conn) resp)
      (.write (:ch conn)
              (gio/contiguous
               (gio/encode (request->frame request spec)
                           (request->value request spec))))
      reply-promise)))
