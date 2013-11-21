(ns xcljb.auth
  (:require [gloss core io])
  (:import java.nio.ByteBuffer))

(defn- read-bytes [ch bytes]
  (let [buf (ByteBuffer/allocate bytes)]
    (.read ch buf)
    (.rewind buf)
    (gloss.io/decode (case bytes
                       1 :ubyte
                       2 :uint16
                       4 :uint32)
                     buf)))

(defn- read-string [ch length]
  (let [buf (ByteBuffer/allocate length)]
    (.read ch buf)
    (.rewind buf)
    (gloss.io/decode (gloss.core/string :ascii) buf)))

(defn- parse-xauthority []
  (when-let [auth-file (System/getenv "XAUTHORITY")]
    (with-open [ins (clojure.java.io/input-stream auth-file)
                ch (java.nio.channels.Channels/newChannel ins)]
      (let [family (read-bytes ch 2)
            addrlen (read-bytes ch 2)
            addr (read-string ch addrlen)
            numlen (read-bytes ch 2)
            num (read-string ch numlen)
            namelen (read-bytes ch 2)
            name (read-string ch namelen)
            datalen (read-bytes ch 2)
            data (doall (repeatedly datalen #(read-bytes ch 1)))]
        {:family family
         :addr addr
         :num num
         :name name
         :data data}))))

(defn get-auth []
  (parse-xauthority))
