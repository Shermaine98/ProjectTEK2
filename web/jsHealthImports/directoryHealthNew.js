/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $("#addhospitalT tbody").on("change", 'input[type="text"]', function () {
        var row = $(this).closest("tr");

        var x = row.find("#doctors").val();
        var y = row.find("#nurses").val();
        var z = row.find("#midwives").val();
        var a = row.find("#beds").val();

        row.find("#doctors").val(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#nurses").val(y.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#midwives").val(z.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        row.find("#beds").val(a.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

    });

});