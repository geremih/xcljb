(clojure.core/ns
 xcljb.gen.xproto
 (:use [xcljb.gen xproto-types])
 (:require [xcljb conn gen-common]))

(def
 -XCB
 {:minor-version nil,
  :major-version nil,
  :header "xproto",
  :extension-multiword false,
  :extension-name nil,
  :extension-xname nil})

(def ButtonIndex {:5 5, :4 4, :3 3, :2 2, :1 1, :Any 0})

(def MappingStatus {:Failure 2, :Busy 1, :Success 0})

(def ClipOrdering {:YXBanded 3, :YXSorted 2, :YSorted 1, :Unsorted 0})

(def Circulate {:LowerHighest 1, :RaiseLowest 0})

(def BackPixmap {:ParentRelative 1, :None 0})

(def
 Family
 {:Internet6 6, :ServerInterpreted 5, :Chaos 2, :DECnet 1, :Internet 0})

(def SetMode {:Delete 1, :Insert 0})

(def GetPropertyType {:Any 0})

(def BackingStore {:Always 2, :WhenMapped 1, :NotUseful 0})

(def
 EventMask
 {:ColorMapChange 8388608,
  :ButtonMotion 8192,
  :ButtonPress 4,
  :OwnerGrabButton 16777216,
  :KeyPress 1,
  :Button4Motion 2048,
  :Button3Motion 1024,
  :FocusChange 2097152,
  :Button1Motion 256,
  :PointerMotionHint 128,
  :LeaveWindow 32,
  :PointerMotion 64,
  :KeymapState 16384,
  :ButtonRelease 8,
  :VisibilityChange 65536,
  :ResizeRedirect 262144,
  :NoEvent 0,
  :Button5Motion 4096,
  :PropertyChange 4194304,
  :Button2Motion 512,
  :SubstructureRedirect 1048576,
  :EnterWindow 16,
  :KeyRelease 2,
  :StructureNotify 131072,
  :SubstructureNotify 524288,
  :Exposure 32768})

(def PropMode {:Append 2, :Prepend 1, :Replace 0})

(def Place {:OnBottom 1, :OnTop 0})

(def JoinStyle {:Bevel 2, :Round 1, :Miter 0})

(def WindowClass {:InputOnly 2, :InputOutput 1, :CopyFromParent 0})

(def ScreenSaver {:Active 1, :Reset 0})

(def
 Allow
 {:SyncBoth 7,
  :AsyncBoth 6,
  :ReplayKeyboard 5,
  :SyncKeyboard 4,
  :AsyncKeyboard 3,
  :ReplayPointer 2,
  :SyncPointer 1,
  :AsyncPointer 0})

(def StackMode {:Opposite 4, :BottomIf 3, :TopIf 2, :Below 1, :Above 0})

(def HostMode {:Delete 1, :Insert 0})

(def Exposures {:Default 2, :Allowed 1, :NotAllowed 0})

(def NotifyMode {:WhileGrabbed 3, :Ungrab 2, :Grab 1, :Normal 0})

(def Grab {:Any 0})

(def AutoRepeatMode {:Default 2, :On 1, :Off 0})

(def Time {:CurrentTime 0})

(def Mapping {:Pointer 2, :Keyboard 1, :Modifier 0})

(def
 ModMask
 {:Any 32768,
  :5 128,
  :4 64,
  :3 32,
  :2 16,
  :1 8,
  :Control 4,
  :Lock 2,
  :Shift 1})

(def
 KeyButMask
 {:Mod3 32,
  :Mod2 16,
  :Mod5 128,
  :Mod4 64,
  :Shift 1,
  :Mod1 8,
  :Control 4,
  :Button1 256,
  :Lock 2,
  :Button3 1024,
  :Button2 512,
  :Button5 4096,
  :Button4 2048})

(def FillStyle {:OpaqueStippled 3, :Stippled 2, :Tiled 1, :Solid 0})

(def
 Gravity
 {:South 8,
  :WinUnmap 0,
  :NorthWest 1,
  :Static 10,
  :North 2,
  :NorthEast 3,
  :BitForget 0,
  :East 6,
  :SouthEast 9,
  :West 4,
  :Center 5,
  :SouthWest 7})

