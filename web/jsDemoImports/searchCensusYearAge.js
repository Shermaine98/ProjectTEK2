/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function format() {
    $(".number").each(function () {
        var x = $(this).val();
        $(this).val(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    });
}

function formatTDNumber() {
    $(".number").each(function () {
        var x = $(this).text();
        $(this).text(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    });
}


function autoCompleteAgeGroup() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchAgeGroupSex',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}


function autoCompleteMarital() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchMaritalStatus',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}

function autoCompleteHighestAttainment() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchHighestAttainment',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}

function autoNutritionalStatus() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchNutritionalStatus',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}

function autoCompleteHospitalDirectory() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchHospitalsDirectory',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}


function autoCompletePrivateClassroom() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchPrivateClassroom',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}

function autoCompletePrivateEnrollment() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchPrivateEnrollment',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}


function autoPublicClassroom() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchPublicClassroom',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}

function autoPublicEnrollment() {
    $("#searchCensusYear").devbridgeAutocomplete({
        serviceUrl: 'SearchPublicEnrollment',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing year'
    });
}



function getDataAgeGroup() {

    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    $.ajax({
        url: "SetDataServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear
        },
        success: function (data) {
            if (data[0].ByAgeGroupSexTable.length === 0) {
                errorMessage(censusYear);
                document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();

                var str = '<table id="dataTable" class="table table-hover table-bordered dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;
                $('#thead').append('<tr style="background-color: #454545; color: #fff">\n\
                                    <th>Location</th> \n\
                                    <th>Age Group</th>\n\
                                    <th class="centerTD">Both Sexes</th> \n\
                                    <th class="centerTD">Male Count</th>\n\
                                    <th class="centerTD">Female Count</th> \n\
                                    </tr>');



                for (i = 0; i < print[0].ByAgeGroupSexTable.length; i++) {
                    $('#data').append('<tr> \n\
                                        <td>' + print[0].ByAgeGroupSexTable[i].location + '</td>  \n\
                                        <td>' + print[0].ByAgeGroupSexTable[i].AgeGroup + '</td> \n\
                                        <td class="centerTD number">' + print[0].ByAgeGroupSexTable[i].BothSexes + '</td> \n\
                                        <td class="centerTD number">' + print[0].ByAgeGroupSexTable[i].Male + '</td> \n\
                                        <td class="centerTD number">' + print[0].ByAgeGroupSexTable[i].Female + '</td>'
                            + '</tr>');

                }
                //COMA
                formatTDNumber();

                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": true,
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    },
                    "columns": [
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'}
                    ]
                });

                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chart(print);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}



function chart(print) {

    var topCategories = [];
    for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
        topCategories.push(print[0].totalAgeGroupSex[i].ageGroup);
    }

    var malePerBarangay = [];
    for (var i = 0; i < print[0].arrTotalMFFemale.length; i++) {
        item = {};
        item["name"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation;
        item["y"] = print[0].arrTotalMFFemale[i].arrTotalMFMale;
        item["drilldown"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation + 'm';
        malePerBarangay.push(item);
    }
    var femalePerBarangay = [];
    for (var i = 0; i < print[0].arrTotalMFFemale.length; i++) {
        item = {};
        item["name"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation;
        item["y"] = print[0].arrTotalMFFemale[i].arrTotalMFFemale;
        item["drilldown"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation + 'f';
        femalePerBarangay.push(item);
    }

    var malePerAgeGroup = [];
    for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
        item = {};
        item["name"] = print[0].totalAgeGroupSex[i].ageGroup;
        item["y"] = -print[0].totalAgeGroupSex[i].male;
        item["drilldown"] = print[0].totalAgeGroupSex[i].ageGroup + 'm';
        malePerAgeGroup.push(item);
    }
    var femalePerAgeGroup = [];
    for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
        item = {};
        item["name"] = print[0].totalAgeGroupSex[i].ageGroup;
        item["y"] = print[0].totalAgeGroupSex[i].female;
        item["drilldown"] = print[0].totalAgeGroupSex[i].ageGroup + 'f';
        femalePerAgeGroup.push(item);
    }


    var male = [];
    for (i = 0; i < print[0].totalAgeGroupSex.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["id"] = print[0].totalAgeGroupSex[i].ageGroup + 'm';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].ageGroupBarangay.length; x++) {
            if (print[0].totalAgeGroupSex[i].ageGroup == print[0].ageGroupBarangay[x].ageGroup) {
                item2 = {}
                item2["name"] = print[0].ageGroupBarangay[x].location;
                item2["y"] = print[0].ageGroupBarangay[x].male;
                data.push(item2);
            }
        }
        item["data"] = data;
        male.push(item);
    }
    var drilldownCategories = [];
    for (i = 0; i < print[0].totalAgeGroupSex.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["id"] = print[0].totalAgeGroupSex[i].ageGroup + 'f';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].ageGroupBarangay.length; x++) {
            if (print[0].totalAgeGroupSex[i].ageGroup == print[0].ageGroupBarangay[x].ageGroup) {
                item2 = {}
                item2["name"] = print[0].ageGroupBarangay[x].location;
                item2["y"] = print[0].ageGroupBarangay[x].female;
                data.push(item2);
                drilldownCategories.push(print[0].ageGroupBarangay[x].location);
            }
        }
        item["data"] = data;
        male.push(item);
    }

    // Create the chart
    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'bar',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 10
                },
                relativeTo: 'chart'
            },
            events: {
                drilldown: function (e) {
                    var chart = this;
                    Highcharts.charts[0].xAxis[0].update({
                        reversed: true,
                        labels: {
                            step: 1
                        }});
                },
                drillup: function (e) {

                    var chart = this;
                    chart.xAxis[0].update({categories: topCategories,
                        reversed: false,
                        labels: {
                            step: 1
                        }});
                }
            }
        },
        title: {
            text: 'Household Population by Age Group and Sex'
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        plotOptions: {
            series: {
                dataLabels: {
                    stacking: 'normal'
                }
            }
        },
        xAxis: [{
                type: "category",
                reversed: false,
                labels: {
                    step: 1
                }
            }], //{ // mirror axis on right side
//                opposite: true,
//                reversed: false,
//                categories: topCategories,
//                linkedTo: 0,
//                labels: {
//                    step: 1
//                }
//            }],
        yAxis: {
            title: {
                text: null
            },
            labels: {
                formatter: function () {
                    return Math.abs(this.value);
                }
            }
        },
        plotOptions: {
            series: {
                stacking: 'normal'
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + ', age ' + this.point.category + '</b><br/>' +
                        'Population: ' + Highcharts.numberFormat(Math.abs(this.point.y), 0);
            }
        },
        series: [{
                name: 'Male',
                data: malePerAgeGroup
            }, {
                name: 'Female',
                color: '#FF9999',
                data: femalePerAgeGroup
            }],
        drilldown: {
            series: male
        }
    });
}



function getMaritalStatusData() {
    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    $.ajax({
        url: "SetMaritalDataServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear
        },
        success: function (data) {
            if (data[0].maritalTable.length === 0) {
                errorMessage(censusYear);
                document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();

                var str = '<table  id="dataTable" class="table table-bordered table-hover dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;
                $('#thead').append('<tr style="background-color: #454545; color: #fff">\n\
                                    <th style="width:10%">Location</th> \n\
                                    <th style="width:10%">Sex</th>\n\
                                    <th style="width:10%">Age Group</th> \n\
                                    <th style="width:10%">Single</th>\n\
                                    <th style="width:10%">Married</th> \n\
                                    <th style="width:10%">Widowed</th> \n\
                                    <th style="width:10%">Divorced / Separated</th> \n\
                                    <th style="width:10%">Common Law/Live-in</th> \n\
                                    <th style="width:10%">Unknown</th> \n\
                                    <th style="width:10%">Total</th> \n\
                                    </tr>');

                for (i = 0; i < print[0].maritalTable.length; i++) {
                    $('#data').append('<tr> \n\
                                        <td>' + print[0].maritalTable[i].location + '</td>  \n\
                                        <td>' + print[0].maritalTable[i].sex + '</td> \n\
                                        <td>' + print[0].maritalTable[i].ageGroup + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].single + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].married + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].widowed + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].divorced + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].liveIn + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].unknown + '</td> \n\
                                        <td class="centerTD number">' + print[0].maritalTable[i].total + '</td>'
                            + '</tr>');

                }

                //COMA
                formatTDNumber();
                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": true,
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    }
                });
                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chartMarital(print);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}


