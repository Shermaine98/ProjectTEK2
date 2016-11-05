/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    HotTopic();
    ViewComments();


    $(document).on("click", '.nDefault', function () {
        var decision = "comment";
        var commentID = $(this).closest("tr").find("span.commentID").text();

        var isLike = "true";
        $.ajax({
            context: this,
            url: "FavoriteServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                decision: decision,
                commentID: commentID,
                isLike: isLike
            },
            success: function (data) {
                if (data === true) {
                    HotTopic();
                    ViewComments();
                }
            }, error: function (XMLHttpRequest, textStatus, exception) {
                console.log(exception);
            }
        });
    });

//remove like
    $(document).on("click", '.nPrimary', function () {
        var decision = "comment";
        var commentID = $(this).closest("tr").find("span.commentID").text();

        var isLike = "false";
        $.ajax({
            url: "FavoriteServlet",
            type: 'POST',
            dataType: "JSON",
            data: {
                commentID: commentID,
                decision: decision,
                isLike: isLike
            },
            success: function (data) {
                if (data === true) {
                    HotTopic();
                    ViewComments();
                }

            }, error: function (XMLHttpRequest, textStatus, exception) {
                console.log(exception);
            }
        });
    });

    //FORUM

    $(document).on("click", '.nDefaultS', function () {
        var decision = "forum";
        var forumId = document.getElementById("forumID").value;
        var forumTitle = document.getElementById("forumTitle").value;
        var createdby = document.getElementById("commentedById").value;
        var isLike = "true";
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
                    location.reload();
                }

            }, error: function (XMLHttpRequest, textStatus, exception) {
                console.log(exception);
            }
        });
    });

//remove like
    $(document).on("click", '.nPrimaryS', function () {
        var decision = "forum";
        var forumId = document.getElementById("forumID").value;

        var isLike = "false";
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
                    location.reload();
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

                $(tbodytr).append('<td><span style="display: none;" class="forumId">' + data[i].forumID + '</span><span title="title">\n\
                    <a class="titleName">' + data[i].forumTitle + '</a></span><br/>\n\
                    <p style="font-size: 13px; color: #a3a3a3;">Created on ' + data[i].dateCreated + ' by ' + data[i].createdByName + '</p></td>');


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
            table.setAttribute("class", "table");
            table.setAttribute("style", "margin-top: 0");
            element.appendChild(table);
            var tbody = document.createElement("tbody");
            table.appendChild(tbody);
            
            var repliesNum = ' '+data.length + ' Replies';
            $('#repliesNumber').text(repliesNum);

            for (var i = 0; i < data.length; i++) {

                var tbodytrtop = document.createElement("tr");
                tbodytrtop.setAttribute("style",  "background: #E0EAF1; color: #858C93; height: 10px; font-size: 12px;  border-style: solid;border-width: 15px;");
                tbody.appendChild(tbodytrtop);
                var tbodytr = document.createElement("tr");
                tbody.appendChild(tbodytr);
                var td1 = document.createElement("td");
                td1.setAttribute("width", "25%");
                tbodytr.appendChild(td1);
                var td2 = document.createElement("td");
                tbodytr.appendChild(td2);

                var firstColumn = '<div style="width:100%">\n\
                                    <b style="color:#6A738E">'+data[i].commentedByName+'</b></td>\n\
                                    <div style="color:#8E737C; font-size:65%">Replied on '+data[i].dateOfComment+'</div>\n\
                                    </div>';
                $(td1).append(firstColumn);

                if (data[i].isLike === false) {

                    $(td2).append(data[i].comment + '<br><br><span style="display: none;" class="commentID">' + data[i].commentID + '</span><h5 style="font-size: 13px; text-align:right;"> \n\
                                                <button class="btn btn-flat btn-default btn-xs nDefault">\n\
                                                    <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 1%;"></i> \n\
                                                    ' + data[i].commentCount + ' </button></h5>');

                } else {
                    $(td2).append(data[i].comment + '<br><br><span style="display: none;" class="commentID">' + data[i].commentID + '</span><h5 style="font-size: 13px; text-align:right;"> \n\
                                                <button class="btn btn-flat btn-primary btn-xs nPrimary">\n\
                                                    <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 1%;"></i> \n\
                                                    ' + data[i].commentCount + ' </button></h5>');
                }
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
                $("#comment").val('');

                $("#notificationHeader").text("Success!");
                $("#modal_Header").css({background: "#00a65a"});
                $("#notificationHeader").css({color: "#FFFFFF"});
                $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'>Your comment has been saved!</p>");
                $("#notificationModal").modal("show");
                // Set a timeout to hide the element again
                setTimeout(function () {
                    $("#modal_Header").css({background: ""});
                    $("#notificationModal").modal("hide");
                    $("#notificationHeader").text("");
                    $("#notificationBodyModal").empty();
                }, 4000);
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(textStatus);
        }
    });
}

var ctx = "${pageContext.request.contextPath}";
// if title is clicked - redirect get TitleName and redirect to specific forum page

$(document).on('click', '.titleName', function () {
    var forumId = $(this).closest("tr").find("span.forumId").text();
//    console.log(forumId);
    window.location.replace("ForumSpecificServlet?forumId=" + forumId);

});