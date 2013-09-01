(ns xcljb.xmlgen.xml2ir
  (:require [clojure.string :as string]
            [xcljb.xmlgen.ir :as ir]))

(def ^:private SKIP {"xproto" {:union #{"ClientMessageData"}
                               :request #{"ConfigureWindow"
                                          "QueryTextExtents"
                                          "GetImage"
                                          "GetKeyboardMapping"
                                          "ImageText16"}
                               :event #{"ClientMessage"}}
                     "randr" {:union #{"NotifyData"}
                              :event #{"Notify"}}})

(def ^:private PRIMITIVE-TYPES (atom {"BOOL" :ubyte
                                      "BYTE" :ubyte
                                      "INT8" :byte
                                      "INT16" :int16
                                      "INT32" :int32
                                      "CARD8" :ubyte
                                      "CARD16" :uint16
                                      "CARD32" :uint32
                                      "char" :ubyte
                                      "void" :ubyte}))
(def ^:private TYPES (atom []))
(def ^:private ENUMS (atom {}))
(def ^:private REQUESTS (atom []))
(def ^:private REPLIES (atom []))
(def ^:private EVENTS (atom []))
(def ^:private ERRORS (atom []))

(defn- parse-expression [elem]
  (case (:tag elem)
    :op (let [op (-> elem (:attrs) (:op))
              content (:content elem)
              expr1 (-> content (first) (parse-expression))
              expr2 (-> content (second) (parse-expression))]
          (ir/->Op op expr1 expr2))
    :unop (let [op (-> elem (:attrs) (:op))
                expr (-> elem (:content) (first) (parse-expression))]
            (ir/->Unop op expr))
    :fieldref (let [ref (-> elem (:content) (first))]
                (ir/->Fieldref ref))
    :enumref (let [ref (-> elem (:attrs) (:ref) (@ENUMS))
                   item (-> elem (:content) (first))
                   ref-item (first (filter #(= (:name %) item)
                                           (:content ref)))]
               (ir/->Value (:value ref-item)))
    :popcount (let [expr (-> elem (:content) (first) (parse-expression))]
                (ir/->Popcount expr))
    :sumof (let [ref (-> elem (:attrs) (:ref))]
             (ir/->Sumof ref))
    :value (let [val (-> elem (:content) (first) (Long/decode))]
             (ir/->Value val))
    :bit (let [val (-> elem (:content) (first) (Integer/parseInt))]
           (ir/->Value (bit-set 0 val)))))

(defn- parse-pad [elem]
  (let [bytes (-> elem (:attrs) (:bytes) (Long/parseLong))]
    (ir/->Pad bytes)))

(defn- parse-field [elem]
  (let [{:keys [name type enum altenum mask]} (:attrs elem)
        prim? (@PRIMITIVE-TYPES type)]
    (if prim?
      (ir/->PrimitiveField name type enum altenum mask)
      (ir/->Field name type enum altenum mask))))

(defn- parse-list [elem]
  (let [{:keys [name type enum altenum mask]} (:attrs elem)
        expr (if-let [e (-> elem (:content) (first))]
               (parse-expression e)
               nil)
        prim? (@PRIMITIVE-TYPES type)]
    (if prim?
      (ir/->PrimitiveList name type enum altenum mask expr)
      (ir/->List name type enum altenum mask expr))))

(defn- parse-valueparam [elem]
  (assert (= (:tag elem) :valueparam))
  (let [attrs (:attrs elem)
        name (-> attrs (:value-mask-name) (string/replace #"_mask$" ""))
        mask-type (-> attrs (:value-mask-type))]
    (ir/->Valueparam name mask-type)))

(defn- parse-enum [elem]
  (assert (= (:tag elem) :enum))
  (let [name (-> elem (:attrs) (:name))
        enum (loop [content (:content elem)
                    cnt 0
                    items []]
               (if (empty? content)
                 (ir/->-Enum name items)
                 (let [c (first content)]
                   (if (= (:tag c) :doc)
                     (recur (rest content) cnt items)
                     (let [item-name (-> c (:attrs) (:name))
                           item-content (-> c (:content) (first))]
                       (if item-content
                         (let [val (:value (parse-expression item-content))]
                           (recur (rest content)
                                  (inc val)
                                  (conj items (ir/->Item item-name val))))
                         (recur (rest content)
                                (inc cnt)
                                (conj items (ir/->Item item-name cnt)))))))))]
    (swap! ENUMS conj [name enum])
    enum))

(defn- parse-xids [elem]
  (assert (#{:xidtype :xidunion} (:tag elem)))
  (let [name (-> elem (:attrs) (:name))]
    (swap! PRIMITIVE-TYPES conj [name :uint32])))

(defn- parse-typedef [elem]
  (let [attrs (:attrs elem)
        oldname (:oldname attrs)
        newname (:newname attrs)]
    (if-let [prim-type (@PRIMITIVE-TYPES oldname)]
      (swap! PRIMITIVE-TYPES conj [newname prim-type])
      (let [comp-type (@TYPES oldname)]
        (assert comp-type)
        (swap! TYPES conj comp-type)))))

(defn- parse-content [content]
  (for [c content
        :when (not (#{:reply :switch :exprfield :doc} (:tag c)))]
    (case (:tag c)
      :pad (parse-pad c)
      :field (parse-field c)
      :list (parse-list c)
      :valueparam (parse-valueparam c))))

(defn- parse-struct [elem]
  (assert (= (:tag elem) :struct))
  (let [name (-> elem (:attrs) (:name))
        content (parse-content (:content elem))
        struct (ir/->Struct name content)]
    (swap! TYPES conj struct)
    struct))

(defn- parse-reply [name request-opcode elem]
  (let [content (parse-content (:content elem))
        reply (ir/->Reply name request-opcode content)]
    (swap! REPLIES conj reply)
    reply))

(defn- parse-request [elem]
  (assert (= (:tag elem) :request))
  (let [attrs (:attrs elem)
        name (-> attrs (:name))
        opcode (-> attrs (:opcode) (Integer/parseInt))
        combine-adjacent (= (:combine-adjacent attrs) "true")
        content (:content elem)
        request-content (parse-content content)
        reply-elem (first (filter #(= (:tag %) :reply) content))
        request (ir/->Request name opcode combine-adjacent request-content)]
    (swap! REQUESTS conj request)
    (when reply-elem
      (parse-reply name opcode reply-elem))
    request))

(defn- parse-event [elem]
  (let [attrs (:attrs elem)
        name (:name attrs)
        num (-> attrs (:number) (Integer/parseInt))
        no-seq-num (= (:no-sequence-number attrs) "true")
        content (parse-content (:content elem))
        event (ir/->Event name num no-seq-num content)]
    (swap! EVENTS conj event)
    event))

(defn- parse-eventcopy [elem]
  (let [attrs (:attrs elem)
        name (:name attrs)
        num (-> attrs (:number) (Integer/parseInt))
        ref (first (filter #(= (:name %) (:ref attrs)) @EVENTS))
        event (assoc ref :name name :number num)]
    (assert ref)
    (swap! EVENTS conj event)
    event))

(defn- parse-error [elem]
  (let [attrs (:attrs elem)
        name (:name attrs)
        num (-> attrs (:number) (Integer/parseInt))
        content (parse-content (:content elem))
        error (ir/->-Error name num content)]
    (swap! ERRORS conj error)
    error))

(defn- parse-errorcopy [elem]
  (let [attrs (:attrs elem)
        name (:name attrs)
        num (-> attrs (:number) (Integer/parseInt))
        ref (first (filter #(= (:name %) (:ref attrs)) @ERRORS))
        error (assoc ref :name name :number num)]
    (assert ref)
    (swap! ERRORS conj error)
    error))

(defn- parse-import [elem])

(defn- parse-xcb [elem]
  (assert (= (:tag elem) :xcb))
  (let [attrs (:attrs elem)
        header (:header attrs)
        ext-name (:extension-name attrs)
        ext-xname (:extension-xname attrs)
        ext-multiword (= (:extension-multiword attrs) "true")
        major-version (if-let [v (:major-version attrs)]
                        (Integer/parseInt v)
                        nil)
        minor-version (if-let [v (:minor-version attrs)]
                        (Integer/parseInt v)
                        nil)
        content (parse-content (:content elem))]
    (ir/->Xcb header ext-xname ext-name ext-multiword major-version minor-version)))

(defn xml->ir [elem]
  (let [xcb (parse-xcb elem)]
    (doseq [e (:content elem)]
      (case (:tag e)
        :import (parse-import e)
        :enum (parse-enum e)
        (:xidtype :xidunion) (parse-xids e)
        :typedef (parse-typedef e)
        :struct (parse-struct e)
        :union nil
        :request (when-not ((get-in SKIP [(:header xcb) :request])
                            (-> e (:attrs) (:name)))
                   (parse-request e))
        :event (when-not ((get-in SKIP [(:header xcb) :event])
                          (-> e (:attrs) (:name)))
                 (parse-event e))
        :eventcopy (parse-eventcopy e)
        :error (parse-error e)
        :errorcopy (parse-errorcopy e)))
    [xcb @PRIMITIVE-TYPES @TYPES @ENUMS @REQUESTS @REPLIES @EVENTS @ERRORS]))
