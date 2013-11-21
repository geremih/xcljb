;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.screensaver-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def
 QueryVersionRequest
 (xcljb.gen-common/->Request
  "MIT-SCREEN-SAVER"
  0
  [(xcljb.gen-common/->Field
    "client-major-version"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "client-minor-version"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 2)]))

(def
 QueryInfoRequest
 (xcljb.gen-common/->Request
  "MIT-SCREEN-SAVER"
  1
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)]))

(def
 SelectInputRequest
 (xcljb.gen-common/->Request
  "MIT-SCREEN-SAVER"
  2
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field
    "event-mask"
    xcljb.gen.xproto-types/CARD32)]))

(def
 SetAttributesRequest
 (xcljb.gen-common/->Request
  "MIT-SCREEN-SAVER"
  3
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "border-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "class" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Field "depth" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "visual" xcljb.gen.xproto-types/VISUALID)
   (xcljb.gen-common/->Valueparam
    "value"
    xcljb.gen.xproto-types/CARD32)]))

(def
 UnsetAttributesRequest
 (xcljb.gen-common/->Request
  "MIT-SCREEN-SAVER"
  4
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)]))

(def
 SuspendRequest
 (xcljb.gen-common/->Request
  "MIT-SCREEN-SAVER"
  5
  [(xcljb.gen-common/->BoolField "suspend" 1)
   (xcljb.gen-common/->Pad 3)]))

(def
 QueryVersionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "server-major-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "server-minor-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 20)]))

(def
 QueryInfoReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "state" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "saver-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field
    "ms-until-server"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "ms-since-user-input"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "event-mask"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "kind" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Pad 7)]))

(def
 NotifyEvent
 (xcljb.gen-common/->Event
  "MIT-SCREEN-SAVER"
  "Notify"
  0
  false
  [(xcljb.gen-common/->Field "code" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "state" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "sequence-number"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "time" xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "root" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "kind" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->BoolField "forced" 1)
   (xcljb.gen-common/->Pad 14)]))

;;; Manually written.