function chartMarital(print) {

    counter = 0;

    var barangays = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        barangays.push(print[0].maleBarangay[i].arrMaleLocation);
    }

    var ageGroups = [];
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                ageGroups.push(print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup);
            }
        }
    }


    var singleMale = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].maleBarangay[i].arrMaleLocation + " - single male";
        item["y"] = print[0].maleBarangay[i].arrMaleSingle;
        item["drilldown"] = true;
        singleMale.push(item);
    }

    var singleFemale = [];
    for (var i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].femaleBarangay[i].arrFemaleLocation + " - single female";
        item["y"] = print[0].femaleBarangay[i].arrFemaleSingle;
        item["drilldown"] = true;
        singleFemale.push(item);
    }

    var marriedMale = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].maleBarangay[i].arrMaleLocation + " - married male";
        item["y"] = print[0].maleBarangay[i].arrMaleMarried;
        item["drilldown"] = true;
        marriedMale.push(item);
    }

    var marriedFemale = [];
    for (var i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].femaleBarangay[i].arrFemaleLocation + " - married female";
        item["y"] = print[0].femaleBarangay[i].arrFemaleMarried;
        item["drilldown"] = true;
        marriedFemale.push(item);
    }

    var divorcedMale = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].maleBarangay[i].arrMaleLocation + " - divorced male";
        item["y"] = print[0].maleBarangay[i].arrMaleSeparated;
        item["drilldown"] = true;
        divorcedMale.push(item);
    }

    var divorcedFemale = [];
    for (var i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].femaleBarangay[i].arrFemaleLocation + " - divorced female";
        item["y"] = print[0].femaleBarangay[i].arrFemaleSeparated;
        item["drilldown"] = true;
        divorcedFemale.push(item);
    }

    var commonLawMale = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].maleBarangay[i].arrMaleLocation + " - common male";
        item["y"] = print[0].maleBarangay[i].arrMaleLiveIn;
        item["drilldown"] = true;
        commonLawMale.push(item);
    }

    var commonLawFemale = [];
    for (var i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].femaleBarangay[i].arrFemaleLocation + " - common female";
        item["y"] = print[0].femaleBarangay[i].arrFemaleLiveIn;
        item["drilldown"] = true;
        commonLawFemale.push(item);
    }


    var widowedMale = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].maleBarangay[i].arrMaleLocation + " - widowed male";
        item["y"] = print[0].maleBarangay[i].arrMaleWidowed;
        item["drilldown"] = true;
        widowedMale.push(item);
    }

    var widowedFemale = [];
    for (var i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].femaleBarangay[i].arrFemaleLocation + " - widowed female";
        item["y"] = print[0].femaleBarangay[i].arrFemaleWidowed;
        item["drilldown"] = true;
        widowedFemale.push(item);
    }

    var unknownMale = [];
    for (var i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].maleBarangay[i].arrMaleLocation + " - unknown male";
        item["y"] = print[0].maleBarangay[i].arrMaleUnknown;
        item["drilldown"] = true;
        unknownMale.push(item);
    }

    var unknownFemale = [];
    for (var i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = print[0].femaleBarangay[i].arrFemaleLocation + " - unknown female";
        item["y"] = print[0].femaleBarangay[i].arrFemaleUnknown;
        item["drilldown"] = true;
        unknownFemale.push(item);
    }

    var maritalStatus =
            {'Single': {
                    name: 'Male',
                    type: 'column',
                    data: singleMale
                },
                'Single2': {
                    name: 'Female',
                    type: 'column',
                    color: '#FF9999',
                    data: singleFemale
                },
                'Married': {
                    name: 'Male',
                    type: 'column',
                    data: marriedMale
                },
                'Married2': {
                    name: 'Female',
                    type: 'column',
                    color: 'red',
                    data: marriedFemale
                },
                'Divorced': {
                    name: 'Male',
                    type: 'column',
                    data: divorcedMale
                },
                'Divorced2': {
                    name: 'Female',
                    type: 'column',
                    color: '#bada55',
                    data: divorcedFemale
                },
                'Common Law/Live-in': {
                    name: 'Male',
                    type: 'column',
                    data: commonLawMale
                },
                'Common Law/Live-in2': {
                    name: 'Female',
                    type: 'column',
                    color: '#FF9999',
                    data: commonLawFemale
                },
                'Widowed': {
                    name: 'Male',
                    type: 'column',
                    data: widowedMale
                },
                'Widowed2': {
                    name: 'Female',
                    type: 'column',
                    color: '#FF9999',
                    data: widowedFemale
                },
                'Unknown': {
                    name: 'Male',
                    type: 'column',
                    data: unknownMale
                },
                'Unknown2': {
                    name: 'Female',
                    type: 'column',
                    color: '#FF9999',
                    data: unknownFemale
                }};

    for (i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleAgeGroup.length; x++) {
            if (print[0].maleBarangay[i].arrMaleLocation == print[0].maleAgeGroup[x].arrMaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].maleAgeGroup[x].arrMaleAgeGroupAgeGroup;
                item2["y"] = print[0].maleAgeGroup[x].arrMaleAgeGroupSingle;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].maleBarangay[i].arrMaleLocation + " - single male"] = item;
    }
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup;
                item2["y"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupFemaleSingle;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].femaleBarangay[i].arrFemaleLocation + " - single female"] = item;
    }

    for (i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleAgeGroup.length; x++) {
            if (print[0].maleBarangay[i].arrMaleLocation == print[0].maleAgeGroup[x].arrMaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].maleAgeGroup[x].arrMaleAgeGroupAgeGroup;
                item2["y"] = print[0].maleAgeGroup[x].arrMaleAgeGroupMarried;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].maleBarangay[i].arrMaleLocation + " - married male"] = item;
    }
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup;
                item2["y"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupFemaleMarried;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].femaleBarangay[i].arrFemaleLocation + " - married female"] = item;
    }


    for (i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleAgeGroup.length; x++) {
            if (print[0].maleBarangay[i].arrMaleLocation == print[0].maleAgeGroup[x].arrMaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].maleAgeGroup[x].arrMaleAgeGroupAgeGroup;
                item2["y"] = print[0].maleAgeGroup[x].arrMaleAgeGroupSeparated;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].maleBarangay[i].arrMaleLocation + " - divorced male"] = item;
    }
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup;
                item2["y"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupFemaleSeparated;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].femaleBarangay[i].arrFemaleLocation + " - divorced female"] = item;
    }

    for (i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleAgeGroup.length; x++) {
            if (print[0].maleBarangay[i].arrMaleLocation == print[0].maleAgeGroup[x].arrMaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].maleAgeGroup[x].arrMaleAgeGroupAgeGroup;
                item2["y"] = print[0].maleAgeGroup[x].arrMaleAgeGroupWidowed;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].maleBarangay[i].arrMaleLocation + " - widowed male"] = item;
    }
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = 'Female'
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup;
                item2["y"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupFemaleWidowed;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].femaleBarangay[i].arrFemaleLocation + " - widowed female"] = item;
    }

    for (i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleAgeGroup.length; x++) {
            if (print[0].maleBarangay[i].arrMaleLocation == print[0].maleAgeGroup[x].arrMaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].maleAgeGroup[x].arrMaleAgeGroupAgeGroup;
                item2["y"] = print[0].maleAgeGroup[x].arrMaleAgeGroupLiveIn;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].maleBarangay[i].arrMaleLocation + " - common male"] = item;
    }
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup;
                item2["y"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupFemaleLiveIn;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].femaleBarangay[i].arrFemaleLocation + " - common female"] = item;
    }

    for (i = 0; i < print[0].maleBarangay.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleAgeGroup.length; x++) {
            if (print[0].maleBarangay[i].arrMaleLocation == print[0].maleAgeGroup[x].arrMaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].maleAgeGroup[x].arrMaleAgeGroupAgeGroup;
                item2["y"] = print[0].maleAgeGroup[x].arrMaleAgeGroupUnknown;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].maleBarangay[i].arrMaleLocation + " - unknown male"] = item;
    }
    for (i = 0; i < print[0].femaleBarangay.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
            if (print[0].femaleBarangay[i].arrFemaleLocation == print[0].femaleAgeGroup[x].arrFemaleAgeGroupLocation) {
                item2 = {}
                item2["name"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupAgeGroup;
                item2["y"] = print[0].femaleAgeGroup[x].arrFemaleAgeGroupFemaleUnknown;
                data.push(item2);
            }
        }
        item["data"] = data;
        maritalStatus[print[0].femaleBarangay[i].arrFemaleLocation + " - unknown female"] = item;
    }

    console.log(JSON.stringify(maritalStatus));

    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'pie',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 30
                },
                relativeTo: 'chart'
            },
            events: {
                drilldown: function (e) {
                    if (!e.seriesOptions) {
                        var chart = this,
                                drilldowns = maritalStatus,
                                series = [drilldowns[e.point.name], drilldowns[e.point.name + '2']];
                        chart.addSingleSeriesAsDrilldown(e.point, series[0]);
                        chart.addSingleSeriesAsDrilldown(e.point, series[1]);
                        chart.applyDrilldown();
                        counter++;
                        if (counter === 1) {
                            chart.xAxis[0].update({categories: barangays});
                        } else if (counter === 2) {
                            chart.xAxis[0].update({categories: ageGroups});
                        }
                    }
                },
                drillup: function (e) {

                    var chart = this;
                    if (counter == 2) {
                        counter--;
                    }
                    if (counter === 1) {
                        chart.xAxis[0].update({categories: barangays});
                    }
                }
            }
        },
        title: {
            text: 'Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status'
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        xAxis: {
            type: 'category'
        },
        legend: {
            enabled: true
        },
        yAxis: {
            title: {text: 'Population'}
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true
                }
            }
        },
        series: [{
                name: 'Caloocan City',
                colorByPoint: true,
                data: [{
                        name: 'Single',
                        y: print[0].Total.maritalStatusSingle,
                        drilldown: true}, {
                        name: 'Common Law/Live-in',
                        y: print[0].Total.maritalStatusCommonLaw,
                        drilldown: true}, {
                        name: 'Divorced',
                        y: print[0].Total.maritalStatusDivorced,
                        drilldown: true}, {
                        name: 'Married',
                        y: print[0].Total.maritalStatusMaried,
                        drilldown: true}, {
                        name: 'Unknown',
                        y: print[0].Total.maritalStatusUnknown,
                        drilldown: true}, {
                        name: 'Widowed',
                        y: print[0].Total.maritalStatusWidowed,
                        drilldown: true
                    }]
            }],
        drilldown: {
            series: []
        }
    });

}



