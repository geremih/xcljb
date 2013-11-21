;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xevie-internal
 (:require [xcljb common gen-common] [xcljb.gen xevie-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XEVIE" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xevie-types/QueryVersionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XEVIE" 1]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xevie-types/StartReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XEVIE" 2]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xevie-types/EndReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XEVIE" 3]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xevie-types/SendReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XEVIE" 4]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xevie-types/SelectInputReply
  reply-buf
  nil))

;;; Manually written.
