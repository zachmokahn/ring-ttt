(ns ring-ttt.core-spec (:require [ring-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]
            [ring-ttt.core :refer :all]))

(describe "Acceptance Test:"
  (describe "Home Route:"
    (it "gets the home page"
      (let [response (app (_home-page-request))]
        (should= 200 (:status response)))))

  (describe "Game Route:"
    (describe "Player vs. Player"
      (describe "New Game"
        (it "Player 1's Turn, :turn -> player1, :board -> new board"
          (let [response (app (_new-game-player1-pvp-easy-post-request))]
            (should= _empty-board (:board response))
            (should= :player1 (:turn response))))

        (it "Player 2's Turn, :turn -> player2, :board -> new board"
          (let [response (app (_new-game-player2-pvp-easy-post-request))]
            (should= _empty-board (:board response))
            (should= :player2 (:turn response)))))

      (describe "Post Move"
        (it "Player 1's Turn, :turn -> player2, :board -> board with posted move for player 1"
          (let [response (app (_move-game-player1))]
            (should= (assoc _empty-board 4 "x") (:board response))
            (should= false (:game-over response))
            (should= :player2 (:turn response))))

        (it "Player 2's Turn, :turn -> player1, :board -> board with posted move for player 2"
          (let [response (app (_move-game-player2))]
            (should= (assoc _empty-board 4 "o") (:board response))
            (should= false (:game-over response))
            (should= :player1 (:turn response))))))

    (describe "Player vs. Computer"
      (describe "New Game"
        (it "Player's Turn, :turn -> player1,   :board -> new board"
          (let [response (app (_new-game-player1-pvc-hard-post-request))]
            (should= _empty-board (:board response))
            (should= :player1 (:turn response))))

        (it "Computer's Turn, :turn -> player1, :board -> new board with computers first move"
        (let [response (app (_new-game-player2-pvc-hard-post-request))]
          (should-not= _empty-board (:board response))
          (should= false (:game-over response))
          (should= :player1 (:turn response)))))

    (describe "Post Move"
      (it "Player's turn, :turn -> player1,   :board -> board with computer and players posted move"
        (let [response (app (_move-game-player1-pvc))]
          (should= (assoc _empty-board 3 "x" 4 "o") (:board response))
          (should= false (:game-over response))
          (should= :player1 (:turn response))))))

    (describe "Game Over"
      (it "DRAW, :game-over -> true, :winner -> 'draw'"
        (let [response (app (_draw-game))]
          (should= "draw" (:winner response))
          (should= true (:game-over response))))

      (it "WIN: Player1, :game-over -> true, :winner -> 'first-player'"
        (let [response (app (_player1-win))]
          (should= "first-player" (:winner response))
          (should= true (:game-over response))))

      (it "WIN: Player2, :game-over -> true, :winner -> 'second-player'"
        (let [response (app (_player2-win))]
          (should= "second-player" (:winner response))
          (should= true (:game-over response))))))

  (describe "Invalid Route:"
    (it "renders a 404"
      (let [response (app (_test-request))]
      (should= 200 (:status response))))))
