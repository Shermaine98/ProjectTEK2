/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


$(document).ready(function () {

    $('#nutritional-table-error tbody').each(function () {

        $(this).find(".ErrorC").css('color', '#fff');
        $(this).find('.ErrorC').css('background-color', '#a93e3e');

        var all = false;


        $('#nutritional-table-error tbody tr.trMale td.totalMaleError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var maleCountError = $row.val();
            if (parseInt(maleCountError, 10) === -1 || maleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(maleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });

        $('#nutritional-table-error tbody tr.trMale td.pupilsWeighedMaleError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var maleCountError = $row.val();
            if (parseInt(maleCountError, 10) === -1 || maleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(maleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });

        $('#nutritional-table-error tbody tr.trMale td.maleCountError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var maleCountError = $row.val();
            if (parseInt(maleCountError, 10) === -1 || maleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(maleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });


        $('#nutritional-table-error tbody tr.trFemale td.totalFemaleError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var femaleCountError = $row.val();
            if (parseInt(femaleCountError, 10) === -1 || femaleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(femaleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });

        $('#nutritional-table-error tbody tr.trFemale td.totalFemaleError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var femaleCountError = $row.val();
            if (parseInt(femaleCountError, 10) === -1 || femaleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(femaleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });


        $('#nutritional-table-error tbody tr.trFemale td.pupilsWeighedFemaleError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var femaleCountError = $row.val();
            if (parseInt(femaleCountError, 10) === -1 || femaleCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(femaleCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });

        $('#nutritional-table-error tbody tr.trTotal td.totalCountError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var bTotalCountError = $row.val();
            if (parseInt(bTotalCountError, 10) === -1 || bTotalCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(bTotalCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });



        $('#nutritional-table-error tbody tr.trTotal td.pupilsWeighedTotalError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var bTotalCountError = $row.val();
            if (parseInt(bTotalCountError, 10) === -1 || bTotalCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(bTotalCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });




        $('#nutritional-table-error tbody tr.trTotal td.bTotalCountError input').each(function () {
            var $row = $(this);
            console.log($row.val());
            var bTotalCountError = $row.val();
            if (parseInt(bTotalCountError, 10) === -1 || bTotalCountError === "") {
                $row.val("");
                all = true;
            } else {
                $row.val(bTotalCountError.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            }

        });


        if (all === true) {
            $(this).find(".ErrorC").css('color', '#fff');
            $(this).find('.ErrorC').css('background-color', '#a93e3e');
        }
    });


    $("#nutritional-table-error tbody").on("change", 'input[type="text"]', function () {
        var onChange = false;
        var body = $(this).closest('tbody');
        var totalMale = 0;
        var totalFemale = 0;
        var totaAll = 0;

        body.find('tr.trMale td.maleCountError input').each(function () {
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


        body.find('tr.trFemale td.femaleCountError input').each(function () {
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
        body.find('tr.trMale td.maleCountError input').each(function (majorLoop) {
            var totalMaleError = $(this).val();
            body.find('tr.trFemale td.femaleCountError input').each(function (majorLoop2) {
                if (majorLoop === majorLoop2) {
                    var totalFemaleError = $(this).val();
                    body.find('tr.trTotal td.bTotalCountError input').each(function (majorLoop3) {
                        if (majorLoop2 === majorLoop3) {
                            totalEach = parseInt(totalMaleError, 10) + parseInt(totalFemaleError, 10) || 0;
                            $(this).val(totalEach.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                            totalEach = 0;
                        }

                    });
                }
            });
        });

        body.find('tr.trTotal td.bTotalCountError input').each(function () {
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


        var wME = body.find("tr.trMale td.pupilsWeighedMaleError input");
        var wFE = body.find("tr.trFemale td.pupilsWeighedFemaleError input");
        var wTE = body.find("tr.trTotal td.pupilsWeighedTotalError input");


        var getMTotal = body.find("tr.trMale td.totalMaleError input");
        var getTotal = body.find("tr.trTotal td.totalCountError input");
        var getFTotal = body.find("tr.trFemale td.totalFemaleError input");


        getTotal.val(totaAll.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        getFTotal.val(totalFemale.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        getMTotal.val(totalMale.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));


        wME.val(totalMale.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        wFE.val(totalFemale.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        
        wTE.val(totaAll.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));


        if (onChange === true) {
            body.find(".ErrorC").css('color', '#fff');
            body.find('.ErrorC').css('background-color', '#a93e3e');
        } else {
            body.find('.ErrorC').css('background-color', 'green');
        }

    });



});
