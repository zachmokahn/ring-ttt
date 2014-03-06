(ns ring-ttt.core-spec (:require [ring-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]
            [clojure.data.json :refer [write-str]]
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
            (should= (:body response)
                     (write-str {:board _empty-board :turn :player1}))))

        (it "Player 2's Turn, :turn -> player2, :board -> new board"
          (let [response (app (_new-game-player2-pvp-easy-post-request))]
            (should= (:body response)
                     (write-str { :board _empty-board :turn :player2 })))))

      (describe "Post Move"
        (it "Player 1's Turn, :turn -> player2, :board -> board with posted move for player 1"
          (let [response (app (_move-game-player1))]
            (should= (:body response)
                     (write-str {:game-over false
                                 :board (assoc _empty-board 4 "x")
                                 :turn :player2}))))

        (it "Player 2's Turn, :turn -> player1, :board -> board with posted move for player 2"
          (let [response (app (_move-game-player2))]
            (should= (:body response)
                     (write-str {:game-over false
                                 :board (assoc _empty-board 4 "o")
                                 :turn :player1}))))))

    (describe "Player vs. Computer"
      (describe "New Game"
        (it "Player's Turn, :turn -> player1,   :board -> new board"
          (let [response (app (_new-game-player1-pvc-hard-post-request))]
            (should= (:body response)
                     (write-str {:board _empty-board
                                 :turn :player1}))))

        (it "Computer's Turn, :turn -> player1, :board -> new board with computers first move"
        (let [response (app (_new-game-player2-pvc-hard-post-request))]
            (should= (:body response)
                     (write-str {:game-over false
                                 :board (assoc _empty-board 4 "o")
                                 :turn :player1})))))

      (describe "Post Move"
        (it "Player's turn, :turn -> player1,   :board -> board with computer and players posted move"
          (let [response (app (_move-game-player1-pvc))]
              (should= (:body response)
                       (write-str {:game-over false
                                   :board (assoc _empty-board 3 "x" 4 "o")
                                   :turn :player1}))))))))
