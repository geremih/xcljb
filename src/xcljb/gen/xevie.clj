;;;; This file is automatically generated. DO NOT MODIFY.

(clojure.core/ns
 xcljb.gen.xevie
 (:require xcljb.conn-ext xcljb.gen.xevie-types))

(def
 -XCLJB
 {:minor-version 0,
  :major-version 1,
  :header "xevie",
  :extension-multiword false,
  :extension-name "Xevie",
  :extension-xname "XEVIE"})

(def DATATYPE {:modified 1, :unmodified 0})

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
   "XEVIE"
   xcljb.gen.xevie-types/QueryVersionRequest
   request)))

(clojure.core/defn
 start
 [conn screen]
 (clojure.core/let
  [request (clojure.core/zipmap [:screen] [screen])]
  (xcljb.conn-ext/send
   conn
   "XEVIE"
   xcljb.gen.xevie-types/StartRequest
   request)))

(clojure.core/defn
 end
 [conn cmap]
 (clojure.core/let
  [request (clojure.core/zipmap [:cmap] [cmap])]
  (xcljb.conn-ext/send
   conn
   "XEVIE"
   xcljb.gen.xevie-types/EndRequest
   request)))

(clojure.core/defn
 send
 [conn event data-type]
 (clojure.core/let
  [request (clojure.core/zipmap [:event :data-type] [event data-type])]
  (xcljb.conn-ext/send
   conn
   "XEVIE"
   xcljb.gen.xevie-types/SendRequest
   request)))

(clojure.core/defn
 select-input
 [conn event-mask]
 (clojure.core/let
  [request (clojure.core/zipmap [:event-mask] [event-mask])]
  (xcljb.conn-ext/send
   conn
   "XEVIE"
   xcljb.gen.xevie-types/SelectInputRequest
   request)))

;;; Manually written.
