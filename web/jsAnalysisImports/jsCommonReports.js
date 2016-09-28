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
        error: {
            
        }
    });
}

function setMaritalStatus() {
    $.ajax({
        url: "SetMaritalStatusServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
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
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}