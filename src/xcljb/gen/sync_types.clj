;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.sync-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def ALARM (xcljb.gen-common/->Primitive :uint32))

(def COUNTER (xcljb.gen-common/->Primitive :uint32))

(def FENCE (xcljb.gen-common/->Primitive :uint32))

(def
 INT64
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "hi" xcljb.gen.xproto-types/INT32)
   (xcljb.gen-common/->Field "lo" xcljb.gen.xproto-types/CARD32)]))

(def
 SYSTEMCOUNTER
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)
   (xcljb.gen-common/->Field "resolution" xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->Field "name-len" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->StringField
    "name"
    (xcljb.gen-common/->Fieldref "name-len"))]))

(def
 TRIGGER
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)
   (xcljb.gen-common/->Field "wait-type" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "wait-value" xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->Field
    "test-type"
    xcljb.gen.xproto-types/CARD32)]))

(def
 WAITCONDITION
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "trigger" xcljb.gen.sync-types/TRIGGER)
   (xcljb.gen-common/->Field
    "event-threshold"
    xcljb.gen.sync-types/INT64)]))

(def
 InitializeRequest
 (xcljb.gen-common/->Request
  "SYNC"
  0
  [(xcljb.gen-common/->Field
    "desired-major-version"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "desired-minor-version"
    xcljb.gen.xproto-types/CARD8)]))

(def ListSystemCountersRequest (xcljb.gen-common/->Request "SYNC" 1 []))

(def
 CreateCounterRequest
 (xcljb.gen-common/->Request
  "SYNC"
  2
  [(xcljb.gen-common/->Field "id" xcljb.gen.sync-types/COUNTER)
   (xcljb.gen-common/->Field
    "initial-value"
    xcljb.gen.sync-types/INT64)]))

(def
 DestroyCounterRequest
 (xcljb.gen-common/->Request
  "SYNC"
  6
  [(xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)]))

(def
 QueryCounterRequest
 (xcljb.gen-common/->Request
  "SYNC"
  5
  [(xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)]))

(def
 AwaitRequest
 (xcljb.gen-common/->Request
  "SYNC"
  7
  [(xcljb.gen-common/->List
    "wait-list"
    xcljb.gen.sync-types/WAITCONDITION
    nil)]))

(def
 ChangeCounterRequest
 (xcljb.gen-common/->Request
  "SYNC"
  4
  [(xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)
   (xcljb.gen-common/->Field "amount" xcljb.gen.sync-types/INT64)]))

(def
 SetCounterRequest
 (xcljb.gen-common/->Request
  "SYNC"
  3
  [(xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)
   (xcljb.gen-common/->Field "value" xcljb.gen.sync-types/INT64)]))

(def
 CreateAlarmRequest
 (xcljb.gen-common/->Request
  "SYNC"
  8
  [(xcljb.gen-common/->Field "id" xcljb.gen.sync-types/ALARM)
   (xcljb.gen-common/->Valueparam
    "value"
    xcljb.gen.xproto-types/CARD32)]))

(def
 ChangeAlarmRequest
 (xcljb.gen-common/->Request
  "SYNC"
  9
  [(xcljb.gen-common/->Field "id" xcljb.gen.sync-types/ALARM)
   (xcljb.gen-common/->Valueparam
    "value"
    xcljb.gen.xproto-types/CARD32)]))

(def
 DestroyAlarmRequest
 (xcljb.gen-common/->Request
  "SYNC"
  11
  [(xcljb.gen-common/->Field "alarm" xcljb.gen.sync-types/ALARM)]))

(def
 QueryAlarmRequest
 (xcljb.gen-common/->Request
  "SYNC"
  10
  [(xcljb.gen-common/->Field "alarm" xcljb.gen.sync-types/ALARM)]))

(def
 SetPriorityRequest
 (xcljb.gen-common/->Request
  "SYNC"
  12
  [(xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "priority" xcljb.gen.xproto-types/INT32)]))

(def
 GetPriorityRequest
 (xcljb.gen-common/->Request
  "SYNC"
  13
  [(xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)]))

(def
 CreateFenceRequest
 (xcljb.gen-common/->Request
  "SYNC"
  14
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "fence" xcljb.gen.sync-types/FENCE)
   (xcljb.gen-common/->BoolField "initially-triggered" 1)]))

(def
 TriggerFenceRequest
 (xcljb.gen-common/->Request
  "SYNC"
  15
  [(xcljb.gen-common/->Field "fence" xcljb.gen.sync-types/FENCE)]))

(def
 ResetFenceRequest
 (xcljb.gen-common/->Request
  "SYNC"
  16
  [(xcljb.gen-common/->Field "fence" xcljb.gen.sync-types/FENCE)]))

(def
 DestroyFenceRequest
 (xcljb.gen-common/->Request
  "SYNC"
  17
  [(xcljb.gen-common/->Field "fence" xcljb.gen.sync-types/FENCE)]))

(def
 QueryFenceRequest
 (xcljb.gen-common/->Request
  "SYNC"
  18
  [(xcljb.gen-common/->Field "fence" xcljb.gen.sync-types/FENCE)]))

(def
 AwaitFenceRequest
 (xcljb.gen-common/->Request
  "SYNC"
  19
  [(xcljb.gen-common/->List
    "fence-list"
    xcljb.gen.sync-types/FENCE
    nil)]))

(def
 InitializeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "major-version"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "minor-version"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 22)]))

(def
 ListSystemCountersReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "counters-len"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 20)
   (xcljb.gen-common/->List
    "counters"
    xcljb.gen.sync-types/SYSTEMCOUNTER
    (xcljb.gen-common/->Fieldref "counters-len"))]))

(def
 QueryCounterReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "counter-value"
    xcljb.gen.sync-types/INT64)]))

(def
 QueryAlarmReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "trigger" xcljb.gen.sync-types/TRIGGER)
   (xcljb.gen-common/->Field "delta" xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->BoolField "events" 1)
   (xcljb.gen-common/->Field "state" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 2)]))

(def
 GetPriorityReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "priority" xcljb.gen.xproto-types/INT32)]))

(def
 QueryFenceReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->BoolField "triggered" 1)
   (xcljb.gen-common/->Pad 23)]))

(def
 CounterNotifyEvent
 (xcljb.gen-common/->Event
  "SYNC"
  "CounterNotify"
  0
  false
  [(xcljb.gen-common/->Field "kind" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "counter" xcljb.gen.sync-types/COUNTER)
   (xcljb.gen-common/->Field "wait-value" xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->Field
    "counter-value"
    xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "count" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->BoolField "destroyed" 1)
   (xcljb.gen-common/->Pad 1)]))

(def
 AlarmNotifyEvent
 (xcljb.gen-common/->Event
  "SYNC"
  "AlarmNotify"
  1
  false
  [(xcljb.gen-common/->Field "kind" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "alarm" xcljb.gen.sync-types/ALARM)
   (xcljb.gen-common/->Field
    "counter-value"
    xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->Field "alarm-value" xcljb.gen.sync-types/INT64)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "state" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)]))

(def
 CounterError
 (xcljb.gen-common/->Error'
  "SYNC"
  "Counter"
  0
  [(xcljb.gen-common/->Field
    "bad-counter"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "minor-opcode"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "major-opcode"
    xcljb.gen.xproto-types/CARD8)]))

(def
 AlarmError
 (xcljb.gen-common/->Error'
  "SYNC"
  "Alarm"
  1
  [(xcljb.gen-common/->Field "bad-alarm" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "minor-opcode"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "major-opcode"
    xcljb.gen.xproto-types/CARD8)]))

;;; Manually written.
