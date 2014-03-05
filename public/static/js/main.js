(function() {
  $(function() {
    var board;
    console.log('ready');
    board = new TTT.Board;
    return board.init();
  });

}).call(this);
