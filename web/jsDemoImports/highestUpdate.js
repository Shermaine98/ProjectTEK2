/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$(document).ready(function () {
    
    
    
     $('#Highest-table tbody').each(function () {

        $(this).find(".errorH2").css('color', '#fff');
        $(this).find('.errorH2').css('background-color', '#a93e3e');

        var all = false;

        $('#Highest-table tbody tr.data td.countError input').each(function () {
            var $row = $(this);
            var maleCountError = $row.val();
            if (parseInt(maleCountError, 10) === -1 || maleCountError === "") {
                console.log("LALALA");
                $row.val("");
                all = true;
            } else {
                $row.val(maleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });


        $('#Highest-table tbody tr.data td.countError2 input').each(function () {
            var $row = $(this);
            var femaleCountError = $row.val();

            if (parseInt(femaleCountError, 10) === -1 || femaleCountError === "") {
                $row.val("");
                 console.log("LALALA");
                all = true;
            } else {
                $row.val(femaleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }
        });
        
        if (all === true) {
            $(this).find(".errorH2").css('color', '#fff');
            $(this).find('.errorH2').css('background-color', '#a93e3e');
        }

    });

    $("#Highest-table tbody").on("change", 'input[type="text"]', function () {

        var $row = $(this).closest('tbody');
        var countError = parseInt($row.find("#countError").val().replace(/,/g, ''), 10);
        console.log(countError);
        //  var countError2 = parseInt($row.find("#countError2").val().replace(/,/g, ''), 10);

        var x = false;
        $row.find("tr.data td.countError input").each(function (index, value) {
            if ($(this).val() === -1 || $(this).val() === "") {
                x = true;
            }
        });

        $row.find("tr.data td.countError2 input").each(function (index, value) {
            if ($(this).val() === -1 || $(this).val() === "") {
                x = true;
            }
        });



        if (x === false) {
            $row.find("tr.data td.countError input").each(function (index, value) {
                $row.find('.errorH2').css('background-color', 'green');
                console.log(x);
            });

            $row.find("tr.data td.countError2 input").each(function (index, value) {
                $row.find('.errorH2').css('background-color', 'green');
                console.log(x);
            });

        } else {
            $row.find("tr.data td.countError input").each(function (index, value) {
                $row.find('.errorH2').css('color', '#fff');
                $row.find('.errorH2').css('background-color', '#a93e3e');
            });

            $row.find("tr.data td.countError2 input").each(function (index, value) {
                $row.find('.errorH2').css('color', '#fff');
                $row.find('.errorH2').css('background-color', '#a93e3e');
            });
        }


        var x = $row.val();

        var y = $row.val();

        $row.val(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        $row.val(y.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    });

});
