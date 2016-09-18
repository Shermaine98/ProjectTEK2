/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#approvedReport').click(function () {
    var fromID = $('#formID').val();
    document.getElementById('sformID').setAttribute('value', fromID);
    document.getElementById('approveServlet').submit();

});

