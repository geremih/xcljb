;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.randr-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types render-types]))

(def MODE (xcljb.gen-common/->Primitive :uint32))

(def CRTC (xcljb.gen-common/->Primitive :uint32))

(def OUTPUT (xcljb.gen-common/->Primitive :uint32))

(def
 ScreenSize
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "mwidth" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "mheight" xcljb.gen.xproto-types/CARD16)]))

(def
 RefreshRates
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "n-rates" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->List
    "rates"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "n-rates"))]))

(def
 ModeInfo
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "dot-clock" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "hsync-start"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "hsync-end" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "htotal" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "hskew" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "vsync-start"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vsync-end" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vtotal" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "name-len" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "mode-flags"
    xcljb.gen.xproto-types/CARD32)]))

(def
 CrtcChange
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)
   (xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->Field "x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)]))

(def
 OutputChange
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)
   (xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "connection" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "subpixel-order"
    xcljb.gen.xproto-types/CARD8)]))

(def
 OutputProperty
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "atom" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 11)]))

(def
 QueryVersionRequest
 (xcljb.gen-common/->Request
  "RANDR"
  0
  [(xcljb.gen-common/->Field
    "major-version"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "minor-version"
    xcljb.gen.xproto-types/CARD32)]))

(def
 SetScreenConfigRequest
 (xcljb.gen-common/->Request
  "RANDR"
  2
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "size-id" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "rate" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)]))

(def
 SelectInputRequest
 (xcljb.gen-common/->Request
  "RANDR"
  4
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "enable" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)]))

(def
 GetScreenInfoRequest
 (xcljb.gen-common/->Request
  "RANDR"
  5
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 GetScreenSizeRangeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  6
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 SetScreenSizeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  7
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "mm-width" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "mm-height"
    xcljb.gen.xproto-types/CARD32)]))

(def
 GetScreenResourcesRequest
 (xcljb.gen-common/->Request
  "RANDR"
  8
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 GetOutputInfoRequest
 (xcljb.gen-common/->Request
  "RANDR"
  9
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)]))

(def
 ListOutputPropertiesRequest
 (xcljb.gen-common/->Request
  "RANDR"
  10
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)]))

(def
 QueryOutputPropertyRequest
 (xcljb.gen-common/->Request
  "RANDR"
  11
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "property" xcljb.gen.xproto-types/ATOM)]))

(def
 ConfigureOutputPropertyRequest
 (xcljb.gen-common/->Request
  "RANDR"
  12
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "property" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->BoolField "pending" 1)
   (xcljb.gen-common/->BoolField "range" 1)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->List
    "values"
    xcljb.gen.xproto-types/INT32
    nil)]))

