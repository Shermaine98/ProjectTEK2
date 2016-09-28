function setHHPopAgeGroupSex (censusYear){
    var censusYear = 2015;
    
    $.ajax({
        url: "SetDataServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            censusYear: censusYear
        },
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
            $('#byAgeGrpSex').highcharts({
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