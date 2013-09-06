(clojure.core/ns
 xcljb.gen.xproto-internal
 (:require [xcljb gen-common] [xcljb.gen xproto-types]))

(clojure.core/defn
 read-Char2b
 [ch]
 (clojure.core/let
  [byte1
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   byte2
   (.read-type xcljb.gen.xproto-types/CARD8 ch)]
  (xcljb.gen.xproto-types/->Char2b byte1 byte2)))

(clojure.core/defn
 read-Point
 [ch]
 (clojure.core/let
  [x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)]
  (xcljb.gen.xproto-types/->Point x y)))

(clojure.core/defn
 read-Rectangle
 [ch]
 (clojure.core/let
  [x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)]
  (xcljb.gen.xproto-types/->Rectangle x y width height)))

(clojure.core/defn
 read-Arc
 [ch]
 (clojure.core/let
  [x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   angle1
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   angle2
   (.read-type xcljb.gen.xproto-types/INT16 ch)]
  (xcljb.gen.xproto-types/->Arc x y width height angle1 angle2)))

(clojure.core/defn
 read-Format
 [ch]
 (clojure.core/let
  [depth
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   bits-per-pixel
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   scanline-pad
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 5)]
  (xcljb.gen.xproto-types/->Format depth bits-per-pixel scanline-pad)))

(clojure.core/defn
 read-Visualtype
 [ch]
 (clojure.core/let
  [visual-id
   (.read-type xcljb.gen.xproto-types/VISUALID ch)
   class
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   bits-per-rgb-value
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   colormap-entries
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   red-mask
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   green-mask
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   blue-mask
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   _
   (xcljb.gen-common/read-pad ch 4)]
  (xcljb.gen.xproto-types/->Visualtype
   visual-id
   class
   bits-per-rgb-value
   colormap-entries
   red-mask
   green-mask
   blue-mask)))

(clojure.core/defn
 read-Depth
 [ch]
 (clojure.core/let
  [depth
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   visuals-len
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   visuals
   (clojure.core/doall
    (clojure.core/repeatedly
     visuals-len
     (clojure.core/fn
      []
      (xcljb.gen.xproto-internal/read-Visualtype ch))))]
  (xcljb.gen.xproto-types/->Depth depth visuals-len visuals)))

(clojure.core/defn
 read-Screen
 [ch]
 (clojure.core/let
  [root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   default-colormap
   (.read-type xcljb.gen.xproto-types/COLORMAP ch)
   white-pixel
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   black-pixel
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   current-input-masks
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   width-in-pixels
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height-in-pixels
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   width-in-millimeters
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height-in-millimeters
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   min-installed-maps
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   max-installed-maps
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   root-visual
   (.read-type xcljb.gen.xproto-types/VISUALID ch)
   backing-stores
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   save-unders
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   root-depth
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   allowed-depths-len
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   allowed-depths
   (clojure.core/doall
    (clojure.core/repeatedly
     allowed-depths-len
     (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Depth ch))))]
  (xcljb.gen.xproto-types/->Screen
   root
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
   allowed-depths)))

(clojure.core/defn
 read-SetupRequest
 [ch]
 (clojure.core/let
  [byte-order
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   protocol-major-version
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   protocol-minor-version
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   authorization-protocol-name-len
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   authorization-protocol-data-len
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 2)
   authorization-protocol-name
   (xcljb.gen-common/read-string ch authorization-protocol-name-len)
   authorization-protocol-data
   (xcljb.gen-common/read-string ch authorization-protocol-data-len)]
  (xcljb.gen.xproto-types/->SetupRequest
   byte-order
   protocol-major-version
   protocol-minor-version
   authorization-protocol-name-len
   authorization-protocol-data-len
   authorization-protocol-name
   authorization-protocol-data)))

(clojure.core/defn
 read-SetupFailed
 [ch]
 (clojure.core/let
  [status
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   reason-len
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   protocol-major-version
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   protocol-minor-version
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   length
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   reason
   (xcljb.gen-common/read-string ch reason-len)]
  (xcljb.gen.xproto-types/->SetupFailed
   status
   reason-len
   protocol-major-version
   protocol-minor-version
   length
   reason)))

