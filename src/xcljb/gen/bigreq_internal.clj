;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.bigreq-internal
 (:require [xcljb common gen-common] [xcljb.gen bigreq-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["BIG-REQUESTS" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.bigreq-types/EnableReply
  reply-buf
  nil))

;;; Manually written.