function setHHPopAgeGroupSex (){
    $.ajax({
        url: "SetHHPopAgeGroupSex",
        type: 'POST',
        dataType: "JSON",
        success: function(data){
            var print = data;
            var malePerBarangay = [];
            PerBarangay = [];
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
            var topCategories = [];
            for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
                topCategories.push(print[0].totalAgeGroupSex[i].ageGroup);
            }
            
            // Create the chart
            $('#output').highcharts({
                chart: {
                    type: 'bar',
                    zoomType: 'x',
                    panning: true,
                    panKey: 'shift',
                    events: {
                        drilldown: function (e) {
                            var chart = this;
                            Highcharts.charts[0].xAxis[0].update({categories: drilldownCategories,
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
                                }
                            });
                        }
                    }
                },
                title: {
                    text: 'Household Population by Age Group and Sex for ' + print[0].Total.latestYear
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
                        categories: topCategories,
                        reversed: false,
                        labels: {
                            step: 1
                        }
                    }],
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
            })
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}

    var print;
    var city = "Caloocan City";
    function setMaritalStatus(chart) {
        $.ajax({
        url: "SetMaritalStatusServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            var print = data;
            for (var i = 0; i < print[0].years.length; i++) {
                    alert(print[0].years[i].year);
                    $('#years').append('<input type="checkbox" class="filter" id="year" value="' 
                            + print[0].years[i].year + '"checked>'
                            +print[0].years[i].year+'</input></br>');
                }

                //district
                for (var i = 0; i < print[0].districts.length; i++) {
                    if(print[0].districts[i].district!=city){
                        $('#districts').append('<input type="checkbox" class="filter" id="district" value="' 
                                + print[0].districts[i].district + '" checked>'+print[0].districts[i].district+'</input></br>');
                    }
                }
            if(chart=="0"||chart=="Pie Chart"){
                drawMaritalStatusBar(print);
            }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
    }
    
    var year = [];
    var district = [];
    var barangay = [];
    var analysischart = [];

    
    //CLICK location
    $(document).on("click", '.filter', function () {
        year = [];
        district = [];
        analysischart[0] = print[0];

        $('[id="district"]:checked').each(function (e) {
            var id = $(this).attr('value');
            district.push(id);
        });

        $('[id="year"]:checked').each(function (e) {
            var id = $(this).attr('value');
            year.push(id);    
        });
        
        removeYear(analysischart, year);
        removeDistrict(analysischart, district);
        chart(analysischart);

    });

    function removeYear(analysischart, year){
        analysischart[0].years.length = 0;
        for (var x = 0; x < year.length; x++) {
            item = {};
            item["year"] = year[x];
            analysischart[0].years.push(item);
        }
    }
    
    function removeDistrict(analysischart, district){
        analysischart[0].districts.length = 0;

        for (var x = 0; x < district.length; x++) {
            item = {};
            item['district'] = district[x];
            analysischart[0].districts.push(item);
        }
    }
    
    function removeBarangay(analysischart, barangay){
        analysischart[0].barangays.length = 0;

        for (var x = 0; x < barangay.length; x++) {
            item = {};
            item["barangay"] = barangay[x];
            analysischart[0].barangays.push(item);
        }
    }
    
    
    function drawMaritalStatusBar(print){
    
            var print = data;    
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

            $('#output').highcharts({
                chart: {
                    type: 'pie',
                    zoomType: 'x',
                    panning: true,
                    panKey: 'shift',
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
                    text: 'Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status for ' + print[0].Total.censusYear
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



function setClassroomRequirement(){
    $.ajax({
        url: "SetClassroomRequirementServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            var print = data;   
            var totalEnrollees = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'e';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].enrollment.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].enrollment[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].enrollment[x].year) {
                                totals = totals + parseInt(print[0].enrollment[x].enrollment); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalEnrollees.push(item);
            }
            
            var totalClassrooms = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'c';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].classrooms.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].classrooms[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].classrooms[x].year) {
                                totals = totals + (parseInt(print[0].classrooms[x].classrooms)*45); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalClassrooms.push(item);
            }
            
            var totalGaps = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'g';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].gaps.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].gaps[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].gaps[x].year) {
                                totals = totals + parseInt(print[0].gaps[x].gap); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalGaps.push(item);
            }
            
            $('#output').highcharts({
            chart: {
                type: 'column',
                drilled: false,
                zoomType: 'xy',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: 'Classroom Requirements in Public Elementary School'
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
                    name: 'Enrollees',
                    type: 'column',
                    data: totalEnrollees
                },  {
                    name: 'Classrooms',
                    type: 'column',
                    data: totalClassrooms
                }, {
                    name: 'Gaps',
                    type: 'column',
                    color: '#FF3232',
                    data: totalGaps
                    
                }
            ],
            drilldown: {
                //series: drilldownsHospital
            }
        });
            
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}

function setEnrollmentTeacherClassroom(){
    $.ajax({
        url: "EnrollmentTeachersClassrooms",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            var print = data;   
            var totalEnrollees = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'e';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].enrollment.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].enrollment[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].enrollment[x].year) {
                                totals = totals + parseInt(print[0].enrollment[x].enrollment); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalEnrollees.push(item);
            }
            
            var totalClassrooms = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'c';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].classrooms.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].classrooms[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].classrooms[x].year) {
                                totals = totals + (parseInt(print[0].classrooms[x].classrooms)); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalClassrooms.push(item);
            }
            
            var totalTeachers = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 't';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].classrooms.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].classrooms[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].classrooms[x].year) {
                                totals = totals + (parseInt(print[0].classrooms[x].teachers)); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalTeachers.push(item);
            }
            
            
            $('#output').highcharts({
            chart: {
                type: 'column',
                drilled: false,
                zoomType: 'xy',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: 'Classroom Requirements in Public Elementary School'
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
                    name: 'Enrollees',
                    type: 'column',
                    data: totalEnrollees
                },  {
                    name: 'Teachers',
                    type: 'column',
                    data: totalTeachers
                }, {
                    name: 'Classrooms',
                    type: 'column',
                    color: '#FF3232',
                    data: totalClassrooms
                    
                }
            ],
            drilldown: {
                //series: drilldownsHospital
            }
        });
            
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}