<%@page import="model.accounts.User"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@include file="ProfileModal.jsp" %>

<html>
    <head>
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="AdminLTE/bootstrap/css/bootstrap.min.css" />
        <!--Font Awesome -->
        <link href="AdminLTE/fonts/font-awesome.css" rel="stylesheet" type="text/css"/>
        <!--Ionicons -->
        <link href="AdminLTE/fonts/ionicons.min.css" rel="stylesheet" type="text/css"/>
        <!-- Theme style -->
        <link rel="stylesheet" href="AdminLTE/dist/css/AdminLTE.min.css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link href="AdminLTE/dist/css/skins/skin-yellow-light.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="AdminLTE/dist/css/skins/skin-yellow-light.min.css" />
        <!-- Date Picker -->
        <link rel="stylesheet" href="AdminLTE/plugins/datepicker/datepicker3.css" />
        <!-- Daterange picker -->
        <link rel="stylesheet" href="AdminLTE/plugins/daterangepicker/daterangepicker-bs3.css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" />
        <!-- Roboto Font -->
        <link href="AdminLTE/fonts/roboto.css" rel="stylesheet" type="text/css" />

        <link rel="icon" href="index_template/Ph_seal_ncr_caloocan.png" />
        <!--DataTables-->
        <link rel="stylesheet" href="AdminLTE/plugins/datatables/dataTables.bootstrap.css" />


        <style>
            .circles-small{
                font-size: 60%;
            }
            .title, h1, h2, h3{
                font-family: "Roboto", sans-serif;
            }
            .DT{
                margin: 0 auto;
                width: 95%;
                background: #fff;
            }
            li a:hover {
                /*background: #ebebeb;*/
                color: #557696;
                font-weight: normal;
                text-decoration: none;
                cursor: pointer;
                cursor: hand;
            }
            a{
                color: #000;
            }
            .p{
                color: #888888;
                font-size: small;
            }
            hr{
                padding: 0;
                margin: 0;
            }
            .padding2{
                padding: 2%;
            }
            .notification_drop{
                overflow:hidden;
                overflow-y:scroll;
                max-height: 300px;
            }
        </style>
    </head>
    <body class="hold-transition skin-yellow-light fixed">
        <div class="wrapper">
            <header class="main-header hidden-print">
                <!-- Logo -->
                <div class="logo">
                    <!--logo for regular state and mobile devices-->
                    <img src="index_template/test.png" style="height: 48px"/>
                </div>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">

                            <li class="dropdown notifications-menu">

                                <!--NOTIFICATIONS HIDDEN-->
                                <input type="hidden" id="yearImport" />

                                <a href="#" class="dropdown-toggle" id="dropdownClicked" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-danger" id="value"></span></a>
                                <ul class="dropdown-menu notification_drop">
                                    <li class="header">Notifications</li>
                                    <li id="myDropdown">
                                        <!--CONTENT HERE-->
                                    </li>
                                </ul>
                            </li>
                            <!-- User Account: style can be found in dropdown.less -->
                            <% User chck = (User) session.getAttribute("user");%>

                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <% if (chck.getGender().equals("Male")) { %>
                                    <img src="AdminLTE/dist/img/user1-128x128.jpg" class="user-image" alt="User Image">
                                    <% } else if (chck.getGender().equals("Female")) { %>
                                    <img src="AdminLTE/dist/img/user7-128x128.jpg" class="user-image" alt="User Image">
                                    <% }%>
                                    <b><%= chck.getFirstName()%> <%= chck.getLastName()%></b>
                                </a>

                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <% if (chck.getGender().equals("Male")) { %>
                                        <img src="AdminLTE/dist/img/user1-128x128.jpg" class="img-circle" alt="User Image">
                                        <% } else if (chck.getGender().equals("Female")) { %>
                                        <img src="AdminLTE/dist/img/user7-128x128.jpg" class="img-circle" alt="User Image">
                                        <% }%>
                                        <p style="margin:0 auto; margin-top: 1%;">
                                            <%= chck.getFirstName()%> <%= chck.getLastName()%><br/>
                                            <small>
                                                <%if (chck.getPosition().equalsIgnoreCase("Project Development Officer III")) {%>
                                                <span class="fa fa-mortar-board"></span> Education
                                                <%} else if (chck.getPosition().equalsIgnoreCase("Project Development Officer I")) {%>
                                                <span class="fa fa-users"></span> Demographics
                                                <%} else if (chck.getPosition().equalsIgnoreCase("Project Development Officer IV")) {%>
                                                <span class="fa fa-hospital-o"></span> Health
                                                <%}%></small>
                                            <small>
                                                <%= chck.getPosition()%>
                                                <input type="hidden" name="position" value="<%=chck.getPosition()%>"  id="position" />
                                            </small>
                                            <!--NOTIFICATIONS HIDDEN-->
                                        </p>

                                    </li>
                                </ul>
                            </li>

                            <!-- Control Sidebar Toggle Button -->
                            <li>
                                <a href="#"  class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-cog"></i></a>
                                <ul class="dropdown-menu" style="padding: 5px;">
                                    <li><a data-toggle="modal" data-target="#profileModal">Change Password</a></li>
                                    <li><a href="Logout" onClick="clearStorage()">Sign out</a></li>
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
                        <!--                        <li>
                                                    <a href="${pageContext.request.contextPath}/ServletAccess?redirect=homePDO">
                                                        <i class="fa fa-dashboard"></i><span>Dashboard</span>
                                                    </a>
                                                </li>-->
                        <li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=homePDO">
                                <i class="fa fa-home"></i><span> Home</span>
                            </a>
                        </li>
                        <li>
                            <%if (chck.getPosition().equalsIgnoreCase("Project Development Officer III")) {%>
                            <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=education_approval">
                                <i class="fa fa-check-circle-o"></i><span>Approval of Reports</span>
                            </a>
                            <%} else if (chck.getPosition().equalsIgnoreCase("Project Development Officer I")) {%>
                            <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=demographics_approval">
                                <i class="fa fa-check-circle-o"></i><span>Approval of Reports</span>
                            </a>
                            <%} else if (chck.getPosition().equalsIgnoreCase("Project Development Officer IV")) {%>
                            <a href="${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=health_approval">
                                <i class="fa fa-check-circle-o"></i><span>Approval of Reports</span>
                            </a>
                            <%}%>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=reportsLibrary">
                                <i class="fa fa-book"></i>
                                <span>Internal Reports Library</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#" onclick='$("#clicked-span").removeClass("fa-angle-down");
                                    $("#clicked-span").addClass("fa-angle-left");'>
                                <img src="AdminLTE/dist/img/analysis.png" style="width: 8%;" alt=""/>
                                <span style="margin-left: 2%">Analysis</span>
                                <span id="clicked-span" class="fa fa-angle-down" style="float: right;"></span>
                            </a>
                            <ul class="treeview-menu menu-open" style="display:block; background-color: #f9fafc; margin-left: 8%;">
                                <li>
                                    <a href="${pageContext.request.contextPath}/ServletAccess?redirect=StandardManipulation" style="color: #cc5200">
                                        <i class="fa fa-bar-chart-o"></i>
                                        <span>Integrated Analytics</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/ServletAccess?redirect=DataManipulation" style="color: #cc5200">
                                        <i class="fa fa-database"></i>
                                        <span>Customized Analytics</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#" onclick='$("#clicked-span").removeClass("fa-angle-down");
                                    $("#clicked-span").addClass("fa-angle-left");'>
                                <span class=" fa-file-word-o fa"></span>
                                <span>Report Generation</span>
                                <span id="clicked-span" class="fa fa-angle-down" style="float: right;"></span>
                            </a>
                            <ul class="treeview-menu menu-open" style="display:block; background-color: #f9fafc; margin-left: 8%;">

                                <li>
                                    <a href="${pageContext.request.contextPath}/ServletAccess?redirect=CreateReport" style="color: #cc5200">
                                        <i class="fa fa-pencil-square-o"></i>
                                        <span>Create Report</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/ServletAccess?redirect=SavedReports" style="color: #cc5200">
                                        <i class="fa fa-file-text-o"></i>
                                        <span>Draft Reports</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=PublishedReports">
                                <i class="fa fa-file-archive-o"></i>
                                <span>Published Reports</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=forums">
                                <i class="fa fa-comments"></i>
                                <span>Forums</span>
                            </a>
                        </li>
                    </ul>
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

        <script>

                                $(document).ready(function () {
                                    //   localStorage.setItem("seenNotifications", 0);

                                    setInterval(ajaxCall, 1000);
                                    document.getElementById('yearImport').setAttribute('value', new Date().getFullYear());
                                    var year = document.getElementById('yearImport').value;
                                    function ajaxCall() {
                                        var position = document.getElementById('position').value;

                                        $.ajax({
                                            url: "NotificationsPusher",
                                            type: 'POST',
                                            dataType: "json",
                                            data: {
                                                year: year,
                                                position: position
                                            },
                                            success: function (data) {

                                                if (typeof Storage !== "undefined") {
                                                    if (sessionStorage.data) {
                                                        var recent = JSON.parse(sessionStorage.data);
                                                        if (sessionStorage.data !== JSON.stringify(data)) {
                                                            $("#value").html(Math.abs(data.length - recent.length));
                                                        } else {
                                                            //do nothing
                                                        }
                                                        sessionStorage.data = JSON.stringify(data);
                                                    } else {
                                                        sessionStorage.data = JSON.stringify(data);
                                                        $("#value").html(data.length);
                                                    }
                                                }



                                                $("#myDropdown").empty();
                                                if (data.length === 0) {
                                                    $("#myDropdown").append('<li class="padding2" style="text-align:center;">No New Notifications</li>');
                                                    $("#value").empty();
                                                    $("#value").append("");
                                                }
                                                for (var i = 0; i < data.length; i++) {
                                                    if (i != 0)
                                                        $("#myDropdown").append('<hr/>');
                                                    if (data[i].status === "For Approval") {
                                                        $("#myDropdown").append('<li class="padding2"><a class="task" style="text-decoration:none;">'
                                                                + data[i].task + '</a><br/><h6 class="p">Recently Uploaded By ' + data[i].name + '<br/>' + data[i].time + '</h6></li>');
                                                    }
                                                    if (data[i].status === "Delayed") {
                                                        $("#myDropdown").append('<li class="padding2"><a class="delayed" style="text-decoration:none;">'
                                                                + data[i].task + '</a><br/><h6 class="p">The Report is delayed for <br/>' + data[i].time + ' days </h6></li>');
                                                    }
                                                    if (data[i].status === "Pending") {
                                                        $("#myDropdown").append('<li class="padding2"><a class="delayed" style="text-decoration:none;">'
                                                                + data[i].task + '</a><br/><h6 class="p">The Report is due for <br/>' + data[i].time + ' days </h6></li>');
                                                    }
                                                }

                                            }, error: function (XMLHttpRequest, textStatus, exception) {
                                                console.log(XMLHttpRequest.responseText);
                                            }
                                        });
                                    }

                                    $('body').on('click', 'a.delayed', function () {
                                        var formName = $(this).text();
                                        if (formName === "Health Analysis Matrix") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Matrix");
                                        } else if (formName === "Education Analysis Matrix") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Matrix");
                                        } else if (formName === "Demographics Analysis Matrix") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Matrix");
                                        } else if (formName === "Demographics Analysis") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Analysis");
                                            //DEMO
                                        } else if (formName === "Education Analysis") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Analysis");
                                        } else if (formName === "Health Analysis") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Analysis");
                                        } else if (formName === "Eudcation Integrated Analysis") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Integrated");
                                        }//DEMO END
                                        else if (formName === "Health Integrated Analysis") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Integrated");
                                        } else if (formName === "Demographics Integrated Analysis") {
                                            window.location.replace("ReportAccess?redirect=addChartReport&type=Integrated");
                                        }

                                    });


                                    $('body').on('click', 'a.task', function () {
                                        var formName = $(this).text();
                                        if (formName === "Enrollment in Public School") {
                                            window.location.replace("ViewReportsForApprovalEduc?page=enrollmentApprovalS&classification=public&formID=" + year);
                                        } else if (formName === "Enrollment in Private School") {
                                            window.location.replace("ViewReportsForApprovalEduc?page=enrollmentApprovalS&classification=private&formID=" + year);
                                        } else if (formName === "Number of Teachers and Classrooms for Public Schools") {
                                            window.location.replace("ViewReportsForApprovalEduc?page=directoryApproval&classification=public");
                                        } else if (formName === "Number of Teachers and Classrooms for Private Schools") {
                                            window.location.replace("ViewReportsForApprovalEduc?page=directoryApproval&classification=private");
                                            //DEMO
                                        } else if (formName === "Household Population by Age Group and Sex") {
                                            window.location.replace("ViewArchivesReportForApproval?page=byAgeGroupApprovalS&formID=" + year);
                                        } else if (formName === "Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex") {
                                            window.location.replace("ViewArchivesReportForApproval?page=highestAttaintmentApprovalS&formID=" + year);
                                        } else if (formName === "Household Population 10 years old & over by Age Group, Sex and Marital Status") {
                                            window.location.replace("ViewArchivesReportForApproval?page=maritalStatusApprovalS&formID=" + year);
                                        }//DEMO END
                                        else if (formName === "Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender") {
                                            window.location.replace("ViewReportForApprovalHealth?page=nutritionalApprovalS&formID=" + year);
                                        } else if (formName === "List of Hospitals") {
                                            window.location.replace("ViewReportForApprovalHealth?page=directoryApprovalS&formID=" + year);
                                        }

                                    });
                                });

                                var dropdown = document.getElementById("dropdownClicked");
                                dropdown.addEventListener('click', myFunction, false);
                                /* When the user clicks on the button,
                                 toggle between hiding and showing the dropdown content */
                                function myFunction() {
                                    $("#value").empty();
                                    document.getElementById("myDropdown").classList.toggle("show");
                                    console.log("working");
                                }
                                function clearStorage() {
                                    sessionStorage.clear();
                                }

                                // Close the dropdown if the user clicks outside of it
                                window.onclick = function (event) {
                                    if (!event.target.matches('.dropbtn')) {
                                        var dropdowns = document.getElementsByClassName("dropdown-content");
                                        var i;
                                        for (i = 0; i < dropdowns.length; i++) {
                                            var openDropdown = dropdowns[i];
                                            if (openDropdown.classList.contains('show')) {
                                                openDropdown.classList.remove('show');
                                            }
                                        }
                                    }
                                }
        </script>
    </body>
</html>
