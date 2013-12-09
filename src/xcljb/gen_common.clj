(ns xcljb.gen-common
  (:require [gloss core io]
            [xcljb common]))

(defprotocol Measurable
  (sizeof [this value]))

(defprotocol Serializable
  (->frame [this value]
    "For fields, value is context instead.")
  (->value [this value]
    "For fields, value is context instead."))

(defprotocol Deserializable
  (deserialize [this buf context]))

(defprotocol Evalable
  (eval-expr [this context]))

;;; Expressions

(defrecord Op [op expr1 expr2]
  Evalable
  (eval-expr [_ context]
    (op (eval-expr expr1 context)
        (eval-expr expr2 context))))

(defrecord Unop [op expr]
  Evalable
  (eval-expr [_ context]
    (op (eval-expr expr context))))

(defrecord Fieldref [ref]
  Evalable
  (eval-expr [_ context]
    (let [v (get context (keyword ref))]
      (assert v)
      v)))

(defrecord Popcount [expr]
  Evalable
  (eval-expr [_ context]
    (xcljb.common/bit-count (eval-expr expr context))))

(defrecord Sumof [ref]
  Evalable
  (eval-expr [_ context]
    (let [v (get context (keyword ref))]
      (assert v)
      (reduce + 0 v))))

(defrecord Value [value]
  Evalable
  (eval-expr [_ _]
    value))

;;; Fields

(defrecord Pad [bytes]
  Measurable
  (sizeof [_ _]
    bytes)

  Serializable
  (->frame [_ _]
    (repeat bytes :byte))
  (->value [_ _]
    (repeat bytes 0))

  Deserializable
  (deserialize [_ buf _]
    (-> buf
        (.limit (.capacity buf))
        (.position (+ (.position buf) bytes)))
    ;; Since nobody cares about paddings, return 0s.
    (repeat bytes 0)))

(defrecord BoolField [name size]
  Measurable
  (sizeof [_ _]
    size)

  Serializable
  (->frame [_ _]
    (case size
      1 :ubyte
      4 :uint32))
  (->value [_ context]
    (let [v (get context (keyword name))]
      (assert (not (nil? v)))
      (if v 1 0)))

  Deserializable
  (deserialize [_ buf _]
    (.limit buf (+ (.position buf) size))
    (let [res (case size
                1 (gloss.io/decode :ubyte buf)
                4 (gloss.io/decode :uint32 buf))]
      (.position buf (.limit buf))
      (not (zero? res)))))

(defrecord StringField [name expr]
  Measurable
  (sizeof [_ s]
    (count s))

  Serializable
  (->frame [_ _]
    (gloss.core/string :ascii))
  (->value [_ context]
    (let [v (get context (keyword name))]
      (assert v)
      v))

  Deserializable
  (deserialize [_ buf context]
    (assert expr)
    (.limit buf (+ (.position buf)
                   (eval-expr expr context)))
    (let [res (gloss.io/decode (gloss.core/string :ascii) buf)]
      (.position buf (.limit buf))
      res)))

(defrecord Field [name type]
  Measurable
  (sizeof [_ v]
    (sizeof type v))

  Serializable
  (->frame [_ context]
    (->frame type (get context (keyword name))))
  (->value [_ context]
    (let [v (get context (keyword name))]
      (assert v)
      (->value type v)))

  Deserializable
  (deserialize [_ buf context]
    (deserialize type buf context)))

