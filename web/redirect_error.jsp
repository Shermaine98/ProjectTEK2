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
    </head>

    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="error-page">
                        <div class="error-content" style="margin: 20% auto 0 auto;">
                            <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>

                            <p>
                                We will work on fixing that right away.
                                Meanwhile, you may <a href="index.jsp">return to dashboard</a>.
                            </p>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
