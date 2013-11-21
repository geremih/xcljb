;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xinerama-internal
 (:require [xcljb common gen-common] [xcljb.gen xinerama-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XINERAMA" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xinerama-types/QueryVersionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XINERAMA" 1]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xinerama-types/GetStateReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XINERAMA" 2]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xinerama-types/GetScreenCountReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XINERAMA" 3]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xinerama-types/GetScreenSizeReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XINERAMA" 4]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xinerama-types/IsActiveReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XINERAMA" 5]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xinerama-types/QueryScreensReply
  reply-buf
  nil))

;;; Manually written.
