<%--
    Document   : home
    Created on : Jun 8, 2016, 10:13:59 PM
    Author     : Geraldine Atayan
--%>
<%@page import="Model.record"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Approval of Reports</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>
        <link href="cssImported/approval.css" rel="stylesheet" type="text/css"/>
        <style>
            td{
                text-align:left;
            }
            .callout{
                margin: 0 auto 2% auto;
                text-align: center;
                width: 80%;
            }
        </style>
    </head>

    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header" style="margin-bottom: 4%;">
                    <h1><i class="fa fa-check-circle-o"></i> Approval of Education Reports</h1>
                </section>

                <%                    String temp = (String) request.getAttribute("message");
                    if (temp.equalsIgnoreCase("success")) {
                %>
                <div class="callout callout-success">
                    <h4>Success!</h4>
                    <p>You have successfully approved the report. Kindly go to the Internal Reports Library in order to see report.</p>
                </div>
                <%
                } else if (temp.equalsIgnoreCase("notSuccess")) {
                %>
                <div class="callout callout-danger">
                    <h4>Error</h4>
                    <p>Something went wrong while approving the report</p>
                </div>
                <%
                } else if (temp.equalsIgnoreCase("successRejected")) {
                %>
                <div class="callout callout-success">
                    <h4>Success!</h4>
                    <p>You have successfully rejected a report</p>
                </div><%
                } else if (temp.equalsIgnoreCase("notSuccessRejection")) {
                %>
                <div class="callout callout-danger">
                    <h4>Error</h4>
                    <p>Something went wrong while rejecting the report</p>
                </div>
                <%}%>
                <select class="form-control select" onChange="changeDiv(this)" id="theSelect">
                    <option disabled selected>Choose Education Report</option>
                    <option value="1">Enrollment in Public Schools</option>
                    <option value="2">Enrollment in Private Schools</option>
                    <option value="3">Number of Teachers and Classrooms for Public Schools</option>
                    <option value="4">Number of Teachers and Classrooms for Private Schools</option>
                </select>

                <% String subject = request.getAttribute("subject").toString();%>
                <input type="text" hidden value="<%=subject%>" id="report_title" />

                <%
                    ArrayList<record> publicEnrollment = (ArrayList<record>) request.getAttribute("publicEnrollment");
                    ArrayList<record> privateEnrollment = (ArrayList<record>) request.getAttribute("privateEnrollment");

                    ArrayList<record> directoryPublic = (ArrayList<record>) request.getAttribute("directoryPublic");
                    ArrayList<record> directoryPrivate = (ArrayList<record>) request.getAttribute("directoryPrivate");%>

                <section class="content">
                    <div class="row">

                        <div class="displayNone" id="enrollpublic">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Enrollment in Public Schools Reports for Approval</h3>
                                </div>

                                <div class="box-body">
                                    <form id="archivedPublicE" action="ViewReportsForApprovalEduc" method="post">
                                        <table id="approval" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% boolean x = false;
                                                    for (int i = 0; i < publicEnrollment.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=publicEnrollment.get(i).getFormID()%></td>
                                                    <td><%=publicEnrollment.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="clickTablePublicE" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto"  value="View Data"/></td>
                                                </tr>
                                                <% x = true;

                                                    }
                                                    if (x == false) {
                                                %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        <input name="page" type="hidden" value="enrollmentApproval" />
                                        <input id="PubEformID" name="formID" type="hidden" />
                                        <input name="classification" type="hidden" value="public" />
                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!--END OF Approval-->
                        <div class="displayNone" id="enrollprivate">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Enrollment in Private Schools Reports for Approval</h3>
                                </div>
                                <div class="box-body">

                                    <form id="archivedPrivateE" action="ViewReportsForApprovalEduc" method="post">
                                        <table id="approval" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%  boolean y = false;
                                                    for (int i = 0; i < privateEnrollment.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=privateEnrollment.get(i).getFormID()%></td>
                                                    <td><%=privateEnrollment.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="clickTablePrivateE" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto"  value="View Data"/></td>
                                                </tr>
                                                <% y = true;

                                                    }
                                                    if (y == false) {
                                                %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        <input  name="page" type="hidden" value="enrollmentApproval" />
                                        <input  name="classification" type="hidden" value="private" />
                                        <input id="PriEformID" name="formID" type="hidden" />

                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>

                        <div class="displayNone" id="publicdirect">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Number of Teachers and Classrooms for Public Schools Reports for Approval</h3>
                                </div>
                                <div class="box-body">
                                    <form id="archivedDirPub" action="ViewReportsForApprovalEduc" method="post">
                                        <table id="approval" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%  boolean z = false;
                                                    for (int i = 0; i < directoryPublic.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=directoryPublic.get(i).getFormID()%></td>
                                                    <td><%=directoryPublic.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="ClickedTableDirectoryPub" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto" value="View Data"/></td>
                                                </tr>
                                                <% z = true;
                                                    }

                                                    if (z == false) {
                                                %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                        <input  name="classification" type="hidden" value="Public" />

                                        <input id="page" name="page" type="hidden" value="directoryApproval" />
                                        <input id="dPubformID" name="formID" type="hidden" />

                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>

                        <div class="displayNone" id="privatedirect">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Number of Teachers and Classrooms for Private Schools Reports for Approval</h3>
                                </div>
                                <div class="box-body">

                                    <form id="archivedDirPri" action="ViewArchivesReportForApproval" method="post">
                                        <table id="approval" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% boolean a = false;
                                                    for (int i = 0; i < directoryPrivate.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=directoryPrivate.get(i).getFormID()%></td>
                                                    <td><%=directoryPrivate.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="ClickedTableDirectoryPri" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto"  value="View Data"/></td>
                                                </tr>
                                                <% a = true;


                                                    }
                                                    if (a == false) {
                                                %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                        <input name="page" type="hidden" value="directoryApproval" />
                                        <input  name="classification" type="hidden" value="Private" />
                                        <input id="dPriformID" name="formID" type="hidden" />
                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>

                    </div>
                </section>
            </div>
        </div>
        <!-- ./wrapper -->
        <script src="jsEducImports/clickviewTableEduc.js" type="text/javascript"></script>
        <script src="jsImported/jspDataTables_main.js" type="text/javascript"></script>
        <script>
                    function changeDiv(elem) {
                        if (elem.value == 1) {
                            document.getElementById('enrollpublic').style.display = "block";
                            document.getElementById('enrollprivate').style.display = "none";
                            document.getElementById('publicdirect').style.display = "none";
                            document.getElementById('privatedirect').style.display = "none";
                        } else if (elem.value == 2) {
                            document.getElementById('enrollpublic').style.display = "none";
                            document.getElementById('enrollprivate').style.display = "block";
                            document.getElementById('publicdirect').style.display = "none";
                            document.getElementById('privatedirect').style.display = "none";
                        } else if (elem.value == 3) {
                            document.getElementById('enrollpublic').style.display = "none";
                            document.getElementById('enrollprivate').style.display = "none";
                            document.getElementById('publicdirect').style.display = "block";
                            document.getElementById('privatedirect').style.display = "none";
                        } else if (elem.value == 4) {
                            document.getElementById('enrollpublic').style.display = "none";
                            document.getElementById('enrollprivate').style.display = "none";
                            document.getElementById('publicdirect').style.display = "none";
                            document.getElementById('privatedirect').style.display = "block";
                        }
                    }

                    $(document).ready(function () {
                        var title = document.getElementById('report_title').value;
                        if (title == "epublic") {
                            $("#theSelect").val(1).change();
                        } else if (title == "eprivate") {
                            $("#theSelect").val(2).change();
                        } else if (title == "publicdirectory") {
                            $("#theSelect").val(3).change();
                        } else if (title == "privatedirectory") {
                            $("#theSelect").val(4).change();
                        }
                    })
        </script>
    </body>
</html>