function getSchoolData() {
    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    var classification = document.getElementById('form_name').value;
    if (classification == "privateDirectory") {
        classification = "Private";
    } else {
        classification = "Public";
    }
    $.ajax({
        url: "SetSchoolDirectoryServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear,
            classification: classification
        },
        success: function (data) {
            if (data[0].det.length === 0) {
                errorMessage(censusYear);
                document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();
                $('#dataTable2').remove();

                var str = '<table id="dataTable" class="table table-bordered dataTable" role="grid" aria-describedby="incomplete_info">\n\
                            <thead style="display:none;"><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr></thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;

                for (i = 0; i < print[0].det.length; i++) {
                    var totalSeats = print[0].det[i].schoolDirectoryKinderSeats + print[0].det[i].schoolDirectoryElemSeats;
                    console.log(print[0].det[i].schoolDirectoryKinderSeats + print[0].det[i].schoolDirectoryElemSeats);
                    console.log(print[0].det[i].schoolDirectoryKinderSeats + '+' + print[0].det[i].schoolDirectoryElemSeats);
                    $('#data').append('<tr style = "background-color: #454545; color: #fff" >\n\
                                        <th style="vertical-align: bottom; text-align: left;" >Name of School</th>\n\
                                        <td class="nr" colspan = "8" style="border-right: none; text-align: left;">' + print[0].det[i].schoolDirectoryName + '</td>\n\
                                        <td style="border-left: none; text-align: right"></td>\n\
                                    </tr>\n\
                                    <tr>\n\
                                        <th colspan="6" style="text-align:center;">Teachers</th>\n\
                                        <th colspan="2" style="text-align:center;">Classrooms</th>\n\
                                        <th colspan="2" style="text-align:center;">Seats</th>\n\
                                    </tr>\n\
                                    <tr>\n\
                                        <th colspan="3" style="text-align:center;">Kinder</th>\n\
                                        <th colspan="3" style="text-align:center;">Elementary</th>\n\
                                        <th rowspan="2" style="vertical-align: bottom; text-align:center;\n\
                                            border-width: thin;\n\
                                            padding: 1%;">Kinder</th>\n\
                                        <th rowspan="2" style="vertical-align: bottom; text-align:center;">Elementary</th>\n\
                                        <th rowspan="2" style="vertical-align: bottom; text-align:center;">Kinder</th>\n\
                                        <th rowspan="2" style="vertical-align: bottom; text-align:center;">Elementary</th>\n\
                                    </tr>\n\
                                    <tr>\n\
                                        <th style="text-align:center;">Male</th>\n\
                                        <th style="text-align:center;">Female</th>\n\
                                        <th style="text-align:center;">Total</th>\n\
                                        <th style="text-align:center;">Male</th>\n\
                                        <th style="text-align:center;">Female</th>\n\
                                        <th style="text-align:center;">Total</th>\n\
                                    </tr>\n\
                                    <tr>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryKinderTeachersMale + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryKinderTeachersFemale + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryKinderTeachers + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryElemTeachersMale + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryElemTeachersFemale + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryElemTeachers + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryKinderClassroom + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryElemClassroom + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryKinderSeats + '</td>\n\
                                        <td class="centerTD number">' + print[0].det[i].schoolDirectoryElemSeats + '</td>\n\
                                    </tr>\n\
                                    <tr>\n\
                                        <th colspan="5" style="text-align:right; margin-right: 2%;">Total Teachers</th>\n\
                                        <td class="number" style="text-align:center;">' + (print[0].det[i].schoolDirectoryKinderTeachers + print[0].det[i].schoolDirectoryElemTeachers) + '</td>\n\
                                        <th style="text-align:right; margin-right: 2%;">Total Classrooms</th>\n\
                                        <td class="number" style="text-align:center;">' + (print[0].det[i].schoolDirectoryKinderClassroom + print[0].det[i].schoolDirectoryElemClassroom) + '</td>\n\
                                        <th style="text-align:right; margin-right: 2%;">Total Seats</th>\n\
                                        <td class="number" style="text-align:center;">' + totalSeats + '</td>\n\
                                    </tr>');
                }
                //COMA
                formatTDNumber();
                $.fn.dataTable.ext.errMode = 'none';
                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": false,
                    "pageLength": 12,
                    "lengthMenu": [[12, 24, 36, -1], [12, 24, 36, "All"]],
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    }
                });

                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chartSchool(print, classification);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}


function chartSchool(print, classification) {
    teachers = [{
            name: classification + ' Schools',
            y: print[0].total.totalTeachers,
            drilldown: 'teachers1'
        }];

    classrooms = [{
            name: classification + ' Schools',
            y: print[0].total.totalClassrooms,
            drilldown: 'classrooms1'
        }];

    seats = [{
            name: classification + ' Schools',
            category: classification + ' Schools',
            y: print[0].total.totalSeats,
            drilldown: 'seats1'
        }];

    var schoolsSeats = [];
    for (var i = 0; i < print[0].det.length; i++) {
        item = {};
        item["name"] = print[0].det[i].schoolDirectoryName;
        item["y"] = (print[0].det[i].schoolDirectoryElemSeats + print[0].det[i].schoolDirectoryKinderSeats);
        schoolsSeats.push(item);
    }

    var schoolClassrooms = [];
    for (var i = 0; i < print[0].det.length; i++) {
        item = {};
        item["name"] = print[0].det[i].schoolDirectoryName;
        item["y"] = (print[0].det[i].schoolDirectoryElemClassroom + print[0].det[i].schoolDirectoryKinderClassroom);
        schoolClassrooms.push(item);
    }

    var schoolTeachers = [];
    for (var i = 0; i < print[0].det.length; i++) {
        item = {};
        item["name"] = print[0].det[i].schoolDirectoryName;
        item["y"] = (print[0].det[i].schoolDirectoryElemTeachers + print[0].det[i].schoolDirectoryKinderTeachers);
        schoolTeachers.push(item);
    }


    // Create the chart
    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'column',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            name: classification + ' Schools',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 10
                },
                relativeTo: 'chart'
            }
        },
        title: {
            text: 'Number of Teachers and Classrooms for ' + classification + ' Schools'
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        plotOptions: {
            series: {
                dataLabels: {
                    format: '{point.name}: {point.y:.1f}'
                },
                column: {
                    stacking: 'normal'
                }
            }
        },
        xAxis: {
            type: 'category'
        },
        tooltip: {
            pointFormat: '<span style="color:{point.color}">{series.name}</span>: <b>{point.y}</b> of total<br/>'
        },
        legend: {
            enabled: true
        },
        series: [{
                name: 'Teachers',
                data: teachers
            }, {
                name: 'Classrooms',
                data: classrooms
            }, {
                name: 'Seats',
                data: seats
            }], drilldown: {
            series: [{
                    name: 'Teachers',
                    id: 'teachers1',
                    data: schoolTeachers
                },
                {
                    name: 'Classrooms',
                    id: 'classrooms1',
                    data: schoolClassrooms
                },
                {
                    name: 'Seats',
                    id: 'seats1',
                    data: schoolsSeats
                }
            ]
        }
    });
}

