(clojure.core/ns
 xcljb.gen.xproto
 (:require [xcljb conn gen-common] [xcljb.gen xproto-types]))

(def
 -XCB
 {:minor-version nil,
  :major-version nil,
  :header "xproto",
  :extension-multiword false,
  :extension-name nil,
  :extension-xname nil})

(def BUTTON-INDEX {:5 5, :4 4, :3 3, :2 2, :1 1, :any 0})

(def MAPPING-STATUS {:failure 2, :busy 1, :success 0})

(def CLIP-ORDERING {:yxbanded 3, :yxsorted 2, :ysorted 1, :unsorted 0})

(def CIRCULATE {:lower-highest 1, :raise-lowest 0})

(def BACK-PIXMAP {:parent-relative 1, :none 0})

(def
 FAMILY
 {:internet6 6,
  :server-interpreted 5,
  :chaos 2,
  :decnet 1,
  :internet 0})

(def SET-MODE {:delete 1, :insert 0})

(def GET-PROPERTY-TYPE {:any 0})

(def BACKING-STORE {:always 2, :when-mapped 1, :not-useful 0})

(def
 EVENT-MASK
 {:pointer-motion-hint 128,
  :property-change 4194304,
  :pointer-motion 64,
  :leave-window 32,
  :focus-change 2097152,
  :button4motion 2048,
  :substructure-redirect 1048576,
  :keymap-state 16384,
  :button3motion 1024,
  :no-event 0,
  :structure-notify 131072,
  :button1motion 256,
  :button-motion 8192,
  :color-map-change 8388608,
  :button-release 8,
  :key-press 1,
  :button-press 4,
  :resize-redirect 262144,
  :exposure 32768,
  :owner-grab-button 16777216,
  :button5motion 4096,
  :enter-window 16,
  :visibility-change 65536,
  :button2motion 512,
  :substructure-notify 524288,
  :key-release 2})

(def PROP-MODE {:append 2, :prepend 1, :replace 0})

(def PLACE {:on-bottom 1, :on-top 0})

(def JOIN-STYLE {:bevel 2, :round 1, :miter 0})

(def WINDOW-CLASS {:input-only 2, :input-output 1, :copy-from-parent 0})

(def SCREEN-SAVER {:active 1, :reset 0})

(def
 ALLOW
 {:sync-both 7,
  :async-both 6,
  :replay-keyboard 5,
  :sync-keyboard 4,
  :async-keyboard 3,
  :replay-pointer 2,
  :sync-pointer 1,
  :async-pointer 0})

(def
 STACK-MODE
 {:opposite 4, :bottom-if 3, :top-if 2, :below 1, :above 0})

(def HOST-MODE {:delete 1, :insert 0})

(def EXPOSURES {:default 2, :allowed 1, :not-allowed 0})

(def NOTIFY-MODE {:while-grabbed 3, :ungrab 2, :grab 1, :normal 0})

(def GRAB {:any 0})

(def AUTO-REPEAT-MODE {:default 2, :on 1, :off 0})

(def TIME {:current-time 0})

(def MAPPING {:pointer 2, :keyboard 1, :modifier 0})

(def
 MOD-MASK
 {:any 32768,
  :5 128,
  :4 64,
  :3 32,
  :2 16,
  :1 8,
  :control 4,
  :lock 2,
  :shift 1})

(def
 KEY-BUT-MASK
 {:mod4 64,
  :button3 1024,
  :lock 2,
  :button2 512,
  :button5 4096,
  :button4 2048,
  :control 4,
  :button1 256,
  :mod1 8,
  :shift 1,
  :mod3 32,
  :mod2 16,
  :mod5 128})

(def FILL-STYLE {:opaque-stippled 3, :stippled 2, :tiled 1, :solid 0})

(def
 GRAVITY
 {:win-unmap 0,
  :bit-forget 0,
  :west 4,
  :south-west 7,
  :north-east 3,
  :static 10,
  :center 5,
  :north 2,
  :south 8,
  :north-west 1,
  :east 6,
  :south-east 9})

(def LED-MODE {:on 1, :off 0})

(def BLANKING {:default 2, :preferred 1, :not-preferred 0})

(def
 INPUT-FOCUS
 {:follow-keyboard 3, :parent 2, :pointer-root 1, :none 0})

(def FONT {:none 0})

(def COLOR-FLAG {:blue 4, :green 2, :red 1})

(def GRAB-MODE {:async 1, :sync 0})

