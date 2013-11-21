;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.shape-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def OP xcljb.gen.xproto-types/CARD8)

(def KIND xcljb.gen.xproto-types/CARD8)

(def QueryVersionRequest (xcljb.gen-common/->Request "SHAPE" 0 []))

(def
 RectanglesRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  1
  [(xcljb.gen-common/->Field "operation" xcljb.gen.shape-types/OP)
   (xcljb.gen-common/->Field
    "destination-kind"
    xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Field "ordering" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "x-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->List
    "rectangles"
    xcljb.gen.xproto-types/RECTANGLE
    nil)]))

(def
 MaskRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  2
  [(xcljb.gen-common/->Field "operation" xcljb.gen.shape-types/OP)
   (xcljb.gen-common/->Field
    "destination-kind"
    xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "x-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "source-bitmap"
    xcljb.gen.xproto-types/PIXMAP)]))

(def
 CombineRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  3
  [(xcljb.gen-common/->Field "operation" xcljb.gen.shape-types/OP)
   (xcljb.gen-common/->Field
    "destination-kind"
    xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Field "source-kind" xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "x-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "source-window"
    xcljb.gen.xproto-types/WINDOW)]))

(def
 OffsetRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  4
  [(xcljb.gen-common/->Field
    "destination-kind"
    xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Pad 3)
   (xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "x-offset" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y-offset" xcljb.gen.xproto-types/INT16)]))

(def
 QueryExtentsRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  5
  [(xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)]))

(def
 SelectInputRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  6
  [(xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->BoolField "enable" 1)
   (xcljb.gen-common/->Pad 3)]))

(def
 InputSelectedRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  7
  [(xcljb.gen-common/->Field
    "destination-window"
    xcljb.gen.xproto-types/WINDOW)]))

(def
 GetRectanglesRequest
 (xcljb.gen-common/->Request
  "SHAPE"
  8
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "source-kind" xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Pad 3)]))

(def
 QueryVersionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "major-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "minor-version"
    xcljb.gen.xproto-types/CARD16)]))

(def
 QueryExtentsReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->BoolField "bounding-shaped" 1)
   (xcljb.gen-common/->BoolField "clip-shaped" 1)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->Field
    "bounding-shape-extents-x"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "bounding-shape-extents-y"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "bounding-shape-extents-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "bounding-shape-extents-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "clip-shape-extents-x"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "clip-shape-extents-y"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "clip-shape-extents-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "clip-shape-extents-height"
    xcljb.gen.xproto-types/CARD16)]))

(def
 InputSelectedReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->BoolField "enabled" 1)]))

(def
 GetRectanglesReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "ordering" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Field
    "rectangles-len"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 20)
   (xcljb.gen-common/->List
    "rectangles"
    xcljb.gen.xproto-types/RECTANGLE
    (xcljb.gen-common/->Fieldref "rectangles-len"))]))

(def
 NotifyEvent
 (xcljb.gen-common/->Event
  "SHAPE"
  "Notify"
  0
  false
  [(xcljb.gen-common/->Field "shape-kind" xcljb.gen.shape-types/KIND)
   (xcljb.gen-common/->Field
    "affected-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "extents-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "extents-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "extents-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "extents-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "server-time"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->BoolField "shaped" 1)
   (xcljb.gen-common/->Pad 11)]))

;;; Manually written.
