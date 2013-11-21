;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.render
 (:require xcljb.conn-ext xcljb.gen.render-types))

(def
 -XCLJB
 {:minor-version 11,
  :major-version 0,
  :header "render",
  :extension-multiword false,
  :extension-name "Render",
  :extension-xname "RENDER"})

(def REPEAT {:reflect 3, :pad 2, :normal 1, :none 0})

(def
 SUB-PIXEL
 {:none 5,
  :vertical-bgr 4,
  :vertical-rgb 3,
  :horizontal-bgr 2,
  :horizontal-rgb 1,
  :unknown 0})

(def
 CP
 {:alpha-xorigin 4,
  :graphics-exposure 128,
  :component-alpha 4096,
  :clip-yorigin 32,
  :alpha-map 2,
  :poly-edge 512,
  :poly-mode 1024,
  :clip-xorigin 16,
  :subwindow-mode 256,
  :clip-mask 64,
  :dither 2048,
  :repeat 1,
  :alpha-yorigin 8})

(def POLY-MODE {:imprecise 1, :precise 0})

(def POLY-EDGE {:smooth 1, :sharp 0})

(def
 PICT-OP
 {:in 5,
  :conjoint-dst 34,
  :hslhue 59,
  :hslcolor 61,
  :saturate 13,
  :hslluminosity 62,
  :color-burn 54,
  :conjoint-out-reverse 40,
  :conjoint-in-reverse 38,
  :disjoint-over 19,
  :disjoint-atop 25,
  :dst 2,
  :conjoint-src 33,
  :out-reverse 8,
  :hard-light 55,
  :disjoint-dst 18,
  :conjoint-clear 32,
  :xor 11,
  :disjoint-out 23,
  :difference 57,
  :disjoint-atop-reverse 26,
  :disjoint-over-reverse 20,
  :soft-light 56,
  :src 1,
  :lighten 52,
  :disjoint-in-reverse 22,
  :screen 49,
  :conjoint-xor 43,
  :disjoint-in 21,
  :color-dodge 53,
  :atop 9,
  :over 3,
  :over-reverse 4,
  :atop-reverse 10,
  :overlay 50,
  :in-reverse 6,
  :disjoint-src 17,
  :conjoint-over 35,
  :hslsaturation 60,
  :conjoint-atop 41,
  :disjoint-out-reverse 24,
  :darken 51,
  :add 12,
  :conjoint-in 37,
  :disjoint-xor 27,
  :clear 0,
  :conjoint-atop-reverse 42,
  :conjoint-over-reverse 36,
  :exclusion 58,
  :out 7,
  :conjoint-out 39,
  :disjoint-clear 16,
  :multiply 48})

(def PICTURE {:none 0})

(def PICT-TYPE {:direct 1, :indexed 0})

(clojure.core/defn
 query-version
 [conn client-major-version client-minor-version]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:client-major-version :client-minor-version]
    [client-major-version client-minor-version])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/QueryVersionRequest
   request)))

(clojure.core/defn
 query-pict-formats
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/QueryPictFormatsRequest
   request)))

(clojure.core/defn
 query-pict-index-values
 [conn format]
 (clojure.core/let
  [request (clojure.core/zipmap [:format] [format])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/QueryPictIndexValuesRequest
   request)))

(clojure.core/defn
 create-picture
 [conn pid drawable format value]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:pid :drawable :format :value]
    [pid drawable format value])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreatePictureRequest
   request)))

(clojure.core/defn
 change-picture
 [conn picture value]
 (clojure.core/let
  [request (clojure.core/zipmap [:picture :value] [picture value])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/ChangePictureRequest
   request)))

(clojure.core/defn
 set-picture-clip-rectangles
 [conn picture clip-x-origin clip-y-origin rectangles]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture :clip-x-origin :clip-y-origin :rectangles]
    [picture clip-x-origin clip-y-origin rectangles])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/SetPictureClipRectanglesRequest
   request)))

(clojure.core/defn
 free-picture
 [conn picture]
 (clojure.core/let
  [request (clojure.core/zipmap [:picture] [picture])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/FreePictureRequest
   request)))

(clojure.core/defn
 composite
 [conn
  op
  src
  mask
  dst
  src-x
  src-y
  mask-x
  mask-y
  dst-x
  dst-y
  width
  height]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op
     :src
     :mask
     :dst
     :src-x
     :src-y
     :mask-x
     :mask-y
     :dst-x
     :dst-y
     :width
     :height]
    [op
     src
     mask
     dst
     src-x
     src-y
     mask-x
     mask-y
     dst-x
     dst-y
     width
     height])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CompositeRequest
   request)))

(clojure.core/defn
 trapezoids
 [conn op src dst mask-format src-x src-y traps]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :src-x :src-y :traps]
    [op src dst mask-format src-x src-y traps])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/TrapezoidsRequest
   request)))

(clojure.core/defn
 triangles
 [conn op src dst mask-format src-x src-y triangles]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :src-x :src-y :triangles]
    [op src dst mask-format src-x src-y triangles])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/TrianglesRequest
   request)))

