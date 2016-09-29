/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    Viewforums();
});


function Viewforums() {
    $.ajax({
        url: "ServletForums",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {

//            console.log(data);
            var element = document.getElementById("forumDiv");
            $('#forumDiv').empty();

            var para = document.createElement("div");
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
                $(tbodytr).append('<td><span title="favoritesCount"></span>' + data[i].favoritesCount + '</td>');
                $(tbodytr).append('<td><span title="comments">' + data[i].commentsCount + '</span</td>');
                $(tbodytr).append('<td><span title="favoritesCount"></span>' + data[i].commentsCount + '</td>');
                $(tbodytr).append('<td><span title="reports">' + data[i].reportCounts + '</span</td>');
                $(tbodytr).append('<td><span title="title">' + data[i].forumTitle + '</span></td>');
                $(tbodytr).append('<td><span title="body">' + data[i].body + '</span></td>');
                // $(tbodytr).append('<td><span title="createdBy">' + data[i].createdBy + '</span></td>');
                $(tbodytr).append('<td><span title="createdByName">' + data[i].createdByName + '</span></td>');
                var tbodytr2 = document.createElement("tr");
                tbody.appendChild(tbodytr2);
                for (var j = 0; data[i].tags.length > j; j++) {
                    $(tbodytr2).append('<td>' + data[i].tags[j].tag + '</td>');
                }
                $(tbodytr2).append('<td>' + data[i].dateCreated + '</td>');

            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });

}

function submitNewForum() {

    var forumTitle = document.getElementById('forumTitle').value;
    var forumBody = document.getElementById('forumBody').value;
    console.log(forumTitle);
    console.log(forumBody);

    $.ajax({
        url: "NewForumServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            forumTitle: forumTitle,
            forumBody: forumBody
        },
        
         success: function (data) {

        console.log(data);
            if (data === true) {
                Viewforums();
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(textStatus);
        }
    });

}