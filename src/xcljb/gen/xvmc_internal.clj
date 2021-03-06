;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xvmc-internal
 (:require [xcljb common gen-common] [xcljb.gen xvmc-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo-MotionCompensation" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xvmc-types/QueryVersionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo-MotionCompensation" 1]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xvmc-types/ListSurfaceTypesReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo-MotionCompensation" 2]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xvmc-types/CreateContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo-MotionCompensation" 4]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xvmc-types/CreateSurfaceReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo-MotionCompensation" 6]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xvmc-types/CreateSubpictureReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo-MotionCompensation" 8]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xvmc-types/ListSubpictureTypesReply
  reply-buf
  nil))

;;; Manually written.
