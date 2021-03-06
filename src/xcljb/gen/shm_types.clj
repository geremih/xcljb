;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.shm-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(def SEG (xcljb.gen-common/->Primitive :uint32))

(def QueryVersionRequest (xcljb.gen-common/->Request "MIT-SHM" 0 []))

(def
 AttachRequest
 (xcljb.gen-common/->Request
  "MIT-SHM"
  1
  [(xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)
   (xcljb.gen-common/->Field "shmid" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->BoolField "read-only" 1)
   (xcljb.gen-common/->Pad 3)]))

(def
 DetachRequest
 (xcljb.gen-common/->Request
  "MIT-SHM"
  2
  [(xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)]))

(def
 PutImageRequest
 (xcljb.gen-common/->Request
  "MIT-SHM"
  3
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field
    "total-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "total-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "src-x" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "src-y" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "src-width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "src-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "dst-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "dst-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "depth" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "format" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "send-event" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)
   (xcljb.gen-common/->Field "offset" xcljb.gen.xproto-types/CARD32)]))

(def
 GetImageRequest
 (xcljb.gen-common/->Request
  "MIT-SHM"
  4
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "plane-mask"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "format" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)
   (xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)
   (xcljb.gen-common/->Field "offset" xcljb.gen.xproto-types/CARD32)]))

(def
 CreatePixmapRequest
 (xcljb.gen-common/->Request
  "MIT-SHM"
  5
  [(xcljb.gen-common/->Field "pid" xcljb.gen.xproto-types/PIXMAP)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "depth" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)
   (xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)
   (xcljb.gen-common/->Field "offset" xcljb.gen.xproto-types/CARD32)]))

(def
 QueryVersionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->BoolField "shared-pixmaps" 1)
   (xcljb.gen-common/->Field
    "major-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "minor-version"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "uid" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "gid" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "pixmap-format"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 15)]))

(def
 GetImageReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "depth" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "visual" xcljb.gen.xproto-types/VISUALID)
   (xcljb.gen-common/->Field "size" xcljb.gen.xproto-types/CARD32)]))

(def
 CompletionEvent
 (xcljb.gen-common/->Event
  "MIT-SHM"
  "Completion"
  0
  false
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field
    "minor-event"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "major-event" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)
   (xcljb.gen-common/->Field "offset" xcljb.gen.xproto-types/CARD32)]))

(def
 BadSegError
 (xcljb.gen-common/->ErrorCopy
  "MIT-SHM"
  "BadSeg"
  0
  {:ext-name nil, :number 2}))

;;; Manually written.
