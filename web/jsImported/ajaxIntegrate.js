/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function integrate() { 
    var timer;
    clearTimeout(timer);
    $("#integrateLoad").modal("show");
  timer = setTimeout(function() { 
      $.ajax({
        url: "ServletETL",
        type: 'POST',
        dataType: "JSON"
       ,success: function (data) {
            $("#integrateLoad").modal("hide");
            $("#successAlert").empty();
             $("#result").modal("show");
             $("#successAlert").append("You have successfully integrated the data");
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(exception);
        }
    });}, 2000);
}