;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.screensaver
 (:require xcljb.conn-ext xcljb.gen.screensaver-types))

(def
 -XCLJB
 {:minor-version 1,
  :major-version 1,
  :header "screensaver",
  :extension-multiword false,
  :extension-name "ScreenSaver",
  :extension-xname "MIT-SCREEN-SAVER"})

(def STATE {:disabled 3, :cycle 2, :on 1, :off 0})

(def EVENT {:cycle-mask 2, :notify-mask 1})

(def KIND {:external 2, :internal 1, :blanked 0})

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
   "MIT-SCREEN-SAVER"
   xcljb.gen.screensaver-types/QueryVersionRequest
   request)))

(clojure.core/defn
 query-info
 [conn drawable]
 (clojure.core/let
  [request (clojure.core/zipmap [:drawable] [drawable])]
  (xcljb.conn-ext/send
   conn
   "MIT-SCREEN-SAVER"
   xcljb.gen.screensaver-types/QueryInfoRequest
   request)))

(clojure.core/defn
 select-input
 [conn drawable event-mask]
 (clojure.core/let
  [request
   (clojure.core/zipmap [:drawable :event-mask] [drawable event-mask])]
  (xcljb.conn-ext/send
   conn
   "MIT-SCREEN-SAVER"
   xcljb.gen.screensaver-types/SelectInputRequest
   request)))

(clojure.core/defn
 set-attributes
 [conn drawable x y width height border-width class depth visual value]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:drawable
     :x
     :y
     :width
     :height
     :border-width
     :class
     :depth
     :visual
     :value]
    [drawable x y width height border-width class depth visual value])]
  (xcljb.conn-ext/send
   conn
   "MIT-SCREEN-SAVER"
   xcljb.gen.screensaver-types/SetAttributesRequest
   request)))

(clojure.core/defn
 unset-attributes
 [conn drawable]
 (clojure.core/let
  [request (clojure.core/zipmap [:drawable] [drawable])]
  (xcljb.conn-ext/send
   conn
   "MIT-SCREEN-SAVER"
   xcljb.gen.screensaver-types/UnsetAttributesRequest
   request)))

(clojure.core/defn
 suspend
 [conn suspend]
 (clojure.core/let
  [request (clojure.core/zipmap [:suspend] [suspend])]
  (xcljb.conn-ext/send
   conn
   "MIT-SCREEN-SAVER"
   xcljb.gen.screensaver-types/SuspendRequest
   request)))

;;; Manually written.
