TTT.Service =
  postNewGame: (rules, fn) ->
    @_post("/game/new-game", rules, fn)

  postMove: (rules, index, fn) ->
    rules["gameMove"] = index
    @_post("/game/make-move", rules, fn)

  _post: ( url, data, callback) ->
    $.ajax({
      url : url
      type: "POST"
      data: data
      success: callback
      dataType: 'json'
      statusCode: { 404 : -> alert("Ummm.... What?")  }
    })

window.TTT.Service = TTT.Service
