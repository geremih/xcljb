;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.bigreq
 (:require xcljb.conn-ext xcljb.gen.bigreq-types))

(def
 -XCLJB
 {:minor-version 0,
  :major-version 0,
  :header "bigreq",
  :extension-multiword true,
  :extension-name "BigRequests",
  :extension-xname "BIG-REQUESTS"})

(clojure.core/defn
 enable
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "BIG-REQUESTS"
   xcljb.gen.bigreq-types/EnableRequest
   request)))

;;; Manually written.
