(ns ring-ttt.controllers.controller-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.controllers.controller :refer :all]))

(describe "Controller -> Abstract Spec:"
  (describe "#respond-with"
    (it "gets provided file from the 'public' directory"
      (should-invoke ring.util.response/file-response
        {:with ["test.html" {:root "public"}]}
      (respond-with "test.html"))))

  (describe "HTTP Request Methods"

    (describe "#get?"
      (it "determines if it is a GET request"
        (should= true (get? {:request-method :get}))
        (should= false (get? {:request-method :test}))))

    (describe "#post?"
      (it "determines if it is a POST request"
        (should= true (post? {:request-method :post}))
        (should= false (post? {:request-method :test}))))))
