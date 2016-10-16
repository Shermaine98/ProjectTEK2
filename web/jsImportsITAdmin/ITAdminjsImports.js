/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

                    console.log("TRIAl" + data);
                    
                        $("#modalHeader").addClass("modal-header-success");
                        $("#notificationHeader").text("Success");
                        $("#notificationBodyModal").append("<p>Succesfuly! Approved</p>");
                        $("#notificationModal").modal("show");
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modalHeader").removeClass("modal-header-success");
                            $("#notificationModal").modal("hide");
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 3000);
                        accountsCounts();
                    

                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(exception);
                }
            });

        } else if (this.id == 'clickedRejectE') {
            var redirect = "reject";
            var userID = $(this).closest("tr").find(".nr").text();
            var whichtr = $(this).closest("tr");
            whichtr.remove();
            $.ajax({
                url: "approvals",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    userID: userID
                },
                success: function (data) {
                     console.log("hello");
                    if (data === true) {
                        $("#modalHeader").addClass("modal-header-success");
                        $("#notificationHeader").text("Success");
                        $("#notificationBodyModal").append("<p>Succesfuly! Rejected</p>");
                        $("#notificationModal").show();
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modalHeader").removeClass("modal-header-success");
                            $("#notificationModal").hide();
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 3000);
                        accountsCounts();
                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(exception);
                }
            });

        }
    });


    //internal
    $('#clickedApprovedI, #clickedRejectI').click(function () {
        if (this.id == 'clickedApprovedI') {
            var redirect = "approveI";
            var userID = $(this).closest("tr").find(".nr").text();
            var division = $(this).closest("tr").find('#division :selected').text();
            var position = $(this).closest("tr").find('#position_title :selected').text();
            var employmentDate = $(this).closest("tr").find('#employmentDate').val();
            var whichtr = $(this).closest("tr");
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
                    if (data === true) {
                        $("#modalHeader").addClass("modal-header-success");
                        $("#notificationHeader").text("Success");
                        $("#notificationBodyModal").append("<p>Succesfuly! Approved</p>");
                        $("#notificationModal").show();
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modalHeader").removeClass("modal-header-success");
                            $("#notificationModal").hide();
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 3000);
                        accountsCounts();
                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(exception);
                }
            });

        } else if (this.id == 'clickedRejectI') {
            var redirect = "reject";
            var userID = $(this).closest("tr").find(".nr").text();
            var whichtr = $(this).closest("tr");
            whichtr.remove();
            $.ajax({
                url: "approvals",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    userID: userID
                },
                success: function (data) {
                    if (data === true) {
                        $("#modalHeader").addClass("modal-header-success");
                        $("#notificationHeader").text("Success");
                        $("#notificationBodyModal").append("<p>Succesfuly! Rejected</p>");
                        $("#notificationModal").show();
                        // Set a timeout to hide the element again
                        setTimeout(function () {
                            $("#modalHeader").removeClass("modal-header-success");
                            $("#notificationModal").hide();
                            $("#notificationHeader").text("");
                            $("#notificationBodyModal").empty();
                        }, 3000);
                        accountsCounts();
                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(exception);
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