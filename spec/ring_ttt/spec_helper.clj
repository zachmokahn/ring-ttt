(ns ring-ttt.spec-helper
  (:use ring.mock.request))

(def _turn :player2 )
(def _mode :pvc)
(def _empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(def _draw-board  ["x" "o" "x" "x" "o" "x" "o" "-" "-"])
(def _fake-parsed-params { :board _empty-board :mode _mode :turn _turn } )

(defn _new-game-player1-pvc-hard-post-request []
  (request :post "/game/new-game"
    { "gameTurn"       "first-player"
      "gameMode"       "singlePlayer"
      "gameDifficulty" "hard"}))

(defn _new-game-player2-pvc-hard-post-request []
  (request :post "/game/new-game"
    { "gameTurn"       "second-player"
      "gameMode"       "singlePlayer"
      "gameDifficulty" "hard"}))

(defn _new-game-player1-pvp-easy-post-request []
  (request :post "/game/new-game"
    { "gameTurn"       "first-player"
      "gameMode"       "twoPlayer"
      "gameDifficulty" "easy"}))

(defn _new-game-player2-pvp-easy-post-request []
  (request :post "/game/new-game"
    { "gameTurn"       "second-player"
      "gameMode"       "twoPlayer"
      "gameDifficulty" "easy"}))

(defn _move-game-player1 []
  (request :post "/game/make-move"
    { "gameBoard[]"      _empty-board
      "gameTurn"       "first-player"
      "gameMove"       4
      "gameMode"       "twoPlayer"
      "gameDifficulty" "hard"}))

(defn _move-game-player2 []
  (request :post "/game/make-move"
    { "gameBoard[]"      _empty-board
      "gameTurn"       "second-player"
      "gameMove"       4
      "gameMode"       "twoPlayer"
      "gameDifficulty" "hard"}))

(defn _move-game-player1-pvc []
  (request :post "/game/make-move"
    { "gameBoard[]"      _empty-board
      "gameTurn"       "first-player"
      "gameMove"       3
      "gameMode"       "singlePlayer"
      "gameDifficulty" "hard"}))

(defn _move-game-player2-pvc []
  (request :post "/game/make-move"
    { "gameBoard[]"      _empty-board
      "gameTurn"       "first-player"
      "gameMove"       3
      "gameMode"       "singlePlayer"
      "gameDifficulty" "hard"}))

(defn _draw-game []
  (request :post "/game/make-move"
    { "gameBoard[]"      _draw-board
      "gameTurn"       "first-player"
      "gameMove"       7
      "gameMode"       "singlePlayer"
      "gameDifficulty" "hard"}))

(defn _player1-win []
  (request :post "/game/make-move"
    { "gameBoard[]"      _draw-board
      "gameTurn"       "first-player"
      "gameMove"       8
      "gameMode"       "twoPlayer"
      "gameDifficulty" "hard"}))

(defn _player2-win []
  (request :post "/game/make-move"
    { "gameBoard[]"      (assoc _draw-board 5 "-")
      "gameTurn"       "first-player"
      "gameMove"       5
      "gameMode"       "singlePlayer"
      "gameDifficulty" "hard"}))

(defn _home-page-request []
  (request :get "/"))

(defn _test-request []
  (request :get "/test/uri" ))
