;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xfixes
 (:require xcljb.conn-ext xcljb.gen.xfixes-types))

(def
 -XCLJB
 {:minor-version 0,
  :major-version 4,
  :header "xfixes",
  :extension-multiword false,
  :extension-name "XFixes",
  :extension-xname "XFIXES"})

(def REGION {:none 0})

(def CURSOR-NOTIFY-MASK {:display-cursor 1})

(def CURSOR-NOTIFY {:display-cursor 0})

(def
 SELECTION-EVENT-MASK
 {:selection-client-close 4,
  :selection-window-destroy 2,
  :set-selection-owner 1})

(def
 SELECTION-EVENT
 {:selection-client-close 2,
  :selection-window-destroy 1,
  :set-selection-owner 0})

(def SAVE-SET-MAPPING {:unmap 1, :map 0})

(def SAVE-SET-TARGET {:root 1, :nearest 0})

(def SAVE-SET-MODE {:delete 1, :insert 0})

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
   "XFIXES"
   xcljb.gen.xfixes-types/QueryVersionRequest
   request)))

(clojure.core/defn
 change-save-set
 [conn mode target map window]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:mode :target :map :window]
    [mode target map window])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/ChangeSaveSetRequest
   request)))

(clojure.core/defn
 select-selection-input
 [conn window selection event-mask]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:window :selection :event-mask]
    [window selection event-mask])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SelectSelectionInputRequest
   request)))

(clojure.core/defn
 select-cursor-input
 [conn window event-mask]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:window :event-mask] [window event-mask])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SelectCursorInputRequest
   request)))

(clojure.core/defn
 get-cursor-image
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/GetCursorImageRequest
   request)))

(clojure.core/defn
 create-region
 [conn region rectangles]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:region :rectangles] [region rectangles])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/CreateRegionRequest
   request)))

(clojure.core/defn
 create-region-from-bitmap
 [conn region bitmap]
 (clojure.core/let
  [request (clojure.core/zipmap [:region :bitmap] [region bitmap])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/CreateRegionFromBitmapRequest
   request)))

(clojure.core/defn
 create-region-from-window
 [conn region window kind]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:region :window :kind] [region window kind])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/CreateRegionFromWindowRequest
   request)))

(clojure.core/defn
 create-region-from-gc
 [conn region gc]
 (clojure.core/let
  [request (clojure.core/zipmap [:region :gc] [region gc])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/CreateRegionFromGCRequest
   request)))

(clojure.core/defn
 create-region-from-picture
 [conn region picture]
 (clojure.core/let
  [request (clojure.core/zipmap [:region :picture] [region picture])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/CreateRegionFromPictureRequest
   request)))

(clojure.core/defn
 destroy-region
 [conn region]
 (clojure.core/let
  [request (clojure.core/zipmap [:region] [region])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/DestroyRegionRequest
   request)))

(clojure.core/defn
 set-region
 [conn region rectangles]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:region :rectangles] [region rectangles])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SetRegionRequest
   request)))

(clojure.core/defn
 copy-region
 [conn source destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:source :destination] [source destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/CopyRegionRequest
   request)))

(clojure.core/defn
 union-region
 [conn source1 source2 destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:source1 :source2 :destination]
    [source1 source2 destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/UnionRegionRequest
   request)))

(clojure.core/defn
 intersect-region
 [conn source1 source2 destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:source1 :source2 :destination]
    [source1 source2 destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/IntersectRegionRequest
   request)))

(clojure.core/defn
 subtract-region
 [conn source1 source2 destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:source1 :source2 :destination]
    [source1 source2 destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SubtractRegionRequest
   request)))

(clojure.core/defn
 invert-region
 [conn source bounds destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:source :bounds :destination]
    [source bounds destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/InvertRegionRequest
   request)))

(clojure.core/defn
 translate-region
 [conn region dx dy]
 (clojure.core/let
  [request (clojure.core/zipmap [:region :dx :dy] [region dx dy])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/TranslateRegionRequest
   request)))

(clojure.core/defn
 region-extents
 [conn source destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:source :destination] [source destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/RegionExtentsRequest
   request)))

(clojure.core/defn
 fetch-region
 [conn region]
 (clojure.core/let
  [request (clojure.core/zipmap [:region] [region])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/FetchRegionRequest
   request)))

(clojure.core/defn
 set-gc-clip-region
 [conn gc region x-origin y-origin]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:gc :region :x-origin :y-origin]
    [gc region x-origin y-origin])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SetGCClipRegionRequest
   request)))

(clojure.core/defn
 set-window-shape-region
 [conn dest dest-kind x-offset y-offset region]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:dest :dest-kind :x-offset :y-offset :region]
    [dest dest-kind x-offset y-offset region])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SetWindowShapeRegionRequest
   request)))

(clojure.core/defn
 set-picture-clip-region
 [conn picture region x-origin y-origin]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:picture :region :x-origin :y-origin]
    [picture region x-origin y-origin])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SetPictureClipRegionRequest
   request)))

(clojure.core/defn
 set-cursor-name
 [conn cursor nbytes name]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:cursor :nbytes :name] [cursor nbytes name])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/SetCursorNameRequest
   request)))

(clojure.core/defn
 get-cursor-name
 [conn cursor]
 (clojure.core/let
  [request (clojure.core/zipmap [:cursor] [cursor])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/GetCursorNameRequest
   request)))

(clojure.core/defn
 get-cursor-image-and-name
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/GetCursorImageAndNameRequest
   request)))

(clojure.core/defn
 change-cursor
 [conn source destination]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:source :destination] [source destination])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/ChangeCursorRequest
   request)))

(clojure.core/defn
 change-cursor-by-name
 [conn src nbytes name]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:src :nbytes :name] [src nbytes name])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/ChangeCursorByNameRequest
   request)))

(clojure.core/defn
 expand-region
 [conn source destination left right top bottom]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:source :destination :left :right :top :bottom]
    [source destination left right top bottom])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/ExpandRegionRequest
   request)))

(clojure.core/defn
 hide-cursor
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/HideCursorRequest
   request)))

(clojure.core/defn
 show-cursor
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "XFIXES"
   xcljb.gen.xfixes-types/ShowCursorRequest
   request)))

;;; Manually written.
