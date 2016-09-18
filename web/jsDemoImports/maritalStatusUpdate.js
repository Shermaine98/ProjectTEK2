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
    $('td.singleError').css('color', '#fff');
    $('td.marriedError').css('color', '#fff');
    $('td.windowedError').css('color', '#fff');
    $('td.divorcedSeparatedError').css('color', '#fff');
    $('td.commonLawLiveInError').css('color', '#fff');
    $('td.unknownError').css('color', '#fff');
    $('td.totalError').css('color', '#fff');


    $("#error_maritalStatus tbody").on("ready", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var singleError = parseInt(row.find("#singleError").val(), 10);
        var marriedError = parseInt(row.find("#marriedError").val(), 10);
        var windowedError = parseInt(row.find("#windowedError").val(), 10);
        var divorcedSeparatedError = parseInt(row.find("#divorcedSeparatedError").val(), 10);
        var commonLawLiveInError = parseInt(row.find("#commonLawLiveInError").val(), 10);
        var unknownError = parseInt(row.find("#unknownError").val(), 10);
        var totalError = parseInt(row.find("#totalError").val(), 10);


        if (singleError === -1) {
            row.find("#femaleError").val(0);
        }
        if (marriedError === -1)
            row.find("#maleError").val(0);

        if (windowedError === -1)
            row.find("#maleError").val(0);

        if (divorcedSeparatedError === -1)
            row.find("#maleError").val(0);

        if (commonLawLiveInError === -1)
            row.find("#maleError").val(0);

        if (unknownError === -1)
            row.find("#maleError").val(0);
        if (totalError === -1)
            row.find("#maleError").val(0);
    });

    $("#error_maritalStatus tbody").on("change", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var singleError = parseInt(row.find("#singleError").val(), 10);
        var marriedError = parseInt(row.find("#marriedError").val(), 10);
        var windowedError = parseInt(row.find("#windowedError").val(), 10);
        var divorcedSeparatedError = parseInt(row.find("#divorcedSeparatedError").val(), 10);
        var commonLawLiveInError = parseInt(row.find("#commonLawLiveInError").val(), 10);
        var unknownError = parseInt(row.find("#unknownError").val(), 10);

        var totalError = parseInt(row.find("#totalError").val(), 10);

        var bothSexes = singleError + marriedError + windowedError + divorcedSeparatedError + commonLawLiveInError + unknownError;
        row.find("#totalError").val(totalError);

        row.find(".singleError").css('background-color', 'pink');
        row.find(".marriedError").css('background-color', 'pink');
        row.find(".windowedError").css('background-color', 'pink');
        row.find(".divorcedSeparatedError").css('background-color', 'pink');
        row.find(".commonLawLiveInError").css('background-color', 'pink');
        row.find(".unknownError").css('background-color', 'pink');
        row.find(".totalError").css('background-color', 'pink');
    });

});
