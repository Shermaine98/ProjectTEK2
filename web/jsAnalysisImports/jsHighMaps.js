/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors:  Gian Carlo Roxas, Shermaine Sy, Geraldine Atayan
 */


$(function () {


//    var points = {
//    type: "mappoint",
//    data: [
//        { lat: 14.732941896443691, lon: 121.0489771164021 },
//        { lat: 14.658035496544379, lon: 120.99610733438706 },
//        { lat: 14.758447876197796, lon: 121.07170425963776 },
//        { lat: 14.740100559463535, lon: 121.01525019199126 },
//        { lat: 14.631247004006475, lon: 120.9978800953949 }
//    ]
//}
    // Prepare random data
    var data = [
        {
            "code": "BARANGAY 1"
        },
        {
            "code": "BARANGAY 2"
        },
        {
            "code": "BARANGAY 3"
        },
        {
            "code": "BARANGAY 4"
        },
        {
            "code": "BARANGAY 5"
        },
        {
            "code": "BARANGAY 6"
        },
        {
            "code": "BARANGAY 7"
        },
        {
            "code": "BARANGAY 8"
        },
        {
            "code": "BARANGAY 9"
        },
        {
            "code": "BARANGAY 10"
        }
    ];

    $.getJSON('https://api.myjson.com/bins/4olk3', function (geojson) {

        // Initiate the chart
        $('#map').highcharts('Map', {

            title : {
                text : 'Caloocan City Map'
            },

            mapNavigation: {
                enabled: true,
                buttonOptions: {
                    verticalAlign: 'bottom'
                }
            },
            tooltip: {
                formatter: function () {
                    return this.point.capital + ', ' + this.point.parentState + '<br>Lat: ' + this.point.lat + ' Lon: ' + this.point.lon + '<br>Population: ' + this.point.population;
                },
                crosshairs: [{
                    zIndex: 5,
                    dashStyle: 'dot',
                    snap: false,
                    color: 'white'
                }, {
                    zIndex: 5,
                    dashStyle: 'dot',
                    snap: false,
                    color: 'white'
                }],
            },
            series : [{       name: 'schools',
                type: "mappoint",
                "marker": {
                    "lineColor": "black",
                    "radius": 100
                },
                data: [
                    {name: 'haha', lat: 130.973849, lon: 0.648866}
                ]
            },{
                data : data,
                mapData: geojson,
                joinBy: ['BRGY', 'code'],
                name: 'Location',
                states: {
                    hover: {
                        color: '#BADA55'
                    }
                }
            }
        ]
        });
    });
});