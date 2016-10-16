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
                    $("#notificationHeader").text("Success!");
                    $("#modal_Header").css({background: "#00a65a"});
                    $("#notificationHeader").css({color: "#FFFFFF"});
                    $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'>You have successfully integrated the data.</p>");
                    $("#notificationModal").modal("show");
                    // Set a timeout to hide the element again
                    setTimeout(function () {
                        $("#modal_Header").css({background: ""});
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