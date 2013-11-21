(ns xcljb.common)

(defmulti read-reply (fn [ext-name opcode _] [ext-name opcode]))

(defmulti read-event (fn [ext-name n _] [ext-name n]))

(defmulti read-error (fn [ext-name n _] [ext-name n]))

(defn padding [n]
  (rem (- 4 (rem n 4))
       4))

(defn bit-count [n]
  (.bitCount (BigInteger/valueOf n)))

