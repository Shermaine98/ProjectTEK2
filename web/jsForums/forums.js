/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    integrate();
});


function integrate() {
    $.ajax({
        url: "ServletForums",
        type: 'POST',
        dataType: "JSON"
        , success: function (data) {

            console.log(data);
            var para = document.createElement("div");
            var element = document.getElementById("forumBody");
            element.appendChild(para);

            var table = document.createElement("table");
            table.setAttribute("class", "table table-hover table-bordered");
            table.setAttribute("style", " margin: 0 auto;");
            para.appendChild(table);

            var tbody = document.createElement("tbody");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");

                tbody.appendChild(tbodytr);
                $(tbodytr).append('<td><span title="favoritesCount"></span>'+data[i].favoritesCount+'</td>');
                $(tbodytr).append('<td><span title="comments">'+data[i].commentsCount+'</span</td>');
                $(tbodytr).append('<td><span title="favoritesCount"></span>'+data[i].commentsCount+'</td>');
                $(tbodytr).append('<td><span title="reports">' + data[i].reportCounts + '</span</td>');
                $(tbodytr).append('<td><span title="favorites"></span></td>');
                $(tbodytr).append('<td><span title="title">' + data[i].forumTitle + '</span></td>');
                 $(tbodytr).append('<td><span title="title">' + data[i].body + '</span></td>');
                $(tbodytr).append('<td><span title="createdBy">' + data[i].createdBy + '</span></td>');
                
                var tbodytr2 = document.createElement("tr");
                tbody.appendChild(tbodytr2);
                for (var j = 0; data[j].tags.length > j; i++) {
                $(tbodytr2).append('<td>'+data[i].tags[j].tag+'</td>');
                }
            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(exception);
        }
    });

}