describe "Board", ->
  board = null
  testData = {test: "data"}

  newGame = ->
    $("[data-id='newGame']").click()

  beforeEach ->
    setFixtures("""
      <input data-id='gameMode' value='mode'/>
      <input data-id='gameTurn' value='turn'/>
      <input data-id='gameDifficulty' value='difficulty'/>
      <div class="boardPiece" data-index-id='0'>
      <button data-id='newGame'/>""")
    board = new TTT.Board
    spyOn(board.view, "reset")
    spyOn(TTT.Service, "postNewGame").and.returnValue testData
    spyOn(TTT.Service, "postMove").and.returnValue testData
    board.init()

  describe "new game button -> click", ->

    it "collects options and passes them to the new game service", ->
      newGame()
      expect(TTT.Service.postNewGame).toHaveBeenCalled()

    it "resets the board view", ->
      newGame()
      expect(board.view.reset).toHaveBeenCalled()

  describe "Making a move", ->
    it "passes the index from the div clicked to the postMove service", ->
      newGame()
      $("[data-index-id='0']").click()
      expect(TTT.Service.postMove).toHaveBeenCalled()
