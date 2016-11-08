/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$(document).ready(function () {
    $(document).on("click", '#clickNutritional', function () {
        var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                .find(".nr")     // Gets a descendent with class="nr"
                .text();
        document.getElementById('HformID').setAttribute('value', $item);
        document.getElementById('Archivednutritional').submit();


    });

    $(document).on("click", '#directory', function () {
        var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                .find(".nr")     // Gets a descendent with class="nr"
                .text();         // Retrieves the text within <td>
        document.getElementById('DformID').setAttribute('value', $item);
        document.getElementById('directoryApproval').submit();

    });

});