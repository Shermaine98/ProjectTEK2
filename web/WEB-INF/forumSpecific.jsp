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
        <title>Project TEK | Home </title>

        <style>
            tr{
                height: 50px;
            }

            div{
                display:inline-block;
                position:relative;
            }

            button{
                position:absolute;
                bottom:10px;
                right:10px;
            }
        </style>
    </head>
    <body>
        <div class ="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-7">
                            <div class="box box-solid">
                                <div class="box-body">
                                    <table class="table table-condensed">
                                        <thead>
                                            <tr>
                                                <td colspan="2" style="font-size: medium; font-weight: 600">Top Topics</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="vertical-align:middle; font-size: medium;"><a href="">Caloocan City is the greatest city...</a></td>
                                            </tr>
                                            <tr>
                                                <td style="vertical-align:middle; font-size: medium;"><a href="">On the development of the city plan</a></td>
                                            </tr>
                                            <tr>
                                                <td style="vertical-align:middle; font-size: medium;"><a href="">Lalalalala</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->

                            <!--
                                forumBody
                            -->
                            <div id="specificForum">
                                <% Forums forum = (Forums) request.getAttribute("forum");%>
                                <input type = "hidden" value="<%= forum.getForumID()%>" />
                                <input type="text"  value="<%= forum.getForumTitle()%>" />
                                <textarea> <%= forum.getBody()%> </textarea>
                                <input type="text"  value=" <%= forum.getCreatedByName()%>" />
                                <input type="text"  value=" <%= forum.getCommentsCount()%>" />
                                <input type="text"  value=" <%= forum.getReportCount()%>" />
                                <input type="text" value=" <%= forum.getFavoritesCount()%> "/>
                                <input type="text" value="<%= forum.getDateCreated()%>"/>
                                <table>
                                    <% for (int i = 0; i < forum.getTags().size(); i++) {%>
                                    <td> <%=forum.getTags()%> </td>
                                    <%}%>
                                </table>
                                <table id="tableComments">
                                    <!--Comments here-->


                                </table>
                            </div>
                            <!--
                              /.forumBody
                            -->
                            <div>
                                <textarea name="" id="txt" cols="30" rows="10"></textarea>
                                <button>Submit</button>
                            </div>
                            <!-- /.box -->
                        </div>

                        <div class="col-md-5">
                            <div class="box box-solid">
                                <!--                                <div class="box-header with-border">
                                                                    <h3 class="box-title">Carousel</h3>
                                                                </div>-->
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                        <ol class="carousel-indicators">
                                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                            <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
                                            <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
                                        </ol>
                                        <div class="carousel-inner">
                                            <div class="item active">
                                                <img src="img/Caloocan1.jpg" alt="First slide" style="width:100%;height: 400px;object-fit: cover">

                                            </div>
                                            <div class="item">
                                                <img src="img/Caloocan2.jpg" alt="Second slide" style="width:100%;height: 400px;object-fit: cover">

                                                <!--                                                <div class="carousel-caption">
                                                                                                    Third Slide
                                                                                                </div>-->
                                            </div>
                                            <div class="item">
                                                <img src="img/Caloocan3.jpg" alt="Third slide" style="width:100%;height: 400px;object-fit: cover">

                                                <!--                                                <div class="carousel-caption">
                                                                                                    Third Slide
                                                                                                </div>-->
                                            </div>
                                        </div>
                                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                            <span class="fa fa-angle-left"></span>
                                        </a>
                                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                            <span class="fa fa-angle-right"></span>
                                        </a>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>

                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
