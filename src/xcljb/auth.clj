(ns xcljb.auth
  (:require [xcljb.gen-common :as common]))

(defn- parse-xauthority []
  (when-let [auth-file (System/getenv "XAUTHORITY")]
    (with-open [ins (clojure.java.io/input-stream auth-file)
                ch (java.nio.channels.Channels/newChannel ins)]
      (let [family (common/read-bytes ch 2)
            addrlen (common/read-bytes ch 2)
            addr (common/read-string ch addrlen)
            numlen (common/read-bytes ch 2)
            num (common/read-string ch numlen)
            namelen (common/read-bytes ch 2)
            name (common/read-string ch namelen)
            datalen (common/read-bytes ch 2)
            data (doall (repeatedly datalen #(common/read-bytes ch 1)))]
        {:family family
         :addr addr
         :num num
         :name name
         :data data}))))

(defn get-auth []
  (parse-xauthority))
