<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html >
    <head>
        <title>Caloocan City Hall: City Planning Department</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- CSS -->
        <link rel="stylesheet" href="cssImported/googleapisfonts.css" rel="stylesheet" type="text/css"/>
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
            .container {
                display: flex;
                justify-content: center;
            }
            .post-thumb {
                margin-top: 20px;
                margin-right: 30px;
                float: left
            }
            .post-thumb img {
                display: block
            }
            .post-content {
                margin-left: 160px
            }
            .post-content p{
                color: #000;
            }
            .post-title {
                margin-top: 15%;
                font-weight: bold;
                font-size: 250%;
            }
            a{
                color: #fff
            }
            a:hover {
                color: #9999ff
            }
        </style>

    </head>
    <body style="background-color: #4d4d4d">

        <% if (null != request.getAttribute("loggedOut")) { %>
        <div id="notify" style="background: #444; width: 100%; height: 30px; float: top;">You have successfully logged out!</div>
        <% }%>

        <div class="container">
            <div class="col-sm-5">
                <div class="form-box">
                    <div class="form-top">
                        <div class="post-thumb">
                            <img src="index_template/Ph_seal_ncr_caloocan.png" width="130" /></div>
                        <div class="post-content">
                            <h3 class="post-title">City Planning Department</h3></div>
                    </div>

                    <div class="form-bottom">
                        <form role="form" action="Login" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" placeholder="Username" class="form-username form-control" id="form-username" required />
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="Password" class="form-password form-control" id="form-password" required />
                            </div>
                            <button type="submit" class="btn" style="background-color:#ee913a">Log in</button>
                        </form><br/>
                        <center><a href="register.jsp">No account yet? Register now!</a></center>
                    </div>
                </div>
            </div>
        </div>


        <script>
            setTimeout(function () {
                $('#notify').fadeOut('fast');
            }, 4000); // <-- time in milliseconds
        </script>
        <!-- Javascript -->
        <script src="index_template/assets/js/jquery-1.11.1.min.js"></script>
        <script src="index_template/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="index_template/assets/js/jquery.backstretch.min.js"></script>
        <script src="index_template/assets/js/scripts.js"></script>

    </body>
</html>
