/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$('#approvedReport').click(function () {
    var fromID = $('#formID').val();
    document.getElementById('sformID').setAttribute('value', fromID);
    document.getElementById('approveServlet').submit();

});

