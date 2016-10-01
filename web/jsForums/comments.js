/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    ViewComments();
});


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
            
            
            for (var i = 0; i < data.length; i++) {
                $("#commentsBox").append('<p>' + data[i].commentID + ' </p>');
                $("#commentsBox").append('<p>' + data[i].comment +  ' </p');
                $("#commentsBox").append('<p>' + data[i].commentedByName + ' </p>');
                $("#commentsBox").append('<p>' + data[i].dateCreated +  ' </p>');
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
                ViewComments();
            }

        }, error: function (XMLHttpRequest, textStatus, exception) {
            console.log(textStatus);
        }
    });
}