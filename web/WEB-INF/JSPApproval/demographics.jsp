<%--
    Document   : demographics
    Created on : Jun 8, 2016, 10:13:59 PM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
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
                    <h1><i class="fa fa-check-circle-o"></i> Approval of Demographic Reports</h1>
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


                <%
                    ArrayList<Record> age = (ArrayList<Record>) request.getAttribute("age");
                    ArrayList<Record> marital = (ArrayList<Record>) request.getAttribute("marital");
                    ArrayList<Record> highest = (ArrayList<Record>) request.getAttribute("highest");%>

                <% if (age.size() == 0 && marital.size() == 0 && highest.size() == 0) {%>
                <div class="box box-solid box-archived" style="width: 80%; margin: 0 auto;">
                    <div class="box-body">
                        <h5 align="center">There are no demographic reports for approval.</h5>
                    </div>
                </div>
                <% } else { %>

                <select class="form-control select" id="theSelect" onChange="changeDiv(this)">
                    <option disabled selected>Choose Demographic Report</option>
                    <% if (age.size() > 0) {%>
                    <option value="1">Household Population by Age Group and Sex</option>
                    <%}
                        if (marital.size() > 0) {%>
                    <option value="2">Household Population 10 years old & over by Age group, Sex and Marital Status</option>
                    <%}
                        if (highest.size() > 0) {%>
                    <option value="3">Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex</option>
                    <% } %>
                </select>
                <%}%>
                <% String subject = request.getAttribute("subject").toString();%>
                <input type="text" hidden value="<%=subject%>" id="report_title" />

                <section class="content">
                    <div class="row">
                        <div class="displayNone" id="agegroup">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Reports of Household Population by Age Group and Sex for Approval</h3>
                                </div>

                                <div class="box-body">
                                    <form id="archivedViewAge" action="ViewArchivesReportForApproval" method="post">
                                        <table id="approval" class="table table-striped" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <%  boolean x = false;
                                                    for (int i = 0; i < age.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=age.get(i).getFormID()%></td>
                                                    <td><%=age.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="clickedTableAge" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto" type="button" value="View Data"/></td>
                                                </tr>
                                                <% x = true;

                                                    }

                                                    if (x == false) {%>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        <input id="page" name="page" type="hidden" value="byAgeGroupApproval" />
                                        <input id="AformID" name="formID" type="hidden" />
                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                        </div>
                        <!--END OF Approval-->

                        <!--MARITAL-->

                        <div class="displayNone" id="marital">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Reports of Household Population 10 years old & over by Age group, Sex and Marital Status for Approval</h3>
                                </div>
                                <div class="box-body">
                                    <form id="archivedViewMarital" action="ViewArchivesReportForApproval" method="post">
                                        <table id="approval" class="table table-striped" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%   boolean y = false;
                                                    for (int i = 0; i < marital.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=marital.get(i).getFormID()%></td>
                                                    <td><%=marital.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="clickedTableMarital" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto" type="button" value="View Data"/></td>
                                                </tr>
                                                <% y = true;

                                                    }
                                                    if (y == false) { %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        <input id="page" name="page" type="hidden" value="maritalStatusApproval" />
                                        <input id="MformID" name="formID" type="hidden" />

                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>

                        <!--HIGHEST-->

                        <div class="displayNone" id="highestcompleted">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">Reports of Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex for Approval</h3>
                                </div>
                                <div class="box-body">

                                    <form id="archivedViewHighest" action="ViewArchivesReportForApproval" method="post">
                                        <table id="approval" class="table table-striped" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th width="3%" style="text-align: center;">View Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%  boolean z = false;
                                                    for (int i = 0; i < highest.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr"><%=highest.get(i).getFormID()%></td>
                                                    <td><%=highest.get(i).getCensusYear()%></td>
                                                    <td> <input align="center" id ="clickedTableHighest" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto" type="button" value="View Data"/></td>
                                                </tr>
                                                <% z = true;

                                                    }
                                                    if (z == false) { %>
                                                <tr>
                                                    <td colspan="3">There are no reports requiring approval</td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                        <input id="page" name="page" type="hidden" value="highestAttaintmentApproval" />
                                        <input id="HformID" name="formID" type="hidden" />

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
        <script src="jsDemoImports/ClickViewTable.js" type="text/javascript"></script>
        <script src="jsImported/jspDataTables_main.js" type="text/javascript"></script>
        <script>
                    function changeDiv(elem) {
                        if (elem.value == 1) {
                            document.getElementById('agegroup').style.display = "block";
                            document.getElementById('marital').style.display = "none";
                            document.getElementById('highestcompleted').style.display = "none";
                        } else if (elem.value == 2) {
                            document.getElementById('agegroup').style.display = "none";
                            document.getElementById('marital').style.display = "block";
                            document.getElementById('highestcompleted').style.display = "none";
                        } else if (elem.value == 3) {
                            document.getElementById('agegroup').style.display = "none";
                            document.getElementById('marital').style.display = "none";
                            document.getElementById('highestcompleted').style.display = "block";
                        }
                    }

                    $(document).ready(function () {
                        var title = document.getElementById('report_title').value;
                        if (title == "AgeGroup") {
                            $("#theSelect").val(1).change();
                        } else if (title == "marital") {
                            $("#theSelect").val(2).change();
                        } else if (title == "highest") {
                            $("#theSelect").val(3).change();
                        }
                    })

        </script>
    </body>
</html>
