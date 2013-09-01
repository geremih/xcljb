;;;; 10.5

(ns xcljb.examples.xcb-tutorial.x-events
  (:require [xcljb.conn :as conn]
            [xcljb.core :as core]
            [xcljb.gen.xproto :as xproto])
  (:import [xcljb.gen.xproto_types ExposeEvent ButtonPressEvent ButtonReleaseEvent MotionNotifyEvent EnterNotifyEvent LeaveNotifyEvent KeyPressEvent KeyReleaseEvent]))

(defn- print-modifiers [mask]
  (let [mods ["Shift" "Lock" "Ctrl" "Alt" "Mod2" "Mod3" "Mod4" "Mod5" "Button1" "Button2" "Button3" "Button4" "Button5"]
        ind (.getLowestSetBit (BigInteger/valueOf mask))]
    (when-not (= ind -1)
      (println "Modifier mask:" (mods ind)))))

(defn -main [& args]
  (let [c (conn/connect)
        screen (-> c (core/get-setup) (:roots) (first))
        win (core/gen-res-id c)]
    (xproto/create-window c
                          0
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
                                              (reduce #(bit-or %1 (%2 xproto/EventMask))
                                                      0
                                                      [:Exposure :ButtonPress :ButtonRelease :PointerMotion :EnterWindow :LeaveWindow :KeyPress :KeyRelease])]))
    (xproto/map-window c win)
    (while true
      (let [e (core/wait-event c)]
        (condp instance? e
          ExposeEvent
          (do
            (printf "Window %d exposed. Region to be redrawn at location (%d,%d), with dimension (%d,%d)\n" (:window e) (:x e) (:y e) (:width e) (:height e))
            (flush))

          ButtonPressEvent
          (do
            (print-modifiers (:state e))
            (case (:detail e)
              4 (printf "Wheel Button up in window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
              5 (printf "WheelButton down in window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
              (printf "Button %d pressed in window %d, at coordinates (%d,%d)\n" (:detail e) (:event e) (:event-x e) (:event-y e)))
            (flush))

          ButtonReleaseEvent
          (do
            (print-modifiers (:state e))
            (printf "Button %d released in window %d, at coordinates (%d,%d)\n" (:detail e) (:event e) (:event-x e) (:event-y e))
            (flush))

          MotionNotifyEvent
          (do
            (printf "Mouse moved in window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
            (flush))

          EnterNotifyEvent
          (do
            (printf "Mouse entered window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
            (flush))

          LeaveNotifyEvent
          (do
            (printf "Mouse left window %d, at coordinates (%d,%d)\n" (:event e) (:event-x e) (:event-y e))
            (flush))

          KeyPressEvent
          (do
            (print-modifiers (:state e))
            (println "Key pressed in window" (:event e)))

          KeyReleaseEvent
          (do
            (print-modifiers (:state e))
            (println "Key released in window" (:event e)))

          (println "Unknown event:" e))))))
