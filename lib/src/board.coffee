TTT = window.TTT = {}

class TTT.Board
  constructor: ->
    @view = new TTT.BoardView
  init: ->
    @bindNewGame()

  bindNewGame: ->
    $("[data-id='newGame']").on "click", =>
      @view.reset()
      @updateGameData()
      @startNewGame()

  startNewGame: =>
    TTT.Service.postNewGame @gameRules, (newGame) =>
      console.log(newGame)
      @view.sync(newGame)
      @updateGameData(newGame["board"])
      @assignTurn(newGame)
      @bindGameBoard()

  getWinner: (results) ->
    if (results.winner == "draw")
      alert("The game is a draw, nobody wins")
    else
      alert("The winner is #{results.winner}, congrats!")


  bindGameBoard : ->
    $(".boardPiece").on "click", (event) =>
      $(".boardPiece").unbind()
      index = $(event.target).data("index-id")
      TTT.Service.postMove @gameRules, index, (response) =>
        @updateBoard(response["board"])
        @assignTurn(response)
        @view.sync(response)
        if response["game-over"]
          @getWinner(response)
        else
          @bindGameBoard()

  updateBoard : (board) ->
    @gameRules.gameBoard = board

  assignTurn: (newGame) ->
    if newGame["turn"] == "player1"
      @gameRules.gameTurn = "first-player"
    else
      @gameRules.gameTurn = "second-player"

  updateGameData: (board) =>
    @gameRules = {
      gameBoard: board
      gameMode: $("[data-id='gameMode']").val()
      gameTurn: $("[data-id='gameTurn']").val()
      gameDifficulty: $("[data-id='gameDifficulty']").val()
    }

class TTT.BoardView
  reset: ->
    for index in [0..8]
      @removeClass(index)

  sync: (gameData) ->
    @reset()
    @displayBoard(gameData["board"])
    @turn = gameData["turn"]

  displayBoard: (board) ->
    for index in [0..8]
      @applyClass(board[index], index)

  applyClass: (marker, position) ->
    if marker == "x"
      @_applyClass("cross", position)
    else if marker == "o"
      @_applyClass("circle", position)

  _applyClass: (marker, position) ->
    $("[data-index-id='#{position}']").addClass(marker)

  removeClass: (position) ->
    $("[data-index-id='#{position}']").removeClass("circle")
    $("[data-index-id='#{position}']").removeClass("cross")

window.TTT.Board = TTT.Board
window.TTT.BoardView = TTT.BoardView