(def WINDOW {:none 0})

(def
 QUERY-SHAPE-OF
 {:fastest-stipple 2, :fastest-tile 1, :largest-cursor 0})

(def
 GX
 {:and-inverted 4,
  :nand 14,
  :or-reverse 11,
  :equiv 9,
  :or 7,
  :set 15,
  :noop 5,
  :xor 6,
  :invert 10,
  :and-reverse 2,
  :and 1,
  :nor 8,
  :or-inverted 13,
  :clear 0,
  :copy-inverted 12,
  :copy 3})

(def
 ATOM
 {:rgb-gray-map 28,
  :rgb-color-map 24,
  :superscript-y 48,
  :resolution 60,
  :wm-class 67,
  :quad-width 57,
  :end-space 46,
  :wm-zoom-hints 42,
  :wm-hints 35,
  :bitmap 5,
  :wm-icon-name 37,
  :string 31,
  :point 21,
  :max-space 45,
  :copyright 61,
  :pixmap 20,
  :rgb-green-map 29,
  :weight 58,
  :subscript-x 49,
  :subscript-y 50,
  :underline-position 51,
  :full-name 65,
  :norm-space 44,
  :cut-buffer1 10,
  :visualid 32,
  :cut-buffer2 11,
  :underline-thickness 52,
  :integer 19,
  :cut-buffer0 9,
  :notice 62,
  :strikeout-ascent 53,
  :any 0,
  :cursor 8,
  :cut-buffer6 15,
  :cut-buffer7 16,
  :wm-size-hints 41,
  :cut-buffer5 14,
  :cut-buffer4 13,
  :wm-command 34,
  :resource-manager 23,
  :family-name 64,
  :rgb-blue-map 26,
  :cap-height 66,
  :cut-buffer3 12,
  :primary 1,
  :rectangle 22,
  :wm-client-machine 36,
  :atom 4,
  :x-height 56,
  :rgb-default-map 27,
  :wm-icon-size 38,
  :point-size 59,
  :drawable 17,
  :arc 3,
  :none 0,
  :min-space 43,
  :italic-angle 55,
  :rgb-red-map 30,
  :cardinal 6,
  :secondary 2,
  :font-name 63,
  :strikeout-descent 54,
  :wm-name 39,
  :font 18,
  :wm-transient-for 68,
  :superscript-x 47,
  :window 33,
  :wm-normal-hints 40,
  :colormap 7,
  :rgb-best-map 25})

(def IMAGE-FORMAT {:zpixmap 2, :xypixmap 1, :xybitmap 0})

(def
 CONFIG-WINDOW
 {:stack-mode 64,
  :sibling 32,
  :border-width 16,
  :height 8,
  :width 4,
  :y 2,
  :x 1})

(def
 VISIBILITY
 {:fully-obscured 2, :partially-obscured 1, :unobscured 0})

(def FONT-DRAW {:right-to-left 1, :left-to-right 0})

(def IMAGE-ORDER {:msbfirst 1, :lsbfirst 0})

(def SUBWINDOW-MODE {:include-inferiors 1, :clip-by-children 0})

(def
 CW
 {:border-pixel 8,
  :backing-store 64,
  :win-gravity 32,
  :backing-planes 128,
  :bit-gravity 16,
  :save-under 1024,
  :override-redirect 512,
  :event-mask 2048,
  :cursor 16384,
  :dont-propagate 4096,
  :backing-pixel 256,
  :back-pixmap 1,
  :border-pixmap 4,
  :back-pixel 2,
  :colormap 8192})

(def SEND-EVENT-DEST {:item-focus 1, :pointer-window 0})

(def MAP-STATE {:viewable 2, :unviewable 1, :unmapped 0})

(def PROPERTY {:delete 1, :new-value 0})

(def ARC-MODE {:pie-slice 1, :chord 0})

(def POLY-SHAPE {:convex 2, :nonconvex 1, :complex 0})

(def
 MAP-INDEX
 {:5 7, :4 6, :3 5, :2 4, :1 3, :control 2, :lock 1, :shift 0})

(def CURSOR {:none 0})

(def MOTION {:hint 1, :normal 0})

(def
 KB
 {:auto-repeat-mode 128,
  :key 64,
  :led-mode 32,
  :led 16,
  :bell-duration 8,
  :bell-pitch 4,
  :bell-percent 2,
  :key-click-percent 1})

(def COLORMAP-STATE {:installed 1, :uninstalled 0})

