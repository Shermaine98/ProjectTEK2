/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $(document).on("click", "#invalidDirectory", function () {
        var redirect = "invalid";
        var schoolName = $(this).closest("tr").find(".nr").text();
        var censusYear = document.getElementById("censusYear").value;

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
                if (data === true)
                    alert("Directory Deleted");
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
        var classification = $(this).closest("tr").find(".classification").text();
        var category = $(this).closest("tr").find(".category").text();
        var accreditation = $(this).closest("tr").find(".accreditation").text();
        var year = document.getElementById('censusYear').value;

//        alert(doctor);
        $("#info").empty();
        $("#tableUpdateBody").empty();
        $("#UpdateModal").modal("show");


        if (classification === "Government Hospital") {
            $("#info").append('<label for="classification" style="width: 35%;">Hospital Classification: </label>');
            $("#info").append('<select required class="form-control" style="width: 55%;" name="classification">\n\
                                                     <option value="' + classification + '">' + classification + '</option> \n\
                                                    <option value="private">Private Hospital</option> \n\
                                                </select><br/><br/>');
        } else {
            $("#info").append('<label for="classification" style="width: 35%;">Hospital Classification: </label>');
            $("#info").append('<select required class="form-control" style="width: 55%;" name="classification">\n\
                                                     <option value="' + classification + '">' + classification + '</option> \n\
                                                    <option value="public">Government Hospital</option>\n\
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
        $("#tableUpdateBody").append('<td><input type="text" class=form-control name="doctors" value="' + doctor + '" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
        $("#tableUpdateBody").append('<td><input type="text" class=form-control name="nurses" value="' + nurses + '" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
        $("#tableUpdateBody").append('<td><input type="text" class="form-control name="midwives" value="' + midwives + '" onkeypress= "return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
        $("#tableUpdateBody").append('<td><input type="text" class="form-control" name="beds" value="' + beds + '"  onkeypress= "return event.charCode >= 48 && event.charCode <= 57" required  /></td>');
        $("#tableUpdateBody").append('</tr>');

    });

});