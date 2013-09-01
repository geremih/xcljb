;;;; 11.5

(ns xcljb.examples.xcb-tutorial.draw-text
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]
            [xcljb.gen.xproto-types :as xproto-types])
  (:import [xcljb.gen.xproto_types ExposeEvent KeyReleaseEvent]))

(def ^:private WIDTH 300)
(def ^:private HEIGHT 100)

(defn- gc-font-get [c screen window font-name]
  (let [font (core/gen-res-id c)
        gc (core/gen-res-id c)]
    (xproto/open-font c font (count font-name) font-name)
    (xproto/create-gc c gc window
                      (core/->Valueparam [(:Foreground xproto/GC)
                                          (:Background xproto/GC)
                                          (:Font xproto/GC)]
                                         [(:black-pixel screen)
                                          (:white-pixel screen)
                                          font]))
    (xproto/close-font c font)
    gc))

(defn- text-draw [c screen window x1 y1 label]
  (let [gc (gc-font-get c screen window "7x13")]
    (xproto/image-text8 c (count label) window gc x1 y1 label)
    (xproto/free-gc c gc)))

(defn -main [& args]
  (let [c (conn/connect)
        setup (core/get-setup c)
        screen (-> setup (:roots) (first))
        window (core/gen-res-id c)]
    (xproto/create-window c
                          (:root-depth screen)
                          window (:root screen)
                          20 200 WIDTH HEIGHT
                          0 (:InputOutput xproto/WindowClass)
                          (:root-visual screen)
                          (core/->Valueparam [(:BackPixel xproto/CW)
                                              (:EventMask xproto/CW)]
                                             [(:white-pixel screen)
                                              (apply bit-or
                                                     ((juxt
                                                       :KeyRelease
                                                       :ButtonPress
                                                       :Exposure
                                                       :PointerMotion)
                                                      xproto/EventMask))]))
    (xproto/map-window c window)
    (while true
      (let [e (core/wait-event c)]
        (condp instance? e
          ExposeEvent
          (text-draw c screen window 10 (- HEIGHT 10) "Press ESC key to exit...")

          KeyReleaseEvent
          (when (= (:detail e) 9)       ; ESC
            (conn/disconnect c)
            (System/exit 0))

          nil)))))
