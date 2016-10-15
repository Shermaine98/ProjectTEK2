/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    accountsCounts();

    $('#clickedApproved, #clickedReject').click(function () {
        if (this.id == 'clickedApproved') {
            var redirect = "approve";
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
                    if (data === true)
                        alert("Sucess Approval");
                    accountsCounts();
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(exception);
                }
            });

        } else if (this.id == 'clickedReject') {
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
                    if (data === true)
                        alert("Reject Success");
                    accountsCounts();
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
            //  console.log("coutns" + );
            // console.log( "coutns" + );

        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(exception);
        }
    });
}