function getHospitalData() {
    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    var classification = document.getElementById('form_name').value;
    $.ajax({
        url: "SetHealthDirectoryData",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear
        },
        success: function (data) {
            if (data[0].privTable.length === 0) {
                errorMessage(censusYear);
                document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();
                $('#dataTable2').remove();

                var str = '<b style="font-size: large">Private Hospitals</b><br><table  id="dataTable" class="table table-bordered table-hover dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                var str2 = '<b style="font-size: large">Government Hospitals</b><br><table  id="dataTable2" class="table table-bordered table-hover dataTable"> <thead id="thead2">\n\
                            </thead>\n\
                            <tbody id="data2">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str + "<br><br>" + str2;
                $('#thead').append('<tr style="background-color: #454545; color: #fff">\n\
                                    <th>Name of Hospital</th>\n\
                                    <th>Address</th>\n\
                                    <th>Telephone/Fax Number</th>\n\
                                    <th>Total No. of Doctors</th>\n\
                                    <th>Total No. of Nurses</th>\n\
                                    <th>Total No. of Midwives</th>\n\
                                    <th>No. of Beds</th>\n\\n\
                                </tr>');
                $('#thead2').append('<tr style="background-color: #454545; color: #fff">\n\
                                    <th>Name of Hospital</th>\n\
                                    <th>Address</th>\n\
                                    <th>Telephone/Fax Number</th>\n\
                                    <th>Total No. of Doctors</th>\n\
                                    <th>Total No. of Nurses</th>\n\
                                    <th>Total No. of Midwives</th>\n\
                                    <th>No. of Beds</th>\n\\n\
                                </tr>');
                for (i = 0; i < print[0].privTable.length; i++) {
                    $('#data').append('<tr> \n\
                                        <td width="35%">' + print[0].privTable[i].privateDirectoryName + '</td>  \n\
                                        <td width="40%">' + print[0].privTable[i].privateDirectoryAddress + '</td> \n\
                                        <td>' + print[0].privTable[i].privateDirectoryTelephone + '</td> \n\
                                        <td class="format centerTD number">' + print[0].privTable[i].privateDirectoryDoctors + '</td> \n\
                                        <td class="format centerTD number">' + print[0].privTable[i].privateDirectoryNurses + '</td> \n\
                                        <td class="format centerTD number">' + print[0].privTable[i].privateDirectoryMidwives + '</td> \n\
                                        <td class="format centerTD number">' + print[0].privTable[i].privateDirectoryBeds + '</td>'
                            + '</tr>');
                }
                for (i = 0; i < print[0].pubTable.length; i++) {
                    $('#data2').append('<tr> \n\
                                        <td width="35%">' + print[0].pubTable[i].publicDirectoryName + '</td>  \n\
                                        <td width="40%">' + print[0].pubTable[i].publicDirectoryAddress + '</td> \n\
                                        <td>' + print[0].pubTable[i].publicDirectoryTelephone + '</td> \n\
                                        <td class="format centerTD number">' + print[0].pubTable[i].publicDirectoryDoctors + '</td> \n\
                                        <td class="format centerTD number">' + print[0].pubTable[i].publicDirectoryNurses + '</td> \n\
                                        <td class="format centerTD number">' + print[0].pubTable[i].publicDirectoryMidwives + '</td> \n\
                                        <td class="format centerTD number">' + print[0].pubTable[i].publicDirectoryBeds + '</td>'
                            + '</tr>');
                }
                //COMA
                formatTDNumber();

                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": true,
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    },
                    "columns": [
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'}
                    ]
                });
                $("#dataTable2").DataTable({
                    "paging": true,
                    "ordering": true,
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    },
                    "columns": [
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'},
                        {"orderDataType": "dom-text", type: 'string'}
                    ]
                });
                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chartHealth(print);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}

