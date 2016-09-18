/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#rootwizard').bootstrapWizard({'tabClass': 'nav nav-pills'});
    $("#archived").DataTable({
        "paging": false,
        "ordering": true,
        "info": false, "language": {
            "emptyTable": "No Data"
        }
    });

    $("#archived tbody").on("click", 'input[type="button"]', (function () {

        var censusYear = $(this).closest("tr").find(".nr").text();
        var page = document.getElementById('page').value;
        $.ajax({
            url: "SetDataServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                censusYear: censusYear,
                page: page
            },
            success: function (data) {
                var print = data;
                $('#dataTable').remove();

                var str = '<table  id="dataTable" class="table table-bordered table-hover dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
                document.getElementById("TableHolder").innerHTML = str;
                $('#thead').append('<tr>\n\
                                    <th>Location</th> \n\
                                    <th>Age Group</th>\n\
                                    <th>Both Sexes</th> \n\
                                    <th>Male Count</th>\n\
                                    <th>Female Count</th> \n\
                                    </tr>');



                for (i = 0; i < print[0].ByAgeGroupSexTable.length; i++) {
                    $('#data').append('<tr> \n\
                                        <td><input type="text" readonly  value="' + print[0].ByAgeGroupSexTable[i].location + '"/></td>  \n\
                                        <td><input type="text" readonly value="' + print[0].ByAgeGroupSexTable[i].AgeGroup + '"/></td> \n\
                                        <td><input class="format" type="number" readonly value="' + print[0].ByAgeGroupSexTable[i].BothSexes + '" /></td> \n\
                                        <td><input class="format" type="number" readonly value="' + print[0].ByAgeGroupSexTable[i].Male + '" /></td> \n\
                                        <td><input class="format" type="number" readonly  value="' + print[0].ByAgeGroupSexTable[i].Female + '" /></td>'
                                         + '</tr>');

                }
                $("#dataTable").DataTable({
                    "paging": false,
                    "ordering": true,
                    "info": false, "language": {
                        "emptyTable": "No Data"
                    }
                });
                chart(print);
            }, error: function (XMLHttpRequest, textStatus, exception) {
                alert(XMLHttpRequest.responseText);
            }
        });
    }));






   function chart(print){
       var MFTotalBgy = [];
       var location = [];
       var male = [];
       var female = [];
       var malePerBarangay = [];
       for(var i = 0; i < print[0].arrTotalMFFemale.length; i++){
           item={};
           item["name"]=print[0].arrTotalMFFemale[i].arrTotalMFlocation;
           item["y"]=print[0].arrTotalMFFemale[i].arrTotalMFMale;
           item["drilldown"]=print[0].arrTotalMFFemale[i].arrTotalMFlocation+'m';
           malePerBarangay.push(item);
       }
       console.log(JSON.stringify(malePerBarangay));
       var femalePerBarangay = [];
       for(var i = 0; i < print[0].arrTotalMFFemale.length; i++){
           item={};
           item["name"]=print[0].arrTotalMFFemale[i].arrTotalMFlocation;
           item["y"]=print[0].arrTotalMFFemale[i].arrTotalMFFemale;
           item["drilldown"]=print[0].arrTotalMFFemale[i].arrTotalMFlocation+'f';
           femalePerBarangay.push(item);
       }
       
        var sexAgeGroup = [{
            name: 'Male per Barangay',
            id: 'male',
            type: 'column',
            data: malePerBarangay
            },
            {
            name: 'Female per Barangay',
            id: 'female',
            type: 'column',
            data: femalePerBarangay
            }
        ];
        for(i = 0; i < print[0].arrTotalMFFemale.length; i++){
            item={};
            item["name"]='Male in ' + print[0].arrTotalMFFemale[i].arrTotalMFlocation;
            item["id"]=print[0].arrTotalMFFemale[i].arrTotalMFlocation + 'm';
            item["type"]='column';
            data=[];
            for(x = 0; x < print[0].arrByAgeGroup.length;x++){
                if(print[0].arrTotalMFFemale[i].arrTotalMFlocation==print[0].arrByAgeGroup[x].arrByAgeGrouplocation){
                    item2={}
                    item2["name"]=print[0].arrByAgeGroup[x].arrByAgeGroupAgeGroup;
                    item2["y"]=print[0].arrByAgeGroup[x].arrByAgeGroupMale;
                    data.push(item2);
                }
            }
            item["data"]=data;
//            main.push(item);
//            maleAgeGroup.push(main,data);
            sexAgeGroup.push(item);
       }
       
       for(i = 0; i < print[0].arrTotalMFFemale.length; i++){
            item={};
            item["name"]='Female in ' + print[0].arrTotalMFFemale[i].arrTotalMFlocation;
            item["id"]=print[0].arrTotalMFFemale[i].arrTotalMFlocation + 'f';
            item["type"]='column';
            data=[];
            for(x = 0; x < print[0].arrByAgeGroup.length;x++){
                if(print[0].arrTotalMFFemale[i].arrTotalMFlocation==print[0].arrByAgeGroup[x].arrByAgeGrouplocation){
                    item2={}
                    item2["name"]=print[0].arrByAgeGroup[x].arrByAgeGroupAgeGroup;
                    item2["y"]=print[0].arrByAgeGroup[x].arrByAgeGroupFemale;
                    data.push(item2);
                }
            }
            item["data"]=data;
//            main.push(item);
//            maleAgeGroup.push(main,data);
            sexAgeGroup.push(item);
       }
       
       // Create the chart
        $('#byAgeGrpSex').highcharts({
            chart: {
                    type: 'pie',
                    zoomType: 'x',
                    panning: true,
                    panKey: 'shift'
                },
            title: {
                    text: 'By Age Group and Sex'
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
            xAxis: {
                type: 'category'
            },
            tooltip: {
                    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
                },
            legend: {
                enabled: false
            },           
            series: [{
                    name: 'Caloocan City',
                    colorByPoint: true,
                    data: [{
                        name: 'Male',
                        y: print[0].Total.TotalMale,
                        drilldown: 'male'}, {
                        name: 'Female',
                        y: print[0].Total.TotalFemale,
                        drilldown: 'female',
                        color: '#FF9999' 
                        }]
                }],drilldown:{
                    series: 
                        sexAgeGroup
                    
                    }
                });
            }
        });
