<%--
    Document   : home_others
    Created on : 07 15, 16, 10:17:46 PM
    Author     : Geraldine
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project TEK | Home </title>
        <link href="cssImported/jquery.tagit.css" rel="stylesheet" type="text/css"/>
        <link href="cssImported/tagit.ui-zendesk.css" rel="stylesheet" type="text/css"/>
        <script src="jsForums/tag-it.js" type="text/javascript"></script>
        <script src="jsForums/forums.js" type="text/javascript"></script>
        <script src="jsImported/list.min.js" type="text/javascript"></script>

        <style>
            tr{
                height: 50px;
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
                        <!--MODAL-->

                        <div class="col-md-12">
                            <div id="new_topic" class="col-md-6">
                                <div class="box box-solid" style="margin-top: 2%;">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Create New Topic</h3>
                                    </div>
                                    <div class="box-body">
                                        <input id="forumTitle" placeholder="Topic Title" class="form-control" style="width:100%; resize: none; margin-bottom: 2%;" />
                                        <textarea id="forumBody" placeholder="Body" class="form-control" style="width:100%; resize: none; height: 150px; margin-bottom: 2%;"></textarea>
                                        <input class="form-control" style="width:100%; resize: none; margin-bottom: 2%;" placeholder="Tags" id="tagInput" />
                                        <button type="submit" onclick="submitNewForum()" class="btn btn-success pull-right" >Save</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <!--<div class="box">-->
                                <div class="box-body">
                                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                        <ol class="carousel-indicators">
                                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                            <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
                                            <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
                                        </ol>
                                        <div class="carousel-inner">
                                            <div class="item active">
                                                <img src="img/Caloocan1.jpg" alt="First slide" style="width:100%;height: 350px;object-fit: cover">

                                            </div>
                                            <div class="item">
                                                <img src="img/Caloocan2.jpg" alt="Second slide" style="width:100%;height: 350px;object-fit: cover">

                                                <!--                                                <div class="carousel-caption">
                                                                                                    Third Slide
                                                                                                </div>-->
                                            </div>
                                            <div class="item">
                                                <img src="img/Caloocan3.jpg" alt="Third slide" style="width:100%;height: 350px;object-fit: cover">

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
                                    <!-- /.box-body -->
                                    <!--</div>-->
                                    <!-- /.box -->
                                </div>
                                <!--</div>-->
                            </div>
                        </div>

                        <!--END MODAL-->
                        <div class="col-md-12" style="margin-top: 2%;">
                            <div class="col-md-6">
                                <div class="box box-solid">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Hot Topics</h3>
                                    </div>
                                    <div class="box-body">
                                        <div id="hotTopicDiv">

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box -->

                            <!--
                                forumBody
                            -->
                            <div class="col-md-6">
                                <div class="box box-solid">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">All Topics</h3>
                                    </div>
                                    <div class="box-body" id="searchDiv" >
                                    <input class="search" placeholder="Search by tags or by topic name" />
                                        <div id="forumDiv">
                                        </div>
                                    </div>
                                </div>
                                <!--
                                  /.forumBody
                                -->
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <script>

           </script>
    </body>

</html>
