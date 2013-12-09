;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.dpms-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def
 GetVersionRequest
 (xcljb.gen-common/->Request
  "DPMS"
  0
  [(xcljb.gen-common/->Field
    "client-major-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "client-minor-version"
    xcljb.gen.xproto-types/CARD16)]))

(def CapableRequest (xcljb.gen-common/->Request "DPMS" 1 []))

(def GetTimeoutsRequest (xcljb.gen-common/->Request "DPMS" 2 []))

(def
 SetTimeoutsRequest
 (xcljb.gen-common/->Request
  "DPMS"
  3
  [(xcljb.gen-common/->Field
    "standby-timeout"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "suspend-timeout"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "off-timeout"
    xcljb.gen.xproto-types/CARD16)]))

(def EnableRequest (xcljb.gen-common/->Request "DPMS" 4 []))

(def DisableRequest (xcljb.gen-common/->Request "DPMS" 5 []))

(def
 ForceLevelRequest
 (xcljb.gen-common/->Request
  "DPMS"
  6
  [(xcljb.gen-common/->Field
    "power-level"
    xcljb.gen.xproto-types/CARD16)]))

(def InfoRequest (xcljb.gen-common/->Request "DPMS" 7 []))

(def
 GetVersionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "server-major-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "server-minor-version"
    xcljb.gen.xproto-types/CARD16)]))

(def
 CapableReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->BoolField "capable" 1)
   (xcljb.gen-common/->Pad 23)]))

(def
 GetTimeoutsReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "standby-timeout"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "suspend-timeout"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "off-timeout"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 18)]))

(def
 InfoReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "power-level"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->BoolField "state" 1)
   (xcljb.gen-common/->Pad 21)]))

;;; Manually written.