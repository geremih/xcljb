;;;; 9.3

(ns xcljb.examples.xcb-tutorial.drawing-in-window
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]
            [xcljb.gen.xproto-types :as xproto-types])
  (:import [xcljb.gen.xproto_types ExposeEvent]))

(defn -main [& args]
  (let [c (conn/connect)
        screen (-> c (core/get-setup) (:roots) (first))
        win (core/gen-res-id c)
        foreground (core/gen-res-id c)
        points (for [[x y] [[10 10]
                            [10 20]
                            [20 10]
                            [20 20]]]
                 (xproto-types/->POINT x y))
        polyline (for [[x y] [[50 10
                               5 20     ; rest of points are relative
                               25 -20
                               10 10]]]
                   (xproto-types/->POINT x y))
        segments (for [[x1 y1 x2 y2] [[100 10 140 30]
                                      [110 25 130 60]]]
                   (xproto-types/->SEGMENT x1 y1 x2 y2))
        rectangles (for [[x y width height] [[10 50 40 20]
                                             [80 50 10 40]]]
                     (xproto-types/->RECTANGLE x y width height))
        arcs (for [[x y width height angle1 angle2]
                   [[10 100 60 40 0 (bit-shift-left 90 6)]
                    [90 100 55 40 0 (bit-shift-left 270 6)]]]
               (xproto-types/->ARC x y width height angle1 angle2))]
    (xproto/create-gc c
                      foreground
                      (:root screen)
                      {(:foreground xproto/GC) (:black-pixel screen)
                       (:graphics-exposures xproto/GC) 0})
    ;; Create the window.
    (xproto/create-window c
                          (:copy-from-parent xproto/WINDOW-CLASS)
                          win
                          (:root screen)
                          0 0
                          150 150
                          10
                          (:input-output xproto/WINDOW-CLASS)
                          (:root-visual screen)
                          {(:back-pixel xproto/CW) (:white-pixel screen)
                           (:event-mask xproto/CW) (:exposure xproto/EVENT-MASK)})
    ;; Map the window on the screen.
    (xproto/map-window c win)
    (while true
      (let [e (core/wait-event c)]
        (condp instance? e
          ExposeEvent
          (do
            ;; We draw the points.
            (xproto/poly-point c
                               (:origin xproto/COORD-MODE)
                               win
                               foreground
                               points)
            ;; We draw the polygonal line.
            (xproto/poly-line c
                              (:previous xproto/COORD-MODE)
                              win
                              foreground
                              polyline)
            ;; We draw the segements.
            (xproto/poly-segment c
                                 win
                                 foreground
                                 segments)
            ;; We draw the rectangles.
            (xproto/poly-rectangle c
                                   win
                                   foreground
                                   rectangles)
            ;; We draw the arcs.
            (xproto/poly-arc c
                             win
                             foreground
                             arcs))

          ;; Unknown event type, ignore it.
          nil)))))
