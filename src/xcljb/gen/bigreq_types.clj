;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.bigreq-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def EnableRequest (xcljb.gen-common/->Request "BIG-REQUESTS" 0 []))

(def
 EnableReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "maximum-request-length"
    xcljb.gen.xproto-types/CARD32)]))

;;; Manually written.