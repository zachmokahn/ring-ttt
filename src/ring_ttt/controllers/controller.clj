(ns ring-ttt.controllers.controller
  (:use [clojure.string]
        [ring.util.response]))

(defn split-uri [uri]
  (split uri #"/"))

(defn get-uri [request]
  (:uri request))

(defn respond-with [page]
  (file-response page {:root "public"}))

(defn get? [response]
  (= :get (:request-method response)))

(defn post? [response]
  (= :post (:request-method response)))
