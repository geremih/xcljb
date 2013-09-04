(clojure.core/ns xcljb.gen.xproto-types (:require [xcljb gen-common]))

(def KEYSYM (xcljb.gen-common/->PrimitiveType :uint32))
(def BYTE (xcljb.gen-common/->PrimitiveType :ubyte))
(def CARD8 (xcljb.gen-common/->PrimitiveType :ubyte))
(def GCONTEXT (xcljb.gen-common/->PrimitiveType :uint32))
(def FONTABLE (xcljb.gen-common/->PrimitiveType :uint32))
(def INT8 (xcljb.gen-common/->PrimitiveType :byte))
(def KEYCODE (xcljb.gen-common/->PrimitiveType :ubyte))
(def INT32 (xcljb.gen-common/->PrimitiveType :int32))
(def FONT (xcljb.gen-common/->PrimitiveType :uint32))
(def CARD32 (xcljb.gen-common/->PrimitiveType :uint32))
(def WINDOW (xcljb.gen-common/->PrimitiveType :uint32))
(def ATOM (xcljb.gen-common/->PrimitiveType :uint32))
(def BUTTON (xcljb.gen-common/->PrimitiveType :ubyte))
(def void (xcljb.gen-common/->PrimitiveType :ubyte))
(def INT16 (xcljb.gen-common/->PrimitiveType :int16))
(def CARD16 (xcljb.gen-common/->PrimitiveType :uint16))
(def TIMESTAMP (xcljb.gen-common/->PrimitiveType :uint32))
(def CURSOR (xcljb.gen-common/->PrimitiveType :uint32))
(def char (xcljb.gen-common/->PrimitiveType :ubyte))
(def COLORMAP (xcljb.gen-common/->PrimitiveType :uint32))
(def VISUALID (xcljb.gen-common/->PrimitiveType :uint32))
(def PIXMAP (xcljb.gen-common/->PrimitiveType :uint32))
(def DRAWABLE (xcljb.gen-common/->PrimitiveType :uint32))

(clojure.core/defrecord
 Char2b
 [byte1 byte2]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)])
 (to-value [this] [(:byte1 this) (:byte2 this)]))

(clojure.core/defrecord
 Point
 [x y]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)])
 (to-value [this] [(:x this) (:y this)]))

(clojure.core/defrecord
 Rectangle
 [x y width height]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)])
 (to-value [this] [(:x this) (:y this) (:width this) (:height this)]))

(clojure.core/defrecord
 Arc
 [x y width height angle1 angle2]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)])
 (to-value
  [this]
  [(:x this)
   (:y this)
   (:width this)
   (:height this)
   (:angle1 this)
   (:angle2 this)]))

(clojure.core/defrecord
 Format
 [depth bits-per-pixel scanline-pad]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   5))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 5 :byte)])
 (to-value
  [this]
  [(:depth this)
   (:bits-per-pixel this)
   (:scanline-pad this)
   (clojure.core/repeat 5 0)]))

(clojure.core/defrecord
 Visualtype
 [visual-id
  class
  bits-per-rgb-value
  colormap-entries
  red-mask
  green-mask
  blue-mask]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/VISUALID)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   4))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/VISUALID)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat 4 :byte)])
 (to-value
  [this]
  [(:visual-id this)
   (:class this)
   (:bits-per-rgb-value this)
   (:colormap-entries this)
   (:red-mask this)
   (:green-mask this)
   (:blue-mask this)
   (clojure.core/repeat 4 0)]))

(clojure.core/defrecord
 Depth
 [depth visuals-len visuals]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   4
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:visuals this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 1 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 4 :byte)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:visuals this))])
 (to-value
  [this]
  [(:depth this)
   (clojure.core/repeat 1 0)
   (:visuals-len this)
   (clojure.core/repeat 4 0)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:visuals this))]))

(clojure.core/defrecord
 Screen
 [root
  default-colormap
  white-pixel
  black-pixel
  current-input-masks
  width-in-pixels
  height-in-pixels
  width-in-millimeters
  height-in-millimeters
  min-installed-maps
  max-installed-maps
  root-visual
  backing-stores
  save-unders
  root-depth
  allowed-depths-len
  allowed-depths]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/VISUALID)
   (.sizeof xcljb.gen.xproto-types/BYTE)
   1
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:allowed-depths this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/VISUALID)
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:allowed-depths this))])
 (to-value
  [this]
  [(:root this)
   (:default-colormap this)
   (:white-pixel this)
   (:black-pixel this)
   (:current-input-masks this)
   (:width-in-pixels this)
   (:height-in-pixels this)
   (:width-in-millimeters this)
   (:height-in-millimeters this)
   (:min-installed-maps this)
   (:max-installed-maps this)
   (:root-visual this)
   (:backing-stores this)
   (if (:save-unders this) 1 0)
   (:root-depth this)
   (:allowed-depths-len this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:allowed-depths this))]))

