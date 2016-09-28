<%--
    Document   : reportLibrary
    Created on : 07 14, 16, 10:46:44 PM
    Author     : Geraldine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>
        <script src="jsImported/jquery.bootstrap.wizard.min.js" type="text/javascript"></script>
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsDemoImports/searchCensusYearAge.js" type="text/javascript"></script>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
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
        </style>

        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class ="wrapper hidden-print">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <section class="content-header" style="margin-bottom: 1%;">
                    <h1><i class="fa fa-book"></i> Internal Reports Library</h1>
                </section>
                <!-- Content Header (Page header) -->
                <section class="content">
                    <div class="row">
                        <%                        String reportAction = (String) request.getAttribute("reportSet");
                        %>
                        <input id="reportSet" type="hidden" value="<%=reportAction%>" />
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
                                    <input style="width: 100px; background: #fff;" type="text" class="form-control" onkeyup="updateButton()" name="censusYear" disabled id="searchCensusYear" placeholder="Census Year" />
                                    <button disabled id="button" type="button" class="btn btn-default" name="submitBtn" onClick="getData()"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-top: 4%; display:none;" id="contentHere">
                            <div class="box box-solid" style="height:100%; ">
                                <div class="box-header with-border">
                                    <h3 class="box-title" id="reportTitle"></h3>
                                    <!--<a href="javascript:window.print();">-->
                                    <button class="btn btn-primary btn-sm" style="float:right;" onclick="print_div()"><span class="fa fa-print" style="margin-right: 2%"></span> Print</button>
                                    <!--</a>-->
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <!--CHARTS, change id value-->
                                    <center>
                                        <div id="byAgeGrpSex" style="width:90%;">
                                            <center><img id="loadingSpinner" src="img/spinner.gif" style="width:3%; height:3%; margin-top:10%;"><div class="col-md-12"></center>
                                        </div>
                                    </center>
                                    <!--TABLE-->
                                    <div id="TableHolder" style="margin-top:3%; width: 90%; margin-left: auto; margin-right: auto;">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <div class="visible-print" id="TESTING">

            <div style="margin-bottom: 6%;" align="center">
                <img src="img/Caloocan-Logo.png" alt=""/><br>
                <h3>City Planning and Development Department</h3>
                <p style="font-size: medium; " id="prepared_by">Prepared By: Janine Dela Cruz</p>
                <p style="font-size: medium; " id="print_year"></p>
                <p id="DateHere"></p>

            </div>

            <!-- CHARTS, change id value -->  
            <div id="printChart" style="width:90%;">
            </div>
            <!--TABLE-->            
            <div id="printTable" style="margin-top:3%; width: 90%; margin-left: auto; margin-right: auto;">

            </div>
        </div>

        <script>
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
                    $('#searchCensusYear').keypress(autoPublicEnrollment());
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
                $('#button').removeAttr('disabled');
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

                $('#dataTable').DataTable().destroy(false);
                document.getElementById("print_year").innerHTML = "Report for the Year " + year;
                $("#DateHere").html(today);
                jQuery('#printTable').html(jQuery("#TableHolder").html());
                jQuery('#printChart').html(jQuery("#byAgeGrpSex").html());

                window.print();
                document.body.onfocus = doneyet();
            }
            function doneyet() {
                document.body.onfocus = "";
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