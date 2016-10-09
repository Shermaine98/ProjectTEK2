/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getTags();
    Viewforums();
    HotTopic();
    
    //add like
    
    var decision = "forum";
    var isLike;
    $(document).on("click", '.nDefault', function () {

        var forumId = $(this).closest("tr").find("span.forumId").text();
        var forumTitle = $(this).closest("tr").find("span.forumTitle").text();
        var createdby = $(this).closest("tr").find("span.createdBy").text();
        isLike = "true";
        $.ajax({
            context: this,
            url: "FavoriteServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                decision: decision,
                forumId: forumId,
                forumTitle: forumTitle,
                createdby: createdby,
                isLike: isLike
            },
            success: function (data) {

                if (data === true) {
                    getTags();
                    Viewforums();
                    HotTopic();
                }

            }, error: function (XMLHttpRequest, textStatus, exception) {
                console.log(exception);
            }
        });
    });

//remove like
    $(document).on("click", '.nPrimary', function () {
        var forumId = $(this).closest("tr").find("span.forumId").text();

        isLike = "false";
        $.ajax({
            url: "FavoriteServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                decision: decision,
                isLike: isLike,
                forumId: forumId
            },
            success: function (data) {
                if (data === true) {
                    getTags();
                    Viewforums();
                    HotTopic();
                }
            }, error: function (XMLHttpRequest, textStatus, exception) {
                console.log(exception);
            }
        });



    });

});

function HotTopic() {
    $.ajax({
        url: "HotTopic",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {

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
                if (data[i].isLike === false) {
                    $(tbodytr).append('<td><button class="btn btn-flat btn-default btn-sm disabled">\n\
                                  <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 25%;"></i>'
                            + data[i].favoritesCount + '</button></td>');
                } else {
                    $(tbodytr).append('<td><button class="btn btn-flat btn-primary btn-sm disabled">\n\
                                  <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 25%;"></i>'
                            + data[i].favoritesCount + '</button></td>');

                }

                $(tbodytr).append('<span style="display: none;" class="forumId">' + data[i].forumID + '</span> \n\
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

            var element = document.getElementById("forumDiv");
            $('#forumDiv').empty();

            var para = document.createElement("div");
            element.appendChild(para);

            var table = document.createElement("table");
            table.setAttribute("class", "table");
            table.setAttribute("style", "margin: 0 auto;");
            para.appendChild(table);

            var tbody = document.createElement("tbody");
             tbody.setAttribute("class", "list");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");

                tbody.appendChild(tbodytr);
                if (data[i].isLike === false) {
                    $(tbodytr).append('<td><button class="btn btn-block btn-default btn-sm nDefault" id="btnFavorite"> <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 23%;"></i>' + data[i].favoritesCount + '</button></td>');
                } else {
                    $(tbodytr).append('<td><button class="btn btn-block btn-primary btn-sm nPrimary" id="btnFavorite"> <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 23%;"></i>' + data[i].favoritesCount + '</button></td>');

                }


                $(tbodytr).append('<td><button class="btn btn-block btn-default btn-sm"><i class="glyphicon glyphicon-comment" style="margin-right: 23%;"></i>' + data[i].commentsCount + '</button></td>');
                $(tbodytr).append('<td class="forumTitleSearch"><span style="display: none;" class="createdBy">' + data[i].createdBy + '</span><span style="display: none;" class="forumId">' + data[i].forumID + '</span> \n\
                    <a class="titleName" style="font-size: 22px;"><span class="forumTitle">' + data[i].forumTitle + '</span></a></td>');

                var tbodytr2 = document.createElement("tr");
                tbody.appendChild(tbodytr2);
                var tr2td = document.createElement("td");
                tr2td.setAttribute("colspan", "2");
                tbodytr2.appendChild(tr2td);
                for (var j = 0; data[i].tags.length > j; j++) {
                    $(tr2td).append('<p style="margin-right: 2%;" class="tagSearch btn btn-flat btn-default btn-xs">' + data[i].tags[j].tag + '</p>');
                }
                $(tbodytr2).append('<td width="70%"><h5 align="right" style="color: #777; font-size: 13px;">created on ' + data[i].dateCreated + ' by ' + data[i].createdByName + '</h5></td>');
                    
                
                var options = {
                    valueNames: [ 'forumTitleSearch', 'tagSearch']
                  };

                  var userList = new List('searchDiv', options);

            }


        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });

}

function submitNewForum() {

    var forumTitle = document.getElementById('forumTitle').value;
    var forumBody = document.getElementById('forumBody').value;
    var tags = [];
    var x = document.getElementsByClassName("tagit-label");
    for (var i = 0; i < x.length; i++) {
        tags.push(x[i].innerText);
    }

    $.ajax({
        url: "NewForumServlet",
        type: 'POST',
        dataType: "JSON",
        data: {
            forumTitle: forumTitle,
            forumBody: forumBody,
            tags: tags
        },
        success: function (data) {
            if (data === true) {
                getTags();
                Viewforums();
                HotTopic();
                $("#forumTitle").val('');
                $("#forumBody").val('');
                $('#tagInput').tagit('removeAll');
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(textStatus);
        }
    });

}

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tags = [];


function getTags() {
    $.ajax({
        url: "TagServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            tags.length = 0;
            $.each(data, function (key, value) {
                tags.push(value.tag);
            });

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(exception);
        }
    });
}

// tag it 
$(function () {
    $('#tagInput').tagit({
        availableTags: tags
    });
});



var ctx = "${pageContext.request.contextPath}";
// if title is clicked - redirect get TitleName and redirect to specific forum page

$(document).on('click', '.titleName', function () {
    var forumId = $(this).closest("tr").find("span.forumId").text();
//    console.log(forumId);
    window.location.replace("ForumSpecificServlet?forumId=" + forumId);

});