(def
 VISUAL-CLASS
 {:direct-color 5,
  :true-color 4,
  :pseudo-color 3,
  :static-color 2,
  :gray-scale 1,
  :static-gray 0})

(def COORD-MODE {:previous 1, :origin 0})

(def ACCESS-CONTROL {:enable 1, :disable 0})

(def COLORMAP {:none 0})

(def
 NOTIFY-DETAIL
 {:none 7,
  :pointer-root 6,
  :pointer 5,
  :nonlinear-virtual 4,
  :nonlinear 3,
  :inferior 2,
  :virtual 1,
  :ancestor 0})

(def
 CLOSE-DOWN
 {:retain-temporary 2, :retain-permanent 1, :destroy-all 0})

(def
 GC
 {:line-style 32,
  :line-width 16,
  :tile 1024,
  :background 8,
  :dash-offset 1048576,
  :foreground 4,
  :dash-list 2097152,
  :graphics-exposures 65536,
  :fill-style 256,
  :plane-mask 2,
  :arc-mode 4194304,
  :function 1,
  :clip-origin-x 131072,
  :clip-origin-y 262144,
  :fill-rule 512,
  :stipple 2048,
  :cap-style 64,
  :subwindow-mode 32768,
  :clip-mask 524288,
  :join-style 128,
  :font 16384,
  :tile-stipple-origin-x 4096,
  :tile-stipple-origin-y 8192})

(def COLORMAP-ALLOC {:all 1, :none 0})

(def LINE-STYLE {:double-dash 2, :on-off-dash 1, :solid 0})

(def PIXMAP {:none 0})

(def
 BUTTON-MASK
 {:any 32768, :5 4096, :4 2048, :3 1024, :2 512, :1 256})

(def
 GRAB-STATUS
 {:frozen 4,
  :not-viewable 3,
  :invalid-time 2,
  :already-grabbed 1,
  :success 0})

(def KILL {:all-temporary 0})

(def CAP-STYLE {:projecting 3, :round 2, :butt 1, :not-last 0})

(def FILL-RULE {:winding 1, :even-odd 0})