(def
 ChangeOutputPropertyRequest
 (xcljb.gen-common/->Request
  "RANDR"
  13
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "property" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field "type" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field "format" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "mode" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->Field "num-units" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->List
    "data"
    xcljb.gen.xproto-types/void
    (xcljb.gen-common/->Op
     clojure.core//
     (xcljb.gen-common/->Op
      clojure.core/*
      (xcljb.gen-common/->Fieldref "num-units")
      (xcljb.gen-common/->Fieldref "format"))
     (xcljb.gen-common/->Value 8)))]))

(def
 DeleteOutputPropertyRequest
 (xcljb.gen-common/->Request
  "RANDR"
  14
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "property" xcljb.gen.xproto-types/ATOM)]))

(def
 GetOutputPropertyRequest
 (xcljb.gen-common/->Request
  "RANDR"
  15
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "property" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field "type" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field
    "long-offset"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "long-length"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->BoolField "delete" 1)
   (xcljb.gen-common/->BoolField "pending" 1)
   (xcljb.gen-common/->Pad 2)]))

(def
 CreateModeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  16
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field
    "mode-info"
    xcljb.gen.randr-types/ModeInfo)
   (xcljb.gen-common/->StringField "name" nil)]))

(def
 DestroyModeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  17
  [(xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)]))

(def
 AddOutputModeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  18
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)]))

(def
 DeleteOutputModeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  19
  [(xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)]))

(def
 GetCrtcInfoRequest
 (xcljb.gen-common/->Request
  "RANDR"
  20
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)]))

(def
 SetCrtcConfigRequest
 (xcljb.gen-common/->Request
  "RANDR"
  21
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)
   (xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->List
    "outputs"
    xcljb.gen.randr-types/OUTPUT
    nil)]))

(def
 GetCrtcGammaSizeRequest
 (xcljb.gen-common/->Request
  "RANDR"
  22
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)]))

(def
 GetCrtcGammaRequest
 (xcljb.gen-common/->Request
  "RANDR"
  23
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)]))

(def
 SetCrtcGammaRequest
 (xcljb.gen-common/->Request
  "RANDR"
  24
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field "size" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->List
    "red"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "size"))
   (xcljb.gen-common/->List
    "green"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "size"))
   (xcljb.gen-common/->List
    "blue"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "size"))]))

(def
 GetScreenResourcesCurrentRequest
 (xcljb.gen-common/->Request
  "RANDR"
  25
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 SetCrtcTransformRequest
 (xcljb.gen-common/->Request
  "RANDR"
  26
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field
    "transform"
    xcljb.gen.render-types/TRANSFORM)
   (xcljb.gen-common/->Field
    "filter-len"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->StringField
    "filter-name"
    (xcljb.gen-common/->Fieldref "filter-len"))
   (xcljb.gen-common/->List
    "filter-params"
    xcljb.gen.render-types/FIXED
    nil)]))

(def
 GetCrtcTransformRequest
 (xcljb.gen-common/->Request
  "RANDR"
  27
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)]))

(def
 GetPanningRequest
 (xcljb.gen-common/->Request
  "RANDR"
  28
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)]))

(def
 SetPanningRequest
 (xcljb.gen-common/->Request
  "RANDR"
  29
  [(xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "left" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "top" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "track-left"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "track-top" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "track-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "track-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "border-left"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "border-top" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "border-right"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "border-bottom"
    xcljb.gen.xproto-types/INT16)]))

(def
 SetOutputPrimaryRequest
 (xcljb.gen-common/->Request
  "RANDR"
  30
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)]))

(def
 GetOutputPrimaryRequest
 (xcljb.gen-common/->Request
  "RANDR"
  31
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 QueryVersionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "major-version"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "minor-version"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 16)]))

(def
 SetScreenConfigReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "new-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "root" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field
    "subpixel-order"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 10)]))

(def
 GetScreenInfoReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "rotations" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "root" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "n-sizes" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "size-id" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "rate" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "n-info" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->List
    "sizes"
    xcljb.gen.randr-types/ScreenSize
    (xcljb.gen-common/->Fieldref "n-sizes"))
   (xcljb.gen-common/->List
    "rates"
    xcljb.gen.randr-types/RefreshRates
    (xcljb.gen-common/->Op
     clojure.core/-
     (xcljb.gen-common/->Fieldref "n-info")
     (xcljb.gen-common/->Fieldref "n-sizes")))]))

(def
 GetScreenSizeRangeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "min-width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "min-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "max-width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "max-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 16)]))

(def
 GetScreenResourcesReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "num-crtcs" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-outputs"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "num-modes" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "names-len" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 8)
   (xcljb.gen-common/->List
    "crtcs"
    xcljb.gen.randr-types/CRTC
    (xcljb.gen-common/->Fieldref "num-crtcs"))
   (xcljb.gen-common/->List
    "outputs"
    xcljb.gen.randr-types/OUTPUT
    (xcljb.gen-common/->Fieldref "num-outputs"))
   (xcljb.gen-common/->List
    "modes"
    xcljb.gen.randr-types/ModeInfo
    (xcljb.gen-common/->Fieldref "num-modes"))
   (xcljb.gen-common/->List
    "names"
    xcljb.gen.xproto-types/BYTE
    (xcljb.gen-common/->Fieldref "names-len"))]))

(def
 GetOutputInfoReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "crtc" xcljb.gen.randr-types/CRTC)
   (xcljb.gen-common/->Field "mm-width" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "mm-height" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "connection" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "subpixel-order"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "num-crtcs" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "num-modes" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-preferred"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-clones"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "name-len" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->List
    "crtcs"
    xcljb.gen.randr-types/CRTC
    (xcljb.gen-common/->Fieldref "num-crtcs"))
   (xcljb.gen-common/->List
    "modes"
    xcljb.gen.randr-types/MODE
    (xcljb.gen-common/->Fieldref "num-modes"))
   (xcljb.gen-common/->List
    "clones"
    xcljb.gen.randr-types/OUTPUT
    (xcljb.gen-common/->Fieldref "num-clones"))
   (xcljb.gen-common/->List
    "name"
    xcljb.gen.xproto-types/BYTE
    (xcljb.gen-common/->Fieldref "name-len"))]))

(def
 ListOutputPropertiesReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "num-atoms" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 22)
   (xcljb.gen-common/->List
    "atoms"
    xcljb.gen.xproto-types/ATOM
    (xcljb.gen-common/->Fieldref "num-atoms"))]))

(def
 QueryOutputPropertyReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->BoolField "pending" 1)
   (xcljb.gen-common/->BoolField "range" 1)
   (xcljb.gen-common/->BoolField "immutable" 1)
   (xcljb.gen-common/->Pad 21)
   (xcljb.gen-common/->List
    "valid-values"
    xcljb.gen.xproto-types/INT32
    (xcljb.gen-common/->Fieldref "length"))]))

(def
 GetOutputPropertyReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "format" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "type" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field
    "bytes-after"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "num-items" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 12)
   (xcljb.gen-common/->List
    "data"
    xcljb.gen.xproto-types/BYTE
    (xcljb.gen-common/->Op
     clojure.core/*
     (xcljb.gen-common/->Fieldref "num-items")
     (xcljb.gen-common/->Op
      clojure.core//
      (xcljb.gen-common/->Fieldref "format")
      (xcljb.gen-common/->Value 8))))]))

(def
 CreateModeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)
   (xcljb.gen-common/->Pad 20)]))

(def
 GetCrtcInfoReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "mode" xcljb.gen.randr-types/MODE)
   (xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "rotations" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-outputs"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-possible-outputs"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->List
    "outputs"
    xcljb.gen.randr-types/OUTPUT
    (xcljb.gen-common/->Fieldref "num-outputs"))
   (xcljb.gen-common/->List
    "possible"
    xcljb.gen.randr-types/OUTPUT
    (xcljb.gen-common/->Fieldref "num-possible-outputs"))]))

(def
 SetCrtcConfigReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Pad 20)]))

(def
 GetCrtcGammaSizeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "size" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 22)]))

(def
 GetCrtcGammaReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "size" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 22)
   (xcljb.gen-common/->List
    "red"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "size"))
   (xcljb.gen-common/->List
    "green"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "size"))
   (xcljb.gen-common/->List
    "blue"
    xcljb.gen.xproto-types/CARD16
    (xcljb.gen-common/->Fieldref "size"))]))

(def
 GetScreenResourcesCurrentReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "num-crtcs" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-outputs"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "num-modes" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "names-len" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 8)
   (xcljb.gen-common/->List
    "crtcs"
    xcljb.gen.randr-types/CRTC
    (xcljb.gen-common/->Fieldref "num-crtcs"))
   (xcljb.gen-common/->List
    "outputs"
    xcljb.gen.randr-types/OUTPUT
    (xcljb.gen-common/->Fieldref "num-outputs"))
   (xcljb.gen-common/->List
    "modes"
    xcljb.gen.randr-types/ModeInfo
    (xcljb.gen-common/->Fieldref "num-modes"))
   (xcljb.gen-common/->List
    "names"
    xcljb.gen.xproto-types/BYTE
    (xcljb.gen-common/->Fieldref "names-len"))]))

(def
 GetCrtcTransformReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "pending-transform"
    xcljb.gen.render-types/TRANSFORM)
   (xcljb.gen-common/->BoolField "has-transforms" 1)
   (xcljb.gen-common/->Pad 3)
   (xcljb.gen-common/->Field
    "current-transform"
    xcljb.gen.render-types/TRANSFORM)
   (xcljb.gen-common/->Pad 4)
   (xcljb.gen-common/->Field
    "pending-len"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "pending-nparams"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "current-len"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "current-nparams"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->StringField
    "pending-filter-name"
    (xcljb.gen-common/->Fieldref "pending-len"))
   (xcljb.gen-common/->List
    "pending-params"
    xcljb.gen.render-types/FIXED
    (xcljb.gen-common/->Fieldref "pending-nparams"))
   (xcljb.gen-common/->StringField
    "current-filter-name"
    (xcljb.gen-common/->Fieldref "current-len"))
   (xcljb.gen-common/->List
    "current-params"
    xcljb.gen.render-types/FIXED
    (xcljb.gen-common/->Fieldref "current-nparams"))]))

(def
 GetPanningReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "left" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "top" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "track-left"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "track-top" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "track-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "track-height"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "border-left"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "border-top" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "border-right"
    xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field
    "border-bottom"
    xcljb.gen.xproto-types/INT16)]))

(def
 SetPanningReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "status" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)]))

(def
 GetOutputPrimaryReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "output" xcljb.gen.randr-types/OUTPUT)]))

(def
 ScreenChangeNotifyEvent
 (xcljb.gen-common/->Event
  "RANDR"
  "ScreenChangeNotify"
  0
  false
  [(xcljb.gen-common/->Field "rotation" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field
    "timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "config-timestamp"
    xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "root" xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field
    "request-window"
    xcljb.gen.xproto-types/WINDOW)
   (xcljb.gen-common/->Field "size-id" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "subpixel-order"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "mwidth" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "mheight" xcljb.gen.xproto-types/CARD16)]))

(def
 BadOutputError
 (xcljb.gen-common/->Error' "RANDR" "BadOutput" 0 []))

(def BadCrtcError (xcljb.gen-common/->Error' "RANDR" "BadCrtc" 1 []))

(def BadModeError (xcljb.gen-common/->Error' "RANDR" "BadMode" 2 []))

;;; Manually written.

(def
 NotifyEvent
 (xcljb.gen-common/->Event
  "RANDR"
  "Notify"
  1
  false
  [(xcljb.gen-common/->Field "sub-code" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Stub
    "u"
    (clojure.core/reify
     xcljb.gen-common/Measurable
     (sizeof [_ _] 28)
     xcljb.gen-common/Deserializable
     (deserialize
      [_ buf context]
      (xcljb.gen-common/deserialize
       (clojure.core/case
        (:sub-code context)
        0
        xcljb.gen.randr-types/CrtcChange
        1
        xcljb.gen.randr-types/OutputChange
        2
        xcljb.gen.randr-types/OutputProperty)
       buf
       context))))]))
