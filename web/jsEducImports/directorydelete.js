/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $(document).on("click", "#invalidDirectory", function(){
            var redirect = "invalid";
            var schoolName = $(this).closest("tbody").find(".nr").text();
            var classification = document.getElementById("classification").value;
            var censusYear = document.getElementById("censusYear").value;
            $(this).closest("tbody").remove();

            $.ajax({
                url: "UpdateSchoolDirectory",
                type: 'POST',
                dataType: "JSON",
                data: {
                    redirect: redirect,
                    censusYear: censusYear,
                    schoolName: schoolName,
                    classification: classification
                },
                success: function (data) {
                    if (data === true){
                        alert("Directory Deleted");
                    }else{
                         alert("Not Sucessfuly Deleted");
                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    alert(XMLHttpRequest);
                }
            });
    });  
    
    $(document).on("click", "#updateDirectory", function(){
        $("#UpdateModal").modal("show");
            var schoolName = $(this).closest("tbody").find(".nr").text();
             $('#schoolName').val(schoolName);
            
    });
});