<%--
    Document   : register
    Created on : Jun 11, 2016, 1:12:39 PM
    Author     : Dinding
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

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
            .form_element{
                padding: 0 20px;
                vertical-align: middle;
                background: #fff;
                border: 3px solid #fff;height:50px;
                font-family: 'Roboto', sans-serif;
                font-size: 16px;
                font-weight: 300;
                line-height: 50px;
                line-height: 50px;
                color: #888;
            }
            .form-title {
                font-weight: bold;
                font-size: 130%;
                /*COLOR: #1a1a1a*/
            }
            p{
                COLOR: #f2f2f2;
                margin-bottom: -20px;
            }
            a{
                color: #fff
            }
            a:hover {
                color: #9999ff
            }
            .error
            {
                border:1px solid red;
            }
        </style>
    </head>
    <body style="background-color: #4d4d4d">
        <!--<img src='index_template/background.jpeg' style='position:fixed;top:0px;left:0px;width:100%;z-index:-1; -webkit-filter: blur(1px);'>-->
        <div class="container">
            <div class="col-sm-5">

                <div class="form-box">
                    <div class="form-top">
                        <div class="post-thumb">
                            <img src="index_template/Ph_seal_ncr_caloocan.png" alt="Caloocan" width="130"  /></div>
                        <div class="post-content">
                            <h3 class="post-title">City Planning Department</h3></div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="Register" method="post">
                            <h4 class="form-title">Personal Information</h4>
                            <div class="form-group" style="float:left; width: 49%; margin-right: 2%">
                                <label class="sr-only">First Name</label>
                                <input type="text" required name="firstName" placeholder="First Name" class="form-control">
                            </div>
                            <div class="form-group" style="float:left; width: 49%; ">
                                <label class="sr-only">Last Name</label>
                                <input type="text" required name="lastName" placeholder="Last Name" class="form-control">
                            </div>
                            <div class="form-group">
                                <select required name="gender" class="form-control form_element" style="">
                                    <option value="" disabled selected>Select Gender</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <p>Input Birthdate (DD/MM/YY)</p>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">Birthdate</label>
                                <input required class="form-control form_element" id="Birthdate"  type="date" name="birthdate" onfocus="(this.type = 'date')"  onblur="(this.type = 'text')" />
                            </div>
                            <div class="form-group">
                                <p>Are you an employee or an external researcher?</p>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">Are you an employee or an external researcher?</label>
                                <input type="radio" name="division" required value="employee" onclick="hideReason()"> Employee<br>
                                <input type="radio" name="division" value="others" onclick="showReason()"> Researcher<br>
                            </div>
                            <div class="form-group" id="reason1" style="display:none; font-size: small; margin: 0; ">
                                Please input the reason below for wanting access to the published reports of Caloocan City Planning Department.*
                            </div>
                            <div class="form-group" id="reason" style="display:none;">
                                <textarea class="form-group" rows="4" style="width: 100%; resize: none;" name="reason" ></textarea>
                            </div>

                            <h4 class="form-title">Account Details</h4>
                            <div class="form-group">
                                <label class="sr-only">Email</label>
                                <input required type="text" name="email" onkeyup="registerValidation()" placeholder="Email Address" class="form-username form-control" id="email">
                                <label style="display:none; color: white; font-size: 14px; font-style: italic; font-weight: normal"
                                       id="emailValidation">*Email has already been taken</label>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input required type="text" name="username" onkeyup="registerValidation()" placeholder="Username" class="form-username form-control" id="userName">
                                <label style="display:none; color: white; font-size: 14px; font-style: italic; font-weight: normal"
                                       id="userNameValidation">*Username has already been taken</label>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input required type="password" name="password" placeholder="Password" class="form-password form-control" id="form-password">
                            </div>
                            <button type="submit" id="submit"  class="btn" style="background-color:#ee913a">Submit</button>
                            <a href="index.jsp">Back</a>
                        </form>
                    </div>
                </div>
            </div>
        </div><br/><br/>

        <!-- Javascript -->
        <script src="index_template/assets/js/jquery-1.11.1.min.js"></script>
        <script src="index_template/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="index_template/assets/js/jquery.backstretch.min.js"></script>
        <script src="index_template/assets/js/scripts.js"></script>
        <script src="index_template/DateTime.js" type="text/javascript"></script>
        <script>

                                    function showReason() {
                                        $("#reason").show();
                                        $("#reason1").show();
                                    }

                                    function hideReason() {
                                        $("#reason").hide();
                                        $("#reason1").hide();
                                    }

                                    function registerValidation() {
                                        var email = document.getElementById('email').value;
                                        var userName = document.getElementById('userName').value;
                                        $.ajax({
                                            url: "RegisterValidation",
                                            type: 'POST',
                                            dataType: "JSON",
                                            data: {
                                                email: email,
                                                userName: userName
                                            },
                                            success: function (data) {
                                                $("#email").css("border", "none");
                                                $("#userName").css("border", "none");
                                                document.getElementById('emailValidation').style.display = "none";
                                                document.getElementById('userNameValidation').style.display = "none";
                                                if (data === "trueEmail") {
                                                    $("#email").css("border", "3px solid red");
                                                    document.getElementById('emailValidation').style.display = "block";
                                                    $("#submit").prop("disabled", true);
                                                } else if (data === "trueUserName") {
                                                    $("#userName").css("border", "3px solid red");
                                                    document.getElementById('userNameValidation').style.display = "block";
                                                    $("#submit").prop("disabled", true);
                                                } else if (data === "false") {
                                                    $("#submit").prop("disabled", false);
                                                }
                                            }, error: function (XMLHttpRequest, textStatus, exception) {
                                                console.log(exception);
                                            }
                                        });

                                    }
        </script>
    </body>

</html>
