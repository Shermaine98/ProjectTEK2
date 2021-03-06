/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
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
        var singleError = row.find(".singleError input").val();
        var marriedError = row.find(".marriedError input").val();
        var windowedError = row.find(".windowedError input").val();
        var divorcedSeparatedError = row.find(".divorcedSeparatedError input").val();
        var commonLawLiveInError = row.find(".commonLawLiveInError input").val();
        var unknownError = row.find(".unknownError input").val();
        var totalError = row.find(".totalError input").val();

        if (parseInt(singleError, 10) === -1) {
            row.find(".singleError input").val("");
        } else {
            row.find("#singleError").val(singleError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }


        if (parseInt(marriedError, 10) === -1) {
            row.find(".marriedError input").val("");
        } else {
            row.find("#marriedError").val(marriedError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }


        if (parseInt(windowedError, 10) === -1) {
            row.find(".windowedError input").val("");
        } else {
            row.find("#windowedError").val(windowedError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }

        if (parseInt(divorcedSeparatedError, 10) === -1) {
            row.find(".divorcedSeparatedError input").val("");
        } else {
            row.find("#divorcedSeparatedError").val(divorcedSeparatedError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }


        if (parseInt(commonLawLiveInError, 10) === -1) {
            row.find(".commonLawLiveInError input").val("");
        } else {
            row.find("#commonLawLiveInError").val(commonLawLiveInError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }

        if (parseInt(unknownError, 10) === -1) {
            row.find(".unknownError input").val("");
        } else {
            row.find("#unknownError").val(unknownError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }
        if (parseInt(totalError, 10) === -1) {
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


        var a = row.find("#singleError").val();
        var b = row.find("#marriedError").val();
        var c = row.find("#windowedError").val();
        var d = row.find("#divorcedSeparatedError").val();
        var e = row.find("#commonLawLiveInError").val();
        var f = row.find("#unknownError").val();
        var g = row.find("#totalError").val();

        var z = false;


        if (a === "" || b === "" ||  c === "" || d === "" ||  e === "" || f === "" ||  a === "" || g === ""){

            z = true;
        }


        if (z === false) {
            row.find(".singleError").css('background-color', 'green');
            row.find(".marriedError").css('background-color', 'green');
            row.find(".windowedError").css('background-color', 'green');
            row.find(".divorcedSeparatedError").css('background-color', 'green');
            row.find(".commonLawLiveInError").css('background-color', 'green');
            row.find(".unknownError").css('background-color', 'green');
            row.find(".totalError").css('background-color', 'green');
            row.find(".reasonNum").css('background-color', 'green');

        } else {
            row.find('.singleError').css('background-color', '#a93e3e');
            row.find('.marriedError').css('background-color', '#a93e3e');
            row.find('.windowedError').css('background-color', '#a93e3e');
            row.find('.divorcedSeparatedError').css('background-color', '#a93e3e');
            row.find('.commonLawLiveInError').css('background-color', '#a93e3e');
            row.find('.unknownError').css('background-color', '#a93e3e');
            row.find('.totalError').css('background-color', '#a93e3e');
            row.find('.reasonNum').css('background-color', '#a93e3e');
            row.find('.singleError').css('color', '#fff');
            row.find('.marriedError').css('color', '#fff');
            row.find('.windowedError').css('color', '#fff');
            row.find('.divorcedSeparatedError').css('color', '#fff');
            row.find('.commonLawLiveInError').css('color', '#fff');
            row.find('.unknownError').css('color', '#fff');
            row.find('.totalError').css('color', '#fff');
            row.find('.reasonNum').css('color', '#fff');
        }





        row.find("#singleError").val(a.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#marriedError").val(b.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#windowedError").val(c.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#divorcedSeparatedError").val(d.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#commonLawLiveInError").val(e.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#unknownError").val(f.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#totalError").val(g.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

    });
});