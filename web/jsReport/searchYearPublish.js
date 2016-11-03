/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    if ($("#reportTitle").length != 0) {
        var year = document.getElementById('reportYear').value;
        var sector = document.getElementById('reportSector').value;
        var reportTitle = document.getElementById('reportTitle').value;

        if (reportTitle == "Matrix") {
            document.getElementById('integrateData').style.display = "none";
            document.getElementById('contentHere').style.display = "block";
            setMatrixS(year, sector);
        } else if (reportTitle == "Analysis") {
            document.getElementById('integrateData').style.display = "none";
            document.getElementById('contentHere').style.display = "block";
            setAnalysisS(year, sector);

        } else if (reportTitle == "Integrated") {

            document.getElementById('contentHere').style.display = "none";
            document.getElementById('integrateData').style.display = "block";
            setIntegratedS(year, sector);
        }



    }
});
//specific

function setAnalysisS(year, sector) {
    $('#years').empty();
    $('#barangays').empty();
    $('#districts').empty();
    $.ajax({
        url: "SetReportAnalysis",
        type: 'POST',
        dataType: "JSON",
        data: {
            year: year,
            sector: sector
        },
        success: function (data) {
            $("#content").empty();
            $("#prepared_by").empty();
            $("#info").empty();
            document.getElementById("prepared_by").innerHTML = "Prepared By  " + data[0].createdBy;
            $("#reportTitle").empty();
            $("#reportTitle").append(data[0].sector + " Analysis of " + year + " prepared by " + data[0].createdBy);

            for (var i = 0; i < data.length; i++) {
                var para = document.createElement("div");
                var element = document.getElementById("content");
                para.setAttribute("style", "width:90%; margin: 0 auto; ");
                element.appendChild(para);

                $(para).append('<img style="width: 90%;" id="image"  src="' + data[i].path + '"><br><br>');

                $(para).append('<b>Analysis: </b> <br><br>' + data[i].text + "<br>");
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function setIntegratedS(year, sector) {
    $.ajax({
        url: "SetIntegratedAnalysis",
        type: 'POST',
        dataType: "JSON",
        data: {
            year: year,
            sector: sector
        },
        success: function (data) {

            $("#content").empty();
            $("#prepared_by").empty();
            $("#integratedanalysis").empty();
            document.getElementById("prepared_by").innerHTML = "Prepared By  " + data[0].createdBy;
            $("#info").empty();
            $("#info").append('<input type="text"  value="' + data[0].year + '"/>');
            $("#info").append('<input type="text"  value="' + data[0].sector + '"/>');
            $("#info").append('<input type="text" id="createdBy"  value="' + data[0].createdBy + '"/>');
            for (var i = 0; i < data.length; i++) {
                $("#integratedanalysis").append('<b>Analysis: </b><br>' + data[i].text);
//                var element = document.getElementById("content");
//          
//                $(element).append('<input type="text"  value="' + data[i].text + '"/>');
            }

            //CHART
            var print;
            var city = "Caloocan City";

            $.ajax({
                url: "SetAnalysisDataServlet",
                type: 'POST',
                dataType: "JSON",
                success: function (data) {
                    $('#years').empty();
                    $('#barangays').empty();
                    $('#districts').empty();
                    print = data;
                    var yearSearch = document.getElementById('searchCensusYear').value;
                    for (var i = 0; i < print[0].years.length; i++) {
                        if (print[0].years[i].year <= yearSearch) {
                            $('#years').append('<input type="checkbox" class="filter" id="year" value="'
                                    + print[0].years[i].year + '"checked>'
                                    + print[0].years[i].year + '</input></br>');
                        } else {
                            print[0].years.splice(i, i);
                        }
                    }

                    //barangay
                    for (var i = 0; i < print[0].barangays.length; i++) {
                        $("#barangays").append('<input type="checkbox" class="filter" id="barangay" value="'
                                + print[0].barangays[i].barangay + '" checked>' + 'Barangay '
                                + print[0].barangays[i].barangay + '</input></br>');
                    }

                    //district
                    for (var i = 0; i < print[0].districts.length; i++) {
                        if (print[0].districts[i].district != city) {
                            $('#districts').append('<input type="checkbox" class="filter" id="district" value="'
                                    + print[0].districts[i].district + '" checked>' + print[0].districts[i].district + '</input></br>');
                        }
                    }
                    chart(print);
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(XMLHttpRequest.responseText);
                }
            });


            var year = [];
            var district = [];
            var barangay = [];
            var analysischart = [];


            //CLICK location
            $(document).on("click", '.filter', function () {
                year = [];
                district = [];
                barangay = [];

                analysischart[0] = print[0];
                $('[id="barangay"]:checked').each(function (e) {
                    var id = $(this).attr('value');
                    barangay.push(id);
                });

                $('[id="district"]:checked').each(function (e) {
                    var id = $(this).attr('value');
                    district.push(id);
                });

                $('[id="year"]:checked').each(function (e) {
                    var id = $(this).attr('value');
                    year.push(id);
                });

                removeYear(analysischart, year);
                removeBarangay(analysischart, barangay);
                removeDistrict(analysischart, district);
                chart(analysischart);

            });

            function removeYear(analysischart, year) {
                analysischart[0].years.length = 0;
                for (var x = 0; x < year.length; x++) {
                    item = {};
                    item["year"] = year[x];
                    analysischart[0].years.push(item);
                }
            }

            function removeDistrict(analysischart, district) {
                analysischart[0].districts.length = 0;

                for (var x = 0; x < district.length; x++) {
                    item = {};
                    item['district'] = district[x];
                    analysischart[0].districts.push(item);
                }
            }

            function removeBarangay(analysischart, barangay) {
                analysischart[0].barangays.length = 0;

                for (var x = 0; x < barangay.length; x++) {
                    item = {};
                    item["barangay"] = barangay[x];
                    analysischart[0].barangays.push(item);
                }
            }
            function chart(print) {

                var total = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'c';
                    item["type"] = 'column';
                    data = [];

                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (i == 0 || i == 1) {
                            item['color'] = '#7CB5EC';
                        } else if (print[0].years[i].year == print[0].schoolAge[x].year) {
                            if (print[0].schoolAge[x].isOutlier == true) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    item["color"] = "#FF0000";
                                }
                                break;
                            } else {
                                item['color'] = '#7CB5EC';
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                    totals = totals + parseInt(print[0].schoolAge[x].people, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    total.push(item);
                }


                var totalEnrollment = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'e';
                    data = [];
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year) {
                                    totals = totals + print[0].enrollmentSchoolAge[x].enrollment;
                                }

                            }
                        }
                    }
                    item["y"] = totals;
                    totalEnrollment.push(item);
                }

                var drilldowns = [];
                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    var northOutlier = false;
                    var southOutlier = false;
                    item = {};
                    item["name"] = print[0].years[i].year + ' School-going age population';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'c';

                    data = [];
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "SOUTH") {
                            if (print[0].schoolAge[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    southOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "NORTH") {
                            if (print[0].schoolAge[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    northOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].schoolAge[x].people, 10);
                                } else if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].schoolAge[x].people, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northc';
                    if (i == 0 || i == 1 || northOutlier == false) {
                        item['color'] = '#7CB5EC';
                    } else if (northOutlier) {
                        item2['color'] = '#FF0000';
                    }
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southc';
                    if (i == 0 || i == 1 || southOutlier == false) {
                        item['color'] = '#7CB5EC';
                    } else if (southOutlier) {
                        item2['color'] = '#FF0000';
                    }
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldowns.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Elementary Enrollment';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'e';

                    data = [];
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                } else if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northe';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southe';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldowns.push(item);
                }


                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan School-Going Age Population';
                    item["id"] = print[0].years[i].year + 'southc';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].schoolAge[x].district;
                                    item2["y"] = print[0].schoolAge[x].people;
                                    if (i == 0 || i == 1 || print[0].schoolAge[x].isOutlier == false) {
                                        item['color'] = '#7CB5EC';
                                    } else if (print[0].schoolAge[x].isOutlier) {
                                        item2['color'] = '#FF0000';
                                    }
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }



                for (var y = 0; y < print[0].years.length; y++) {
                    var northIOutlier = false;
                    var northIIOutlier = false;
                    var northIIIOutlier = false;
                    var northIVOutlier = false;
                    var northi = 0;
                    var northii = 0;
                    var northiii = 0;
                    var northiv = 0;
                    item = {};
                    item["name"] = print[0].years[y].year + ' North Caloocan School-Going Age Population';
                    item["id"] = print[0].years[y].year + 'northc';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year) {
                            if (print[0].schoolAge[x].zone == "NORTH") {
                                if (print[0].schoolAge[x].district === "Caloocan North IV") {
                                    if (print[0].schoolAge[x].isOutlier) {
                                        northIVOutlier = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            if (print[0].schoolAge[x].district === "Caloocan North III") {
                                if (print[0].schoolAge[x].isOutlier) {
                                    northIIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            if (print[0].schoolAge[x].district === "Caloocan North II") {
                                if (print[0].schoolAge[x].isOutlier) {
                                    northIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            if (print[0].schoolAge[x].district === "Caloocan North I") {
                                if (print[0].schoolAge[x].isOutlier) {
                                    northIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }

                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            for (var z = 0; z < print[0].districts.length; z++) {
                                if (print[0].schoolAge[x].district == print[0].districts[z].district) {
                                    if (print[0].schoolAge[x].district === "Caloocan North IV") {
                                        northiv = northiv + print[0].schoolAge[x].people;
                                    } else if (print[0].schoolAge[x].district === "Caloocan North III") {
                                        northiii = northiii + print[0].schoolAge[x].people;
                                    } else if (print[0].schoolAge[x].district === "Caloocan North II") {
                                        northii = northii + print[0].schoolAge[x].people;
                                    } else if (print[0].schoolAge[x].district === "Caloocan North I") {
                                        northi = northi + print[0].schoolAge[x].people;
                                    }
                                }
                            }
                        }
                        if (x === print[0].schoolAge.length - 1) {

                        }
                    }
                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North I' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North I';
                            if (y == 0 || y == 1 || northIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            item2["y"] = northi;
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North II' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North II';
                            item2["y"] = northii;
                            if (y == 0 || y == 1 || northIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North III' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North III';
                            item2["y"] = northiii;
                            if (y == 0 || y == 1 || northIIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North IV' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North IV';
                            item2["y"] = northiv;
                            if (y == 0 || y == 1 || northIVOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIVOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Enrollment';
                    item["id"] = print[0].years[i].year + 'northe';
                    item["type"] = 'column';
                    data = [];
                    for (x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].enrollmentSchoolAge[x].district;
                                    item2["y"] = print[0].enrollmentSchoolAge[x].enrollment;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Enrollment';
                    item["id"] = print[0].years[i].year + 'southe';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].enrollmentSchoolAge[x].district;
                                    item2["y"] = print[0].enrollmentSchoolAge[x].enrollment;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }

                // Create the chart
                $('#container').highcharts({
                    chart: {
                        type: 'column',
                        drilled: false,
                        zoomType: 'xy',
                        panning: true,
                        panKey: 'shift'//,
//                events: {
//                    drilldown: function (e) {
//                        var chart2 = $('#container2').highcharts(),
//                                chart3 = $('#container3').highcharts(),
//                                pointIndex = e.point.index;
//                        if (chart2.options.chart.drilled) {
//                            this.options.chart.drilled = true;
//                            chart2.options.chart.drilled = true;
//                            chart3.options.chart.drilled = true;
//                            chart2.series[0].data[pointIndex].doDrilldown();
//                            chart3.series[0].data[pointIndex].doDrilldown();
//                        } else if (!chart2.options.chart.drilled) {
//                            this.options.chart.drilled = true;
//                            chart2.options.chart.drilled = true;
//                            chart2.series[0].data[pointIndex].doDrilldown();
//                            chart3.options.chart.drilled = true;
//                            chart3.series[5].data[pointIndex].doDrilldown();
//                        }
//                    },
//                    drillup: function (e) {
//                        var chart2 = $('#container2').highcharts(),
//                                chart3 = $('#container3').highcharts();
//                        if (chart2.options.chart.drilled) {
//                            this.options.chart.drilled = false;
//                            chart2.options.chart.drilled = false;
//                            chart2.drillUp();
//                            chart3.options.chart.drilled = false;
//                            chart3.drillUp();
//                        } else if (!chart2.options.chart.drilled) {
//                            this.options.chart.drilled = false;
//                            chart2.options.chart.drilled = false;
//                            chart2.drillUp();
//                            chart3.options.chart.drilled = false;
//                            chart3.drillUp();
//                        }
//                    }
//                }
                    },
                    title: {
                        text: 'School Going Age Population vs. Enrollment'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    series: [{
                            name: 'Caloocan City',
                            type: 'column',
                            data: total
                        }, {
                            name: 'Enrollment',
                            type: 'spline',
                            data: totalEnrollment
                        }
                    ],
                    drilldown: {
                        series:
                                drilldowns
                    }
                });


                // Create the chart
                var totalPeople = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'p';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (i == 0 || i == 1) {
                            item['color'] = '#7CB5EC';
                        } else if (print[0].years[i].year == print[0].people[x].year) {
                            if (print[0].people[x].isOutlier == true) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].people[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].people[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    item["color"] = "#FF0000";
                                }
                                break;
                            } else {
                                item['color'] = '#7CB5EC';
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].people[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].people[x].year) {
                                    totals = totals + parseInt(print[0].people[x].people, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals
                    totalPeople.push(item);
                }


                var totalBeds = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'b';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year) {
                                    totals = totals + print[0].hospitals[x].beds;
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalBeds.push(item);
                }

                var totalDoctors = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'd';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year) {
                                    totals = totals + print[0].hospitals[x].doctors;
                                }

                            }
                        }
                    }
                    item["y"] = totals;
                    totalDoctors.push(item);
                }

                var totalNurses = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'n';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year) {
                                    totals = totals + print[0].hospitals[x].nurses;
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalNurses.push(item);
                }

                var drilldownsHospital = [];
                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    var southOutlier = false;
                    var northOutlier = false;
                    item = {};
                    item["name"] = 'Caloocan City Population';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'p';

                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "SOUTH") {
                            if (print[0].people[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].people[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].people[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    southOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "NORTH") {
                            if (print[0].people[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].people[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].people[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    northOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].people[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].people[x].people, 10);
                                } else if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].people[x].people, 10);
                                }

                            }
                        }
                        if (x === print[0].people.length - 1) {
                            item2 = {};
                            item2["name"] = 'North';
                            item2["y"] = north;
                            item2["drilldown"] = print[0].years[i].year + 'northp';
                            if (i == 0 || i == 1 || northOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                            item2 = {};
                            item2["name"] = 'South';
                            item2["y"] = south;
                            item2["drilldown"] = print[0].years[i].year + 'southp';
                            if (i == 0 || i == 1 || southOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (southOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }



                for (var y = 0; y < print[0].years.length; y++) {
                    var northIOutlier = false;
                    var northIIOutlier = false;
                    var northIIIOutlier = false;
                    var northIVOutlier = false;
                    var northi = 0;
                    var northii = 0;
                    var northiii = 0;
                    var northiv = 0;
                    item = {};
                    item["name"] = print[0].years[y].year + ' North Caloocan Population';
                    item["id"] = print[0].years[y].year + 'northp';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year) {
                            if (print[0].people[x].zone == "NORTH") {
                                if (print[0].people[x].district === "Caloocan North IV") {
                                    if (print[0].people[x].isOutlier) {
                                        northIVOutlier = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            if (print[0].people[x].district === "Caloocan North III") {
                                if (print[0].people[x].isOutlier) {
                                    northIIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            if (print[0].people[x].district === "Caloocan North II") {
                                if (print[0].people[x].isOutlier) {
                                    northIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            if (print[0].people[x].district === "Caloocan North I") {
                                if (print[0].people[x].isOutlier) {
                                    northIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }

                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            for (var z = 0; z < print[0].districts.length; z++) {
                                if (print[0].people[x].district == print[0].districts[z].district) {
                                    if (print[0].people[x].district === "Caloocan North IV") {
                                        northiv = northiv + print[0].people[x].people;
                                    } else if (print[0].people[x].district === "Caloocan North III") {
                                        northiii = northiii + print[0].people[x].people;
                                    } else if (print[0].people[x].district === "Caloocan North II") {
                                        northii = northii + print[0].people[x].people;
                                    } else if (print[0].people[x].district === "Caloocan North I") {
                                        northi = northi + print[0].people[x].people;
                                    }
                                }
                            }
                        }
                    }
                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North I' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North I';
                            if (y == 0 || y == 1 || northIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            item2["y"] = northi;
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North II' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North II';
                            item2["y"] = northii;
                            if (y == 0 || y == 1 || northIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North III' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North III';
                            item2["y"] = northiii;
                            if (y == 0 || y == 1 || northIIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North IV' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North IV';
                            item2["y"] = northiv;
                            if (y == 0 || y == 1 || northIVOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIVOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years.year + ' Caloocan City Hospital Beds';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'b';

                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].hospitals[x].beds, 10);
                                } else if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].hospitals[x].beds, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North Caloocan Hospital Beds';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northb';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South Caloocan Hospital Beds';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southb';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }



                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Caloocan City Hospital Nurses';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'n';

                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].hospitals[x].nurses, 10);
                                } else if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].hospitals[x].nurses, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North Caloocan Hospital Beds';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northn';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South Caloocan Hospital Beds';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southn';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Caloocan City Hospital Doctors';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'd';

                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].hospitals[x].doctors, 10);
                                } else if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].hospitals[x].doctors, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North Caloocan Hospital Doctors';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northd';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South Caloocan Hospital Doctors';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southd';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Population';
                    item["id"] = print[0].years[i].year + 'southp';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].people[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "SOUTH") {
                                    for (var y = 0; y < print[0].districts.length; y++) {
                                        if (print[0].people[x].district == print[0].districts[y].district) {
                                            item2 = {};
                                            item2["name"] = print[0].people[x].district;
                                            item2["y"] = print[0].people[x].people;
                                            if (i == 0 || i == 1 || print[0].people[x].isOutlier == false) {
                                                item['color'] = '#7CB5EC';
                                            } else if (print[0].people[x].isOutlier) {
                                                item2['color'] = '#FF0000';
                                            }
                                            data.push(item2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Hospital Beds';
                    item["id"] = print[0].years[i].year + 'southb';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].beds;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Hospital Beds';
                    item["id"] = print[0].years[i].year + 'northb';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].beds;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Hospital Doctors';
                    item["id"] = print[0].years[i].year + 'southd';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].doctors;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Hospital Doctors';
                    item["id"] = print[0].years[i].year + 'northd';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].doctors;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }


                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Hospital Nurses';
                    item["id"] = print[0].years[i].year + 'southn';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].nurses;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Hospital Nurses';
                    item["id"] = print[0].years[i].year + 'northn';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].nurses;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }
                $('#container2').highcharts({
                    chart: {
                        type: 'column',
                        drilled: false,
                        zoomType: 'xy',
                        panning: true,
                        panKey: 'shift'//,
//      events:{
//      	drilldown: function(e) {
//          var chart = $('#container').highcharts(),
//            	pointIndex = e.point.index;
//          if(!chart.options.chart.drilled) {
//            this.options.chart.drilled = false;
//            chart.options.chart.drilled = false;
//          	chart.series[0].data[pointIndex].doDrilldown();
//          } 
//          
//          
//        },
//        drillup: function(e) {
//          var chart = $('#container').highcharts();
//					
//          if(chart.options.chart.drilled) {
//          	this.options.chart.drilled = false;
//            chart.options.chart.drilled = true;
//          	chart.drillUp();
//          } 
//          
//        }
//      }
                    },
                    title: {
                        text: 'Population vs. Hospital Total number of Beds, Doctors and Nurses'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    plotOptions: {
                        column: {
//        stacking: 'normal'
                            dataLabels: {
                                enabled: true
                            },
                            series: {
                                allowPointSelect: true
                            }
                        },
                    },
                    series: [{
                            name: 'Population',
                            type: 'column',
                            data: totalPeople
                        }, {
                            name: 'Beds',
                            type: 'column',
                            data: totalBeds
                        }, {
                            name: 'Doctors',
                            type: 'column',
                            data: totalDoctors
                        }, {
                            name: 'Nurses',
                            type: 'column',
                            data: totalNurses
                        }
                    ],
                    drilldown: {
                        series: drilldownsHospital
                    }
                });

                var totalSeverelyWasted = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'sw';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].severelyWasted, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalSeverelyWasted.push(item);
                }

                var totalNormal = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'normal';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].normal, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalNormal.push(item);
                }

                var totalOverweight = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'overweight';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].overweight, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalOverweight.push(item);
                }

                var totalObese = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'obese';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].obese, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalObese.push(item);
                }

                var totalWasted = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'wasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].wasted, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalWasted.push(item);
                }

                var totalNotWeighed = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totalsWeighed = 0;
                    var totalEnrollment = 0;
                    var total = 0;
                    var
                            item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'notweighed';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totalsWeighed = totalsWeighed + parseInt(print[0].nutrition[x].weighed, 10);
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year) {
                                    totalEnrollment = totalEnrollment + print[0].enrollmentSchoolAge[x].enrollment;
                                }
                            }
                        }
                    }
                    total = totalEnrollment - totalsWeighed;
                    item["y"] = total;
                    totalNotWeighed.push(item);
                }

                var drilldownsNutrtion = [];
                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Normal Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'normal';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].normal, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].normal, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northnormal';
                    data.push(item2);

                    item3 = {};
                    item3["name"] = 'South';
                    item3["y"] = south;
                    item3["drilldown"] = print[0].years[i].year + 'southnormal';
                    data.push(item3);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Severely Wasted Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'sw';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].severelyWasted, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].severelyWasted, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northsw';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southsw';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Wasted Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'wasted';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].wasted, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].wasted, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northwasted';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southwasted';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Overweight Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'overweight';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].overweight, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].overweight, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northoverweight';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southoverweight';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Obese Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'obese';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].obese, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].obese, 10);
                                }
                            }
                        }
                    }

                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northobese';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southobese';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    var southEnrollment = 0;
                    var northEnrollment = 0;

                    item = {};
                    item["name"] = print[0].years[i].year + ' Not Weighed Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'notweighed';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].weighed, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].weighed, 10);
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "SOUTH") {
                                    southEnrollment = southEnrollment + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                } else if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "NORTH") {
                                    northEnrollment = northEnrollment + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = (northEnrollment - north);
                    item2["drilldown"] = print[0].years[i].year + 'northnotweighed';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = (southEnrollment - south);
                    item2["drilldown"] = print[0].years[i].year + 'southnotweighed';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);

                    south = 0;
                    north = 0;
                }


                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Normal Students';
                    item["id"] = print[0].years[i].year + 'northnormal';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].normal;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Normal Students';
                    item["id"] = print[0].years[i].year + 'southnormal';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].normal;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Severely Wasted Students';
                    item["id"] = print[0].years[i].year + 'northsw';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].severelyWasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Severely Wasted Students';
                    item["id"] = print[0].years[i].year + 'southsw';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].severelyWasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'northwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                //alert(print[0].districts[y].district);
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'southwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Overweight Students';
                    item["id"] = print[0].years[i].year + 'northoverweight';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].overweight;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Overweight Students';
                    item["id"] = print[0].years[i].year + 'southoverweight';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].overweight;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Obese Students';
                    item["id"] = print[0].years[i].year + 'northobese';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].obese;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Obese Students';
                    item["id"] = print[0].years[i].year + 'southobese';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].obese;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var north = 0;
                    var northEnrollment = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'northwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'southwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Not Weighed Students';
                    item["id"] = print[0].years[i].year + 'southnotweighed';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                            for (var y = 0; y < print[0].districts.length; y++) {
                                if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                    for (var y = 0; y < print[0].enrollmentSchoolAge.length; y++) {
                                        if (print[0].years[i].year == print[0].enrollmentSchoolAge[y].year && print[0].enrollmentSchoolAge[y].zone === "SOUTH" && print[0].enrollmentSchoolAge[y].district == print[0].nutrition[x].district) {
                                            item2 = {};
                                            item2["name"] = print[0].nutrition[x].district;
                                            item2["y"] = print[0].enrollmentSchoolAge[y].enrollment - print[0].nutrition[x].weighed;
                                            data.push(item2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Not Weighed Students';
                    item["id"] = print[0].years[i].year + 'northnotweighed';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                            for (var y = 0; y < print[0].districts.length; y++) {
                                if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                    for (var y = 0; y < print[0].enrollmentSchoolAge.length; y++) {
                                        if (print[0].years[i].year == print[0].enrollmentSchoolAge[y].year && print[0].enrollmentSchoolAge[y].zone === "NORTH" && print[0].enrollmentSchoolAge[y].district == print[0].nutrition[x].district) {
                                            item2 = {};
                                            item2["name"] = print[0].nutrition[x].district;
                                            item2["y"] = print[0].enrollmentSchoolAge[y].enrollment - print[0].nutrition[x].weighed;
                                            data.push(item2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                $('#container3').highcharts({
                    chart: {
                        type: 'column',
                        drilled: false,
                        zoomType: 'xy',
                        panning: true,
                        panKey: 'shift'
                    },
                    title: {
                        text: 'Nutritional Status of the enrolled Elementary Students'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    yAxis: {
                        min: 0,
                        stackLabels: {
                            enabled: true
                        }
                    },
                    plotOptions: {
                        column: {
                            stacking: 'normal',
                            dataLabels: {
                                enabled: true,
                                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                                style: {
                                    textShadow: '0 0 3px white'
                                }
                            }
                        }
                    },
                    series: [{
                            name: 'Severely Wasted',
                            data: totalSeverelyWasted
                        }, {
                            name: 'Wasted',
                            data: totalWasted
                        }, {
                            name: 'Normal',
                            data: totalNormal
                        },
                        {
                            name: 'Overweight',
                            data: totalOverweight
                        },
                        {
                            name: 'Obese',
                            data: totalObese
                        },
                        {
                            name: 'Not Weighed',
                            data: totalNotWeighed
                        }],
                    drilldown: {
                        series: drilldownsNutrtion
                    }
                });
//        year = [];
//        place = [];
//        location = [];
//        analysischart = {};
            }
            //CHART END

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function setMatrixS(year, sector) {
    $('#years').empty();
    $('#barangays').empty();
    $('#districts').empty();
    $.ajax({
        url: "SetReportMatrix",
        type: 'POST',
        dataType: "JSON",
        data: {
            year: year,
            sector: sector
        },
        success: function (data) {
            $("#info").empty();
            $("#content").empty();
            $("#prepared_by").empty();
            document.getElementById("prepared_by").innerHTML = "Prepared By  " + data[0].createdBy;
            $("#reportTitle").empty();
            $("#reportTitle").append(data[0].sector + " Analysis Matrix of " + year + " prepared by " + data[0].createdBy);

            for (var i = 0; i < data.length; i++) {
                var para = document.createElement("div");
                var element = document.getElementById("content");
                para.setAttribute("style", "box-body");
                element.appendChild(para);
                $(para).append('<img style="width: 90%; margin-bottom: 5%;" id="image"  src="' + data[i].path + '">');
                var table = document.createElement("table");
                table.setAttribute("class", "table table-hover table-bordered");
                table.setAttribute("style", " margin: 0 auto;");
                para.appendChild(table);

                var thead = document.createElement("thead");
                table.appendChild(thead);
                var tr = document.createElement("tr");
                thead.appendChild(tr);
                tr.setAttribute("id", "trcontent");
                tr.setAttribute("style", "background-color: #454545; color: #fff;");
                $(tr).append("<td>Observations</td>");
                $(tr).append("<td>Explanations</td>");
                $(tr).append("<td>Implications</td>");
                $(tr).append("<td>Interventions</td>");
                var tbody = document.createElement("tbody");

                table.appendChild(tbody);
                var tbodytr = document.createElement("tr");
                tbody.appendChild(tbodytr);
                $(tbodytr).append('<td>' + data[i].observations + '</td>');
                $(tbodytr).append('<td>' + data[i].explanations + '</td>');
                $(tbodytr).append('<td>' + data[i].implications + '</td>');
                $(tbodytr).append('<td>' + data[i].interventions + '</td>');

            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(XMLHttpRequest.responseText);
        }
    });
}



//not specific 
function searchMatrix() {
    var sector = $('#category').find(":selected").text();
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchReportMatrix',
        type: 'POST',
        params: {
            sector: sector
        },
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'Not Available'
    });
}


function searchAnalysis() {
    var sector = $('#category').find(":selected").text();
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchReportAnalysis',
        type: 'POST',
        params: {
            sector: sector
        },
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'Not Available'
    });
}

function searchIntegrated() {
    var sector = $('#category').find(":selected").text();
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchIntegratedAnalysis',
        type: 'POST',
        params: {
            sector: sector
        },
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'Not Available'
    });
}

function setAnalysis() {
    var year = document.getElementById('searchCensusYear').value;
    var sector = $('#category').find(":selected").text();
    $('#years').empty();
    $('#barangays').empty();
    $('#districts').empty();
    $.ajax({
        url: "SetReportAnalysis",
        type: 'POST',
        dataType: "JSON",
        data: {
            year: year,
            sector: sector
        },
        success: function (data) {
            $("#content").empty();
            $("#prepared_by").empty();
            $("#info").empty();
            document.getElementById("prepared_by").innerHTML = "Prepared By  " + data[0].createdBy;
            $("#reportTitle").empty();
            $("#reportTitle").append(data[0].sector + " Analysis of " + year + " prepared by " + data[0].createdBy);

            for (var i = 0; i < data.length; i++) {
                var para = document.createElement("div");
                var para2 = document.createElement("div");
                var element = document.getElementById("content");
                para.setAttribute("style", "width:90%; margin: 0 auto; ");
                element.appendChild(para);
                para.appendChild(para2);


                encodeImage(data[i].path, function (dataURL) {
                    $(para2).append('<img style="width: 90%; margin-bottom: 5%;" id="image"  src="' + dataURL + '">');
                });

//                $(para).append('<img style="width: 90%;" id="image"  src="' + data[i].path + '"><br><br>');
                $(para).append('<b>Analysis: </b> <br><br>' + data[i].text + "<br>");
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function setIntegrated() {
    var year = document.getElementById('searchCensusYear').value;
    var sector = $('#category').find(":selected").text();
    $.ajax({
        url: "SetIntegratedAnalysis",
        type: 'POST',
        dataType: "JSON",
        data: {
            year: year,
            sector: sector
        },
        success: function (data) {

            $("#content").empty();
            $("#prepared_by").empty();
            $("#integratedanalysis").empty();
            document.getElementById("prepared_by").innerHTML = "Prepared By  " + data[0].createdBy;
            $("#info").empty();
            $("#info").append('<input type="text"  value="' + data[0].year + '"/>');
            $("#info").append('<input type="text"  value="' + data[0].sector + '"/>');
            $("#info").append('<input type="text" id="createdBy"  value="' + data[0].createdBy + '"/>');
            for (var i = 0; i < data.length; i++) {
                $("#integratedanalysis").append('<b>Analysis: </b><br>' + data[i].text);
//                var element = document.getElementById("content");
//          
//                $(element).append('<input type="text"  value="' + data[i].text + '"/>');
            }

            //CHART
            var print;
            var city = "Caloocan City";

            $.ajax({
                url: "SetAnalysisDataServlet",
                type: 'POST',
                dataType: "JSON",
                success: function (data) {
                    $('#years').empty();
                    $('#barangays').empty();
                    $('#districts').empty();
                    print = data;
                    var yearSearch = document.getElementById('searchCensusYear').value;
                    for (var i = 0; i < print[0].years.length; i++) {
                        if (print[0].years[i].year <= yearSearch) {
                            $('#years').append('<input type="checkbox" class="filter" id="year" value="'
                                    + print[0].years[i].year + '"checked>'
                                    + print[0].years[i].year + '</input></br>');

                        } else {
                            print[0].years.splice(i, i);
                        }
                    }

                    //barangay
                    for (var i = 0; i < print[0].barangays.length; i++) {
                        $("#barangays").append('<input type="checkbox" class="filter" id="barangay" value="'
                                + print[0].barangays[i].barangay + '" checked>' + 'Barangay '
                                + print[0].barangays[i].barangay + '</input></br>');
                    }

                    //district
                    for (var i = 0; i < print[0].districts.length; i++) {
                        if (print[0].districts[i].district != city) {
                            $('#districts').append('<input type="checkbox" class="filter" id="district" value="'
                                    + print[0].districts[i].district + '" checked>' + print[0].districts[i].district + '</input></br>');
                        }
                    }
                    chart(print);
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(XMLHttpRequest.responseText);
                }
            });


            var year = [];
            var district = [];
            var barangay = [];
            var analysischart = [];


            //CLICK location
            $(document).on("click", '.filter', function () {
                year = [];
                district = [];
                barangay = [];

                analysischart[0] = print[0];
                $('[id="barangay"]:checked').each(function (e) {
                    var id = $(this).attr('value');
                    barangay.push(id);
                });

                $('[id="district"]:checked').each(function (e) {
                    var id = $(this).attr('value');
                    district.push(id);
                });

                $('[id="year"]:checked').each(function (e) {
                    var id = $(this).attr('value');
                    year.push(id);
                });

                removeYear(analysischart, year);
                removeBarangay(analysischart, barangay);
                removeDistrict(analysischart, district);
                chart(analysischart);

            });

            function removeYear(analysischart, year) {
                analysischart[0].years.length = 0;
                for (var x = 0; x < year.length; x++) {
                    item = {};
                    item["year"] = year[x];
                    analysischart[0].years.push(item);
                }
            }

            function removeDistrict(analysischart, district) {
                analysischart[0].districts.length = 0;

                for (var x = 0; x < district.length; x++) {
                    item = {};
                    item['district'] = district[x];
                    analysischart[0].districts.push(item);
                }
            }

            function removeBarangay(analysischart, barangay) {
                analysischart[0].barangays.length = 0;

                for (var x = 0; x < barangay.length; x++) {
                    item = {};
                    item["barangay"] = barangay[x];
                    analysischart[0].barangays.push(item);
                }
            }
            function chart(print) {

                var total = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'c';
                    item["type"] = 'column';
                    data = [];

                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (i == 0 || i == 1) {
                            item['color'] = '#7CB5EC';
                        } else if (print[0].years[i].year == print[0].schoolAge[x].year) {
                            if (print[0].schoolAge[x].isOutlier == true) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    item["color"] = "#FF0000";
                                }
                                break;
                            } else {
                                item['color'] = '#7CB5EC';
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                    totals = totals + parseInt(print[0].schoolAge[x].people, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    total.push(item);
                }


                var totalEnrollment = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'e';
                    data = [];
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year) {
                                    totals = totals + print[0].enrollmentSchoolAge[x].enrollment;
                                }

                            }
                        }
                    }
                    item["y"] = totals;
                    totalEnrollment.push(item);
                }

                var drilldowns = [];
                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    var northOutlier = false;
                    var southOutlier = false;
                    item = {};
                    item["name"] = print[0].years[i].year + ' School-going age population';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'c';

                    data = [];
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "SOUTH") {
                            if (print[0].schoolAge[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    southOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "NORTH") {
                            if (print[0].schoolAge[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].schoolAge[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    northOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].schoolAge[x].people, 10);
                                } else if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].schoolAge[x].people, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northc';
                    if (i == 0 || i == 1 || northOutlier == false) {
                        item['color'] = '#7CB5EC';
                    } else if (northOutlier) {
                        item2['color'] = '#FF0000';
                    }
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southc';
                    if (i == 0 || i == 1 || southOutlier == false) {
                        item['color'] = '#7CB5EC';
                    } else if (southOutlier) {
                        item2['color'] = '#FF0000';
                    }
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldowns.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Elementary Enrollment';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'e';

                    data = [];
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                } else if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northe';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southe';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldowns.push(item);
                }


                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan School-Going Age Population';
                    item["id"] = print[0].years[i].year + 'southc';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].schoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].schoolAge[x].district;
                                    item2["y"] = print[0].schoolAge[x].people;
                                    if (i == 0 || i == 1 || print[0].schoolAge[x].isOutlier == false) {
                                        item['color'] = '#7CB5EC';
                                    } else if (print[0].schoolAge[x].isOutlier) {
                                        item2['color'] = '#FF0000';
                                    }
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }



                for (var y = 0; y < print[0].years.length; y++) {
                    var northIOutlier = false;
                    var northIIOutlier = false;
                    var northIIIOutlier = false;
                    var northIVOutlier = false;
                    var northi = 0;
                    var northii = 0;
                    var northiii = 0;
                    var northiv = 0;
                    item = {};
                    item["name"] = print[0].years[y].year + ' North Caloocan School-Going Age Population';
                    item["id"] = print[0].years[y].year + 'northc';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year) {
                            if (print[0].schoolAge[x].zone == "NORTH") {
                                if (print[0].schoolAge[x].district === "Caloocan North IV") {
                                    if (print[0].schoolAge[x].isOutlier) {
                                        northIVOutlier = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            if (print[0].schoolAge[x].district === "Caloocan North III") {
                                if (print[0].schoolAge[x].isOutlier) {
                                    northIIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            if (print[0].schoolAge[x].district === "Caloocan North II") {
                                if (print[0].schoolAge[x].isOutlier) {
                                    northIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            if (print[0].schoolAge[x].district === "Caloocan North I") {
                                if (print[0].schoolAge[x].isOutlier) {
                                    northIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }

                    for (var x = 0; x < print[0].schoolAge.length; x++) {
                        if (print[0].years[y].year == print[0].schoolAge[x].year && print[0].schoolAge[x].zone == "NORTH") {
                            for (var z = 0; z < print[0].districts.length; z++) {
                                if (print[0].schoolAge[x].district == print[0].districts[z].district) {
                                    if (print[0].schoolAge[x].district === "Caloocan North IV") {
                                        northiv = northiv + print[0].schoolAge[x].people;
                                    } else if (print[0].schoolAge[x].district === "Caloocan North III") {
                                        northiii = northiii + print[0].schoolAge[x].people;
                                    } else if (print[0].schoolAge[x].district === "Caloocan North II") {
                                        northii = northii + print[0].schoolAge[x].people;
                                    } else if (print[0].schoolAge[x].district === "Caloocan North I") {
                                        northi = northi + print[0].schoolAge[x].people;
                                    }
                                }
                            }
                        }
                        if (x === print[0].schoolAge.length - 1) {

                        }
                    }
                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North I' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North I';
                            if (y == 0 || y == 1 || northIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            item2["y"] = northi;
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North II' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North II';
                            item2["y"] = northii;
                            if (y == 0 || y == 1 || northIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North III' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North III';
                            item2["y"] = northiii;
                            if (y == 0 || y == 1 || northIIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North IV' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North IV';
                            item2["y"] = northiv;
                            if (y == 0 || y == 1 || northIVOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIVOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Enrollment';
                    item["id"] = print[0].years[i].year + 'northe';
                    item["type"] = 'column';
                    data = [];
                    for (x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].enrollmentSchoolAge[x].district;
                                    item2["y"] = print[0].enrollmentSchoolAge[x].enrollment;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Enrollment';
                    item["id"] = print[0].years[i].year + 'southe';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].enrollmentSchoolAge[x].district;
                                    item2["y"] = print[0].enrollmentSchoolAge[x].enrollment;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }

                // Create the chart
                $('#container').highcharts({
                    chart: {
                        type: 'column',
                        drilled: false,
                        zoomType: 'xy',
                        panning: true,
                        panKey: 'shift'//,
//                events: {
//                    drilldown: function (e) {
//                        var chart2 = $('#container2').highcharts(),
//                                chart3 = $('#container3').highcharts(),
//                                pointIndex = e.point.index;
//                        if (chart2.options.chart.drilled) {
//                            this.options.chart.drilled = true;
//                            chart2.options.chart.drilled = true;
//                            chart3.options.chart.drilled = true;
//                            chart2.series[0].data[pointIndex].doDrilldown();
//                            chart3.series[0].data[pointIndex].doDrilldown();
//                        } else if (!chart2.options.chart.drilled) {
//                            this.options.chart.drilled = true;
//                            chart2.options.chart.drilled = true;
//                            chart2.series[0].data[pointIndex].doDrilldown();
//                            chart3.options.chart.drilled = true;
//                            chart3.series[5].data[pointIndex].doDrilldown();
//                        }
//                    },
//                    drillup: function (e) {
//                        var chart2 = $('#container2').highcharts(),
//                                chart3 = $('#container3').highcharts();
//                        if (chart2.options.chart.drilled) {
//                            this.options.chart.drilled = false;
//                            chart2.options.chart.drilled = false;
//                            chart2.drillUp();
//                            chart3.options.chart.drilled = false;
//                            chart3.drillUp();
//                        } else if (!chart2.options.chart.drilled) {
//                            this.options.chart.drilled = false;
//                            chart2.options.chart.drilled = false;
//                            chart2.drillUp();
//                            chart3.options.chart.drilled = false;
//                            chart3.drillUp();
//                        }
//                    }
//                }
                    },
                    title: {
                        text: 'School Going Age Population vs. Enrollment'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    series: [{
                            name: 'Caloocan City',
                            type: 'column',
                            data: total
                        }, {
                            name: 'Enrollment',
                            type: 'spline',
                            data: totalEnrollment
                        }
                    ],
                    drilldown: {
                        series:
                                drilldowns
                    }
                });


                // Create the chart
                var totalPeople = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'p';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (i == 0 || i == 1) {
                            item['color'] = '#7CB5EC';
                        } else if (print[0].years[i].year == print[0].people[x].year) {
                            if (print[0].people[x].isOutlier == true) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].people[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].people[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    item["color"] = "#FF0000";
                                }
                                break;
                            } else {
                                item['color'] = '#7CB5EC';
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].people[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].people[x].year) {
                                    totals = totals + parseInt(print[0].people[x].people, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals
                    totalPeople.push(item);
                }


                var totalBeds = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'b';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year) {
                                    totals = totals + print[0].hospitals[x].beds;
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalBeds.push(item);
                }

                var totalDoctors = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'd';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year) {
                                    totals = totals + print[0].hospitals[x].doctors;
                                }

                            }
                        }
                    }
                    item["y"] = totals;
                    totalDoctors.push(item);
                }

                var totalNurses = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'n';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year) {
                                    totals = totals + print[0].hospitals[x].nurses;
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalNurses.push(item);
                }

                var drilldownsHospital = [];
                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    var southOutlier = false;
                    var northOutlier = false;
                    item = {};
                    item["name"] = 'Caloocan City Population';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'p';

                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "SOUTH") {
                            if (print[0].people[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].people[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].people[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    southOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "NORTH") {
                            if (print[0].people[x].isOutlier) {
                                var isFiltered = false;
                                for (var y = 0; y < print[0].districts.length; y++) {
                                    if (print[0].people[x].district == print[0].districts[y].district) {
                                        if (print[0].years[i].year == print[0].people[x].year) {
                                            isFiltered = true;
                                            break;
                                        }
                                    }
                                }
                                if (isFiltered) {
                                    northOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].people[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].people[x].people, 10);
                                } else if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].people[x].people, 10);
                                }

                            }
                        }
                        if (x === print[0].people.length - 1) {
                            item2 = {};
                            item2["name"] = 'North';
                            item2["y"] = north;
                            item2["drilldown"] = print[0].years[i].year + 'northp';
                            if (i == 0 || i == 1 || northOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                            item2 = {};
                            item2["name"] = 'South';
                            item2["y"] = south;
                            item2["drilldown"] = print[0].years[i].year + 'southp';
                            if (i == 0 || i == 1 || southOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (southOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }



                for (var y = 0; y < print[0].years.length; y++) {
                    var northIOutlier = false;
                    var northIIOutlier = false;
                    var northIIIOutlier = false;
                    var northIVOutlier = false;
                    var northi = 0;
                    var northii = 0;
                    var northiii = 0;
                    var northiv = 0;
                    item = {};
                    item["name"] = print[0].years[y].year + ' North Caloocan Population';
                    item["id"] = print[0].years[y].year + 'northp';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year) {
                            if (print[0].people[x].zone == "NORTH") {
                                if (print[0].people[x].district === "Caloocan North IV") {
                                    if (print[0].people[x].isOutlier) {
                                        northIVOutlier = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            if (print[0].people[x].district === "Caloocan North III") {
                                if (print[0].people[x].isOutlier) {
                                    northIIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            if (print[0].people[x].district === "Caloocan North II") {
                                if (print[0].people[x].isOutlier) {
                                    northIIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            if (print[0].people[x].district === "Caloocan North I") {
                                if (print[0].people[x].isOutlier) {
                                    northIOutlier = true;
                                    break;
                                }
                            }
                        }
                    }

                    for (var x = 0; x < print[0].people.length; x++) {
                        if (print[0].years[y].year == print[0].people[x].year && print[0].people[x].zone == "NORTH") {
                            for (var z = 0; z < print[0].districts.length; z++) {
                                if (print[0].people[x].district == print[0].districts[z].district) {
                                    if (print[0].people[x].district === "Caloocan North IV") {
                                        northiv = northiv + print[0].people[x].people;
                                    } else if (print[0].people[x].district === "Caloocan North III") {
                                        northiii = northiii + print[0].people[x].people;
                                    } else if (print[0].people[x].district === "Caloocan North II") {
                                        northii = northii + print[0].people[x].people;
                                    } else if (print[0].people[x].district === "Caloocan North I") {
                                        northi = northi + print[0].people[x].people;
                                    }
                                }
                            }
                        }
                    }
                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North I' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North I';
                            if (y == 0 || y == 1 || northIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            item2["y"] = northi;
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North II' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North II';
                            item2["y"] = northii;
                            if (y == 0 || y == 1 || northIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North III' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North III';
                            item2["y"] = northiii;
                            if (y == 0 || y == 1 || northIIIOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIIIOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }

                    for (var z = 0; z < print[0].districts.length; z++) {
                        if ('Caloocan North IV' == print[0].districts[z].district) {
                            item2 = {};
                            item2["name"] = 'Caloocan North IV';
                            item2["y"] = northiv;
                            if (y == 0 || y == 1 || northIVOutlier == false) {
                                item['color'] = '#7CB5EC';
                            } else if (northIVOutlier) {
                                item2['color'] = '#FF0000';
                            }
                            data.push(item2);
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years.year + ' Caloocan City Hospital Beds';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'b';

                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].hospitals[x].beds, 10);
                                } else if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].hospitals[x].beds, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North Caloocan Hospital Beds';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northb';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South Caloocan Hospital Beds';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southb';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }



                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Caloocan City Hospital Nurses';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'n';

                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].hospitals[x].nurses, 10);
                                } else if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].hospitals[x].nurses, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North Caloocan Hospital Beds';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northn';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South Caloocan Hospital Beds';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southn';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Caloocan City Hospital Doctors';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'd';

                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].hospitals[x].doctors, 10);
                                } else if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].hospitals[x].doctors, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North Caloocan Hospital Doctors';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northd';
                    data.push(item2);

                    item2 = {};
                    item2["name"] = 'South Caloocan Hospital Doctors';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southd';
                    data.push(item2);
                    item['data'] = data;
                    souths.push(item);
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Population';
                    item["id"] = print[0].years[i].year + 'southp';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].people.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].people[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].people[x].year && print[0].people[x].zone === "SOUTH") {
                                    for (var y = 0; y < print[0].districts.length; y++) {
                                        if (print[0].people[x].district == print[0].districts[y].district) {
                                            item2 = {};
                                            item2["name"] = print[0].people[x].district;
                                            item2["y"] = print[0].people[x].people;
                                            if (i == 0 || i == 1 || print[0].people[x].isOutlier == false) {
                                                item['color'] = '#7CB5EC';
                                            } else if (print[0].people[x].isOutlier) {
                                                item2['color'] = '#FF0000';
                                            }
                                            data.push(item2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Hospital Beds';
                    item["id"] = print[0].years[i].year + 'southb';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].beds;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Hospital Beds';
                    item["id"] = print[0].years[i].year + 'northb';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].beds;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Hospital Doctors';
                    item["id"] = print[0].years[i].year + 'southd';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].doctors;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Hospital Doctors';
                    item["id"] = print[0].years[i].year + 'northd';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].doctors;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }


                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Hospital Nurses';
                    item["id"] = print[0].years[i].year + 'southn';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].nurses;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Hospital Nurses';
                    item["id"] = print[0].years[i].year + 'northn';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].hospitals.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].hospitals[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].hospitals[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].hospitals[x].district;
                                    item2["y"] = print[0].hospitals[x].nurses;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsHospital.push(item);
                }
                $('#container2').highcharts({
                    chart: {
                        type: 'column',
                        drilled: false,
                        zoomType: 'xy',
                        panning: true,
                        panKey: 'shift'//,
//      events:{
//      	drilldown: function(e) {
//          var chart = $('#container').highcharts(),
//            	pointIndex = e.point.index;
//          if(!chart.options.chart.drilled) {
//            this.options.chart.drilled = false;
//            chart.options.chart.drilled = false;
//          	chart.series[0].data[pointIndex].doDrilldown();
//          } 
//          
//          
//        },
//        drillup: function(e) {
//          var chart = $('#container').highcharts();
//					
//          if(chart.options.chart.drilled) {
//          	this.options.chart.drilled = false;
//            chart.options.chart.drilled = true;
//          	chart.drillUp();
//          } 
//          
//        }
//      }
                    },
                    title: {
                        text: 'Population vs. Hospital Total number of Beds, Doctors and Nurses'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    plotOptions: {
                        column: {
//        stacking: 'normal'
                            dataLabels: {
                                enabled: true
                            },
                            series: {
                                allowPointSelect: true
                            }
                        },
                    },
                    series: [{
                            name: 'Population',
                            type: 'column',
                            data: totalPeople
                        }, {
                            name: 'Beds',
                            type: 'column',
                            data: totalBeds
                        }, {
                            name: 'Doctors',
                            type: 'column',
                            data: totalDoctors
                        }, {
                            name: 'Nurses',
                            type: 'column',
                            data: totalNurses
                        }
                    ],
                    drilldown: {
                        series: drilldownsHospital
                    }
                });

                var totalSeverelyWasted = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'sw';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].severelyWasted, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalSeverelyWasted.push(item);
                }

                var totalNormal = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'normal';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].normal, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalNormal.push(item);
                }

                var totalOverweight = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'overweight';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].overweight, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalOverweight.push(item);
                }

                var totalObese = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'obese';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].obese, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalObese.push(item);
                }

                var totalWasted = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totals = 0;
                    item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'wasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totals = totals + parseInt(print[0].nutrition[x].wasted, 10);
                                }
                            }
                        }
                    }
                    item["y"] = totals;
                    totalWasted.push(item);
                }

                var totalNotWeighed = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var totalsWeighed = 0;
                    var totalEnrollment = 0;
                    var total = 0;
                    var
                            item = {};
                    item["name"] = print[0].years[i].year;
                    item["drilldown"] = print[0].years[i].year + 'notweighed';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year) {
                                    totalsWeighed = totalsWeighed + parseInt(print[0].nutrition[x].weighed, 10);
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year) {
                                    totalEnrollment = totalEnrollment + print[0].enrollmentSchoolAge[x].enrollment;
                                }
                            }
                        }
                    }
                    total = totalEnrollment - totalsWeighed;
                    item["y"] = total;
                    totalNotWeighed.push(item);
                }

                var drilldownsNutrtion = [];
                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Normal Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'normal';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].normal, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].normal, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northnormal';
                    data.push(item2);

                    item3 = {};
                    item3["name"] = 'South';
                    item3["y"] = south;
                    item3["drilldown"] = print[0].years[i].year + 'southnormal';
                    data.push(item3);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Severely Wasted Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'sw';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].severelyWasted, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].severelyWasted, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northsw';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southsw';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Wasted Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'wasted';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].wasted, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].wasted, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northwasted';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southwasted';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Overweight Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'overweight';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].overweight, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].overweight, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northoverweight';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southoverweight';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' Obese Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'obese';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].obese, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].obese, 10);
                                }
                            }
                        }
                    }

                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = north;
                    item2["drilldown"] = print[0].years[i].year + 'northobese';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = south;
                    item2["drilldown"] = print[0].years[i].year + 'southobese';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);
                }

                var souths = [];
                for (var i = 0; i < print[0].years.length; i++) {
                    var south = 0;
                    var north = 0;
                    var southEnrollment = 0;
                    var northEnrollment = 0;

                    item = {};
                    item["name"] = print[0].years[i].year + ' Not Weighed Students';
                    item["type"] = 'column';
                    item["id"] = print[0].years[i].year + 'notweighed';

                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    south = south + parseInt(print[0].nutrition[x].weighed, 10);
                                } else if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    north = north + parseInt(print[0].nutrition[x].weighed, 10);
                                }
                            }
                        }
                    }
                    for (var x = 0; x < print[0].enrollmentSchoolAge.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].enrollmentSchoolAge[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "SOUTH") {
                                    southEnrollment = southEnrollment + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                } else if (print[0].years[i].year == print[0].enrollmentSchoolAge[x].year && print[0].enrollmentSchoolAge[x].zone === "NORTH") {
                                    northEnrollment = northEnrollment + parseInt(print[0].enrollmentSchoolAge[x].enrollment, 10);
                                }
                            }
                        }
                    }
                    item2 = {};
                    item2["name"] = 'North';
                    item2["y"] = (northEnrollment - north);
                    item2["drilldown"] = print[0].years[i].year + 'northnotweighed';
                    data.push(item2);
                    item2 = {};
                    item2["name"] = 'South';
                    item2["y"] = (southEnrollment - south);
                    item2["drilldown"] = print[0].years[i].year + 'southnotweighed';
                    data.push(item2);

                    item['data'] = data;
                    souths.push(item);
                    drilldownsNutrtion.push(item);

                    south = 0;
                    north = 0;
                }


                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Normal Students';
                    item["id"] = print[0].years[i].year + 'northnormal';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].normal;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Normal Students';
                    item["id"] = print[0].years[i].year + 'southnormal';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].normal;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Severely Wasted Students';
                    item["id"] = print[0].years[i].year + 'northsw';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].severelyWasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Severely Wasted Students';
                    item["id"] = print[0].years[i].year + 'southsw';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].severelyWasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'northwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                //alert(print[0].districts[y].district);
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'southwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].nutrition[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Overweight Students';
                    item["id"] = print[0].years[i].year + 'northoverweight';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].overweight;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Overweight Students';
                    item["id"] = print[0].years[i].year + 'southoverweight';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].overweight;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Obese Students';
                    item["id"] = print[0].years[i].year + 'northobese';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].obese;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Obese Students';
                    item["id"] = print[0].years[i].year + 'southobese';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].obese;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    var north = 0;
                    var northEnrollment = 0;
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'northwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Wasted Students';
                    item["id"] = print[0].years[i].year + 'southwasted';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        for (var y = 0; y < print[0].districts.length; y++) {
                            if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                                    item2 = {};
                                    item2["name"] = print[0].nutrition[x].district;
                                    item2["y"] = print[0].nutrition[x].wasted;
                                    data.push(item2);
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' South Caloocan Not Weighed Students';
                    item["id"] = print[0].years[i].year + 'southnotweighed';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "SOUTH") {
                            for (var y = 0; y < print[0].districts.length; y++) {
                                if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                    for (var y = 0; y < print[0].enrollmentSchoolAge.length; y++) {
                                        if (print[0].years[i].year == print[0].enrollmentSchoolAge[y].year && print[0].enrollmentSchoolAge[y].zone === "SOUTH" && print[0].enrollmentSchoolAge[y].district == print[0].nutrition[x].district) {
                                            item2 = {};
                                            item2["name"] = print[0].nutrition[x].district;
                                            item2["y"] = print[0].enrollmentSchoolAge[y].enrollment - print[0].nutrition[x].weighed;
                                            data.push(item2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                for (var i = 0; i < print[0].years.length; i++) {
                    item = {};
                    item["name"] = print[0].years[i].year + ' North Caloocan Not Weighed Students';
                    item["id"] = print[0].years[i].year + 'northnotweighed';
                    item["type"] = 'column';
                    data = [];
                    for (var x = 0; x < print[0].nutrition.length; x++) {
                        if (print[0].years[i].year == print[0].hospitals[x].year && print[0].nutrition[x].zone === "NORTH") {
                            for (var y = 0; y < print[0].districts.length; y++) {
                                if (print[0].nutrition[x].district == print[0].districts[y].district) {
                                    for (var y = 0; y < print[0].enrollmentSchoolAge.length; y++) {
                                        if (print[0].years[i].year == print[0].enrollmentSchoolAge[y].year && print[0].enrollmentSchoolAge[y].zone === "NORTH" && print[0].enrollmentSchoolAge[y].district == print[0].nutrition[x].district) {
                                            item2 = {};
                                            item2["name"] = print[0].nutrition[x].district;
                                            item2["y"] = print[0].enrollmentSchoolAge[y].enrollment - print[0].nutrition[x].weighed;
                                            data.push(item2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldownsNutrtion.push(item);
                }

                $('#container3').highcharts({
                    chart: {
                        type: 'column',
                        drilled: false,
                        zoomType: 'xy',
                        panning: true,
                        panKey: 'shift'
                    },
                    title: {
                        text: 'Nutritional Status of the enrolled Elementary Students'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    yAxis: {
                        min: 0,
                        stackLabels: {
                            enabled: true
                        }
                    },
                    plotOptions: {
                        column: {
                            stacking: 'normal',
                            dataLabels: {
                                enabled: true,
                                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                                style: {
                                    textShadow: '0 0 3px white'
                                }
                            }
                        }
                    },
                    series: [{
                            name: 'Severely Wasted',
                            data: totalSeverelyWasted
                        }, {
                            name: 'Wasted',
                            data: totalWasted
                        }, {
                            name: 'Normal',
                            data: totalNormal
                        },
                        {
                            name: 'Overweight',
                            data: totalOverweight
                        },
                        {
                            name: 'Obese',
                            data: totalObese
                        },
                        {
                            name: 'Not Weighed',
                            data: totalNotWeighed
                        }],
                    drilldown: {
                        series: drilldownsNutrtion
                    }
                });
//        year = [];
//        place = [];
//        location = [];
//        analysischart = {};
            }
            //CHART END

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function setMatrix() {
    $('#years').empty();
    $('#barangays').empty();
    $('#districts').empty();
    var year = document.getElementById('searchCensusYear').value;
    var sector = $('#category').find(":selected").text();
    $.ajax({
        url: "SetReportMatrix",
        type: 'POST',
        dataType: "JSON",
        data: {
            year: year,
            sector: sector
        },
        success: function (data) {
            $("#info").empty();
            $("#content").empty();
            $("#prepared_by").empty();
            document.getElementById("prepared_by").innerHTML = "Prepared By  " + data[0].createdBy;
            $("#reportTitle").empty();
            $("#reportTitle").append(data[0].sector + " Analysis Matrix of " + year + " prepared by " + data[0].createdBy);

            for (var i = 0; i < data.length; i++) {
                var para = document.createElement("div");
                var para2 = document.createElement("div");
                var element = document.getElementById("content");
                para.setAttribute("style", "box-body");
                element.appendChild(para);
                para.appendChild(para2);
//                $(para).append('<img style="width: 90%; margin-bottom: 5%;" id="image"  src="' + data[i].path + '">');


                encodeImage(data[i].path, function (dataURL) {
                    $(para2).append('<img style="width: 90%; margin-bottom: 5%;" id="image"  src="' + dataURL + '">');
                });

                var table = document.createElement("table");
                table.setAttribute("class", "table table-hover table-bordered");
                table.setAttribute("style", " margin: 0 auto;");
                para.appendChild(table);

                var thead = document.createElement("thead");
                table.appendChild(thead);
                var tr = document.createElement("tr");
                thead.appendChild(tr);
                tr.setAttribute("id", "trcontent");
                tr.setAttribute("style", "background-color: #454545; color: #fff;");
                $(tr).append("<td>Observations</td>");
                $(tr).append("<td>Explanations</td>");
                $(tr).append("<td>Implications</td>");
                $(tr).append("<td>Interventions</td>");
                var tbody = document.createElement("tbody");

                table.appendChild(tbody);
                var tbodytr = document.createElement("tr");
                tbody.appendChild(tbodytr);
                $(tbodytr).append('<td>' + data[i].observations + '</td>');
                $(tbodytr).append('<td>' + data[i].explanations + '</td>');
                $(tbodytr).append('<td>' + data[i].implications + '</td>');
                $(tbodytr).append('<td>' + data[i].interventions + '</td>');

            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function encodeImage(imageUri, callback) {
    var c = document.createElement('canvas');
    var ctx = c.getContext("2d");
    var img = new Image();
    img.onload = function () {
        c.width = this.width;
        c.height = this.height;
        ctx.drawImage(img, 0, 0);
        var dataURL = c.toDataURL("image/png");
        callback(dataURL)
    };
    img.src = imageUri;
}