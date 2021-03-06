;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.shape
 (:require xcljb.conn-ext xcljb.gen.shape-types))

(def
 -XCLJB
 {:minor-version 1,
  :major-version 1,
  :header "shape",
  :extension-multiword false,
  :extension-name "Shape",
  :extension-xname "SHAPE"})

(def SK {:input 2, :clip 1, :bounding 0})

(def SO {:invert 4, :subtract 3, :intersect 2, :union 1, :set 0})

(clojure.core/defn
 query-version
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/QueryVersionRequest
   request)))

(clojure.core/defn
 rectangles
 [conn
  operation
  destination-kind
  ordering
  destination-window
  x-offset
  y-offset
  rectangles]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:operation
     :destination-kind
     :ordering
     :destination-window
     :x-offset
     :y-offset
     :rectangles]
    [operation
     destination-kind
     ordering
     destination-window
     x-offset
     y-offset
     rectangles])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/RectanglesRequest
   request)))

(clojure.core/defn
 mask
 [conn
  operation
  destination-kind
  destination-window
  x-offset
  y-offset
  source-bitmap]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:operation
     :destination-kind
     :destination-window
     :x-offset
     :y-offset
     :source-bitmap]
    [operation
     destination-kind
     destination-window
     x-offset
     y-offset
     source-bitmap])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/MaskRequest
   request)))

(clojure.core/defn
 combine
 [conn
  operation
  destination-kind
  source-kind
  destination-window
  x-offset
  y-offset
  source-window]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:operation
     :destination-kind
     :source-kind
     :destination-window
     :x-offset
     :y-offset
     :source-window]
    [operation
     destination-kind
     source-kind
     destination-window
     x-offset
     y-offset
     source-window])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/CombineRequest
   request)))

(clojure.core/defn
 offset
 [conn destination-kind destination-window x-offset y-offset]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:destination-kind :destination-window :x-offset :y-offset]
    [destination-kind destination-window x-offset y-offset])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/OffsetRequest
   request)))

(clojure.core/defn
 query-extents
 [conn destination-window]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:destination-window] [destination-window])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/QueryExtentsRequest
   request)))

(clojure.core/defn
 select-input
 [conn destination-window enable]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:destination-window :enable]
    [destination-window enable])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/SelectInputRequest
   request)))

(clojure.core/defn
 input-selected
 [conn destination-window]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:destination-window] [destination-window])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/InputSelectedRequest
   request)))

(clojure.core/defn
 get-rectangles
 [conn window source-kind]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:window :source-kind] [window source-kind])]
  (xcljb.conn-ext/send
   conn
   "SHAPE"
   xcljb.gen.shape-types/GetRectanglesRequest
   request)))

;;; Manually written.
