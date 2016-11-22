<%--
    Document   : valiNutritionalStatus
    Created on : Jul 3, 2016, 10:06:07 AM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page import="model.temp.health.NutritionalStatusTemp"%>
<%@page import="model.health.NutritionalStatus"%>
<%@page import="java.util.ArrayList"%>
<%@include file="../levelOfAccess.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Preview Upload | Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>
        <!--PREVENT ENTER KEY TO SUBMIT FORM-->
        <script src="jsImported/ePreventEnter.js" type="text/javascript"></script>
        <!--GET YEAR FOR OF SUBMISSION-->
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <!--UPDATING THE FILE/ ERROR-->
        <script src="jsHealthImports/nutritionalUpdate.js" type="text/javascript"></script>
        <!--Pace Imports-->
        <script src="AdminLTE/plugins/pace/pace.js" type="text/javascript"></script>
        <link href="AdminLTE/plugins/pace/dataurl.css" rel="stylesheet" type="text/css"/>
        <style>
            input[type="text"]
            {
                background: transparent;
                border: none;
                width: auto;
            }
            .no_display{
                display:none;
            }
            /*
                     #content {
                         display: table;
                     }

                     #pageFooter {
                         display: table-footer-group;
                     }

                     #pageFooter:after {
                         counter-increment: page;
                         content: counter(page);
                     }

                     #pageFooter:after {
             counter-increment: page;
             content:"Page " counter(page);
             left: 0;
             top: 100%;
             white-space: nowrap;
             z-index: 20px;
             -moz-border-radius: 5px;
             -moz-box-shadow: 0px 0px 4px #222;
             background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
             background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
           }
            */

        </style>
    </head>
    <body>
        <div class="wrapper hidden-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <div class="box-header with-border" style="margin-bottom: 2%;">
                            <center>
                                <!--THIS IS FOR THE PAGE TITLE-->
                                <%                                    String redirect = (String) request.getAttribute("page");
                                    if (redirect.equalsIgnoreCase("upload")) {
                                %>

                                <h1 class="box-title"><b>Preview of Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender Report for</b> </h1>
                                <h1 class="box-title" id="text_year"></h1>

                                <% } else if (redirect.equalsIgnoreCase("edited")) {%>
                                <h1 class="box-title"><b>Edit Saved Report | Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender Report for </b></h1>
                                <h1 class="box-title" id="text_year"></h1>
                                <%}%>
                                <br>
                                <!--END FOR THE PAGE TITLE-->
                            </center>
                        </div>

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
                        <form id="UploadDb" action="ValiNutritionalStatus" method="POST">
                            <%
                                ArrayList<NutritionalStatusTemp> NutritionalStatusTemp = (ArrayList<NutritionalStatusTemp>) request.getAttribute("ArrError");
                                if (NutritionalStatusTemp != null) {
                            %>
                            <div class="DT" id="errorsDiv">
                                <table id="nutritional-table-error" class="table table-bordered">

                                        <%for (int i = 0; i < NutritionalStatusTemp.size(); i++) {%>
                                        <tbody>
                                        <tr style="background-color: #454545; color: #fff">
                                            <th colspan="2">Location</th>
                                            <td colspan="4"><input name="districtError" type="text" value="<%=NutritionalStatusTemp.get(i).getDistrict()%>" readonly/></td>
                                            <th colspan="2">Grade Level</th>
                                            <td colspan="5"><input name="gradeLevelError" type="text" value="<%=NutritionalStatusTemp.get(i).getGradeLevel()%>" readonly /></td>
                                        </tr>
                                        <tr>
                                            <th rowspan="2" colspan="2" class="centerTD">Enrollment</th>
                                            <th rowspan="2" class="centerTD">No. of Pupils Weighed</th>
                                            <th colspan="10" class="centerTD">Body Mass Index</th>
                                        </tr>
                                        <tr>
                                             <%for (int y = 0; y < NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().size(); y++) {%>
                                            <th class="centerTD"><input name="bmiError"  type="hidden" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getBMI()%>"  />
                                                <%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getBMI()%></th>
                                             <th class="centerTD">Reason</th>
                                                <% }%>
                                        </tr>
                                        <tr class='trMale EditTable'>
                                            <th><input name="validation1" class="centerTD" type="hidden" value="<%=NutritionalStatusTemp.get(i).isValidation()%>" />Male</th>
                                            <td class="ErrorC totalMaleError"><input style="width: 60px;" id="totalMaleError" name="totalMaleError" class="centerTD" type="text" value="<%=NutritionalStatusTemp.get(i).getTotalMale()%>" readonly /></td>
                                            <td class="ErrorC pupilsWeighedMaleError centerTD"><%=NutritionalStatusTemp.get(i).getPupilsWeighedMale()%><input id="pupilsWeighedMaleError" name="pupilsWeighedMaleError" class="centerTD"  type="hidden" value="<%=NutritionalStatusTemp.get(i).getPupilsWeighedMale()%>"  /></td>
                                                <%for (int y = 0; y < NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().size(); y++) {%>
                                            <td class="ErrorC maleCountError">
                                                <input id="maleCountError" name="maleCountError" class="centerTD" style="width:70px"  type="text" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()%>"   onkeypress="return event.charCode >= 48 && event.charCode <= 57"/></td>
                                            <td class="ErrorC" rowspan="3" style="vertical-align: middle; text-align: center"  ><input name="validation" class="centerTD" type="hidden" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getValidation()%>" style="width: auto" /><%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getReason()%></td>
                                                <% }%>
                                        </tr>
                                        <tr class="trFemale">
                                            <th>Female</th>
                                            <td class="ErrorC totalFemaleError"><input id="totalFemaleError" style="width: 60px;" name="totalFemaleError" class="centerTD" type="text" value="<%=NutritionalStatusTemp.get(i).getTotalFemale()%>"  readonly/></td>
                                            <td class="ErrorC pupilsWeighedFemaleError centerTD"><%=NutritionalStatusTemp.get(i).getPupilsWeighedFemale()%><input id="pupilsWeighedFemaleError" name="pupilsWeighedFemaleError" class="centerTD"  type="hidden" value="<%=NutritionalStatusTemp.get(i).getPupilsWeighedFemale()%>"  /></td>
                                                <%for (int y = 0; y < NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().size(); y++) {%>
                                            <td class="ErrorC femaleCountError"><input id="femaleCountError" name="femaleCountError" class="centerTD"  style="width:70px"  type="text" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()%>"   onkeypress="return event.charCode >= 48 && event.charCode <= 57"/></td>
                                            <td class="no_display"><input name="validation" class="centerTD" type="hidden" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getValidation()%>" style="width: auto" /><%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getReason()%></td>
                                                <% }%>
                                        </tr>
                                        <tr class="trTotal">
                                            <th>Total</th>
                                            <td class="ErrorC totalCountError"><input name="totalCountError" style="width: 60px;" class="centerTD" type="text" value="<%=NutritionalStatusTemp.get(i).getTotalCount()%>" readonly /></td>
                                            <td class="ErrorC pupilsWeighedTotalError centerTD"><%=NutritionalStatusTemp.get(i).getPupilsWeighedTotal()%><input name="pupilsWeighedTotalError" class="centerTD"  type="hidden" value="<%=NutritionalStatusTemp.get(i).getPupilsWeighedTotal()%>" /></td>
                                                <%for (int y = 0; y < NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().size(); y++) {%>
                                            <td class="ErrorC bTotalCountError"><input name="bTotalCountError" class="centerTD" style="width:70px"  type="text" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()%>"  readonly/></td>
                                            <td class="no_display"><input name="validation" class="centerTD" type="hidden" value="<%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getValidation()%>" style="width: auto" /><%=NutritionalStatusTemp.get(i).getNutritionalStatusBMITemp().get(y).getReason()%></td>
                                                <% } %>
                                        </tr>
                                          </tbody>
                                        <%}%>

                                </table>
                            </div>
                            <div align="center">
                                <a onclick="print_div()"><input align="center" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto" type="button" value="Print Error Summary"/></a>
                            </div>
                            <% }
                                ArrayList<NutritionalStatus> NutritionalStatus = (ArrayList<NutritionalStatus>) request.getAttribute("ArrNoError");
                            %>

                            <div class="DT">
                                <table class="table table-bordered">
                                    <tbody>
                                        <%for (int i = 0; i < NutritionalStatus.size(); i++) {%>
                                        <tr style="background-color: #454545; color: #fff">
                                            <th>Location</th>
                                            <td colspan="3"><input name="district" type="text" value="<%=NutritionalStatus.get(i).getDistrict()%>" readonly/></td>
                                            <th>Grade Level</th>
                                            <td colspan="3"><input name="gradeLevel" type="text" value="<%=NutritionalStatus.get(i).getGradeLevel()%>" readonly /></td>
                                        </tr>
                                        <tr>
                                            <th rowspan="2" colspan="2" class="centerTD">Enrollment</th>
                                            <th rowspan="2" class="centerTD">No. of Pupils Weighed</th>
                                            <th colspan="5" class="centerTD">Body Mass Index</th>
                                        </tr>
                                        <tr>
                                            <%for (int y = 0; y < NutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {%>
                                            <th class="centerTD"><input name="bmi" type="hidden" value="<%=NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getBMI()%>"  />
                                                <%=NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getBMI()%></th>
                                                <% }%>
                                            <!--
                                            <th class="centerTD">Severely Wasted</th>
                                            <th class="centerTD">Wasted</th>
                                            <th class="centerTD">Normal</th>
                                            <th class="centerTD">Overweight</th>
                                            <th class="centerTD">Obese</th>
                                            -->
                                        </tr>
                                        <tr class='EditTable'>
                                            <th>Male</th>
                                            <td><input name="totalMale" class="centerTD" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getTotalMale())%>" readonly /></td>
                                            <td><input name="pupilsWeighedMale" class="centerTD"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getPupilsWeighedMale())%>" readonly /></td>
                                                <%for (int y = 0; y < NutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {%>
                                            <td> <input name="maleCount" class="centerTD" style="width:70px"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getMaleCount())%>"  readonly/></td>
                                                <% }%>
                                        </tr>
                                        <tr>
                                            <th>Female</th>
                                            <td><input name="totalFemale" class="centerTD" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getTotalFemale())%>" style="width: auto" readonly/></td>
                                            <td><input name="pupilsWeighedFemale" class="centerTD"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getPupilsWeighedFemale())%>"  readonly/></td>
                                                <%for (int y = 0; y < NutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {%>
                                            <td><input name="femaleCount" class="centerTD"  style="width:70px"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getFemaleCount())%>"  readonly/></td>
                                                <% }%>
                                        </tr>
                                        <tr>
                                            <th>Total</th>
                                            <td><input name="totalCount" class="centerTD" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getTotalCount())%>" style="width: auto" readonly /></td>
                                            <td><input name="pupilsWeighedTotal" class="centerTD"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getPupilsWeighedTotal())%>" readonly/></td>
                                                <%for (int y = 0; y < NutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {%>
                                            <td><input name="bTotalCount" class="centerTD" style="width:70px"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getTotalCount())%>"  readonly/></td>
                                                <% } %>
                                        </tr>
                                        <%
                                            }%>
                                    </tbody>
                                </table>
                            </div>

                            <div style="width:96%; margin-left: 2%; margin-top: 5%;">
                                <div style="display:inline; float:left;" class="">
                                    <a href="${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=percentageDist">
                                        <input type="button" class="btn btn-block btn-default" style="width: 100px;" value='Back'>
                                    </a></div>
                                <div  style="display:inline; float:right;" class="">
                                    <input  class="btn btn-block btn-success" type="submit" value="Submit" /></div>
                            </div>
                            <input type="hidden" name="year" class="year" id="year" />
                            <input type="hidden" name="uploadedBy" value="<%= user.getUserID()%>" />
                            <input type="hidden" name="page" value="<%=redirect%>"/>
                            <input type="hidden" name="errorMessage" value="<%=temp%>"/>

                        </form>
                    </div>
                </section>
            </div>
        </div>

        <div class="visible-print">
            <div style="margin-bottom: 6%;" align="center">
                <img src="img/Caloocan-Logo.png" alt=""/><br>
                <h4>City Planning and Development Department</h4>
                <p>Percentage Distribution of Elementary School Children in Each District<br>
                    in the Division of Caloocan by Nutritional Status/By Gender<br>
                    Errors Summary Report for 2016<br>
                    Prepared By: <%= user.getFirstName()%> <%= user.getLastName()%></p>
                <p id="DateHere"></p>
            </div>
            <!--TABLE-->
            <p style="margin-left: 5%;">Please provide the correct inputs for the missing and incorrect fields as seen in the table below.</p>
            <div id="printTable" style="width: 80%; ">
            </div>
            <footer>
                <!--
                <div id="content">
                    <div id="pageFooter">Page </div>
                    multi-page content here...
                </div>
                -->
                <div style='text-align:center;
                     /*position:fixed;*/
                     height:50px;
                     bottom:0px;
                     left:0px;
                     right:0px;
                     margin-bottom:0px;'>Page 1
                    <!--<span class="pageCounter"></span>/<span class="totalPages"></span>-->
                </div>
            </footer>
        </div>

        <script>
            function print_div() {
                $('#printTable').empty();
                var m_names = new Array("January", "February", "March",
                        "April", "May", "June", "July", "August", "September",
                        "October", "November", "December");

                var d = new Date();
                var curr_date = d.getDate();
                var curr_month = d.getMonth();
                var curr_year = d.getFullYear();
                var today = m_names[curr_month] + " " + curr_date
                        + ", " + curr_year;

                $("#DateHere").html(today);

                $('div#errorsDiv').clone().appendTo('#printTable');
               // jQuery('#printTable').html(jQuery("#errorsDiv").html());
                window.print();
            }
            var d = new Date();
            var n = d.getFullYear();
            document.getElementById('text_year').innerHTML = "<b>" + n + "</b>";
        </script>
    </body>
</html>
