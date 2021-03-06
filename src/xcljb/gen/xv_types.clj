;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xv-types
 (:require [xcljb gen-common] [xcljb.gen xproto-types shm-types]))

(def PORT (xcljb.gen-common/->Primitive :uint32))

(def ENCODING (xcljb.gen-common/->Primitive :uint32))

(def
 Rational
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "numerator" xcljb.gen.xproto-types/INT32)
   (xcljb.gen-common/->Field
    "denominator"
    xcljb.gen.xproto-types/INT32)]))

(def
 Format
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "visual" xcljb.gen.xproto-types/VISUALID)
   (xcljb.gen-common/->Field "depth" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)]))

(def
 AdaptorInfo
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "base-id" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "name-size" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "num-ports" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "num-formats"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "type" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->StringField
    "name"
    (xcljb.gen-common/->Fieldref "name-size"))
   (xcljb.gen-common/->List
    "formats"
    xcljb.gen.xv-types/Format
    (xcljb.gen-common/->Fieldref "num-formats"))]))

(def
 EncodingInfo
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "encoding" xcljb.gen.xv-types/ENCODING)
   (xcljb.gen-common/->Field "name-size" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->Field "rate" xcljb.gen.xv-types/Rational)
   (xcljb.gen-common/->StringField
    "name"
    (xcljb.gen-common/->Fieldref "name-size"))]))

(def
 Image
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "data-size" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "num-planes"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->List
    "pitches"
    xcljb.gen.xproto-types/CARD32
    (xcljb.gen-common/->Fieldref "num-planes"))
   (xcljb.gen-common/->List
    "offsets"
    xcljb.gen.xproto-types/CARD32
    (xcljb.gen-common/->Fieldref "num-planes"))
   (xcljb.gen-common/->List
    "data"
    xcljb.gen.xproto-types/CARD8
    (xcljb.gen-common/->Fieldref "data-size"))]))

(def
 AttributeInfo
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "flags" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "min" xcljb.gen.xproto-types/INT32)
   (xcljb.gen-common/->Field "max" xcljb.gen.xproto-types/INT32)
   (xcljb.gen-common/->Field "size" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->StringField
    "name"
    (xcljb.gen-common/->Fieldref "size"))]))

(def
 ImageFormatInfo
 (xcljb.gen-common/->Struct
  [(xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "type" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "byte-order" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->List
    "guid"
    xcljb.gen.xproto-types/CARD8
    (xcljb.gen-common/->Value 16))
   (xcljb.gen-common/->Field "bpp" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Field "num-planes" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 2)
   (xcljb.gen-common/->Field "depth" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)
   (xcljb.gen-common/->Field "red-mask" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "green-mask"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "blue-mask" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "format" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)
   (xcljb.gen-common/->Field
    "y-sample-bits"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "u-sample-bits"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "v-sample-bits"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "vhorz-y-period"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "vhorz-u-period"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "vhorz-v-period"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "vvert-y-period"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "vvert-u-period"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field
    "vvert-v-period"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->List
    "vcomp-order"
    xcljb.gen.xproto-types/CARD8
    (xcljb.gen-common/->Value 32))
   (xcljb.gen-common/->Field
    "vscanline-order"
    xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 11)]))

(def QueryExtensionRequest (xcljb.gen-common/->Request "XVideo" 0 []))

(def
 QueryAdaptorsRequest
 (xcljb.gen-common/->Request
  "XVideo"
  1
  [(xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)]))

(def
 QueryEncodingsRequest
 (xcljb.gen-common/->Request
  "XVideo"
  2
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)]))

(def
 GrabPortRequest
 (xcljb.gen-common/->Request
  "XVideo"
  3
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "time" xcljb.gen.xproto-types/TIMESTAMP)]))

(def
 UngrabPortRequest
 (xcljb.gen-common/->Request
  "XVideo"
  4
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "time" xcljb.gen.xproto-types/TIMESTAMP)]))

(def
 PutVideoRequest
 (xcljb.gen-common/->Request
  "XVideo"
  5
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field "vid-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vid-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)]))

(def
 PutStillRequest
 (xcljb.gen-common/->Request
  "XVideo"
  6
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field "vid-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vid-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)]))

(def
 GetVideoRequest
 (xcljb.gen-common/->Request
  "XVideo"
  7
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field "vid-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vid-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)]))

(def
 GetStillRequest
 (xcljb.gen-common/->Request
  "XVideo"
  8
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field "vid-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "vid-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vid-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)]))

(def
 StopVideoRequest
 (xcljb.gen-common/->Request
  "XVideo"
  9
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)]))

(def
 SelectVideoNotifyRequest
 (xcljb.gen-common/->Request
  "XVideo"
  10
  [(xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->BoolField "onoff" 1)
   (xcljb.gen-common/->Pad 3)]))

(def
 SelectPortNotifyRequest
 (xcljb.gen-common/->Request
  "XVideo"
  11
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->BoolField "onoff" 1)
   (xcljb.gen-common/->Pad 3)]))

(def
 QueryBestSizeRequest
 (xcljb.gen-common/->Request
  "XVideo"
  12
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "vid-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "vid-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->BoolField "motion" 1)
   (xcljb.gen-common/->Pad 3)]))

