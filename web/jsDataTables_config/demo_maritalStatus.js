/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


$(function () {
    $("#marital_status").DataTable({
        "paging": true,
        "ordering": true,
        "bFilter": false,
        "info": false,
        "columns": [
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'}
        ]
    });
    $("#viewmarital_status").DataTable({
        "paging": true,
        "ordering": true,
        "info": false,
        "columns": [
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'}
        ]
    });
//    $("#error_maritalStatus").DataTable({
//        "paging": false,
//        "ordering": true,
//        "info": false,
//        "columns": [
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'},
//            {"orderDataType": "dom-text", type: 'string'}
//        ]
//    });
});