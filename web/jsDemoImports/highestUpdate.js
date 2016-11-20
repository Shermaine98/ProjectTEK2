/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$(document).ready(function () {
    $('#Highest-table tbody tr.data').each(function () {
        var $row = $(this);
        var countError = $row.find(".countError input").val();

        var countError2 = $row.find(".countError2 input").val();


        if (parseInt(countError, 10) === -1 || countError === "" ) {
            $row.find(".countError input").val("");
            $row.find(".countError2 input").each(function (index, value) {
                $row.find('.errorH2').css('color', '#fff');
                $row.find('.errorH2').css('background-color', '#a93e3e');
            });
        } else {
            $row.find("#countError").val(countError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

        }

        if (parseInt(countError2, 10) === -1 || countError2 === "" ) {
            $row.find(".countError2 input").val("");
            $row.find(".countError2 input").each(function (index, value) {
                $row.find('.errorH2').css('color', '#fff');
                $row.find('.errorH2').css('background-color', '#a93e3e');
            });
        } else {
            $row.find("#countError2").val(countError2.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

        }
    });

    $("#Highest-table tbody").on("change", 'input[type="text"]', function () {

        var $row = $(this).closest("tr.data");
        var countError = parseInt($row.find("#countError").val().replace(/,/g, ''), 10);
        console.log(countError);
        //  var countError2 = parseInt($row.find("#countError2").val().replace(/,/g, ''), 10);

        var x = false;
        $row.find(".countError input").each(function (index, value) {
            if ($(this).val() === -1 || $(this).val() === "") {
                x = true;
            }
        });

        $row.find(".countError2 input").each(function (index, value) {
            if ($(this).val() === -1 || $(this).val() === "") {
                x = true;
            }
        });



        if (x === false) {
            $row.find(".countError input").each(function (index, value) {
                $row.find('.errorH2').css('background-color', 'green');
                console.log(x);
            });

            $row.find(".countError2 input").each(function (index, value) {
                $row.find('.errorH2').css('background-color', 'green');
                console.log(x);
            });

        } else {
            $row.find(".countError input").each(function (index, value) {
                $row.find('.errorH2').css('color', '#fff');
                $row.find('.errorH2').css('background-color', '#a93e3e');
            });

            $row.find(".countError2 input").each(function (index, value) {
                $row.find('.errorH2').css('color', '#fff');
                $row.find('.errorH2').css('background-color', '#a93e3e');
            });
        }


        var x = $row.find("#countError").val();

        var y = $row.find("#countError2").val();

        $row.find("#countError").val(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        $row.find("#countError2").val(y.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    });

});
