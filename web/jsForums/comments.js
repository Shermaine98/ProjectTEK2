/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    HotTopic();
    ViewComments();
});

function HotTopic() {
    $.ajax({
        url: "HotTopic",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {

//            console.log(data);
            var element = document.getElementById("hotTopicDiv");
            $('#hotTopicDiv').empty();

            var para = document.createElement("div");
            element.appendChild(para);

            var table = document.createElement("table");
            table.setAttribute("class", "table table-bordered");
            table.setAttribute("style", "");
            para.appendChild(table);

            var tbody = document.createElement("tbody");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");

                tbody.appendChild(tbodytr);

                $(tbodytr).append('<td>' + data[i].favoritesCount + '</td>');
                //    $(tbodytr).append('<td> <button  <i class="glyphicon glyphicon-list-alt"></i>' + data[i].commentsCount + '</button</td>');
                //     $(tbodytr).append('<td><button id="btnReport" <i class="glyphicon glyphicon-thumbs-down"></i>' + data[i].reportCounts + '</button</td>');
                $(tbodytr).append('<td><span title="title"><input type="hidden" class="forumId" value=' + data[i].forumID + ' />  <a class="titleName">' + data[i].forumTitle + '</a></span></td>');
                // $(tbodytr).append('<td><span title="body">' + data[i].body + '</span></td>');
                // $(tbodytr).append('<td><span title="createdBy">' + data[i].createdBy + '</span></td>');
                $(tbodytr).append('<td>' + data[i].dateCreated + '</td>');
                $(tbodytr).append('<td> ' + data[i].createdByName + '</td>');
                //    var tbodytr2 = document.createElement("tr");
                //    tbody.appendChild(tbodytr2);
                //    for (var j = 0; data[i].tags.length > j; j++) {
                //        $(tbodytr2).append('<td><a class="tagsName">' + data[i].tags[j].tag + '</a></td>');
                //    }


            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });

}
function ViewComments() {
    var forumID = document.getElementById('forumID').value;
    $.ajax({
        url: "CommentsGetterServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            forumID: forumID
        },
        success: function (data) {
            console.log(data.length);
            var element = document.getElementById("commentsBox");
            $('#commentsBox').empty();

            var table = document.createElement("table");
            table.setAttribute("class", "table table-bordered");
            table.setAttribute("style", "margin-top: 0");
            element.appendChild(table);
            var tbody = document.createElement("tbody");
            table.appendChild(tbody);

            for (var i = 0; i < data.length; i++) {

                var tbodytr = document.createElement("tr");
                tbody.appendChild(tbodytr);
                var td1 = document.createElement("td");
                td1.setAttribute("width", "25%");
                tbodytr.appendChild(td1);
                var td2 = document.createElement("td");
                tbodytr.appendChild(td2);

                $(td1).append(data[i].commentedByName);
                $(td2).append(data[i].comment + '<br><br><h5 style="font-size: 13px; text-align:right;"> \n\
                                                <button class="btn btn-flat btn-primary btn-xs" style="margin-right: 2%;">\n\
                                                    <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 1%;"></i> \n\
                                                    Count </button>\n\
                                                Posted on ' + data[i].dateCreated + '</h5>');
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });
}


function submitNewComment() {
    var forumTitle = document.getElementById('forumTitle').value;
    var forumID = document.getElementById('forumID').value;
    var createdBy = document.getElementById('commentedById').value;
    var comment = document.getElementById('comment').value;
    $.ajax({
        url: "CommentsServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            forumTitle: forumTitle,
            forumID: forumID,
            createdBy: createdBy,
            comment: comment
        },
        success: function (data) {

            console.log(data);
            if (data === true) {
                HotTopic();
                ViewComments();
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(textStatus);
        }
    });
}