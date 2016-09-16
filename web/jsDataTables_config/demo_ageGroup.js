$(function () {
    $("#dataTable-ageGroup").DataTable({
        "paging": true,
        "ordering": true,
        "bFilter": false,
        "info": false,
        "columns": [
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'}
        ]
    });
    $("#error-ageGroup").DataTable({
        "paging": false,
        "ordering": true,
        "bFilter": false,
        "info": false,
        "columns": [
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'}
        ]
    });
    $("#approval-ageGroup").DataTable({
        "paging": true,
        "ordering": true,
        "info": false,
        "columns": [
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'},
            {"orderDataType": "dom-text", type: 'string'}
        ]
    });
});