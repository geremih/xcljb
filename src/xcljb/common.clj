(ns xcljb.common
  (:use [gloss io])
  (:require [gloss.core :as glcore])
  (:import [java.nio ByteBuffer]))

(defmulti read-reply (fn [ext-name opcode _] [ext-name opcode]))

(defmulti read-event (fn [ext-name n _] [ext-name n]))

(defmulti read-error (fn [ext-name n _] [ext-name n]))

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

(defn padding [n]
  (rem (- 4 (rem n 4))
       4))

(defn bit-count [n]
  (.bitCount (BigInteger/valueOf n)))

