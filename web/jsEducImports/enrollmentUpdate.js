/* 
 *  ProjectTEK - DLSU CCS 2016
 */


$(document).ready(function () {


    $('#enrollemt-error tbody').each(function () {
        var all = false;

        $('#enrollemt-error tbody tr.maleE td.maleCountError input').each(function () {
            var $row = $(this);
            var maleCountError = $row.val();
            if (parseInt(maleCountError, 10) === -1 || maleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(maleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });


        $('#enrollemt-error tbody tr.femaleE td.femaleCountError input').each(function () {
            var $row = $(this);
            var femaleCountError = $row.val();

            if (parseInt(femaleCountError, 10) === -1 || femaleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.find("#femaleCountError").val(femaleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }
        });


        $('#enrollemt-error tbody tr.totalE td.totalCountError input').each(function () {
            var $row = $(this);
            var totalCountError = $row.val();
            if (parseInt(totalCountError, 10) === -1 || totalCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(totalCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });


        if (all === true) {
            $(this).find(".errorV").css('color', '#fff');
            $(this).find('.errorV').css('background-color', '#a93e3e');
        }
    });


    $("#enrollemt-error tbody").on("change", 'input[type="text"]', function () {
        var onChange = false;
         var body = $(this).closest('tbody');
        var totalMale = 0;
        var totalFemale = 0;
        var totaAll = 0;

        body.find('tr.maleE td.maleCountError input').each(function () {
            var $row = $(this);
            var maleCountError = $row.val();
            if (parseInt(maleCountError, 10) === -1 || maleCountError === "") {
                $row.val("");
                onChange = true;
            } else {
                $row.val(maleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

            totalMale += parseInt(maleCountError, 10) || 0;
        });


            body.find('tr.femaleE td.femaleCountError input').each(function () {
            var $row = $(this);
            var femaleCountError = $row.val();

            if (parseInt(femaleCountError, 10) === -1 || femaleCountError === "") {
                $row.val("");
                onChange = true;
            } else {
                $row.val(femaleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

            totalFemale += parseInt(femaleCountError, 10) || 0;
        });


        var totalEach = 0;
           body.find('tr.maleE td.maleCountError input').each(function (majorLoop) {
            var totalMaleError = $(this).val();
                body.find('tr.femaleE td.femaleCountError input').each(function (majorLoop2) {
                if (majorLoop === majorLoop2) {
                    var totalFemaleError = $(this).val();
                      body.find('tr.totalE td.totalCountError input').each(function (majorLoop3) {
                        if (majorLoop2 === majorLoop3) {
                            totalEach = parseInt(totalMaleError, 10) + parseInt(totalFemaleError, 10);
                            $(this).val(totalEach.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                            totalEach = 0;
                        }

                    });
                }
            });
        });

           body.find('tr.totalE td.totalCountError input').each(function () {
            var $row = $(this);
            var totalCountError = $row.val();
            if (parseInt(totalCountError, 10) === -1 || totalCountError === "") {
                $row.val("");
                onChange = true;
            } else {
                $row.val(totalCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }
            totaAll += parseInt(totalCountError, 10) || 0;
        });
        
       
        
        var getMTotal = body.find("tr.maleE td.totalMaleError input");
        var getTotal = body.find("tr.totalE td.grandTotalError input");
        var getFTotal = body.find("tr.femaleE td.femaleTotal input");

        var genderD = body.find("tr.gd td.GenderDisparityIndexError input");
       
        getTotal.val(totaAll.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        getFTotal.val(totalFemale.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        getMTotal.val(totalMale.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));

        genderD.val(Math.round(parseInt(totalFemale, 10) / parseInt(totalMale, 10) * 100) / 100);

        if (onChange === true) {
            body.find(".errorV").css('color', '#fff');
            body.find('.errorV').css('background-color', '#a93e3e');
        } else {
            body.find('.errorV').css('background-color', 'green');
        }

    });
});