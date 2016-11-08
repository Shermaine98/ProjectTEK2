/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tags = [];
$(document).ready(function () {
    getTags();
    Viewforums();
    HotTopic();

    //add like
    // tag it 
    $(function () {
        $('#tagInput').tagit({
            availableTags: tags
        });
    });



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

            if (data.length > 0) {

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

            } else {
                $(element).append("<p>There are currently no hot topics.</p>");
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
            table.setAttribute("class", "table dataTable");
            table.setAttribute("style", "margin: 0;");
            table.setAttribute('id', 'dataTable');
            table.setAttribute('cellspacing', '0');
            table.setAttribute('cellpadding', '0');
            para.appendChild(table);

            var thead = document.createElement("thead");
            thead.setAttribute("style", "display:none;");
            table.appendChild(thead);

            $(thead).append('<tr><th></th><th></th></tr>');

            var tbody = document.createElement("tbody");
            tbody.setAttribute("class", "list");
            table.appendChild(tbody);

            for (var i = 0; data.length > i; i++) {

                var tbodytr = document.createElement("tr");
                tbody.appendChild(tbodytr);
                if (data[i].isLike === false) {
                    $(tbodytr).append('<td style="width:23%"><button style ="margin-right:5%" class="btn btn-flat btn-default btn-xs nDefault" id="btnFavorite"> <i class="glyphicon glyphicon-thumbs-up""></i>&nbsp;' + data[i].favoritesCount + '</button><button class="btn btn-flat btn-default btn-xs"><i class="glyphicon glyphicon-comment" ></i>&nbsp;' + data[i].commentsCount + '</button></td>');
                } else {
                    $(tbodytr).append('<td style="width:23%"><button style ="margin-right:5%" class="btn btn-flat btn-primary btn-xs nPrimary" id="btnFavorite"> <i class="glyphicon glyphicon-thumbs-up""></i>&nbsp;' + data[i].favoritesCount + '</button><button class="btn btn-flat btn-default btn-xs"><i class="glyphicon glyphicon-comment" ></i>&nbsp;' + data[i].commentsCount + '</button></td>');
                }

                $(tbodytr).append('<td class="forumTitleSearch"><span style="display: none;" class="createdBy">' + data[i].createdBy + '</span><span style="display: none;" class="forumId">' + data[i].forumID + '</span> \n\
                    <a class="titleName" style="font-size: 15px;"><span class="forumTitle">' + data[i].forumTitle + '</span></a>\n\
                    <p style="color: #777; font-size: 10px;">Created by ' + data[i].createdByName + ' on ' + data[i].dateCreated + '</p></td>');

                var tbodytr2 = document.createElement("tr");
                //tbodytr2.setAttribute('border-style','1');
                tbody.appendChild(tbodytr2);
                var tr2td = document.createElement("td");
                tr2td.setAttribute("colspan", "3");
                $(tr2td).append('Tags:&nbsp;');
                tbodytr2.appendChild(tr2td);
                for (var j = 1; data[i].tags.length > j; j++) {
                    $(tr2td).append('<p style="margin-right: 2%;color: #4E85C0; border-style:none; background-color: #E1ECF4" class="tagSearch btn btn-flat btn-default btn-xs">' + data[i].tags[j].tag + '</p>');
                }
                var tr2td = document.createElement("td");
                tbodytr2.appendChild(tr2td);
                var options = {
                    valueNames: ['forumTitleSearch', 'tagSearch']
                };

                var userList = new List('searchDiv', options);


            }
            $("#dataTable").DataTable({
                "paging": true,
                "ordering": false,
                "lengthChange": false,
                "searching": false,
                "info": false, "language": {
                    "emptyTable": "There are no topics posted yet."
                }
            });


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


                $("#notificationHeader").text("Success!");
                $("#modalHeader").css({background: "#00a65a"});
                $("#notificationHeader").css({color: "#FFFFFF"});
                $("#notificationBodyModal").append("<p style='padding: 3%; text-align:center;'>You have successfully created a new topic!</p>");
                $("#notificationModal").modal("show");
                // Set a timeout to hide the element again
                setTimeout(function () {
                    $("#modalHeader").css({background: ""});
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

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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


var ctx = "${pageContext.request.contextPath}";
// if title is clicked - redirect get TitleName and redirect to specific forum page

$(document).on('click', '.titleName', function () {
    var forumId = $(this).closest("tr").find("span.forumId").text();
//    console.log(forumId);
    window.location.replace("ForumSpecificServlet?forumId=" + forumId);

});