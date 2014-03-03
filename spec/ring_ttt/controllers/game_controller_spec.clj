(ns ring-ttt.controllers.game-controller-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.controllers.game-controller :refer :all]))

(def _new-game
  {:uri "/game/new-game"})

(describe "Controller -> Game Spec:"
  (describe "#game?"
    (it "true if meets criteria for game controller"
      (should= true (game? _new-game)))
    (it "false if doesn't meet game controller criteria"
      (should= false (game? {:uri "/"}))))

  (describe "#new-game"
    (it "is pending"))

  (describe "#game-post-controller"
    (it "posts a new game"
      (should= new-game (game-post-controller _new-game))))

  (describe "#game-controller"
    (it "delegates HTTP:POST -> game-post-controller"
      (with-redefs [ring-ttt.controllers.controller/post? (fn [_] true)]
        (should-invoke game-post-controller {:with [_new-game]}
        (game-controller _new-game))))))
