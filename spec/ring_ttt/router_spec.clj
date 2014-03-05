(ns ring-ttt.router-spec
  (:require [ring-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]
            [ring-ttt.router :refer :all]))

(def _request _new-game-player2-pvc-hard-post-request)

(describe "Router Spec:"
    (describe "#router"
      (it "-> propper controller if route exists"
      (with-redefs [ring-ttt.controllers.home-controller/home?
                    (fn [_] true)]
      (should-invoke  ring-ttt.controllers.home-controller/home-controller
                        {:with [_test-request]}
                      (router _test-request))))

      (it "-> invalid-controller if route doesn't exist"
        (should-invoke
          ring-ttt.controllers.invalid-controller/invalid-controller
            {:with [(_test-request)]}
             (router (_test-request))))))
