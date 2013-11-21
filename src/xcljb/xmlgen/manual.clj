;;;; All manually written functions in this file should correspond to xcljb.xmlgen.xml2ir/SKIP.

(ns xcljb.xmlgen.manual)

(def MANUAL {"xproto" {:request #{"ConfigureWindow" "QueryTextExtents"}
                       :event #{"ClientMessage"}}
             "randr" {:event #{"Notify"}}})

(defmulti gen-request-fn identity)
(defmulti gen-event-fn identity)

(defmulti gen-request-type identity)
(defmulti gen-event-type identity)

;;; xproto

;;; ConfigureWindow

(defmethod gen-request-fn "ConfigureWindow" [_]
  `(defn ~'configure-window [~'conn ~'window ~'value]
     (xcljb.conn-internal/send ~'conn
                               xcljb.gen.xproto-types/ConfigureWindowRequest
                               {:window ~'window
                                :value ~'value})))

(defmethod gen-request-type "ConfigureWindow" [_]
  `(def ~'ConfigureWindowRequest
     (xcljb.gen-common/->Request
      nil
      12
      [(xcljb.gen-common/->Pad 1)
       (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
       (reify
         xcljb.gen-common/Measurable
         (~'sizeof [~'_ ~'vp]
           (+ 4 (* (count ~'vp) 4)))

         xcljb.gen-common/Serializable
         (~'->frame [~'_ ~'context]
           (let [~'vp (:value ~'context)]
             [:uint16
              :ubyte :ubyte
              (repeat (count ~'vp) :uint32)]))
         (~'->value [~'_ ~'context]
           (let [~'vp (:value ~'context)]
             [(reduce bit-or 0 (keys ~'vp))
              0 0
              (->> ~'vp (sort-by key) (map second))])))])))

;;; QueryTextExtents

(defmethod gen-request-fn "QueryTextExtents" [_]
  `(defn ~'query-text-extents [~'conn ~'font ~'string]
     (xcljb.conn-internal/send ~'conn
                               xcljb.gen.xproto-types/QueryTextExtentsRequest
                               {:font ~'font
                                :string ~'string})))

(defmethod gen-request-type "QueryTextExtents" [_]
  `(def ~'QueryTextExtentsRequest
     (xcljb.gen-common/->Request
      nil
      48
      [(reify
         xcljb.gen-common/Measurable
         (~'sizeof [~'_ ~'_]
           1)

         xcljb.gen-common/Serializable
         (~'->frame [~'_ ~'_]
           :ubyte)
         (~'->value [~'_ ~'context]
           (if (odd? (count (:string ~'context))) 1 0)))
       (xcljb.gen-common/->Field "font" xcljb.gen.xproto-types/FONTABLE)
       (xcljb.gen-common/->List "string" xcljb.gen.xproto-types/CHAR2B nil)])))

;;; ClientMessage

(defmethod gen-event-fn "ClientMessage" [_]
  `(defmethod xcljb.common/read-event [nil 33] [~'_ ~'_ ~'event-buf]
     (xcljb.gen-common/deserialize xcljb.gen.xproto-types/ClientMessageEvent
                                   ~'event-buf
                                   nil)))

(defmethod gen-event-type "ClientMessage" [_]
  `(def ~'ClientMessageEvent
     (xcljb.gen-common/->Event
      nil
      "ClientMessage"
      33
      false
      [(xcljb.gen-common/->Field "format" xcljb.gen.xproto-types/CARD8)
       (xcljb.gen-common/->Field "window" xcljb.gen.xproto-types/WINDOW)
       (xcljb.gen-common/->Field "ATOM" xcljb.gen.xproto-types/ATOM)
       (reify
         xcljb.gen-common/Measurable
         (~'sizeof [~'_ ~'_]
           160)

         xcljb.gen-common/Deserializable
         (~'deserialize [~'_ ~'buf ~'context]
           (xcljb.gen-common/deserialize
            (xcljb.gen-common/->List
             "data"
             (case (:format ~'context)
               8 xcljb.gen.xproto-types/CARD8
               16 xcljb.gen.xproto-types/CARD16
               32 xcljb.gen.xproto-types/CARD32)
             (xcljb.gen-common/->Value
              (case (:format ~'context)
                8 20
                16 10
                32 5)))
            ~'buf
            ~'context)))])))

;;; randr

;;; Notify

(defmethod gen-event-fn "Notify" [_]
  `(defmethod xcljb.common/read-event ["RANDR" 1] [~'_ ~'_ ~'event-buf]
     (xcljb.gen-common/deserialize xcljb.gen.randr-types/NotifyEvent
                                   ~'event-buf
                                   nil)))

(defmethod gen-event-type "Notify" [_]
  `(def ~'NotifyEvent
     (xcljb.gen-common/->Event
      "RANDR"
      "Notify"
      1
      false
      [(xcljb.gen-common/->Field "sub-code" xcljb.gen.xproto-types/CARD8)
       (reify
         xcljb.gen-common/Measurable
         (~'sizeof [~'_ ~'_]
           28)

         xcljb.gen-common/Deserializable
         (~'deserialize [~'_ ~'buf ~'context]
           (xcljb.gen-common/deserialize
            (case (:sub-code ~'context)
              0 xcljb.gen.randr-types/CrtcChange
              1 xcljb.gen.randr-types/OutputChange
              2 xcljb.gen.randr-types/OutputProperty)
            ~'buf
            ~'context)))])))
