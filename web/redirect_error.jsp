<%--
    Document   : redirect_error
    Created on : Jun 23, 2016, 10:14:33 PM
    Author     : Shermaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>There seems to be a problem.</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>

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

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="index_template/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="index_template/assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="index_template/assets/css/form-elements.css">
        <link rel="stylesheet" href="index_template/assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="icon" href="index_template/Ph_seal_ncr_caloocan.png">

        <style>
            p, h3{
                color: #333333;
                font-weight: 500;
            }
            a{
                color: #0099cc;
                font-weight: 500;
            }
            body{
                background-color: #e2e2e2;
            }
        </style>

    </head>

    <body>
        <div class="error-page">
            <div class="error-content" style="margin: 50% auto 0 auto;">
                <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>

                <p>
                    We will work on fixing that right away.
                    Meanwhile, you may <a href="Logout">log in</a> again.
                </p>
            </div>
        </div>
        <!-- Javascript -->
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
        <script src="AdminLTE/plugins/daterangepicker/moment.min.js"></script>
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