function chartHealth(print) {
    doctors = [{
            name: print[0].privTotal.totalPrivateClassification,
            y: print[0].privTotal.totalPrivateDoctors,
            drilldown: 'doctors1'
        }, {
            name: print[0].pubTotal.totalPublicClassification,
            y: print[0].pubTotal.totalPublicDoctors,
            drilldown: 'doctors2'
        }];

    nurses = [{
            name: print[0].privTotal.totalPrivateClassification,
            y: print[0].privTotal.totalPrivateNurses,
            drilldown: 'nurses1'
        }, {
            name: print[0].pubTotal.totalPublicClassification,
            y: print[0].pubTotal.totalPublicNurses,
            drilldown: 'nurses2'
        }];

    midwives = [{
            name: print[0].privTotal.totalPrivateClassification,
            y: print[0].privTotal.totalPrivateMidwives,
            drilldown: 'midwives1'
        }, {name: print[0].pubTotal.totalPublicClassification,
            y: print[0].pubTotal.totalPublicMidwives,
            drilldown: 'midwives2'
        }];

    beds = [{
            name: print[0].privTotal.totalPrivateClassification,
            y: print[0].privTotal.totalPrivateBeds,
            drilldown: 'beds1'
        }, {
            name: print[0].pubTotal.totalPublicClassification,
            y: print[0].pubTotal.totalPublicBeds,
            drilldown: 'beds2'
        }];

    var doctors1 = [];
    for (var i = 0; i < print[0].privTable.length; i++) {
        item = {};
        item["name"] = print[0].privTable[i].privateDirectoryName;
        item["y"] = print[0].privTable[i].privateDirectoryDoctors;
        doctors1.push(item);
    }

    var doctors2 = [];
    for (var i = 0; i < print[0].pubTable.length; i++) {
        item = {};
        item["name"] = print[0].pubTable[i].publicDirectoryName;
        item["y"] = print[0].pubTable[i].publicDirectoryDoctors;
        doctors2.push(item);
    }

    var nurses1 = [];
    for (var i = 0; i < print[0].privTable.length; i++) {
        item = {};
        item["name"] = print[0].privTable[i].privateDirectoryName;
        item["y"] = print[0].privTable[i].privateDirectoryNurses;
        nurses1.push(item);
    }

    var nurses2 = [];
    for (var i = 0; i < print[0].pubTable.length; i++) {
        item = {};
        item["name"] = print[0].pubTable[i].publicDirectoryName;
        item["y"] = print[0].pubTable[i].publicDirectoryNurses;
        nurses2.push(item);
    }

    var midwives1 = [];
    for (var i = 0; i < print[0].privTable.length; i++) {
        item = {};
        item["name"] = print[0].privTable[i].privateDirectoryName;
        item["y"] = print[0].privTable[i].privateDirectoryMidwives;
        midwives1.push(item);
    }

    var midwives2 = [];
    for (var i = 0; i < print[0].pubTable.length; i++) {
        item = {};
        item["name"] = print[0].pubTable[i].publicDirectoryName;
        item["y"] = print[0].pubTable[i].publicDirectoryMidwives;
        midwives2.push(item);
    }

    var beds1 = [];
    for (var i = 0; i < print[0].privTable.length; i++) {
        item = {};
        item["name"] = print[0].privTable[i].privateDirectoryName;
        item["y"] = print[0].privTable[i].privateDirectoryBeds;
        beds1.push(item);
    }

    var beds2 = [];
    for (var i = 0; i < print[0].pubTable.length; i++) {
        item = {};
        item["name"] = print[0].pubTable[i].publicDirectoryName;
        item["y"] = print[0].pubTable[i].publicDirectoryBeds;
        beds2.push(item);
    }

    // Create the chart
    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'column',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 10
                },
                relativeTo: 'chart'
            }
        },
        title: {
            text: 'List of Hospitals'
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        plotOptions: {
            series: {
                dataLabels: {
                    format: '{point.name}: {point.y:.1f}'
                }
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{point.color}">{series.name}</span>: <b>{point.y}</b> of total<br/>'
        },
        legend: {
            enabled: true
        },
        xAxis: {
            type: "category"
        },
        series: [{
                name: 'Doctors',
                data: doctors
            }, {
                name: 'Nurses',
                data: nurses
            }, {
                name: 'Midwives',
                data: midwives
            }, {
                name: 'Beds',
                data: beds
            }], drilldown: {
            series: [{
                    name: 'Doctors',
                    id: 'doctors1',
                    data: doctors1
                }, {
                    name: 'Doctors',
                    id: 'doctors2',
                    data: doctors2
                }, {
                    name: 'Nurses',
                    id: 'nurses1',
                    data: nurses1
                }, {
                    name: 'Nurses',
                    id: 'nurses2',
                    data: nurses2
                }, {
                    name: 'Midwives',
                    id: 'midwives1',
                    data: midwives1
                }, {
                    name: 'Midwives',
                    id: 'midwives2',
                    data: midwives2
                }, {
                    name: 'Beds',
                    id: 'beds1',
                    data: beds1
                }, {
                    name: 'Beds',
                    id: 'beds2',
                    data: beds2
                }
            ]}

    });
}

function getEnrollmentData() {
    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    var classification = document.getElementById('form_name').value;
    if (classification == "ePublic") {
        classification = "Public";
    } else {
        classification = "Private";
    }
    $.ajax({
        url: "SetEnrollmentDataServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear,
            classification: classification
        },
        success: function (data) {
            if (data[0].school.length === 0) {
                errorMessage(censusYear);
                document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();
                $('#dataTable2').remove();

                var str = '<table id = "dataTable" class="table table-bordered dataTable">\n\
                            <thead style="display:none;"><tr>\n\
<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th>\n\
</tr></thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;

                for (i = 0; i < print[0].school.length; i++) {
                    var lol = '<tr style = "background-color: #454545; color: #fff" >\n\
                                            <th colspan="2">School Name</th>\n\
                                            <td colspan="4">' + print[0].school[i].bySchoolName + '</td>\n\
                                            <th colspan="2">District</th>\n\
                                            <td colspan="2">' + print[0].school[i].bySchoolDistrict + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th colspan="2">School Type</th>\n\
                                            <td colspan="4">' + print[0].school[i].bySchoolSchoolType + '</td>\n\
                                            <th colspan="2">Gender Disparity Index</th>\n\
                                            <td colspan="2">' + print[0].school[i].bySchoolGDI + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th style="vertical-align: middle">Sex</th>';
                    for (y = 0; y < print[0].school[i].bySchoolGradeLevel.length; y++) {
                        lol += '<th style="vertical-align: middle">' + print[0].school[i].bySchoolGradeLevel[y].bySchoolGrade + '</th>';
                    }
                    lol += '<th style="vertical-align: middle">Grand Total</th>\n\
                                        </tr>\n\
                                        <tr class="EditTable">\n\
                                            <th>Male</th>';
                    for (y = 0; y < print[0].school[i].bySchoolGradeLevel.length; y++) {
                        lol += '<td class="number" style="text-align:center;">' + print[0].school[i].bySchoolGradeLevel[y].bySchoolGradeMale + '</td>';
                    }
                    lol += '<td class="number" style="text-align:center;">' + print[0].school[i].bySchoolTotalMale + '</td>\n\
                                            </tr>\n\
                                        <tr>\n\
                                            <th>Female</th>';
                    for (y = 0; y < print[0].school[i].bySchoolGradeLevel.length; y++) {
                        lol += '<td class="number" style="text-align:center;">' + print[0].school[i].bySchoolGradeLevel[y].bySchoolGradeFemale + '</td>';
                    }
                    lol += '<td class="number" style="text-align:center;">' + print[0].school[i].bySchoolTotalFemale + '</td>\n\
                                            </tr>\n\
                                        <tr>\n\
                                            <th>Total</th>';
                    for (y = 0; y < print[0].school[i].bySchoolGradeLevel.length; y++) {
                        lol += '<td class="number" style="text-align:center;">' + (print[0].school[i].bySchoolGradeLevel[y].bySchoolGradeMale + print[0].school[i].bySchoolGradeLevel[y].bySchoolGradeFemale) + '</td>';
                    }
                    lol += '<td class="number" style="text-align:center;">' + print[0].school[i].bySchoolGrandTotal + '</td>\n\
                                            </tr>';
                    $('#data').append(lol);
                }
                formatTDNumber();
                //How to hide datatable error
                $.fn.dataTable.ext.errMode = 'none';
                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": false,
                    "pageLength": 12,
                    "lengthMenu": [[12, 24, 36, -1], [12, 24, 36, "All"]],
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    }
                });
                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chartEnrollment(print);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}


