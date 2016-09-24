<%@page import="model.accounts.User"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<html>
    <head>
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="AdminLTE/bootstrap/css/bootstrap.min.css">
        <!--Font Awesome -->
        <link href="AdminLTE/fonts/font-awesome.css" rel="stylesheet" type="text/css"/>
        <!--Ionicons -->
        <link href="AdminLTE/fonts/ionicons.min.css" rel="stylesheet" type="text/css"/>
        <!-- Theme style -->
        <link rel="stylesheet" href="AdminLTE/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link href="AdminLTE/dist/css/skins/skin-yellow-light.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="AdminLTE/dist/css/skins/skin-yellow-light.min.css">
        <!-- Date Picker -->
        <link rel="stylesheet" href="AdminLTE/plugins/datepicker/datepicker3.css">
        <!-- Daterange picker -->
        <link rel="stylesheet" href="AdminLTE/plugins/daterangepicker/daterangepicker-bs3.css">
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
        <!-- Roboto Font -->
        <link href="AdminLTE/fonts/roboto.css" rel="stylesheet" type="text/css"/>

        <link rel="icon" href="index_template/Ph_seal_ncr_caloocan.png">
        <!--DataTables-->
        <link rel="stylesheet" href="AdminLTE/plugins/datatables/dataTables.bootstrap.css">

        <style>
            .circles-small{
                font-size: 60%;
            }
            .title, h1, h2, h3{
                font-family: "Roboto", sans-serif;
            }
        </style>
    </head>
    <body class="hold-transition skin-yellow-light fixed">
        <div class="wrapper">
            <header class="main-header hidden-print">
                <!-- Logo -->
                <div class="logo">
                    <!-- logo for regular state and mobile devices -->
                    <img src="index_template/test.png" style="height: 48px"/>
                </div>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Notifications: style can be found in dropdown.less -->
<!--                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-danger"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 0 notifications</li>
                                    <li>
                                         inner menu: contains the actual data
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all</a></li>
                                </ul>
                            </li>-->
                            <!-- User Account: style can be found in dropdown.less -->
                            <% User chck = (User) session.getAttribute("user");%>

                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <img src="AdminLTE/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <b><%= chck.getFirstName()%> <%= chck.getLastName()%></b>
                                </a>

                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>
                                            <%= chck.getFirstName()%> <%= chck.getLastName()%><br/>
                                            <small><%= chck.getPosition()%></small>
                                        </p>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->
                            <li>
                                <a href="#"  class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-cog"></i></a>
                                <ul class="dropdown-menu" style="padding: 5px;">
                                    <li><a data-toggle="modal" data-target="#profileModal">Change Password</a></li>
                                    <li><a href="Logout">Sign out</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar hidden-print">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <!--<li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=home_guest">
                                <i class="fa fa-dashboard"></i><span>Home</span>
                            </a>
                        </li>-->
                        <li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=reportsLibrary">
                                <i class="fa fa-book"></i>
                                <span>Internal Reports Library</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=PublishedReports">
                                <i class="fa fa-file-archive-o"></i>
                                <span>Published Reports</span>
                            </a>
                        </li>
                </section>
                <!-- /.sidebar -->
            </aside>
        </div>
        <!-- jQuery 2.2.0 -->
        <script src="AdminLTE/plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script src="AdminLTE/plugins/jQueryUI/jquery-ui.js" type="text/javascript"></script>
        <script>
            $.widget.bridge('uibutton', $.ui.button);
            $.widget.bridge('uitooltip', $.ui.tooltip);
        </script>
        <!-- Bootstrap 3.3.6 -->
        <script src="AdminLTE/bootstrap/js/bootstrap.min.js"></script>
        <!-- daterangepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>
        <!-- datepicker -->
        <script src="AdminLTE/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <!-- Slimscroll -->
        <script src="AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="AdminLTE/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="AdminLTE/dist/js/app.min.js"></script>
        <!--DataTables-->
        <script src="AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="AdminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
    </body>
</html>
