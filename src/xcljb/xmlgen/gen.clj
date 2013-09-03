(ns xcljb.xmlgen.gen
  (:require [clojure.pprint :as pp]
            [clojure.data.xml :as xml]
            [xcljb.xmlgen.ir :as ir]
            [xcljb.xmlgen.xml2ir :as xml2ir])
  (:import [xcljb.xmlgen.ir Struct]))

(defn- gen-enum [enum]
  (let [s-name (-> enum (:name) (ir/beautify :enum) (symbol))
        content (for [item (:content enum)]
                  [(-> item (:name) (ir/beautify :enum-item) (keyword))
                   (:value item)])]
    `(def ~s-name ~(reduce conj {} content))))

(defn- gen-read-table [name key-fn val-fn coll]
  `(defn ~(symbol name) [expr#]
     (case expr#
       ~@(reduce #(conj %1 (key-fn %2) (-> %2 (val-fn) (symbol)))
                 []
                 coll))))

(defn- gen-read-reply [replies]
  (gen-read-table "read-reply"
                  :request-opcode
                  #(-> %
                       (:name)
                       (ir/beautify :reply)
                       (ir/beautify :read-type))
                  replies))

(defn- gen-read-event [events]
  (gen-read-table "read-event"
                  :number
                  #(-> %
                       (:name)
                       (ir/beautify :event)
                       (ir/beautify :read-type))
                  events))

(defn- gen-read-error [errors]
  (gen-read-table "read-error"
                  :number
                  #(-> %
                       (:name)
                       (ir/beautify :error)
                       (ir/beautify :read-type))
                  errors))

(defn- gen-primitive [[name type]]
  `(def ~(symbol name)
     (xcljb.gen-common/->PrimitiveType ~type)))

(defn- gen-xcb [xcb]
  `(def ~(symbol "-XCB") {:header ~(:header xcb)
                          :extension-name ~(:extension-name xcb)
                          :extension-xname ~(:extension-xname xcb)
                          :extension-multiword ~(:extension-multiword xcb)
                          :major-version ~(:major-version xcb)
                          :minor-version ~(:minor-version xcb)}))

(defn- gen-header [xcb]
  (let [header (:header xcb)]
    `(ns ~(symbol (str "xcljb.gen." header))
       (:require [~@(map symbol ["xcljb" "conn" "gen-common"])]
                 [~@(map symbol ["xcljb.gen" (str header "-types")])]))))

(defn -main [& args]
  (let [root (xml/parse (java.io.BufferedReader. *in*))
        [xcb
         prim-types
         comp-types
         enums
         requests
         replies
         events
         errors] (xml2ir/xml->ir root)
        file-prefix (str "src/xcljb/gen/" (:header xcb))]
    (with-open [wrtr (clojure.java.io/writer (str file-prefix ".clj"))]
      (pp/pprint (gen-header xcb) wrtr)

      (.write wrtr "\n")
      (pp/pprint (gen-xcb xcb) wrtr)

      (doseq [enum (vals enums)]
        (.write wrtr "\n")
        (pp/pprint (gen-enum enum) wrtr))

      (doseq [request requests]
        (.write wrtr "\n")
        (pp/pprint (.gen-request-fn request) wrtr)))

    (with-open [wrtr (clojure.java.io/writer (str file-prefix "_types.clj"))]
      (pp/pprint
       `(ns ~(symbol (str "xcljb.gen." (:header xcb) "-types"))
          (:require [~@(map symbol ["xcljb" "gen-common"])]))
       wrtr)

      (.write wrtr "\n")
      (doseq [prim prim-types]
        (pp/pprint (gen-primitive prim) wrtr))

      (doseq [type comp-types]
        (.write wrtr "\n")
        (condp instance? type
          Struct (pp/pprint (.gen-type type) wrtr)))

      (doseq [request requests]
        (.write wrtr "\n")
        (pp/pprint (.gen-type request) wrtr))

      (doseq [reply replies]
        (.write wrtr "\n")
        (pp/pprint (.gen-type reply) wrtr))

      (doseq [event events]
        (.write wrtr "\n")
        (pp/pprint (.gen-type event) wrtr))

      (doseq [error errors]
        (.write wrtr "\n")
        (pp/pprint (.gen-type error) wrtr)))

    (with-open [wrtr (clojure.java.io/writer (str file-prefix "_internal.clj"))]
      (pp/pprint
       `(ns ~(symbol (str "xcljb.gen." (:header xcb) "-internal"))
          (:require [~@(map symbol ["xcljb" "gen-common"])]
                    [~@(map symbol ["xcljb.gen" (str (:header xcb) "-types")])]))
       wrtr)

      (doseq [type comp-types]
        (.write wrtr "\n")
        (condp instance? type
          Struct (pp/pprint (.gen-read-fn type) wrtr)))

      (doseq [reply replies]
        (.write wrtr "\n")
        (pp/pprint (.gen-read-fn reply) wrtr))

      (doseq [event events]
        (.write wrtr "\n")
        (pp/pprint (.gen-read-fn event) wrtr))

      (doseq [error errors]
        (.write wrtr "\n")
        (pp/pprint (.gen-read-fn error) wrtr))

      (.write wrtr "\n")
      (pp/pprint (gen-read-reply replies) wrtr)

      (.write wrtr "\n")
      (pp/pprint (gen-read-event events) wrtr)

      (.write wrtr "\n")
      (pp/pprint (gen-read-error errors) wrtr))))
