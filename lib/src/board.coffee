TTT = window.TTT = {}

class TTT.Board
  constructor: ->
    @view = new TTT.BoardView
  init: ->
    @bindNewGame()

  bindNewGame: ->
    $("[data-id='newGame']").on "click", =>
      @view.reset()
      @view.init(TTT.Service.postNewGame(@assignGameRules()))

  assignGameRules: =>
    @gameRules = {
      mode: $("[data-id='gameMode']").val()
      turn: $("[data-id='gameTurn']").val()
      difficulty: $("[data-id='gameDifficulty']").val()
    }

class TTT.BoardView
  init: (gameData) ->
  reset: ->

window.TTT.Board = TTT.Board
window.TTT.BoardView = TTT.BoardView
