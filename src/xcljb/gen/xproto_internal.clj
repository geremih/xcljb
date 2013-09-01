(clojure.core/ns
 xcljb.gen.xproto-internal
 (:use [xcljb.gen xproto-types])
 (:require [xcljb gen-common]))

(clojure.core/defn
 read-CHAR2B
 [ch]
 (clojure.core/let
  [byte1 (.read-type CARD8 ch) byte2 (.read-type CARD8 ch)]
  (->CHAR2B byte1 byte2)))

(clojure.core/defn
 read-POINT
 [ch]
 (clojure.core/let
  [x (.read-type INT16 ch) y (.read-type INT16 ch)]
  (->POINT x y)))

(clojure.core/defn
 read-RECTANGLE
 [ch]
 (clojure.core/let
  [x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)]
  (->RECTANGLE x y width height)))

(clojure.core/defn
 read-ARC
 [ch]
 (clojure.core/let
  [x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)
   angle1
   (.read-type INT16 ch)
   angle2
   (.read-type INT16 ch)]
  (->ARC x y width height angle1 angle2)))

(clojure.core/defn
 read-FORMAT
 [ch]
 (clojure.core/let
  [depth
   (.read-type CARD8 ch)
   bits-per-pixel
   (.read-type CARD8 ch)
   scanline-pad
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 5)]
  (->FORMAT depth bits-per-pixel scanline-pad)))

(clojure.core/defn
 read-VISUALTYPE
 [ch]
 (clojure.core/let
  [visual-id
   (.read-type VISUALID ch)
   class
   (.read-type CARD8 ch)
   bits-per-rgb-value
   (.read-type CARD8 ch)
   colormap-entries
   (.read-type CARD16 ch)
   red-mask
   (.read-type CARD32 ch)
   green-mask
   (.read-type CARD32 ch)
   blue-mask
   (.read-type CARD32 ch)
   _
   (xcljb.gen-common/read-pad ch 4)]
  (->VISUALTYPE
   visual-id
   class
   bits-per-rgb-value
   colormap-entries
   red-mask
   green-mask
   blue-mask)))

