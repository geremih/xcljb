;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xv-internal
 (:require [xcljb common gen-common] [xcljb.gen xv-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/QueryExtensionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 1]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/QueryAdaptorsReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 2]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/QueryEncodingsReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 3]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/GrabPortReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 12]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/QueryBestSizeReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 14]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/GetPortAttributeReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 15]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/QueryPortAttributesReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 16]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/ListImageFormatsReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["XVideo" 17]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/QueryImageAttributesReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["XVideo" 0]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/VideoNotifyEvent
  event-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["XVideo" 1]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/PortNotifyEvent
  event-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-error
 ["XVideo" 0]
 [_ _ error-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/BadPortError
  error-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-error
 ["XVideo" 1]
 [_ _ error-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/BadEncodingError
  error-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-error
 ["XVideo" 2]
 [_ _ error-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xv-types/BadControlError
  error-buf
  nil))

;;; Manually written.
