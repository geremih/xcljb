;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xf86dri
 (:require xcljb.conn-ext xcljb.gen.xf86dri-types))

(def
 -XCLJB
 {:minor-version 1,
  :major-version 4,
  :header "xf86dri",
  :extension-multiword false,
  :extension-name "XF86Dri",
  :extension-xname "XFree86-DRI"})

(clojure.core/defn
 query-version
 [conn]
 (clojure.core/let
  [request (clojure.core/zipmap [] [])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/QueryVersionRequest
   request)))

(clojure.core/defn
 query-direct-rendering-capable
 [conn screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen] [screen])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/QueryDirectRenderingCapableRequest
   request)))

(clojure.core/defn
 open-connection
 [conn screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen] [screen])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/OpenConnectionRequest
   request)))

(clojure.core/defn
 close-connection
 [conn screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen] [screen])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/CloseConnectionRequest
   request)))

(clojure.core/defn
 get-client-driver-name
 [conn screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen] [screen])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/GetClientDriverNameRequest
   request)))

(clojure.core/defn
 create-context
 [conn screen visual context]
 (clojure.core/let
  [request
   (clojure.core/zipmap
    [:screen :visual :context]
    [screen visual context])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/CreateContextRequest
   request)))

(clojure.core/defn
 destroy-context
 [conn screen context]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen :context] [screen context])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/DestroyContextRequest
   request)))

(clojure.core/defn
 create-drawable
 [conn screen drawable]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen :drawable] [screen drawable])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/CreateDrawableRequest
   request)))

(clojure.core/defn
 destroy-drawable
 [conn screen drawable]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen :drawable] [screen drawable])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/DestroyDrawableRequest
   request)))

(clojure.core/defn
 get-drawable-info
 [conn screen drawable]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen :drawable] [screen drawable])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/GetDrawableInfoRequest
   request)))

(clojure.core/defn
 get-device-info
 [conn screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen] [screen])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/GetDeviceInfoRequest
   request)))

(clojure.core/defn
 auth-connection
 [conn screen magic]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen :magic] [screen magic])]
  (xcljb.conn-ext/send
   conn
   "XFree86-DRI"
   xcljb.gen.xf86dri-types/AuthConnectionRequest
   request)))

;;; Manually written.