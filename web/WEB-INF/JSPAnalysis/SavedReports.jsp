<%--
    Document   : StandardManipulation
    Created on : 07 19, 16, 12:36:46 AM
    Author     : Geraldine
--%>

<%@page import="model.reports.Integrated"%>
<%@page import="model.reports.Matrix"%>
<%@page import="model.reports.ReportAnalysis"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="jsAnalysisImports/synchronizedCharts.js" type="text/javascript"></script>
        <script src="Highcharts/modules/map.js" type="text/javascript"></script>
        <script src="Highcharts/modules/data.js" type="text/javascript"></script>
        <title>Draft Reports</title>

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
                    <li class="active title">Draft Reports</li>
                </ol>
                <section class="content">
                    <% String savedMessage = (String) request.getAttribute("savedMessage");
                        if (savedMessage.equalsIgnoreCase("success")) {
                    %>
                    <div class="callout callout-success">
                        <h4>Success! The report has been temporarily saved</h4>
                        <p>Kindly edit them later</p>
                    </div>
                    <%} else if (savedMessage.equalsIgnoreCase("error")) {%>
                    <div class="callout callout-danger">
                        <h4>The report has not been saved</h4>
                        <p>A problem was encountered while uploading your file</p>
                    </div>
                    <%}%>

                    <div class="row">
                        <div id="report" style="margin: 0 auto; float:none;" align="center">
                            <input type="hidden" name="userID" id="userID" value="<%= user.getUserID()%>" />
                            <!--CONTENT BODY-->
                            <%
                                int counter = (int) request.getAttribute("counter");
                                String sector = (String) request.getAttribute("sector");
                            %>
                            <%if (counter > 1) {%>
                            <%
                                String matrix = (String) request.getAttribute("Matrix");
                                String analysis = (String) request.getAttribute("Analysis");
                                String integrated = (String) request.getAttribute("Integrated");
                            %>

                            <div class="form-inline" style=" margin-bottom: 2%;">
                                <div class="form-group">
                                    <select id="selected" name="title" onchange="changeDiv(this)" class="form-control" style="width: 350px;">
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
                                    <button class="form-class btn btn-primary" id="spanClick">Add Chart</button>
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
                                        <input id="inputTitle" name="title"  type="text" value="<%=sector%> Analysis Matrix" style="width:250px;  background:transparent; border:none;" readonly />
                                        <%}%>

                                        <%if (analysis != "none") {%>
                                        <input id="inputTitle" name="title"  type="text" value="<%=sector%> Analysis" style="width:250px;  background:transparent; border:none;" readonly />
                                        <%}%>

                                        <%if (integrated != "none") {%>
                                        <input id="inputTitle" name="title"  type="text" value="<%=sector%> Integrated Analysis" style="width:250px; background:transparent; border:none;" readonly />
                                        <%}%>
                                    </p>
                                    <button class="form-class btn btn-primary" id="spanClick">Add Chart</button>
                                </div>
                            </div>

                            <%} else {
                                String counterAlert = (String) request.getAttribute("countAlert");
                            %>
                            <%if (counterAlert.equalsIgnoreCase("createReport")) { %>
                            <input id="create" type="text" style="background:transparent; border:none;"/>
                            <div data-backdrop="static" id="warning" class="modal fade" role="dialog">
                                <div class="modal-dialog" style="margin-top: 15%;">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Please Go To Create Report..</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p align="center">There are no draft reports at the moment.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="${pageContext.request.contextPath}/ReportAccess?redirect=CreateReport" class="btn btn-primary">Proceed to Create Reports</a>
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
                                            <h4 class="modal-title">No drafts found!</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p align="center">You currently don't have any draft reports. Either proceed to create reports
                                                or view published reports.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="${pageContext.request.contextPath}/ReportAccess?redirect=CreateReport" class="btn btn-primary">Proceed To Create Reports</a>
                                            <a href="${pageContext.request.contextPath}/ServletAccess?redirect=PublishedReports" class="btn btn-primary">Proceed To Published Reports</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <%}
                                }%>
                            <!-- /.box -->
                        </div>
                        <%if (counter != 0) {%>
                        <%
                            String matrix = (String) request.getAttribute("Matrix");
                            String analysis = (String) request.getAttribute("Analysis");
                            String integrated = (String) request.getAttribute("Integrated");

                        %>
                        <form id="reportAnalysis" action="SavedReportAnalysis" method="post">
                            <div class="col-md-10 " id="analysis" align="center" style="margin: 2% auto 0 auto; float:none; display:none;">
                                <%                                    if (analysis.equalsIgnoreCase("Analysis")) {
                                        ArrayList<ReportAnalysis> reportAnalysis = (ArrayList<ReportAnalysis>) request.getAttribute("reportAnalysis");
                                %>
                                <h3>Drafts of <%=reportAnalysis.get(0).getSector()%> Analysis  Produced by <%=reportAnalysis.get(0).getCreatedByName()%></h3>

                                <% for (int i = 0; i < reportAnalysis.size(); i++) {%>
                                <div class="box box-solid reportAnalysis">

                                    <%        if (i != 0) {%>
                                    <hr style="margin-top: 2%; margin-bottom: 2%;">
                                    <%}%>
                                    <input type="hidden" name="reportTypeAnlysis" value="analysis" />
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <img style="width: 90%;" id="image" src="<%=reportAnalysis.get(i).getChartPath()%>">
                                        <input type="hidden" name="imageSrcAnalysis" value="<%=reportAnalysis.get(i).getChartName()%>"/>
                                        <textarea name="analysis" rows="4" cols="50" style="resize: none; width: 90%;"
                                                  placeholder="Type Analysis Here..."> <%=reportAnalysis.get(i).getText()%></textarea>
                                        <div style="margin-top: 2%;">
                                            <button type="button" class="btn btn-danger btn-sm pull-left" onclick="deleteAnaylsis(this)"><span class="glyphicon glyphicon-remove"></span> Delete Draft</button>

                                        </div>
                                    </div>
                                </div>
                                <%}%>
                                <input type="hidden" class="draftID" name="isDraft" id="reportIsMatrix" />
                                <button class="btn btn-primary pull-right" type="button" onClick="onClickModal(this)" >Publish</button>
                                <button class="btn btn-primary button-submit pull-right" type="button" style="margin-right: 1%;" onClick="setDraftModal(this)">Save</button>
                                <%}%>
                            </div>
                        </form>


                        <form id="reportMatrix" action="SavedReportMatrix" method="post">
                            <div class="col-md-10"  id="matrix" align="center"  style="margin: 0 auto; float:none; display:none;">

                                <%
                                    if (matrix.equalsIgnoreCase("Matrix")) {
                                        ArrayList<Matrix> Matrix = (ArrayList<Matrix>) request.getAttribute("MatrixSaved");
                                %>
                                <h3 align="center">Drafts of <%=Matrix.get(0).getSector()%> Analysis Matrix  Produced by <%=Matrix.get(0).getCreatedByName()%></h3>
                                <%   for (int i = 0; i < Matrix.size(); i++) {%>
                                <div class="box box-solid reportMatrix"  >
                                    <hr style="margin-top: 2%; margin-bottom: 2%;">
                                    <input type="hidden" name="reportTypeMatrix" value="matrix" />
                                    <!-- /.box-header -->
                                    <div class="box-body" align="center">
                                        <img style="width: 90%;" id="image"  src="<%= Matrix.get(i).getChartPath()%>">
                                        <input type="hidden" name="imageSrcMatrix" value="<%=Matrix.get(i).getChartName()%>"/>
                                        <table class="table table-bordered ">
                                            <thead>
                                                <tr style = "background-color: #454545; color: #fff">
                                                    <td>Observations</td>
                                                    <td>Explanations</td>
                                                    <td>Implications</td>
                                                    <td>Interventions</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><textarea name="observations" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"><%=Matrix.get(i).getObservations()%></textarea></td>
                                                    <td><textarea name="Explanations" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"><%=Matrix.get(i).getExplanations()%></textarea></td>
                                                    <td><textarea name ="Implications" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"><%=Matrix.get(i).getImplications()%></textarea></td>
                                                    <td><textarea name="Interventions" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"><%=Matrix.get(i).getInternventions()%></textarea></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div style="margin-top: 2%;">
                                            <button type="button" class="btn btn-danger btn-sm pull-left" onclick="deleteMatrix(this)"><span class="glyphicon glyphicon-remove"></span> Delete Draft</button>
                                            <!--                                            <button type="button" class="btn btn-primary pull-right" onclick="addRow()"><span class="fa fa-plus"></span> Add Row</button>-->
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                                <input type="hidden" class="draftID" name="isDraft" id="reportDraftMatrix" />
                                <button class="btn btn-primary pull-right" type="button" onClick="onClickModal(this)" >Publish</button>
                                <button class="btn btn-primary button-submit pull-right" type="button" style="margin-right: 1%;" onClick="setDraftModal(this)">Save</button>

                                <% }%>
                            </div>
                        </form>


                        <form id="reportIntegrated" action="SavedReportIntegration" method="post">
                            <div class="col-md-10"  id="integrated" style="margin: 0 auto; float:none; display:none; ">
                                <%
                                    if (integrated.equalsIgnoreCase("Integrated")) {
                                        Integrated Integrated = (Integrated) request.getAttribute("IntegratedSaved");
                                %>
                                <h3 align="center">Drafts of <%=Integrated.getSector()%> Integrated Analysis  Produced by <%=Integrated.getCreatedByName()%></h3>

                                <div class="col-md-6">
                                    <div class="box box-solid">
                                        <div class="box-header with-border" style="background: #a1bce1; color: #FFF">
                                            <h4 class="box-title">Filter By Year</h4>
                                        </div>
                                        <div class="box-body">
                                            <div id="years" style="height: 140px;"> <!--- overflow-y: scroll; -->
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="box box-solid">
                                        <div class="box-header with-border" style="background: #a1bce1; color: #FFF">
                                            <h4 class="box-title">Filter By District</h4>
                                        </div>
                                        <div class="box-body">
                                            <div id="districts" style=" height: 140px;"> <!--- overflow-y: scroll; -->
                                            </div>
                                        </div>
                                    </div>
                                </div>



                                <div class="col-md-12" align="center"  >
                                    <div class="box box-solid">
                                        <div class="box-body">

                                            <div id="container" style="min-width: 310px; height: 300px; width: 600px; margin: 0 auto"></div>
                                            <div id="container2" style="min-width: 310px; height: 300px; width: 600px; margin: 0 auto"></div>
                                            <div id="container3" style="min-width: 310px; height: 300px; width: 600px; margin: 0 auto"></div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <!-- /.box -->
                                </div>


                                <div class="col-md-12" align="center"  >
                                    <div class="box box-solid">

                                        <!-- /.box-header -->
                                        <div class="box-body">
                                            <textarea name="text" placeholder="Type Analysis Here..." rows="20" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"><%=Integrated.getText()%></textarea>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <!-- /.box -->
                                </div>

                                <input type="hidden" class="draftID" name="isDraft" id="reportDraftIntegration" />
                                <button class="btn btn-primary pull-right" type="button" onClick="onClickModal(this)" >Publish</button>
                                <button class="btn btn-primary button-submit pull-right" type="button" style="margin-right: 1%;" onClick="setDraft(this)">Save</button>


                            </div>
                            <%}%>
                        </form>

                        <!--Start MODAL-->

                        <!--END MODAL INFORMATION-->
                        <!--END IF-->
                        <%}%>
                    </div>

                </section>
                <div id="publishWarning" class="modal fade" role="dialog">
                    <div class="modal-dialog" style="padding: 0; margin-left: 30%; margin-top: 10%;">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" style="background: #f39c12;">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title" style="color: #FFF;">Warning!</h4>
                            </div>
                            <div class="modal-body" align="center">
                                <p>You will no longer be able to edit this report once you submit it.
                                    Are you sure you want to proceed?</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary button-submit"  onClick="setSubmit()()" value="Submit"/>Submit</button>
                                <button class="btn btn-default" data-dismiss="modal" type="button">Cancel</button>
                            </div>
                        </div>

                    </div>
                </div>

                <!--save warning modal-->
                <div id="saveWarning" class="modal fade" role="dialog">
                    <div class="modal-dialog" style="padding: 0; margin-left: 30%; margin-top: 10%;">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" style="background: #f39c12;">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title" style="color: #FFF;">Warning!</h4>
                            </div>
                            <div class="modal-body" align="center">
                                <p>Are you sure you want save this? Previous report will be updated.</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary button-submit"  onClick="setSubmit()()" value="Submit"/>Submit</button>
                                <button class="btn btn-default" data-dismiss="modal" type="button">Cancel</button>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
        <script>
            var form = 0;
            function changeDiv(elem) {
                if (elem.value == "Matrix") {
                    document.getElementById('matrix').style.display = "block";
                    document.getElementById('analysis').style.display = "none";
                    document.getElementById('integrated').style.display = "none";
                } else if (elem.value == "Analysis") {
                    document.getElementById('matrix').style.display = "none";
                    document.getElementById('analysis').style.display = "block";
                    document.getElementById('integrated').style.display = "none";
                } else if (elem.value == "Integrated") {
                    var link = document.getElementById('spanClick');
                    link.style.visibility = 'hidden';
                    document.getElementById('matrix').style.display = "none";
                    document.getElementById('integrated').style.display = "block";
                    document.getElementById('analysis').style.display = "none";

                }
            }


            $(document).on('click', '#spanClick', function () {
                var x = $('#selected').is(':visible');
                var y = $('#inputTitle').is(':visible');
                var j;

                if (x) {
                    j = $("#selected option:selected").text();
                } else if (y) {
                    j = $("#inputTitle").val();
                }

                window.location.replace("${pageContext.request.contextPath}/ReportAccess?redirect=addChartReport&type=" + j);
            });


            function onClickModal(modal) {
                var x = $(modal).parents("form").find(".draftID");
                x.val("false");
                form = $(modal).parents('form:first');
                $("#publishWarning").modal("show");
            }

            function setSubmit() {
                form.submit();
            }

            function setDraftModal(draft) {
                var x = $(draft).parents("form").find(".draftID");
                x.val("true");
                form = $(draft).parents('form:first');
                $("#saveWarning").modal("show");
            }

            function deleteAnaylsis(a) {
                $(a).closest('.reportAnalysis').remove();
                console.log($(a).closest('.reportAnalysis').remove());
            }

            function deleteMatrix(a) {
                $(a).closest('.reportMatrix').remove();
                console.log($(a).closest('.reportMatrix').remove());
            }



            $(document).ready(function () {
                var x = $('#either').is(':visible');
                var y = $('#create').is(':visible');
                var z = $('#submitted').is(':visible');

                if (x || y || z) {
                    $(window).load(function () {
                        $('#warning').modal('show');
                    });
                }



                //if one only
                var reportTitle = $('#inputTitle').is(':visible');

                if (reportTitle) {

                    var report = $('#inputTitle').val();
                    console.log("Integrated ANALYSIS" + report.indexOf("Integrated Analysis"));
                    console.log("ANALYSIS" + report.indexOf("Analysis"));

                    if (report.indexOf("Matrix") > -1) {
                        console.log("hello");
                        document.getElementById('matrix').style.display = "block";
                        document.getElementById('integrated').style.display = "none";
                        document.getElementById('analysis').style.display = "none";
                    } else if (report.indexOf("Integrated Analysis") === 13 && report.indexOf("Integrated Analysis") !== 24 || report.indexOf("Integrated Analysis") === 10 && report.indexOf("Integrated Analysis") !== 21 || report.indexOf("Integrated Analysis") === 7 && report.indexOf("Integrated Analysis") !== 13 || report.indexOf("Integrated Analysis") === 7 && report.indexOf("Integrated Analysis") !== 18) {

                        var link = document.getElementById('spanClick');
                        link.style.visibility = 'hidden';

                        document.getElementById('matrix').style.display = "none";
                        document.getElementById('integrated').style.display = "block";
                        document.getElementById('analysis').style.display = "none";
                    } else if (report.indexOf("Analysis") > -1 && report.indexOf("Analysis") == 13 || report.indexOf("Analysis") == 7 || report.indexOf("Analysis") == 10) {


                        document.getElementById('matrix').style.display = "none";
                        document.getElementById('integrated').style.display = "none";
                        document.getElementById('analysis').style.display = "block";
                    }


                }


            });
        </script>
    </body>
</html>
