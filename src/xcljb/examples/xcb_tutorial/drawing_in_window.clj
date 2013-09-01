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
        points (map #(apply xproto-types/->POINT %)
                    (partition 2 [10 10
                                  10 20
                                  20 10
                                  20 20]))
        polyline (map #(apply xproto-types/->POINT %)
                      (partition 2 [50 10
                                    5 20 ; rest of points are relative
                                    25 -20
                                    10 10]))
        segments (map #(apply xproto-types/->SEGMENT %)
                      (partition 4 [100 10 140 30
                                    110 25 130 60]))
        rectangles (map #(apply xproto-types/->RECTANGLE %)
                        (partition 4 [10 50 40 20
                                      80 50 10 40]))
        arcs (map #(apply xproto-types/->ARC %)
                  (partition 6 [10 100 60 40 0 (bit-shift-left 90 6)
                                90 100 55 40 0 (bit-shift-left 270 6)]))]
    (xproto/create-gc c
                      foreground
                      (:root screen)
                      (core/->Valueparam [(:Foreground xproto/GC)
                                          (:GraphicsExposures xproto/GC)]
                                         [(:black-pixel screen) 0]))
    ;; Create the window.
    (xproto/create-window c
                          (:CopyFromParent xproto/WindowClass)
                          win
                          (:root screen)
                          0 0
                          150 150
                          10
                          (:InputOutput xproto/WindowClass)
                          (:root-visual screen)
                          (core/->Valueparam [(:BackPixel xproto/CW)
                                              (:EventMask xproto/CW)]
                                             [(:white-pixel screen)
                                              (:Exposure xproto/EventMask)]))
    ;; Map the window on the screen.
    (xproto/map-window c win)
    (while true
      (let [e (core/wait-event c)]
        (condp instance? e
          ExposeEvent
          (do
            ;; We draw the points.
            (xproto/poly-point c
                               (:Origin xproto/CoordMode)
                               win
                               foreground
                               points)
            ;; We draw the polygonal line.
            (xproto/poly-line c
                              (:Previous xproto/CoordMode)
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
