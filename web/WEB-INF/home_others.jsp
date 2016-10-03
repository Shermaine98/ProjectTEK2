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
        <script src="jsForums/forums.js" type="text/javascript"></script>

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

                        <div >
                            <textarea id="forumTitle"  style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"> "title" </textarea>
                            <textarea id="forumBody" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;">"body" </textarea>
                             <textarea  style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;">"tags" </textarea>
                        </div>
                        <div>
                            <button type="submit" onclick="submitNewForum()" class="btn btn-success" >Save</button>
                        </div>


                        <!--END MODAL-->                       
                        <div class="col-md-7">
                            <h3>Hot Topics</h3>
                            <div id="hotTopicDiv">

                            </div>
                            <!-- /.box -->

                            <!--
                                forumBody
                            -->
                            
                            <h3 style="margin-top: 5%">All Topics</h3>
                            <div id="forumDiv">


                            </div>
                            <!--
                              /.forumBody
                            -->

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
