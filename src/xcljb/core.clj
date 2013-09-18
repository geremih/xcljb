(ns xcljb.core)

(defn get-setup
  "Access the data returned by the server during connection setup. See
  the X protocol specification for more details."
  [conn]
  (:setup @conn))

(defn wait-event
  "Returns the next event from connection conn. Blocks until an event
  arrives."
  [conn]
  (.take (:events @conn)))

(defn poll-event
  "Returns the next event from connection conn, or nil if no event
  arrives before timeout (in milliseconds)."
  [conn timeout]
  (.poll (:events @conn) timeout java.util.concurrent.TimeUnit/MILLISECONDS))

(defn- max-res-id [conn]
  (let [res-mask (-> conn (get-setup) (:resource-id-mask))
        res-shifts (.getLowestSetBit (BigInteger/valueOf res-mask))]
    (assert (not (neg? res-shifts)))
    (bit-shift-right res-mask res-shifts)))

;;; TODO: Use XC-MISC extension to generate resource ids.
(defn gen-xid
  "Allocates an XID for a new object."
  [conn]
  (let [res-base (-> conn (get-setup) (:resource-id-base))
        res-mask (-> conn (get-setup) (:resource-id-mask))
        res-shifts (.getLowestSetBit (BigInteger/valueOf res-mask))
        res-id (inc (:res-id @conn))]
    (assert (<= res-id (max-res-id conn)) (str "Unable to generate new resource id."))
    (swap! conn assoc :res-id res-id)
    (assert (not (neg? res-shifts)))
    (bit-or (bit-shift-left res-id res-shifts)
            res-base)))
