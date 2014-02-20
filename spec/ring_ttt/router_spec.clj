(ns ring-ttt.router-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.router :refer :all]))

(let [request     {:request-method :test
                   :uri "/test"}
      test-routes {"/test" "test"}]
(describe "SPEC: ring-ttt.router"

  (context "#get-http-method"
    (it "returns the method from the request"
      (should= :test
               (get-http-method request))))

  (context "#get-http-uri"
    (it "returns the uri from the request"
      (should= "/test"
               (get-http-uri request))))

  (context "#get-body"
    (it "returns the body from the route and uri"
      (should= "test"
               (get-body test-routes "/test"))))

  (context "#routes"
    (it "contains routes for http-get"
      (should= ring-ttt.routes/get-routes
               (:get routes)))
    (it "contains routes for http-posts"
      (should= ring-ttt.routes/post-routes
               (:post routes))))))
