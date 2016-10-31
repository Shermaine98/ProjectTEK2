/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    //var print;

    $.ajax({
        url: "AdminHome",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            //print = data;
            //chart(print);
            setChart(data);
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
});
function setChart(print){
   
        var data = [];
        for (var i = 0; i < print[0].series.length; i++) {
            item = {};
            item["name"] = print[0].series[i].name;
            item["y"] = print[0].series[i].y;
            data.push(item);
        }
       
        console.log(JSON.stringify(data));
    
        $('#container').highcharts({
            chart: {
                type: 'spline',
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
                text: 'User Sign-Ups',
                x: -20 //center,
            },
            subtitle: {
                x: -20
            },
            xAxis: {
                type:'category'
            },
            yAxis: {
                title: {
                    text: '',
                    color: '#E2E2E2'
                }
            },
                series:[{ //isang line maraming data
                name: 'User Sign-Ups',
                data: data
                }]

        });

}
