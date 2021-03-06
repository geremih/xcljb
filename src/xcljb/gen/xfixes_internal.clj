;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xfixes-internal
 (:require [xcljb common gen-common] [xcljb.gen xfixes-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XFIXES" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/QueryVersionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XFIXES" 4]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/GetCursorImageReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XFIXES" 19]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/FetchRegionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XFIXES" 24]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/GetCursorNameReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XFIXES" 25]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/GetCursorImageAndNameReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["XFIXES" 0]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/SelectionNotifyEvent
  event-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["XFIXES" 1]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/CursorNotifyEvent
  event-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-error
 ["XFIXES" 0]
 [_ _ error-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xfixes-types/BadRegionError
  error-buf
  nil))

;;; Manually written.