(clojure.core/defn
 create-window
 [conn__1077__auto__
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
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CreateWindowRequest
    1
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
    value)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-window-attributes
 [conn__1077__auto__ window value]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeWindowAttributesRequest
    2
    window
    value)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-window-attributes
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetWindowAttributesRequest 3 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 destroy-window
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->DestroyWindowRequest 4 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 destroy-subwindows
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->DestroySubwindowsRequest 5 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-save-set
 [conn__1077__auto__ mode window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeSaveSetRequest 6 mode window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 reparent-window
 [conn__1077__auto__ window parent x y]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ReparentWindowRequest
    7
    window
    parent
    x
    y)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 map-window
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->MapWindowRequest 8 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 map-subwindows
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->MapSubwindowsRequest 9 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 unmap-window
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UnmapWindowRequest 10 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 unmap-subwindows
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UnmapSubwindowsRequest 11 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 circulate-window
 [conn__1077__auto__ direction window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CirculateWindowRequest
    13
    direction
    window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-geometry
 [conn__1077__auto__ drawable]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetGeometryRequest 14 drawable)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-tree
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryTreeRequest 15 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 intern-atom
 [conn__1077__auto__ only-if-exists name-len name]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->InternAtomRequest
    16
    only-if-exists
    name-len
    name)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-atom-name
 [conn__1077__auto__ atom]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetAtomNameRequest 17 atom)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-property
 [conn__1077__auto__ mode window property type format data-len data]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangePropertyRequest
    18
    mode
    window
    property
    type
    format
    data-len
    data)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 delete-property
 [conn__1077__auto__ window property]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->DeletePropertyRequest 19 window property)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-property
 [conn__1077__auto__
  delete
  window
  property
  type
  long-offset
  long-length]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetPropertyRequest
    20
    delete
    window
    property
    type
    long-offset
    long-length)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 list-properties
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ListPropertiesRequest 21 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-selection-owner
 [conn__1077__auto__ owner selection time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetSelectionOwnerRequest
    22
    owner
    selection
    time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-selection-owner
 [conn__1077__auto__ selection]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetSelectionOwnerRequest 23 selection)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 convert-selection
 [conn__1077__auto__ requestor selection target property time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ConvertSelectionRequest
    24
    requestor
    selection
    target
    property
    time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 send-event
 [conn__1077__auto__ propagate destination event-mask event]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SendEventRequest
    25
    propagate
    destination
    event-mask
    event)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 grab-pointer
 [conn__1077__auto__
  owner-events
  grab-window
  event-mask
  pointer-mode
  keyboard-mode
  confine-to
  cursor
  time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GrabPointerRequest
    26
    owner-events
    grab-window
    event-mask
    pointer-mode
    keyboard-mode
    confine-to
    cursor
    time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 ungrab-pointer
 [conn__1077__auto__ time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UngrabPointerRequest 27 time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 grab-button
 [conn__1077__auto__
  owner-events
  grab-window
  event-mask
  pointer-mode
  keyboard-mode
  confine-to
  cursor
  button
  modifiers]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GrabButtonRequest
    28
    owner-events
    grab-window
    event-mask
    pointer-mode
    keyboard-mode
    confine-to
    cursor
    button
    modifiers)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 ungrab-button
 [conn__1077__auto__ button grab-window modifiers]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UngrabButtonRequest
    29
    button
    grab-window
    modifiers)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-active-pointer-grab
 [conn__1077__auto__ cursor time event-mask]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeActivePointerGrabRequest
    30
    cursor
    time
    event-mask)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 grab-keyboard
 [conn__1077__auto__
  owner-events
  grab-window
  time
  pointer-mode
  keyboard-mode]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GrabKeyboardRequest
    31
    owner-events
    grab-window
    time
    pointer-mode
    keyboard-mode)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 ungrab-keyboard
 [conn__1077__auto__ time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UngrabKeyboardRequest 32 time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 grab-key
 [conn__1077__auto__
  owner-events
  grab-window
  modifiers
  key
  pointer-mode
  keyboard-mode]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GrabKeyRequest
    33
    owner-events
    grab-window
    modifiers
    key
    pointer-mode
    keyboard-mode)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 ungrab-key
 [conn__1077__auto__ key grab-window modifiers]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UngrabKeyRequest
    34
    key
    grab-window
    modifiers)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 allow-events
 [conn__1077__auto__ mode time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->AllowEventsRequest 35 mode time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 grab-server
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GrabServerRequest 36)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 ungrab-server
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UngrabServerRequest 37)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-pointer
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryPointerRequest 38 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-motion-events
 [conn__1077__auto__ window start stop]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetMotionEventsRequest
    39
    window
    start
    stop)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 translate-coordinates
 [conn__1077__auto__ src-window dst-window src-x src-y]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->TranslateCoordinatesRequest
    40
    src-window
    dst-window
    src-x
    src-y)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 warp-pointer
 [conn__1077__auto__
  src-window
  dst-window
  src-x
  src-y
  src-width
  src-height
  dst-x
  dst-y]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->WarpPointerRequest
    41
    src-window
    dst-window
    src-x
    src-y
    src-width
    src-height
    dst-x
    dst-y)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-input-focus
 [conn__1077__auto__ revert-to focus time]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetInputFocusRequest
    42
    revert-to
    focus
    time)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-input-focus
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetInputFocusRequest 43)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-keymap
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryKeymapRequest 44)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 open-font
 [conn__1077__auto__ fid name-len name]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->OpenFontRequest 45 fid name-len name)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 close-font
 [conn__1077__auto__ font]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CloseFontRequest 46 font)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-font
 [conn__1077__auto__ font]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryFontRequest 47 font)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 list-fonts
 [conn__1077__auto__ max-names pattern-len pattern]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ListFontsRequest
    49
    max-names
    pattern-len
    pattern)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 list-fonts-with-info
 [conn__1077__auto__ max-names pattern-len pattern]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ListFontsWithInfoRequest
    50
    max-names
    pattern-len
    pattern)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-font-path
 [conn__1077__auto__ font-qty font]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetFontPathRequest 51 font-qty font)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-font-path
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetFontPathRequest 52)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 create-pixmap
 [conn__1077__auto__ depth pid drawable width height]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CreatePixmapRequest
    53
    depth
    pid
    drawable
    width
    height)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 free-pixmap
 [conn__1077__auto__ pixmap]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->FreePixmapRequest 54 pixmap)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 create-gc
 [conn__1077__auto__ cid drawable value]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CreateGCRequest 55 cid drawable value)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-gc
 [conn__1077__auto__ gc value]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeGCRequest 56 gc value)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 copy-gc
 [conn__1077__auto__ src-gc dst-gc value-mask]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CopyGCRequest
    57
    src-gc
    dst-gc
    value-mask)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-dashes
 [conn__1077__auto__ gc dash-offset dashes-len dashes]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetDashesRequest
    58
    gc
    dash-offset
    dashes-len
    dashes)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-clip-rectangles
 [conn__1077__auto__
  ordering
  gc
  clip-x-origin
  clip-y-origin
  rectangles]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetClipRectanglesRequest
    59
    ordering
    gc
    clip-x-origin
    clip-y-origin
    rectangles)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 free-gc
 [conn__1077__auto__ gc]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->FreeGCRequest 60 gc)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 clear-area
 [conn__1077__auto__ exposures window x y width height]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ClearAreaRequest
    61
    exposures
    window
    x
    y
    width
    height)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 copy-area
 [conn__1077__auto__
  src-drawable
  dst-drawable
  gc
  src-x
  src-y
  dst-x
  dst-y
  width
  height]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CopyAreaRequest
    62
    src-drawable
    dst-drawable
    gc
    src-x
    src-y
    dst-x
    dst-y
    width
    height)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 copy-plane
 [conn__1077__auto__
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
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CopyPlaneRequest
    63
    src-drawable
    dst-drawable
    gc
    src-x
    src-y
    dst-x
    dst-y
    width
    height
    bit-plane)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-point
 [conn__1077__auto__ coordinate-mode drawable gc points]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyPointRequest
    64
    coordinate-mode
    drawable
    gc
    points)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-line
 [conn__1077__auto__ coordinate-mode drawable gc points]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyLineRequest
    65
    coordinate-mode
    drawable
    gc
    points)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-segment
 [conn__1077__auto__ drawable gc segments]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolySegmentRequest
    66
    drawable
    gc
    segments)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-rectangle
 [conn__1077__auto__ drawable gc rectangles]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyRectangleRequest
    67
    drawable
    gc
    rectangles)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-arc
 [conn__1077__auto__ drawable gc arcs]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyArcRequest 68 drawable gc arcs)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 fill-poly
 [conn__1077__auto__ drawable gc shape coordinate-mode points]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->FillPolyRequest
    69
    drawable
    gc
    shape
    coordinate-mode
    points)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-fill-rectangle
 [conn__1077__auto__ drawable gc rectangles]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyFillRectangleRequest
    70
    drawable
    gc
    rectangles)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-fill-arc
 [conn__1077__auto__ drawable gc arcs]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyFillArcRequest 71 drawable gc arcs)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 put-image
 [conn__1077__auto__
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
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PutImageRequest
    72
    format
    drawable
    gc
    width
    height
    dst-x
    dst-y
    left-pad
    depth
    data)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-text8
 [conn__1077__auto__ drawable gc x y items]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyText8Request
    74
    drawable
    gc
    x
    y
    items)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 poly-text16
 [conn__1077__auto__ drawable gc x y items]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->PolyText16Request
    75
    drawable
    gc
    x
    y
    items)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 image-text8
 [conn__1077__auto__ string-len drawable gc x y string]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ImageText8Request
    76
    string-len
    drawable
    gc
    x
    y
    string)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 image-text16
 [conn__1077__auto__ string-len drawable gc x y string]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ImageText16Request
    77
    string-len
    drawable
    gc
    x
    y
    string)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 create-colormap
 [conn__1077__auto__ alloc mid window visual]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CreateColormapRequest
    78
    alloc
    mid
    window
    visual)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 free-colormap
 [conn__1077__auto__ cmap]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->FreeColormapRequest 79 cmap)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 copy-colormap-and-free
 [conn__1077__auto__ mid src-cmap]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CopyColormapAndFreeRequest
    80
    mid
    src-cmap)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 install-colormap
 [conn__1077__auto__ cmap]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->InstallColormapRequest 81 cmap)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 uninstall-colormap
 [conn__1077__auto__ cmap]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->UninstallColormapRequest 82 cmap)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 list-installed-colormaps
 [conn__1077__auto__ window]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ListInstalledColormapsRequest 83 window)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 alloc-color
 [conn__1077__auto__ cmap red green blue]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->AllocColorRequest 84 cmap red green blue)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 alloc-named-color
 [conn__1077__auto__ cmap name-len name]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->AllocNamedColorRequest
    85
    cmap
    name-len
    name)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 alloc-color-cells
 [conn__1077__auto__ contiguous cmap colors planes]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->AllocColorCellsRequest
    86
    contiguous
    cmap
    colors
    planes)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 alloc-color-planes
 [conn__1077__auto__ contiguous cmap colors reds greens blues]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->AllocColorPlanesRequest
    87
    contiguous
    cmap
    colors
    reds
    greens
    blues)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 free-colors
 [conn__1077__auto__ cmap plane-mask pixels]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->FreeColorsRequest
    88
    cmap
    plane-mask
    pixels)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 store-colors
 [conn__1077__auto__ cmap items]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->StoreColorsRequest 89 cmap items)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 store-named-color
 [conn__1077__auto__ flags cmap pixel name-len name]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->StoreNamedColorRequest
    90
    flags
    cmap
    pixel
    name-len
    name)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-colors
 [conn__1077__auto__ cmap pixels]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryColorsRequest 91 cmap pixels)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 lookup-color
 [conn__1077__auto__ cmap name-len name]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->LookupColorRequest 92 cmap name-len name)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 create-cursor
 [conn__1077__auto__
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
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CreateCursorRequest
    93
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
    y)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 create-glyph-cursor
 [conn__1077__auto__
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
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->CreateGlyphCursorRequest
    94
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
    back-blue)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 free-cursor
 [conn__1077__auto__ cursor]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->FreeCursorRequest 95 cursor)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 recolor-cursor
 [conn__1077__auto__
  cursor
  fore-red
  fore-green
  fore-blue
  back-red
  back-green
  back-blue]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->RecolorCursorRequest
    96
    cursor
    fore-red
    fore-green
    fore-blue
    back-red
    back-green
    back-blue)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-best-size
 [conn__1077__auto__ class drawable width height]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryBestSizeRequest
    97
    class
    drawable
    width
    height)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 query-extension
 [conn__1077__auto__ name-len name]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->QueryExtensionRequest 98 name-len name)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 list-extensions
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ListExtensionsRequest 99)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-keyboard-mapping
 [conn__1077__auto__
  keycode-count
  first-keycode
  keysyms-per-keycode
  keysyms]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeKeyboardMappingRequest
    100
    keycode-count
    first-keycode
    keysyms-per-keycode
    keysyms)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-keyboard-control
 [conn__1077__auto__ value]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeKeyboardControlRequest 102 value)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-keyboard-control
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetKeyboardControlRequest 103)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 bell
 [conn__1077__auto__ percent]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->BellRequest 104 percent)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-pointer-control
 [conn__1077__auto__
  acceleration-numerator
  acceleration-denominator
  threshold
  do-acceleration
  do-threshold]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangePointerControlRequest
    105
    acceleration-numerator
    acceleration-denominator
    threshold
    do-acceleration
    do-threshold)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-pointer-control
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetPointerControlRequest 106)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-screen-saver
 [conn__1077__auto__ timeout interval prefer-blanking allow-exposures]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetScreenSaverRequest
    107
    timeout
    interval
    prefer-blanking
    allow-exposures)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-screen-saver
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetScreenSaverRequest 108)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 change-hosts
 [conn__1077__auto__ mode family address-len address]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ChangeHostsRequest
    109
    mode
    family
    address-len
    address)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 list-hosts
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ListHostsRequest 110)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-access-control
 [conn__1077__auto__ mode]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetAccessControlRequest 111 mode)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-close-down-mode
 [conn__1077__auto__ mode]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetCloseDownModeRequest 112 mode)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 kill-client
 [conn__1077__auto__ resource]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->KillClientRequest 113 resource)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 rotate-properties
 [conn__1077__auto__ window atoms-len delta atoms]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->RotatePropertiesRequest
    114
    window
    atoms-len
    delta
    atoms)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 force-screen-saver
 [conn__1077__auto__ mode]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->ForceScreenSaverRequest 115 mode)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-pointer-mapping
 [conn__1077__auto__ map-len map]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetPointerMappingRequest 116 map-len map)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-pointer-mapping
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetPointerMappingRequest 117)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 set-modifier-mapping
 [conn__1077__auto__ keycodes-per-modifier keycodes]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->SetModifierMappingRequest
    118
    keycodes-per-modifier
    keycodes)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 get-modifier-mapping
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->GetModifierMappingRequest 119)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))

(clojure.core/defn
 no-operation
 [conn__1077__auto__]
 (clojure.core/let
  [request-struct__1078__auto__
   (xcljb.gen.xproto-types/->NoOperationRequest 127)]
  (xcljb.gen-common/send
   conn__1077__auto__
   request-struct__1078__auto__)))
