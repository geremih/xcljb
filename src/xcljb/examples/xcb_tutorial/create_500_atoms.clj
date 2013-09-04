;;;; 4.2

(ns xcljb.examples.xcb-tutorial.create-500-atoms
  (:require [xcljb.conn :as conn]
            [xcljb.gen.xproto :as xproto]))

(defn- create-atoms [c n]
  (let [as (for [i (range n)]
             (let [name (str "NAME" i)]
               (xproto/intern-atom c false (count name) name)))]
    (doseq [a as]
      @a)
    nil))

(defn -main [& args]
  (let [c (conn/connect)]
    (time (create-atoms c 500))))
