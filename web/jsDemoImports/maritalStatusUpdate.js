/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('td.singleError').css('background-color', '#a93e3e');
    $('td.marriedError').css('background-color', '#a93e3e');
    $('td.windowedError').css('background-color', '#a93e3e');
    $('td.divorcedSeparatedError').css('background-color', '#a93e3e');
    $('td.commonLawLiveInError').css('background-color', '#a93e3e');
    $('td.unknownError').css('background-color', '#a93e3e');
    $('td.totalError').css('background-color', '#a93e3e');
     $('td.reasonNum').css('background-color', '#a93e3e');
    $('td.singleError').css('color', '#fff');
    $('td.marriedError').css('color', '#fff');
    $('td.windowedError').css('color', '#fff');
    $('td.divorcedSeparatedError').css('color', '#fff');
    $('td.commonLawLiveInError').css('color', '#fff');
    $('td.unknownError').css('color', '#fff');
    $('td.totalError').css('color', '#fff');
     $('td.reasonNum').css('color', '#fff');


    $('#error_maritalStatus tbody tr').each(function () {
        var row = $(this);
        var singleError = parseInt(row.find(".singleError input").val(), 10);
        var marriedError = parseInt(row.find(".marriedError input").val(), 10);
        var windowedError = parseInt(row.find(".windowedError input").val(), 10);
        var divorcedSeparatedError = parseInt(row.find(".divorcedSeparatedError input").val(), 10);
        var commonLawLiveInError = parseInt(row.find(".commonLawLiveInError input").val(), 10);
        var unknownError = parseInt(row.find(".unknownError input").val(), 10);
        var totalError = parseInt(row.find(".totalError input").val(), 10);

        if (singleError === -1) {
            row.find(".singleError input").val("");
        } else {
            row.find("#singleError").val(singleError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }


        if (marriedError === -1) {
            row.find(".marriedError input").val("");
        } else {
            row.find("#marriedError").val(marriedError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }


        if (windowedError === -1) {
            row.find(".windowedError input").val("");
        } else {
            row.find("#windowedError").val(windowedError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }

        if (divorcedSeparatedError === -1) {
            row.find(".divorcedSeparatedError input").val("");
        } else {
            row.find("#divorcedSeparatedError").val(divorcedSeparatedError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }


        if (commonLawLiveInError === -1) {
            row.find(".commonLawLiveInError input").val("");
        } else {
            row.find("#commonLawLiveInError").val(commonLawLiveInError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }

        if (unknownError === -1) {
            row.find(".unknownError input").val("");
        } else {
            row.find("#unknownError").val(unknownError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }
        if (totalError === -1) {
            row.find(".totalError input").val("");
        } else {
            row.find("#totalError").val(totalError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }

    });
    $("#error_maritalStatus tbody").on("change", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var singleError = parseInt(row.find("#singleError").val().replace(/,/g, ''), 10);
        var marriedError = parseInt(row.find("#marriedError").val().replace(/,/g, ''), 10);
        var windowedError = parseInt(row.find("#windowedError").val().replace(/,/g, ''), 10);
        var divorcedSeparatedError = parseInt(row.find("#divorcedSeparatedError").val().replace(/,/g, ''), 10);
        var commonLawLiveInError = parseInt(row.find("#commonLawLiveInError").val().replace(/,/g, ''), 10);
        var unknownError = parseInt(row.find("#unknownError").val().replace(/,/g, ''), 10);
        //  var totalError = parseInt(row.find("#totalError").val().replace(/,/g, ''), 10);

        var totalError = singleError + marriedError + windowedError + divorcedSeparatedError + commonLawLiveInError + unknownError || 0;
        row.find("#totalError").val(totalError);


        row.find(".singleError").css('background-color', 'green');
        row.find(".marriedError").css('background-color', 'green');
        row.find(".windowedError").css('background-color', 'green');
        row.find(".divorcedSeparatedError").css('background-color', 'green');
        row.find(".commonLawLiveInError").css('background-color', 'green');
        row.find(".unknownError").css('background-color', 'green');
        row.find(".totalError").css('background-color', 'green');
        row.find(".reasonNum").css('background-color', 'green');
        
        var a = row.find("#singleError").val();
        var b = row.find("#marriedError").val();
        var c = row.find("#windowedError").val();
        var d = row.find("#divorcedSeparatedError").val();
        var e = row.find("#commonLawLiveInError").val();
        var f = row.find("#unknownError").val();
        var g = row.find("#totalError").val();

        row.find("#singleError").val(a.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#marriedError").val(b.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#windowedError").val(c.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#divorcedSeparatedError").val(d.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#commonLawLiveInError").val(e.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#unknownError").val(f.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#totalError").val(g.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

    });
});

function colorCheck(row) {
    var singleError = parseInt(row.find(".singleError input").val(), 10);
    var marriedError = parseInt(row.find(".marriedError input").val(), 10);
    var windowedError = parseInt(row.find(".windowedError input").val(), 10);
    var divorcedSeparatedError = parseInt(row.find(".divorcedSeparatedError input").val(), 10);
    var commonLawLiveInError = parseInt(row.find(".commonLawLiveInError input").val(), 10);
    var unknownError = parseInt(row.find(".unknownError input").val(), 10);
    var totalError = parseInt(row.find(".totalError input").val(), 10);

    if (singleError.val=="") {
        row.find(".singleError").css('background-color', 'red');
        row.find(".marriedError").css('background-color', 'red');
        row.find(".windowedError").css('background-color', 'red');
        row.find(".divorcedSeparatedError").css('background-color', 'red');
        row.find(".commonLawLiveInError").css('background-color', 'red');
        row.find(".unknownError").css('background-color', 'red');
        row.find(".totalError").css('background-color', 'red');
    } else {
        row.find(".singleError").css('background-color', 'green');
        row.find(".marriedError").css('background-color', 'green');
        row.find(".windowedError").css('background-color', 'green');
        row.find(".divorcedSeparatedError").css('background-color', 'green');
        row.find(".commonLawLiveInError").css('background-color', 'green');
        row.find(".unknownError").css('background-color', 'green');
        row.find(".totalError").css('background-color', 'green');
    }
}