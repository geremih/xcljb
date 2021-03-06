;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xinerama
 (:require xcljb.conn-ext xcljb.gen.xinerama-types))

(def
 -XCLJB
 {:minor-version 1,
  :major-version 1,
  :header "xinerama",
  :extension-multiword false,
  :extension-name "Xinerama",
  :extension-xname "XINERAMA"})

(clojure.core/defn
 query-version
 [conn major minor]
 (clojure.core/let
  [request (clojure.core/zipmap [:major :minor] [major minor])]
  (xcljb.conn-ext/send
   conn
   "XINERAMA"
   xcljb.gen.xinerama-types/QueryVersionRequest
   request)))

(clojure.core/defn
 get-state
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "XINERAMA"
   xcljb.gen.xinerama-types/GetStateRequest
   request)))

(clojure.core/defn
 get-screen-count
 [conn window]
 (clojure.core/let
  [request (clojure.core/zipmap [:window] [window])]
  (xcljb.conn-ext/send
   conn
   "XINERAMA"
   xcljb.gen.xinerama-types/GetScreenCountRequest
   request)))

(clojure.core/defn
 get-screen-size
 [conn window screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:window :screen] [window screen])]
  (xcljb.conn-ext/send
   conn
   "XINERAMA"
   xcljb.gen.xinerama-types/GetScreenSizeRequest
   request)))

(clojure.core/defn
 is-active
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "XINERAMA"
   xcljb.gen.xinerama-types/IsActiveRequest
   request)))

(clojure.core/defn
 query-screens
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "XINERAMA"
   xcljb.gen.xinerama-types/QueryScreensRequest
   request)))

;;; Manually written.
