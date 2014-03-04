(ns ring-ttt.controllers.game-controller-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.controllers.game-controller :refer :all]))

(def _new-game  {:uri    "/game/new-game"})
(def _make-move {:uri "/game/make-move"})

(describe "Controller -> Game Spec:"
  (describe "#parse-params"
    (it "returns value of provided parameters and request"
      (should= "data"
               (parse-params "test" {:params {"test" "data"}}))))

  (describe "#game?"
    (it "true if meets criteria for game controller"
      (should= true (game? _new-game)))
    (it "false if doesn't meet game controller criteria"
      (should= false (game? {:uri "/"}))))

  (describe "#new-game"
    (it "starts a new game"
      (let [json {:board "board" :turn "turn"}]
      (with-redefs [ring-ttt.interface/play-game (fn [_] json)]
        (should= json (new-game _new-game))))))

  (describe "#game-post-controller"
    (it "posts a new game"
      (should= new-game (game-post-controller _new-game)))
    (it "posts a move"
      (should= make-move (game-post-controller _make-move))))

  (describe "#game-controller"
    (it "delegates HTTP:POST -> game-post-controller"
      (with-redefs [ring-ttt.controllers.controller/post? (fn [_] true)]
        (should-invoke game-post-controller {:with [_new-game]}
        (game-controller _new-game))))))
