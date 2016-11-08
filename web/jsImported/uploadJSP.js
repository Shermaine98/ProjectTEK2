/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


$(document).ready(function () {

    document.getElementById('year').setAttribute('value', new Date().getFullYear());

    document.getElementById('file').onchange = uploadOnChange;
    var isExcel = function (name) {
        return name.match(/xls$/i);
    };

    function uploadOnChange() {
        var filename = this.value;
        var lastIndex = filename.lastIndexOf("\\");
        if (lastIndex >= 0) {
            filename = filename.substring(lastIndex + 1);
        }

        if (filename !== "" && isExcel(filename)) {
            document.getElementById('filename').innerHTML = filename;
            document.getElementById('filename').style.fontStyle = 'italic';
            document.getElementById('filename').style.color = '#cc5200';
            document.getElementById('btnsubmit').style.display = "block";
        } else {
            alert('Please Choose Correct File');
            document.getElementById('filename').style.fontStyle = 'normal';
            document.getElementById('filename').style.color = '#333333';
            document.getElementById('filename').innerHTML = '<i class="fa fa-cloud-upload"></i> Upload File';
            document.getElementById('btnsubmit').style.display = "none";
        }
    }


    $("#UploadFileExcel").submit(function (event) {
        var page = document.getElementById('UplaodFilePage').value;
        var year = document.getElementById('year').value;
        var x = this;
        $.ajax({
            context: this,
            url: "UploadCheckerServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                censusYear: year,
                page: page
            }, success: function (data) {
                if (data === "approved") {
                    $("#modal-body").empty();
                    $("#modal-body").append('<p id="alerta">This file has already been uploaded and approved.</p>');
                    $("#modal-footer").append('<button id="cancelButtonA" type="button"class="btn btn-default" data-dismiss="modal">Close</button>');
                    
                $("#showModalWarning").modal("show");
                        $('#cancelButtonA').click(function () {
                        $('#showModalWarning').modal('hide');
                        $("#modal-body").empty();
                        $("#modal-footer").empty();
                    });

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

                    $('#submitButton').click(function () {
                        x.submit();
                    });
                } else {
                    this.submit();
                }

            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(exception);
            }
        });

        event.preventDefault();

    });
    
    $("#UploadFileExcelEnrollment").submit(function (event) {
        var page = document.getElementById('UplaodFilePage').value;
        var year = document.getElementById('year').value;
        var classification = document.getElementById('classification').value;

        var x = this;
        $.ajax({
            context: this,
            url: "UploadCheckerServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                censusYear: year,
                page: page,
                classification:classification
            }, success: function (data) {
                if (data === "approved") {
                    $("#modal-body").empty();
                    $("#modal-body").append('<p id="alerta">This file has already been uploaded and approved.</p>');
                    $("#modal-footer").append('<button id="cancelButtonA" type="button"class="btn btn-default" data-dismiss="modal">Close</button>');
                    
                $("#showModalWarning").modal("show");
                        $('#cancelButtonA').click(function () {
                        $('#showModalWarning').modal('hide');
                        $("#modal-body").empty();
                        $("#modal-footer").empty();
                    });

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

                    $('#submitButton').click(function () {
                        x.submit();
                    });
                } else {
                    this.submit();
                }

            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(exception);
            }
        });

        event.preventDefault();

    });

    //$(document).on("click", '#clickedViewApproval, #clickedReport, #clickedMissing', function () {

    $('#clickedViewApproval, #clickedReport, #clickedMissing').click(function () {
        if (this.id === 'clickedViewApproval') {
            var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                    .find(".nr")     // Gets a descendent with class="nr"
                    .text();         // Retrieves the text within <td>
            document.getElementById('aformID').setAttribute('value', $item);
            document.getElementById('archivedView').submit();
        } else if (this.id === 'clickedReport') {
            var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                    .find(".nr")     // Gets a descendent with class="nr"
                    .text();         // Retrieves the text within <td>
            document.getElementById('vcensusYear').setAttribute('value', $item);
            document.getElementById('archivedViewReport').submit();
        } else if (this.id === 'clickedMissing') {
            var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                    .find(".nr")     // Gets a descendent with class="nr"
                    .text();         // Retrieves the text within <td>
            document.getElementById('iformID').setAttribute('value', $item);
            document.getElementById('ErrorPage').submit();
        }
    });
});