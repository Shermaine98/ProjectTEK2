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
            url: "ETLServlet",
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
                    $("#modal-footer").append('<button id="submitButton" type="button"class="btn btn-default" data-dismiss="modal">Submit</button>');

                } else {
                    $("#integrateLoad").modal("hide");
                    $("#notificationHeader").text("Success!");
                    $("#modal_Header").css({background: "#00a65a"});
                    $("#notificationHeader").css({color: "#FFFFFF"});
                    $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'>You have successfully integrated the data.</p>");
                    $("#modal-footer").append('<button id="submitButton" type="button"class="btn btn-default" data-dismiss="modal">Submit</button>');
                    $("#notificationModal").modal("show");

                }
                $("#notificationBodyModal").empty();
                $("#modal-footer").empty();
                $("#modal-footer").append('<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(exception);
            }
        });
    }, 2000);
}