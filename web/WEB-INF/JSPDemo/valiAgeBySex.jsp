<%--
    Document   : valiAgeBySex
    Created on : Jun 18, 2016, 7:01:18 PM
    Author     : shermainesy
--%>

<%@page import="model.demo.ByAgeGroupSex"%>
<%@page import="model.temp.demo.ByAgeGroupTemp"%>
<%@page import="java.util.ArrayList"%>
<%@include file="../levelOfAccess.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Preview Upload | Household Population by Age Group and Sex</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>
        <!--other Imports-->
        <script src="jsDataTables_config/demo_ageGroup.js" type="text/javascript"></script>
        <script src="jsImported/ePreventEnter.js" type="text/javascript"></script>
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <script src="jsDemoImports/byAgeGroupSexUpdate.js" type="text/javascript"></script>

        <!--Pace Imports-->
        <script src="Pace/pace.js"></script>
        <link href="Pace/dataurl.css" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper hidden-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <%                            String redirect = (String) request.getAttribute("page");
                            if (redirect.equalsIgnoreCase("upload")) {
                        %>
                        <div class="box-header with-border" style="margin-bottom: 2%;">
                            <center>
                                <h1 class="box-title" style="margin-right: 1%;"><b>Preview of Household Population by Age Group and Sex for </b></h1>
                                <h1 class="box-title" id="text_year"></h1>
                            </center>
                        </div>
                        <%} else {%>
                        <div class="box-header with-border" style="margin-bottom: 2%;">
                            <center>
                                <h1 class="box-title" style="margin-right: 1%;"><b>Edit Saved Report | Household Population by Age Group and Sex for </b></h1>
                                <h1 class="box-title" id="text_year"></h1>
                            </center>
                        </div>
                        <%}%>
                        <%
                            String temp = (String) request.getAttribute("ErrorMessage");
                            if (temp.equalsIgnoreCase("Error")) {
                        %>
                        <div class="box-header with-border" style="margin-bottom: 2%; width: 90%; margin-left: auto; margin-right: auto;">
                            <div class="callout callout-danger">
                                <h4>There are errors.</h4>

                                <p>Kindly head onto the cells highlighted in red to see what's wrong.</p>
                            </div>
                        </div>
                        <% } %>
                        <div>
                            <form id="UploadDb" action="ValiAgeByGrpServ" method="post">

                                <%     ArrayList<ByAgeGroupTemp> byAgeGroupError = (ArrayList<ByAgeGroupTemp>) request.getAttribute("ArrError");

                                    if (byAgeGroupError != null) {%>

                                <div class="box-body">
                                    <div id="errorsDiv" class="dataTable-width">
                                        <table id="error-ageGroup" class="table table-bordered table-striped dataTable">
                                            <thead class="heading">
                                                <tr>
                                                    <th>Location</th>
                                                    <th>Age Group</th>
                                                    <th class="centerTD">Both Sex</th>
                                                    <th class="centerTD">Male Count</th>
                                                    <th class="centerTD">Female Count</th>
                                                </tr>
                                            </thead>
                                            <tbody class="whitebg">
                                                <%for (int i = 0; i < byAgeGroupError.size(); i++) {%>
                                                <tr>
                                                    <td><input name="locationError" type="text" value="<%=byAgeGroupError.get(i).getBarangay()%>" readonly/></td>
                                                    <td><input name="ageGroupError" type="text" value="<%=byAgeGroupError.get(i).getAgeGroup()%>" readonly /></td>
                                                    <td class='bothSexesError' ><input  class="centerTD" name="bothSexesError" id="bothSexesError" type="text" value=" <%=byAgeGroupError.get(i).getBothSex()%> "  readonly/></td>
                                                    <td class='maleSexesError' ><input  class="centerTD" name="maleError" id="maleSexesError" type="text" onkeypress="return event.charCode >= 48 && event.charCode <= 57" value=" <%=byAgeGroupError.get(i).getMaleCount()%> "  /></td>
                                                    <td class='femaleSexesError'><input class="centerTD" name="femaleError" id="femaleSexesError" type="text" onkeypress="return event.charCode >= 48 && event.charCode <= 57" value=" <%=byAgeGroupError.get(i).getFemaleCount()%> "  /></td>
                                                </tr>
                                                <%}%>
                                            </tbody>

                                        </table>
                                        <input name="errorMessage" type="hidden" value="<%=temp%>" />

                                    </div>

                                    <div align="center">
                                        <a onclick="print_div()"><input align="center" class="btn btn-success" style="margin: 1% auto 5% auto" type="button" value="Print Error Summary"/></a>
                                    </div>


                                    <%}
                                        ArrayList<ByAgeGroupSex> ByAgeGroupSex = (ArrayList<ByAgeGroupSex>) request.getAttribute("ArrNoError"); %>
                                    <div class="dataTable-width">
                                        <table id="dataTable-ageGroup" class="table table-bordered table-striped dataTable">
                                            <thead class="heading">
                                                <tr>
                                                    <th>Location</th>
                                                    <th>Age Group</th>
                                                    <th class="centerTD">Both Sex</th>
                                                    <th class="centerTD">Male Count</th>
                                                    <th class="centerTD">Female Count</th>
                                                </tr>
                                            </thead>
                                            <tbody class="whitebg">
                                                <% for (int i = 0; i < ByAgeGroupSex.size(); i++) {%>
                                                <tr>
                                                    <td><input name="location" type="text" value="<%=ByAgeGroupSex.get(i).getBarangay()%>" readonly /></td>
                                                    <td><input name="ageGroup" type="text" value="<%=ByAgeGroupSex.get(i).getAgeGroup()%>" readonly /></td>
                                                    <td><input class="centerTD" name="bothSexes" type="text" value="<%=ByAgeGroupSex.get(i).getFormatcount(ByAgeGroupSex.get(i).getBothSex())%>" readonly /></td>
                                                    <td><input class="centerTD" name="male" type="text" value="<%=ByAgeGroupSex.get(i).getFormatcount(ByAgeGroupSex.get(i).getMaleCount())%>" readonly /></td>
                                                    <td><input class="centerTD" name="female" type="text" value="<%=ByAgeGroupSex.get(i).getFormatcount(ByAgeGroupSex.get(i).getFemaleCount())%>" readonly /></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>

                                        <input type="hidden" name="year" class="year" id="year" />
                                        <div style="margin-top: 5%;">
                                            <div style="display:inline; float:left;">
                                                <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=byAgeGroupSex">
                                                    <input type="button" class="btn btn-block btn-default" style="width: 100px;" value='Back'>
                                                </a></div>
                                            <div  style="display:inline; float:right;">
                                                <input class="btn btn-block btn-success" onclick="submitForm()" type="button" value="Submit" /></div>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="page" value="<%=redirect%>"/>
                                <input type="hidden" name="uploadedBy" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="errorMessage" value="<%=temp%>"/>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <div class="visible-print">

            <div style="margin-bottom: 6%;" align="center">
                <img src="img/Caloocan-Logo.png" alt=""/><br>
                <h4>City Planning and Development Department</h4>
                <p>Household Population By Age Group and Sex<br>
                    Errors Summary Report for 2016<br>
                    Prepared By: <%= user.getFirstName()%> <%= user.getLastName()%></p>
                <p id="DateHere"></p>
            </div>
            <!--TABLE-->
            <p style="margin-left: 5%;">Please provide the correct inputs for the missing and incorrect fields as seen in the table below.</p>
            <div id="printTable" style="width: 90%; margin-left: auto; margin-right: auto;">
            </div>
            <footer>
                <div style='text-align:center;
                     position:fixed;
                     height:50px;
                     background-color:red;
                     bottom:0px;
                     left:0px;
                     right:0px;
                     margin-bottom:0px;'>Page 1
                    <!--<span class="pageCounter"></span>/<span class="totalPages"></span>-->
                </div>
            </footer>
        </div>

        <script>
            function submitForm() {

                $('#dataTable-ageGroup').DataTable().destroy(false);
                $('#UploadDb').submit();
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

                $('#error-ageGroup').DataTable().destroy(false);
                $("#DateHere").html(today);
                jQuery('#printTable').html(jQuery("#errorsDiv").html());
                window.print();
                document.body.onfocus = doneyet();
            }
            function doneyet() {
                document.body.onfocus = "";
                $('#printTable').empty();
                var ary = [];
                $('#error-ageGroup thead th').each(function () {
                    ary.push({
                        "orderDataType": "dom-text",
                        type: 'string'
                    });
                });
                $("#error-ageGroup").DataTable({
                    "paging": false,
                    "ordering": true,
                    "bFilter": false,
                    "info": false, "language": {
                        "empty2Table": "No Data"
                    },
                    "columns": ary
                });
            }

            var d = new Date();
            var n = d.getFullYear();
            document.getElementById('text_year').innerHTML = "<b>" + n + "</b>";
        </script>

    </body>
</html>