(def LedMode {:On 1, :Off 0})

(def Blanking {:Default 2, :Preferred 1, :NotPreferred 0})

(def InputFocus {:FollowKeyboard 3, :Parent 2, :PointerRoot 1, :None 0})

(def Font {:None 0})

(def ColorFlag {:Blue 4, :Green 2, :Red 1})

(def GrabMode {:Async 1, :Sync 0})

(def Window {:None 0})

(def QueryShapeOf {:FastestStipple 2, :FastestTile 1, :LargestCursor 0})

(def
 GX
 {:orReverse 11,
  :nand 14,
  :orInverted 13,
  :equiv 9,
  :andReverse 2,
  :copyInverted 12,
  :or 7,
  :set 15,
  :noop 5,
  :xor 6,
  :andInverted 4,
  :invert 10,
  :and 1,
  :nor 8,
  :clear 0,
  :copy 3})

(def
 Atom
 {:MAX_SPACE 45,
  :WM_TRANSIENT_FOR 68,
  :RESOLUTION 60,
  :SUPERSCRIPT_Y 48,
  :STRIKEOUT_DESCENT 54,
  :RGB_COLOR_MAP 24,
  :QUAD_WIDTH 57,
  :RGB_BLUE_MAP 26,
  :BITMAP 5,
  :WM_COMMAND 34,
  :WM_CLASS 67,
  :RESOURCE_MANAGER 23,
  :STRING 31,
  :UNDERLINE_POSITION 51,
  :FONT_NAME 63,
  :RGB_RED_MAP 30,
  :PIXMAP 20,
  :WM_ICON_SIZE 38,
  :WEIGHT 58,
  :RGB_DEFAULT_MAP 27,
  :PRIMARY 1,
  :END_SPACE 46,
  :WM_CLIENT_MACHINE 36,
  :VISUALID 32,
  :STRIKEOUT_ASCENT 53,
  :CUT_BUFFER7 16,
  :RGB_BEST_MAP 25,
  :ITALIC_ANGLE 55,
  :CUT_BUFFER6 15,
  :None 0,
  :ARC 3,
  :CUT_BUFFER5 14,
  :NOTICE 62,
  :RGB_GRAY_MAP 28,
  :SUPERSCRIPT_X 47,
  :CURSOR 8,
  :CUT_BUFFER3 12,
  :UNDERLINE_THICKNESS 52,
  :CUT_BUFFER4 13,
  :SECONDARY 2,
  :WM_HINTS 35,
  :CUT_BUFFER1 10,
  :CAP_HEIGHT 66,
  :X_HEIGHT 56,
  :WM_NAME 39,
  :WM_ICON_NAME 37,
  :CUT_BUFFER2 11,
  :ATOM 4,
  :FULL_NAME 65,
  :FAMILY_NAME 64,
  :WM_NORMAL_HINTS 40,
  :CUT_BUFFER0 9,
  :INTEGER 19,
  :SUBSCRIPT_X 49,
  :DRAWABLE 17,
  :SUBSCRIPT_Y 50,
  :Any 0,
  :WM_SIZE_HINTS 41,
  :CARDINAL 6,
  :NORM_SPACE 44,
  :RGB_GREEN_MAP 29,
  :WM_ZOOM_HINTS 42,
  :FONT 18,
  :RECTANGLE 22,
  :COPYRIGHT 61,
  :POINT 21,
  :POINT_SIZE 59,
  :WINDOW 33,
  :MIN_SPACE 43,
  :COLORMAP 7})

(def ImageFormat {:ZPixmap 2, :XYPixmap 1, :XYBitmap 0})

(def
 ConfigWindow
 {:StackMode 64,
  :Sibling 32,
  :BorderWidth 16,
  :Height 8,
  :Width 4,
  :Y 2,
  :X 1})

(def Visibility {:FullyObscured 2, :PartiallyObscured 1, :Unobscured 0})

(def FontDraw {:RightToLeft 1, :LeftToRight 0})

(def ImageOrder {:MSBFirst 1, :LSBFirst 0})

