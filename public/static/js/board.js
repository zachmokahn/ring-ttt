(function() {
  var TTT,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  TTT = window.TTT = {};

  TTT.Board = (function() {
    function Board() {
      this.assignGameRules = __bind(this.assignGameRules, this);
      this.view = new TTT.BoardView;
    }

    Board.prototype.init = function() {
      return this.bindNewGame();
    };

    Board.prototype.bindNewGame = function() {
      return $("[data-id='newGame']").on("click", (function(_this) {
        return function() {
          _this.view.reset();
          return _this.view.init(TTT.Service.postNewGame(_this.assignGameRules()));
        };
      })(this));
    };

    Board.prototype.assignGameRules = function() {
      return this.gameRules = {
        mode: $("[data-id='gameMode']").val(),
        turn: $("[data-id='gameTurn']").val(),
        difficulty: $("[data-id='gameDifficulty']").val()
      };
    };

    return Board;

  })();

  TTT.BoardView = (function() {
    function BoardView() {}

    BoardView.prototype.init = function(gameData) {};

    BoardView.prototype.reset = function() {};

    return BoardView;

  })();

  window.TTT.Board = TTT.Board;

  window.TTT.BoardView = TTT.BoardView;

}).call(this);