(clojure.core/defrecord
 SetupRequest
 [byte-order
  protocol-major-version
  protocol-minor-version
  authorization-protocol-name-len
  authorization-protocol-data-len
  authorization-protocol-name
  authorization-protocol-data]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:authorization-protocol-name this))
   (clojure.core/count (:authorization-protocol-data this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 1 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (gloss.core/string :ascii)])
 (to-value
  [this]
  [(:byte-order this)
   (clojure.core/repeat 1 0)
   (:protocol-major-version this)
   (:protocol-minor-version this)
   (:authorization-protocol-name-len this)
   (:authorization-protocol-data-len this)
   (clojure.core/repeat 2 0)
   (:authorization-protocol-name this)
   (:authorization-protocol-data this)]))

(clojure.core/defrecord
 SetupFailed
 [status
  reason-len
  protocol-major-version
  protocol-minor-version
  length
  reason]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/count (:reason this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (gloss.core/string :ascii)])
 (to-value
  [this]
  [(:status this)
   (:reason-len this)
   (:protocol-major-version this)
   (:protocol-minor-version this)
   (:length this)
   (:reason this)]))

(clojure.core/defrecord
 SetupAuthenticate
 [status length reason]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   5
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/count (:reason this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 5 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (gloss.core/string :ascii)])
 (to-value
  [this]
  [(:status this)
   (clojure.core/repeat 5 0)
   (:length this)
   (:reason this)]))

(clojure.core/defrecord
 Setup
 [status
  protocol-major-version
  protocol-minor-version
  length
  release-number
  resource-id-base
  resource-id-mask
  motion-buffer-size
  vendor-len
  maximum-request-length
  roots-len
  pixmap-formats-len
  image-byte-order
  bitmap-format-bit-order
  bitmap-format-scanline-unit
  bitmap-format-scanline-pad
  min-keycode
  max-keycode
  vendor
  pixmap-formats
  roots]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/KEYCODE)
   (.sizeof xcljb.gen.xproto-types/KEYCODE)
   4
   (clojure.core/count (:vendor this))
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:pixmap-formats this))
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:roots this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 1 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/KEYCODE)
   (.to-frame xcljb.gen.xproto-types/KEYCODE)
   (clojure.core/repeat 4 :byte)
   (gloss.core/string :ascii)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:pixmap-formats this))
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:roots this))])
 (to-value
  [this]
  [(:status this)
   (clojure.core/repeat 1 0)
   (:protocol-major-version this)
   (:protocol-minor-version this)
   (:length this)
   (:release-number this)
   (:resource-id-base this)
   (:resource-id-mask this)
   (:motion-buffer-size this)
   (:vendor-len this)
   (:maximum-request-length this)
   (:roots-len this)
   (:pixmap-formats-len this)
   (:image-byte-order this)
   (:bitmap-format-bit-order this)
   (:bitmap-format-scanline-unit this)
   (:bitmap-format-scanline-pad this)
   (:min-keycode this)
   (:max-keycode this)
   (clojure.core/repeat 4 0)
   (:vendor this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:pixmap-formats this))
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:roots this))]))

(clojure.core/defrecord
 Timecoord
 [time x y]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)])
 (to-value [this] [(:time this) (:x this) (:y this)]))

(clojure.core/defrecord
 Fontprop
 [name value]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/CARD32)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/CARD32)])
 (to-value [this] [(:name this) (:value this)]))

(clojure.core/defrecord
 Charinfo
 [left-side-bearing
  right-side-bearing
  character-width
  ascent
  descent
  attributes]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)])
 (to-value
  [this]
  [(:left-side-bearing this)
   (:right-side-bearing this)
   (:character-width this)
   (:ascent this)
   (:descent this)
   (:attributes this)]))

(clojure.core/defrecord
 Str
 [name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (gloss.core/string :ascii)])
 (to-value [this] [(:name-len this) (:name this)]))

(clojure.core/defrecord
 Segment
 [x1 y1 x2 y2]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)])
 (to-value [this] [(:x1 this) (:y1 this) (:x2 this) (:y2 this)]))

(clojure.core/defrecord
 Coloritem
 [pixel red green blue flags]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/BYTE)
   1))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/BYTE)
   (clojure.core/repeat 1 :byte)])
 (to-value
  [this]
  [(:pixel this)
   (:red this)
   (:green this)
   (:blue this)
   (:flags this)
   (clojure.core/repeat 1 0)]))

(clojure.core/defrecord
 Rgb
 [red green blue]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)])
 (to-value
  [this]
  [(:red this) (:green this) (:blue this) (clojure.core/repeat 2 0)]))

(clojure.core/defrecord
 Host
 [family address-len address]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   (.sizeof xcljb.gen.xproto-types/CARD8)
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/BYTE)
    (clojure.core/count (:address this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [(.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 1 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (clojure.core/count (:address this))
    (.to-frame xcljb.gen.xproto-types/BYTE))])
 (to-value
  [this]
  [(:family this)
   (clojure.core/repeat 1 0)
   (:address-len this)
   (:address this)]))

