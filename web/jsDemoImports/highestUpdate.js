/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$(document).ready(function () {
    $('td.errorH').css('color', '#fff');
    $('td.errorH').css('background-color', '#a93e3e');


    $('Highest-table tbody tr').each(function () {
        var $row = $(this);

        var val = $row.find(".errorHI input").val();

        if (parseInt(val, 10) === -1) {
           val.val("");
        }
    });

    $("#error-ageGroup tbody").on("change", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var femaleErorr = parseInt(row.find("#femaleError").val(), 10);
        var MaleError = parseInt(row.find("#maleError").val(), 10);
        //    var bothSexes = femaleErorr + MaleError;
        row.find("#bothSexesError").val(bothSexes);
        row.find("#bothSexesError").val(bothSexes);
        row.find(".errorH").css('background-color', 'pink');
    });

});
