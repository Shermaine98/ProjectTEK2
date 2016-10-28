<%--
    Document   : approvalsInternal
    Created on : 07 5, 16, 9:34:28 PM
    Author     : Geraldine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="cssImported/ionicons.min.css" rel="stylesheet" type="text/css"/>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        
        <script src="jsImportsITAdmin/AdminHome.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title" align="center">Announcements!</h3>
                                </div>
                                <div class="box-body">
                                    Welcome!
                                </div>
                            </div>
                        </div>
                        <br>

                        <%                            String userReg = request.getAttribute("userRegistrations").toString();
                            String eApprove = request.getAttribute("eApprove").toString();
                            String iApprove = request.getAttribute("iApprove").toString();%>

                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3><%=userReg%></h3>

                                    <p>User Registrations</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-users"></i>
                                </div>
                            </div>
                            <!-- small box -->
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=eapprovals">
                                <div class="small-box bg-yellow">
                                    <div class="inner">
                                        <h3><%=eApprove%></h3>

                                        <p>External Registrations for Approval</p>
                                    </div>
                                    <div class="icon">
                                        <i class="fa fa-check"></i>
                                    </div>
                                </div>
                            </a>
                            <!-- small box -->
                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=iapprovals">
                                <div class="small-box bg-yellow">
                                    <div class="inner">
                                        <h3><%=iApprove%></h3>

                                        <p>Internal Registrations for Approval</p>
                                    </div>
                                    <div class="icon">
                                        <i class="fa fa-check"></i>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <div class="col-lg-9 col-xs-12">
                            <div id="container" style="min-width: 310px; height: 343px; margin: 0 auto"></div>
                        </div>

                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
