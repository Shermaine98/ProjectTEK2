/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */

$(document).ready(function () {
    $("#submitAll").submit(function (event) {
        var page = document.getElementById('page').value;
        var classification = document.getElementById('classification').value;

        var x = this;
        $.ajax({
            context: this,
            url: "UploadCheckerServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                page: page,
                classification: classification
            }, success: function (data) {
         
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

                } else if (data === "reUpload") {
                    $("#modal-body").empty();
                    $("#modal-body").append('<p>This file has already been uploaded. Are you sure you want to reupload?</p>');
                    $("#footer").append('<button id="cancelButton" type="button"class="btn btn-default" data-dismiss="modal">Cancel</button>');
                    $("#footer").append('<button id="submitButton" type="button"class="btn btn-default" data-dismiss="modal">Submit</button>');
                    $("#showModalWarning").modal("show");

                    $('#cancelButton').click(function () {
                        $('#showModalWarning').modal('hide');
                        $("#modal-body").empty();
                        $("#footer").empty();
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

});