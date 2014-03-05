(function() {
  TTT.Service = {
    postNewGame: function(rules) {
      return console.log(rules);
    },
    _post: function(url, data, callback) {
      return $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: callback,
        dataType: 'json'
      });
    }
  };

  window.TTT.Service = TTT.Service;

}).call(this);
