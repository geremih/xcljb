(ns xcljb.core)

(defn get-setup [conn]
  (:setup @conn))

(defn wait-event [conn]
  (.take (:events @conn)))

(defn poll-event [conn timeout unit]
  (.poll (:events @conn) timeout unit))

(defn- max-res-id [conn]
  (let [res-mask (-> conn (get-setup) (:resource-id-mask))
        res-shifts (.getLowestSetBit (BigInteger/valueOf res-mask))]
    (assert (not (neg? res-shifts)))
    (bit-shift-right res-mask res-shifts)))

;;; TODO: Use XC-MISC extension to generate resource ids.
(defn gen-res-id [conn]
  (let [res-base (-> conn (get-setup) (:resource-id-base))
        res-mask (-> conn (get-setup) (:resource-id-mask))
        res-shifts (.getLowestSetBit (BigInteger/valueOf res-mask))
        res-id (inc (:res-id @conn))]
    (assert (<= res-id (max-res-id conn)) (str "Unable to generate new resource id."))
    (swap! conn assoc :res-id res-id)
    (assert (not (neg? res-shifts)))
    (bit-or (bit-shift-left res-id res-shifts)
            res-base)))
