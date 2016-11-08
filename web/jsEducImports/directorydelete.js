/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
        var page = document.getElementById('page').value;
        var classification = document.getElementById('classification').value;
    $.ajax({
        url: "UploadCheckerDirectory",
        type: 'POST',
        dataType: "JSON",
        
         data: {
                page: page,
                classification: classification
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
                
                $('.upadateBtn').prop('disabled', true);
                $('.deleteBtn').prop('disabled', true);
                $('#btnsubmit').prop('disabled', true);
                $('#addnew').prop('disabled', true);

            } else if (data === "reUpload") {
                $("#modal-body").empty();
                $("#modal-body").append('<p>This file has already been uploaded. Are you sure you want to reupload?</p>');
                $("#modal-footer").append('<button id="cancelButton" type="button"class="btn btn-default" data-dismiss="modal">Cancel</button>');
                $("#modal-footer").append('<button id="submitButton" type="button"class="btn btn-default" data-dismiss="modal">Submit</button>');
                $("#showModalWarning").modal("show");

                $('#cancelButton').click(function () {
                    $('#showModalWarning').modal('hide');
                    $("#modal-body").empty();
                    $("#modal-footer").empty();
                });
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(exception);
        }
    });
});
    $(document).on("click", "#invalidDirectory", function () {
        var redirect = "invalid";
        var schoolName = $(this).closest("tbody").find(".nr").text();
        var classification = document.getElementById("classification").value;
        var censusYear = document.getElementById("censusYear").value;
        $(this).closest("tbody").remove();

        $.ajax({
            url: "UpdateSchoolDirectory",
            type: 'POST',
            dataType: "JSON",
            data: {
                redirect: redirect,
                censusYear: censusYear,
                schoolName: schoolName,
                classification: classification
            },
            success: function (data) {
                if (data === true) {
                    alert("Directory Deleted");
                } else {
                    alert("Not Sucessfuly Deleted");
                }
            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(XMLHttpRequest);
            }
        });
    });

    $(document).on("click", "#updateDirectory", function () {
        $("#UpdateModal").modal("show");
        var schoolName = $(this).closest("tbody").find(".nr").text();
        $('#schoolName').val(schoolName);

    });
