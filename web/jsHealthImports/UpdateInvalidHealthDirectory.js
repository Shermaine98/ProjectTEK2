/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


$(document).ready(function () {
        var page = document.getElementById('page').value;
    $.ajax({
        url: "UploadCheckerDirectory",
        type: 'POST',
        dataType: "JSON",
        data: {
            page: page
        }, success: function (data) {
            console.log(data);
            if (data === "approved") {
                $("#modal-body").empty();
                $("#modal-body").append('<p id="alerta">This file has already been uploaded and approved.</p>');
                $("#footer").append('<button id="cancelButtonA" type="button"class="btn btn-default" data-dismiss="modal">Close</button>');

                $("#showModalWarning").modal("show");
                $('#cancelButtonA').click(function () {
                    $('#showModalWarning').modal('hide');
                    $("#modal-body").empty();
                    $("#footer").empty();
                });

                $('.approvedDisabled').prop('disabled', true);

            } 
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(exception);
        }
    });
});

$(document).on("click", "#invalidDirectory", function () {
    var redirect = "invalid";
    var schoolName = $(this).closest("tr").find(".nr").text();
    var censusYear = $(this).closest("tr").find(".censusYear input").val();

    var whichtr = $(this).closest("tr");
    whichtr.remove();
    $.ajax({
        url: "UpdateHealthDirectory",
        type: 'POST',
        dataType: "JSON",
        data: {
            redirect: redirect,
            censusYear: censusYear,
            schoolName: schoolName
        },
        success: function (data) {
            if (data === true) {
                $("#notificationHeader").text("Success!");
                $("#modalHeader").css({background: "#00a65a"});
                $("#notificationHeader").css({color: "#FFFFFF"});
                $("#notificationBodyModal").empty();
                $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'>Directory Deleted</p>");
                $("#notificationModalFooter").empty();
                $("#notificationModalFooter").append(' <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                $("#notificationModal").modal("show");
            } else {
                console.log("Not Sucessfuly Deleted");
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(textStatus);
        }
    });
});


$(document).on("click", "#edit", function () {

    var hospitalName = $(this).closest("tr").find(".nr").text();
    var telephone = $(this).closest("tr").find(".telephone").text();
    var doctor = $(this).closest("tr").find(".doctor").text();
    var address = $(this).closest("tr").find(".address").text();
    var nurses = $(this).closest("tr").find(".nurses").text();
    var midwives = $(this).closest("tr").find(".midwives").text();
    var beds = $(this).closest("tr").find(".bed").text();
    var classification = $(this).closest("tr").find(".classificationL").text();
   
    var category = $(this).closest("tr").find(".category").text();
    var accreditation = $(this).closest("tr").find(".accreditation").text();
    var year = $(this).closest("tr").find(".censusYear input").val();

    $("#info").empty();
    $("#tableUpdateBody").empty();
    $("#UpdateModal").modal("show");
    

    if (classification === "Government Hospital") {
         console.log(classification);
        $("#info").append('<label for="classification" style="width: 35%;">Hospital Classification: </label>');
        $("#info").append('<select required class="form-control" style="width: 55%;" name="classification">\n\
                                                     <option value="Government Hospital">Government Hospital</option> \n\
                                                    <option value="Private Hospital">Private Hospital</option> \n\
                                                </select><br/><br/>');
    } else {
        $("#info").append('<label for="classification" style="width: 35%;">Hospital Classification: </label>');
        $("#info").append('<select required class="form-control" style="width: 55%;" name="classification">\n\
                                                     <option value="Private Hospital">Private Hospital</option> \n\
                                                    <option value="Government Hospital">Government Hospital</option>\n\
                                                </select><br/><br/>');
    }


    if (category === "Level 1") {
        $("#info").append('<label for="category" style="width: 35%;">Hospital Category: </label>');
        $("#info").append('<select required class="form-control" style="width: 55%;" name="category">\n\
                                                 <option value="Level 1">Level 1</option>\n\
                                                    <option value="Level 2">Level 2</option>\n\
                                                    <option value="Level 3">Level 3</option>\n\
                                                     <option value="Level 4">Level 4</option>\n\
                                                </select><br/><br/>');
    } else if (category === "Level 2") {
        $("#info").append('<label for="category" style="width: 35%;">Hospital Category: </label>');
        $("#info").append('<select required class="form-control" style="width: 55%;" name="category">\n\
                                                     <option value="Level 2">Level 2</option>\n\
                                                    <option value="Level 1">Level 1</option>\n\
                                                    <option value="Level 3">Level 3</option>\n\
                                                    <option value="Level 4">Level 4</option>\n\
                                                </select><br/><br/>');
    } else if (category === "Level 3") {
        $("#info").append('<label for="category" style="width: 35%;">Hospital Category: </label>');
        $("#info").append('<select required class="form-control" style="width: 55%;" name="category">\n\
                                                   <option value="Level 3">Level 3</option>\n\
                                                    <option value="Level 1">Level 1</option>\n\
                                                    <option value="Level 1">Level 2</option>\n\
                                                    <option value="Level 4">Level 4</option>\n\
                                                </select><br/><br/>');
    } else if (category === "Level 4") {
        $("#info").append('<label for="category" style="width: 35%;">Hospital Category: </label>');
        $("#info").append('<select required class="form-control" style="width: 55%;" name="category">\n\
                                                   <option value="Level 4">Level 4</option>\n\
                                                    <option value="Level 1">Level 1</option>\n\
                                                    <option value="Level 2">Level 2</option>\n\
                                                        <option value="Level 3">Level 3</option>\n\
                                                </select><br/><br/>');
    }

    if (accreditation === "true") {
        $("#info").append('<div style="float:left">');
        $("#info").append('<label for="accredited" style="width: 35%;">Accreditation: </label>\n\
                <input type="radio" name="accredited" value="true" checked="checked"> True');
        $("#info").append('<input style="display: inline; margin-left: 3%;" type="radio" name="accredited" value="false"> False<br></div>');

    } else {
        $("#info").append('<div style="float:left">');
        $("#info").append('<label for="accredited" style="width: 35%;">Accreditation: </label>\n\
                <input type="radio" name="accredited" value="false" checked="checked"> False');
        $("#info").append('<input style="display: inline; margin-left: 3%;" type="radio" name="accredited" value="true"> True<br></div>');
    }
    $("#info").append('<label for="hospitalName" style="width: 35%;">Hospital Name: </label>\n\
<textarea class="form-control" name="hospitalName" readonly style="resize:none; width:55%; margin-bottom:1%;">' + hospitalName + '</textarea>');
    $("#info").append('<label for="address" style="width: 35%;">Hospital Address: </label>\n\
<textarea class="form-control" name="address" readonly style="resize:none; width:55%; margin-bottom:1%;">' + address + '</textarea>');
    $("#info").append('<label for="censusYear" style="width: 35%;">Telephone/Fax Number: </label>\n\
<input type="hidden" name="censusYear" value="' + year + '"  /> \n\
                            <input type="text" class="form-control" name="tel" value="' + telephone + '" style="width: 55%; margin-bottom:2%;" required />');
    $("#tableUpdateBody").append('<tr>');
    $("#tableUpdateBody").append('<td><input type="text" class="form-control" name="doctors" value="' + doctor + '" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
    $("#tableUpdateBody").append('<td><input type="text" class="form-control" name="nurses" value="' + nurses + '" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
    $("#tableUpdateBody").append('<td><input type="text" class="form-control" name="midwives" value="' + midwives + '" onkeypress= "return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
    $("#tableUpdateBody").append('<td><input type="text" class="form-control" name="beds" value="' + beds + '"  onkeypress= "return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
    $("#tableUpdateBody").append('</tr>');

});