function chartEnrollment(print) {
    male = [{
            name: print[0].total.totalName,
            y: print[0].total.totalMale,
            drilldown: 'male1'
        }];

    female = [{
            name: print[0].total.totalName,
            y: print[0].total.totalFemale,
            drilldown: 'female1'
        }];



    var male1 = [];
    for (var i = 0; i < print[0].grade.length; i++) {
        item = {};
        item["name"] = print[0].grade[i].byGradeLevel;
        item["y"] = print[0].grade[i].byGradeLevelMale;
        item["drilldown"] = print[0].grade[i].byGradeLevel + "m";
        male1.push(item);
    }

    var female1 = [];
    for (var i = 0; i < print[0].grade.length; i++) {
        item = {};
        item["name"] = print[0].grade[i].byGradeLevel;
        item["y"] = print[0].grade[i].byGradeLevelFemale;
        item["drilldown"] = print[0].grade[i].byGradeLevel + "f";
        female1.push(item);
    }

    var enrollment = [{
            name: 'Male',
            id: 'male1',
            data: male1
        }, {
            name: 'Female',
            id: 'female1',
            data: female1
        }];

    for (i = 0; i < print[0].grade.length; i++) {
        item = {};
        item["name"] = print[0].grade[i].byGradeLevel;
        item["id"] = print[0].grade[i].byGradeLevel + "m";
        data = [];
        for (x = 0; x < print[0].school.length; x++) {
            for (y = 0; y < print[0].school[x].bySchoolGradeLevel.length; y++)
                if (print[0].grade[i].byGradeLevel === print[0].school[x].bySchoolGradeLevel[y].bySchoolGrade) {
                    item2 = {}
                    item2["name"] = print[0].school[x].bySchoolName;
                    item2["y"] = print[0].school[x].bySchoolGradeLevel[y].bySchoolGradeMale;
                    data.push(item2);
                }
        }
        item["data"] = data;
        enrollment.push(item);
    }

    for (i = 0; i < print[0].grade.length; i++) {
        item = {};
        item["name"] = print[0].grade[i].byGradeLevel;
        item["id"] = print[0].grade[i].byGradeLevel + "f";
        data = [];
        for (x = 0; x < print[0].school.length; x++) {
            for (y = 0; y < print[0].school[x].bySchoolGradeLevel.length; y++)
                if (print[0].grade[i].byGradeLevel === print[0].school[x].bySchoolGradeLevel[y].bySchoolGrade) {
                    item2 = {}
                    item2["name"] = print[0].school[x].bySchoolName;
                    item2["y"] = print[0].school[x].bySchoolGradeLevel[y].bySchoolGradeFemale;
                    data.push(item2);
                }
        }
        item["data"] = data;
        enrollment.push(item);
    }


    // Create the chart
    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'column',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 10
                },
                relativeTo: 'chart'
            }
        },
        title: {
            text: 'Enrollment in ' + print[0].total.totalName
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        plotOptions: {
            series: {
                dataLabels: {
                    format: '{point.name}: {point.y:.1f}'
                }
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{point.color}">{series.name}</span>: <b>{point.y}</b> of total<br/>'
        },
        legend: {
            enabled: true
        },
        xAxis: {
            type: "category"
        },
        yAxis: {
            title: {text: 'Number of Students'}
        },
        series: [{
                name: 'Male',
                data: male
            }, {
                name: 'Female',
                data: female,
                color: '#FF9999'
            }], drilldown: {
            series: enrollment
        }
    });
}

function getNutritionalStatus() {
    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    $.ajax({
        url: "SetNutritionalStatusDataServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear
        },
        success: function (data) {
            if (data[0].total.length === 0) {
                errorMessage(censusYear);
                document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();
                $('#dataTable2').remove();

                var str = '<table id="dataTable" class="table table-bordered table-hover dataTable">\n\
                            <thead style="display:none"><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;
                for (i = 0; i < print[0].total.length; i++) {
                    var table = '<tr style="background-color: #454545; color: #fff">\n\
                                                <th>Location</th>\n\
                                                <td colspan="3">' + print[0].total[i].getDistrict + '</td>\n\
                                                <th>Grade Level</th>\n\
                                                <td colspan="3">' + print[0].total[i].getGradeLevel + '</td>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <th rowspan="2" colspan="2" class="centerTD">Enrollment</th>\n\
                                                <th rowspan="2" class="centerTD">No. of Pupils Weighed</th>\n\
                                                <th colspan="5" class="centerTD">Body Mass Index</th>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <th class="centerTD">Severely Wasted</th>\n\
                                                <th class="centerTD">Wasted</th>\n\
                                                <th class="centerTD">Normal</th>\n\
                                                <th class="centerTD">Overweight</th>\n\
                                                <th class="centerTD">Obese</th>\n\
                                            </tr>\n\
                                            <tr class="EditTable">\n\
                                                <th>Male</th>\n\
                                                <td class="centerTD number">' + print[0].total[i].getTotalMale + '</td>\n\
                                                <td class="centerTD number">' + print[0].total[i].getPupilsWeighedMale + '</td>';
                    for (y = 0; y < print[0].total[i].getNutritionalStatusBMI.length; y++) {
                        table += '<td class="centerTD number">' + print[0].total[i].getNutritionalStatusBMI[y].getMaleCounts + '</td>';
                    }
                    table += '</tr>\n\
                                            <tr>\n\
                                                <th>Female</th>\n\
                                                <td class="centerTD number">' + print[0].total[i].getTotalFemale + '</td>\n\
                                                <td class="centerTD number">' + print[0].total[i].getPupilsWeighedFemale + '</td>';
                    for (y = 0; y < print[0].total[i].getNutritionalStatusBMI.length; y++) {
                        table += '<td class="centerTD number">' + print[0].total[i].getNutritionalStatusBMI[y].getFemaleCounts + '</td>';
                    }
                    table += '</tr>\n\
                                            <tr>\n\
                                                <th>Total</th>\n\
                                                <td class="centerTD number">' + print[0].total[i].getTotalCount + '</td>\n\
                                                <td class="centerTD number">' + print[0].total[i].getPupilsWeighedTotal + '</td>';
                    for (y = 0; y < print[0].total[i].getNutritionalStatusBMI.length; y++) {
                        table += '<td class="centerTD number">' + (print[0].total[i].getNutritionalStatusBMI[y].getMaleCounts + print[0].total[i].getNutritionalStatusBMI[y].getFemaleCounts) + '</td>';
                    }
                    table += '</tr>';
                    $('#data').append(table);
                }
                formatTDNumber();

                $.fn.dataTable.ext.errMode = 'none';
                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": false,
                    "pageLength": 12,
                    "lengthMenu": [[12, 24, 36, -1], [12, 24, 36, "All"]],
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    }
                });
                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chartNutritionalStatus(print);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}