(clojure.core/defn
 tri-strip
 [conn op src dst mask-format src-x src-y points]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :src-x :src-y :points]
    [op src dst mask-format src-x src-y points])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/TriStripRequest
   request)))

(clojure.core/defn
 tri-fan
 [conn op src dst mask-format src-x src-y points]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :src-x :src-y :points]
    [op src dst mask-format src-x src-y points])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/TriFanRequest
   request)))

(clojure.core/defn
 create-glyph-set
 [conn gsid format]
 (clojure.core/let
  [request (clojure.core/zipmap [:gsid :format] [gsid format])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateGlyphSetRequest
   request)))

(clojure.core/defn
 reference-glyph-set
 [conn gsid existing]
 (clojure.core/let
  [request (clojure.core/zipmap [:gsid :existing] [gsid existing])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/ReferenceGlyphSetRequest
   request)))

(clojure.core/defn
 free-glyph-set
 [conn glyphset]
 (clojure.core/let
  [request (clojure.core/zipmap [:glyphset] [glyphset])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/FreeGlyphSetRequest
   request)))

(clojure.core/defn
 add-glyphs
 [conn glyphset glyphs-len glyphids glyphs data]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:glyphset :glyphs-len :glyphids :glyphs :data]
    [glyphset glyphs-len glyphids glyphs data])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/AddGlyphsRequest
   request)))

(clojure.core/defn
 free-glyphs
 [conn glyphset glyphs]
 (clojure.core/let
  [request (clojure.core/zipmap [:glyphset :glyphs] [glyphset glyphs])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/FreeGlyphsRequest
   request)))

(clojure.core/defn
 composite-glyphs8
 [conn op src dst mask-format glyphset src-x src-y glyphcmds]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :glyphset :src-x :src-y :glyphcmds]
    [op src dst mask-format glyphset src-x src-y glyphcmds])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CompositeGlyphs8Request
   request)))

(clojure.core/defn
 composite-glyphs16
 [conn op src dst mask-format glyphset src-x src-y glyphcmds]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :glyphset :src-x :src-y :glyphcmds]
    [op src dst mask-format glyphset src-x src-y glyphcmds])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CompositeGlyphs16Request
   request)))

(clojure.core/defn
 composite-glyphs32
 [conn op src dst mask-format glyphset src-x src-y glyphcmds]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:op :src :dst :mask-format :glyphset :src-x :src-y :glyphcmds]
    [op src dst mask-format glyphset src-x src-y glyphcmds])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CompositeGlyphs32Request
   request)))

(clojure.core/defn
 fill-rectangles
 [conn op dst color rects]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:op :dst :color :rects] [op dst color rects])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/FillRectanglesRequest
   request)))

(clojure.core/defn
 create-cursor
 [conn cid source x y]
 (clojure.core/let
  [request (clojure.core/zipmap [:cid :source :x :y] [cid source x y])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateCursorRequest
   request)))

(clojure.core/defn
 set-picture-transform
 [conn picture transform]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:picture :transform] [picture transform])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/SetPictureTransformRequest
   request)))

(clojure.core/defn
 query-filters
 [conn drawable]
 (clojure.core/let
  [request (clojure.core/zipmap [:drawable] [drawable])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/QueryFiltersRequest
   request)))

(clojure.core/defn
 set-picture-filter
 [conn picture filter-len filter values]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture :filter-len :filter :values]
    [picture filter-len filter values])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/SetPictureFilterRequest
   request)))

(clojure.core/defn
 create-anim-cursor
 [conn cid cursors]
 (clojure.core/let
  [request (clojure.core/zipmap [:cid :cursors] [cid cursors])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateAnimCursorRequest
   request)))

(clojure.core/defn
 add-traps
 [conn picture x-off y-off traps]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture :x-off :y-off :traps]
    [picture x-off y-off traps])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/AddTrapsRequest
   request)))

(clojure.core/defn
 create-solid-fill
 [conn picture color]
 (clojure.core/let
  [request (clojure.core/zipmap [:picture :color] [picture color])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateSolidFillRequest
   request)))

(clojure.core/defn
 create-linear-gradient
 [conn picture p1 p2 num-stops stops colors]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture :p1 :p2 :num-stops :stops :colors]
    [picture p1 p2 num-stops stops colors])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateLinearGradientRequest
   request)))

(clojure.core/defn
 create-radial-gradient
 [conn
  picture
  inner
  outer
  inner-radius
  outer-radius
  num-stops
  stops
  colors]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture
     :inner
     :outer
     :inner-radius
     :outer-radius
     :num-stops
     :stops
     :colors]
    [picture
     inner
     outer
     inner-radius
     outer-radius
     num-stops
     stops
     colors])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateRadialGradientRequest
   request)))

(clojure.core/defn
 create-conical-gradient
 [conn picture center angle num-stops stops colors]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture :center :angle :num-stops :stops :colors]
    [picture center angle num-stops stops colors])]
  (xcljb.conn-ext/send
   conn
   "RENDER"
   xcljb.gen.render-types/CreateConicalGradientRequest
   request)))

;;; Manually written.
