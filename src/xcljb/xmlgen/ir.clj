(ns xcljb.xmlgen.ir
  (:require [clojure.string :as string]))

(defn beautify [name type]
  (case type
    :arg (-> name
             (string/replace #"_" "-")
             (string/replace #"([a-z])([A-Z])" "$1-$2")
             ;(string/replace #"([A-Z])([A-Z][a-z])" "$1-$2")
             (string/lower-case))
    :enum (-> name
              (string/replace #"([a-z])([A-Z])" "$1-$2")
              (string/upper-case))
    :enum-item (-> name
                   (string/replace #"_" "-")
                   (string/replace #"([a-z])([A-Z])" "$1-$2")
                   (string/lower-case))
    :fn-name (-> name
                 (string/replace #"([a-z])([A-Z])" "$1-$2")
                 (string/lower-case))
    :request (str name "Request")
    :reply (str name "Reply")
    :event (str name"Event")
    :error (str name "Error")
    :read-type (str "read-" name)
    :->type (str "->" name)))

(defrecord QualifiedType [ns name])

(defn- parse-type [type]
  (let [{:keys [ns name]} type]
    (symbol (str "xcljb.gen." ns "-types") name)))

(defn- type->read-type [type]
  (let [{:keys [ns name]} type]
    (symbol (str "xcljb.gen." ns "-internal")
            (beautify name :read-type))))

(defn- name->->type [header name]
  (symbol (str "xcljb.gen." header "-types")
          (beautify name :->type)))

(defn- gen-read-fields [fields & body]
  `(let ~(reduce #(conj %1 (.gen-read-type-name %2) (.gen-read-type %2))
                 []
                 fields)
     ~@body))

(defprotocol RequestFn
  (gen-request-fn [this]))

(defprotocol CodeSerializable
  (gen-to-frame [this])
  (gen-to-value [this]))

(defprotocol Measurable
  (gen-sizeof [this])
  (gen-read-sizeof [this]))

(defprotocol Type
  (gen-type [this]))

(defprotocol ReadableFn
  (gen-read-fn [this]))

(defprotocol ReadableType
  (gen-read-type [this])
  (gen-read-type-name [this]))

(defprotocol Evalable
  (gen-eval [this]))

;;; Expressions.

(defrecord Op [op expr1 expr2]
  Evalable
  (gen-eval [this]
    (let [v1 (.gen-eval (:expr1 this))
          v2 (.gen-eval (:expr2 this))]
      (case (:op this)
        "+" `(+ ~v1 ~v2)
        "-" `(- ~v1 ~v2)
        "*" `(* ~v1 ~v2)
        "/" `(/ ~v1 ~v2)
        "&" `(bit-and ~v1 ~v2)
        "<<" `(bit-shift-left ~v1 ~v2)))))

(defrecord Unop [op expr]
  Evalable
  (gen-eval [this]
    (let [v (.gen-eval (:expr this))]
      (case (:op this)
        "~" `(bit-not ~v)))))

(defrecord Fieldref [ref]
  Evalable
  (gen-eval [this]
    (-> this (:ref) (beautify :arg) (symbol))))

(defrecord Popcount [expr]
  Evalable
  (gen-eval [this]
    (let [v (.gen-eval (:expr this))]
      `(.bitCount (BigInteger/valueOf ~v)))))

(defrecord Sumof [ref]
  Evalable
  (gen-eval [this]
    (let [s-ref (-> this (:ref) (beautify :arg) (symbol))]
      `(apply + ~s-ref))))

(defrecord Value [value]
  Evalable
  (gen-eval [this]
    (:value this)))

;;; Fields.

(defrecord Pad [bytes]
  Measurable
  (gen-sizeof [this]
    (:bytes this))
  (gen-read-sizeof [this]
    (.gen-sizeof this))

  CodeSerializable
  (gen-to-frame [this]
    `(repeat ~(:bytes this) :byte))
  (gen-to-value [this]
    `(repeat ~(:bytes this) 0))

  ReadableType
  (gen-read-type [this]
    (let [s-ch (symbol "ch")
          s-read-pad (symbol "xcljb.gen-common" "read-pad")]
      `(~s-read-pad ~s-ch ~(:bytes this))))
  (gen-read-type-name [_]
    (symbol "_")))

(defrecord BoolField [name size]
  Measurable
  (gen-sizeof [this]
    (:size this))
  (gen-read-sizeof [this]
    (.gen-sizeof this))

  CodeSerializable
  (gen-to-frame [this]
    (case (:size this)
      1 :ubyte
      4 :uint32))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(if (~k-name ~s-this)
         1
         0)))

  ReadableType
  (gen-read-type [this]
    (let [s-ch (symbol "ch")]
      `(if (= (xcljb.gen-common/read-bytes ~s-ch ~(:size this)) 1)
         true
         false)))
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

(defrecord StringField [name expr]
  Measurable
  (gen-sizeof [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(count (~k-name ~s-this))))
  (gen-read-sizeof [this]
    (assert (:expr this))
    (.gen-eval (:expr this)))

  CodeSerializable
  (gen-to-frame [this]
    `(gloss.core/string :ascii))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(~k-name ~s-this)))

  ReadableType
  (gen-read-type [this]
    (assert (:expr this))
    (let [s-ch (symbol "ch")
          len (-> this (:expr) (.gen-eval))]
      `(xcljb.gen-common/read-string ~s-ch ~len)))
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

(defrecord PrimitiveField [name type enum altenum mask]
  Measurable
  (gen-sizeof [this]
    (let [s-type (-> this (:type) (parse-type))]
      `(.sizeof ~s-type)))
  (gen-read-sizeof [this]
    (.gen-sizeof this))

  CodeSerializable
  (gen-to-frame [this]
    (let [s-type (-> this (:type) (parse-type))]
      `(.to-frame ~s-type)))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(~k-name ~s-this)))

  ReadableType
  (gen-read-type [this]
    (let [s-ch (symbol "ch")
          s-type (-> this (:type) (parse-type))]
      `(.read-type ~s-type ~s-ch)))
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

(defrecord Field [name type enum altenum mask]
  Measurable
  (gen-sizeof [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(.sizeof (~k-name ~s-this))))
  (gen-read-sizeof [this]
    (let [s-name (-> this (:name) (beautify :arg) (symbol))]
      `(.sizeof ~s-name)))

  CodeSerializable
  (gen-to-frame [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(.to-frame (~k-name ~s-this))))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(.to-value (~k-name ~s-this))))

  ReadableType
  (gen-read-type [this]
    (let [s-ch (symbol "ch")
          s-read-type (-> this (:type) (type->read-type))]
      `(~s-read-type ~s-ch)))
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

(defrecord PrimitiveList [name type enum altenum mask expr]
  Measurable
  (gen-sizeof [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))
          s-type (-> this (:type) (parse-type))]
      `(* (.sizeof ~s-type)
          (count (~k-name ~s-this)))))
  (gen-read-sizeof [this]
    (assert (:expr this))
    (let [s-type (-> this (:type) (parse-type))]
      `(* (.sizeof ~s-type)
          ~(.gen-eval (:expr this)))))

  CodeSerializable
  (gen-to-frame [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (keyword))
          s-type (-> this (:type) (parse-type))]
      `(repeat (count (~k-name ~s-this))
               (.to-frame ~s-type))))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(~k-name ~s-this)))

  ReadableType
  (gen-read-type [this]
    (assert (:expr this))
    (let [s-ch (symbol "ch")
          s-type (-> this (:type) (parse-type))
          len (-> this (:expr) (.gen-eval))]
      `(doall (repeatedly ~len (fn [] (.read-type ~s-type ~s-ch))))))
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

(defrecord List [name type enum altenum mask expr]
  Measurable
  (gen-sizeof [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(reduce (fn [x# y#] (+ x# (.sizeof y#)))
               0
               (~k-name ~s-this))))
  (gen-read-sizeof [this]
    (let [s-name (-> this (:name) (beautify :arg) (symbol))]
      `(reduce (fn [x# y#] (+ x# (.sizeof y#)))
               0
               ~s-name)))

  CodeSerializable
  (gen-to-frame [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(map #(.to-frame %)
            (~k-name ~s-this))))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `(map #(.to-value %)
            (~k-name ~s-this))))

  ReadableType
  (gen-read-type [this]
    (assert (:expr this))
    (let [s-ch (symbol "ch")
          s-read-type (-> this (:type) (type->read-type))
          len (-> this (:expr) (.gen-eval))]
      `(doall (repeatedly ~len (fn [] (~s-read-type ~s-ch))))))
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

