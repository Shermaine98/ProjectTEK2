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
                title: {
                text: 'User Sign-Ups',
                x: -20 //center
            },
            subtitle: {
                x: -20
            },
            xAxis: {
                type:'category'
            },
            yAxis: {
                title: {
                    text: 'Number of Sign-Ups'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
                series:[{ //isang line maraming data
                name: 'User Sign-Ups',
                data: data
                }]

        });

}
