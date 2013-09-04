(ns xcljb.gen-common
  (:use [gloss io])
  (:require [clojure.tools.logging :as log]
            [gloss.core :as glcore])
  (:import [java.nio ByteBuffer]))

(defprotocol Serializable
  (to-frame [this])
  (to-value [this]))

(defprotocol Measurable
  (sizeof [this]))

(defprotocol ReadableType
  (read-type [this ch]))

(def ^:private BUFFER (ByteBuffer/allocateDirect 2048))

(defn read-bytes [ch bytes & {:keys [signed]
                              :or {signed false}}]
  (-> BUFFER (.position 0) (.limit bytes))
  (.read ch BUFFER)
  (.rewind BUFFER)
  (if signed
    (case bytes
      1 (decode :byte BUFFER)
      2 (decode :int16 BUFFER)
      4 (decode :int32 BUFFER))
    (case bytes
      1 (decode :ubyte BUFFER)
      2 (decode :uint16 BUFFER)
      4 (decode :uint32 BUFFER))))

(defn read-string [ch length]
  (-> BUFFER (.position 0) (.limit length))
  (.read ch BUFFER)
  (.rewind BUFFER)
  (decode (glcore/string :ascii) BUFFER))

(defn read-pad [ch bytes]
  (-> BUFFER (.position 0) (.limit bytes))
  (.read ch BUFFER)
  nil)

(defn padding [n]
  (rem (- 4 (rem n 4))
       4))

(defn bit-count [n]
  (.bitCount (BigInteger/valueOf n)))

(defrecord PrimitiveType [type]
  Measurable
  (sizeof [this]
    (case (:type this)
      (:ubyte :byte) 1
      (:uint16 :int16) 2
      (:uint32 :int32) 4))

  Serializable
  (to-frame [this]
    (:type this))
  (to-value [_]
    (throw (Exception. "Calling to-value on PrimitiveType.")))

  ReadableType
  (read-type [this ch]
    (case (:type this)
      :ubyte (read-bytes ch 1)
      :uint16 (read-bytes ch 2)
      :uint32 (read-bytes ch 4)
      :byte (read-bytes ch 1 :signed true)
      :int16 (read-bytes ch 2 :signed true)
      :int32 (read-bytes ch 4 :signed true))))

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
              (contiguous
               (encode (.to-frame request)
                       (.to-value request)))))
    reply-promise))

(defprotocol Valueparam
  (to-mask [this])
  (to-list [this]))
