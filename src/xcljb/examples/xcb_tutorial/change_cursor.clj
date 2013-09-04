;;;; 16.3

(ns xcljb.examples.xcb-tutorial.change-cursor
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]
            [xcljb.gen.xproto-types :as xproto-types])
  (:import [xcljb.gen.xproto_types ExposeEvent ButtonPressEvent KeyReleaseEvent]))

(def ^:private WIDTH 300)
(def ^:private HEIGHT 150)

(defn- gc-font-get [c screen window font-name]
  (let [font (core/gen-res-id c)
        gc (core/gen-res-id c)]
    (xproto/open-font c font (count font-name) font-name)
    (xproto/create-gc c gc window
                      {(:foreground xproto/GC) (:black-pixel screen)
                       (:background xproto/GC) (:white-pixel screen)
                       (:font xproto/GC) font})
    (xproto/close-font c font)
    gc))

(defn- button-draw [c screen window x1 y1 label]
  (let [length (count label)
        inset 2
        gc (gc-font-get c screen window "7x13")
        width (+ (* 7 length)
                 (* 2 (inc inset)))
        height (+ 13 (* 2 (inc inset)))
        points (for [[x y] [[x1 y1]
                            [(+ x1 width) y1]
                            [(+ x1 width) (- y1 height)]
                            [x1 (- y1 height)]
                            [x1 y1]]]
                 (xproto-types/->POINT x y))]
    (xproto/poly-line c
                      (:origin xproto/COORD-MODE)
                      window
                      gc
                      points)
    (xproto/image-text8 c
                        length
                        window
                        gc
                        (+ x1 inset 1)
                        (- y1 inset 1)
                        label)
    (xproto/free-gc c gc)
    nil))

(defn- text-draw [c screen window x1 y1 label]
  (let [gc (gc-font-get c screen window "7x13")]
    (xproto/image-text8 c (count label) window gc x1 y1 label)
    (xproto/free-gc c gc)
    nil))

(defn- cursor-set [c screen window cursor-id]
  (let [font (core/gen-res-id c)
        cursor (core/gen-res-id c)
        gc (core/gen-res-id c)]
    (xproto/open-font c font (count "cursor") "cursor")
    (xproto/create-glyph-cursor c cursor font font cursor-id (inc cursor-id)
                                0 0 0
                                0 0 0)
    (xproto/create-gc c gc window
                      {(:foreground xproto/GC) (:black-pixel screen)
                       (:background xproto/GC) (:white-pixel screen)
                       (:font xproto/GC) font})
    (xproto/change-window-attributes c window
                                     {(:cursor xproto/CW) cursor})
    (xproto/free-cursor c cursor)
    (xproto/close-font c font)
    nil))

(defn -main [& args]
  (let [c (conn/connect)
        setup (core/get-setup c)
        screen (-> setup (:roots) (first))
        window (core/gen-res-id c)
        hand? (atom false)]
    (xproto/create-window c
                          (:root-depth screen)
                          window (:root screen)
                          20 200 WIDTH HEIGHT
                          0 (:input-output xproto/WINDOW-CLASS)
                          (:root-visual screen)
                          {(:back-pixel xproto/CW) (:white-pixel screen)
                           (:event-mask xproto/CW) (apply bit-or
                                                          ((juxt :key-release
                                                                 :button-press
                                                                 :exposure
                                                                 :pointer-motion)
                                                           xproto/EVENT-MASK))})
    (xproto/map-window c window)
    (cursor-set c screen window 68)
    (while true
      (let [e (core/wait-event c)]
        (condp instance? e
          ExposeEvent
          (let [text1 "click here to change cursor"
                text2 "Press ESC key to exit..."]
            (button-draw c screen window
                         (long (/ (- WIDTH (* 7 (count text1))) 2))
                         (long (/ (- HEIGHT 16) 2))
                         text1)
            (text-draw c screen window 10 (- HEIGHT 10) text2))

          ButtonPressEvent
          (let [length (count "click here to change cursor")]
            (when (and (<= (/ (- WIDTH (* 7 length)) 2)
                           (:event-x e)
                           (+ (/ (- WIDTH (* 7 length)) 2)
                              (* 7 length)
                              6))
                       (<= (- (/ (- HEIGHT 16) 2) 19)
                           (:event-y e)
                           (/ (- HEIGHT 16) 2)))
              (swap! hand? not))
            (if @hand?
              (cursor-set c screen window 58)
              (cursor-set c screen window 68)))

          KeyReleaseEvent
          (when (= (:detail e) 9)       ; ESC
            (conn/disconnect c)
            (System/exit 0))

          nil)))))
