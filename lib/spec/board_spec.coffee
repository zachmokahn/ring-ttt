describe "Board", ->
  board = null
  testData = {test: "data"}

  newGame = ->
    $("[data-id='newGame']").click()

  describe "new game button -> click", ->
    beforeEach ->
      spyOn(TTT.Service, "postNewGame").and.returnValue testData
      setFixtures("""
        <input data-id='gameMode' value='mode'/>
        <input data-id='gameTurn' value='turn'/>
        <input data-id='gameDifficulty' value='difficulty'/>
        <button data-id='newGame'/>""")
      board = new TTT.Board
      board.init()

    it "collects options and passes them to the new game service", ->
      newGame()
      gameRules = { mode: "mode", turn: "turn", difficulty: "difficulty" }
      expect(TTT.Service.postNewGame).toHaveBeenCalledWith(gameRules)

    it "resets the board view", ->
      spyOn(board.view, "reset")
      newGame()
      expect(board.view.reset).toHaveBeenCalled()

    it "initializes the board view with new game data", ->
      spyOn(board.view, "init")
      newGame()
      expect(board.view.init).toHaveBeenCalledWith(testData)
