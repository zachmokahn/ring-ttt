(function() {
  TTT.Service = {
    postNewGame: function(rules, fn) {
      return this._post("/game/new-game", rules, fn);
    },
    postMove: function(rules, index, fn) {
      rules["gameMove"] = index;
      return this._post("/game/make-move", rules, fn);
    },
    _post: function(url, data, callback) {
      return $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: callback,
        dataType: 'json',
        statusCode: {
          404: function() {
            return alert("Ummm.... What?");
          }
        }
      });
    }
  };

  window.TTT.Service = TTT.Service;

}).call(this);