(def
 SetPortAttributeRequest
 (xcljb.gen-common/->Request
  "XVideo"
  13
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "attribute" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field "value" xcljb.gen.xproto-types/INT32)]))

(def
 GetPortAttributeRequest
 (xcljb.gen-common/->Request
  "XVideo"
  14
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "attribute" xcljb.gen.xproto-types/ATOM)]))

(def
 QueryPortAttributesRequest
 (xcljb.gen-common/->Request
  "XVideo"
  15
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)]))

(def
 ListImageFormatsRequest
 (xcljb.gen-common/->Request
  "XVideo"
  16
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)]))

(def
 QueryImageAttributesRequest
 (xcljb.gen-common/->Request
  "XVideo"
  17
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)]))

(def
 PutImageRequest
 (xcljb.gen-common/->Request
  "XVideo"
  18
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "src-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "src-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "src-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "src-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->List "data" xcljb.gen.xproto-types/CARD8 nil)]))

(def
 ShmPutImageRequest
 (xcljb.gen-common/->Request
  "XVideo"
  19
  [(xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "gc" xcljb.gen.xproto-types/GCONTEXT)
   (xcljb.gen-common/->Field "shmseg" xcljb.gen.shm-types/SEG)
   (xcljb.gen-common/->Field "id" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "offset" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "src-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "src-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "src-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "src-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-x" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-y" xcljb.gen.xproto-types/INT16)
   (xcljb.gen-common/->Field "drw-w" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "drw-h" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "send-event" xcljb.gen.xproto-types/CARD8)
   (xcljb.gen-common/->Pad 3)]))

(def
 QueryExtensionReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "major" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "minor" xcljb.gen.xproto-types/CARD16)]))

(def
 QueryAdaptorsReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "num-adaptors"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 22)
   (xcljb.gen-common/->List
    "info"
    xcljb.gen.xv-types/AdaptorInfo
    (xcljb.gen-common/->Fieldref "num-adaptors"))]))

(def
 QueryEncodingsReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "num-encodings"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 22)
   (xcljb.gen-common/->List
    "info"
    xcljb.gen.xv-types/EncodingInfo
    (xcljb.gen-common/->Fieldref "num-encodings"))]))

(def
 GrabPortReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Field "result" xcljb.gen.xproto-types/BYTE)]))

(def
 QueryBestSizeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "actual-width"
    xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field
    "actual-height"
    xcljb.gen.xproto-types/CARD16)]))

(def
 GetPortAttributeReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "value" xcljb.gen.xproto-types/INT32)]))

(def
 QueryPortAttributesReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "num-attributes"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "text-size" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 16)
   (xcljb.gen-common/->List
    "attributes"
    xcljb.gen.xv-types/AttributeInfo
    (xcljb.gen-common/->Fieldref "num-attributes"))]))

(def
 ListImageFormatsReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "num-formats"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Pad 20)
   (xcljb.gen-common/->List
    "format"
    xcljb.gen.xv-types/ImageFormatInfo
    (xcljb.gen-common/->Fieldref "num-formats"))]))

(def
 QueryImageAttributesReply
 (xcljb.gen-common/->Reply
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field
    "num-planes"
    xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "data-size" xcljb.gen.xproto-types/CARD32)
   (xcljb.gen-common/->Field "width" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Field "height" xcljb.gen.xproto-types/CARD16)
   (xcljb.gen-common/->Pad 12)
   (xcljb.gen-common/->List
    "pitches"
    xcljb.gen.xproto-types/CARD32
    (xcljb.gen-common/->Fieldref "num-planes"))
   (xcljb.gen-common/->List
    "offsets"
    xcljb.gen.xproto-types/CARD32
    (xcljb.gen-common/->Fieldref "num-planes"))]))

(def
 VideoNotifyEvent
 (xcljb.gen-common/->Event
  "XVideo"
  "VideoNotify"
  0
  false
  [(xcljb.gen-common/->Field "reason" xcljb.gen.xproto-types/BYTE)
   (xcljb.gen-common/->Field "time" xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field
    "drawable"
    xcljb.gen.xproto-types/DRAWABLE)
   (xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)]))

(def
 PortNotifyEvent
 (xcljb.gen-common/->Event
  "XVideo"
  "PortNotify"
  1
  false
  [(xcljb.gen-common/->Pad 1)
   (xcljb.gen-common/->Field "time" xcljb.gen.xproto-types/TIMESTAMP)
   (xcljb.gen-common/->Field "port" xcljb.gen.xv-types/PORT)
   (xcljb.gen-common/->Field "attribute" xcljb.gen.xproto-types/ATOM)
   (xcljb.gen-common/->Field "value" xcljb.gen.xproto-types/INT32)]))

(def BadPortError (xcljb.gen-common/->Error' "XVideo" "BadPort" 0 []))

(def
 BadEncodingError
 (xcljb.gen-common/->Error' "XVideo" "BadEncoding" 1 []))

(def
 BadControlError
 (xcljb.gen-common/->Error' "XVideo" "BadControl" 2 []))

;;; Manually written.
