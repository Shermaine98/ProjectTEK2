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
   
    $('#error-ageGroup tbody tr').each(function () {



        var $row = $(this);
        var  bothSexes = $row.find(".bothSexesError input").val();
        var femaleErorr = $row.find(".femaleSexesError input").val();
       var  maleError = $row.find(".maleSexesError input").val();



        if (parseInt(bothSexes, 10) === -1) {
            $row.find(".bothSexesError input").val("");
        } else {
            $row.find("#bothSexesError").val(bothSexes.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));


        }

        if (parseInt(femaleErorr, 10) === -1) {
            $row.find(".femaleSexesError input").val("");
        } else {
            $row.find("#femaleSexesError").val(femaleErorr.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));


        }
        if (parseInt(maleError, 10) === -1) {
            $row.find(".maleSexesError input").val("");
        } else {
            $row.find("#maleSexesError").val(maleError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

        }

    });



    $("#error-ageGroup tbody").on("change", 'input[type="text"]', function () {
        var row = $(this).closest("tr");
        var femaleErorr = parseInt(row.find("#maleSexesError").val().replace(/,/g, ''), 10);
        var MaleError = parseInt(row.find("#femaleSexesError").val().replace(/,/g, ''), 10);
        var bothSexes = femaleErorr + MaleError || 0;
        row.find("#bothSexesError").val(bothSexes);

        row.find(".bothSexesError").css('background-color', 'green');
        row.find(".maleSexesError").css('background-color', 'green');
        row.find(".femaleSexesError").css('background-color', 'green');

        var x = row.find("#bothSexesError").val();
        var y = row.find("#maleSexesError").val();
        var z = row.find("#femaleSexesError").val();

        row.find("#bothSexesError").val(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#maleSexesError").val(y.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#femaleSexesError").val(z.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

    });

});
