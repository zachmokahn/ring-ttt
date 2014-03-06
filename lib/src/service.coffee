TTT.Service =
  postNewGame: (rules) ->
    console.log(rules)

  _post: (url, data, callback) ->
    $.ajax({
      url : url
      type: "POST"
      data: data
      success: callback
      dataType: 'json'
    })

window.TTT.Service = TTT.Service