(clojure.core/defn
 read-SetupAuthenticate
 [ch]
 (clojure.core/let
  [status
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 5)
   length
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   reason
   (xcljb.gen-common/read-string ch (clojure.core/* length 4))]
  (xcljb.gen.xproto-types/->SetupAuthenticate status length reason)))

(clojure.core/defn
 read-Setup
 [ch]
 (clojure.core/let
  [status
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   protocol-major-version
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   protocol-minor-version
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   length
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   release-number
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   resource-id-base
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   resource-id-mask
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   motion-buffer-size
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   vendor-len
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   maximum-request-length
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   roots-len
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   pixmap-formats-len
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   image-byte-order
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   bitmap-format-bit-order
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   bitmap-format-scanline-unit
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   bitmap-format-scanline-pad
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   min-keycode
   (.read-type xcljb.gen.xproto-types/KEYCODE ch)
   max-keycode
   (.read-type xcljb.gen.xproto-types/KEYCODE ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   vendor
   (xcljb.gen-common/read-string ch vendor-len)
   pixmap-formats
   (clojure.core/doall
    (clojure.core/repeatedly
     pixmap-formats-len
     (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Format ch))))
   roots
   (clojure.core/doall
    (clojure.core/repeatedly
     roots-len
     (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Screen ch))))]
  (xcljb.gen.xproto-types/->Setup
   status
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
   roots)))

(clojure.core/defn
 read-Timecoord
 [ch]
 (clojure.core/let
  [time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)]
  (xcljb.gen.xproto-types/->Timecoord time x y)))

(clojure.core/defn
 read-Fontprop
 [ch]
 (clojure.core/let
  [name
   (.read-type xcljb.gen.xproto-types/ATOM ch)
   value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)]
  (xcljb.gen.xproto-types/->Fontprop name value)))

(clojure.core/defn
 read-Charinfo
 [ch]
 (clojure.core/let
  [left-side-bearing
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   right-side-bearing
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   character-width
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   ascent
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   descent
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   attributes
   (.read-type xcljb.gen.xproto-types/CARD16 ch)]
  (xcljb.gen.xproto-types/->Charinfo
   left-side-bearing
   right-side-bearing
   character-width
   ascent
   descent
   attributes)))

(clojure.core/defn
 read-Str
 [ch]
 (clojure.core/let
  [name-len
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   name
   (xcljb.gen-common/read-string ch name-len)]
  (xcljb.gen.xproto-types/->Str name-len name)))

(clojure.core/defn
 read-Segment
 [ch]
 (clojure.core/let
  [x1
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y1
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   x2
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y2
   (.read-type xcljb.gen.xproto-types/INT16 ch)]
  (xcljb.gen.xproto-types/->Segment x1 y1 x2 y2)))

(clojure.core/defn
 read-Coloritem
 [ch]
 (clojure.core/let
  [pixel
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   red
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   green
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   blue
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   flags
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (xcljb.gen.xproto-types/->Coloritem pixel red green blue flags)))

(clojure.core/defn
 read-Rgb
 [ch]
 (clojure.core/let
  [red
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   green
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   blue
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 2)]
  (xcljb.gen.xproto-types/->Rgb red green blue)))

(clojure.core/defn
 read-Host
 [ch]
 (clojure.core/let
  [family
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   address-len
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   address
   (clojure.core/doall
    (clojure.core/repeatedly
     address-len
     (clojure.core/fn
      []
      (.read-type xcljb.gen.xproto-types/BYTE ch))))]
  (xcljb.gen.xproto-types/->Host family address-len address)))

(clojure.core/defn
 read-GetWindowAttributesReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [backing-store val__1137__auto__]
  (clojure.core/let
   [visual
    (.read-type xcljb.gen.xproto-types/VISUALID ch)
    class
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    bit-gravity
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    win-gravity
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    backing-planes
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    backing-pixel
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    save-under
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    map-is-installed
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    map-state
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    override-redirect
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    colormap
    (.read-type xcljb.gen.xproto-types/COLORMAP ch)
    all-event-masks
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    your-event-mask
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    do-not-propagate-mask
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/VISUALID)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       1
       1
       (.sizeof xcljb.gen.xproto-types/CARD8)
       1
       (.sizeof xcljb.gen.xproto-types/COLORMAP)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       2))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetWindowAttributesReply
    backing-store
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
    do-not-propagate-mask))))

(clojure.core/defn
 read-GetGeometryReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [depth val__1137__auto__]
  (clojure.core/let
   [root
    (.read-type xcljb.gen.xproto-types/WINDOW ch)
    x
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    y
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    width
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    height
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    border-width
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/WINDOW)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       2))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetGeometryReply
    depth
    root
    x
    y
    width
    height
    border-width))))

(clojure.core/defn
 read-QueryTreeReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [root
    (.read-type xcljb.gen.xproto-types/WINDOW ch)
    parent
    (.read-type xcljb.gen.xproto-types/WINDOW ch)
    children-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 14)
    children
    (clojure.core/doall
     (clojure.core/repeatedly
      children-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/WINDOW ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/WINDOW)
       (.sizeof xcljb.gen.xproto-types/WINDOW)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       14
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/WINDOW)
        children-len)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryTreeReply
    root
    parent
    children-len
    children))))

(clojure.core/defn
 read-InternAtomReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [atom (.read-type xcljb.gen.xproto-types/ATOM ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+ 1 (.sizeof xcljb.gen.xproto-types/ATOM)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->InternAtomReply atom))))

(clojure.core/defn
 read-GetAtomNameReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [name-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    name
    (xcljb.gen-common/read-string ch name-len)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       name-len))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetAtomNameReply name-len name))))

(clojure.core/defn
 read-GetPropertyReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [format val__1137__auto__]
  (clojure.core/let
   [type
    (.read-type xcljb.gen.xproto-types/ATOM ch)
    bytes-after
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    value-len
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    _
    (xcljb.gen-common/read-pad ch 12)
    value
    (clojure.core/doall
     (clojure.core/repeatedly
      (clojure.core/* value-len (clojure.core// format 8))
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/void ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/ATOM)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       12
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/void)
        (clojure.core/* value-len (clojure.core// format 8)))))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetPropertyReply
    format
    type
    bytes-after
    value-len
    value))))

(clojure.core/defn
 read-ListPropertiesReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [atoms-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    atoms
    (clojure.core/doall
     (clojure.core/repeatedly
      atoms-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/ATOM ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/ATOM)
        atoms-len)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->ListPropertiesReply atoms-len atoms))))

(clojure.core/defn
 read-GetSelectionOwnerReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [owner (.read-type xcljb.gen.xproto-types/WINDOW ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+ 1 (.sizeof xcljb.gen.xproto-types/WINDOW)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetSelectionOwnerReply owner))))

(clojure.core/defn
 read-GrabPointerReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [status val__1137__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+ (.sizeof xcljb.gen.xproto-types/BYTE)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GrabPointerReply status))))

(clojure.core/defn
 read-GrabKeyboardReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [status val__1137__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+ (.sizeof xcljb.gen.xproto-types/BYTE)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GrabKeyboardReply status))))

(clojure.core/defn
 read-QueryPointerReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [same-screen val__1137__auto__]
  (clojure.core/let
   [root
    (.read-type xcljb.gen.xproto-types/WINDOW ch)
    child
    (.read-type xcljb.gen.xproto-types/WINDOW ch)
    root-x
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    root-y
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    win-x
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    win-y
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    mask
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/WINDOW)
       (.sizeof xcljb.gen.xproto-types/WINDOW)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       2))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryPointerReply
    same-screen
    root
    child
    root-x
    root-y
    win-x
    win-y
    mask))))

(clojure.core/defn
 read-GetMotionEventsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [events-len
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    _
    (xcljb.gen-common/read-pad ch 20)
    events
    (clojure.core/doall
     (clojure.core/repeatedly
      events-len
      (clojure.core/fn
       []
       (xcljb.gen.xproto-internal/read-Timecoord ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD32)
       20
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        events)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetMotionEventsReply events-len events))))

(clojure.core/defn
 read-TranslateCoordinatesReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [same-screen val__1137__auto__]
  (clojure.core/let
   [child
    (.read-type xcljb.gen.xproto-types/WINDOW ch)
    dst-x
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    dst-y
    (.read-type xcljb.gen.xproto-types/INT16 ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/WINDOW)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->TranslateCoordinatesReply
    same-screen
    child
    dst-x
    dst-y))))

(clojure.core/defn
 read-GetInputFocusReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [revert-to val__1137__auto__]
  (clojure.core/let
   [focus (.read-type xcljb.gen.xproto-types/WINDOW ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/WINDOW)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetInputFocusReply revert-to focus))))

(clojure.core/defn
 read-QueryKeymapReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [keys
    (clojure.core/doall
     (clojure.core/repeatedly
      32
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/CARD8 ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (clojure.core/* (.sizeof xcljb.gen.xproto-types/CARD8) 32)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryKeymapReply keys))))

(clojure.core/defn
 read-QueryFontReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [min-bounds
    (xcljb.gen.xproto-internal/read-Charinfo ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    max-bounds
    (xcljb.gen.xproto-internal/read-Charinfo ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    min-char-or-byte2
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    max-char-or-byte2
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    default-char
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    properties-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    draw-direction
    (.read-type xcljb.gen.xproto-types/BYTE ch)
    min-byte1
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    max-byte1
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    all-chars-exist
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    font-ascent
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    font-descent
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    char-infos-len
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    properties
    (clojure.core/doall
     (clojure.core/repeatedly
      properties-len
      (clojure.core/fn
       []
       (xcljb.gen.xproto-internal/read-Fontprop ch))))
    char-infos
    (clojure.core/doall
     (clojure.core/repeatedly
      char-infos-len
      (clojure.core/fn
       []
       (xcljb.gen.xproto-internal/read-Charinfo ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof min-bounds)
       4
       (.sizeof max-bounds)
       4
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/BYTE)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       1
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        properties)
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        char-infos)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryFontReply
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
    char-infos-len
    properties
    char-infos))))

(clojure.core/defn
 read-ListFontsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [names-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    names
    (clojure.core/doall
     (clojure.core/repeatedly
      names-len
      (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Str ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        names)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->ListFontsReply names-len names))))

(clojure.core/defn
 read-ListFontsWithInfoReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [name-len val__1137__auto__]
  (clojure.core/let
   [min-bounds
    (xcljb.gen.xproto-internal/read-Charinfo ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    max-bounds
    (xcljb.gen.xproto-internal/read-Charinfo ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    min-char-or-byte2
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    max-char-or-byte2
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    default-char
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    properties-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    draw-direction
    (.read-type xcljb.gen.xproto-types/BYTE ch)
    min-byte1
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    max-byte1
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    all-chars-exist
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    font-ascent
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    font-descent
    (.read-type xcljb.gen.xproto-types/INT16 ch)
    replies-hint
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    properties
    (clojure.core/doall
     (clojure.core/repeatedly
      properties-len
      (clojure.core/fn
       []
       (xcljb.gen.xproto-internal/read-Fontprop ch))))
    name
    (xcljb.gen-common/read-string ch name-len)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof min-bounds)
       4
       (.sizeof max-bounds)
       4
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/BYTE)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       1
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/INT16)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        properties)
       name-len))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->ListFontsWithInfoReply
    name-len
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
    name))))

(clojure.core/defn
 read-GetFontPathReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [path-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    path
    (clojure.core/doall
     (clojure.core/repeatedly
      path-len
      (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Str ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        path)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetFontPathReply path-len path))))

(clojure.core/defn
 read-GetImageReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [depth val__1137__auto__]
  (clojure.core/let
   [visual
    (.read-type xcljb.gen.xproto-types/VISUALID ch)
    _
    (xcljb.gen-common/read-pad ch 20)
    data
    (clojure.core/doall
     (clojure.core/repeatedly
      (clojure.core/* length 4)
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/BYTE ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/VISUALID)
       20
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/BYTE)
        (clojure.core/* length 4))))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetImageReply depth visual data))))

(clojure.core/defn
 read-ListInstalledColormapsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [cmaps-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    cmaps
    (clojure.core/doall
     (clojure.core/repeatedly
      cmaps-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/COLORMAP ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/COLORMAP)
        cmaps-len)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->ListInstalledColormapsReply
    cmaps-len
    cmaps))))

(clojure.core/defn
 read-AllocColorReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [red
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    green
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    blue
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)
    pixel
    (.read-type xcljb.gen.xproto-types/CARD32 ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       2
       (.sizeof xcljb.gen.xproto-types/CARD32)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->AllocColorReply red green blue pixel))))

(clojure.core/defn
 read-AllocNamedColorReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [pixel
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    exact-red
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    exact-green
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    exact-blue
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    visual-red
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    visual-green
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    visual-blue
    (.read-type xcljb.gen.xproto-types/CARD16 ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->AllocNamedColorReply
    pixel
    exact-red
    exact-green
    exact-blue
    visual-red
    visual-green
    visual-blue))))

(clojure.core/defn
 read-AllocColorCellsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [pixels-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    masks-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 20)
    pixels
    (clojure.core/doall
     (clojure.core/repeatedly
      pixels-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/CARD32 ch))))
    masks
    (clojure.core/doall
     (clojure.core/repeatedly
      masks-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/CARD32 ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       20
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/CARD32)
        pixels-len)
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/CARD32)
        masks-len)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->AllocColorCellsReply
    pixels-len
    masks-len
    pixels
    masks))))

(clojure.core/defn
 read-AllocColorPlanesReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [pixels-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)
    red-mask
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    green-mask
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    blue-mask
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    _
    (xcljb.gen-common/read-pad ch 8)
    pixels
    (clojure.core/doall
     (clojure.core/repeatedly
      pixels-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/CARD32 ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       2
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       8
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/CARD32)
        pixels-len)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->AllocColorPlanesReply
    pixels-len
    red-mask
    green-mask
    blue-mask
    pixels))))

(clojure.core/defn
 read-QueryColorsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [colors-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    colors
    (clojure.core/doall
     (clojure.core/repeatedly
      colors-len
      (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Rgb ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        colors)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryColorsReply colors-len colors))))

(clojure.core/defn
 read-LookupColorReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [exact-red
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    exact-green
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    exact-blue
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    visual-red
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    visual-green
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    visual-blue
    (.read-type xcljb.gen.xproto-types/CARD16 ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->LookupColorReply
    exact-red
    exact-green
    exact-blue
    visual-red
    visual-green
    visual-blue))))

(clojure.core/defn
 read-QueryBestSizeReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [width
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    height
    (.read-type xcljb.gen.xproto-types/CARD16 ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryBestSizeReply width height))))

(clojure.core/defn
 read-QueryExtensionReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [present
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    major-opcode
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    first-event
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    first-error
    (.read-type xcljb.gen.xproto-types/CARD8 ch)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       1
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD8)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->QueryExtensionReply
    present
    major-opcode
    first-event
    first-error))))

(clojure.core/defn
 read-ListExtensionsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [names-len val__1137__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    names
    (clojure.core/doall
     (clojure.core/repeatedly
      names-len
      (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Str ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       24
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        names)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->ListExtensionsReply names-len names))))

(clojure.core/defn
 read-GetKeyboardMappingReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [keysyms-per-keycode val__1137__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    keysyms
    (clojure.core/doall
     (clojure.core/repeatedly
      length
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/KEYSYM ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/BYTE)
       24
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/KEYSYM)
        length)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetKeyboardMappingReply
    keysyms-per-keycode
    keysyms))))

(clojure.core/defn
 read-GetKeyboardControlReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [global-auto-repeat val__1137__auto__]
  (clojure.core/let
   [led-mask
    (.read-type xcljb.gen.xproto-types/CARD32 ch)
    key-click-percent
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    bell-percent
    (.read-type xcljb.gen.xproto-types/CARD8 ch)
    bell-pitch
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    bell-duration
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)
    auto-repeats
    (clojure.core/doall
     (clojure.core/repeatedly
      32
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/CARD8 ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/BYTE)
       (.sizeof xcljb.gen.xproto-types/CARD32)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD8)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       2
       (clojure.core/* (.sizeof xcljb.gen.xproto-types/CARD8) 32)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetKeyboardControlReply
    global-auto-repeat
    led-mask
    key-click-percent
    bell-percent
    bell-pitch
    bell-duration
    auto-repeats))))

(clojure.core/defn
 read-GetPointerControlReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [acceleration-numerator
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    acceleration-denominator
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    threshold
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 18)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       18))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetPointerControlReply
    acceleration-numerator
    acceleration-denominator
    threshold))))

(clojure.core/defn
 read-GetScreenSaverReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [_ val__1137__auto__]
  (clojure.core/let
   [timeout
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    interval
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    prefer-blanking
    (.read-type xcljb.gen.xproto-types/BYTE ch)
    allow-exposures
    (.read-type xcljb.gen.xproto-types/BYTE ch)
    _
    (xcljb.gen-common/read-pad ch 18)]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       1
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       (.sizeof xcljb.gen.xproto-types/BYTE)
       (.sizeof xcljb.gen.xproto-types/BYTE)
       18))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetScreenSaverReply
    timeout
    interval
    prefer-blanking
    allow-exposures))))

(clojure.core/defn
 read-ListHostsReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [mode val__1137__auto__]
  (clojure.core/let
   [hosts-len
    (.read-type xcljb.gen.xproto-types/CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    hosts
    (clojure.core/doall
     (clojure.core/repeatedly
      hosts-len
      (clojure.core/fn [] (xcljb.gen.xproto-internal/read-Host ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/BYTE)
       (.sizeof xcljb.gen.xproto-types/CARD16)
       22
       (clojure.core/reduce
        (clojure.core/fn
         [x__927__auto__ y__928__auto__]
         (clojure.core/+ x__927__auto__ (.sizeof y__928__auto__)))
        0
        hosts)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->ListHostsReply mode hosts-len hosts))))

(clojure.core/defn
 read-SetPointerMappingReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [status val__1137__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+ (.sizeof xcljb.gen.xproto-types/BYTE)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->SetPointerMappingReply status))))

(clojure.core/defn
 read-GetPointerMappingReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [map-len val__1137__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    map
    (clojure.core/doall
     (clojure.core/repeatedly
      map-len
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/CARD8 ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       24
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/CARD8)
        map-len)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetPointerMappingReply map-len map))))

(clojure.core/defn
 read-SetModifierMappingReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [status val__1137__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+ (.sizeof xcljb.gen.xproto-types/BYTE)))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->SetModifierMappingReply status))))

(clojure.core/defn
 read-GetModifierMappingReply
 [ch length val__1137__auto__]
 (clojure.core/let
  [keycodes-per-modifier val__1137__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    keycodes
    (clojure.core/doall
     (clojure.core/repeatedly
      (clojure.core/* keycodes-per-modifier 8)
      (clojure.core/fn
       []
       (.read-type xcljb.gen.xproto-types/KEYCODE ch))))]
   (clojure.core/let
    [size__1135__auto__
     (clojure.core/+
      7
      (clojure.core/+
       (.sizeof xcljb.gen.xproto-types/CARD8)
       24
       (clojure.core/*
        (.sizeof xcljb.gen.xproto-types/KEYCODE)
        (clojure.core/* keycodes-per-modifier 8))))
     pads__1136__auto__
     (clojure.core/max
      (clojure.core/- 32 size__1135__auto__)
      (xcljb.gen-common/padding size__1135__auto__))]
    (xcljb.gen-common/read-pad ch pads__1136__auto__))
   (xcljb.gen.xproto-types/->GetModifierMappingReply
    keycodes-per-modifier
    keycodes))))

(clojure.core/defn
 read-KeyPressEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/KEYCODE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/KEYCODE)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->KeyPressEvent
    detail
    time
    root
    event
    child
    root-x
    root-y
    event-x
    event-y
    state
    same-screen)}))

(clojure.core/defn
 read-KeyReleaseEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/KEYCODE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/KEYCODE)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->KeyReleaseEvent
    detail
    time
    root
    event
    child
    root-x
    root-y
    event-x
    event-y
    state
    same-screen)}))

(clojure.core/defn
 read-ButtonPressEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BUTTON ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BUTTON)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ButtonPressEvent
    detail
    time
    root
    event
    child
    root-x
    root-y
    event-x
    event-y
    state
    same-screen)}))

(clojure.core/defn
 read-ButtonReleaseEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BUTTON ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BUTTON)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ButtonReleaseEvent
    detail
    time
    root
    event
    child
    root-x
    root-y
    event-x
    event-y
    state
    same-screen)}))

(clojure.core/defn
 read-MotionNotifyEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->MotionNotifyEvent
    detail
    time
    root
    event
    child
    root-x
    root-y
    event-x
    event-y
    state
    same-screen)}))

(clojure.core/defn
 read-EnterNotifyEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   mode
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   same-screen-focus
   (.read-type xcljb.gen.xproto-types/BYTE ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/BYTE))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->EnterNotifyEvent
    detail
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
    same-screen-focus)}))

(clojure.core/defn
 read-LeaveNotifyEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   root
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   child
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   root-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   root-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   event-y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   state
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   mode
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   same-screen-focus
   (.read-type xcljb.gen.xproto-types/BYTE ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/BYTE))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->LeaveNotifyEvent
    detail
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
    same-screen-focus)}))

(clojure.core/defn
 read-FocusInEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   mode
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event (xcljb.gen.xproto-types/->FocusInEvent detail event mode)}))

(clojure.core/defn
 read-FocusOutEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   mode
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event (xcljb.gen.xproto-types/->FocusOutEvent detail event mode)}))

(clojure.core/defn
 read-KeymapNotifyEvent
 [ch]
 (clojure.core/let
  [keys
   (clojure.core/doall
    (clojure.core/repeatedly
     31
     (clojure.core/fn
      []
      (.read-type xcljb.gen.xproto-types/CARD8 ch))))]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     (clojure.core/* (.sizeof xcljb.gen.xproto-types/CARD8) 31))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num nil,
   :event (xcljb.gen.xproto-types/->KeymapNotifyEvent keys)}))

(clojure.core/defn
 read-ExposeEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   x
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   y
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   count
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 2)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     2)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ExposeEvent
    window
    x
    y
    width
    height
    count)}))

(clojure.core/defn
 read-GraphicsExposureEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   drawable
   (.read-type xcljb.gen.xproto-types/DRAWABLE ch)
   x
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   y
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   count
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/DRAWABLE)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->GraphicsExposureEvent
    drawable
    x
    y
    width
    height
    minor-opcode
    count
    major-opcode)}))

(clojure.core/defn
 read-NoExposureEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   drawable
   (.read-type xcljb.gen.xproto-types/DRAWABLE ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/DRAWABLE)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->NoExposureEvent
    drawable
    minor-opcode
    major-opcode)}))

(clojure.core/defn
 read-VisibilityNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   state
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->VisibilityNotifyEvent window state)}))

(clojure.core/defn
 read-CreateNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   parent
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   border-width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->CreateNotifyEvent
    parent
    window
    x
    y
    width
    height
    border-width
    override-redirect)}))

(clojure.core/defn
 read-DestroyNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event (xcljb.gen.xproto-types/->DestroyNotifyEvent event window)}))

(clojure.core/defn
 read-UnmapNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   from-configure
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     1
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->UnmapNotifyEvent
    event
    window
    from-configure)}))

(clojure.core/defn
 read-MapNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     1
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->MapNotifyEvent
    event
    window
    override-redirect)}))

(clojure.core/defn
 read-MapRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   parent
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event (xcljb.gen.xproto-types/->MapRequestEvent parent window)}))

(clojure.core/defn
 read-ReparentNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   parent
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     1
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ReparentNotifyEvent
    event
    window
    parent
    x
    y
    override-redirect)}))

(clojure.core/defn
 read-ConfigureNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   above-sibling
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   border-width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ConfigureNotifyEvent
    event
    window
    above-sibling
    x
    y
    width
    height
    border-width
    override-redirect)}))

(clojure.core/defn
 read-ConfigureRequestEvent
 [ch]
 (clojure.core/let
  [stack-mode
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   parent
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   sibling
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   border-width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   value-mask
   (.read-type xcljb.gen.xproto-types/CARD16 ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ConfigureRequestEvent
    stack-mode
    parent
    window
    sibling
    x
    y
    width
    height
    border-width
    value-mask)}))

(clojure.core/defn
 read-GravityNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   x
   (.read-type xcljb.gen.xproto-types/INT16 ch)
   y
   (.read-type xcljb.gen.xproto-types/INT16 ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/INT16)
     (.sizeof xcljb.gen.xproto-types/INT16))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->GravityNotifyEvent event window x y)}))

(clojure.core/defn
 read-ResizeRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   width
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   height
   (.read-type xcljb.gen.xproto-types/CARD16 ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD16))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ResizeRequestEvent window width height)}))

(clojure.core/defn
 read-CirculateNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   place
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     4
     (.sizeof xcljb.gen.xproto-types/BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->CirculateNotifyEvent event window place)}))

(clojure.core/defn
 read-CirculateRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   place
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     4
     (.sizeof xcljb.gen.xproto-types/BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->CirculateRequestEvent
    event
    window
    place)}))

(clojure.core/defn
 read-PropertyNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   atom
   (.read-type xcljb.gen.xproto-types/ATOM ch)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   state
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/ATOM)
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->PropertyNotifyEvent
    window
    atom
    time
    state)}))

(clojure.core/defn
 read-SelectionClearEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   owner
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   selection
   (.read-type xcljb.gen.xproto-types/ATOM ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/ATOM))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->SelectionClearEvent
    time
    owner
    selection)}))

(clojure.core/defn
 read-SelectionRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   owner
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   requestor
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   selection
   (.read-type xcljb.gen.xproto-types/ATOM ch)
   target
   (.read-type xcljb.gen.xproto-types/ATOM ch)
   property
   (.read-type xcljb.gen.xproto-types/ATOM ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/ATOM)
     (.sizeof xcljb.gen.xproto-types/ATOM)
     (.sizeof xcljb.gen.xproto-types/ATOM))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->SelectionRequestEvent
    time
    owner
    requestor
    selection
    target
    property)}))

(clojure.core/defn
 read-SelectionNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type xcljb.gen.xproto-types/TIMESTAMP ch)
   requestor
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   selection
   (.read-type xcljb.gen.xproto-types/ATOM ch)
   target
   (.read-type xcljb.gen.xproto-types/ATOM ch)
   property
   (.read-type xcljb.gen.xproto-types/ATOM ch)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/TIMESTAMP)
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/ATOM)
     (.sizeof xcljb.gen.xproto-types/ATOM)
     (.sizeof xcljb.gen.xproto-types/ATOM))]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->SelectionNotifyEvent
    time
    requestor
    selection
    target
    property)}))

(clojure.core/defn
 read-ColormapNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type xcljb.gen.xproto-types/WINDOW ch)
   colormap
   (.read-type xcljb.gen.xproto-types/COLORMAP ch)
   new
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   state
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 2)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/WINDOW)
     (.sizeof xcljb.gen.xproto-types/COLORMAP)
     1
     (.sizeof xcljb.gen.xproto-types/BYTE)
     2)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->ColormapNotifyEvent
    window
    colormap
    new
    state)}))

(clojure.core/defn
 read-MappingNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   request
   (.read-type xcljb.gen.xproto-types/BYTE ch)
   first-keycode
   (.read-type xcljb.gen.xproto-types/KEYCODE ch)
   count
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1169__auto__
    (clojure.core/+
     2
     1
     (.sizeof xcljb.gen.xproto-types/BYTE)
     (.sizeof xcljb.gen.xproto-types/KEYCODE)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1169__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1169__auto__))))
  {:seq-num seq-num,
   :event
   (xcljb.gen.xproto-types/->MappingNotifyEvent
    request
    first-keycode
    count)}))

(clojure.core/defn
 read-RequestError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->RequestError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-ValueError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->ValueError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-WindowError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->WindowError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-PixmapError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->PixmapError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-AtomError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->AtomError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-CursorError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->CursorError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-FontError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->FontError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-MatchError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->MatchError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-DrawableError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->DrawableError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-AccessError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->AccessError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-AllocError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->AllocError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-ColormapError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->ColormapError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-GContextError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->GContextError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-IDChoiceError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->IDChoiceError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-NameError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->NameError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-LengthError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->LengthError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-ImplementationError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type xcljb.gen.xproto-types/CARD32 ch)
   minor-opcode
   (.read-type xcljb.gen.xproto-types/CARD16 ch)
   major-opcode
   (.read-type xcljb.gen.xproto-types/CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1205__auto__
    (clojure.core/+
     (.sizeof xcljb.gen.xproto-types/CARD32)
     (.sizeof xcljb.gen.xproto-types/CARD16)
     (.sizeof xcljb.gen.xproto-types/CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1205__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1205__auto__))))
  (xcljb.gen.xproto-types/->ImplementationError
   bad-value
   minor-opcode
   major-opcode)))

(clojure.core/defn
 read-reply
 [expr__1374__auto__]
 (clojure.core/case
  expr__1374__auto__
  3
  read-GetWindowAttributesReply
  14
  read-GetGeometryReply
  15
  read-QueryTreeReply
  16
  read-InternAtomReply
  17
  read-GetAtomNameReply
  20
  read-GetPropertyReply
  21
  read-ListPropertiesReply
  23
  read-GetSelectionOwnerReply
  26
  read-GrabPointerReply
  31
  read-GrabKeyboardReply
  38
  read-QueryPointerReply
  39
  read-GetMotionEventsReply
  40
  read-TranslateCoordinatesReply
  43
  read-GetInputFocusReply
  44
  read-QueryKeymapReply
  47
  read-QueryFontReply
  49
  read-ListFontsReply
  50
  read-ListFontsWithInfoReply
  52
  read-GetFontPathReply
  73
  read-GetImageReply
  83
  read-ListInstalledColormapsReply
  84
  read-AllocColorReply
  85
  read-AllocNamedColorReply
  86
  read-AllocColorCellsReply
  87
  read-AllocColorPlanesReply
  91
  read-QueryColorsReply
  92
  read-LookupColorReply
  97
  read-QueryBestSizeReply
  98
  read-QueryExtensionReply
  99
  read-ListExtensionsReply
  101
  read-GetKeyboardMappingReply
  103
  read-GetKeyboardControlReply
  106
  read-GetPointerControlReply
  108
  read-GetScreenSaverReply
  110
  read-ListHostsReply
  116
  read-SetPointerMappingReply
  117
  read-GetPointerMappingReply
  118
  read-SetModifierMappingReply
  119
  read-GetModifierMappingReply))

(clojure.core/defn
 read-event
 [expr__1374__auto__]
 (clojure.core/case
  expr__1374__auto__
  2
  read-KeyPressEvent
  3
  read-KeyReleaseEvent
  4
  read-ButtonPressEvent
  5
  read-ButtonReleaseEvent
  6
  read-MotionNotifyEvent
  7
  read-EnterNotifyEvent
  8
  read-LeaveNotifyEvent
  9
  read-FocusInEvent
  10
  read-FocusOutEvent
  11
  read-KeymapNotifyEvent
  12
  read-ExposeEvent
  13
  read-GraphicsExposureEvent
  14
  read-NoExposureEvent
  15
  read-VisibilityNotifyEvent
  16
  read-CreateNotifyEvent
  17
  read-DestroyNotifyEvent
  18
  read-UnmapNotifyEvent
  19
  read-MapNotifyEvent
  20
  read-MapRequestEvent
  21
  read-ReparentNotifyEvent
  22
  read-ConfigureNotifyEvent
  23
  read-ConfigureRequestEvent
  24
  read-GravityNotifyEvent
  25
  read-ResizeRequestEvent
  26
  read-CirculateNotifyEvent
  27
  read-CirculateRequestEvent
  28
  read-PropertyNotifyEvent
  29
  read-SelectionClearEvent
  30
  read-SelectionRequestEvent
  31
  read-SelectionNotifyEvent
  32
  read-ColormapNotifyEvent
  34
  read-MappingNotifyEvent))

(clojure.core/defn
 read-error
 [expr__1374__auto__]
 (clojure.core/case
  expr__1374__auto__
  1
  read-RequestError
  2
  read-ValueError
  3
  read-WindowError
  4
  read-PixmapError
  5
  read-AtomError
  6
  read-CursorError
  7
  read-FontError
  8
  read-MatchError
  9
  read-DrawableError
  10
  read-AccessError
  11
  read-AllocError
  12
  read-ColormapError
  13
  read-GContextError
  14
  read-IDChoiceError
  15
  read-NameError
  16
  read-LengthError
  17
  read-ImplementationError))