(def SubwindowMode {:IncludeInferiors 1, :ClipByChildren 0})

(def
 CW
 {:BackingPlanes 128,
  :WinGravity 32,
  :BackPixmap 1,
  :DontPropagate 4096,
  :BackPixel 2,
  :OverrideRedirect 512,
  :EventMask 2048,
  :SaveUnder 1024,
  :Colormap 8192,
  :BitGravity 16,
  :BackingPixel 256,
  :Cursor 16384,
  :BorderPixmap 4,
  :BackingStore 64,
  :BorderPixel 8})

(def SendEventDest {:ItemFocus 1, :PointerWindow 0})

(def MapState {:Viewable 2, :Unviewable 1, :Unmapped 0})

(def Property {:Delete 1, :NewValue 0})

(def ArcMode {:PieSlice 1, :Chord 0})

(def PolyShape {:Convex 2, :Nonconvex 1, :Complex 0})

(def
 MapIndex
 {:5 7, :4 6, :3 5, :2 4, :1 3, :Control 2, :Lock 1, :Shift 0})

(def Cursor {:None 0})

(def Motion {:Hint 1, :Normal 0})

(def
 KB
 {:AutoRepeatMode 128,
  :Key 64,
  :LedMode 32,
  :Led 16,
  :BellDuration 8,
  :BellPitch 4,
  :BellPercent 2,
  :KeyClickPercent 1})

(def ColormapState {:Installed 1, :Uninstalled 0})

(def
 VisualClass
 {:DirectColor 5,
  :TrueColor 4,
  :PseudoColor 3,
  :StaticColor 2,
  :GrayScale 1,
  :StaticGray 0})

(def CoordMode {:Previous 1, :Origin 0})

(def AccessControl {:Enable 1, :Disable 0})

(def Colormap {:None 0})

(def
 NotifyDetail
 {:None 7,
  :PointerRoot 6,
  :Pointer 5,
  :NonlinearVirtual 4,
  :Nonlinear 3,
  :Inferior 2,
  :Virtual 1,
  :Ancestor 0})

(def CloseDown {:RetainTemporary 2, :RetainPermanent 1, :DestroyAll 0})

(def
 GC
 {:TileStippleOriginX 4096,
  :Function 1,
  :LineWidth 16,
  :GraphicsExposures 65536,
  :Foreground 4,
  :ClipOriginY 262144,
  :ClipOriginX 131072,
  :DashOffset 1048576,
  :FillRule 512,
  :Stipple 2048,
  :TileStippleOriginY 8192,
  :Font 16384,
  :JoinStyle 128,
  :SubwindowMode 32768,
  :CapStyle 64,
  :Tile 1024,
  :FillStyle 256,
  :Background 8,
  :PlaneMask 2,
  :ArcMode 4194304,
  :ClipMask 524288,
  :DashList 2097152,
  :LineStyle 32})

(def ColormapAlloc {:All 1, :None 0})

(def LineStyle {:DoubleDash 2, :OnOffDash 1, :Solid 0})

(def Pixmap {:None 0})

(def ButtonMask {:Any 32768, :5 4096, :4 2048, :3 1024, :2 512, :1 256})

(def
 GrabStatus
 {:Frozen 4,
  :NotViewable 3,
  :InvalidTime 2,
  :AlreadyGrabbed 1,
  :Success 0})

(def Kill {:AllTemporary 0})

(def CapStyle {:Projecting 3, :Round 2, :Butt 1, :NotLast 0})

(def FillRule {:Winding 1, :EvenOdd 0})