(defrecord BoolList [name size expr]
  Measurable
  (sizeof [_ bs]
    (* (count bs)
       size))

  Serializable
  (->frame [_ context]
    (repeat (count (get context (keyword name)))
            (case size
              1 :ubyte
              4 :uint32)))
  (->value [_ context]
    (let [bs (get context (keyword name))]
      (assert bs)
      (map #(if % 1 0) bs)))

  Deserializable
  (deserialize [_ buf context]
    (assert expr)
    (let [len (eval-expr expr context)
          _ (.limit buf (+ (.position buf)
                           (* len size)))
          frame (repeat len (case size
                              1 :ubyte
                              4 :uint32))
          res (gloss.io/decode frame buf)]
      (.position buf (.limit buf))
      res)))

(defrecord List [name type expr]
  Measurable
  (sizeof [_ vs]
    (reduce #(+ %1 (sizeof type %2))
            0
            vs))

  Serializable
  (->frame [_ context]
    (map #(->frame type %) (get context (keyword name))))
  (->value [_ context]
    (let [v (get context (keyword name))]
      (assert v)
      (map #(->value type %) v)))

  Deserializable
  (deserialize [_ buf context]
    (assert expr)
    (doall (for [_ (range (eval-expr expr context))]
             (deserialize type buf context)))))

(defrecord Valueparam [name mask-type]
  Measurable
  (sizeof [_ vp]
    ;; Mask type is always a primitive.
    (+ (sizeof mask-type nil)
       (* (count vp) 4)))

  ;; FIXME: For valueparam with mask type of "CARD16", the value-mask-name refers to actual, existing field.
  Serializable
  (->frame [_ context]
    ;; Mask type is always a primitive.
    (let [vp (get context (keyword name))]
      [(->frame mask-type nil)
       (repeat (count vp) :uint32)]))
  (->value [_ context]
    (let [vp (get context (keyword name))]
      (assert vp)
      [(reduce bit-or 0 (keys vp))
       (->> vp (sort-by key) (map second))]))

  Deserializable
  (deserialize [_ buf context]
    ;; HACK: Assume mask is CARD32.
    (let [mask (deserialize mask-type buf context)
          masks (for [m (iterate #(bit-shift-left % 1) 1)
                      :while (<= m mask)
                      :when (not (zero? (bit-and mask m)))]
                  m)
          _ (.limit buf (+ (.position buf)
                           (* (count masks) 4)))
          vs (gloss.io/decode (repeat (count masks) :uint32) buf)]
      (.position buf (.limit buf))
      (zipmap masks vs))))

(defrecord Stub [name reification]
  Measurable
  (sizeof [_ v]
    (sizeof reification v))

  Serializable
  (->frame [_ context]
    (->frame reification context))
  (->value [_ context]
    (->value reification context))

  Deserializable
  (deserialize [_ buf context]
    (deserialize reification buf context)))

;;; Concrete types

(defrecord Primitive [type]
  Measurable
  (sizeof [_ _]
    (case type
      (:ubyte :byte) 1
      (:uint16 :int16) 2
      (:uint32 :int32 :float32) 4
      :float64 8))

  Serializable
  (->frame [_ _]
    type)
  (->value [_ v]
    v)

  Deserializable
  (deserialize [this buf _]
    (.limit buf (+ (.position buf) (sizeof this nil)))
    (let [res (gloss.io/decode type buf)]
      (.position buf (.limit buf))
      res)))

(defrecord Struct [content]
  Measurable
  (sizeof [_ v]
    ;; sizeof works for Pads since it ignores second argument to its sizeof.
    (reduce #(+ %1 (sizeof %2 (get v (keyword (:name %2)))))
            0
            content))

  Serializable
  (->frame [_ v]
    (map #(->frame % v) content))
  (->value [_ v]
    (map #(->value % v) content))

  Deserializable
  (deserialize [_ buf _]
    (let [res (reduce #(conj %1 [(keyword (:name %2))
                                 (deserialize %2 buf %1)])
                      {}
                      content)]
      (dissoc res nil))))

;;; Request, reply, event, error

(defrecord Request [ext-name opcode content]
  Measurable
  (sizeof [_ request]
    (+ (if ext-name 4 3)
       (reduce #(+ %1 (sizeof %2 (get request (keyword (:name %2)))))
               0
               content)))

  Serializable
  (->frame [_ request]
    (map #(->frame % request) content))
  (->value [_ request]
    (map #(->value % request) content)))

(defrecord Reply [content]
  Measurable
  (sizeof [_ reply]
    (reduce #(+ %1 (sizeof %2 (get reply (keyword (:name %2)))))
            0
            content))

  Deserializable
  (deserialize [_ reply-buf _]
    (let [buf (:buf reply-buf)
          _ (.position buf 1)
          first-res (when-let [c (first content)]
                      [(keyword (:name c)) (deserialize c buf {})])
          init-res {:xcljb/sequence-number (:seq-num reply-buf)
                    ;; Has to be named "length", since e.g. GetKeyboardMapping requires it.
                    :length (:len reply-buf)}
          _ (-> buf (.clear) (.position 8))
          res (reduce #(conj %1 [(keyword (:name %2))
                                 (deserialize %2 buf %1)])
                      (conj init-res first-res)
                      (rest content))]
      (dissoc res nil))))

(defrecord Event [ext-name name number no-seq-number content]
  Measurable
  (sizeof [_ event]
    (let [len (reduce #(+ %1 (sizeof %2 (get event (keyword (:name %2)))))
                      0
                      content)]
      (if no-seq-number
        len
        ;; sizeof sequence number is 2.
        (+ 2 len))))

  Deserializable
  (deserialize [_ event-buf _]
    (let [buf (:buf event-buf)
          init-res {:xcljb/event (keyword ext-name name)
                    :xcljb/event-code number
                    :xcljb/from-sendevent (:from-sendevent event-buf)}
          _ (.position buf 1)
          res (if no-seq-number
                (reduce #(conj %1 [(keyword (:name %2)) (deserialize %2 buf %1)])
                        init-res
                        content)
                (let [first-res (when-let [c (first content)]
                                  [(keyword (:name c)) (deserialize c buf {})])
                      _ (-> buf (.limit 4) (.position 2))
                      seq-num [:xcljb/sequence-number (gloss.io/decode :uint16 buf)]
                      _ (-> buf (.clear) (.position 4))]
                  (reduce #(conj %1 [(keyword (:name %2)) (deserialize %2 buf %1)])
                          (conj init-res first-res seq-num)
                          (rest content))))]
      (dissoc res nil))))

(defrecord EventCopy [ext-name name number ref]
  Measurable
  (sizeof [_ event]
    (sizeof ref event))

  Deserializable
  (deserialize [_ event-buf _]
    (assoc (xcljb.common/read-event (:ext-name ref) (:number ref) event-buf)
      :xcljb/event (keyword ext-name name)
      :xcljb/event-code number)))

(defrecord Error' [ext-name name number content]
  Measurable
  (sizeof [_ error]
    (reduce #(+ %1 (sizeof %2 (get error (keyword (:name %2)))))
            0
            content))

  Deserializable
  (deserialize [_ error-buf _]
    (let [buf (:buf error-buf)
          _ (.position buf 4)
          res (reduce #(conj %1 [(keyword (:name %2)) (deserialize %2 buf %1)])
                      {:xcljb/error (keyword ext-name name)
                       :xcljb/error-code number
                       :xcljb/sequence-number (:seq-num error-buf)}
                      content)]
      (dissoc res nil))))

(defrecord ErrorCopy [ext-name name number ref]
  Measurable
  (sizeof [_ error]
    (sizeof ref error))

  Deserializable
  (deserialize [_ error-buf _]
    (assoc (xcljb.common/read-error (:ext-name ref) (:number ref) error-buf)
      :xcljb/error (keyword ext-name name)
      :xcljb/error-code number)))