(clojure.core/defrecord
 CreateWindowRequest
 [opcode
  depth
  wid
  parent
  x
  y
  width
  height
  border-width
  class
  visual
  value]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/VISUALID)
   (clojure.core/+
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/*
     (clojure.core/-> this (:value) (clojure.core/count))
     4))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/VISUALID)
   [(.to-frame xcljb.gen.xproto-types/CARD32)
    (clojure.core/repeat
     (clojure.core/-> this (:value) (clojure.core/count))
     :uint32)]
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [1
   (:depth this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:wid this)
   (:parent this)
   (:x this)
   (:y this)
   (:width this)
   (:height this)
   (:border-width this)
   (:class this)
   (:visual this)
   (xcljb.gen-common/valueparam->value (:value this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeWindowAttributesRequest
 [opcode window value]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (clojure.core/+
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/*
     (clojure.core/-> this (:value) (clojure.core/count))
     4))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   [(.to-frame xcljb.gen.xproto-types/CARD32)
    (clojure.core/repeat
     (clojure.core/-> this (:value) (clojure.core/count))
     :uint32)]
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [2
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (xcljb.gen-common/valueparam->value (:value this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetWindowAttributesRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [3
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 DestroyWindowRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [4
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 DestroySubwindowsRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [5
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeSaveSetRequest
 [opcode mode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [6
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ReparentWindowRequest
 [opcode window parent x y]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [7
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:parent this)
   (:x this)
   (:y this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 MapWindowRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [8
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 MapSubwindowsRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [9
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UnmapWindowRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [10
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UnmapSubwindowsRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [11
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CirculateWindowRequest
 [opcode direction window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [13
   (:direction this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetGeometryRequest
 [opcode drawable]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/DRAWABLE)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [14
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryTreeRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [15
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 InternAtomRequest
 [opcode only-if-exists name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [16
   (if (:only-if-exists this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:name-len this)
   (clojure.core/repeat 2 0)
   (:name this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetAtomNameRequest
 [opcode atom]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/ATOM)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [17
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:atom this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangePropertyRequest
 [opcode mode window property type format data-len data]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   3
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/void)
    (clojure.core/count (:data this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 3 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat
    (clojure.core/count (:data this))
    (.to-frame xcljb.gen.xproto-types/void))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [18
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:property this)
   (:type this)
   (:format this)
   (clojure.core/repeat 3 0)
   (:data-len this)
   (:data this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 DeletePropertyRequest
 [opcode window property]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/ATOM)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [19
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:property this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetPropertyRequest
 [opcode delete window property type long-offset long-length]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD32)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [20
   (if (:delete this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:property this)
   (:type this)
   (:long-offset this)
   (:long-length this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ListPropertiesRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [21
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetSelectionOwnerRequest
 [opcode owner selection time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [22
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:owner this)
   (:selection this)
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetSelectionOwnerRequest
 [opcode selection]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/ATOM)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [23
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:selection this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ConvertSelectionRequest
 [opcode requestor selection target property time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/ATOM)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/ATOM)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [24
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:requestor this)
   (:selection this)
   (:target this)
   (:property this)
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SendEventRequest
 [opcode propagate destination event-mask event]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (clojure.core/count (:event this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [25
   (if (:propagate this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:destination this)
   (:event-mask this)
   (:event this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GrabPointerRequest
 [opcode
  owner-events
  grab-window
  event-mask
  pointer-mode
  keyboard-mode
  confine-to
  cursor
  time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CURSOR)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/BYTE)
   (.to-frame xcljb.gen.xproto-types/BYTE)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [26
   (if (:owner-events this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:grab-window this)
   (:event-mask this)
   (:pointer-mode this)
   (:keyboard-mode this)
   (:confine-to this)
   (:cursor this)
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UngrabPointerRequest
 [opcode time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [27
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GrabButtonRequest
 [opcode
  owner-events
  grab-window
  event-mask
  pointer-mode
  keyboard-mode
  confine-to
  cursor
  button
  modifiers]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CURSOR)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 1 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [28
   (if (:owner-events this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:grab-window this)
   (:event-mask this)
   (:pointer-mode this)
   (:keyboard-mode this)
   (:confine-to this)
   (:cursor this)
   (:button this)
   (clojure.core/repeat 1 0)
   (:modifiers this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UngrabButtonRequest
 [opcode button grab-window modifiers]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [29
   (:button this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:grab-window this)
   (:modifiers this)
   (clojure.core/repeat 2 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeActivePointerGrabRequest
 [opcode cursor time event-mask]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CURSOR)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [30
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cursor this)
   (:time this)
   (:event-mask this)
   (clojure.core/repeat 2 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GrabKeyboardRequest
 [opcode owner-events grab-window time pointer-mode keyboard-mode]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/BYTE)
   2))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (.to-frame xcljb.gen.xproto-types/BYTE)
   (.to-frame xcljb.gen.xproto-types/BYTE)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [31
   (if (:owner-events this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:grab-window this)
   (:time this)
   (:pointer-mode this)
   (:keyboard-mode this)
   (clojure.core/repeat 2 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UngrabKeyboardRequest
 [opcode time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [32
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GrabKeyRequest
 [opcode
  owner-events
  grab-window
  modifiers
  key
  pointer-mode
  keyboard-mode]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/KEYCODE)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/KEYCODE)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 3 :byte)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [33
   (if (:owner-events this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:grab-window this)
   (:modifiers this)
   (:key this)
   (:pointer-mode this)
   (:keyboard-mode this)
   (clojure.core/repeat 3 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UngrabKeyRequest
 [opcode key grab-window modifiers]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/KEYCODE)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/KEYCODE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [34
   (:key this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:grab-window this)
   (:modifiers this)
   (clojure.core/repeat 2 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 AllowEventsRequest
 [opcode mode time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [35
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GrabServerRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [36
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UngrabServerRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [37
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryPointerRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [38
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetMotionEventsRequest
 [opcode window start stop]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [39
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:start this)
   (:stop this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 TranslateCoordinatesRequest
 [opcode src-window dst-window src-x src-y]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [40
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:src-window this)
   (:dst-window this)
   (:src-x this)
   (:src-y this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 WarpPointerRequest
 [opcode
  src-window
  dst-window
  src-x
  src-y
  src-width
  src-height
  dst-x
  dst-y]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [41
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:src-window this)
   (:dst-window this)
   (:src-x this)
   (:src-y this)
   (:src-width this)
   (:src-height this)
   (:dst-x this)
   (:dst-y this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetInputFocusRequest
 [opcode revert-to focus time]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/TIMESTAMP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/TIMESTAMP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [42
   (:revert-to this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:focus this)
   (:time this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetInputFocusRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [43
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryKeymapRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [44
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 OpenFontRequest
 [opcode fid name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/FONT)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/FONT)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [45
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:fid this)
   (:name-len this)
   (clojure.core/repeat 2 0)
   (:name this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CloseFontRequest
 [opcode font]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/FONT)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/FONT)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [46
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:font this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryFontRequest
 [opcode font]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/FONTABLE)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/FONTABLE)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [47
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:font this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ListFontsRequest
 [opcode max-names pattern-len pattern]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/count (:pattern this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [49
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:max-names this)
   (:pattern-len this)
   (:pattern this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ListFontsWithInfoRequest
 [opcode max-names pattern-len pattern]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/count (:pattern this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [50
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:max-names this)
   (:pattern-len this)
   (:pattern this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetFontPathRequest
 [opcode font-qty font]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:font this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:font this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [51
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:font-qty this)
   (clojure.core/repeat 2 0)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:font this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetFontPathRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [52
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CreatePixmapRequest
 [opcode depth pid drawable width height]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/PIXMAP)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/PIXMAP)
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [53
   (:depth this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:pid this)
   (:drawable this)
   (:width this)
   (:height this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 FreePixmapRequest
 [opcode pixmap]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/PIXMAP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/PIXMAP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [54
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:pixmap this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CreateGCRequest
 [opcode cid drawable value]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (clojure.core/+
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/*
     (clojure.core/-> this (:value) (clojure.core/count))
     4))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   [(.to-frame xcljb.gen.xproto-types/CARD32)
    (clojure.core/repeat
     (clojure.core/-> this (:value) (clojure.core/count))
     :uint32)]
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [55
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cid this)
   (:drawable this)
   (xcljb.gen-common/valueparam->value (:value this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeGCRequest
 [opcode gc value]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/+
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/*
     (clojure.core/-> this (:value) (clojure.core/count))
     4))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   [(.to-frame xcljb.gen.xproto-types/CARD32)
    (clojure.core/repeat
     (clojure.core/-> this (:value) (clojure.core/count))
     :uint32)]
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [56
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:gc this)
   (xcljb.gen-common/valueparam->value (:value this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CopyGCRequest
 [opcode src-gc dst-gc value-mask]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/CARD32)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [57
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:src-gc this)
   (:dst-gc this)
   (:value-mask this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetDashesRequest
 [opcode gc dash-offset dashes-len dashes]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/CARD8)
    (clojure.core/count (:dashes this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (clojure.core/count (:dashes this))
    (.to-frame xcljb.gen.xproto-types/CARD8))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [58
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:gc this)
   (:dash-offset this)
   (:dashes-len this)
   (:dashes this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetClipRectanglesRequest
 [opcode ordering gc clip-x-origin clip-y-origin rectangles]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:rectangles this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:rectangles this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [59
   (:ordering this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:gc this)
   (:clip-x-origin this)
   (:clip-y-origin this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:rectangles this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 FreeGCRequest
 [opcode gc]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/GCONTEXT)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [60
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:gc this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ClearAreaRequest
 [opcode exposures window x y width height]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [61
   (if (:exposures this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:x this)
   (:y this)
   (:width this)
   (:height this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CopyAreaRequest
 [opcode
  src-drawable
  dst-drawable
  gc
  src-x
  src-y
  dst-x
  dst-y
  width
  height]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [62
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:src-drawable this)
   (:dst-drawable this)
   (:gc this)
   (:src-x this)
   (:src-y this)
   (:dst-x this)
   (:dst-y this)
   (:width this)
   (:height this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CopyPlaneRequest
 [opcode
  src-drawable
  dst-drawable
  gc
  src-x
  src-y
  dst-x
  dst-y
  width
  height
  bit-plane]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD32)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [63
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:src-drawable this)
   (:dst-drawable this)
   (:gc this)
   (:src-x this)
   (:src-y this)
   (:dst-x this)
   (:dst-y this)
   (:width this)
   (:height this)
   (:bit-plane this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyPointRequest
 [opcode coordinate-mode drawable gc points]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:points this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:points this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [64
   (:coordinate-mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:points this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyLineRequest
 [opcode coordinate-mode drawable gc points]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:points this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:points this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [65
   (:coordinate-mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:points this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolySegmentRequest
 [opcode drawable gc segments]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:segments this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:segments this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [66
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:segments this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyRectangleRequest
 [opcode drawable gc rectangles]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:rectangles this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:rectangles this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [67
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:rectangles this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyArcRequest
 [opcode drawable gc arcs]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:arcs this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:arcs this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [68
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:arcs this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 FillPolyRequest
 [opcode drawable gc shape coordinate-mode points]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   2
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:points this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 2 :byte)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:points this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [69
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (:shape this)
   (:coordinate-mode this)
   (clojure.core/repeat 2 0)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:points this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyFillRectangleRequest
 [opcode drawable gc rectangles]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:rectangles this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:rectangles this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [70
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:rectangles this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyFillArcRequest
 [opcode drawable gc arcs]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:arcs this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:arcs this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [71
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:arcs this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PutImageRequest
 [opcode
  format
  drawable
  gc
  width
  height
  dst-x
  dst-y
  left-pad
  depth
  data]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   2
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/BYTE)
    (clojure.core/count (:data this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (clojure.core/count (:data this))
    (.to-frame xcljb.gen.xproto-types/BYTE))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [72
   (:format this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (:width this)
   (:height this)
   (:dst-x this)
   (:dst-y this)
   (:left-pad this)
   (:depth this)
   (clojure.core/repeat 2 0)
   (:data this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyText8Request
 [opcode drawable gc x y items]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/BYTE)
    (clojure.core/count (:items this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/repeat
    (clojure.core/count (:items this))
    (.to-frame xcljb.gen.xproto-types/BYTE))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [74
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (:x this)
   (:y this)
   (:items this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 PolyText16Request
 [opcode drawable gc x y items]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/BYTE)
    (clojure.core/count (:items this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/repeat
    (clojure.core/count (:items this))
    (.to-frame xcljb.gen.xproto-types/BYTE))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [75
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (:x this)
   (:y this)
   (:items this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ImageText8Request
 [opcode string-len drawable gc x y string]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (clojure.core/count (:string this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [76
   (:string-len this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (:x this)
   (:y this)
   (:string this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ImageText16Request
 [opcode string-len drawable gc x y string]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/GCONTEXT)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:string this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/GCONTEXT)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:string this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [77
   (:string-len this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:gc this)
   (:x this)
   (:y this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:string this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CreateColormapRequest
 [opcode alloc mid window visual]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/BYTE)
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/VISUALID)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/BYTE)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/VISUALID)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [78
   (:alloc this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:mid this)
   (:window this)
   (:visual this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 FreeColormapRequest
 [opcode cmap]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/COLORMAP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [79
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CopyColormapAndFreeRequest
 [opcode mid src-cmap]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/COLORMAP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [80
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:mid this)
   (:src-cmap this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 InstallColormapRequest
 [opcode cmap]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/COLORMAP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [81
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 UninstallColormapRequest
 [opcode cmap]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/COLORMAP)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [82
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ListInstalledColormapsRequest
 [opcode window]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [83
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 AllocColorRequest
 [opcode cmap red green blue]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [84
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:red this)
   (:green this)
   (:blue this)
   (clojure.core/repeat 2 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 AllocNamedColorRequest
 [opcode cmap name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [85
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:name-len this)
   (clojure.core/repeat 2 0)
   (:name this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 AllocColorCellsRequest
 [opcode contiguous cmap colors planes]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [86
   (if (:contiguous this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:colors this)
   (:planes this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 AllocColorPlanesRequest
 [opcode contiguous cmap colors reds greens blues]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   :ubyte
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [87
   (if (:contiguous this) 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:colors this)
   (:reds this)
   (:greens this)
   (:blues this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 FreeColorsRequest
 [opcode cmap plane-mask pixels]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/count (:pixels this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat
    (clojure.core/count (:pixels this))
    (.to-frame xcljb.gen.xproto-types/CARD32))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [88
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:plane-mask this)
   (:pixels this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 StoreColorsRequest
 [opcode cmap items]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/reduce
    (clojure.core/fn
     [x__925__auto__ y__926__auto__]
     (clojure.core/+ x__925__auto__ (.sizeof y__926__auto__)))
    0
    (:items this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/map
    (fn* [p1__929__930__auto__] (.to-frame p1__929__930__auto__))
    (:items this))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [89
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (clojure.core/map
    (fn* [p1__931__932__auto__] (.to-value p1__931__932__auto__))
    (:items this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 StoreNamedColorRequest
 [opcode flags cmap pixel name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD32)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [90
   (:flags this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:pixel this)
   (:name-len this)
   (clojure.core/repeat 2 0)
   (:name this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryColorsRequest
 [opcode cmap pixels]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/count (:pixels this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (clojure.core/repeat
    (clojure.core/count (:pixels this))
    (.to-frame xcljb.gen.xproto-types/CARD32))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [91
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:pixels this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 LookupColorRequest
 [opcode cmap name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/COLORMAP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/COLORMAP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [92
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cmap this)
   (:name-len this)
   (clojure.core/repeat 2 0)
   (:name this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CreateCursorRequest
 [opcode
  cid
  source
  mask
  fore-red
  fore-green
  fore-blue
  back-red
  back-green
  back-blue
  x
  y]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CURSOR)
   (.sizeof xcljb.gen.xproto-types/PIXMAP)
   (.sizeof xcljb.gen.xproto-types/PIXMAP)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (.to-frame xcljb.gen.xproto-types/PIXMAP)
   (.to-frame xcljb.gen.xproto-types/PIXMAP)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [93
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cid this)
   (:source this)
   (:mask this)
   (:fore-red this)
   (:fore-green this)
   (:fore-blue this)
   (:back-red this)
   (:back-green this)
   (:back-blue this)
   (:x this)
   (:y this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 CreateGlyphCursorRequest
 [opcode
  cid
  source-font
  mask-font
  source-char
  mask-char
  fore-red
  fore-green
  fore-blue
  back-red
  back-green
  back-blue]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CURSOR)
   (.sizeof xcljb.gen.xproto-types/FONT)
   (.sizeof xcljb.gen.xproto-types/FONT)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (.to-frame xcljb.gen.xproto-types/FONT)
   (.to-frame xcljb.gen.xproto-types/FONT)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [94
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cid this)
   (:source-font this)
   (:mask-font this)
   (:source-char this)
   (:mask-char this)
   (:fore-red this)
   (:fore-green this)
   (:fore-blue this)
   (:back-red this)
   (:back-green this)
   (:back-blue this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 FreeCursorRequest
 [opcode cursor]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/CURSOR)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [95
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cursor this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 RecolorCursorRequest
 [opcode
  cursor
  fore-red
  fore-green
  fore-blue
  back-red
  back-green
  back-blue]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CURSOR)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CURSOR)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [96
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:cursor this)
   (:fore-red this)
   (:fore-green this)
   (:fore-blue this)
   (:back-red this)
   (:back-green this)
   (:back-blue this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryBestSizeRequest
 [opcode class drawable width height]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/DRAWABLE)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/CARD16)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/DRAWABLE)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [97
   (:class this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:drawable this)
   (:width this)
   (:height this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 QueryExtensionRequest
 [opcode name-len name]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   2
   (clojure.core/count (:name this))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat 2 :byte)
   (gloss.core/string :ascii)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [98
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:name-len this)
   (clojure.core/repeat 2 0)
   (:name this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ListExtensionsRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [99
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeKeyboardMappingRequest
 [opcode keycode-count first-keycode keysyms-per-keycode keysyms]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/KEYCODE)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   2
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/KEYSYM)
    (clojure.core/count (:keysyms this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/KEYCODE)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 2 :byte)
   (clojure.core/repeat
    (clojure.core/count (:keysyms this))
    (.to-frame xcljb.gen.xproto-types/KEYSYM))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [100
   (:keycode-count this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:first-keycode this)
   (:keysyms-per-keycode this)
   (clojure.core/repeat 2 0)
   (:keysyms this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeKeyboardControlRequest
 [opcode value]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (clojure.core/+
    (.sizeof xcljb.gen.xproto-types/CARD32)
    (clojure.core/*
     (clojure.core/-> this (:value) (clojure.core/count))
     4))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   [(.to-frame xcljb.gen.xproto-types/CARD32)
    (clojure.core/repeat
     (clojure.core/-> this (:value) (clojure.core/count))
     :uint32)]
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [102
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (xcljb.gen-common/valueparam->value (:value this))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetKeyboardControlRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [103
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 BellRequest
 [opcode percent]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 (.sizeof xcljb.gen.xproto-types/INT8)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/INT8)
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [104
   (:percent this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangePointerControlRequest
 [opcode
  acceleration-numerator
  acceleration-denominator
  threshold
  do-acceleration
  do-threshold]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   1
   1))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   :ubyte
   :ubyte
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [105
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:acceleration-numerator this)
   (:acceleration-denominator this)
   (:threshold this)
   (if (:do-acceleration this) 1 0)
   (if (:do-threshold this) 1 0)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetPointerControlRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [106
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetScreenSaverRequest
 [opcode timeout interval prefer-blanking allow-exposures]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [107
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:timeout this)
   (:interval this)
   (:prefer-blanking this)
   (:allow-exposures this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetScreenSaverRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [108
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ChangeHostsRequest
 [opcode mode family address-len address]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (.sizeof xcljb.gen.xproto-types/CARD8)
   1
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/BYTE)
    (clojure.core/count (:address this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD8)
   (clojure.core/repeat 1 :byte)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (clojure.core/repeat
    (clojure.core/count (:address this))
    (.to-frame xcljb.gen.xproto-types/BYTE))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [109
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:family this)
   (clojure.core/repeat 1 0)
   (:address-len this)
   (:address this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ListHostsRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [110
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetAccessControlRequest
 [opcode mode]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 (.sizeof xcljb.gen.xproto-types/CARD8)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [111
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetCloseDownModeRequest
 [opcode mode]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 (.sizeof xcljb.gen.xproto-types/CARD8)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [112
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 KillClientRequest
 [opcode resource]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 1 (.sizeof xcljb.gen.xproto-types/CARD32)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/CARD32)
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [113
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:resource this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 RotatePropertiesRequest
 [opcode window atoms-len delta atoms]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   1
   (.sizeof xcljb.gen.xproto-types/WINDOW)
   (.sizeof xcljb.gen.xproto-types/CARD16)
   (.sizeof xcljb.gen.xproto-types/INT16)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/ATOM)
    (clojure.core/count (:atoms this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (clojure.core/repeat 1 :byte)
   :uint16
   (.to-frame xcljb.gen.xproto-types/WINDOW)
   (.to-frame xcljb.gen.xproto-types/CARD16)
   (.to-frame xcljb.gen.xproto-types/INT16)
   (clojure.core/repeat
    (clojure.core/count (:atoms this))
    (.to-frame xcljb.gen.xproto-types/ATOM))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [114
   (clojure.core/repeat 1 0)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:window this)
   (:atoms-len this)
   (:delta this)
   (:atoms this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 ForceScreenSaverRequest
 [opcode mode]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+ 3 (.sizeof xcljb.gen.xproto-types/CARD8)))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [115
   (:mode this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetPointerMappingRequest
 [opcode map-len map]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/CARD8)
    (clojure.core/count (:map this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (clojure.core/repeat
    (clojure.core/count (:map this))
    (.to-frame xcljb.gen.xproto-types/CARD8))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [116
   (:map-len this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:map this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetPointerMappingRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [117
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 SetModifierMappingRequest
 [opcode keycodes-per-modifier keycodes]
 xcljb.gen-common/Measurable
 (sizeof
  [this]
  (clojure.core/+
   3
   (.sizeof xcljb.gen.xproto-types/CARD8)
   (clojure.core/*
    (.sizeof xcljb.gen.xproto-types/KEYCODE)
    (clojure.core/count (:keycodes this)))))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   (.to-frame xcljb.gen.xproto-types/CARD8)
   :uint16
   (clojure.core/repeat
    (clojure.core/count (:keycodes this))
    (.to-frame xcljb.gen.xproto-types/KEYCODE))
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [118
   (:keycodes-per-modifier this)
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (:keycodes this)
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetModifierMappingRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [119
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 NoOperationRequest
 [opcode]
 xcljb.gen-common/Measurable
 (sizeof [this] (clojure.core/+ 3))
 xcljb.gen-common/Serializable
 (to-frame
  [this]
  [:ubyte
   nil
   :uint16
   (clojure.core/repeat
    (xcljb.gen-common/padding (.sizeof this))
    :byte)])
 (to-value
  [this]
  [127
   nil
   (clojure.core/int
    (java.lang.Math/ceil (clojure.core// (.sizeof this) 4)))
   (clojure.core/repeat (xcljb.gen-common/padding (.sizeof this)) 0)]))

(clojure.core/defrecord
 GetWindowAttributesReply
 [backing-store
  visual
  class
  bit-gravity
  win-gravity
  backing-planes
  backing-pixel
  save-under
  map-is-installed
  map-state
  override-redirect
  colormap
  all-event-masks
  your-event-mask
  do-not-propagate-mask])

(clojure.core/defrecord
 GetGeometryReply
 [depth root x y width height border-width])

(clojure.core/defrecord
 QueryTreeReply
 [root parent children-len children])

(clojure.core/defrecord InternAtomReply [atom])

(clojure.core/defrecord GetAtomNameReply [name-len name])

(clojure.core/defrecord
 GetPropertyReply
 [format type bytes-after value-len value])

(clojure.core/defrecord ListPropertiesReply [atoms-len atoms])

(clojure.core/defrecord GetSelectionOwnerReply [owner])

(clojure.core/defrecord GrabPointerReply [status])

(clojure.core/defrecord GrabKeyboardReply [status])

(clojure.core/defrecord
 QueryPointerReply
 [same-screen root child root-x root-y win-x win-y mask])

(clojure.core/defrecord GetMotionEventsReply [events-len events])

(clojure.core/defrecord
 TranslateCoordinatesReply
 [same-screen child dst-x dst-y])

(clojure.core/defrecord GetInputFocusReply [revert-to focus])

(clojure.core/defrecord QueryKeymapReply [keys])

(clojure.core/defrecord
 QueryFontReply
 [min-bounds
  max-bounds
  min-char-or-byte2
  max-char-or-byte2
  default-char
  properties-len
  draw-direction
  min-byte1
  max-byte1
  all-chars-exist
  font-ascent
  font-descent
  char-infos-len
  properties
  char-infos])

(clojure.core/defrecord ListFontsReply [names-len names])

(clojure.core/defrecord
 ListFontsWithInfoReply
 [name-len
  min-bounds
  max-bounds
  min-char-or-byte2
  max-char-or-byte2
  default-char
  properties-len
  draw-direction
  min-byte1
  max-byte1
  all-chars-exist
  font-ascent
  font-descent
  replies-hint
  properties
  name])

(clojure.core/defrecord GetFontPathReply [path-len path])

(clojure.core/defrecord ListInstalledColormapsReply [cmaps-len cmaps])

(clojure.core/defrecord AllocColorReply [red green blue pixel])

(clojure.core/defrecord
 AllocNamedColorReply
 [pixel
  exact-red
  exact-green
  exact-blue
  visual-red
  visual-green
  visual-blue])

(clojure.core/defrecord
 AllocColorCellsReply
 [pixels-len masks-len pixels masks])

(clojure.core/defrecord
 AllocColorPlanesReply
 [pixels-len red-mask green-mask blue-mask pixels])

(clojure.core/defrecord QueryColorsReply [colors-len colors])

(clojure.core/defrecord
 LookupColorReply
 [exact-red exact-green exact-blue visual-red visual-green visual-blue])

(clojure.core/defrecord QueryBestSizeReply [width height])

(clojure.core/defrecord
 QueryExtensionReply
 [present major-opcode first-event first-error])

(clojure.core/defrecord ListExtensionsReply [names-len names])

(clojure.core/defrecord
 GetKeyboardControlReply
 [global-auto-repeat
  led-mask
  key-click-percent
  bell-percent
  bell-pitch
  bell-duration
  auto-repeats])

(clojure.core/defrecord
 GetPointerControlReply
 [acceleration-numerator acceleration-denominator threshold])

(clojure.core/defrecord
 GetScreenSaverReply
 [timeout interval prefer-blanking allow-exposures])

(clojure.core/defrecord ListHostsReply [mode hosts-len hosts])

(clojure.core/defrecord SetPointerMappingReply [status])

(clojure.core/defrecord GetPointerMappingReply [map-len map])

(clojure.core/defrecord SetModifierMappingReply [status])

(clojure.core/defrecord
 GetModifierMappingReply
 [keycodes-per-modifier keycodes])

(clojure.core/defrecord
 KeyPressEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  same-screen])

(clojure.core/defrecord
 KeyReleaseEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  same-screen])

(clojure.core/defrecord
 ButtonPressEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  same-screen])

(clojure.core/defrecord
 ButtonReleaseEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  same-screen])

(clojure.core/defrecord
 MotionNotifyEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  same-screen])

(clojure.core/defrecord
 EnterNotifyEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  mode
  same-screen-focus])

(clojure.core/defrecord
 LeaveNotifyEvent
 [detail
  time
  root
  event
  child
  root-x
  root-y
  event-x
  event-y
  state
  mode
  same-screen-focus])

(clojure.core/defrecord FocusInEvent [detail event mode])

(clojure.core/defrecord FocusOutEvent [detail event mode])

(clojure.core/defrecord KeymapNotifyEvent [keys])

(clojure.core/defrecord ExposeEvent [window x y width height count])

(clojure.core/defrecord
 GraphicsExposureEvent
 [drawable x y width height minor-opcode count major-opcode])

(clojure.core/defrecord
 NoExposureEvent
 [drawable minor-opcode major-opcode])

(clojure.core/defrecord VisibilityNotifyEvent [window state])

(clojure.core/defrecord
 CreateNotifyEvent
 [parent window x y width height border-width override-redirect])

(clojure.core/defrecord DestroyNotifyEvent [event window])

(clojure.core/defrecord UnmapNotifyEvent [event window from-configure])

(clojure.core/defrecord MapNotifyEvent [event window override-redirect])

(clojure.core/defrecord MapRequestEvent [parent window])

(clojure.core/defrecord
 ReparentNotifyEvent
 [event window parent x y override-redirect])

(clojure.core/defrecord
 ConfigureNotifyEvent
 [event
  window
  above-sibling
  x
  y
  width
  height
  border-width
  override-redirect])

(clojure.core/defrecord
 ConfigureRequestEvent
 [stack-mode
  parent
  window
  sibling
  x
  y
  width
  height
  border-width
  value-mask])

(clojure.core/defrecord GravityNotifyEvent [event window x y])

(clojure.core/defrecord ResizeRequestEvent [window width height])

(clojure.core/defrecord CirculateNotifyEvent [event window place])

(clojure.core/defrecord CirculateRequestEvent [event window place])

(clojure.core/defrecord PropertyNotifyEvent [window atom time state])

(clojure.core/defrecord SelectionClearEvent [time owner selection])

(clojure.core/defrecord
 SelectionRequestEvent
 [time owner requestor selection target property])

(clojure.core/defrecord
 SelectionNotifyEvent
 [time requestor selection target property])

(clojure.core/defrecord ColormapNotifyEvent [window colormap new state])

(clojure.core/defrecord
 MappingNotifyEvent
 [request first-keycode count])

(clojure.core/defrecord
 RequestError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 ValueError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 WindowError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 PixmapError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord AtomError [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 CursorError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord FontError [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 MatchError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 DrawableError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 AccessError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 AllocError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 ColormapError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 GContextError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 IDChoiceError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord NameError [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 LengthError
 [bad-value minor-opcode major-opcode])

(clojure.core/defrecord
 ImplementationError
 [bad-value minor-opcode major-opcode])
