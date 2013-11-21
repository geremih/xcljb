(ns xcljb.xmlgen.ir
  (:require [clojure.string :as string]))

(defn beautify [name type]
  (case type
    :ns-name (string/replace name #"_" "-")
    :arg (-> name
             (string/replace #"_" "-")
             (string/replace #"([a-z])([A-Z])" "$1-$2")
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
                 ;; e.g. "GetXIDList" -> "Get-XID-List".
                 (string/replace #"([A-Z])([A-Z][a-z])" "$1-$2")
                 (string/lower-case))
    :request (str name "Request")
    :reply (str name "Reply")
    :event (str name"Event")
    :error (str name "Error")
    :type (if (= name (string/upper-case name))
            (string/capitalize name)
            name)
    :read-type (str "read-" name)
    :->type (str "->" name)))

(defrecord QualifiedType [ns name])

(defn- parse-type [type]
  (let [{:keys [ns name]} type]
    (symbol (str "xcljb.gen." (beautify ns :ns-name) "-types") name)))

(defn- type->read-type [type]
  (let [{:keys [ns name]} type]
    (symbol (str "xcljb.gen." (beautify ns :ns-name) "-internal")
            (-> name (beautify :type) (beautify :read-type)))))

(defn- name->->type [context name]
  (symbol (str "xcljb.gen." (beautify (:header context) :ns-name) "-types")
          (-> name (beautify :type) (beautify :->type))))

(defn- gen-read-fields [fields & body]
  `(let ~(reduce #(conj %1 (.gen-read-type-name %2) (.gen-read-type %2))
                 []
                 fields)
     ~@body))

(defn- extension-name [context]
  (:extension-xname context))

(defprotocol RequestFn
  (gen-request-fn [this]))

(defprotocol Measurable
  (gen-sizeof [this])
  (gen-read-sizeof [this]))

(defprotocol Type
  (gen-type [this]))

(defprotocol Expr
  (gen-expr [this]))

(defprotocol ReadableFn
  (gen-read-fn [this]))

(defprotocol ReadableType
  (gen-read-type [this])
  (gen-read-type-name [this]))

;;; Expressions.

(defrecord Op [op expr1 expr2]
  Expr
  (gen-expr [this]
    (let [op (case (:op this)
               "+" 'clojure.core/+
               "-" 'clojure.core/-
               "*" 'clojure.core/*
               "/" 'clojure.core//
               "&" 'clojure.core/bit-and
               "<<" 'clojure.core/bit-shift-left)]
      `(xcljb.gen-common/->Op ~op
                              ~(gen-expr (:expr1 this))
                              ~(gen-expr (:expr2 this))))))

(defrecord Unop [op expr]
  Expr
  (gen-expr [this]
    (let [op (case (:op this)
               "~" 'clojure.core/bit-not)]
      `(xcljb.gen-common/->Unop ~op ~(gen-expr (:expr this))))))

(defrecord Fieldref [ref]
  Expr
  (gen-expr [this]
    `(xcljb.gen-common/->Fieldref ~(beautify (:ref this) :arg))))

(defrecord Popcount [expr]
  Expr
  (gen-expr [this]
    `(xcljb.gen-common/->Popcount ~(gen-expr (:expr this)))))

(defrecord Sumof [ref]
  Expr
  (gen-expr [this]
    `(xcljb.gen-common/->Sumof ~(beautify (:ref this) :arg))))

(defrecord Value [value]
  Expr
  (gen-expr [this]
    `(xcljb.gen-common/->Value ~(:value this))))

;;; Primitives.

(defrecord Primitive [name type]
  Type
  (gen-type [this]
    `(def ~(symbol (:name this))
       (xcljb.gen-common/->Primitive ~(:type this)))))

;;; Fields.

(defrecord Pad [bytes]
  Type
  (gen-type [this]
    `(xcljb.gen-common/->Pad ~(:bytes this))))

(defrecord BoolField [name size]
  Type
  (gen-type [this]
    `(xcljb.gen-common/->BoolField ~(beautify (:name this) :arg)
                                   ~(:size this))))

(defrecord StringField [name expr]
  Type
  (gen-type [this]
    `(xcljb.gen-common/->StringField ~(beautify (:name this) :arg)
                                     ~(when-let [expr (:expr this)]
                                        (gen-expr expr)))))

(defrecord Field [name type enum altenum mask]
  Type
  (gen-type [this]
    `(xcljb.gen-common/->Field ~(beautify (:name this) :arg)
                               ~(parse-type (:type this)))))

(defrecord List [name type enum altenum mask expr]
  Type
  (gen-type [this]
    `(xcljb.gen-common/->List ~(beautify (:name this) :arg)
                              ~(parse-type (:type this))
                              ~(when-let [expr (:expr this)]
                                 (gen-expr expr)))))

;; Valueparam.

(defrecord Valueparam [name mask-type]
  Type
  (gen-type [this]
    `(xcljb.gen-common/->Valueparam ~(beautify (:name this) :arg)
                                    ~(parse-type (:mask-type this)))))

;; Enum and Item.

(defrecord Item [name value])

;; Avoid naming conflict with java.lang.Enum.
(defrecord -Enum [name content])

(defn- instance-of? [inst classes]
  (some #(instance? % inst) classes))

(defn- gen-args [content]
  (let [fs (filter #(instance-of? % [BoolField
                                     StringField
                                     Field
                                     List
                                     Valueparam])
                   content)]
    (map #(-> % (:name) (beautify :arg)) fs)))

;; Struct.

(defrecord Struct [name content]
  Type
  (gen-type [this]
    `(def ~(symbol (:name this))
       (xcljb.gen-common/->Struct [~@(map gen-type (:content this))]))))

(defrecord Typedef [name type]
  Type
  (gen-type [this]
    `(def ~(symbol (:name this))
       ~(parse-type (:type this)))))

;; Request, Reply, Event, Error.

(defrecord Request [context name opcode combine-adjacent content]
  Measurable
  (gen-sizeof [this]
    `(+ ~(if (extension-name (:context this)) 4 3)
        ~@(map #(.gen-sizeof %) (:content this))))
  (gen-read-sizeof [this]
    (throw (Exception.)))

  RequestFn
  (gen-request-fn [this]
    (let [s-name (-> this (:name) (beautify :fn-name) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))
          s-struct (name->->type (:context this)
                                 (-> this (:name) (beautify :request)))
          conn (gensym "conn")
          request-struct (gensym "request-struct")]
      `(defn ~s-name [~conn ~@s-args]
         (let [~request-struct (~s-struct ~@s-args)]
           ~(if-let [ext-name (extension-name (:context this))]
              `(xcljb.conn-ext/send ~conn
                                    ~request-struct
                                    ~ext-name)
              `(xcljb.conn-internal/send ~conn
                                         ~request-struct))))))

  CodeSerializable
  (gen-to-frame [this]
    `[~@(map #(.gen-to-frame %) (:content this))])
  (gen-to-value [this]
    `[~@(map #(.gen-to-value %) (:content this))])

  Type
  (gen-type [this]
    (let [s-name (-> this (:name) (beautify :request) (symbol))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defrecord ~s-name [~@s-args]
         xcljb.common/Measurable
         (~(symbol "sizeof") [~(symbol "this")]
          ~(.gen-sizeof this))

         xcljb.common/Serializable
         (~(symbol "to-frame") [~(symbol "this")]
          ~(.gen-to-frame this))
         (~(symbol "to-value") [~(symbol "this")]
          ~(.gen-to-value this))

         xcljb.common/Request
         (~(symbol "opcode") [~(symbol "_")]
          ~(:opcode this))))))

(defrecord SequenceNumber []
  Measurable
  (gen-sizeof [_]
    2)
  (gen-read-sizeof [this]
    (.gen-sizeof this))

  ReadableType
  (gen-read-type [_]
    (let [s-ch (symbol "ch")
          s-read-bytes (symbol "xcljb.common" "read-bytes")]
      `(~s-read-bytes ~s-ch 2)))
  ;; FIXME: Pick a name without causing naming conflict.
  (gen-read-type-name [_]
    (symbol "seq-num")))

(defrecord Reply [context name request-opcode content]
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
          s-_ (symbol "_")
          ;; Has to be named "length", since e.g. GetKeyboardMapping requires it.
          s-len (symbol "length")
          ext-name (extension-name (:context this))
          opcode (:request-opcode this)
          s-reply (name->->type (:context this)
                                (-> this (:name) (beautify :reply)))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defmethod xcljb.common/read-reply [~ext-name ~opcode] [~s-_ ~s-_ ~s-ch ~s-len val#]
         (let [~(.gen-read-type-name (first (:content this))) val#]
           ~(gen-read-fields
             (rest (:content this))
             `(let [size# (+ 7 ~(.gen-read-sizeof this))
                    pads# (max (- 32 size#)
                               (xcljb.common/padding size#))]
                (xcljb.common/read-pad ~s-ch pads#))
             `(~s-reply ~@s-args)))))))

(defrecord Event [context name number no-seq-number content]
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
          s-_ (symbol "_")
          s-event (name->->type (:context this)
                                (-> this (:name) (beautify :event)))
          ext-name (extension-name (:context this))
          number (:number this)
          seq-num (->SequenceNumber)
          content (:content this)
          fields (if (:no-seq-number this)
                   content
                   (apply conj
                          [(first content)]
                          seq-num
                          (rest content)))
          s-args (map symbol (gen-args fields))]
      `(defmethod xcljb.common/read-event [~ext-name ~number] [~s-_ ~s-_ ~s-ch]
         ~(gen-read-fields
           fields
           `(let [size# (+ 1 ~(.gen-read-sizeof this))
                  pads# (max 0 (- 32 size#))]
              (xcljb.common/read-pad ~s-ch pads#))
           `{:seq-num ~(if (:no-seq-number this)
                         nil
                         (.gen-read-type-name seq-num))
             :event (~s-event ~@s-args)})))))

;; Avoid naming conflict with java.lang.Error.
(defrecord -Error [context name number content]
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
          s-_ (symbol "_")
          ext-name (extension-name (:context this))
          s-error (name->->type (:context this)
                                (-> this (:name) (beautify :error)))
          s-args (->> this (:content) (gen-args) (map symbol))]
      `(defmethod xcljb.common/read-error [~ext-name ~number] [~s-_ ~s-_ ~s-ch]
         ~(gen-read-fields
           (:content this)
           `(let [size# (+ 4 ~(.gen-read-sizeof this))
                  pads# (max 0 (- 32 size#))]
              (xcljb.common/read-pad ~s-ch pads#))
           `(~s-error ~@s-args))))))

;; Xcb.

(defrecord Xcb [header extension-name extension-xname extension-multiword major-version minor-version])