(clojure.core/defn
 read-DEPTH
 [ch]
 (clojure.core/let
  [depth
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   visuals-len
   (.read-type CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   visuals
   (clojure.core/doall
    (clojure.core/repeatedly
     visuals-len
     (clojure.core/fn [] (read-VISUALTYPE ch))))]
  (->DEPTH depth visuals-len visuals)))

(clojure.core/defn
 read-SCREEN
 [ch]
 (clojure.core/let
  [root
   (.read-type WINDOW ch)
   default-colormap
   (.read-type COLORMAP ch)
   white-pixel
   (.read-type CARD32 ch)
   black-pixel
   (.read-type CARD32 ch)
   current-input-masks
   (.read-type CARD32 ch)
   width-in-pixels
   (.read-type CARD16 ch)
   height-in-pixels
   (.read-type CARD16 ch)
   width-in-millimeters
   (.read-type CARD16 ch)
   height-in-millimeters
   (.read-type CARD16 ch)
   min-installed-maps
   (.read-type CARD16 ch)
   max-installed-maps
   (.read-type CARD16 ch)
   root-visual
   (.read-type VISUALID ch)
   backing-stores
   (.read-type BYTE ch)
   save-unders
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   root-depth
   (.read-type CARD8 ch)
   allowed-depths-len
   (.read-type CARD8 ch)
   allowed-depths
   (clojure.core/doall
    (clojure.core/repeatedly
     allowed-depths-len
     (clojure.core/fn [] (read-DEPTH ch))))]
  (->SCREEN
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
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   protocol-major-version
   (.read-type CARD16 ch)
   protocol-minor-version
   (.read-type CARD16 ch)
   authorization-protocol-name-len
   (.read-type CARD16 ch)
   authorization-protocol-data-len
   (.read-type CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 2)
   authorization-protocol-name
   (xcljb.gen-common/read-string ch authorization-protocol-name-len)
   authorization-protocol-data
   (xcljb.gen-common/read-string ch authorization-protocol-data-len)]
  (->SetupRequest
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
   (.read-type CARD8 ch)
   reason-len
   (.read-type CARD8 ch)
   protocol-major-version
   (.read-type CARD16 ch)
   protocol-minor-version
   (.read-type CARD16 ch)
   length
   (.read-type CARD16 ch)
   reason
   (xcljb.gen-common/read-string ch reason-len)]
  (->SetupFailed
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
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 5)
   length
   (.read-type CARD16 ch)
   reason
   (xcljb.gen-common/read-string ch (clojure.core/* length 4))]
  (->SetupAuthenticate status length reason)))

(clojure.core/defn
 read-Setup
 [ch]
 (clojure.core/let
  [status
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   protocol-major-version
   (.read-type CARD16 ch)
   protocol-minor-version
   (.read-type CARD16 ch)
   length
   (.read-type CARD16 ch)
   release-number
   (.read-type CARD32 ch)
   resource-id-base
   (.read-type CARD32 ch)
   resource-id-mask
   (.read-type CARD32 ch)
   motion-buffer-size
   (.read-type CARD32 ch)
   vendor-len
   (.read-type CARD16 ch)
   maximum-request-length
   (.read-type CARD16 ch)
   roots-len
   (.read-type CARD8 ch)
   pixmap-formats-len
   (.read-type CARD8 ch)
   image-byte-order
   (.read-type CARD8 ch)
   bitmap-format-bit-order
   (.read-type CARD8 ch)
   bitmap-format-scanline-unit
   (.read-type CARD8 ch)
   bitmap-format-scanline-pad
   (.read-type CARD8 ch)
   min-keycode
   (.read-type KEYCODE ch)
   max-keycode
   (.read-type KEYCODE ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   vendor
   (xcljb.gen-common/read-string ch vendor-len)
   pixmap-formats
   (clojure.core/doall
    (clojure.core/repeatedly
     pixmap-formats-len
     (clojure.core/fn [] (read-FORMAT ch))))
   roots
   (clojure.core/doall
    (clojure.core/repeatedly
     roots-len
     (clojure.core/fn [] (read-SCREEN ch))))]
  (->Setup
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
 read-TIMECOORD
 [ch]
 (clojure.core/let
  [time
   (.read-type TIMESTAMP ch)
   x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)]
  (->TIMECOORD time x y)))

(clojure.core/defn
 read-FONTPROP
 [ch]
 (clojure.core/let
  [name (.read-type ATOM ch) value (.read-type CARD32 ch)]
  (->FONTPROP name value)))

(clojure.core/defn
 read-CHARINFO
 [ch]
 (clojure.core/let
  [left-side-bearing
   (.read-type INT16 ch)
   right-side-bearing
   (.read-type INT16 ch)
   character-width
   (.read-type INT16 ch)
   ascent
   (.read-type INT16 ch)
   descent
   (.read-type INT16 ch)
   attributes
   (.read-type CARD16 ch)]
  (->CHARINFO
   left-side-bearing
   right-side-bearing
   character-width
   ascent
   descent
   attributes)))

(clojure.core/defn
 read-STR
 [ch]
 (clojure.core/let
  [name-len
   (.read-type CARD8 ch)
   name
   (xcljb.gen-common/read-string ch name-len)]
  (->STR name-len name)))

(clojure.core/defn
 read-SEGMENT
 [ch]
 (clojure.core/let
  [x1
   (.read-type INT16 ch)
   y1
   (.read-type INT16 ch)
   x2
   (.read-type INT16 ch)
   y2
   (.read-type INT16 ch)]
  (->SEGMENT x1 y1 x2 y2)))

(clojure.core/defn
 read-COLORITEM
 [ch]
 (clojure.core/let
  [pixel
   (.read-type CARD32 ch)
   red
   (.read-type CARD16 ch)
   green
   (.read-type CARD16 ch)
   blue
   (.read-type CARD16 ch)
   flags
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (->COLORITEM pixel red green blue flags)))

(clojure.core/defn
 read-RGB
 [ch]
 (clojure.core/let
  [red
   (.read-type CARD16 ch)
   green
   (.read-type CARD16 ch)
   blue
   (.read-type CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 2)]
  (->RGB red green blue)))

(clojure.core/defn
 read-HOST
 [ch]
 (clojure.core/let
  [family
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)
   address-len
   (.read-type CARD16 ch)
   address
   (clojure.core/doall
    (clojure.core/repeatedly
     address-len
     (clojure.core/fn [] (.read-type BYTE ch))))]
  (->HOST family address-len address)))

(clojure.core/defn
 read-GetWindowAttributesReply
 [ch len__1433 val__1104__auto__]
 (clojure.core/let
  [backing-store val__1104__auto__]
  (clojure.core/let
   [visual
    (.read-type VISUALID ch)
    class
    (.read-type CARD16 ch)
    bit-gravity
    (.read-type CARD8 ch)
    win-gravity
    (.read-type CARD8 ch)
    backing-planes
    (.read-type CARD32 ch)
    backing-pixel
    (.read-type CARD32 ch)
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
    (.read-type CARD8 ch)
    override-redirect
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    colormap
    (.read-type COLORMAP ch)
    all-event-masks
    (.read-type CARD32 ch)
    your-event-mask
    (.read-type CARD32 ch)
    do-not-propagate-mask
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      (.sizeof VISUALID)
      (.sizeof CARD16)
      (.sizeof CARD8)
      (.sizeof CARD8)
      (.sizeof CARD32)
      (.sizeof CARD32)
      1
      1
      (.sizeof CARD8)
      1
      (.sizeof COLORMAP)
      (.sizeof CARD32)
      (.sizeof CARD32)
      (.sizeof CARD16)
      2)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1433))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1433)
       size__1103__auto__))))
   (->GetWindowAttributesReply
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
 [ch len__1434 val__1104__auto__]
 (clojure.core/let
  [depth val__1104__auto__]
  (clojure.core/let
   [root
    (.read-type WINDOW ch)
    x
    (.read-type INT16 ch)
    y
    (.read-type INT16 ch)
    width
    (.read-type CARD16 ch)
    height
    (.read-type CARD16 ch)
    border-width
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      (.sizeof WINDOW)
      (.sizeof INT16)
      (.sizeof INT16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      2)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1434))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1434)
       size__1103__auto__))))
   (->GetGeometryReply depth root x y width height border-width))))

(clojure.core/defn
 read-QueryTreeReply
 [ch len__1435 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [root
    (.read-type WINDOW ch)
    parent
    (.read-type WINDOW ch)
    children-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 14)
    children
    (clojure.core/doall
     (clojure.core/repeatedly
      children-len
      (clojure.core/fn [] (.read-type WINDOW ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof WINDOW)
      (.sizeof WINDOW)
      (.sizeof CARD16)
      14
      (clojure.core/* (.sizeof WINDOW) children-len))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1435))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1435)
       size__1103__auto__))))
   (->QueryTreeReply root parent children-len children))))

(clojure.core/defn
 read-InternAtomReply
 [ch len__1436 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [atom (.read-type ATOM ch)]
   (clojure.core/let
    [size__1103__auto__ (clojure.core/+ 1 (.sizeof ATOM))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1436))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1436)
       size__1103__auto__))))
   (->InternAtomReply atom))))

(clojure.core/defn
 read-GetAtomNameReply
 [ch len__1437 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [name-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    name
    (xcljb.gen-common/read-string ch name-len)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+ 1 (.sizeof CARD16) 22 name-len)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1437))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1437)
       size__1103__auto__))))
   (->GetAtomNameReply name-len name))))

(clojure.core/defn
 read-GetPropertyReply
 [ch len__1438 val__1104__auto__]
 (clojure.core/let
  [format val__1104__auto__]
  (clojure.core/let
   [type
    (.read-type ATOM ch)
    bytes-after
    (.read-type CARD32 ch)
    value-len
    (.read-type CARD32 ch)
    _
    (xcljb.gen-common/read-pad ch 12)
    value
    (clojure.core/doall
     (clojure.core/repeatedly
      (clojure.core/* value-len (clojure.core// format 8))
      (clojure.core/fn [] (.read-type void ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      (.sizeof ATOM)
      (.sizeof CARD32)
      (.sizeof CARD32)
      12
      (clojure.core/*
       (.sizeof void)
       (clojure.core/* value-len (clojure.core// format 8))))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1438))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1438)
       size__1103__auto__))))
   (->GetPropertyReply format type bytes-after value-len value))))

(clojure.core/defn
 read-ListPropertiesReply
 [ch len__1439 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [atoms-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    atoms
    (clojure.core/doall
     (clojure.core/repeatedly
      atoms-len
      (clojure.core/fn [] (.read-type ATOM ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      22
      (clojure.core/* (.sizeof ATOM) atoms-len))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1439))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1439)
       size__1103__auto__))))
   (->ListPropertiesReply atoms-len atoms))))

(clojure.core/defn
 read-GetSelectionOwnerReply
 [ch len__1440 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [owner (.read-type WINDOW ch)]
   (clojure.core/let
    [size__1103__auto__ (clojure.core/+ 1 (.sizeof WINDOW))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1440))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1440)
       size__1103__auto__))))
   (->GetSelectionOwnerReply owner))))

(clojure.core/defn
 read-GrabPointerReply
 [ch len__1441 val__1104__auto__]
 (clojure.core/let
  [status val__1104__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1103__auto__ (clojure.core/+ (.sizeof BYTE))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1441))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1441)
       size__1103__auto__))))
   (->GrabPointerReply status))))

(clojure.core/defn
 read-GrabKeyboardReply
 [ch len__1442 val__1104__auto__]
 (clojure.core/let
  [status val__1104__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1103__auto__ (clojure.core/+ (.sizeof BYTE))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1442))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1442)
       size__1103__auto__))))
   (->GrabKeyboardReply status))))

(clojure.core/defn
 read-QueryPointerReply
 [ch len__1443 val__1104__auto__]
 (clojure.core/let
  [same-screen val__1104__auto__]
  (clojure.core/let
   [root
    (.read-type WINDOW ch)
    child
    (.read-type WINDOW ch)
    root-x
    (.read-type INT16 ch)
    root-y
    (.read-type INT16 ch)
    win-x
    (.read-type INT16 ch)
    win-y
    (.read-type INT16 ch)
    mask
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof WINDOW)
      (.sizeof WINDOW)
      (.sizeof INT16)
      (.sizeof INT16)
      (.sizeof INT16)
      (.sizeof INT16)
      (.sizeof CARD16)
      2)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1443))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1443)
       size__1103__auto__))))
   (->QueryPointerReply
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
 [ch len__1444 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [events-len
    (.read-type CARD32 ch)
    _
    (xcljb.gen-common/read-pad ch 20)
    events
    (clojure.core/doall
     (clojure.core/repeatedly
      events-len
      (clojure.core/fn [] (read-TIMECOORD ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD32)
      20
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       events))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1444))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1444)
       size__1103__auto__))))
   (->GetMotionEventsReply events-len events))))

(clojure.core/defn
 read-TranslateCoordinatesReply
 [ch len__1445 val__1104__auto__]
 (clojure.core/let
  [same-screen val__1104__auto__]
  (clojure.core/let
   [child
    (.read-type WINDOW ch)
    dst-x
    (.read-type INT16 ch)
    dst-y
    (.read-type INT16 ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof WINDOW)
      (.sizeof INT16)
      (.sizeof INT16))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1445))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1445)
       size__1103__auto__))))
   (->TranslateCoordinatesReply same-screen child dst-x dst-y))))

(clojure.core/defn
 read-GetInputFocusReply
 [ch len__1446 val__1104__auto__]
 (clojure.core/let
  [revert-to val__1104__auto__]
  (clojure.core/let
   [focus (.read-type WINDOW ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+ (.sizeof CARD8) (.sizeof WINDOW))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1446))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1446)
       size__1103__auto__))))
   (->GetInputFocusReply revert-to focus))))

(clojure.core/defn
 read-QueryKeymapReply
 [ch len__1447 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [keys
    (clojure.core/doall
     (clojure.core/repeatedly
      32
      (clojure.core/fn [] (.read-type CARD8 ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+ 1 (clojure.core/* (.sizeof CARD8) 32))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1447))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1447)
       size__1103__auto__))))
   (->QueryKeymapReply keys))))

(clojure.core/defn
 read-QueryFontReply
 [ch len__1448 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [min-bounds
    (read-CHARINFO ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    max-bounds
    (read-CHARINFO ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    min-char-or-byte2
    (.read-type CARD16 ch)
    max-char-or-byte2
    (.read-type CARD16 ch)
    default-char
    (.read-type CARD16 ch)
    properties-len
    (.read-type CARD16 ch)
    draw-direction
    (.read-type BYTE ch)
    min-byte1
    (.read-type CARD8 ch)
    max-byte1
    (.read-type CARD8 ch)
    all-chars-exist
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    font-ascent
    (.read-type INT16 ch)
    font-descent
    (.read-type INT16 ch)
    char-infos-len
    (.read-type CARD32 ch)
    properties
    (clojure.core/doall
     (clojure.core/repeatedly
      properties-len
      (clojure.core/fn [] (read-FONTPROP ch))))
    char-infos
    (clojure.core/doall
     (clojure.core/repeatedly
      char-infos-len
      (clojure.core/fn [] (read-CHARINFO ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof min-bounds)
      4
      (.sizeof max-bounds)
      4
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof BYTE)
      (.sizeof CARD8)
      (.sizeof CARD8)
      1
      (.sizeof INT16)
      (.sizeof INT16)
      (.sizeof CARD32)
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       properties)
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       char-infos))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1448))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1448)
       size__1103__auto__))))
   (->QueryFontReply
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
 [ch len__1449 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [names-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    names
    (clojure.core/doall
     (clojure.core/repeatedly
      names-len
      (clojure.core/fn [] (read-STR ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      22
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       names))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1449))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1449)
       size__1103__auto__))))
   (->ListFontsReply names-len names))))

(clojure.core/defn
 read-ListFontsWithInfoReply
 [ch len__1450 val__1104__auto__]
 (clojure.core/let
  [name-len val__1104__auto__]
  (clojure.core/let
   [min-bounds
    (read-CHARINFO ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    max-bounds
    (read-CHARINFO ch)
    _
    (xcljb.gen-common/read-pad ch 4)
    min-char-or-byte2
    (.read-type CARD16 ch)
    max-char-or-byte2
    (.read-type CARD16 ch)
    default-char
    (.read-type CARD16 ch)
    properties-len
    (.read-type CARD16 ch)
    draw-direction
    (.read-type BYTE ch)
    min-byte1
    (.read-type CARD8 ch)
    max-byte1
    (.read-type CARD8 ch)
    all-chars-exist
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    font-ascent
    (.read-type INT16 ch)
    font-descent
    (.read-type INT16 ch)
    replies-hint
    (.read-type CARD32 ch)
    properties
    (clojure.core/doall
     (clojure.core/repeatedly
      properties-len
      (clojure.core/fn [] (read-FONTPROP ch))))
    name
    (xcljb.gen-common/read-string ch name-len)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      (.sizeof min-bounds)
      4
      (.sizeof max-bounds)
      4
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof BYTE)
      (.sizeof CARD8)
      (.sizeof CARD8)
      1
      (.sizeof INT16)
      (.sizeof INT16)
      (.sizeof CARD32)
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       properties)
      name-len)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1450))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1450)
       size__1103__auto__))))
   (->ListFontsWithInfoReply
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
 [ch len__1451 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [path-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    path
    (clojure.core/doall
     (clojure.core/repeatedly
      path-len
      (clojure.core/fn [] (read-STR ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      22
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       path))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1451))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1451)
       size__1103__auto__))))
   (->GetFontPathReply path-len path))))

(clojure.core/defn
 read-ListInstalledColormapsReply
 [ch len__1452 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [cmaps-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    cmaps
    (clojure.core/doall
     (clojure.core/repeatedly
      cmaps-len
      (clojure.core/fn [] (.read-type COLORMAP ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      22
      (clojure.core/* (.sizeof COLORMAP) cmaps-len))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1452))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1452)
       size__1103__auto__))))
   (->ListInstalledColormapsReply cmaps-len cmaps))))

(clojure.core/defn
 read-AllocColorReply
 [ch len__1453 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [red
    (.read-type CARD16 ch)
    green
    (.read-type CARD16 ch)
    blue
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)
    pixel
    (.read-type CARD32 ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      2
      (.sizeof CARD32))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1453))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1453)
       size__1103__auto__))))
   (->AllocColorReply red green blue pixel))))

(clojure.core/defn
 read-AllocNamedColorReply
 [ch len__1454 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [pixel
    (.read-type CARD32 ch)
    exact-red
    (.read-type CARD16 ch)
    exact-green
    (.read-type CARD16 ch)
    exact-blue
    (.read-type CARD16 ch)
    visual-red
    (.read-type CARD16 ch)
    visual-green
    (.read-type CARD16 ch)
    visual-blue
    (.read-type CARD16 ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD32)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1454))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1454)
       size__1103__auto__))))
   (->AllocNamedColorReply
    pixel
    exact-red
    exact-green
    exact-blue
    visual-red
    visual-green
    visual-blue))))

(clojure.core/defn
 read-AllocColorCellsReply
 [ch len__1455 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [pixels-len
    (.read-type CARD16 ch)
    masks-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 20)
    pixels
    (clojure.core/doall
     (clojure.core/repeatedly
      pixels-len
      (clojure.core/fn [] (.read-type CARD32 ch))))
    masks
    (clojure.core/doall
     (clojure.core/repeatedly
      masks-len
      (clojure.core/fn [] (.read-type CARD32 ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      (.sizeof CARD16)
      20
      (clojure.core/* (.sizeof CARD32) pixels-len)
      (clojure.core/* (.sizeof CARD32) masks-len))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1455))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1455)
       size__1103__auto__))))
   (->AllocColorCellsReply pixels-len masks-len pixels masks))))

(clojure.core/defn
 read-AllocColorPlanesReply
 [ch len__1456 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [pixels-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)
    red-mask
    (.read-type CARD32 ch)
    green-mask
    (.read-type CARD32 ch)
    blue-mask
    (.read-type CARD32 ch)
    _
    (xcljb.gen-common/read-pad ch 8)
    pixels
    (clojure.core/doall
     (clojure.core/repeatedly
      pixels-len
      (clojure.core/fn [] (.read-type CARD32 ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      2
      (.sizeof CARD32)
      (.sizeof CARD32)
      (.sizeof CARD32)
      8
      (clojure.core/* (.sizeof CARD32) pixels-len))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1456))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1456)
       size__1103__auto__))))
   (->AllocColorPlanesReply
    pixels-len
    red-mask
    green-mask
    blue-mask
    pixels))))

(clojure.core/defn
 read-QueryColorsReply
 [ch len__1457 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [colors-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    colors
    (clojure.core/doall
     (clojure.core/repeatedly
      colors-len
      (clojure.core/fn [] (read-RGB ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      22
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       colors))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1457))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1457)
       size__1103__auto__))))
   (->QueryColorsReply colors-len colors))))

(clojure.core/defn
 read-LookupColorReply
 [ch len__1458 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [exact-red
    (.read-type CARD16 ch)
    exact-green
    (.read-type CARD16 ch)
    exact-blue
    (.read-type CARD16 ch)
    visual-red
    (.read-type CARD16 ch)
    visual-green
    (.read-type CARD16 ch)
    visual-blue
    (.read-type CARD16 ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1458))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1458)
       size__1103__auto__))))
   (->LookupColorReply
    exact-red
    exact-green
    exact-blue
    visual-red
    visual-green
    visual-blue))))

(clojure.core/defn
 read-QueryBestSizeReply
 [ch len__1459 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [width (.read-type CARD16 ch) height (.read-type CARD16 ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+ 1 (.sizeof CARD16) (.sizeof CARD16))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1459))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1459)
       size__1103__auto__))))
   (->QueryBestSizeReply width height))))

(clojure.core/defn
 read-QueryExtensionReply
 [ch len__1460 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [present
    (if
     (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
     true
     false)
    major-opcode
    (.read-type CARD8 ch)
    first-event
    (.read-type CARD8 ch)
    first-error
    (.read-type CARD8 ch)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      1
      (.sizeof CARD8)
      (.sizeof CARD8)
      (.sizeof CARD8))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1460))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1460)
       size__1103__auto__))))
   (->QueryExtensionReply
    present
    major-opcode
    first-event
    first-error))))

(clojure.core/defn
 read-ListExtensionsReply
 [ch len__1461 val__1104__auto__]
 (clojure.core/let
  [names-len val__1104__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    names
    (clojure.core/doall
     (clojure.core/repeatedly
      names-len
      (clojure.core/fn [] (read-STR ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      24
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       names))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1461))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1461)
       size__1103__auto__))))
   (->ListExtensionsReply names-len names))))

(clojure.core/defn
 read-GetKeyboardControlReply
 [ch len__1462 val__1104__auto__]
 (clojure.core/let
  [global-auto-repeat val__1104__auto__]
  (clojure.core/let
   [led-mask
    (.read-type CARD32 ch)
    key-click-percent
    (.read-type CARD8 ch)
    bell-percent
    (.read-type CARD8 ch)
    bell-pitch
    (.read-type CARD16 ch)
    bell-duration
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 2)
    auto-repeats
    (clojure.core/doall
     (clojure.core/repeatedly
      32
      (clojure.core/fn [] (.read-type CARD8 ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof BYTE)
      (.sizeof CARD32)
      (.sizeof CARD8)
      (.sizeof CARD8)
      (.sizeof CARD16)
      (.sizeof CARD16)
      2
      (clojure.core/* (.sizeof CARD8) 32))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1462))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1462)
       size__1103__auto__))))
   (->GetKeyboardControlReply
    global-auto-repeat
    led-mask
    key-click-percent
    bell-percent
    bell-pitch
    bell-duration
    auto-repeats))))

(clojure.core/defn
 read-GetPointerControlReply
 [ch len__1463 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [acceleration-numerator
    (.read-type CARD16 ch)
    acceleration-denominator
    (.read-type CARD16 ch)
    threshold
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 18)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof CARD16)
      18)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1463))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1463)
       size__1103__auto__))))
   (->GetPointerControlReply
    acceleration-numerator
    acceleration-denominator
    threshold))))

(clojure.core/defn
 read-GetScreenSaverReply
 [ch len__1464 val__1104__auto__]
 (clojure.core/let
  [_ val__1104__auto__]
  (clojure.core/let
   [timeout
    (.read-type CARD16 ch)
    interval
    (.read-type CARD16 ch)
    prefer-blanking
    (.read-type BYTE ch)
    allow-exposures
    (.read-type BYTE ch)
    _
    (xcljb.gen-common/read-pad ch 18)]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      1
      (.sizeof CARD16)
      (.sizeof CARD16)
      (.sizeof BYTE)
      (.sizeof BYTE)
      18)]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1464))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1464)
       size__1103__auto__))))
   (->GetScreenSaverReply
    timeout
    interval
    prefer-blanking
    allow-exposures))))

(clojure.core/defn
 read-ListHostsReply
 [ch len__1465 val__1104__auto__]
 (clojure.core/let
  [mode val__1104__auto__]
  (clojure.core/let
   [hosts-len
    (.read-type CARD16 ch)
    _
    (xcljb.gen-common/read-pad ch 22)
    hosts
    (clojure.core/doall
     (clojure.core/repeatedly
      hosts-len
      (clojure.core/fn [] (read-HOST ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof BYTE)
      (.sizeof CARD16)
      22
      (clojure.core/reduce
       (clojure.core/fn
        [x__903__auto__ y__904__auto__]
        (clojure.core/+ x__903__auto__ (.sizeof y__904__auto__)))
       0
       hosts))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1465))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1465)
       size__1103__auto__))))
   (->ListHostsReply mode hosts-len hosts))))

(clojure.core/defn
 read-SetPointerMappingReply
 [ch len__1466 val__1104__auto__]
 (clojure.core/let
  [status val__1104__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1103__auto__ (clojure.core/+ (.sizeof BYTE))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1466))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1466)
       size__1103__auto__))))
   (->SetPointerMappingReply status))))

(clojure.core/defn
 read-GetPointerMappingReply
 [ch len__1467 val__1104__auto__]
 (clojure.core/let
  [map-len val__1104__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    map
    (clojure.core/doall
     (clojure.core/repeatedly
      map-len
      (clojure.core/fn [] (.read-type CARD8 ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      24
      (clojure.core/* (.sizeof CARD8) map-len))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1467))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1467)
       size__1103__auto__))))
   (->GetPointerMappingReply map-len map))))

(clojure.core/defn
 read-SetModifierMappingReply
 [ch len__1468 val__1104__auto__]
 (clojure.core/let
  [status val__1104__auto__]
  (clojure.core/let
   []
   (clojure.core/let
    [size__1103__auto__ (clojure.core/+ (.sizeof BYTE))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1468))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1468)
       size__1103__auto__))))
   (->SetModifierMappingReply status))))

(clojure.core/defn
 read-GetModifierMappingReply
 [ch len__1469 val__1104__auto__]
 (clojure.core/let
  [keycodes-per-modifier val__1104__auto__]
  (clojure.core/let
   [_
    (xcljb.gen-common/read-pad ch 24)
    keycodes
    (clojure.core/doall
     (clojure.core/repeatedly
      (clojure.core/* keycodes-per-modifier 8)
      (clojure.core/fn [] (.read-type KEYCODE ch))))]
   (clojure.core/let
    [size__1103__auto__
     (clojure.core/+
      (.sizeof CARD8)
      24
      (clojure.core/*
       (.sizeof KEYCODE)
       (clojure.core/* keycodes-per-modifier 8)))]
    (clojure.core/cond
     (clojure.core/< size__1103__auto__ 25)
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/- 25 size__1103__auto__))
     (clojure.core/< size__1103__auto__ (clojure.core/+ 25 len__1469))
     (xcljb.gen-common/read-pad
      ch
      (clojure.core/-
       (clojure.core/+ 25 len__1469)
       size__1103__auto__))))
   (->GetModifierMappingReply keycodes-per-modifier keycodes))))

(clojure.core/defn
 read-KeyPressEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type KEYCODE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof KEYCODE)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->KeyPressEvent
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
   (.read-type KEYCODE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof KEYCODE)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->KeyReleaseEvent
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
   (.read-type BUTTON ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BUTTON)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->ButtonPressEvent
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
   (.read-type BUTTON ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BUTTON)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->ButtonReleaseEvent
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
   (.read-type BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   same-screen
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BYTE)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->MotionNotifyEvent
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
   (.read-type BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   mode
   (.read-type BYTE ch)
   same-screen-focus
   (.read-type BYTE ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BYTE)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     (.sizeof BYTE)
     (.sizeof BYTE))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->EnterNotifyEvent
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
   (.read-type BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   root
   (.read-type WINDOW ch)
   event
   (.read-type WINDOW ch)
   child
   (.read-type WINDOW ch)
   root-x
   (.read-type INT16 ch)
   root-y
   (.read-type INT16 ch)
   event-x
   (.read-type INT16 ch)
   event-y
   (.read-type INT16 ch)
   state
   (.read-type CARD16 ch)
   mode
   (.read-type BYTE ch)
   same-screen-focus
   (.read-type BYTE ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BYTE)
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     (.sizeof BYTE)
     (.sizeof BYTE))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->LeaveNotifyEvent
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
   (.read-type BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   mode
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BYTE)
     (.sizeof WINDOW)
     (.sizeof BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num, :event (->FocusInEvent detail event mode)}))

(clojure.core/defn
 read-FocusOutEvent
 [ch]
 (clojure.core/let
  [detail
   (.read-type BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   mode
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BYTE)
     (.sizeof WINDOW)
     (.sizeof BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num, :event (->FocusOutEvent detail event mode)}))

(clojure.core/defn
 read-KeymapNotifyEvent
 [ch]
 (clojure.core/let
  [keys
   (clojure.core/doall
    (clojure.core/repeatedly
     31
     (clojure.core/fn [] (.read-type CARD8 ch))))]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+ (clojure.core/* (.sizeof CARD8) 31))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num nil, :event (->KeymapNotifyEvent keys)}))

(clojure.core/defn
 read-ExposeEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type WINDOW ch)
   x
   (.read-type CARD16 ch)
   y
   (.read-type CARD16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)
   count
   (.read-type CARD16 ch)
   _
   (xcljb.gen-common/read-pad ch 2)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     2)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->ExposeEvent window x y width height count)}))

(clojure.core/defn
 read-GraphicsExposureEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   drawable
   (.read-type DRAWABLE ch)
   x
   (.read-type CARD16 ch)
   y
   (.read-type CARD16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)
   minor-opcode
   (.read-type CARD16 ch)
   count
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof DRAWABLE)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD8)
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->GraphicsExposureEvent
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
   (.read-type DRAWABLE ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof DRAWABLE)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->NoExposureEvent drawable minor-opcode major-opcode)}))

(clojure.core/defn
 read-VisibilityNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type WINDOW ch)
   state
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+ 2 1 (.sizeof WINDOW) (.sizeof BYTE) 3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num, :event (->VisibilityNotifyEvent window state)}))

(clojure.core/defn
 read-CreateNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   parent
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)
   border-width
   (.read-type CARD16 ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->CreateNotifyEvent
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
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+ 2 1 (.sizeof WINDOW) (.sizeof WINDOW))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num, :event (->DestroyNotifyEvent event window)}))

(clojure.core/defn
 read-UnmapNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   from-configure
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+ 2 1 (.sizeof WINDOW) (.sizeof WINDOW) 1 3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->UnmapNotifyEvent event window from-configure)}))

(clojure.core/defn
 read-MapNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+ 2 1 (.sizeof WINDOW) (.sizeof WINDOW) 1 3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->MapNotifyEvent event window override-redirect)}))

(clojure.core/defn
 read-MapRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   parent
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+ 2 1 (.sizeof WINDOW) (.sizeof WINDOW))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num, :event (->MapRequestEvent parent window)}))

(clojure.core/defn
 read-ReparentNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   parent
   (.read-type WINDOW ch)
   x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     1
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->ReparentNotifyEvent event window parent x y override-redirect)}))

(clojure.core/defn
 read-ConfigureNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   above-sibling
   (.read-type WINDOW ch)
   x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)
   border-width
   (.read-type CARD16 ch)
   override-redirect
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     1
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->ConfigureNotifyEvent
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
   (.read-type BYTE ch)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   parent
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   sibling
   (.read-type WINDOW ch)
   x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)
   border-width
   (.read-type CARD16 ch)
   value-mask
   (.read-type CARD16 ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     (.sizeof BYTE)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16)
     (.sizeof CARD16))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->ConfigureRequestEvent
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
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   x
   (.read-type INT16 ch)
   y
   (.read-type INT16 ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof INT16)
     (.sizeof INT16))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num, :event (->GravityNotifyEvent event window x y)}))

(clojure.core/defn
 read-ResizeRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type WINDOW ch)
   width
   (.read-type CARD16 ch)
   height
   (.read-type CARD16 ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof CARD16)
     (.sizeof CARD16))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->ResizeRequestEvent window width height)}))

(clojure.core/defn
 read-CirculateNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   place
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     4
     (.sizeof BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->CirculateNotifyEvent event window place)}))

(clojure.core/defn
 read-CirculateRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   event
   (.read-type WINDOW ch)
   window
   (.read-type WINDOW ch)
   _
   (xcljb.gen-common/read-pad ch 4)
   place
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     4
     (.sizeof BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->CirculateRequestEvent event window place)}))

(clojure.core/defn
 read-PropertyNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type WINDOW ch)
   atom
   (.read-type ATOM ch)
   time
   (.read-type TIMESTAMP ch)
   state
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 3)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof ATOM)
     (.sizeof TIMESTAMP)
     (.sizeof BYTE)
     3)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->PropertyNotifyEvent window atom time state)}))

(clojure.core/defn
 read-SelectionClearEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   owner
   (.read-type WINDOW ch)
   selection
   (.read-type ATOM ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof ATOM))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->SelectionClearEvent time owner selection)}))

(clojure.core/defn
 read-SelectionRequestEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   time
   (.read-type TIMESTAMP ch)
   owner
   (.read-type WINDOW ch)
   requestor
   (.read-type WINDOW ch)
   selection
   (.read-type ATOM ch)
   target
   (.read-type ATOM ch)
   property
   (.read-type ATOM ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof WINDOW)
     (.sizeof ATOM)
     (.sizeof ATOM)
     (.sizeof ATOM))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->SelectionRequestEvent
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
   (.read-type TIMESTAMP ch)
   requestor
   (.read-type WINDOW ch)
   selection
   (.read-type ATOM ch)
   target
   (.read-type ATOM ch)
   property
   (.read-type ATOM ch)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof TIMESTAMP)
     (.sizeof WINDOW)
     (.sizeof ATOM)
     (.sizeof ATOM)
     (.sizeof ATOM))]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event
   (->SelectionNotifyEvent time requestor selection target property)}))

(clojure.core/defn
 read-ColormapNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   window
   (.read-type WINDOW ch)
   colormap
   (.read-type COLORMAP ch)
   new
   (if
    (clojure.core/= (xcljb.gen-common/read-bytes ch 1) 1)
    true
    false)
   state
   (.read-type BYTE ch)
   _
   (xcljb.gen-common/read-pad ch 2)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof WINDOW)
     (.sizeof COLORMAP)
     1
     (.sizeof BYTE)
     2)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->ColormapNotifyEvent window colormap new state)}))

(clojure.core/defn
 read-MappingNotifyEvent
 [ch]
 (clojure.core/let
  [_
   (xcljb.gen-common/read-pad ch 1)
   seq-num
   (xcljb.gen-common/read-bytes ch 2)
   request
   (.read-type BYTE ch)
   first-keycode
   (.read-type KEYCODE ch)
   count
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1133__auto__
    (clojure.core/+
     2
     1
     (.sizeof BYTE)
     (.sizeof KEYCODE)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1133__auto__ 31)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 31 size__1133__auto__))))
  {:seq-num seq-num,
   :event (->MappingNotifyEvent request first-keycode count)}))

(clojure.core/defn
 read-RequestError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->RequestError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-ValueError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->ValueError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-WindowError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->WindowError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-PixmapError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->PixmapError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-AtomError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->AtomError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-CursorError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->CursorError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-FontError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->FontError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-MatchError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->MatchError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-DrawableError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->DrawableError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-AccessError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->AccessError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-AllocError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->AllocError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-ColormapError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->ColormapError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-GContextError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->GContextError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-IDChoiceError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->IDChoiceError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-NameError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->NameError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-LengthError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->LengthError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-ImplementationError
 [ch]
 (clojure.core/let
  [bad-value
   (.read-type CARD32 ch)
   minor-opcode
   (.read-type CARD16 ch)
   major-opcode
   (.read-type CARD8 ch)
   _
   (xcljb.gen-common/read-pad ch 1)]
  (clojure.core/let
   [size__1166__auto__
    (clojure.core/+
     (.sizeof CARD32)
     (.sizeof CARD16)
     (.sizeof CARD8)
     1)]
   (clojure.core/when
    (clojure.core/< size__1166__auto__ 28)
    (xcljb.gen-common/read-pad
     ch
     (clojure.core/- 28 size__1166__auto__))))
  (->ImplementationError bad-value minor-opcode major-opcode)))

(clojure.core/defn
 read-reply
 [expr__1322__auto__]
 (clojure.core/case
  expr__1322__auto__
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
 [expr__1322__auto__]
 (clojure.core/case
  expr__1322__auto__
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
 [expr__1322__auto__]
 (clojure.core/case
  expr__1322__auto__
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
