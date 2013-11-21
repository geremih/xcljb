;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.sync-internal
 (:require [xcljb common gen-common] [xcljb.gen sync-types]))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SYNC" 0]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/InitializeReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SYNC" 1]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/ListSystemCountersReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SYNC" 5]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/QueryCounterReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SYNC" 10]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/QueryAlarmReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SYNC" 13]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/GetPriorityReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-reply
 ["SYNC" 18]
 [_ _ reply-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/QueryFenceReply
  reply-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["SYNC" 0]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/CounterNotifyEvent
  event-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-event
 ["SYNC" 1]
 [_ _ event-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/AlarmNotifyEvent
  event-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-error
 ["SYNC" 0]
 [_ _ error-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/CounterError
  error-buf
  nil))

(clojure.core/defmethod
 xcljb.common/read-error
 ["SYNC" 1]
 [_ _ error-buf]
 (xcljb.gen-common/deserialize
  xcljb.gen.sync-types/AlarmError
  error-buf
  nil))

;;; Manually written.
