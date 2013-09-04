;;;; 12.2

(ns xcljb.examples.xcb-tutorial.set-window-icon-names
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]))

(defn -main [& args]
  (let [c (conn/connect)
        screen (-> c (core/get-setup) (:roots) (first))
        win (core/gen-res-id c)
        title "Hello World !"
        title-icon "Hello World ! (iconified)"]
    (xproto/create-window c
                          0
                          win
                          (:root screen)
                          0 0
                          250 150
                          10
                          (:input-output xproto/WINDOW-CLASS)
                          (:root-visual screen)
                          (core/->Valueparam [] []))
    ;; Set the title of the window.
    (xproto/change-property c (:replace xproto/PROP-MODE) win
                            (:wm-name xproto/ATOM) (:string xproto/ATOM) 8
                            (count title) (map int title))
    ;; Set the title of the window icon.
    (xproto/change-property c (:replace xproto/PROP-MODE) win
                            (:wm-icon-name xproto/ATOM) (:string xproto/ATOM) 8
                            (count title-icon) (map int title-icon))
    ;; Map the window on the screen.
    (xproto/map-window c win)))