function chartNutritionalStatus(print) {

    var male = [];
    for (i = 0; i < print[0].highLevel.length; i++) {
        item = {};
        item['name'] = print[0].highLevel[i].BMI;
        item['y'] = print[0].highLevel[i].male;
        item['drilldown'] = print[0].highLevel[i].BMI + 'm';
        male.push(item);
    }

    var female = [];
    for (i = 0; i < print[0].highLevel.length; i++) {
        item = {};
        item['name'] = print[0].highLevel[i].BMI;
        item['y'] = print[0].highLevel[i].female;
        item['drilldown'] = print[0].highLevel[i].BMI + 'f';
        female.push(item);
    }

    var maleDistrict = [];
    for (i = 0; i < print[0].highLevel.length; i++) {
        item = {};
        item["name"] = 'Male';
        item["id"] = print[0].highLevel[i].BMI + "m";
        data = [];
        for (x = 0; x < print[0].district.length; x++) {
            console.log('LOL:' + print[0].district[x].BMI.length);
            for (y = 0; y < print[0].district[x].BMI.length; y++) {
                console.log('HERE');
                if (print[0].highLevel[i].BMI === print[0].district[x].BMI[y].getBMI) {
                    item2 = {};
                    item2["name"] = print[0].district[x].getDistrict;
                    item2["y"] = print[0].district[x].BMI[y].getMaleCounts;
                    item2["drilldown"] = print[0].district[x].getDistrict + print[0].district[x].BMI[y].getBMI + 'm';
                    data.push(item2);
                }

            }

        }
        item["data"] = data;
        maleDistrict.push(item);
    }

    for (i = 0; i < print[0].highLevel.length; i++) {
        item = {};
        item["name"] = 'Female';
        item["id"] = print[0].highLevel[i].BMI + "f";
        data = [];
        for (x = 0; x < print[0].district.length; x++) {
            console.log('LOL:' + print[0].district[x].BMI.length);
            for (y = 0; y < print[0].district[x].BMI.length; y++) {
                console.log('HERE');
                if (print[0].highLevel[i].BMI === print[0].district[x].BMI[y].getBMI) {
                    item2 = {};
                    item2["name"] = print[0].district[x].getDistrict;
                    item2["y"] = print[0].district[x].BMI[y].getFemaleCounts;
                    item2["drilldown"] = print[0].district[x].getDistrict + print[0].district[x].BMI[y].getBMI + 'f';
                    data.push(item2);
                }
            }
        }
        item["data"] = data;
        maleDistrict.push(item);
    }
    for (i = 0; i < print[0].district.length; i++) {
        for (x = 0; x < print[0].district[i].BMI.length; x++) {
            item = {};
            item["name"] = 'Female';
            item["id"] = print[0].district[i].getDistrict + print[0].district[i].BMI[x].getBMI + 'f';
            data = [];
            for (y = 0; y < print[0].grade.length; y++) {
                for (z = 0; z < print[0].grade[y].getNutritionalStatusBMI.length; z++) {
                    if (print[0].district[i].getDistrict == print[0].grade[y].getDistrict) {
                        if (print[0].district[i].BMI[x].getBMI == print[0].grade[y].getNutritionalStatusBMI[z].getBMI) {
                            item2 = {}
                            item2["name"] = print[0].grade[y].getGradeLevel;
                            item2["y"] = print[0].grade[y].getNutritionalStatusBMI[z].getFemaleCounts;
                            data.push(item2);
                        }
                    }
                }
            }
            item["data"] = data;
            maleDistrict.push(item);
        }
    }

    for (i = 0; i < print[0].district.length; i++) {
        for (x = 0; x < print[0].district[i].BMI.length; x++) {
            item = {};
            item["name"] = 'Male';
            item["id"] = print[0].district[i].getDistrict + print[0].district[i].BMI[x].getBMI + 'm';
            data = [];
            for (y = 0; y < print[0].grade.length; y++) {
                for (z = 0; z < print[0].grade[y].getNutritionalStatusBMI.length; z++) {
                    if (print[0].district[i].getDistrict == print[0].grade[y].getDistrict) {
                        if (print[0].district[i].BMI[x].getBMI == print[0].grade[y].getNutritionalStatusBMI[z].getBMI) {
                            item2 = {}
                            item2["name"] = print[0].grade[y].getGradeLevel;
                            item2["y"] = print[0].grade[y].getNutritionalStatusBMI[z].getMaleCounts;
                            data.push(item2);
                        }
                    }
                }
            }
            item["data"] = data;
            maleDistrict.push(item);
        }
    }
    console.log(JSON.stringify(maleDistrict));

    // Create the chart
    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'column',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 30
                },
                relativeTo: 'chart'
            }
        },
        title: {
            text: 'Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender'
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        plotOptions: {
            series: {
                dataLabels: {
                    format: '{point.name}: {point.y:.1f}'
                }
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{point.color}">{series.name}</span>: <b>{point.y}</b><br/>'
        },
        legend: {
            enabled: true
        },
        xAxis: {
            type: "category"
        },
        yAxis: {
            title: {text: 'Number of Students'}
        },
        series: [{
                name: 'Male',
                data: male
            }, {name: 'Female',
                data: female,
                color: '#FF9999'
            }],
        drilldown: {series: maleDistrict}
    });
}

function getHighestCompleted() {
    reportTitle.innerText = $('#form_name').find(":selected").text();
    var censusYear = document.getElementById('searchCensusYear').value;
    $.ajax({
        url: "SetHighestCompletedData",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear
        },
        success: function (data) {
            if (data[0].table.length === 0) {
                errorMessage(censusYear);
               // document.getElementById('contentHere').style.display = "none";
                document.getElementById('noReport').style.display = "block";
                document.getElementById("contentNone").innerHTML = "";
                document.getElementById("contentNone").innerHTML = "There are no " + $('#form_name').find(":selected").text() + " reports available for the year " + censusYear + ".";
            } else {
                document.getElementById("print_year").innerHTML =$('#form_name').find(":selected").text() + " Report for " + year;
                document.getElementById('contentHere').style.display = "block";
                document.getElementById('noReport').style.display = "display";
                var print = data;
                $('#dataTable').remove();
                $('#dataTable2').remove();

                var str = '<table id="dataTable" class="table table-bordered table-hover dataTable">\n\
                            <thead style="display:none"><th></th><th></th><th></th><th></th></thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;
                for (i = 0; i < print[0].table.length; i++) {
                    $('#data').append('<tr style="background-color: #454545; color: #fff">\n\
                                            <th>Location</th>\n\
                                            <td colspan="3">' + print[0].table[i].location + '</td>\n\
                                        </tr>\n\
                                        <tr style="background-color: #454545; color: #fff">\n\
                                            <th>Sex</th>\n\
                                            <td>' + print[0].table[i].sex + '</td>\n\
                                            <th>Age Group</th>\n\
                                            <td>' + print[0].table[i].ageGroup + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th>Highest Grade Completed</th>\n\
                                            <th class="centerTD">Total Count</th>\n\
                                            <th>Highest Grade Completed</th>\n\
                                            <th class="centerTD">Total Count</th>\n\
                                        </tr>');

                    for (y = 0; y < print[0].table[i].highestCompletedDet.length; y += 2) {
                        if (print[0].table[i].highestCompletedDet[y + 1] != null) {
                            $('#data').append('<tr>\n\
                                                        <th>' + print[0].table[i].highestCompletedDet[y].highestCompleted + '</th>\n\
                                                        <td class="centerTD number">' + print[0].table[i].highestCompletedDet[y].count + '</td>\n\
                                                        <th>' + print[0].table[i].highestCompletedDet[y + 1].highestCompleted + '</th>\n\
                                                        <td class="centerTD number">' + print[0].table[i].highestCompletedDet[y + 1].count + '</td></tr>');
                        } else {
                            $('#data').append('<tr>\n\
                                                        <th>' + print[0].table[i].highestCompletedDet[y].highestCompleted + '</th>\n\
                                                        <td class="centerTD number">' + print[0].table[i].highestCompletedDet[y].count + '</td>\n\
                                                        <th></th>\n\
                                                        <td></td></tr>');
                        }
                    }
                    $('#data').append('<tr>\n\
                                            <th></th>\n\
                                            <th></th>\n\
                                            <th>Total</th>\n\
                                            <td class="centerTD number">' + print[0].table[i].total + '</td>\n\
                                        </tr>\n\
                                        <% }%>');
                }
                formatTDNumber();
                $.fn.dataTable.ext.errMode = 'none';
                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": false,
                    "pageLength": 12,
                    "lengthMenu": [[12, 24, 36, -1], [12, 24, 36, "All"]],
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    }
                });
                $('#loadingSpinner').hide();
                $('input:text').focus(
                        function () {
                            $('#searchCensusYear').val('');
                        });
                chartHighestCompleted(print);
            }
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}


