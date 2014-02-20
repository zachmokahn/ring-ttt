(ns ring-ttt.router
  (:require [ring-ttt.routes :refer :all]))

(def routes
  {:get  get-routes
   :post post-routes})

(defn get-http-method [request]
  (get request :request-method))

(defn get-http-uri [request]
  (get request :uri))

(defn get-body [routes uri]
  (or (get routes uri) page-not-found))

(defn controller [request]
  (let [method (get-http-method request)
        uri    (get-http-uri request)]
    (get-body (method routes) uri)))
