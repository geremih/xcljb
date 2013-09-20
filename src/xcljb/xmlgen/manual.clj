;;;; All manually written functions in this file should correspond to xcljb.xmlgen.xml2ir/SKIP.

(ns xcljb.xmlgen.manual)

(def MANUAL {"xproto" {:request #{"ConfigureWindow" "QueryTextExtents"}
                       :reply #{"QueryTextExtents"}
                       :event #{"ClientMessage"}}})

(defmulti gen-request-fn identity)
(defmulti gen-reply-fn identity)
(defmulti gen-event-fn identity)

(defmulti gen-request-type identity)
(defmulti gen-reply-type identity)
(defmulti gen-event-type identity)

;;; xproto

;;; ConfigureWindow

(defmethod gen-request-fn "ConfigureWindow" [_]
  `(defn ~(symbol "configure-window") [conn# window# value#]
     (xcljb.conn-internal/send conn#
                               (xcljb.gen.xproto-types/->ConfigureWindowRequest
                                12 window# value#))))

(defmethod gen-request-type "ConfigureWindow" [_]
  (let [s-this (symbol "this")
        [[s-opcode s-window s-value]
         [k-opcode k-window k-value]] ((juxt #(map symbol %) #(map keyword %))
                                       ["opcode" "window" "value"])]
    `(defrecord ~(symbol "ConfigureWindowRequest") [~s-opcode ~s-window ~s-value]
       xcljb.common/Measurable
       (~(symbol "sizeof") [~s-this]
        (+ 3
          1
          (.sizeof xcljb.gen.xproto-types/WINDOW)
          (.sizeof xcljb.gen.xproto-types/CARD16)
          2
          (* 4
             (count (~k-value ~s-this)))))

       xcljb.common/Serializable
       (~(symbol "to-frame") [~s-this]
        [:ubyte
         :ubyte
         :uint16
         (.to-frame xcljb.gen.xproto-types/WINDOW)
         :uint16
         :uint16
         (repeat (count (~k-value ~s-this))
                 :uint32)])
       (~(symbol "to-value") [~s-this]
        (let [vp# (xcljb.common/valueparam->value (~k-value ~s-this))]
          [(~k-opcode ~s-this)
           0
           (+ 3 (count (~k-value ~s-this)))
           (~k-window ~s-this)
           (first vp#)
           0
           (second vp#)])))))

;;; QueryTextExtents

(defmethod gen-request-fn "QueryTextExtents" [_]
  `(defn ~(symbol "query-text-extents") [conn# font# string#]
     (xcljb.conn-internal/send conn#
                               (xcljb.gen.xproto-types/->QueryTextExtentsRequest
                                48 font# string#))))

(defmethod gen-request-type "QueryTextExtents" [_]
  (let [s-this (symbol "this")
        [[s-opcode s-font s-string]
         [k-opcode k-font k-string]] ((juxt #(map symbol %) #(map keyword %))
                                      ["opcode" "font" "string"])]
    `(defrecord ~(symbol "QueryTextExtentsRequest") [~s-opcode ~s-font ~s-string]
       xcljb.common/Measurable
       (~(symbol "sizeof") [~s-this]
        (+ 3
          1
          (.sizeof xcljb.gen.xproto-types/FONTABLE)
          (.sizeof (~k-string ~s-this))))

       xcljb.common/Serializable
       (~(symbol "to-frame") [~s-this]
        [:ubyte
         :ubyte
         :uint16
         (.to-frame xcljb.gen.xproto-types/FONTABLE)
         (map #(.to-frame %) (~k-string ~s-this))
         (repeat (xcljb.common/padding (.sizeof ~s-this)) :byte)])
       (~(symbol "to-value") [~s-this]
        [(~k-opcode ~s-this)
         (if (odd? (count (~k-string ~s-this))) 1 0)
         (int (Math/ceil (/ (.sizeof ~s-this) 4)))
         (~k-font ~s-this)
         (map #(.to-value %) (~k-string ~s-this))
         (repeat (xcljb.common/padding (.sizeof ~s-this)) 0)]))))

(defmethod gen-reply-fn "QueryTextExtents" [_]
  (let [s-_ (symbol "_")]
    `(defmethod xcljb.common/read-reply 48 [~s-_ ch# ~s-_ draw-direction#]
       (let [font-ascent# (.read-type xcljb.gen.xproto-types/INT16 ch#)
             font-descent# (.read-type xcljb.gen.xproto-types/INT16 ch#)
             overall-ascent# (.read-type xcljb.gen.xproto-types/INT16 ch#)
             overall-descent# (.read-type xcljb.gen.xproto-types/INT16 ch#)
             overall-width# (.read-type xcljb.gen.xproto-types/INT32 ch#)
             overall-left# (.read-type xcljb.gen.xproto-types/INT32 ch#)
             overall-right# (.read-type xcljb.gen.xproto-types/INT32 ch#)
             ~s-_ (xcljb.common/read-pad ch# 4)]
         (xcljb.gen.xproto-types/->QueryTextExtentsReply
          draw-direction# font-ascent# font-descent# overall-ascent#
          overall-descent# overall-width# overall-left# overall-right#)))))

(defmethod gen-reply-type "QueryTextExtents" [_]
  `(defrecord ~(symbol "QueryTextExtentsReply")
       [~@(map symbol ["draw-direction" "font-ascent" "font-descent"
                       "overall-ascent" "overall-descent" "overall-width"
                       "overall-left" "overall-right"])]))

;;; ClientMessage

(defmethod gen-event-fn "ClientMessage" [_]
  (let [s-_ (symbol "_")]
    `(defmethod xcljb.common/read-event 33 [~s-_ ch#]
       (let [format# (.read-type xcljb.gen.xproto-types/CARD8 ch#)
             seq-num# (xcljb.common/read-bytes ch# 2)
             window# (.read-type xcljb.gen.xproto-types/WINDOW ch#)
             type# (.read-type xcljb.gen.xproto-types/ATOM ch#)
             data# (case format#
                     8 (doall (repeatedly 20 #(.read-type xcljb.gen.xproto-types/CARD8 ch#)))
                     16 (doall (repeatedly 10 #(.read-type xcljb.gen.xproto-types/CARD16 ch#)))
                     32 (doall (repeatedly 5 #(.read-type xcljb.gen.xproto-types/CARD32 ch#))))]
         {:seq-num seq-num#
          :event (xcljb.gen.xproto-types/->ClientMessageEvent
                  format# window# type# data#)}))))

(defmethod gen-event-type "ClientMessage" [_]
  `(defrecord ~(symbol "ClientMessageEvent")
       [~@(map symbol ["format" "window" "type" "data"])]))