function chartHighestCompleted(print) {
    var barangays = [];
    for (x = 0; x < print[0].maleLocation.length; x++) {
        barangays.push(print[0].maleLocation[x].arrMaleLocationBarangay);
    }

    var total = [];
    for (i = 0; i < print[0].total.length; i++) {
        item = {};
        item["name"] = print[0].total[i].arrHighestCompleted;
        item["y"] = print[0].total[i].arrCount;
        item["drilldown"] = true;
        total.push(item);
    }

    var highestCompleted = [{
            name: 'Caloocan City',
            data: total
        }];

    var highestCompletedDrilldown = {};
    for (i = 0; i < print[0].total.length; i++) {
        item = {};
        item["name"] = "Male";
        item["type"] = 'column';
        data = [];
        for (x = 0; x < print[0].maleLocation.length; x++) {
            for (y = 0; y < print[0].maleLocation[x].arrMaleHighestCompletedLocation.length; y++)
                if (print[0].total[i].arrHighestCompleted == print[0].maleLocation[x].arrMaleHighestCompletedLocation[y].arrMaleLocationHighestCompleted) {
                    item2 = {}
                    item2["name"] = print[0].maleLocation[x].arrMaleLocationBarangay + print[0].maleLocation[x].arrMaleHighestCompletedLocation[y].arrMaleLocationHighestCompleted + ' - Male';
                    item2["y"] = print[0].maleLocation[x].arrMaleHighestCompletedLocation[y].arrMaleLocationCount;
                    item2["drilldown"] = true;
                    data.push(item2);
                }
        }
        item["data"] = data;
        highestCompletedDrilldown[print[0].total[i].arrHighestCompleted] = item;
    }

    for (i = 0; i < print[0].total.length; i++) {
        item = {};
        item["name"] = "Female";
        item["type"] = 'column';
        item["color"] = '#FF9999';
        data = [];
        for (x = 0; x < print[0].femaleLocation.length; x++) {
            for (y = 0; y < print[0].femaleLocation[x].arrFemaleHighestCompletedLocation.length; y++)
                if (print[0].total[i].arrHighestCompleted == print[0].femaleLocation[x].arrFemaleHighestCompletedLocation[y].arrFemaleLocationHighestCompleted) {
                    item2 = {}
                    item2["name"] = print[0].femaleLocation[x].arrFemaleLocationBarangay + print[0].femaleLocation[x].arrFemaleHighestCompletedLocation[y].arrFemaleLocationHighestCompleted + ' - Female';
                    item2["y"] = print[0].femaleLocation[x].arrFemaleHighestCompletedLocation[y].arrFemaleLocationCount;
                    item2["drilldown"] = true;
                    data.push(item2);
                }
        }
        item["data"] = data;
        highestCompletedDrilldown[print[0].total[i].arrHighestCompleted + '2'] = item;
    }
    var highestCompleted;
    for (i = 0; i < print[0].femaleLocation.length; i++) {
        for (y = 0; y < print[0].femaleLocation[i].arrFemaleHighestCompletedLocation.length; y++) {
            item = {};
            item["name"] = "Female";
            item["type"] = 'column';
            data = [];
            for (x = 0; x < print[0].femaleAgeGroup.length; x++) {
                for (z = 0; z < print[0].femaleAgeGroup[x].highestCompletedDetFemale.length; z++) {
                    if (print[0].femaleLocation[i].arrFemaleLocationBarangay == print[0].femaleAgeGroup[x].locationFemale) {
                        if (print[0].femaleLocation[i].arrFemaleHighestCompletedLocation[y].arrFemaleLocationHighestCompleted == print[0].femaleAgeGroup[x].highestCompletedDetFemale[z].highestCompletedFemale) {
                            item2 = {}
                            item2["name"] = print[0].femaleAgeGroup[x].highestCompletedDetFemale[z].highestCompletedFemale;
                            item2["y"] = print[0].femaleAgeGroup[x].highestCompletedDetFemale[z].countFemale;
                            data.push(item2);
                        }
                    }
                    item["data"] = data;
                    highestCompletedDrilldown[print[0].femaleLocation[i].arrFemaleLocationBarangay + print[0].femaleLocation[i].arrFemaleHighestCompletedLocation[y].arrFemaleLocationHighestCompleted + ' - Female'] = item;
                }
            }
        }
    }

    for (i = 0; i < print[0].maleLocation.length; i++) {
        for (y = 0; y < print[0].maleLocation[i].arrMaleHighestCompletedLocation.length; y++) {
            item = {};
            item["name"] = "Male";
            item["type"] = 'column';
            data = [];
            for (x = 0; x < print[0].maleAgeGroup.length; x++) {
                for (z = 0; z < print[0].maleAgeGroup[x].highestCompletedDetMale.length; z++) {
                    if (print[0].maleLocation[i].arrMaleLocationBarangay == print[0].maleAgeGroup[x].locationMale) {
                        if (print[0].maleLocation[i].arrMaleHighestCompletedLocation[y].arrMaleLocationHighestCompleted == print[0].maleAgeGroup[x].highestCompletedDetMale[z].highestCompletedMale) {
                            item2 = {}
                            item2["name"] = print[0].maleAgeGroup[x].highestCompletedDetMale[z].highestCompletedMale;
                            item2["y"] = print[0].maleAgeGroup[x].highestCompletedDetMale[z].countMale;
                            data.push(item2);
                        }
                    }
                    item["data"] = data;
                    highestCompletedDrilldown[print[0].maleLocation[i].arrMaleLocationBarangay + print[0].maleLocation[i].arrMaleHighestCompletedLocation[y].arrMaleLocationHighestCompleted + ' - Male'] = item;
                }
            }
        }
    }

    // Create the chart
    $('#byAgeGrpSex').highcharts({
        chart: {
            type: 'pie',
            zoomType: 'xy',
            panning: true,
            panKey: 'shift',
            resetZoomButton: {
                position: {
                    align: 'right', // by default
                    verticalAlign: 'top', // by default
                    x: -40,
                    y: 30
                },
                relativeTo: 'chart'
            },
            events: {
                drilldown: function (e) {
                    if (!e.seriesOptions) {
                        var chart = this,
                                drilldowns = highestCompletedDrilldown,
                                series = [drilldowns[e.point.name], drilldowns[e.point.name + '2']];
                        chart.addSingleSeriesAsDrilldown(e.point, series[0]);
                        chart.addSingleSeriesAsDrilldown(e.point, series[1]);
                        chart.applyDrilldown();
                        chart.xAxis[0].update({categories: barangays});
                    }

                }
            }
        },
        title: {
            text: 'Household Population 5 Yrs Old and Over by Highest Grade/Year Completed, Age Group and Sex'
        },
        subtitle: {
            text: 'Click and drag to zoom in. Hold down shift key to pan.'
        },
        xAxis: {
            type: 'category'
        },
        legend: {
            enabled: true
        },
        yAxis: {
            title: {text: 'Population'}
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true
                }
            }
        },
        series: highestCompleted,
        drilldown: {
            series: []
        }
    });
}


function errorMessage(year) {
    $("#notificationHeader").empty();
    $("#notificationBodyModal").empty();
    $("#notificationHeader").css({color: "#FFFFFF"});
    $("#modal_Header").css({background: "#b34112"});
    $("#notificationHeader").text("Warning!");
    $("#notificationBodyModal").append('<p style="padding:3%; text-align:center;">No Report Available for the year/word "' + year + '"</p>');
    $("#notificationModal").modal("show");
}