(clojure.core/defn
 create-window
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->CreateWindowRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-window-attributes
 [conn__1050__auto__ window value]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangeWindowAttributesRequest 2 window value)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-window-attributes
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GetWindowAttributesRequest 3 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 destroy-window
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->DestroyWindowRequest 4 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 destroy-subwindows
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->DestroySubwindowsRequest 5 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-save-set
 [conn__1050__auto__ mode window]
 (clojure.core/let
  [request-struct__1051__auto__ (->ChangeSaveSetRequest 6 mode window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 reparent-window
 [conn__1050__auto__ window parent x y]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ReparentWindowRequest 7 window parent x y)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 map-window
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->MapWindowRequest 8 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 map-subwindows
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->MapSubwindowsRequest 9 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 unmap-window
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->UnmapWindowRequest 10 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 unmap-subwindows
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->UnmapSubwindowsRequest 11 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 circulate-window
 [conn__1050__auto__ direction window]
 (clojure.core/let
  [request-struct__1051__auto__
   (->CirculateWindowRequest 13 direction window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-geometry
 [conn__1050__auto__ drawable]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetGeometryRequest 14 drawable)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-tree
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->QueryTreeRequest 15 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 intern-atom
 [conn__1050__auto__ only-if-exists name-len name]
 (clojure.core/let
  [request-struct__1051__auto__
   (->InternAtomRequest 16 only-if-exists name-len name)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-atom-name
 [conn__1050__auto__ atom]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetAtomNameRequest 17 atom)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-property
 [conn__1050__auto__ mode window property type format data-len data]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangePropertyRequest
    18
    mode
    window
    property
    type
    format
    data-len
    data)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 delete-property
 [conn__1050__auto__ window property]
 (clojure.core/let
  [request-struct__1051__auto__
   (->DeletePropertyRequest 19 window property)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-property
 [conn__1050__auto__
  delete
  window
  property
  type
  long-offset
  long-length]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GetPropertyRequest
    20
    delete
    window
    property
    type
    long-offset
    long-length)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 list-properties
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->ListPropertiesRequest 21 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-selection-owner
 [conn__1050__auto__ owner selection time]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetSelectionOwnerRequest 22 owner selection time)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-selection-owner
 [conn__1050__auto__ selection]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GetSelectionOwnerRequest 23 selection)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 convert-selection
 [conn__1050__auto__ requestor selection target property time]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ConvertSelectionRequest
    24
    requestor
    selection
    target
    property
    time)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 send-event
 [conn__1050__auto__ propagate destination event-mask event]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SendEventRequest 25 propagate destination event-mask event)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 grab-pointer
 [conn__1050__auto__
  owner-events
  grab-window
  event-mask
  pointer-mode
  keyboard-mode
  confine-to
  cursor
  time]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GrabPointerRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 ungrab-pointer
 [conn__1050__auto__ time]
 (clojure.core/let
  [request-struct__1051__auto__ (->UngrabPointerRequest 27 time)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 grab-button
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->GrabButtonRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 ungrab-button
 [conn__1050__auto__ button grab-window modifiers]
 (clojure.core/let
  [request-struct__1051__auto__
   (->UngrabButtonRequest 29 button grab-window modifiers)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-active-pointer-grab
 [conn__1050__auto__ cursor time event-mask]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangeActivePointerGrabRequest 30 cursor time event-mask)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 grab-keyboard
 [conn__1050__auto__
  owner-events
  grab-window
  time
  pointer-mode
  keyboard-mode]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GrabKeyboardRequest
    31
    owner-events
    grab-window
    time
    pointer-mode
    keyboard-mode)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 ungrab-keyboard
 [conn__1050__auto__ time]
 (clojure.core/let
  [request-struct__1051__auto__ (->UngrabKeyboardRequest 32 time)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 grab-key
 [conn__1050__auto__
  owner-events
  grab-window
  modifiers
  key
  pointer-mode
  keyboard-mode]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GrabKeyRequest
    33
    owner-events
    grab-window
    modifiers
    key
    pointer-mode
    keyboard-mode)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 ungrab-key
 [conn__1050__auto__ key grab-window modifiers]
 (clojure.core/let
  [request-struct__1051__auto__
   (->UngrabKeyRequest 34 key grab-window modifiers)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 allow-events
 [conn__1050__auto__ mode time]
 (clojure.core/let
  [request-struct__1051__auto__ (->AllowEventsRequest 35 mode time)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 grab-server
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GrabServerRequest 36)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 ungrab-server
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->UngrabServerRequest 37)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-pointer
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__ (->QueryPointerRequest 38 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-motion-events
 [conn__1050__auto__ window start stop]
 (clojure.core/let
  [request-struct__1051__auto__
   (->GetMotionEventsRequest 39 window start stop)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 translate-coordinates
 [conn__1050__auto__ src-window dst-window src-x src-y]
 (clojure.core/let
  [request-struct__1051__auto__
   (->TranslateCoordinatesRequest
    40
    src-window
    dst-window
    src-x
    src-y)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 warp-pointer
 [conn__1050__auto__
  src-window
  dst-window
  src-x
  src-y
  src-width
  src-height
  dst-x
  dst-y]
 (clojure.core/let
  [request-struct__1051__auto__
   (->WarpPointerRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-input-focus
 [conn__1050__auto__ revert-to focus time]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetInputFocusRequest 42 revert-to focus time)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-input-focus
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetInputFocusRequest 43)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-keymap
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->QueryKeymapRequest 44)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 open-font
 [conn__1050__auto__ fid name-len name]
 (clojure.core/let
  [request-struct__1051__auto__
   (->OpenFontRequest 45 fid name-len name)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 close-font
 [conn__1050__auto__ font]
 (clojure.core/let
  [request-struct__1051__auto__ (->CloseFontRequest 46 font)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-font
 [conn__1050__auto__ font]
 (clojure.core/let
  [request-struct__1051__auto__ (->QueryFontRequest 47 font)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 list-fonts
 [conn__1050__auto__ max-names pattern-len pattern]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ListFontsRequest 49 max-names pattern-len pattern)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 list-fonts-with-info
 [conn__1050__auto__ max-names pattern-len pattern]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ListFontsWithInfoRequest 50 max-names pattern-len pattern)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-font-path
 [conn__1050__auto__ font-qty font]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetFontPathRequest 51 font-qty font)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-font-path
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetFontPathRequest 52)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 create-pixmap
 [conn__1050__auto__ depth pid drawable width height]
 (clojure.core/let
  [request-struct__1051__auto__
   (->CreatePixmapRequest 53 depth pid drawable width height)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 free-pixmap
 [conn__1050__auto__ pixmap]
 (clojure.core/let
  [request-struct__1051__auto__ (->FreePixmapRequest 54 pixmap)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 create-gc
 [conn__1050__auto__ cid drawable value]
 (clojure.core/let
  [request-struct__1051__auto__
   (->CreateGCRequest 55 cid drawable value)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-gc
 [conn__1050__auto__ gc value]
 (clojure.core/let
  [request-struct__1051__auto__ (->ChangeGCRequest 56 gc value)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 copy-gc
 [conn__1050__auto__ src-gc dst-gc value-mask]
 (clojure.core/let
  [request-struct__1051__auto__
   (->CopyGCRequest 57 src-gc dst-gc value-mask)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-dashes
 [conn__1050__auto__ gc dash-offset dashes-len dashes]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetDashesRequest 58 gc dash-offset dashes-len dashes)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-clip-rectangles
 [conn__1050__auto__
  ordering
  gc
  clip-x-origin
  clip-y-origin
  rectangles]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetClipRectanglesRequest
    59
    ordering
    gc
    clip-x-origin
    clip-y-origin
    rectangles)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 free-gc
 [conn__1050__auto__ gc]
 (clojure.core/let
  [request-struct__1051__auto__ (->FreeGCRequest 60 gc)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 clear-area
 [conn__1050__auto__ exposures window x y width height]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ClearAreaRequest 61 exposures window x y width height)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 copy-area
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->CopyAreaRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 copy-plane
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->CopyPlaneRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-point
 [conn__1050__auto__ coordinate-mode drawable gc points]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyPointRequest 64 coordinate-mode drawable gc points)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-line
 [conn__1050__auto__ coordinate-mode drawable gc points]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyLineRequest 65 coordinate-mode drawable gc points)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-segment
 [conn__1050__auto__ drawable gc segments]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolySegmentRequest 66 drawable gc segments)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-rectangle
 [conn__1050__auto__ drawable gc rectangles]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyRectangleRequest 67 drawable gc rectangles)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-arc
 [conn__1050__auto__ drawable gc arcs]
 (clojure.core/let
  [request-struct__1051__auto__ (->PolyArcRequest 68 drawable gc arcs)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 fill-poly
 [conn__1050__auto__ drawable gc shape coordinate-mode points]
 (clojure.core/let
  [request-struct__1051__auto__
   (->FillPolyRequest 69 drawable gc shape coordinate-mode points)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-fill-rectangle
 [conn__1050__auto__ drawable gc rectangles]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyFillRectangleRequest 70 drawable gc rectangles)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-fill-arc
 [conn__1050__auto__ drawable gc arcs]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyFillArcRequest 71 drawable gc arcs)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 put-image
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->PutImageRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-text8
 [conn__1050__auto__ drawable gc x y items]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyText8Request 74 drawable gc x y items)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 poly-text16
 [conn__1050__auto__ drawable gc x y items]
 (clojure.core/let
  [request-struct__1051__auto__
   (->PolyText16Request 75 drawable gc x y items)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 image-text8
 [conn__1050__auto__ string-len drawable gc x y string]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ImageText8Request 76 string-len drawable gc x y string)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 create-colormap
 [conn__1050__auto__ alloc mid window visual]
 (clojure.core/let
  [request-struct__1051__auto__
   (->CreateColormapRequest 78 alloc mid window visual)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 free-colormap
 [conn__1050__auto__ cmap]
 (clojure.core/let
  [request-struct__1051__auto__ (->FreeColormapRequest 79 cmap)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 copy-colormap-and-free
 [conn__1050__auto__ mid src-cmap]
 (clojure.core/let
  [request-struct__1051__auto__
   (->CopyColormapAndFreeRequest 80 mid src-cmap)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 install-colormap
 [conn__1050__auto__ cmap]
 (clojure.core/let
  [request-struct__1051__auto__ (->InstallColormapRequest 81 cmap)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 uninstall-colormap
 [conn__1050__auto__ cmap]
 (clojure.core/let
  [request-struct__1051__auto__ (->UninstallColormapRequest 82 cmap)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 list-installed-colormaps
 [conn__1050__auto__ window]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ListInstalledColormapsRequest 83 window)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 alloc-color
 [conn__1050__auto__ cmap red green blue]
 (clojure.core/let
  [request-struct__1051__auto__
   (->AllocColorRequest 84 cmap red green blue)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 alloc-named-color
 [conn__1050__auto__ cmap name-len name]
 (clojure.core/let
  [request-struct__1051__auto__
   (->AllocNamedColorRequest 85 cmap name-len name)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 alloc-color-cells
 [conn__1050__auto__ contiguous cmap colors planes]
 (clojure.core/let
  [request-struct__1051__auto__
   (->AllocColorCellsRequest 86 contiguous cmap colors planes)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 alloc-color-planes
 [conn__1050__auto__ contiguous cmap colors reds greens blues]
 (clojure.core/let
  [request-struct__1051__auto__
   (->AllocColorPlanesRequest
    87
    contiguous
    cmap
    colors
    reds
    greens
    blues)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 free-colors
 [conn__1050__auto__ cmap plane-mask pixels]
 (clojure.core/let
  [request-struct__1051__auto__
   (->FreeColorsRequest 88 cmap plane-mask pixels)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 store-colors
 [conn__1050__auto__ cmap items]
 (clojure.core/let
  [request-struct__1051__auto__ (->StoreColorsRequest 89 cmap items)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 store-named-color
 [conn__1050__auto__ flags cmap pixel name-len name]
 (clojure.core/let
  [request-struct__1051__auto__
   (->StoreNamedColorRequest 90 flags cmap pixel name-len name)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-colors
 [conn__1050__auto__ cmap pixels]
 (clojure.core/let
  [request-struct__1051__auto__ (->QueryColorsRequest 91 cmap pixels)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 lookup-color
 [conn__1050__auto__ cmap name-len name]
 (clojure.core/let
  [request-struct__1051__auto__
   (->LookupColorRequest 92 cmap name-len name)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 create-cursor
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->CreateCursorRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 create-glyph-cursor
 [conn__1050__auto__
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
  [request-struct__1051__auto__
   (->CreateGlyphCursorRequest
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
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 free-cursor
 [conn__1050__auto__ cursor]
 (clojure.core/let
  [request-struct__1051__auto__ (->FreeCursorRequest 95 cursor)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 recolor-cursor
 [conn__1050__auto__
  cursor
  fore-red
  fore-green
  fore-blue
  back-red
  back-green
  back-blue]
 (clojure.core/let
  [request-struct__1051__auto__
   (->RecolorCursorRequest
    96
    cursor
    fore-red
    fore-green
    fore-blue
    back-red
    back-green
    back-blue)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-best-size
 [conn__1050__auto__ class drawable width height]
 (clojure.core/let
  [request-struct__1051__auto__
   (->QueryBestSizeRequest 97 class drawable width height)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 query-extension
 [conn__1050__auto__ name-len name]
 (clojure.core/let
  [request-struct__1051__auto__
   (->QueryExtensionRequest 98 name-len name)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 list-extensions
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->ListExtensionsRequest 99)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-keyboard-mapping
 [conn__1050__auto__
  keycode-count
  first-keycode
  keysyms-per-keycode
  keysyms]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangeKeyboardMappingRequest
    100
    keycode-count
    first-keycode
    keysyms-per-keycode
    keysyms)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-keyboard-control
 [conn__1050__auto__ value]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangeKeyboardControlRequest 102 value)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-keyboard-control
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetKeyboardControlRequest 103)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 bell
 [conn__1050__auto__ percent]
 (clojure.core/let
  [request-struct__1051__auto__ (->BellRequest 104 percent)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-pointer-control
 [conn__1050__auto__
  acceleration-numerator
  acceleration-denominator
  threshold
  do-acceleration
  do-threshold]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangePointerControlRequest
    105
    acceleration-numerator
    acceleration-denominator
    threshold
    do-acceleration
    do-threshold)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-pointer-control
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetPointerControlRequest 106)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-screen-saver
 [conn__1050__auto__ timeout interval prefer-blanking allow-exposures]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetScreenSaverRequest
    107
    timeout
    interval
    prefer-blanking
    allow-exposures)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-screen-saver
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetScreenSaverRequest 108)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 change-hosts
 [conn__1050__auto__ mode family address-len address]
 (clojure.core/let
  [request-struct__1051__auto__
   (->ChangeHostsRequest 109 mode family address-len address)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 list-hosts
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->ListHostsRequest 110)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-access-control
 [conn__1050__auto__ mode]
 (clojure.core/let
  [request-struct__1051__auto__ (->SetAccessControlRequest 111 mode)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-close-down-mode
 [conn__1050__auto__ mode]
 (clojure.core/let
  [request-struct__1051__auto__ (->SetCloseDownModeRequest 112 mode)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 kill-client
 [conn__1050__auto__ resource]
 (clojure.core/let
  [request-struct__1051__auto__ (->KillClientRequest 113 resource)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 rotate-properties
 [conn__1050__auto__ window atoms-len delta atoms]
 (clojure.core/let
  [request-struct__1051__auto__
   (->RotatePropertiesRequest 114 window atoms-len delta atoms)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 force-screen-saver
 [conn__1050__auto__ mode]
 (clojure.core/let
  [request-struct__1051__auto__ (->ForceScreenSaverRequest 115 mode)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-pointer-mapping
 [conn__1050__auto__ map-len map]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetPointerMappingRequest 116 map-len map)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-pointer-mapping
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetPointerMappingRequest 117)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 set-modifier-mapping
 [conn__1050__auto__ keycodes-per-modifier keycodes]
 (clojure.core/let
  [request-struct__1051__auto__
   (->SetModifierMappingRequest 118 keycodes-per-modifier keycodes)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 get-modifier-mapping
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->GetModifierMappingRequest 119)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))

(clojure.core/defn
 no-operation
 [conn__1050__auto__]
 (clojure.core/let
  [request-struct__1051__auto__ (->NoOperationRequest 127)]
  (xcljb.gen-common/send
   conn__1050__auto__
   request-struct__1051__auto__)))
