(ns ring-ttt.routes)

(def get-routes {})
(def post-routes {})

(def routes
  {:get  get-routes
   :post post-routes})

(defn get-http-method [request]
  (get request :request-method))

(defn get-http-uri [request]
  (get request :uri))

(defn get-body [routes uri]
  (get routes uri))

(defn controller [request]
  (let [method (get-http-method request)
        uri    (get-http-uri request)]
    (get-body (method routes) uri)))
