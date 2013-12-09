;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.randr
 (:require xcljb.conn-ext xcljb.gen.randr-types))

(def
 -XCLJB
 {:minor-version 3,
  :major-version 1,
  :header "randr",
  :extension-multiword false,
  :extension-name "RandR",
  :extension-xname "RANDR"})

(def NOTIFY {:output-property 2, :output-change 1, :crtc-change 0})

(def CONNECTION {:unknown 2, :disconnected 1, :connected 0})

(def
 MODE-FLAG
 {:double-scan 32,
  :double-clock 4096,
  :pixel-multiplex 2048,
  :hskew-present 512,
  :vsync-positive 4,
  :hsync-negative 2,
  :halve-clock 8192,
  :hsync-positive 1,
  :csync-positive 128,
  :bcast 1024,
  :vsync-negative 8,
  :interlace 16,
  :csync 64,
  :csync-negative 256})

(def
 NOTIFY-MASK
 {:output-property 8,
  :output-change 4,
  :crtc-change 2,
  :screen-change 1})

(def
 SET-CONFIG
 {:failed 3, :invalid-time 2, :invalid-config-time 1, :success 0})

(def
 ROTATION
 {:reflect-y 32,
  :reflect-x 16,
  :rotate-270 8,
  :rotate-180 4,
  :rotate-90 2,
  :rotate-0 1})

(clojure.core/defn
 query-version
 [conn major-version minor-version]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:major-version :minor-version]
    [major-version minor-version])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/QueryVersionRequest
   request)))

(clojure.core/defn
 set-screen-config
 [conn window timestamp config-timestamp size-id rotation rate]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:window :timestamp :config-timestamp :size-id :rotation :rate]
    [window timestamp config-timestamp size-id rotation rate])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetScreenConfigRequest
   request)))

(clojure.core/defn
 select-input
 [conn window enable]
 (clojure.core/let
  [request (clojure.core/zipmap [:window :enable] [window enable])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SelectInputRequest
   request)))

(clojure.core/defn
 get-screen-info
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetScreenInfoRequest
   request)))

(clojure.core/defn
 get-screen-size-range
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetScreenSizeRangeRequest
   request)))

(clojure.core/defn
 set-screen-size
 [conn window width height mm-width mm-height]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:window :width :height :mm-width :mm-height]
    [window width height mm-width mm-height])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetScreenSizeRequest
   request)))

(clojure.core/defn
 get-screen-resources
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetScreenResourcesRequest
   request)))

(clojure.core/defn
 get-output-info
 [conn output config-timestamp]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:output :config-timestamp]
    [output config-timestamp])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetOutputInfoRequest
   request)))

(clojure.core/defn
 list-output-properties
 [conn output]
 (clojure.core/let
  [request (clojure.core/zipmap [:output] [output])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/ListOutputPropertiesRequest
   request)))

(clojure.core/defn
 query-output-property
 [conn output property]
 (clojure.core/let
  [request (clojure.core/zipmap [:output :property] [output property])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/QueryOutputPropertyRequest
   request)))

(clojure.core/defn
 configure-output-property
 [conn output property pending range values]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:output :property :pending :range :values]
    [output property pending range values])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/ConfigureOutputPropertyRequest
   request)))

(clojure.core/defn
 change-output-property
 [conn output property type format mode num-units data]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:output :property :type :format :mode :num-units :data]
    [output property type format mode num-units data])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/ChangeOutputPropertyRequest
   request)))

(clojure.core/defn
 delete-output-property
 [conn output property]
 (clojure.core/let
  [request (clojure.core/zipmap [:output :property] [output property])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/DeleteOutputPropertyRequest
   request)))

(clojure.core/defn
 get-output-property
 [conn output property type long-offset long-length delete pending]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:output
     :property
     :type
     :long-offset
     :long-length
     :delete
     :pending]
    [output property type long-offset long-length delete pending])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetOutputPropertyRequest
   request)))

(clojure.core/defn
 create-mode
 [conn window mode-info name]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:window :mode-info :name]
    [window mode-info name])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/CreateModeRequest
   request)))

(clojure.core/defn
 destroy-mode
 [conn mode]
 (clojure.core/let
  [request (clojure.core/zipmap [:mode] [mode])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/DestroyModeRequest
   request)))

(clojure.core/defn
 add-output-mode
 [conn output mode]
 (clojure.core/let
  [request (clojure.core/zipmap [:output :mode] [output mode])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/AddOutputModeRequest
   request)))

(clojure.core/defn
 delete-output-mode
 [conn output mode]
 (clojure.core/let
  [request (clojure.core/zipmap [:output :mode] [output mode])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/DeleteOutputModeRequest
   request)))

(clojure.core/defn
 get-crtc-info
 [conn crtc config-timestamp]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:crtc :config-timestamp]
    [crtc config-timestamp])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetCrtcInfoRequest
   request)))

(clojure.core/defn
 set-crtc-config
 [conn crtc timestamp config-timestamp x y mode rotation outputs]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:crtc :timestamp :config-timestamp :x :y :mode :rotation :outputs]
    [crtc timestamp config-timestamp x y mode rotation outputs])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetCrtcConfigRequest
   request)))

(clojure.core/defn
 get-crtc-gamma-size
 [conn crtc]
 (clojure.core/let
  [request (clojure.core/zipmap [:crtc] [crtc])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetCrtcGammaSizeRequest
   request)))

(clojure.core/defn
 get-crtc-gamma
 [conn crtc]
 (clojure.core/let
  [request (clojure.core/zipmap [:crtc] [crtc])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetCrtcGammaRequest
   request)))

(clojure.core/defn
 set-crtc-gamma
 [conn crtc size red green blue]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:crtc :size :red :green :blue]
    [crtc size red green blue])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetCrtcGammaRequest
   request)))

(clojure.core/defn
 get-screen-resources-current
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetScreenResourcesCurrentRequest
   request)))

(clojure.core/defn
 set-crtc-transform
 [conn crtc transform filter-len filter-name filter-params]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:crtc :transform :filter-len :filter-name :filter-params]
    [crtc transform filter-len filter-name filter-params])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetCrtcTransformRequest
   request)))

(clojure.core/defn
 get-crtc-transform
 [conn crtc]
 (clojure.core/let
  [request (clojure.core/zipmap [:crtc] [crtc])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetCrtcTransformRequest
   request)))

(clojure.core/defn
 get-panning
 [conn crtc]
 (clojure.core/let
  [request (clojure.core/zipmap [:crtc] [crtc])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetPanningRequest
   request)))

(clojure.core/defn
 set-panning
 [conn
  crtc
  timestamp
  left
  top
  width
  height
  track-left
  track-top
  track-width
  track-height
  border-left
  border-top
  border-right
  border-bottom]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:crtc
     :timestamp
     :left
     :top
     :width
     :height
     :track-left
     :track-top
     :track-width
     :track-height
     :border-left
     :border-top
     :border-right
     :border-bottom]
    [crtc
     timestamp
     left
     top
     width
     height
     track-left
     track-top
     track-width
     track-height
     border-left
     border-top
     border-right
     border-bottom])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetPanningRequest
   request)))

(clojure.core/defn
 set-output-primary
 [conn window output]
 (clojure.core/let
  [request (clojure.core/zipmap [:window :output] [window output])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/SetOutputPrimaryRequest
   request)))

(clojure.core/defn
 get-output-primary
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "RANDR"
   xcljb.gen.randr-types/GetOutputPrimaryRequest
   request)))

;;; Manually written.