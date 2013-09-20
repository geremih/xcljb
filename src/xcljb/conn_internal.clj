(ns xcljb.conn-internal
  (:require [clojure.tools.logging :as log]
            [gloss.io :as gio]
            [xcljb.common :as common]
            [xcljb.gen.xproto-types :as xproto-types]))

(defn- request->frame [request]
  (let [raw-frame (.to-frame request)]
    (concat [(.to-frame xproto-types/CARD8)] ; opcode
            ;; request might be empty, e.g. QueryKeymap.
            (if (empty? raw-frame)
              [(.to-frame xproto-types/BYTE)] ; pad
              [(first raw-frame)])
            [(.to-frame xproto-types/CARD16)] ; length
            (rest raw-frame)
            ;; Paddings.
            (repeat (common/padding (.sizeof request))
                    (.to-frame xproto-types/BYTE)))))

(defn- request->value [request]
  (let [raw-value (.to-value request)
        size (.sizeof request)]
    (concat [(:opcode request)]
            (if (empty? raw-value)
              [0]
              [(first raw-value)])
            [(int (Math/ceil (/ size 4)))]
            (rest raw-value)
            (repeat (common/padding size)
                    0))))

(defn send [conn request]
  (locking (:conn-lock conn)
    (let [next-seq-n (first @(:seq-nums conn))
          reply-promise (promise)
          resp {:seq-num next-seq-n
                :opcode (:opcode request)
                :reply reply-promise}]
      (log/debug "REQUEST Opcode:" (:opcode request)
                 "Sequence Number:" next-seq-n)

      (swap! (:seq-nums conn) rest)
      (.put (:replies conn) resp)
      (.write (:ch conn)
              (gio/contiguous
               (gio/encode (request->frame request)
                           (request->value request))))
      reply-promise)))