;; Valueparam.

(defrecord Valueparam [name mask-type]
  Measurable
  (gen-sizeof [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))
          s-mask-type (-> this (:mask-type) (parse-type))]
      `(+ (.sizeof ~s-mask-type)
          (* (-> ~s-this (~k-name) (:list) (count))
             4))))
  ;; TODO
  (gen-read-sizeof [this])

  ;; FIXME: For valueparam with mask type of "CARD16", the value-mask-name refers to actual, existing field.
  CodeSerializable
  (gen-to-frame [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))
          ;; Mask type is always a primitive type.
          s-mask-type (-> this (:mask-type) (parse-type))]
      `[(.to-frame ~s-mask-type)
        (repeat (count (.to-list (~k-name ~s-this)))
                :uint32)]))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          k-name (-> this (:name) (beautify :arg) (keyword))]
      `[(.to-mask (~k-name ~s-this))
        (.to-list (~k-name ~s-this))]))

  ReadableType
  ;; TODO
  (gen-read-type [this])
  (gen-read-type-name [this]
    (-> this (:name) (beautify :arg) (symbol))))

;; Enum and Item.

(defrecord Item [name value])

;; Avoid naming conflict with java.lang.Enum.
(defrecord -Enum [name content])

(defn- instance-of? [inst classes]
  (some #(instance? % inst) classes))

(defn- gen-args [content]
  (let [fs (filter #(instance-of? % [BoolField
                                     StringField
                                     PrimitiveField
                                     Field
                                     PrimitiveList
                                     List
                                     Valueparam])
                   content)]
    (map #(-> % (:name) (beautify :arg)) fs)))

;; Struct.

(defrecord Struct [header name content]
  Measurable
  (gen-sizeof [this]
    `(+ ~@(map #(.gen-sizeof %) (:content this))))
  (gen-read-sizeof [this]
    `(+ ~@(map #(.gen-read-sizeof %) (:content this))))

  CodeSerializable
  (gen-to-frame [this]
    ;; Has to be a vector, to prevent keyword being interpreted as function.
    `[~@(map #(.gen-to-frame %) (:content this))])
  (gen-to-value [this]
    `[~@(map #(.gen-to-value %) (:content this))])

  Type
  (gen-type [this]
    (let [s-name (-> this (:name) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defrecord ~s-name [~@s-args]
         xcljb.gen-common/Measurable
         (~(symbol "sizeof") [~(symbol "this")]
          ~(.gen-sizeof this))

         xcljb.gen-common/Serializable
         (~(symbol "to-frame") [~(symbol "this")]
          ~(.gen-to-frame this))
         (~(symbol "to-value") [~(symbol "this")]
          ~(.gen-to-value this)))))

  ReadableFn
  (gen-read-fn [this]
    (let [s-ch (symbol "ch")
          s-name (-> this (:name) (beautify :read-type) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))
          s-struct (name->->type (:header this) (:name this))]
      `(defn ~s-name [~s-ch]
         ~(gen-read-fields
           (:content this)
           `(~s-struct ~@s-args))))))

;; Request, Reply, Event, Error.

(defrecord Request [header name opcode combine-adjacent content]
  Measurable
  (gen-sizeof [this]
    `(+ 3
        ~@(map #(.gen-sizeof %) (:content this))))
  (gen-read-sizeof [this]
    (throw (Exception.)))

  RequestFn
  (gen-request-fn [this]
    (let [s-name (-> this (:name) (beautify :fn-name) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))
          s-struct (name->->type (:header this)
                                 (-> this (:name) (beautify :request)))
          opcode (:opcode this)]
      `(defn ~s-name [conn# ~@s-args]
         (let [request-struct# (~s-struct ~opcode ~@s-args)]
           (xcljb.gen-common/send conn#
                                  request-struct#)))))

  CodeSerializable
  (gen-to-frame [this]
    (let [s-this (symbol "this")
          content-frame (map #(.gen-to-frame %) (:content this))]
      ;; Has to be a vector, to prevent keyword being interpreted as function.
      `[~@(concat [:ubyte]
                  [(first content-frame)]
                  [:uint16]
                  (rest content-frame)
                  [`(repeat (xcljb.gen-common/padding (.sizeof ~s-this))
                            :byte)])]))
  (gen-to-value [this]
    (let [s-this (symbol "this")
          content-value (map #(.gen-to-value %) (:content this))]
      `[~@(concat [(:opcode this)]
                  [(first content-value)]
                  [`(int (Math/ceil (/ (.sizeof ~s-this)
                                       4)))]
                  (rest content-value)
                  [`(repeat (xcljb.gen-common/padding (.sizeof ~s-this))
                            0)])]))

  Type
  (gen-type [this]
    (let [s-opcode (symbol "opcode")
          s-name (-> this (:name) (beautify :request) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defrecord ~s-name [~s-opcode ~@s-args]
         xcljb.gen-common/Measurable
         (~(symbol "sizeof") [~(symbol "this")]
          ~(.gen-sizeof this))

         xcljb.gen-common/Serializable
         (~(symbol "to-frame") [~(symbol "this")]
          ~(.gen-to-frame this))
         (~(symbol "to-value") [~(symbol "this")]
          ~(.gen-to-value this))))))

(defrecord SequenceNumber []
  Measurable
  (gen-sizeof [_]
    2)
  (gen-read-sizeof [this]
    (.gen-sizeof this))

  ReadableType
  (gen-read-type [_]
    (let [s-ch (symbol "ch")
          s-read-bytes (symbol "xcljb.gen-common" "read-bytes")]
      `(~s-read-bytes ~s-ch 2)))
  ;; FIXME: Pick a name without causing naming conflict.
  (gen-read-type-name [_]
    (symbol "seq-num")))

(defrecord Reply [header name request-opcode content]
  Measurable
  (gen-sizeof [this]
    (throw (Exception.)))
  (gen-read-sizeof [this]
    `(+ ~@(map #(.gen-read-sizeof %) (:content this))))

  Type
  (gen-type [this]
    (let [s-name (-> this (:name) (beautify :reply) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defrecord ~s-name [~@s-args])))

  ReadableFn
  (gen-read-fn [this]
    (let [s-ch (symbol "ch")
          name (-> this (:name) (beautify :reply))
          s-name (-> name (beautify :read-type) (symbol))
          s-reply (name->->type (:header this)
                                (-> this (:name) (beautify :reply)))
          s-read-pad (symbol "xcljb.gen-common" "read-pad")
          s-args (->> this (:content) (gen-args) (map symbol))
          s-len (gensym "len__")]
      `(defn ~s-name [~s-ch ~s-len val#]
         (let [~(.gen-read-type-name (first (:content this))) val#]
           ~(gen-read-fields
             (rest (:content this))
             `(let [size# ~(.gen-read-sizeof this)]
                (cond
                 (< size# 25) (~s-read-pad ~s-ch
                                           (- 25 size#))
                 (< size# (+ 25 ~s-len)) (~s-read-pad ~s-ch
                                                      (- (+ 25 ~s-len) size#))))
             `(~s-reply ~@s-args)))))))

(defrecord Event [header name number no-seq-number content]
  Measurable
  (gen-sizeof [this]
    (throw (Exception.)))
  (gen-read-sizeof [this]
    (if (:no-seq-number this)
      `(+ ~@(map #(.gen-read-sizeof %) (:content this)))
      `(+ ~(.gen-read-sizeof (->SequenceNumber))
          ~@(map #(.gen-read-sizeof %) (:content this)))))

  Type
  (gen-type [this]
    (let [s-name (-> this (:name) (beautify :event) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defrecord ~s-name [~@s-args])))

  ReadableFn
  (gen-read-fn [this]
    (let [s-ch (symbol "ch")
          name (-> this (:name) (beautify :event))
          s-name (-> name (beautify :read-type) (symbol))
          s-event (name->->type (:header this)
                                (-> this (:name) (beautify :event)))
          seq-num (->SequenceNumber)
          content (:content this)
          fields (if (:no-seq-number this)
                   content
                   (apply conj
                          [(first content)]
                          seq-num
                          (rest content)))
          s-read-pad (symbol "xcljb.gen-common" "read-pad")
          s-args (map symbol (gen-args fields))]
      `(defn ~s-name [~s-ch]
         ~(gen-read-fields
           fields
           `(let [size# ~(.gen-read-sizeof this)]
              (when (< size# 31)
                (~s-read-pad ~s-ch
                             (- 31 size#))))
           `{:seq-num ~(if (:no-seq-number this)
                         nil
                         (.gen-read-type-name seq-num))
             :event (~s-event ~@s-args)})))))

;; Avoid naming conflict with java.lang.Error.
(defrecord -Error [header name number content]
  Measurable
  (gen-sizeof [this]
    (throw (Exception.)))
  (gen-read-sizeof [this]
    `(+ ~@(map #(.gen-read-sizeof %) (:content this))))

  Type
  (gen-type [this]
    (let [s-name (-> this (:name) (beautify :error) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defrecord ~s-name [~@s-args])))

  ReadableFn
  (gen-read-fn [this]
    (let [s-ch (symbol "ch")
          name (-> this (:name) (beautify :error))
          s-name (-> name (beautify :read-type) (symbol))
          s-error (name->->type (:header this)
                                (-> this (:name) (beautify :error)))
          s-read-pad (symbol "xcljb.gen-common" "read-pad")
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defn ~s-name [~s-ch]
         ~(gen-read-fields
           (:content this)
           `(let [size# ~(.gen-read-sizeof this)]
              (when (< size# 28)
                (~s-read-pad ~s-ch
                             (- 28 size#))))
           `(~s-error ~@s-args))))))

;; Xcb.

(defrecord Xcb [header extension-name extension-xname extension-multiword major-version minor-version])
