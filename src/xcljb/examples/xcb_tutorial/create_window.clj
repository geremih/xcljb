;;;; 8

(ns xcljb.examples.xcb-tutorial.create-window
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]))

(defn -main [& args]
  (let [c (conn/connect)
        screen (-> c (core/get-setup) (:roots) (first))
        win (core/gen-res-id c)]
    (xproto/create-window c
                          (:CopyFromParent xproto/WindowClass)
                          win
                          (:root screen)
                          0 0
                          150 150
                          10
                          (:InputOutput xproto/WindowClass)
                          (:root-visual screen)
                          (core/->Valueparam [] []))
    (xproto/map-window c win)))
