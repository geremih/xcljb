;;;; 11.5

(ns xcljb.examples.xcb-tutorial.draw-text
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]
            [xcljb.gen.xproto-types :as xproto-types]))

(def ^:private WIDTH 300)
(def ^:private HEIGHT 100)

(defn- gc-font-get [c screen window font-name]
  (let [font (core/gen-xid c)
        gc (core/gen-xid c)]
    (xproto/open-font c font (count font-name) font-name)
    (xproto/create-gc c gc window
                      {(:foreground xproto/GC) (:black-pixel screen)
                       (:background xproto/GC) (:white-pixel screen)
                       (:font xproto/GC) font})
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
        window (core/gen-xid c)]
    (xproto/create-window c
                          (:root-depth screen)
                          window (:root screen)
                          20 200 WIDTH HEIGHT
                          0 (:input-output xproto/WINDOW-CLASS)
                          (:root-visual screen)
                          {(:back-pixel xproto/CW) (:white-pixel screen)
                           (:event-mask xproto/CW) (apply bit-or
                                                          ((juxt
                                                            :key-release
                                                            :button-press
                                                            :exposure
                                                            :pointer-motion)
                                                           xproto/EVENT-MASK))})
    (xproto/map-window c window)
    (while true
      (let [e (core/wait-event c)]
        (case (:xcljb/event e)
          :Expose
          (text-draw c screen window 10 (- HEIGHT 10) "Press ESC key to exit...")

          :KeyRelease
          (when (= (:detail e) 9)       ; ESC
            (conn/disconnect c)
            (System/exit 0))

          nil)))))
