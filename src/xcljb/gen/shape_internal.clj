;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.shape-internal
 (:require [xcljb common gen-common] [xcljb.gen shape-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SHAPE" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.shape-types/QueryVersionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SHAPE" 5]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.shape-types/QueryExtentsReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SHAPE" 7]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.shape-types/InputSelectedReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SHAPE" 8]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.shape-types/GetRectanglesReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["SHAPE" 0]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.shape-types/NotifyEvent
  event-buf
  nil))

;;; Manually written.