<%--
    Document   : valiHighestAttaintment
    Created on : Jun 18, 2016, 7:01:18 PM
    Author     : Roxas, Atayan and Sy
--%>

<%@page import="model.temp.demo.HighestCompletedTemp"%>
<%@page import="model.demo.HighestCompleted"%>
<%@page import="model.temp.demo.HighestCompletedAgeGroupTemp"%>
<%@page import="java.util.ArrayList"%>
<%@include file="../levelOfAccess.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Preview Upload | Highest Attainment</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>

        <!--PREVENT ENTER KEY TO SUBMIT FORM-->
        <script src="jsImported/ePreventEnter.js" type="text/javascript"></script>
        <!--GET YEAR FOR OF SUBMISSION-->
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <!--UPDATING THE FILE/ ERROR-->
        <script src="jsDemoImports/highestUpdate.js" type="text/javascript"></script>

        <!--Pace Imports-->
        <script src="Pace/pace.js"></script>
        <link href="Pace/dataurl.css" rel="stylesheet" />

    </head>
    <body>
        <div class="wrapper no-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <!--THIS IS FOR THE PAGE TITLE-->
                        <%                            String redirect = (String) request.getAttribute("page");
                            if (redirect.equalsIgnoreCase("upload")) {
                        %>
                        <div class="box-header with-border">
                            <center>
                                <h1 class="box-title" style="margin-right: 1%;"><b>Preview of Household Population 5 Years Old and Over by Highest Grade/Year Completed, Age Group, and Sex for </b></h1>
                                <h1 class="box-title" id="text_year"></h1>
                            </center>
                            <br>
                        </div>
                        <% } else if (redirect.equalsIgnoreCase("edited")) {%>
                        <div class="box-header with-border">
                            <center>
                                <h1 class="box-title" style="margin-right: 1%;"><b>Edit Saved Report | Household Population 5 Years Old and Over by Highest Grade/Year Completed, Age Group, and Sex for </b></h1>
                                <h1 class="box-title" id="text_year"></h1>
                                <br>
                            </center>
                        </div>
                        <%}%>

                        <!--END FOR THE PAGE TITLE-->

                        <!--THIS IS FOR THE PAGE RESULT-->
                        <%
                            String temp = (String) request.getAttribute("ErrorMessage");
                            if (temp.equalsIgnoreCase("Error")) {
                        %>
                        <div class="callout callout-danger" style="margin-bottom: 2%; width: 90%; margin-left: auto; margin-right: auto;">
                            <h4>There are errors.</h4>

                            <p>Kindly head onto the cells highlighted in red to see what's wrong.</p>
                        </div>
                        <%}%>
                        <!--END FOR THE PAGE RESULT-->


                        <!--THIS IS FOR THE PAGE RESULT DATA WITH ERROR-->
                        <form action="ValiHighestAttaintment" method="post">
                            <input type="hidden" name="year" class="year" id="year" />
                            <input type="hidden" name="uploadedBy" value="<%= user.getUserID()%>" />
                            <input type="hidden" name="page" value="<%=redirect%>"/>
                            <input type="hidden" name="errorMessage" value="<%=temp%>"/>
                            <%
                                ArrayList<HighestCompletedTemp> highestError = (ArrayList<HighestCompletedTemp>) request.getAttribute("ArrError");
                                if (highestError != null) {
                            %>
                            <div class="DT" id="errorsDiv">
                                <table class="table table-bordered">
                                    <tbody>
                                        <% for (int i = 0; i < highestError.size(); i++) {%>
                                        <tr style="background-color: #454545; color: #fff">
                                            <th>Location</th>
                                            <td colspan="3"><input type="text" name="locationError" readonly value="<%=highestError.get(i).getLocation()%>" /></td>
                                        </tr>
                                        <tr style="background-color: #454545; color: #fff">
                                            <th>Sex</th>
                                            <td><input type="text" name="sexError" readonly value="<%=highestError.get(i).getSex()%>" /></td>
                                            <th>Age Group</th>
                                            <td><input type="text" name="ageGroupError" readonly value="<%=highestError.get(i).getAgeGroup()%>" /></td>
                                        </tr>
                                        <tr>
                                            <th>Highest Grade Completed</th>
                                            <th class="centerTD">Total Count</th>
                                            <th>Highest Grade Completed</th>
                                            <th class="centerTD">Total Count</th>
                                        </tr>
                                        <%for (int y = 0; y < highestError.get(i).getHighestCompletedAgeGroupTemp().size(); y += 2) {%>
                                        <tr>
                                            <th class='errorH' ><input type="text"   name="highestCompletedError" readonly value="<%= highestError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment()%>"/></th>
                                            <td class='errorH'><input type="text" class="errorHI centerTD" name="countError"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" value="<%=highestError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()%>"/></td>
                                            <th class='errorH'><input type="text" name="highestCompletedError" readonly value="<%= highestError.get(i).getHighestCompletedAgeGroupTemp().get(y + 1).gethighestAttaintment()%>"/></th>
                                            <td class='errorH'><input type="text" class="errorHI centerTD" name="countError"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" value="<%=highestError.get(i).getHighestCompletedAgeGroupTemp().get(y + 1).getCount()%>"/></td>
                                        </tr>
                                        <% }%>
                                        <tr>
                                            <th></th>
                                            <th></th>
                                            <th>Total</th>
                                            <td class='Errototal'><input type="text" class="ErrortotalI centerTD" name="totalError" style="float:right;" readonly value="<%=highestError.get(i).getTotal()%>" /></td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                            </div>
                            <div align="center">
                                <a href="javascript:window.print()"><input align="center" class="btn btn-success" style="margin: 1% auto 5% auto" type="button" value="Print Error Summary"/></a>
                            </div>

                            <!--END IS FOR THE PAGE RESULT DATA WITH ERROR-->
                            <!--THIS IS FOR THE PAGE RESULT FOR DATA WITH NO ERROR-->
                            <%}
                                ArrayList<HighestCompleted> highest = (ArrayList<HighestCompleted>) request.getAttribute("ArrNoError"); %>
                            <div class="DT">
                                <table class="table table-bordered">
                                    <tbody>
                                        <% for (int i = 0; i < highest.size(); i++) {%>
                                        <tr style="background-color: #454545; color: #fff">
                                            <th>Location</th>
                                            <td colspan="3"><input type="text" name="location" readonly value="<%=highest.get(i).getLocation()%>" /></td>
                                        </tr>
                                        <tr style="background-color: #454545; color: #fff">
                                            <th>Sex</th>
                                            <td><input type="text" name="sex" readonly value="<%=highest.get(i).getSex()%>" /></td>
                                            <th>Age Group</th>
                                            <td><input type="text" name="ageGroup" readonly value="<%=highest.get(i).getageGroup()%>" /></td>
                                        </tr>
                                        <tr>
                                            <th>Highest Grade Completed</th>
                                            <th class="centerTD">Total Count</th>
                                            <th>Highest Grade Completed</th>
                                            <th class="centerTD">Total Count</th>
                                        </tr>
                                        <%for (int y = 0; y < highest.get(i).getHighestCompletedAgeGroup().size(); y += 2) {%>

                                        <tr>
                                            <th><input type="text" name="highestCompleted" readonly value="<%= highest.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted()%>"/></th>
                                            <td><input type="text" class="centerTD" name="count" readonly value="<%= highest.get(i).getFormatCount(highest.get(i).getHighestCompletedAgeGroup().get(y).getCount())%>"/></td>
                                            <th><input type="text" name="highestCompleted" readonly value="<%= highest.get(i).getHighestCompletedAgeGroup().get(y + 1).gethighestCompleted()%>"/></th>
                                            <td><input type="text" class="centerTD" name="count" readonly value="<%=highest.get(i).getFormatCount(highest.get(i).getHighestCompletedAgeGroup().get(y + 1).getCount())%>"/></td>
                                        </tr>
                                        <% }%>

                                        <tr>
                                            <th></th>
                                            <th></th>
                                            <th>Total</th>
                                            <td><input type="text" class="centerTD" name="total" style="float:right;" readonly value="<%=highest.get(i).getFormatCount(highest.get(i).getTotal())%>" /></td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                                <!--THIS IS FOR THE BACK AND SUBMIT BUTTON-->

                                <div style="margin-top: 5%;">
                                    <div style="display:inline; float:left;">
                                        <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=HighestCompleted">
                                            <input type="button" class="btn btn-block btn-default" style="width: 100px;" value='Back'>
                                        </a></div>
                                    <div  style="display:inline; float:right;">
                                        <input  class="btn btn-block btn-success" type="Submit" value="Submit" /></div>
                                </div>
                                <!--END FOR THE BACK AND SUBMIT BUTTON-->


                            </div>
                            <!--END IS FOR THE PAGE RESULT FOR DATA WITH NO ERROR-->
                        </form>
                    </div>
                </section>
            </div>
        </div>
        <script>
            var d = new Date();
            var n = d.getFullYear();
            document.getElementById('text_year').innerHTML = "<b>" + n + "</b>";
        </script>
    </body>
</html>
