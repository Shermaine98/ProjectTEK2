<%--
    Document   : valiEnrollment
    Created on : Jul 3, 2016, 10:06:07 AM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page import="model.temp.education.EnrollmentTemp"%>
<%@page import="model.education.Enrollment"%>
<%@page import="java.util.ArrayList"%>
<%@include file="../levelOfAccess.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Preview Upload | Enrollment</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>

        <!--PREVENT ENTER KEY TO SUBMIT FORM-->
        <script src="jsImported/ePreventEnter.js" type="text/javascript"></script>
        <!--GET YEAR FOR OF SUBMISSION-->
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <!--UPDATING THE FILE/ ERROR-->
        <script src="jsEducImports/enrollmentUpdate.js" type="text/javascript"></script>
        <!--Pace Imports-->
        <script src="AdminLTE/plugins/pace/pace.js" type="text/javascript"></script>
        <link href="AdminLTE/plugins/pace/dataurl.css" rel="stylesheet" type="text/css"/>

        <style>
            .no_display{
                display:none;
            }
        </style>
    </head>
    <body>
        <div class="wrapper hidden-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <div class="box-header with-border">
                            <!--THIS IS FOR THE PAGE TITLE-->
                            <%                                String redirect = (String) request.getAttribute("page");
                                String classification = (String) request.getAttribute("classification");

                                if (redirect.equalsIgnoreCase("upload")) {
                            %>
                            <div class="box-header with-border" style="margin-bottom: 2%;">
                                <center>
                                    <h1 class="box-title" style="margin-right: 1%;"><b>Preview of Enrollment in <%=classification%> Schools for </b></h1>
                                    <h1 class="box-title" id="text_year"></h1>
                                </center>
                                <br>
                            </div>
                            <% } else if (redirect.equalsIgnoreCase("edited")) {%>
                            <div class="box-header with-border" style="margin-bottom: 2%;">
                                <center>
                                    <h1 class="box-title" style="margin-right: 1%;"><b>Edit Saved Report | Enrollment in <%=classification%> Schools for </b></h1>
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
                            <div>

                                <form id="UploadDb" action="ValiEnrollment" method="post">
                                    <%
                                        ArrayList<EnrollmentTemp> enrollmentTemp = (ArrayList<EnrollmentTemp>) request.getAttribute("ArrError");
                                        if (enrollmentTemp != null) {

                                    %>
                                    <div class="box-body">
                                        <div class="DT" id="errorsDiv" style="width:92%;">
                                            <table id="enrollemt-error" class="table table-bordered">

                                                <%for (int i = 0; i < enrollmentTemp.size(); i++) {%>
                                                <tbody class="bodyTable">
                                                    <tr style = "background-color: #454545; color: #fff" >
                                                        <th colspan="2">School Name</th>
                                                        <td colspan="8"><input readonly name="schoolNameError" type="text" value="<%=enrollmentTemp.get(i).getSchoolName()%>"/></td>
                                                        <th colspan="3">District</th>
                                                        <td colspan="6"><input name="districtError" type="text"  readonly value="<%=enrollmentTemp.get(i).getDistrict()%>" /></td>
                                                    </tr>
                                                    <tr class="gd">
                                                        <th colspan="2">School Type</th>
                                                        <td colspan="8"><input name="schoolTypeError" type="text" readonly value="<%=enrollmentTemp.get(i).getSchoolType()%>" /></td>
                                                        <th colspan="3">Gender Disparity Index</th>
                                                        <td class="errorV GenderDisparityIndexError" colspan="6"><input id="GenderDisparityIndexError" name="GenderDisparityIndexError" type="text" value="<%= enrollmentTemp.get(i).getGenderDisparityIndex()%>" readonly /></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="vertical-align: middle">Sex</th>
                                                            <%for (int y = 0; y < enrollmentTemp.get(0).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <th style="vertical-align: middle"><%=enrollmentTemp.get(0).getEnrollmentDetArrayList().get(y).getGradeLevel()%>
                                                            <input id="bothSexesError" type="hidden" value="<%=enrollmentTemp.get(0).getEnrollmentDetArrayList().get(y).getGradeLevel()%>"  readonly/></th>
                                                        <th style="vertical-align: middle">Reason</th>
                                                            <%}%>

                                                        <th style="vertical-align: middle">Grand Total</th>
                                                        <th style="vertical-align: middle">Reason</th>
                                                    </tr>
                                                    <tr class="maleE EditTable">
                                                        <th>Male</th>
                                                            <%for (int y = 0; y < enrollmentTemp.get(i).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <td class="errorV maleCountError"><input name="maleCountError" style="width:40px"  type="text" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()%>"   onkeypress="return event.charCode >= 48 && event.charCode <= 57"/></td>
                                                        <td class='errorV validationM' rowspan="3" style="vertical-align: middle"><input  name="gradeLevelError" type="hidden" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel()%>"  /><%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getReason()%>
                                                            <input id='validationM' name="validation" type="text" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getReason()%>" hidden /></td>
                                                            <%}%>
                                                        <td class='errorV totalMaleError'><input style="width:40px" id="totalMaleError" name="totalMaleError" type="text" value="<%= enrollmentTemp.get(i).getTotalMale()%>" readonly /></td>
                                                        <td class="errorV validationM" rowspan="3" style="vertical-align: middle"><%=enrollmentTemp.get(i).getReason()%>
                                                            <input id="validation2" name="validation" type="text" value="<%=enrollmentTemp.get(i).getReason()%>" hidden /></td>
                                                    </tr>
                                                    <tr class="femaleE">
                                                        <th>Female</th>
                                                            <%for (int y = 0; y < enrollmentTemp.get(i).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <td class="errorV femaleCountError" ><input style="width:40px" name="femaleCountError"  type="text" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()%>"   onkeypress="return event.charCode >= 48 && event.charCode <= 57"/></td>
                                                        <td class="no_display"><input name="validation" type="text" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getReason()%>" readonly /></td>
                                                            <%}%>
                                                        <td class="errorV femaleTotal"><input style="width:40px" name="totalFemaleError" type="text" value="<%= enrollmentTemp.get(i).getTotalFemale()%>" readonly /></td>
                                                        <td class="no_display"><input name="validation" type="text" value="<%=enrollmentTemp.get(i).getReason()%>" readonly /></td>
                                                    </tr>
                                                    <tr class="totalE">
                                                        <th>Total</th>
                                                            <%for (int y = 0; y < enrollmentTemp.get(i).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <td class="errorV totalCountError"><input readonly style="width:40px" id="totalCountError" name="totalCountError"  type="text" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()%>"  /></td>
                                                        <td class="no_display"><input name="validation" type="text" value="<%=enrollmentTemp.get(i).getEnrollmentDetArrayList().get(y).getReason()%>" readonly /></td>
                                                            <%}%>
                                                        <td  class="grandTotalError errorV"><input id="grandTotalError" style="width:40px" name="grandTotalError" type="text" value="<%= enrollmentTemp.get(i).getGrandTotal()%>" readonly /></td>
                                                        <td class="no_display"><input name="validation" type="text" value="<%= enrollmentTemp.get(i).getReason()%>" readonly /></td>
                                                    </tr>
                                                </tbody>
                                                <%}%>

                                            </table>
                                        </div>
                                    </div>
                                    <div align="center">
                                        <a onclick="print_div()"><input align="center" class="btn btn-flat btn-success" style="margin: 0 auto 5% auto" type="button" value="Print Error Summary"/></a>
                                    </div>
                                    <% }
                                        ArrayList<Enrollment> enrollment = (ArrayList<Enrollment>) request.getAttribute("ArrNoError");
                                    %>
                                    <div class="box-body">
                                        <div class="DT">
                                            <table class="table table-bordered">
                                                <tbody>
                                                    <%for (int i = 0; i < enrollment.size(); i++) {%>
                                                    <tr style = "background-color: #454545; color: #fff" >
                                                        <th colspan="2">School Name</th>
                                                        <td colspan="4"><input readonly name="schoolName" type="text" value="<%=enrollment.get(i).getSchoolName()%>"/></td>
                                                        <th colspan="2">District</th>
                                                        <td colspan="2"><input name="district" type="text"  readonly value="<%=enrollment.get(i).getDistrict()%>" /></td>

                                                    </tr>
                                                    <tr>
                                                        <th colspan="2">School Type</th>
                                                        <td colspan="4"><input name="schoolType" type="text" readonly value="<%=enrollment.get(i).getSchoolType()%>" /></td>
                                                        <th colspan="2">Gender Disparity Index</th>
                                                        <td colspan="2"><input name="GenderDisparityIndex" type="text" value="<%= enrollment.get(i).getGenderDisparityIndex()%>" readonly /></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="vertical-align: middle">Sex</th>
                                                            <%for (int y = 0; y < enrollment.get(0).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <th style="vertical-align: middle"><input id="bothSexesError" type="text" value="<%=enrollment.get(0).getEnrollmentDetArrayList().get(y).getGradeLevel()%>"  readonly/></th>
                                                            <%}%>
                                                        <th style="vertical-align: middle">Grand Total</th>
                                                    </tr>
                                                    <tr class='EditTable'>
                                                        <th>Male</th>
                                                            <%for (int y = 0; y < enrollment.get(i).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <td><input name="gradeLevel" type="hidden" value="<%=enrollment.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel()%>"  />
                                                            <input name="maleCount" style="width:60px"  type="text" value="<%=enrollment.get(i).getFormatcount(enrollment.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())%>"  readonly/></td>
                                                            <%}%>
                                                        <td><input name="totalMale" type="text" value="<%= enrollment.get(i).getFormatcount(enrollment.get(i).getTotalMale())%>" readonly /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Female</th>
                                                            <%for (int y = 0; y < enrollment.get(i).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <td><input name="femaleCount"  type="text" value="<%=enrollment.get(i).getFormatcount(enrollment.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())%>"  readonly/></td>
                                                            <%}%>
                                                        <td><input name="totalFemale" type="text" value="<%= enrollment.get(i).getFormatcount(enrollment.get(i).getTotalFemale())%>" readonly /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Total</th>
                                                            <%for (int y = 0; y < enrollment.get(i).getEnrollmentDetArrayList().size(); y++) {%>
                                                        <td><input name="totalCount"  type="text" value="<%=enrollment.get(i).getFormatcount(enrollment.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())%>"  readonly/></td>
                                                            <%}%>
                                                        <td><input name="grandTotal" type="text" value="<%= enrollment.get(i).getFormatcount(enrollment.get(i).getGrandTotal())%>" readonly /></td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <!--THIS IS FOR THE BACK AND SUBMIT BUTTON-->

                                    <%
                                        String backbutton = "ePrivate";
                                        if (classification.equalsIgnoreCase("public")) {
                                            backbutton = "ePublic";
                                        }
                                    %>


                                    <div style="margin-top: 5%;">
                                        <div style="display:inline; float:left;">
                                            <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=<%=backbutton%>">
                                                <input type="button" class="btn btn-block btn-default" style="width: 100px;" value='Back'>
                                            </a></div>
                                        <div  style="display:inline; float:right;">
                                            <input  class="btn btn-block btn-success" type="Submit" value="Submit" /></div>
                                    </div>
                                    <!--END FOR THE BACK AND SUBMIT BUTTON-->

                                    <input type="hidden" name="year" class="year" id="year" />
                                    <input type="hidden" name="uploadedBy" value="<%= user.getUserID()%>" />
                                    <input type="hidden" name="page" value="<%=redirect%>"/>
                                    <input type="hidden" name="errorMessage" value="<%=temp%>"/>
                                    <input name="classification" type="hidden" value="<%=classification%>"/>

                                </form>
                            </div>
                            <!--END IS FOR THE PAGE RESULT FOR DATA WITH NO ERROR-->
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <div class="visible-print">

            <div style="margin-bottom: 6%;" align="center">
                <img src="img/Caloocan-Logo.png" alt=""/><br>
                <h4>City Planning and Development Department</h4>
                <p>Enrollment in <%= classification %> Schools<br>
                    Errors Summary Report for 2016<br>
                    Prepared By: <%= user.getFirstName()%> <%= user.getLastName()%></p>
                <p id="DateHere"></p>
            </div>
            <!--TABLE-->
            <p style="margin-left: 5%;">Please provide the correct inputs for the missing and incorrect fields as seen in the table below.</p>
            <div id="printTable" style="width: 90%; margin-right: 10%; ">
            </div>
            <footer>
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
