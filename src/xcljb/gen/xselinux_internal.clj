;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xselinux-internal
 (:require [xcljb common gen-common] [xcljb.gen xselinux-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/QueryVersionReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 2]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetDeviceCreateContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 4]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetDeviceContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 6]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetWindowCreateContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 7]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetWindowContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 9]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetPropertyCreateContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 11]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetPropertyUseContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 12]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetPropertyContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 13]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetPropertyDataContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 14]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/ListPropertiesReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 16]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetSelectionCreateContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 18]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetSelectionUseContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 19]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetSelectionContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 20]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetSelectionDataContextReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 21]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/ListSelectionsReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SELinux" 22]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.xselinux-types/GetClientContextReply
  reply-buf
  nil))

;;; Manually written.
