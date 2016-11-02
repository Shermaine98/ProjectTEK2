<%--
    Document   : valiMaritalStatus
    Created on : Jun 18, 2016, 7:01:18 PM
    Author     : shermainesy
--%>

<%@page import="model.demo.MaritalStatus"%>
<%@page import="model.temp.demo.MaritalStatusTemp"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Preview Upload | Household Population 10 years old & over by Age group, Sex and Marital Status</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>
        <script src="jsDataTables_config/demo_maritalStatus.js" type="text/javascript"></script>
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <script src="jsDemoImports/maritalStatusUpdate.js" type="text/javascript"></script>

        <!--Pace Imports-->
        <script src="Pace/pace.js"></script>
        <link href="Pace/dataurl.css" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper hidden-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <%
                            String redirect = (String) request.getAttribute("page");
                            if (redirect.equalsIgnoreCase("upload")) {
                        %>
                        <div class="box-header with-border">
                            <center>
                                <h1 class="box-title" style="margin-right: 1%;"><b>Preview of Household Population 10 years old & over by Age Group, Sex and Marital Status for </b></h1>
                                <h1 class="box-title" id="text_year"></h1>
                            </center>
                        </div>
                        <%} else {%>
                        <div class="box-header with-border">
                            <center>
                                <h1 class="box-title" style="margin-right: 1%;"><b>Edit Saved Report |Household Population 10 years old & over by Age Group, Sex and Marital Status for </b></h1>
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
                            <form id="uploadDB" action="ValiMaritalStatus" method="post">
                                <%
                                    ArrayList<MaritalStatusTemp> error = (ArrayList<MaritalStatusTemp>) request.getAttribute("ArrError");
                                    if (error != null) {
                                %>
                                <div class="box-body">
                                    <div class="dataTable-width" style="width: 95%;" id="errorsDiv">
                                        <table id="error_maritalStatus" class="table table-bordered table-striped dataTable">
                                            <thead class="heading">
                                                <tr>
                                                    <th>Location</th>
                                                    <th>Sex</th>
                                                    <th>Age Group</th>
                                                    <th class="centerTD">Single</th>
                                                    <th class="centerTD">Married</th>
                                                    <th class="centerTD">Widowed</th>
                                                    <th class="centerTD">Divorced/<br>Separated</th>
                                                    <th class="centerTD">Common Law/ Live-in</th>
                                                    <th class="centerTD">Unknown</th>
                                                    <th class="centerTD">Total</th>
                                                    <th class="centerTD">Reason</th>
                                                </tr>
                                            </thead>
                                            <tbody class="whitebg">
                                                <%
                                                    for (int i = 0; i < error.size(); i++) {

                                                %>
                                                <tr>
                                                    <td width="13%"><input name="locationError" type="text" value="<%=error.get(i).getLocation()%>" /></td>
                                                    <td width="10%"><input name="sexError" type="text" value="<%=error.get(i).getSex()%>" /></td>
                                                    <td width="10%"><input name="ageGroupError" type="text" value="<%=error.get(i).getAgeGroup()%>" /></td>
                                                    <td width="5%" class="singleError"><input class="centerTD" id="singleError" type="text" name="singleError" value="<%=error.get(i).getSingle()%>"   onkeypress="return event.charCode >= 48 && event.charCode <= 57"/></td>
                                                    <td width="5%" class="marriedError"><input class="centerTD" id="marriedError" type="text" name="marriedError" value="<%=error.get(i).getMarried()%>"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" /></td>
                                                    <td width="5%" class="windowedError"><input class="centerTD" id="windowedError" type="text" name="windowedError" value="<%=error.get(i).getWidowed()%>"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" /></td>
                                                    <td width="5%" class="divorcedSeparatedError"><input class="centerTD" id="divorcedSeparatedError" type="text" name="divorcedSeparatedError" value="<%=error.get(i).getDivorcedSeparated()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" /></td>
                                                    <td width="5%" class="commonLawLiveInError"><input class="centerTD" id="commonLawLiveInError" type="text" name="commonLawLiveInError" value="<%=error.get(i).getCommonLawLiveIn()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" /></td>
                                                    <td width="5%" class="unknownError"><input class="centerTD" id="unknownError" type="text" name="unknownError" value="<%=error.get(i).getUnknown()%>"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" /></td>
                                                    <td width="5%" class="totalError"><input  class="centerTD" id="totalError" type="text" name="totalError" value="<%=error.get(i).getTotal()%>"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" /></td>
                                                    <td class='reasonNum'><input type="hidden" name="errorReason" value="<%=error.get(i).getValidation()%>"/>
                                                        <input type="text" readonly id="reasonNum"  value="<%=error.get(i).getErrorReason()%>"/></td>
                                                </tr>
                                                <%

                                                    }
                                                %>
                                            </tbody>

                                        </table>
                                        <input name="errorMessage" type="hidden" value="<%=temp%>" />

                                    </div>
                                    <div align="center">
                                        <input align="center" class="btn btn-flat btn-success" style="margin: 1% auto 5% auto" onClick="print_div()" type="button" value="Print Error Summary"/>
                                    </div>
                                    <%
                                        }
                                        ArrayList<MaritalStatus> noError = (ArrayList<MaritalStatus>) request.getAttribute("ArrNoError");
                                    %>
                                    <div class="dataTable-width" style="width: 95%;" >
                                        <table id="marital_status" class="table table-bordered table-striped dataTable">
                                            <thead class="heading">
                                                <tr>
                                                    <th>Location</th>
                                                    <th>Sex</th>
                                                    <th>Age Group</th>
                                                    <th class="centerTD">Single</th>
                                                    <th class="centerTD">Married</th>
                                                    <th class="centerTD">Widowed</th>
                                                    <th class="centerTD">Divorced/<br>Separated</th>
                                                    <th class="centerTD">Common Law/ Live-in</th>
                                                    <th class="centerTD">Unknown</th>
                                                    <th class="centerTD">Total</th>
                                                </tr>
                                            </thead>
                                            <tbody class="whitebg">
                                                <%
                                                    for (int i = 0; i < noError.size(); i++) {

                                                %>
                                                <tr>
                                                    <td width="13%"><input type="text" name="location" value="<%=noError.get(i).getLocation()%>" readonly /></td>
                                                    <td width="10%"><input type="text" name="sex" value="<%=noError.get(i).getSex()%>" readonly /></td>
                                                    <td width="10%"><input type="text" name="ageGroup" value="<%=noError.get(i).getAgeGroup()%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="single" value="<%=noError.get(i).getFormatcount(noError.get(i).getSingle())%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="married" value="<%=noError.get(i).getFormatcount(noError.get(i).getMarried())%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="windowed" value="<%=noError.get(i).getFormatcount(noError.get(i).getWidowed())%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="divorcedSeparated" value="<%=noError.get(i).getFormatcount(noError.get(i).getDivorcedSeparated())%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="commonLawLiveIn" value="<%=noError.get(i).getFormatcount(noError.get(i).getCommonLawLiveIn())%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="unknown" value="<%=noError.get(i).getFormatcount(noError.get(i).getUnknown())%>" readonly /></td>
                                                    <td width="5%"><input type="text" class="centerTD" name="total" value="<%=noError.get(i).getFormatcount(noError.get(i).getTotal())%>" readonly /></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>

                                    <input type="hidden" name="year" class="year" id="year" />
                                    <div style="width:96%; margin-left: 2%; margin-top: 5%;">
                                        <div style="display:inline; float:left;">
                                            <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=MaritalStatus">
                                                <input type="button" class="btn btn-block btn-default" style="width: 100px;" value='Back'>
                                            </a></div>
                                        <div  style="display:inline; float:right;">
                                            <input class="btn btn-block btn-success" type="button" onclick="submitForm()" value="Submit" /></div>
                                    </div>
                                </div>
                                <input type="hidden" name="uploadedBy" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="page" value="<%=redirect%>"/>
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
                <p>Household Population By Age Group, Sex, and Marital Status<br>
                    Errors Summary Report for 2016<br>
                    Prepared By: <%= user.getFirstName()%> <%= user.getLastName()%></p>
                <p id="DateHere"></p>
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
            <!--TABLE-->
            <p style="margin-left: 5%;">Please provide the correct inputs for the missing and incorrect fields as seen in the table below.</p>
            <div id="printTable" style="width: 90%; margin-left: auto; margin-right: auto;">

            </div>
        </div>

        <script>
            function submitForm() {

                $('#marital_status').DataTable().destroy(false);
                $('#uploadDB').submit();
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

                $('#error_maritalStatus').DataTable().destroy(false);
                $("#DateHere").html(today);
                jQuery('#printTable').html(jQuery("#errorsDiv").html());
                window.print();
//                document.body.onfocus = doneyet();
            }

            var d = new Date();
            var n = d.getFullYear();
            document.getElementById('text_year').innerHTML = "<b>" + n + "</b>";
        </script>
    </body>
</html>
