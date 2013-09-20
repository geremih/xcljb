(ns xcljb.core)

(defn get-setup
  "Access the data returned by the server during connection setup. See
  the X protocol specification for more details."
  [conn]
  (:setup conn))

(defn wait-event
  "Returns the next event from connection conn. Blocks until an event
  arrives."
  [conn]
  (.take (:events conn)))

(defn poll-event
  "Returns the next event from connection conn, or nil if no event
  arrives before timeout (in milliseconds)."
  [conn timeout]
  (.poll (:events conn) timeout java.util.concurrent.TimeUnit/MILLISECONDS))

;;; TODO: Use XC-MISC extension to generate more XIDs when it runs out.
(defn gen-xid
  "Allocates an XID for a new object."
  [conn]
  (dosync
   (let [xid (first (ensure (:xids conn)))]
     (assert xid "Unable to generate new XID.")
     (alter (:xids conn) rest)
     xid)))
