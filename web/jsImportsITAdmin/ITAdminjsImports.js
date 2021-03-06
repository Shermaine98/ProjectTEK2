/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$(document).ready(function () {
    accountsCounts();

    //external
    $('#clickedApprovedE, #clickedRejectE').click(function () {
        if (this.id == 'clickedApprovedE') {
            var redirect = "approveE";
            var userID = $(this).closest("tr").find(".nr").text();
            var whichtr = $(this).closest("tr");
            whichtr.remove();
            $.ajax({
                url: "Approvals",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    userID: userID
                },
                success: function (data) {
                    // @gcla109
                    if (data === true) {
                        $("#notificationHeader").text("Success!");
                        $("#modal_Header").css({background: "#00a65a"});
                        $("#notificationHeader").css({color: "#FFFFFF"});
                        $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'> You have successfully approved the account! </p>");
                        $("#notificationModal").modal("show");
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modal_Header").css({background: ""});
                            $("#notificationModal").modal("hide");
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 4000);
                        accountsCounts();

                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    console.log(XMLHttpRequest);
                }
            });

        } else if (this.id == 'clickedRejectE') {
            var redirect = "reject";
            var userID = $(this).closest("tr").find(".nr").text();
            var whichtr = $(this).closest("tr");
            whichtr.remove();
            console.log(userID);

            $.ajax({
                url: "Approvals",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    userID: userID
                },
                success: function (data) {
                    // @gcla109
                    console.log("TRIAL" + data);
                    if (data === true) {
                        $("#notificationHeader").text("Success!");
                        $("#modal_Header").css({background: "#00a65a"});
                        $("#notificationHeader").css({color: "#FFFFFF"});
                        $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'> You have successfully rejected the account! </p>");
                        $("#notificationModal").modal("show");
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modal_Header").css({background: ""});
                            $("#notificationModal").modal("hide");
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 4000);
                        accountsCounts();
                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    console.log(XMLHttpRequest);
                }
            });
        }
    });


    //internal
    $('#clickedApprovedI, #clickedRejectI').click(function () {
        if (this.id == 'clickedApprovedI') {
            var redirect = "approveI";
            var userID = $(this).closest("tbody tr").find(".Inr").text();
            var division = $(this).closest('tbody').find('tr').find('#division :selected').text();
            var position = $(this).closest('tbody').find('tr').find('#position_title :selected').text();
            var employmentDate = $(this).closest('tbody').find('tr').find('#employmentDate').val();
            var whichtr = $(this).closest("tbody");
            console.log(userID);
            console.log(position);
            console.log(employmentDate);
            whichtr.remove();
            $.ajax({
                url: "Approvals",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    userID: userID,
                    division: division,
                    position: position,
                    employmentDate: employmentDate
                },
                success: function (data) {

                    // @gcla109
                    if (data === true) {
                        $("#notificationHeader").text("Success!");
                        $("#modal_Header").css({background: "#00a65a"});
                        $("#notificationHeader").css({color: "#FFFFFF"});
                        $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'> Successfully Approved! </p>");
                        $("#notificationModal").modal("show");
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modal_Header").css({background: ""});
                            $("#notificationModal").modal("hide");
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 4000);
                        accountsCounts();

                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    console.log(XMLHttpRequest);
                }
            });

        } else if (this.id == 'clickedRejectI') {
            var redirect = "reject";
            var userID = $(this).closest("tbody tr").find(".Inr").text();
            var whichtr = $(this).closest("tbody");
            whichtr.remove();
            $.ajax({
                url: "Approvals",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    userID: userID
                },
                success: function (data) {
                    // @gcla109
                    if (data === true) {
                        $("#notificationHeader").text("Success!");
                        $("#modal_Header").css({background: "#00a65a"});
                        $("#notificationHeader").css({color: "#FFFFFF"});
                        $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'> Successfully Rejected! </p>");
                        $("#notificationModal").modal("show");
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modal_Header").css({background: ""});
                            $("#notificationModal").modal("hide");
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 4000);
                        accountsCounts();

                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    console.log(XMLHttpRequest);
                }
            });

        }
    });
});



function accountsCounts() {

    $.ajax({
        url: "AccountsRegistration",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            $("#iCount span").text(data.iCount);
            $("#eCount span").text(data.eCount);

        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(exception);
        }
    });
}