/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $('td.bothSexesError').css('background-color', '#a93e3e');
    $('td.maleSexesError').css('background-color', '#a93e3e');
    $('td.femaleSexesError').css('background-color', '#a93e3e');
    $('td.bothSexesError').css('color', '#fff');
    $('td.maleSexesError').css('color', '#fff');
    $('td.femaleSexesError').css('color', '#fff');


    $("#error-ageGroup tbody").on("ready", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var femaleErorr = parseInt(row.find("#femaleError").val(), 10);
        var MaleError = parseInt(row.find("#maleError").val(), 10);
        if (femaleErorr === -1) {
            row.find("#femaleError").val(0);
        }
        if (MaleError === -1)
            row.find("#maleError").val(0);
    });

    $("#error-ageGroup tbody").on("change", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var femaleErorr = parseInt(row.find("#femaleError").val(), 10);
        var MaleError = parseInt(row.find("#maleError").val(), 10);
        var bothSexes = femaleErorr + MaleError;
        row.find("#bothSexesError").val(bothSexes);
        row.find("#bothSexesError").val(bothSexes);
        row.find(".bothSexesError").css('background-color', 'pink');
        row.find(".maleSexesError").css('background-color', 'pink');
        row.find(".femaleSexesError").css('background-color', 'pink');
    });

});
