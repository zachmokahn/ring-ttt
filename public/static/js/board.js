(function() {
  var TTT,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  TTT = window.TTT = {};

  TTT.Board = (function() {
    function Board() {
      this.updateGameData = __bind(this.updateGameData, this);
      this.startNewGame = __bind(this.startNewGame, this);
      this.view = new TTT.BoardView;
    }

    Board.prototype.init = function() {
      return this.bindNewGame();
    };

    Board.prototype.bindNewGame = function() {
      return $("[data-id='newGame']").on("click", (function(_this) {
        return function() {
          _this.view.reset();
          _this.updateGameData();
          return _this.startNewGame();
        };
      })(this));
    };

    Board.prototype.startNewGame = function() {
      return TTT.Service.postNewGame(this.gameRules, (function(_this) {
        return function(newGame) {
          console.log(newGame);
          _this.view.sync(newGame);
          _this.updateGameData(newGame["board"]);
          _this.assignTurn(newGame);
          return _this.bindGameBoard();
        };
      })(this));
    };

    Board.prototype.getWinner = function(results) {
      if (results.winner === "draw") {
        return alert("The game is a draw, nobody wins");
      } else {
        return alert("The winner is " + results.winner + ", congrats!");
      }
    };

    Board.prototype.bindGameBoard = function() {
      return $(".boardPiece").on("click", (function(_this) {
        return function(event) {
          var index;
          $(".boardPiece").unbind();
          index = $(event.target).data("index-id");
          return TTT.Service.postMove(_this.gameRules, index, function(response) {
            _this.updateBoard(response["board"]);
            _this.assignTurn(response);
            _this.view.sync(response);
            if (response["game-over"]) {
              return _this.getWinner(response);
            } else {
              return _this.bindGameBoard();
            }
          });
        };
      })(this));
    };

    Board.prototype.updateBoard = function(board) {
      return this.gameRules.gameBoard = board;
    };

    Board.prototype.assignTurn = function(newGame) {
      if (newGame["turn"] === "player1") {
        return this.gameRules.gameTurn = "first-player";
      } else {
        return this.gameRules.gameTurn = "second-player";
      }
    };

    Board.prototype.updateGameData = function(board) {
      return this.gameRules = {
        gameBoard: board,
        gameMode: $("[data-id='gameMode']").val(),
        gameTurn: $("[data-id='gameTurn']").val(),
        gameDifficulty: $("[data-id='gameDifficulty']").val()
      };
    };

    return Board;

  })();

  TTT.BoardView = (function() {
    function BoardView() {}

    BoardView.prototype.reset = function() {
      var index, _i, _results;
      _results = [];
      for (index = _i = 0; _i <= 8; index = ++_i) {
        _results.push(this.removeClass(index));
      }
      return _results;
    };

    BoardView.prototype.sync = function(gameData) {
      this.reset();
      this.displayBoard(gameData["board"]);
      return this.turn = gameData["turn"];
    };

    BoardView.prototype.displayBoard = function(board) {
      var index, _i, _results;
      _results = [];
      for (index = _i = 0; _i <= 8; index = ++_i) {
        _results.push(this.applyClass(board[index], index));
      }
      return _results;
    };

    BoardView.prototype.applyClass = function(marker, position) {
      if (marker === "x") {
        return this._applyClass("cross", position);
      } else if (marker === "o") {
        return this._applyClass("circle", position);
      }
    };

    BoardView.prototype._applyClass = function(marker, position) {
      return $("[data-index-id='" + position + "']").addClass(marker);
    };

    BoardView.prototype.removeClass = function(position) {
      $("[data-index-id='" + position + "']").removeClass("circle");
      return $("[data-index-id='" + position + "']").removeClass("cross");
    };

    return BoardView;

  })();

  window.TTT.Board = TTT.Board;

  window.TTT.BoardView = TTT.BoardView;

}).call(this);
