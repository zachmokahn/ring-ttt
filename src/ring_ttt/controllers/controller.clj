(ns ring-ttt.controllers.controller
  (:use [ring.util.response]))

(defn respond-with [page]
  (file-response page {:root "public"}))

(defn get? [response]
  (= :get (:request-method response)))
