<%--
    Document   : approvalPageByHealth
    Created on : Jun 8, 2016, 10:13:59 PM
    Author     : Geraldine Atayan
--%>
<%@page import="model.Record"%>
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
                    <h1><i class="fa fa-check-circle-o"></i> Approval of Health Reports </h1>
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
                    <option disabled selected>Choose Health Report</option>
                    <option value="1">Reports of Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender for Approval</option>
                    <option value="2">List of Hospitals</option>
                </select>

                <% String subject = request.getAttribute("subject").toString();%>
                <input type="text" hidden value="<%=subject%>" id="report_title" />

                <%
                    ArrayList<Record> ListOfHospital = (ArrayList<Record>) request.getAttribute("directory");
                    ArrayList<Record> NutritionalStatus = (ArrayList<Record>) request.getAttribute("NutritionalStatus");%>

                <section class="content">
                    <div class="row">
                        <div class="displayNone" id="nutrition">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Reports of Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender for Approval</h3>
                                </div>

                                <div class="box-body">
                                    <form id="Archivednutritional" action="ViewReportForApprovalHealth" method="post">
                                        <table id="approval" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    boolean x = false;
                                                    if (NutritionalStatus.size() != 0)
                                                        for (int i = 0; i < NutritionalStatus.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=NutritionalStatus.get(i).getFormID()%></td>
                                                    <td><%=NutritionalStatus.get(i).getCensusYear()%></td>
                                                    <td><input id ="clickNutritional" class="btn btn-flat btn-success" value="View Data"/></td>
                                                </tr>
                                                <% x = true;

                                                        }
                                                    if (x == false) {
                                                %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                        <input  name="page" type="hidden" value="nutritionalApproval" />
                                        <input id="HformID" name="formID" type="hidden" />
                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <div class="displayNone" id="hospitaldirectory">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">List of Hospitals</h3>
                                </div>

                                <div class="box-body">
                                    <form id="directoryApproval" action="ViewReportForApprovalHealth" method="post">
                                        <table id="approval" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    boolean y = false;
                                                    if (ListOfHospital.size() != 0)
                                                        for (int i = 0; i < ListOfHospital.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=ListOfHospital.get(i).getFormID()%></td>
                                                    <td><%=ListOfHospital.get(i).getCensusYear()%></td>
                                                    <td><input id ="directory" class="btn btn-flat btn-success" value="View Data"/></td>
                                                </tr>
                                                <% y = true;

                                                        }
                                                    if (y == false) {
                                                %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                        <input  name="page" type="hidden" value="directoryApproval" />
                                        <input id="DformID" name="formID" type="hidden" />
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
        <script src="jsHealthImports/clickViewTableHealth.js" type="text/javascript"></script>
        <script src="jsImported/jspDataTables_main.js" type="text/javascript"></script>
        <script>
                    function changeDiv(elem) {
                        if (elem.value == 1) {
                            document.getElementById('nutrition').style.display = "block";
                            document.getElementById('hospitaldirectory').style.display = "none";
                        } else if (elem.value == 2) {
                            document.getElementById('nutrition').style.display = "none";
                            document.getElementById('hospitaldirectory').style.display = "block";
                        }
                    }
                    ;


                    $(document).ready(function () {
                        var title = document.getElementById('report_title').value;
                        if (title == "nutritional") {
                            $("#theSelect").val(1).change();
                        } else if (title == "hospitaldirectory") {
                            $("#theSelect").val(2).change();
                        }
//                        else if (title == "") {
//                            $("#theSelect").val(2).change();
//                        } else if (title == "") {
//                            $("#theSelect").val(3).change();
//                        }
                    });
        </script>
    </body>
</html>
