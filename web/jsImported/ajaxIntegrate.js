/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
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
                    $("#modalHeader").css({background: "#00a65a"});
                    $("#notificationHeader").css({color: "#FFFFFF"});
                    $("#notificationBodyModal").append("<p style='margin-right: 10%; padding: 3%; text-align:center;'>You have successfully integrated the data.</p>");
                    $("#notificationModalFooter").empty();
                    $("#notificationModalFooter").append('<a href="ServletAccess?redirect=StandardManipulation"><button type="button"class="btn btn-success" >Proceed to Integrated Analytics</button></a>');
                    $("#notificationModalFooter").append(' <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');

                    $("#notificationModal").modal("show");
                    $('#integrate').prop('disabled', true);
                    $('#integrate').addClass('btn-default');
                    $('#integratetooltip').prop('title', 'All reports are integrated');
                } else {
                    $("#integrateLoad").modal("hide");
                    $("#notificationHeader").text("Success!");
                    $("#modalHeader").css({background: "#00a65a"});
                    $("#notificationHeader").css({color: "#FFFFFF"});
                    $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'>You have successfully integrated the data</p>");
                    $("#notificationModalFooter").empty();
                    $("#notificationModalFooter").append('<a href="ServletAccess?redirect=StandardManipulation"><button type="button" class="btn btn-success" >Proceed to Integrated Analytics</button></a>');
                    $("#notificationModalFooter").append(' <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                    $("#notificationModal").modal("show");
                    $('#integrate').prop('disabled', true);
                    $('#integrate').addClass('btn-default');
                    $('#integratetooltip').prop('title', 'All reports are integrated');

                }
            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(exception);
            }
        });
    }, 2000);
}

