(ns ring-ttt.controllers.controller
  (:require [ring-ttt.constants :refer [ROOT]])
  (:use     [clojure.string]
            [ring.util.response]))

(defn split-uri [uri]
  (split uri #"/"))

(defn get-uri [request]
  (:uri request))

(defn get-path [request]
  (let [uri (get-uri request)]
    (split-uri uri)))

(defn respond-with [page]
  (file-response page {:root ROOT}))

(defn get? [response]
  (= :get (:request-method response)))

(defn post? [response]
  (= :post (:request-method response)))
