/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function integrate() {
    var timer;
    clearTimeout(timer);
    $("#integrateLoad").modal("show");
    timer = setTimeout(function () {
        $.ajax({
            url: "ServletETL",
            type: 'POST',
            dataType: "JSON"
            , success: function (data) {
                if (data === true) {
                    $("#integrateLoad").modal("hide");
                    $("#notificationModal").addClass("modal-success");
                    $("#notificationHeader").text("Success");
                    $("#notificationBodyModal").append("<p><center>You have successfully integrated the data.</center></p>");
                    $("#notificationModal").modal("show");
                    // Set a timeout to hide the element again
                    setTimeout(function () {
                        $("#notificationModal").removeClass("modal-success");
                        $("#notificationModal").modal("hide");
                        $("#notificationHeader").text("");
                        $("#notificationBodyModal").empty();
                    }, 4000);

                }

            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(exception);
            }
        });
    }, 2000);
}