;;;; 10.5

(ns xcljb.examples.xcb-tutorial.x-events
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto]))

(defn- print-modifiers [mask]
  (let [mods ["Shift" "Lock" "Ctrl" "Alt" "Mod2" "Mod3" "Mod4" "Mod5" "Button1" "Button2" "Button3" "Button4" "Button5"]
        ind (.getLowestSetBit (BigInteger/valueOf mask))]
    (when-not (= ind -1)
      (println "Modifier mask:" (mods ind)))))

(defn -main [& args]
  (let [c (conn/connect)
        screen (-> c (core/get-setup) (:roots) (first))
        win (core/gen-xid c)]
    (xproto/create-window c
                          0
                          win
                          (:root screen)
                          0 0
                          150 150
                          10
                          (:input-output xproto/WINDOW-CLASS)
                          (:root-visual screen)
                          {(:back-pixel xproto/CW) (:white-pixel screen)
                           (:event-mask xproto/CW) (apply bit-or
                                                          ((juxt :exposure
                                                                 :button-press
                                                                 :button-release
                                                                 :pointer-motion
                                                                 :enter-window
                                                                 :leave-window
                                                                 :key-press
                                                                 :key-release)
                                                           xproto/EVENT-MASK))})
    (xproto/map-window c win)
    (while true
      (let [e (core/wait-event c)]
        (case (:xcljb/event e)
          :Expose
          (do
            (printf "Window %d exposed. Region to be redrawn at location (%d,%d), with dimension (%d,%d)\n" (:window e) (:x e) (:y e) (:width e) (:height e))
            (flush))

          :ButtonPress
          (do
            (print-modifiers (:state e))
            (case (:detail e)
              4 (printf "Wheel Button up in window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
              5 (printf "WheelButton down in window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
              (printf "Button %d pressed in window %d, at coordinates (%d,%d)\n" (:detail e) (:event e) (:event-x e) (:event-y e)))
            (flush))

          :ButtonRelease
          (do
            (print-modifiers (:state e))
            (printf "Button %d released in window %d, at coordinates (%d,%d)\n" (:detail e) (:event e) (:event-x e) (:event-y e))
            (flush))

          :MotionNotify
          (do
            (printf "Mouse moved in window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
            (flush))

          :EnterNotify
          (do
            (printf "Mouse entered window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
            (flush))

          :LeaveNotify
          (do
            (printf "Mouse left window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
            (flush))

          :KeyPress
          (do
            (print-modifiers (:state e))
            (println "Key pressed in window" (:event e)))

          :KeyRelease
          (do
            (print-modifiers (:state e))
            (println "Key released in window" (:event e)))

          (println "Unknown event:" e))))))
