<%--
    Document   : reportLibrary
    Created on : 07 14, 16, 10:46:44 PM
    Author     :  Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="levelOfAccess.jsp"%>
<%@include file="JSPViewModal/notifcationModal.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>
        <script src="jsImported/jquery.bootstrap.wizard.min.js" type="text/javascript"></script>
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsDemoImports/searchCensusYearAge.js" type="text/javascript"></script>
        <script src="jsReport/specificViewInternalLibrary.js" type="text/javascript"></script>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="pdf/xepOnline.jqPlugin.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reports Library</title>
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

            #loadingSpinner{
                background: #666666;
                display: none;
                position: absolute;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                z-index: 99;
                opacity: 0.3;
            }

            .blur{
                -webkit-filter: blur(5px);
                -moz-filter: blur(5px);
                -o-filter: blur(5px);
                -ms-filter: blur(5px);
                filter: blur(5px);
            }

            #spinnerIMG{
                width: 140px; height: 140px;
                margin-top: 25%; z-index: 3;
            }
        </style>

        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="spinner-overlay" style="display:none;">
            <center><img src="img/spinner.gif" style="margin-top: 19%; margin-left: 17%; height: 150px;" /></center>
        </div>
        <div class="wrapper" style="z-index:1;">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content-header" style="margin-bottom: 1%;">
                    <h1><i class="fa fa-book"></i> Internal Reports Library</h1>
                </section>
                <!-- Content Header (Page header) -->
                <section class="content">
                    <div class="row">
                        <%                            if (null != request.getAttribute("reportSet")) {
                                String reportAction = (String) request.getAttribute("reportSet");
                                if (reportAction.equalsIgnoreCase("schoolDirectory") || reportAction.equalsIgnoreCase("Enrollment")) {
                                    String c = (String) request.getAttribute("classification");

                        %>
                        <input id="classificationSpecific" type="hidden" value="<%=c%>" />
                        <%}%>
                        <input id="reportSet" type="hidden" value="<%=reportAction%>" />
                        <%}%>

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
                                           class="form-control" onkeyup="updateButton()" name="censusYear" disabled
                                           id="searchCensusYear" placeholder="Census Year" />
                                    <button disabled id="button" type="button" class="btn btn-default" name="submitBtn" onClick="getData()"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-top: 4%; display:none;" id="contentHere">
                            <div class="box box-solid" style="height:100%; ">
                                <div class="box-header with-border">
                                    <h3 class="box-title" id="reportTitle"></h3>
                                    <button class="btn btn-primary btn-sm" style="float:right;" id="save_pdf">
                                        <span class="glyphicon glyphicon-save" style="margin-right: 2%"></span> Save as PDF</button>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <center>
                                        <div id="loadingSpinner">
                                            <img id="spinnerIMG" src="img/spinner.gif" />
                                        </div>
                                        <div id="byAgeGrpSex" style="width:80%;">
                                        </div>
                                        <!--TABLE-->
                                        <div id="TableHolder" style="margin-top:3%; width: 90%; margin-left: auto; margin-right: auto;">
                                        </div>
                                    </center>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-10" style="margin: 4% 200px 0 100px; display: none; height: 100px;" id="noReport">
                            <div class="box box-danger" style="height:100%; ">
                                <div class="box-body">
                                    <div id="contentNone" style="width:90%; margin: 3% auto; text-align: center;">
                                        <!--CONTENTHERE -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <div id="TESTING" style="display:none;">
            <div style="margin-bottom: 6%;" align="center">
                <!--<img src="img/Caloocan-Logo.png" alt=""/>-->
                <table style="border: none;">
                    <tr>
                        <td>
                            <img id="imageLogo" alt="Caloocan City" height="100" />
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
            <div id="printChart">
            </div>
            <!--TABLE-->
            <div id="printTable" style="margin-top:3%; width: 90%; margin-left: auto; margin-right: auto;">

            </div>

            <footer>
                <p style="text-align: right;"><pagenum/></p>
            </footer>
        </div>
        <script>

            $(document).ready(function () {
                image("index_template/Ph_seal_ncr_caloocan.png", function (dataURL) {
                    document.getElementById("imageLogo").src = dataURL;

                });
                function image(imageUri, callback) {
                    var c = document.createElement('canvas');
                    var ctx = c.getContext("2d");
                    var img = new Image();
                    img.onload = function () {
                        c.width = this.width;
                        c.height = this.height;
                        ctx.drawImage(img, 0, 0);
                        var dataURL = c.toDataURL("image/png");
                        callback(dataURL);

                    };
                    img.src = imageUri;
                }
            });

            $('#save_pdf').click(function () {
                print_div();

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

            var year = 0;
            function updateReport() {
                var conceptName = $('#category').find(":selected").text();
                if (conceptName == "Demographics") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="AgeGroup">Household Population By Age Group and Sex</option>')
                            .append('<option value="HighestCompleted">Household Population 5 Yrs Old and Over by Highest Grade/Year Completed, Age Group and Sex</option>')
                            .append('<option value="MaritalStatus">Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status</option>')
                            //                            .val('whatever')
                            ;
                } else if (conceptName == "Education") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="ePublic">Enrollment in Public Schools</option>')
                            .append('<option value="ePrivate">Enrollment in Private Schools</option>')
                            .append('<option value="publicDirectory">Number of Teachers and Classrooms for Public Schools</option>')
                            .append('<option value="privateDirectory">Number of Teachers and Classrooms for Private Schools</option>')
                            //                            .val('whatever')
                            ;
                } else if (conceptName == "Health") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Nutritional">Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender</option>')
                            .append('<option value="ListOfHospitals">List of Hospitals</option>')
                            //                            .val('whatever')
                            ;
                }
                $('#form_name').removeAttr('disabled');
            }
            function updateYear() {
                document.getElementById('searchCensusYear').style.background = "#FFF";

                var conceptName = $('#form_name').find(":selected").text();
                if (conceptName == "Household Population By Age Group and Sex") {
                    $('#searchCensusYear').keypress(autoCompleteAgeGroup());
                    $('#searchCensusYear').removeAttr('disabled');
                } else if (conceptName == "Household Population 5 Yrs Old and Over by Highest Grade/Year Completed, Age Group and Sex") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoCompleteHighestAttainment());
                } else if (conceptName == "Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoCompleteMarital());
                } else if (conceptName == "Enrollment in Public Schools") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoPublicEnrollment());
                } else if (conceptName == "Enrollment in Private Schools") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoCompletePrivateEnrollment());
                } else if (conceptName == "Number of Teachers and Classrooms for Public Schools") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoPublicClassroom());
                } else if (conceptName == "Number of Teachers and Classrooms for Private Schools") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoCompletePrivateClassroom());
                } else if (conceptName == "Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoNutritionalStatus());
                } else if (conceptName == "List of Hospitals") {
                    $('#searchCensusYear').removeAttr('disabled');
                    $('#searchCensusYear').keypress(autoCompleteHospitalDirectory());
                }
            }
            function getData() {
                $("#loadingSpinner").show();
                year = $('#searchCensusYear').val();
                var conceptName = $('#form_name').find(":selected").text();
                if (conceptName == "Household Population By Age Group and Sex") {
                    $('#submitBtn').click(getDataAgeGroup());
                } else if (conceptName == "Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status") {
                    $('#submitBtn').click(getMaritalStatusData());
                } else if (conceptName == "Number of Teachers and Classrooms for Private Schools" || conceptName == "Number of Teachers and Classrooms for Public Schools") {
                    $('#submitBtn').click(getSchoolData());
                } else if (conceptName == "List of Hospitals") {
                    $('#submitBtn').click(getHospitalData());
                } else if (conceptName == "Enrollment in Private Schools" || conceptName == "Enrollment in Public Schools") {
                    $('#submitBtn').click(getEnrollmentData());
                } else if (conceptName == "Household Population 5 Yrs Old and Over by Highest Grade/Year Completed, Age Group and Sex") {
                    $('#submitBtn').click(getHighestCompleted());
                } else if (conceptName == "Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender") {
                    $('#submitBtn').click(getNutritionalStatus());
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

            function print_div() {

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
                $("#DateHere").html("Retrieved on " + today);
                jQuery('#printChart').html(jQuery("#byAgeGrpSex").html());
                jQuery('#printTable').html(jQuery("#TableHolder").html());


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
                    "ordering": false,
                    "info": false, "language": {
                        "empty2Table": "No Data"
                    },
                    "columns": ary
                });
            }
        </script>

    </body>
</html>
