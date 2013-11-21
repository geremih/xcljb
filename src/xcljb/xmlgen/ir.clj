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

(defn- name->type [context name]
  (symbol (str "xcljb.gen." (beautify (:header context) :ns-name) "-types")
          (beautify name :type)))

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
  RequestFn
  (gen-request-fn [this]
    (let [s-name (-> this (:name) (beautify :fn-name) (symbol))
          s-spec (name->type (:context this)
                             (beautify (:name this) :request))
          args (-> this (:content) (gen-args))
          k-args (vec (map keyword args))
          s-args (vec (map symbol args))]
      `(defn ~s-name [~'conn ~@s-args]
         (let [~'request (zipmap ~k-args ~s-args)]
           ~(if-let [ext-name (extension-name (:context this))]
              `(xcljb.conn-ext/send ~'conn ~ext-name ~s-spec ~'request)
              `(xcljb.conn-internal/send ~'conn ~s-spec ~'request))))))

  Type
  (gen-type [this]
    `(def ~(-> this (:name) (beautify :request) (symbol))
       (xcljb.gen-common/->Request ~(extension-name (:context this))
                                   ~(:opcode this)
                                   [~@(map gen-type (:content this))]))))

(defrecord Reply [context name request-opcode content]
  Type
  (gen-type [this]
    `(def ~(-> this (:name) (beautify :reply) (symbol))
       (xcljb.gen-common/->Reply [~@(map gen-type (:content this))])))

  ReadableFn
  (gen-read-fn [this]
    (let [ext-name (extension-name (:context this))
          opcode (:request-opcode this)
          s-reply (name->type (:context this)
                              (-> this (:name) (beautify :reply)))]
      `(defmethod xcljb.common/read-reply [~ext-name ~opcode] [~'_ ~'_ ~'reply-buf]
         (xcljb.gen-common/deserialize ~s-reply ~'reply-buf nil)))))

(defrecord QualifiedRef [ext-name number])

(defrecord Event [context name number no-seq-number content]
  Type
  (gen-type [this]
    `(def ~(-> this (:name) (beautify :event) (symbol))
       (xcljb.gen-common/->Event ~(extension-name (:context this))
                                 ~(:name this)
                                 ~(:number this)
                                 ~(:no-seq-number this)
                                 [~@(map gen-type (:content this))])))

  ReadableFn
  (gen-read-fn [this]
    (let [ext-name (extension-name (:context this))
          number (:number this)
          s-event (name->type (:context this)
                              (-> this (:name) (beautify :event)))]
      `(defmethod xcljb.common/read-event [~ext-name ~number] [~'_ ~'_ ~'event-buf]
         (xcljb.gen-common/deserialize ~s-event ~'event-buf nil)))))

(defrecord EventCopy [context name number ref]
  Type
  (gen-type [this]
    `(def ~(-> this (:name) (beautify :event) (symbol))
       (xcljb.gen-common/->EventCopy ~(extension-name (:context this))
                                     ~(:name this)
                                     ~(:number this)
                                     ~(:ref this))))

  ReadableFn
  (gen-read-fn [this]
    (let [ext-name (extension-name (:context this))
          number (:number this)
          s-event (name->type (:context this)
                              (-> this (:name) (beautify :event)))]
      `(defmethod xcljb.common/read-event [~ext-name ~number] [~'_ ~'_ ~'event-buf]
         (xcljb.gen-common/deserialize ~s-event ~'event-buf nil)))))

;; Avoid naming conflict with java.lang.Error.
(defrecord -Error [context name number content]
  Type
  (gen-type [this]
    `(def ~(-> this (:name) (beautify :error) (symbol))
       (xcljb.gen-common/->Error' ~(extension-name (:context this))
                                  ~(:name this)
                                  ~(:number this)
                                  [~@(map gen-type (:content this))])))

  ReadableFn
  (gen-read-fn [this]
    (let [ext-name (extension-name (:context this))
          number (:number this)
          s-error (name->type (:context this)
                              (-> this (:name) (beautify :error)))]
      `(defmethod xcljb.common/read-error [~ext-name ~number] [~'_ ~'_ ~'error-buf]
         (xcljb.gen-common/deserialize ~s-error ~'error-buf nil)))))

(defrecord ErrorCopy [context name number ref]
  Type
  (gen-type [this]
    `(def ~(-> this (:name) (beautify :error) (symbol))
       (xcljb.gen-common/->ErrorCopy ~(extension-name (:context this))
                                     ~(:name this)
                                     ~(:number this)
                                     ~(:ref this))))

  ReadableFn
  (gen-read-fn [this]
    (let [ext-name (extension-name (:context this))
          number (:number this)
          s-error (name->type (:context this)
                              (-> this (:name) (beautify :error)))]
      `(defmethod xcljb.common/read-error [~ext-name ~number] [~'_ ~'_ ~'error-buf]
         (xcljb.gen-common/deserialize ~s-error ~'error-buf nil)))))

;; Xcb.

(defrecord Xcb [header extension-name extension-xname extension-multiword major-version minor-version])
