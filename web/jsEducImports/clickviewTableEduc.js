/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */



$(document).ready(function () {
    
    $(document).on("click", '#clickTablePublicE', function () {
        var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                .find(".nr")     // Gets a descendent with class="nr"
                .text();
        document.getElementById('PubEformID').setAttribute('value', $item);
        document.getElementById('archivedPublicE').submit();


    });
    
    $(document).on("click", '#clickTablePrivateE', function () {
        var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                .find(".nr")     // Gets a descendent with class="nr"
                .text();         // Retrieves the text within <td>
        document.getElementById('PriEformID').setAttribute('value', $item);
        document.getElementById('archivedPrivateE').submit();

    });
    
    $(document).on("click", '#ClickedTableDirectoryPub', function () {
        var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                .find(".nr")     // Gets a descendent with class="nr"
                .text();         // Retrieves the text within <td>

        document.getElementById('dPubformID').setAttribute('value', $item);
        document.getElementById('archivedDirPub').submit();


    });

    $(document).on("click", '#ClickedTableDirectoryPri', function () {
        var $item = $(this).closest("tr")   // Finds the closest row <tr> 
                .find(".nr")     // Gets a descendent with class="nr"
                .text();         // Retrieves the text within <td>

        document.getElementById('dPriformID').setAttribute('value', $item);
        document.getElementById('archivedDirPri').submit();
    });

});