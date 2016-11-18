<%--
    Document   : createReport
    Created on : 07 19, 16, 12:36:46 AM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>

        <title>Create Report</title>

        <style>
            /*            body {
                            text-align: center;
                        }*/

            div#editor {
                width: 81%;
                margin: auto;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="content-wrapper">
                <section class="content-header">
                    <h1><i class="fa fa-pencil-square-o"></i> Report Generation</h1>
                </section>

                <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                    <li class="title">Report Generation</li>
                    <li class="active title">Create Report</li>
                </ol>
                <section class="content">
                    <div class="row">
                        <!--REPORT-->
                        <div id="report" style="margin: 0 auto; float:none;" align="center">
                            <input type="hidden" name="userID" id="userID" value="<%= user.getUserID()%>" />
                            <!--CONTENT BODY-->
                            <%
                                int counter = (int) request.getAttribute("counter");
                                String sector = (String) request.getAttribute("sector");
                            %>
                            <!---->
                            <%if (counter > 1) {%>
                            <%
                                String matrix = (String) request.getAttribute("Matrix");
                                String analysis = (String) request.getAttribute("Analysis");
                                String integrated = (String) request.getAttribute("Integrated");
                            %>
                            <div class="form-inline">
                                <div class="form-group">
                                    <select id="selected" name="title" class="form-control" style="width: 350px;">
                                        <option selected disabled>Choose Report</option>
                                        <%if (matrix != "none") {%>
                                        <option value="Matrix"/> <%=sector%> Analysis Matrix </option>
                                        <%}%>

                                        <%if (analysis != "none") {%>
                                        <option value="Analysis"><%=sector%> Analysis</option>
                                        <%}%>

                                        <%if (integrated != "none") {%>
                                        <option value="Integrated"><%=sector%> Integrated Analysis</option>
                                        <%}%>
                                    </select>
                                    <button class="form-class btn btn-primary" id="spanClick">Create Report</button>
                                </div>
                            </div>
                            <%} else if (counter == 1) {
                                String matrix = (String) request.getAttribute("Matrix");
                                String analysis = (String) request.getAttribute("Analysis");
                                String integrated = (String) request.getAttribute("Integrated");

                            %>

                            <div class="form-inline">
                                <div class="form-group">
                                    <p style="font-weight: bold; font-size: medium;">Create Report for

                                        <%if (matrix != "none") {%>
                                        <input id="inputTitle" name="title" type="text" value="<%=sector%> Analysis Matrix" style="width:300px; background:transparent; border:none;" readonly />
                                        <%}%>

                                        <%if (analysis != "none") {%>
                                        <input id="inputTitle" name="title"  type="text" value="<%=sector%> Analysis" style="width:300px; background:transparent; border:none;" readonly />
                                        <%}%>

                                        <%if (integrated != "none") {%>
                                        <input id="inputTitle" name="title"  type="text" value="<%=sector%> Integrated Analysis" style=" background:transparent; border:none; width: 300px;" readonly />
                                        <%}%>
                                    </p>
                                    <button class="form-class btn btn-primary" id="spanClick">Create Report</button>
                                </div>
                            </div>

                            <%} else {
                                String counterAlert = (String) request.getAttribute("countAlert");
                            %>
                            <%if (counterAlert.equalsIgnoreCase("draft")) { %>
                            <input id="draft" type="text" style="background:transparent; border:none;"/>
                            <div data-backdrop="static" id="warning" class="modal fade" role="dialog">
                                <div class="modal-dialog" style="margin-top: 15%;">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Please Go To Drafts..</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p align="center">You have already created drafts for both matrix and analysis reports.
                                                Please proceed to the draft reports to edit or add new charts to the report.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="${pageContext.request.contextPath}/ReportAccess?redirect=Saved&Navi=true" class="btn btn-primary">Proceed to Saved Reports</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <%} else if (counterAlert.equalsIgnoreCase("submitted")) {%>
                            <input id="submitted" type="text" style="background:transparent; border:none;"/>
                            <div data-backdrop="static" id="warning" class="modal fade" role="dialog">
                                <div class="modal-dialog" style="margin-top: 15%;">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Task Completed!</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p align="center">You have already created all reports for this year.
                                                Please proceed to published reports in order to view report.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=PublishedReports" class="btn btn-primary">Proceed to Published Reports</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <%} else if (counterAlert.equalsIgnoreCase("either")) {%>
                            <input id="either" type="text" style="background:transparent; border:none;"/>
                            <div data-backdrop="static" id="warning" class="modal fade" role="dialog">
                                <div class="modal-dialog" style="margin-top: 15%;">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Task Completed!</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p align="center">You have already created all reports for this year. Either Published or saved
                                                Please proceed to published reports in order to view report.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="${pageContext.request.contextPath}/ReportAccess?redirect=Saved&Navi=true" class="btn btn-primary">Proceed To Saved Reports</a>
                                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=PublishedReports" class="btn btn-primary">Proceed To Published Reports</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <%}
                                }%>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </div>
            <script>
                $(document).ready(function () {

                    var x = $('#either').is(':visible');
                    var y = $('#draft').is(':visible');
                    var z = $('#submitted').is(':visible');

                    if (x || y || z) {
                        $(window).load(function () {
                            $('#warning').modal('show');
                        });
                    }



                    $(document).on('click', '#spanClick', function () {
                        var j;
                        var x = $('#selected').is(':visible');
                        var y = $('#inputTitle').is(':visible');
                        if (x) {
                            j = $("#selected option:selected").val();
                        } else if (y) {
                            j = $("#inputTitle").val();
                        }
                        window.location.replace("${pageContext.request.contextPath}/ReportAccess?redirect=addChartReport&type=" + j);
                    });
                });
            </script>
        </div>
    </body>
</html>
