;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xevie-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def Event (xcljb.gen-common/->Struct [(xcljb.gen-common/->Pad 32)]))

(def
 QueryVersionRequest
 (xcljb.gen-common/->Request
  "XEVIE"
  0
  [(xcljb.gen-common/->Field
    "client-major-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "client-minor-version"
    xcljb.gen.xproto-types/CARD16)]))

(def
 StartRequest
 (xcljb.gen-common/->Request
  "XEVIE"
  1
  [(xcljb.gen-common/->Field "screen" xcljb.gen.xproto-types/CARD32)]))

(def
 EndRequest
 (xcljb.gen-common/->Request
  "XEVIE"
  2
  [(xcljb.gen-common/->Field "cmap" xcljb.gen.xproto-types/CARD32)]))

(def
 SendRequest
 (xcljb.gen-common/->Request
  "XEVIE"
  3
  [(xcljb.gen-common/->Field "event" xcljb.gen.xevie-types/Event)
   (xcljb.gen-common/->Field "data-type" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 64)]))

(def
 SelectInputRequest
 (xcljb.gen-common/->Request
  "XEVIE"
  4
  [(xcljb.gen-common/->Field
    "event-mask"
    xcljb.gen.xproto-types/CARD32)]))

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
 StartReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1) (xcljb.gen-common/->Pad 24)]))

(def
 EndReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1) (xcljb.gen-common/->Pad 24)]))

(def
 SendReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1) (xcljb.gen-common/->Pad 24)]))

(def
 SelectInputReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1) (xcljb.gen-common/->Pad 24)]))

;;; Manually written.
