(ns xcljb.common
  (:use [gloss io])
  (:require [gloss.core :as glcore])
  (:import [java.nio ByteBuffer]))

(defprotocol Serializable
  (to-frame [this])
  (to-value [this]))

(defprotocol Measurable
  (sizeof [this]))

(defprotocol Request
  (opcode [this]))

(defprotocol ReadableType
  (read-type [this ch]))

(defmulti read-reply (fn [ext-name opcode & _] [ext-name opcode]))

(defmulti read-event (fn [ext-name n & _] [ext-name n]))

(defmulti read-error (fn [ext-name n & _] [ext-name n]))

(def ^:private BUFFER (ByteBuffer/allocateDirect 2048))

(defn read-bytes [ch bytes & {:keys [signed float]
                              :or {signed false, float false}}]
  (-> BUFFER (.position 0) (.limit bytes))
  (.read ch BUFFER)
  (.rewind BUFFER)
  (cond
   (and float (= bytes 4)) (decode :float32 BUFFER)
   (and float (= bytes 8)) (decode :float64 BUFFER)
   signed (case bytes
            1 (decode :byte BUFFER)
            2 (decode :int16 BUFFER)
            4 (decode :int32 BUFFER))
   :else (case bytes
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

(defn valueparam->value [vp]
  [(reduce bit-or 0 (keys vp))
   (->> vp (sort-by key) (map second))])

(defn mask->masks [mask]
  (for [m (iterate #(bit-shift-left % 1) 1)
        :while (<= m mask)
        :when (not (zero? (bit-and mask m)))]
    m))

(defrecord PrimitiveType [type]
  Measurable
  (sizeof [this]
    (case (:type this)
      (:ubyte :byte) 1
      (:uint16 :int16) 2
      (:uint32 :int32 :float32) 4
      :float64 8))

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
      :int32 (read-bytes ch 4 :signed true)
      :float32 (read-bytes ch 4 :float true)
      :float64 (read-bytes ch 8 :float true))))
