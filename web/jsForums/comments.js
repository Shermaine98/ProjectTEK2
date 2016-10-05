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
            table.setAttribute("class", "table");
            table.setAttribute("style", "");
            para.appendChild(table);

            var tbody = document.createElement("tbody");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");

                tbody.appendChild(tbodytr);

                $(tbodytr).append('<td><button class="btn btn-flat btn-default btn-sm disabled" style="width: 110%;">\n\
                                  <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 25%;"></i>'
                        + data[i].favoritesCount + '</button></td>');
                $(tbodytr).append('<td><span title="title">\n\
<input type="hidden" class="forumId" value=' + data[i].forumID + ' />  \n\
<a class="titleName">' + data[i].forumTitle + '</a></span><br/>\n\
<p style="font-size: 13px; color: #a3a3a3;">Created on '+data[i].dateCreated+' by ' + data[i].createdByName + '</p></td>');


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

                var tbodytrtop = document.createElement("tr");
                tbodytrtop.setAttribute("style", "background: #5a5a5a; color: #fff; height: 10px; font-size: 12px; ");
                tbody.appendChild(tbodytrtop);
                var tbodytr = document.createElement("tr");
                tbody.appendChild(tbodytr);
                var td1 = document.createElement("td");
                td1.setAttribute("width", "25%");
                tbodytr.appendChild(td1);
                var td2 = document.createElement("td");
                tbodytr.appendChild(td2);
                
                var tdTOP = document.createElement("td");
                tdTOP.setAttribute("colspan", "2");
                tbodytrtop.appendChild(tdTOP);

                $(tdTOP).append('Posted on ' + data[i].dateCreated);
                
                $(td1).append(data[i].commentedByName);
                $(td2).append(data[i].comment + '<br><br><h5 style="font-size: 13px; text-align:right;"> \n\
                                                <button class="btn btn-flat btn-primary btn-xs"">\n\
                                                    <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 1%;"></i> \n\
                                                    Count </button></h5>');
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