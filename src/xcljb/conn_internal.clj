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
  (let [next-seq-n (-> @conn (:seq-num) (inc) (bit-and 0xFFFF))
        reply-promise (promise)
        reply {:seq-num next-seq-n
               :opcode (:opcode request)
               :reply reply-promise}]
    (log/debug "REQUEST Opcode:" (:opcode request)
               "Sequence Number:" next-seq-n)
    (locking (:conn-lock @conn)
      (.put (:replies @conn) reply)
      (swap! conn assoc :seq-num next-seq-n)
      (.write (:ch @conn)
              (gio/contiguous
               (gio/encode (request->frame request)
                           (request->value request)))))
    reply-promise))
