<%--
    Document   : DataManipulation
    Created on : 07 19, 16, 12:36:46 AM
    Author     : Geraldine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsPivot/jsapi.js" type="text/javascript"></script>
        <link href="cssPivot/pivot.css" rel="stylesheet" type="text/css"/>
        <script src="jsPivot/pivot.js" type="text/javascript"></script>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="jsAnalysisImports/jsCommonReports.js" type="text/javascript"></script>
        <script src="jsPivot/gchart_renderers.js" type="text/javascript"></script>
        
        <title>Customized Analytics</title>

        <style>
            .textarea{
                margin: 0 auto;
                width: 80%;
                vertical-align: middle;
            }
            textarea{
                resize: none;
                width: 100%;
            }
            .btn-circle {
                width: 30px;
                height: 30px;
                text-align: center;
                padding: 6px 0;
                font-size: 12px;
                line-height: 1.428571429;
                border-radius: 15px;
            }
            .lightblue{
                background-color: #00ACD6;
                color: #fff;
            }
        </style>
    </head>
    <body>

        <div class="wrapper">
            <div class="content-wrapper">

                <section class="content">
                    <div class="row">
                        <section class="content-header">
                            <h1><img src="AdminLTE/dist/img/analysis.png" style="width: 3%;" alt=""/> Analysis</h1>
                        </section>
                        <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                            <li class="title">Analysis</li>
                            <li class="active title">Customized Analytics</li>
                        </ol>

                        <div align="center" id="analysisbuttons">
                            <select id="selected" onchange="getSelected()" class="form-control" name="title" style="margin: 1% auto; width: 40%;">
                                <option disabled selected>Choose which data to analyze</option>
                                <option value="1">Analyze Demographics Data</option>
                                <option value="2">Analyze Education Data</option>
                                <option value="3">Analyze Health Data</option>
                            </select>
                        </div>
                        <div align="center">
                            <br/><br/>
                            
                            <div id="theButtonsForPivot" style="display:none; margin-left: 2%;">

                                <button class="btn btn-default btn-sm" id="b1" type="button" ><span class="glyphicon glyphicon-menu-left"></span></button>
                                <button class="btn btn-default btn-sm" id="b3" type="button"  >Change Zoom</button>
                                <button class="btn btn-default btn-sm" id="b2" type="button"  ><span class="glyphicon glyphicon-menu-right"></span></button>
                                &nbsp;&nbsp;
                                <button type="button" class="btn btn-info  btn-circle" data-toggle="modal" data-target="#instructions"><i class="fa fa-fw fa-info"></i></button>
                                <br>
                                <button class="btn btn-default btn-sm" id="b1" type="button" ><span class="glyphicon glyphicon-menu-left"></span></button>
                            </div>

                            <select id="commonReports" name="commonReports" class="form-control" onchange="getData()" style="display:none; margin-left: 2%; margin-top: 1%; margin-bottom: 1%; margin-right: 2%; width: 95%;">
                            </select>
                            
                            <div id='withoutChartSelection' style='margin-left:1%;margin-right:1%;'>
                            </div>
                            
                            <div id='withChartSelection' style='margin-left:1%;margin-right:1%;width:100%'>
                            </div>
                            
                            <div class="box-body">
                                <div id="output" style="width:95%; margin-left: 2%; margin-top: 1%; margin-bottom: 1%; margin-right: 2%; ">
                                </div>
                            </div>

                            <%
                                String reportKind = (String) request.getAttribute("type");
                                if (reportKind.equalsIgnoreCase("matrix")) {
                            %>
                            <button  type="button" class="btn btn-primary" id="btnMatrix" style="margin-bottom: 3%;" >Add Chart to Sector Analysis Matrix</button>
                            <%
                                } else if (reportKind.equalsIgnoreCase("analysis")) {
                            %>
                            <button  type="button" class="btn btn-primary"  id="btnReport" style="margin-bottom: 3%;">Add Chart to Report Analysis</button>
                            <%}%>
                        </div>

                        <!--REPORT START HERE-->
                        <!--<div style="margin: 0 auto;">-->
                        <form id="reportForm" action="CreateReport" method="post">
                            <div class="col-md-10" style="display:none; margin: 0 auto; float:none;" id="showReport" align="center">
                                <div class="box box-solid">
                                    <!-- /.box-header -->
                                    <div class="box-body" align="center">
                                        <div id="reportBody">

                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <input type="hidden" name="isDraft" id="reportIsDraft" />
                                    <button class="btn btn-primary button-submit" style="margin-right: 1%;" onClick="setReport('Save')">Save</button>
                                    <!--                                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#publishWarning">Publish</button>-->
                                </div>
                            </div>
                        </form>
                        <!--</div>-->
                        <!--REPORT END HERE-->


                        <!--Instructions MODAL-->
                        <div class="modal fade" id="instructions" role="dialog">
                            <div class="modal-dialog modal-lg" style="width: 1000px; padding: 0; margin-left: 15%; margin-top: 3%;">
                                <div class="modal-content">
                                    <div class="modal-header lightblue">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title" ><b>Instructions</b></h4>
                                    </div>
                                    <div class="modal-body">
                                        <center><img id="loadingSpinner" src="img/DataManipulation.png"></center>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--                        MODAL CONFIRMATION
                                                <div id="publishWarning" class="modal fade" role="dialog">
                                                    <div class="modal-dialog" style="padding: 0; margin-left: 30%; margin-top: 10%;">
                                                         Modal content
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
                                                                <button class="btn btn-primary button-submit" onClick="setReport('Submit')" value="Submit"/>Submit</button>
                                                                <button class="btn btn-default" data-dismiss="modal" type="button">Cancel</button>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                END MODAL INFORMATION-->

                        <!--CHOOSE WHAT DIV-->
                        <div style="display:none;">
                            <div id="report" >
                                <!--                                <div class="col-md-8" align="center">
                                                                    <div class="box box-solid">
                                                                        <div class="box-header with-border">
                                                                            <h3 class="box-title"></h3>
                                                                        </div>
                                                                         /.box-header
                                                                        <div class="box-body" align="center">-->
                                <input type="hidden" name="reportType" value="analysis" />
                                <br>
                                <label for="findings" style="margin-bottom:none; padding-bottom:none;">Analysis:</label>
                                <textarea name="findings" class="form-control" rows="3" style="resize:none; width: 70%; margin: 0 auto 3% auto ;" placeholder="Type Analysis Here..." ></textarea>
                                <!--                                        </div>
                                                                    </div>
                                                                </div>-->
                            </div>
                        </div>

                        <div style="display:none;">
                            <div class="col-md-10" align="center">
                                <div class="box box-solid">
                                    <!-- /.box-header -->
                                    <div class="box-body" align="center" id="matrix">
                                        <input type="hidden" name="reportType" value="matrix" />
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
                                                    <td><textarea name="observations" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"> </textarea></td>
                                                    <td><textarea name="Explanations" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"> </textarea></td>
                                                    <td><textarea name ="Implications" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"> </textarea></td>
                                                    <td><textarea name="Interventions" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"></textarea></td>
                                                </tr>
                                            </tbody>
                                        </table>

                                        <!--                                        <button type="button" class="btn btn-primary pull-right" style="margin-top: 2%;" onclick="addRow()"><span class="fa fa-plus"></span> Add Row</button>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>

        <!--https://github.com/nicolaskruchten/pivottable-->
        <% if (!reportKind.equalsIgnoreCase("none")) { %>

        <% if (user.getPosition().equals("Project Development Officer III")) { %>
        <script>window.onload = function () {
                getFactEducation();
            };
            document.getElementById('analysisbuttons').style.display = "none";
            document.getElementById('theButtonsForPivot').style.display = "block";</script>
            <%} else if (user.getPosition().equals("Project Development Officer IV")) { %>
        <script>window.onload = function () {
                getFactHospital();
            };
            document.getElementById('analysisbuttons').style.display = "none";
            document.getElementById('theButtonsForPivot').style.display = "block";</script>
            <%} else if (user.getPosition().equals("Project Development Officer I")) { %>
        <script>window.onload = function () {
                getFactPeople();
            };
            document.getElementById('analysisbuttons').style.display = "none";
            document.getElementById('theButtonsForPivot').style.display = "block";</script>
            <%}%>
            <%}%>
        <script>

            function deleteRow(a) {
                $(a).closest("tr").remove();
            }

            function deleteDiv(a) {
                $(a).closest('.reportDelete').remove();
            }

            function addRow()
            {
                $('#matrix tr:last').after('<tr><td><textarea name="observation" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"></textarea></td><td><textarea name="explanation" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"></textarea></td><td><textarea name="implication" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"></textarea></td><td style="border-right: none;"><textarea name="intervention" style="width:100%; resize: none; height: 150px; border: none; margin: 0; padding: 0;"></textarea></td><td width="1%" style="margin: 0 auto;border-left: none;"><button class="btn btn-danger btn-sm" onclick="deleteRow(this)">X</button></td>');
            }


            function setReport(z) {
                if (z === "Save") {
                    document.getElementById('reportIsDraft').setAttribute('value', "true");
                    document.getElementById('reportForm').submit();
                }
            }

            function getSelected() {
                var conceptName = $('#selected').find(":selected").val();
                if (conceptName == "1") {
                    getFactPeople();
                } else if (conceptName == "2") {
                    getFactEducation();
                } else if (conceptName == "3") {
                    getFactHospital();
                }
                document.getElementById('theButtonsForPivot').style.display = "block";
            }

            google.load("visualization", "1", {packages: ["corechart", "charteditor"]});

            var derivers = $.pivotUtilities.derivers;
            var renderers = $.extend($.pivotUtilities.renderers, $.pivotUtilities.gchart_renderers);
            var utils = $.pivotUtilities;
            
            function addPivotButtons(){
                $("#theButtonsForPivot").empty();
                $("#theButtonsForPivot").append("<button class='btn btn-default btn-sm' id='b1' type='button' ><span class='glyphicon glyphicon-menu-left'></span></button>");
                $("#theButtonsForPivot").append("<button class='btn btn-default btn-sm' id='b3' type='button'  >Change Zoom</button>");
                $("#theButtonsForPivot").append("<button class='btn btn-default btn-sm' id='b2' type='button'  ><span class='glyphicon glyphicon-menu-right'></span></button>");
                $("#theButtonsForPivot").append("&nbsp;&nbsp;");
                $("#theButtonsForPivot").append("<button type='button' class='btn btn-info  btn-circle' data-toggle='modal' data-target='#instructions'><i class='fa fa-fw fa-info'></i></button>");
            }   
            
            function addWithChartSelections(method, charts){
                $("#theButtonsForPivot").empty();
                $("#withoutChartSelection").empty();
                $("#withChartSelection").empty();
                $("#withChartSelection").append("<div style='margin-right:2%'><div class='col-md-2'>\n\
                                                    <div class='box box-solid'>\n\
                                                        <button id='pivot' class='btn btn-default' style='width:100%; height:200px' onclick='"+method+"'>Pivot Table</button>\n\
                                                    </div>\n\
                                                </div>\n\
                                                <div class='col-md-5'>\n\
                                                    <table>\n\
                                                        <tr>\n\
                                                            <select id='charts' name='charts' class='form-control' onchange='changeChart()'>\n\
                                                            </select>\n\
                                                            </br>\n\
                                                        </tr>\n\
                                                            <div class='box box-solid'>\n\
                                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                                    <h4 class='box-title'>Filter by Year</h4>\n\
                                                                </div>\n\
                                                                <div class='box-body'>\n\
                                                                    <div id='years' style='height: 85px;'>\n\
                                                                    </div>\n\
                                                                </div>\n\
                                                            </div>\n\
                                                        </tr>\n\
                                                    </table>\n\
                                                </div>\n\
                                                <div class='col-md-5'>\n\
                                                    <div class='box box-solid'>\n\
                                                        <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                            <h4 class='box-title'>Filter by District</h4>\n\
                                                        </div>\n\
                                                        <div class='box-body'>\n\
                                                            <div id='districts' style=' height: 140px;'>\n\
                                                            </div>\n\
                                                        </div>\n\
                                                    </div>\n\
                                                </div></div>");
                $('select[name="charts"]').empty();
                $('select[name="charts"]').append("<option disabled selected>Choose visualization</option>");
                for (i = 0; i < charts.length; i++) { 
                    $('select[name="charts"]').append("<option value='"+charts[i]+"'>"+charts[i]+"</option>");
                }
                document.getElementById('commonReports').style.display = "block";
            }   
            
            function addWithChartSelectionsWithBarangays(method, charts){
                $("#theButtonsForPivot").empty();
                $("#withoutChartSelection").empty();
                $("#withChartSelection").empty();
                $("#withChartSelection").append("<div style='margin-right:2%'><div class='col-md-2'>\n\
                                                    <div class='box box-solid'>\n\
                                                        <button id='pivot' class='btn btn-default' style='width:100%; height:200px' onclick='"+method+"'>Pivot Table</button>\n\
                                                    </div>\n\
                                                </div>\n\
                                                <div class='col-md-5'>\n\
                                                    <table>\n\
                                                        <tr>\n\
                                                            <select id='charts' name='charts' class='form-control' onchange='changeChart()'>\n\
                                                            </select>\n\
                                                            </br>\n\
                                                        </tr>\n\
                                                            <div class='box box-solid'>\n\
                                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                                    <h4 class='box-title'>Filter by Sex</h4>\n\
                                                                </div>\n\
                                                                <div class='box-body'>\n\
                                                                    <div id='sex' style='height: 85px;'>\n\
                                                                    </div>\n\
                                                                </div>\n\
                                                            </div>\n\
                                                        </tr>\n\
                                                    </table>\n\
                                                </div>\n\
                                                <div class='col-md-5'>\n\
                                                <table>\n\
                                                    <tr>\n\
                                                        <select id='years' class='form-control' onchange='filterYear()' style=''>\n\
                                                        </select><br>\n\
                                                    </tr>\n\
                                                    <tr>\n\
                                                            <div class='box box-solid'>\n\
                                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                                    <h4 class='box-title'>Filter by Barangay</h4>\n\
                                                                </div>\n\
                                                                <div class='box-body'>\n\
                                                                    <div  id='barangays' style='height: 85px; overflow-y: scroll'>\n\
                                                                </div>\n\
                                                            </div>\n\
                                                    </tr>\n\
                                                </table></div></div>");
                $('select[name="charts"]').empty();
                $('select[name="charts"]').append("<option disabled selected>Choose visualization</option>");
                for (i = 0; i < charts.length; i++) { 
                    $('select[name="charts"]').append("<option value='"+charts[i]+"'>"+charts[i]+"</option>");
                }
                document.getElementById('commonReports').style.display = "block";
            }  
            
            function addWithChartSelectionsWithGradeLevel(method, charts){
                $("#theButtonsForPivot").empty();
                $("#withoutChartSelection").empty();
                $("#withChartSelection").empty();
                $("#withChartSelection").append("<div style='margin-right:2%'><div class='col-md-2'>\n\
                                                    <div class='box box-solid'>\n\
                                                        <button id='pivot' class='btn btn-default' style='width:100%; height:200px' onclick='"+method+"'>Pivot Table</button>\n\
                                                    </div>\n\
                                                </div>\n\
                                                <div class='col-md-5'>\n\
                                                    <table>\n\
                                                        <tr>\n\
                                                            <select id='charts' name='charts' class='form-control' onchange='changeChart()'>\n\
                                                            </select>\n\
                                                            </br>\n\
                                                        </tr>\n\
                                                            <div class='box box-solid'>\n\
                                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                                    <h4 class='box-title'>Filter by Sex</h4>\n\
                                                                </div>\n\
                                                                <div class='box-body'>\n\
                                                                    <div id='sex' style='height: 85px;'>\n\
                                                                    </div>\n\
                                                                </div>\n\
                                                            </div>\n\
                                                        </tr>\n\
                                                    </table>\n\
                                                </div>\n\
                                                <div class='col-md-5'>\n\
                                                <table>\n\
                                                    <tr>\n\
                                                        <select id='years' class='form-control' onchange='filterYear()' style=''>\n\
                                                        </select><br>\n\
                                                    </tr>\n\
                                                    <tr>\n\
                                                            <div class='box box-solid'>\n\
                                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                                    <h4 class='box-title'>Filter by Grade Level</h4>\n\
                                                                </div>\n\
                                                                <div class='box-body'>\n\
                                                                    <div  id='gradeLevels' style='height: 85px; overflow-y: scroll'>\n\
                                                                </div>\n\
                                                            </div>\n\
                                                    </tr>\n\
                                                </table></div></div>");
                $('select[name="charts"]').empty();
                $('select[name="charts"]').append("<option disabled selected>Choose visualization</option>");
                for (i = 0; i < charts.length; i++) { 
                    $('select[name="charts"]').append("<option value='"+charts[i]+"'>"+charts[i]+"</option>");
                }
                document.getElementById('commonReports').style.display = "block";
            }  
            
            function addWithoutChartSelections(method){
                $("#theButtonsForPivot").empty();
                $("#withChartSelection").empty();
                $("#withoutChartSelection").empty();
                $("#withoutChartSelection").append("<div class='col-md-2'>\n\
                                            <div class='box box-solid'>\n\
                                                <button class='btn btn-default' style='width:100%; height:200px' onclick='"+method+"'>Pivot Table</button>\n\
                                            </div>\n\
                                        </div>\n\
                                        <div class='col-md-5'>\n\
                                            <div class='box box-solid'>\n\
                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                    <h4 class='box-title'>Filter by Year</h4>\n\
                                                </div>\n\
                                                <div class='box-body'>\n\
                                                    <div id='years'  style='height: 140px;'> <!--- overflow-y: scroll; -->\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>\n\
                                        <div class='col-md-5'>\n\
                                            <div class='box box-solid'>\n\
                                                <div class='box-header with-border' style='background: #a1bce1; color: #FFF'>\n\
                                                    <h4 class='box-title'>Filter by District</h4>\n\
                                                </div>\n\
                                                <div class='box-body'>\n\
                                                    <div id='districts' style=' height: 140px;'>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>");
            }
            function getFactPeople() {
                $("#output").empty();
                $(function () {
                    $.getJSON("FactPeopleServlet", function (mps) {
                        $("#output").pivotUI(mps, {
                            renderers: renderers,
                            cols: ["Census Year"],
                            aggregatorName: "Sum",
                            vals: ["Total No. Of People"],
                            rendererName: "Bar Chart",
                            unusedAttrsVertical: true,
                            hiddenFromDragDrop: ["Total No. Of People", "Total No. Of Education", "Total No. Of Single", "Total No. Of Married", "Total No. Of Widowed",
                                "Total No. Of Divorced", "Total No. Of Unknown", "Total No. Of Education", "Total No. Of Pre-School", "Total No. of 1st to 4th Grade",
                                "Total No. of 5st to 6th Grade", "Total No. of No Elementary Graduate", "Total No. of HS Graduates", "Total No. of HS Under-Graduates",
                                "Total No. of  Post-Secondary Undergrand", "Total No. of College Degree Holder", "Total No. of No Baccalaureate", "Total No. of Not Stated", "Total No. of College Undergrand Granduates", "Total No. of  Post-Secondary Granduates"]
                        });
                    });
                });
                addPivotButtons();
                $("#withoutChartSelection").empty();
                $("#withChartSelection").empty();
                $('select[name="commonReports"]').empty();
                //common charts
                $('select[name="commonReports"]').append("<option disabled selected>Commonly Used Reports</option>");
                $('select[name="commonReports"]').append("<option value='Historical Growth of Population'>Historical Growth of Population</option>");
                $('select[name="commonReports"]').append("<option value='Household Population by Age Group and Sex'>Household Population by Age Group and Sex</option>");
                $('select[name="commonReports"]').append("<option value='Household Population by Age Group, Sex and Marital Status'>Household Population by Age Group, Sex and Marital Status</option>");
                document.getElementById('commonReports').style.display = "block";
            }


            function getFactEducation() {
                $("#output").empty();
                $(function () {
                    $.getJSON("FactEducationServlet", function (mps) {
                        $("#output").pivotUI(mps, {
                            renderers: renderers,
                            cols: ["Year", "Classification", "Grade Level"],
                            aggregatorName: "Sum",
                            vals: ["Female Count"],
                            unusedAttrsVertical: true,
                            rendererName: "Bar Chart",
                            hiddenFromDragDrop: ["Male Count", "Female Count", "Gender Disparity Index", "Both Sexes Count"]
                        });
                    });
                });
                addPivotButtons();
                $("#withoutChartSelection").empty();
                $("#withChartSelection").empty();
                
                $('select[name="commonReports"]').empty();
                $('select[name="commonReports"]').append("<option disabled selected>Commonly Used Reports</option>");
                $('select[name="commonReports"]').append("<option value='Classroom Requirements in Public Elementary Schools'>Classroom Requirements in Public Elementary Schools</option>");
                $('select[name="commonReports"]').append("<option value='Classroom, Teachers Requirements in Public Elementary Schools'>Classroom, Teachers Requirements in Public Elementary Schools</option>");
                $('select[name="commonReports"]').append("<option value='Data on Enrollment, Teachers and Classrooms'>Data on Enrollment, Teachers and Classrooms</option>");
                $('select[name="commonReports"]').append("<option value='Enrollment in Public and Private Preschools'>Enrollment in Public and Private Preschools</option>");
                $('select[name="commonReports"]').append("<option value='Enrollment in Public and Private Elementary Schools'>Enrollment in Public and Private Schools</option>");
                $('select[name="commonReports"]').append("<option value='School-Going Age Population'>School-Going Age Population</option>");
                document.getElementById('commonReports').style.display = "block";
                   
            }

            function getFactHospital() {
                $("#output").empty();
                $(function () {
                    $.getJSON("FactHospitalServlet", function (mps) {
                        $("#output").pivotUI(mps, {
                            renderers: renderers,
                            cols: ["Year", "Classification"],
                            aggregatorName: "Sum",
                            vals: ["No. of Beds"],
                            unusedAttrsVertical: true,
                            rendererName: "Bar Chart",
                            hiddenFromDragDrop: ["No. of Beds", "No. of Midwives", "No. of Nurses", "No. of Doctors"]
                        });
                    });
                });
                addPivotButtons();
                $("#withoutChartSelection").empty();
                $("#withChartSelection").empty();
                
                $('select[name="commonReports"]').empty();
                $('select[name="commonReports"]').append("<option disabled selected>Commonly Used Reports</option>");
                $('select[name="commonReports"]').append("<option value='Bed Requirement for Hospitals'>Bed Requirement for Hospitals</option>");
                $('select[name="commonReports"]').append("<option value='Medical Health Facilities and Personnel, Government and Private Hospitals'>Medical Health Facilities and Personnel, Government and Private Hospitals</option>");
                $('select[name="commonReports"]').append("<option value='Nutritional Status of the Preschool and Elementary Students'>Nutritional Status of the Preschool and Elementary Students</option>");
                document.getElementById('commonReports').style.display = "block";
            }

            function getData(){
                var conceptName = $('#commonReports').find(":selected").text();
                if(conceptName=='Household Population by Age Group and Sex'){
                    var chart ='Population Pyramid';
                    setHHPopAgeGroupSex(chart);
                    var charts=['Bar Chart','Pie Chart','Population Pyramid', 'Table'];
                    addWithChartSelectionsWithBarangays('getFactPeople()',charts);
                }
                else if(conceptName=='Household Population by Age Group, Sex and Marital Status'){
                    var chart ='Pie Chart';
                    setMaritalStatus(chart);
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Pie Chart','Table'];
                    addWithChartSelectionsWithBarangays('getFactPeople()',charts);
                }
                else if(conceptName=='Classroom Requirements in Public Elementary Schools'){
                    setClassroomRequirement();
                    var chart ='Bar Chart';
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Table'];
                    addWithChartSelections('getFactEducation()',charts);
                }
                else if(conceptName=='Data on Enrollment, Teachers and Classrooms'){
                    setEnrollmentTeacherClassroom();
                    var chart ='Bar Chart';
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Table'];
                    addWithChartSelections('getFactEducation()',charts);
                }
                else if(conceptName=='Nutritional Status of the Preschool and Elementary Students'){
                    var chart ='Pie Chart';
                    setNutritionalStatus(chart);
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Pie Chart','Table'];
                    addWithChartSelectionsWithGradeLevel('getFactHospital()',charts);
                } 
            }
            
            function changeChart(){
                var conceptName = $('#commonReports').find(":selected").text();
                var chartSelected = $('#charts').find(":selected").text();
                if(conceptName=='Household Population by Age Group and Sex'){
                    var charts=['Bar Chart','Pie Chart','Population Pyramid', 'Table'];
                    addWithChartSelectionsWithBarangays('getFactPeople()',charts);
                    setHHPopAgeGroupSex(chartSelected);
                }
                else if(conceptName=='Household Population by Age Group, Sex and Marital Status'){
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Pie Chart','Table'];
                    addWithChartSelectionsWithBarangays('getFactPeople()',charts);
                    setMaritalStatus(chartSelected);
                }
                else if(conceptName=='Classroom Requirements in Public Elementary Schools'){
                    setClassroomRequirement();
                    var chart ='Bar Chart';
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Table'];
                    addWithChartSelections('getFactEducation()',charts);
                }
                else if(conceptName=='Data on Enrollment, Teachers and Classrooms'){
                    setEnrollmentTeacherClassroom();
                    var chart ='Bar Chart';
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Table'];
                    addWithChartSelections('getFactEducation()',charts);
                } 
                else if(conceptName=='Nutritional Status of the Preschool and Elementary Students'){
                    var chart ='Pie Chart';
                    setNutritionalStatus(chart);
                    $("#theButtonsForPivot").empty();
                    var charts=['Bar Chart','Pie Chart','Table'];
                    addWithChartSelectionsWithGradeLevel('getFactHospital()',charts);
                } 
            }

        </script>

    </body>
</html>
