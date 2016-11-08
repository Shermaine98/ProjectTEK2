/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


$(document).ready(function () {
        $('#clickedTableAge, #clickedTableMarital, #clickedTableHighest').click(function () {
        if (this.id === 'clickedTableAge') {
            var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                    .find(".nr")     // Gets a descendent with class="nr"
                    .text();         // Retrieves the text within <td>

            document.getElementById('AformID').setAttribute('value', $item);
            document.getElementById('archivedViewAge').submit();
        } else if (this.id === 'clickedTableMarital') {
            var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                    .find(".nr")     // Gets a descendent with class="nr"
                    .text();         // Retrieves the text within <td>

            document.getElementById('MformID').setAttribute('value', $item);
            document.getElementById('archivedViewMarital').submit();
        } else if (this.id === 'clickedTableHighest') {
            var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                    .find(".nr")     // Gets a descendent with class="nr"
                    .text();         // Retrieves the text within <td>

            document.getElementById('HformID').setAttribute('value', $item);
            document.getElementById('archivedViewHighest').submit();
        }
    });
    
});