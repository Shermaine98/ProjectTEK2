<%--
    Document   : home_others
    Created on : 07 15, 16, 10:17:46 PM
    Author     : Geraldine
--%>


<%@page import="model.forums.Forums"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project TEK |  </title>
        <script src="jsForums/comments.js" type="text/javascript"></script>
        <style>
            tr{
                height: 50px;
            }

            /*            div{
                            display:inline-block;
                            position:relative;
                        }*/

            /*            button{
                            position:absolute;
                            bottom:10px;
                            right:10px;
                        }*/
        </style>
    </head>
    <body>
        <div class ="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-11" style="margin-left: 4%; margin-right: 4%;">
                            <div class="box box-solid">

                                <div id="hotTopicDiv">
                                    <!--HOT TOPIC CONTENTS-->
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->

                            <!--
                                forumBody
                            -->
                            <div id="specificForum" class="box box-solid" style="padding: 3%;">
                                <% Forums forum = (Forums) request.getAttribute("forum");%>

                                <h3 style="margin-bottom: 2px;"><%=forum.getForumTitle() %></h3>
                                <b style="margin-right: 1%; font-weight: normal">Tags:</b>
                                <% for (int i = 0; i < forum.getTags().size(); i++) {%>
                                <button style="margin-right: 1%;" class="btn btn-flat btn-default btn-sm"><%=forum.getTags().get(i).getTag()%></button>
                                <%}%>


                                <table class="table table-bordered" style="margin-top: 2%; margin-bottom: 0;">
                                    <tr>
                                        <td width="25%">
                                            <%=forum.getCreatedByName()%></td>
                                        <td><%= forum.getBody()%><br><br>
                                            <h5 style="font-size: 13px; text-align:right;">
                                                <button class="btn btn-flat btn-primary btn-xs" style="margin-right: 2%;">
                                                    <i class="glyphicon glyphicon-thumbs-up" style="margin-right: 1%;"></i> 
                                                    <%=forum.getFavoritesCount()%> </button>
                                                Posted on <%=forum.getDateCreated()%></h5></td>
                                    </tr>
                                </table>

                                <input id="forumID" type = "hidden" value="<%=forum.getForumID()%>" />
                                <input id="forumTitle" type="hidden"  value="<%=forum.getForumTitle()%>" />
                                <textarea style="display:none;"> <%= forum.getBody()%> </textarea>
                                <input id="commentedById" type="hidden"  value="<%=forum.getCreatedBy()%>" />
                                <input  type="hidden"  value="<%=forum.getCreatedByName()%>" />
                                <input type="hidden"  value="<%=forum.getCommentsCount()%>" />
                                <input type="hidden"  value="<%=forum.getReportCount()%>" />
                                <input type="hidden" value="<%=forum.getFavoritesCount()%> "/>
                                <input type="hidden" value="<%=forum.getDateCreated()%>"/>
                                <!--<table>
                            </table>-->
                                <div id="commentsBox">

                                </div>


                                <textarea class="form-control" id="comment" style="width: 100%; margin-bottom: 2%; resize: none;" placeholder="Add New Comment..."></textarea>
                                <button onclick="submitNewComment()">Submit</button>

                            </div>
                            <!--
                              /.forumBody
                            -->
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
