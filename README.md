# ring-ttt

This is an unbeatable game of tic tac toe with the AI engine built in clojure.

### Installation & Starting Instructions
(You are going to need [Leiningen](http://leiningen.org/), [NPM, and
Grunt](http://thechangelog.com/install-node-js-with-homebrew-on-os-x/))

1. Clone the repository and enter the directory
  ```
  $ git clone http://www.github.com/zachmokahn/ring-ttt.git
  $ cd ring-ttt
  ```

2. Install the dependencies
  ```
  $ npm install
  $ lein deps
  ```

3. Then build the Javascripts (you will also need grunt-cli)
  ```
  $ grunt coffee:sources
  ```

4. Run the game
  ```
  $ lein ring server
  ```

### Playing the Game:
  The game has a field of options up top. You can choose the game mode, first
turn, and difficulty.

#### Game Modes
* Single Player: Human(X) vs. the Computer(O)
* Two Player: Player1(X) vs. Player2(O)

#### First Turn
* Player 1: X moves first
* Player 2: O moves first

### Game Difficulty
* Hard: Minimax AI
* Easy: Random AI
