;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xinerama-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def
 ScreenInfo
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "x-org" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y-org" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)]))

(def
 QueryVersionRequest
 (xcljb.gen-common/->Request
  "XINERAMA"
  0
  [(xcljb.gen-common/->Field "major" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "minor" xcljb.gen.xproto-types/CARD8)]))

(def
 GetStateRequest
 (xcljb.gen-common/->Request
  "XINERAMA"
  1
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 GetScreenCountRequest
 (xcljb.gen-common/->Request
  "XINERAMA"
  2
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 GetScreenSizeRequest
 (xcljb.gen-common/->Request
  "XINERAMA"
  3
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "screen" xcljb.gen.xproto-types/CARD32)]))

(def IsActiveRequest (xcljb.gen-common/->Request "XINERAMA" 4 []))

(def QueryScreensRequest (xcljb.gen-common/->Request "XINERAMA" 5 []))

(def
 QueryVersionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "major" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "minor" xcljb.gen.xproto-types/CARD16)]))

(def
 GetStateReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "state" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 GetScreenCountReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field
    "screen-count"
    xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 GetScreenSizeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "screen" xcljb.gen.xproto-types/CARD32)]))

(def
 IsActiveReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "state" xcljb.gen.xproto-types/CARD32)]))

(def
 QueryScreensReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "number" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 20)
   (xcljb.gen-common/->List
    "screen-info"
    xcljb.gen.xinerama-types/ScreenInfo
    (xcljb.gen-common/->Fieldref "number"))]))

;;; Manually written.