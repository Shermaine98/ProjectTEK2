/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    HotTopic();
    Viewforums();

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
            table.setAttribute("style", "margin: 0 auto;");
            para.appendChild(table);

            var tbody = document.createElement("tbody");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");

                tbody.appendChild(tbodytr);

                $(tbodytr).append('<td><button class="btn btn-flat btn-default btn-sm disabled">\n\
                                  <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 25%;"></i>'
                        + data[i].favoritesCount + '</button></td>');
                $(tbodytr).append('<td><span title="title">\n\
<input type="hidden" class="forumId" value=' + data[i].forumID + ' />  \n\
<a class="titleName">' + data[i].forumTitle + '</a></span><br/>\n\
<p style="font-size: 13px; color: #a3a3a3;">Created on ' + data[i].dateCreated + ' by ' + data[i].createdByName + '</p></td>');


            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });

}

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
            table.setAttribute("class", "table");
            table.setAttribute("style", "margin: 0 auto;");
            para.appendChild(table);

            var tbody = document.createElement("tbody");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");

                tbody.appendChild(tbodytr);

                $(tbodytr).append('<td><button class="btn btn-block btn-primary btn-sm" id="btnFavorite"> <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 23%;"></i>' + data[i].favoritesCount + '</button></td>');
                $(tbodytr).append('<td><button class="btn btn-block btn-primary btn-sm"><i class="glyphicon glyphicon-comment" style="margin-right: 23%;"></i>' + data[i].commentsCount + '</button></td>');
            //    $(tbodytr).append('<td><button class="btn btn-block btn-primary" id="btnReport"> <i class="glyphicon glyphicon-thumbs-down" style="margin-right: 23%;"></i>' + data[i].reportCounts + '</button></td>');
                $(tbodytr).append('<td width="70%"><span title="title" class="pull-right"><input type="hidden" class="forumId" value=' + data[i].forumID + ' />  \n\
                    <a class="titleName" style="font-size: 22px;">' + data[i].forumTitle + '</a></span></td>');

                var tbodytr2 = document.createElement("tr");
                tbody.appendChild(tbodytr2);
//                $(tbodytr2).append('<td>');
                var tr2td = document.createElement("td");
                tr2td.setAttribute("colspan", "2");
                tbodytr2.appendChild(tr2td);
                for (var j = 0; data[i].tags.length > j; j++) {
                    $(tr2td).append('<button style="margin-right: 2%;" class="btn btn-flat btn-default btn-xs"><a class="tagsName">' + data[i].tags[j].tag + '</a></button>');
                }
//                $(tbodytr2).append('</td>');
                $(tbodytr2).append('<td width="70%"><h5 align="right" style="color: #777; font-size: 13px;">created on ' + data[i].dateCreated + ' by ' + data[i].createdByName + '</h5></td>');


            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });

}

function submitNewForum() {

    var forumTitle = document.getElementById('forumTitle').value;
    var forumBody = document.getElementById('forumBody').value;

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
                HotTopic();
                Viewforums();
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(textStatus);
        }
    });

}
var ctx = "${pageContext.request.contextPath}";
// if title is clicked - redirect get TitleName and redirect to specific forum page

$(document).on('click', '.titleName', function () {
    var forumId = $(this).prev().attr('value');
//    console.log(forumId);
    window.location.replace("ForumSpecificServlet?forumId=" + forumId);

});


// if tags is clicked - redirect get to page with tags

$(document).on('click', '.tagsName', function () {
    var tagsName = $(this).text();

    window.location.replace("request.getContextPath()/?tagsName=" + tagsName);


});