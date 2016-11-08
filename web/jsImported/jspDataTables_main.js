/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


$(function () {
    $("#incomplete").DataTable({
        "paging": false,
        "bFilter": false,
        "ordering": false,
        "info": false,
         "aoColumnDefs": [
          { 'bSortable': false, 'aTargets': [ -1 ] }
       ],
        "language": {
        "emptyTable":     "There are no incomplete reports"
    }
        
    });
    
    $("#archived").DataTable({
        "paging": true,
        "ordering": false,
        "info": false,
        "language": {
        "emptyTable":     "There are no archived reports"
    }
    });
    
    $("#approved").DataTable({
        "paging": false,
        "bFilter": false,
        "ordering": false,
        "info": false,
        "language": {
        "emptyTable":     "There are no reports waiting for approval"
    }
    });
});