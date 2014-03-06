(ns ring-ttt.controllers.game-controller-spec
  (:require [ring-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]
            [ring-ttt.controllers.game-controller :refer :all]))

(describe "Controller -> Game Spec:"
  (describe "#parse-params"
    (it "returns value of provided parameters and request"
      (should= "data"
               (parse-params "test" {:params {"test" "data"}}))))

  (describe "#game?"
    (it "true if meets criteria for game controller"
      (should= true (game? (_new-game-player1-pvc-hard-post-request))))
    (it "false if doesn't meet game controller criteria"
      (should= false (game? (_test-request)))))

  (describe "#new-game"
    (it "sends new game data to the game interface"
      (with-redefs [parse-new-game-params (fn [_]
                      _new-game-player1-pvc-hard-post-request)]
      (should-invoke ring-ttt.interface/start-game
                      {:with [_new-game-player1-pvc-hard-post-request]}
                     (new-game _test-request)))))

  (describe "#make-move"
    (it "sends move data to the game interface"
      (with-redefs [parse-make-move-params (fn [_] _move-game-player1)]
      (should-invoke ring-ttt.interface/play-game {:with [_move-game-player1]}
                     (make-move _test-request)))))

  (describe "#game-post-controller"
    (it "posts a new game"
      (should= new-game (game-post-controller (_new-game-player1-pvc-hard-post-request))))
    (it "posts a move"
      (should= make-move (game-post-controller (_move-game-player1)))))

  (describe "#game-controller"
    (it "delegates HTTP:POST -> game-post-controller"
      (with-redefs [ring-ttt.controllers.controller/post? (fn [_] true)]
        (should-invoke game-post-controller {}
        (game-controller (_new-game-player2-pvc-hard-post-request)))))))
