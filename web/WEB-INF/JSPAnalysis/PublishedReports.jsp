<%--
    Document   : reportLibrary
    Created on : 07 14, 16, 10:46:44 PM
    Author     : Geraldine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>
        <script src="jsImported/jquery.bootstrap.wizard.min.js" type="text/javascript"></script>
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsReport/searchYearPublish.js" type="text/javascript"></script>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="pdf/xepOnline.jqPlugin.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Highcharts/modules/map.js" type="text/javascript"></script>
        <script src="Highcharts/modules/data.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Published Reports</title>
        <style>
            .form-group{
                border: 1px solid #c3c3c3
            }
            .form-group{
                /*background-color: #FFF;*/
                border: none;
            }
            #spinner-overlay {
                background-color: #aaa;
                opacity: 0.4;
                position: absolute;
                left: 0;
                top: 0;
                z-index: 100;
                height: 100%;
                width: 100%;
                overflow: hidden;
            }
            .wrapper{
                /*z-index:1;*/

                <% if (!user.getDivision().equalsIgnoreCase("Social Development Planning Division")) { %>
                padding: 25px 25px;
                <%}%>
            }
        </style>

        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="spinner-overlay" style="display:none;">
            <center><img src="img/spinner.gif" style="margin-top: 19%; margin-left: 17%; height: 150px;" /></center>
        </div>

        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->

            <% if (user.getDivision().equalsIgnoreCase("Social Development Planning Division")) { %>
            <div class="content-wrapper">
                <% } %>

                <section class="content-header" style="margin-bottom: 1%;">
                    <h1><i class="fa fa-file-archive-o"></i> Published Reports</h1>
                </section>
                <% String savedMessage = (String) request.getAttribute("savedMessage");
                    if (savedMessage.equalsIgnoreCase("succes")) {
                %>
                <div class="callout callout-success" style="margin-top: 5%; width: 84%; margin: 0 auto;">
                    <h4>Success! The report has been published.</h4>
                </div>
                <%} else if (savedMessage.equalsIgnoreCase("Error")) {%>
                <div class="callout callout-danger" style="margin-top: 5%; width: 84%; margin: 0 auto;">
                    <h4>The report was not published</h4>
                    <p>A problem was encountered while publishing your report</p>
                </div>
                <%}%>


                <!--Specific Report-->

                <%
                    if (null != request.getAttribute("ReportSearch")) {
                        String reportYear = (String) request.getAttribute("yearReport");
                        String reportTitle = (String) request.getAttribute("reportTitle");
                        String reportSector = (String) request.getAttribute("sectorReport");
                %>
                <input type="hidden" id="reportTitle" value="<%=reportTitle%>" />
                <input type="hidden" id="reportYear" value="<%=reportYear%>" />
                <input type="hidden" id="reportSector" value="<%=reportSector%>" />
                <% }%>
                <!--END Specific Report-->



                <!-- Content Header (Page header) -->
                <section class="content" style="min-height: 630px">
                    <div class="row">
                        <div style=" margin: 0 auto; display:block; text-align: center">
                            <div class="form-inline">
                                <div class="form-group">
                                    <select id="category" name="category" class="form-control" onchange="updateReport()">
                                        <option disabled selected>Choose Sector</option>
                                        <option value="Education">Education</option>
                                        <option value="Demographics">Demographics</option>
                                        <option value="Health">Health</option>
                                    </select>
                                    <select id="form_name" name="form_name" class="form-control" disabled onchange="updateYear()" style="width:800px">
                                        <option disabled selected>Choose Report</option>
                                    </select>
                                    <input style="width: 100px; border: solid; border-color: #d2d6de; border-width: thin" type="text" 
                                           class="form-control" onkeyup="updateButton()" disabled name="censusYear"  id="searchCensusYear" 
                                           placeholder="Census Year" />
                                    <button disabled id="button" type="button" class="btn btn-default" name="submitBtn" onClick="getData()"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>
                        </div>

                        <div id="integrateData" style="margin: 4% auto 0 auto; float:none; display:none;" >

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

                            <div class="col-md-12">
                                <div class="box box-solid">
                                    <div class="box-body">
                                        <button class="btn btn-primary btn-sm" style="float:right;" id="save_integrated">
                                            <span class="glyphicon glyphicon-save" style="margin-right: 2%"></span> Save as PDF</button>
                                        <div id="integrated__reports">
                                            <div id="container"  align="center" style="min-width: 310px; width: 600px; margin: 0 auto"></div>
                                            <div id="container2" align="center" style="min-width: 310px; width: 600px; margin: 0 auto"></div>
                                            <div id="container3" align="center" style="min-width: 310px; width: 600px; margin: 0 auto"></div>
                                            <div id="integratedanalysis" style="margin-left: 10%; margin-top: 5%;"></div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <!-- /.box -->
                            </div>
                            <!--End of Body-->
                        </div>

                        <div class="col-md-12" style="margin-top: 4%; display: none;" id="contentHere">
                            <div class="box box-solid" style="height:100%; ">
                                <div class="box-header with-border">
                                    <h3 class="box-title" id="reportTitle"></h3>
                                    <button class="btn btn-primary btn-sm" style="float:right;" id="save_reports">
                                        <span class="glyphicon glyphicon-save" style="margin-right: 2%"></span> Save as PDF</button>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <!--CHARTS, change id value-->
                                    <div id="info" style="width:90%;">
                                        <!--TABLE -->
                                    </div>

                                    <div id="content" style="width:90%; margin: 0 auto;">
                                        <!--CONTENTHERE -->
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </section>

                <% if (user.getDivision().equalsIgnoreCase("Social Development Planning Division")) {%>
            </div>
            <% }%>
        </div>

        <div style="display:none;" id="TESTING">

            <div style="margin-bottom: 6%;" align="center">
                <!--<img src="img/Caloocan-Logo.png" alt=""/>-->
                <table style="border: none;">
                    <tr>
                        <td>
                            <img src="http://i65.tinypic.com/uubsl.png" height="100" />
                        </td>
                        <td style="padding-left: 2%;">
                            <h3>City Planning and Development Department</h3>
                            <p id="prepared_by">Prepared By: Janine Dela Cruz</p>
                            <p id="print_year"></p>
                            <p id="DateHere">Retrieved on </p>
                        </td>
                    </tr>
                </table>
            </div>

            <!-- CHARTS, change id value -->
            <div id="print" style="width:90%;">

            </div>
            <!--TABLE-->

            <footer>
                <p style="text-align: right;"><pagenum/></p>
            </footer>
        </div>

        <script>

            $('#save_integrated').click(function () {
                print_div('integrated');

                var screenTop = $(document).scrollTop();
                var screenHeight = $(document).height();
                $('#spinner-overlay').css('top', screenTop);
                $('#spinner-overlay').css('height', screenHeight);
                $('#spinner-overlay').toggle('show');


                xepOnline.Formatter.Format('TESTING',
                        {pageWidth: '11in', pageHeight: '8.5in'},
                        {render: 'download'},
                        {embedLocalImages: 'true'});

                $('#spinner-overlay').toggle('hide');
                doneyet();
            });


            $('#save_reports').click(function () {
                print_div('reports');

                var screenTop = $(document).scrollTop();
                var screenHeight = $(document).height();
                $('#spinner-overlay').css('top', screenTop);
                $('#spinner-overlay').css('height', screenHeight);
                $('#spinner-overlay').toggle('show');

                xepOnline.Formatter.Format('TESTING',
                        {pageWidth: '11in', pageHeight: '8.5in'},
                        {render: 'download'},
                        {embedLocalImages: 'true'});

                $('#spinner-overlay').toggle('hide');
                doneyet();

            });

            var year;
            function updateReport() {
                var conceptName = $('#category').find(":selected").text();
                if (conceptName === "Demographics") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Matrix">Demographics Analysis Matrix</option>')
                            .append('<option value="Analysis">Demographics Analysis</option>')
                            .append('<option value="Integrated">Demographics Integrated Analysis</option>');

                } else if (conceptName === "Education") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Matrix">Education Analysis Matrix</option>')
                            .append('<option value="Analysis">Education Analysis</option>')
                            .append('<option value="Integrated">Education Integrated Analysis</option>');

                } else if (conceptName === "Health") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Matrix">Health Analysis Matrix</option>')
                            .append('<option value="Analysis">Health Analysis</option>')
                            .append('<option value="Integrated">Health Integrated Analysis</option>');
                }
                $('#form_name').removeAttr('disabled');
            }

            function updateYear() {
                document.getElementById('searchCensusYear').style.background = "#FFF";

                var conceptName = $('#form_name').find(":selected").val();
                if (conceptName == "Matrix") {
                    $('#searchCensusYear').keypress(searchMatrix());
                    $('#searchCensusYear').removeAttr('disabled');

                } else if (conceptName == "Analysis") {
                    $('#searchCensusYear').keypress(searchAnalysis());
                    $('#searchCensusYear').removeAttr('disabled');

                } else if (conceptName == "Integrated") {
                    $('#searchCensusYear').keypress(searchIntegrated());
                    $('#searchCensusYear').removeAttr('disabled');

                }
            }
            function getData() {
                year = $('#searchCensusYear').val();
                var conceptName = $('#form_name').find(":selected").val();
                if (conceptName === "Matrix") {
                    document.getElementById('integrateData').style.display = "none";
                    document.getElementById('contentHere').style.display = "block";
                    $('#submitBtn').click(setMatrix());
                } else if (conceptName === "Analysis") {
                    document.getElementById('integrateData').style.display = "none";
                    document.getElementById('contentHere').style.display = "block";
                    $('#submitBtn').click(setAnalysis());
                } else if (conceptName === "Integrated") {
                    document.getElementById('contentHere').style.display = "none";
                    document.getElementById('integrateData').style.display = "block";

                    $('#submitBtn').click(setIntegrated());
                }
            }

            function updateButton() {
                if ($('#searchCensusYear').val()) {
                    $('#button').removeClass('btn-default');
                    $('#button').addClass('btn-primary');
                    $("#button").prop('disabled', false);
                } else if (!$('#searchCensusYear').val()) {
                    $('#button').removeClass('btn-primary');
                    $('#button').addClass('btn-default');
                    $("#button").prop('disabled', true);
                }
            }

            function print_div(prints) {
                if (prints == "integrated") {
                    jQuery('#print').html(jQuery("#integrated__reports").html());
                } else if (prints == "reports") {
                    jQuery('#print').html(jQuery("#content").html());
                }
                var m_names = new Array("January", "February", "March",
                        "April", "May", "June", "July", "August", "September",
                        "October", "November", "December");

                var d = new Date();
                var curr_date = d.getDate();
                var curr_month = d.getMonth();
                var curr_year = d.getFullYear();
                var today = m_names[curr_month] + " " + curr_date
                        + ", " + curr_year;

                document.getElementById("TESTING").setAttribute("style", "display:block");
                $('#dataTable').DataTable().destroy(false);
                document.getElementById("print_year").innerHTML = "Report for the Year " + year;
                $("#DateHere").html("Retrieved on " + today);
            }

            function doneyet() {
                // document.body.onfocus = "";
                document.getElementById("TESTING").setAttribute("style", "display:none");
                $('#printTable').empty();
                $('#printChart').empty();
                var ary = [];
                $('#dataTable thead th').each(function () {
                    ary.push({
                        "orderDataType": "dom-text",
                        type: 'string'
                    });
                });
                $("#dataTable").DataTable({
                    "paging": true,
                    "ordering": true,
                    "info": false, "language": {
                        "empty2Table": "No Data"
                    },
                    "columns": ary
                });
            }
        </script>

    </body>
</html>
