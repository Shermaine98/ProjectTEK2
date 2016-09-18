<%--
    Document   : showTableEnrollement
    Created on : Jul 15, 2016, 3:19:30 PM
    Author     : Shermaine
--%>

<%@page import="ModelEducation.enrollment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<%@include file="RejectModal_Education.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Report | Enrollment</title>
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>

        <!--Pace Imports-->
        <script src="Pace/pace.js"></script>
        <link href="Pace/dataurl.css" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper no-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <%                            ArrayList<enrollment> enrollment = (ArrayList<enrollment>) request.getAttribute("enrollment");
                            String classification = request.getAttribute("classification").toString();
                            String pageTitle = request.getAttribute("clicked").toString();
                            String toCaps = Character.toUpperCase(classification.charAt(0)) + classification.substring(1);
                        %>


                        <h3 style="text-align:center; margin-left: 2%; margin-right: 2%;">
                            <% if (pageTitle.equals("approvalAdmin")) {%>
                            Report for Approval | Enrollment for
                            <%= toCaps%> Schools <%= enrollment.get(0).getCensusYear()%>
                            <% } %>
                            <% if (pageTitle.equals("forView")) {%>
                            Saved Report
                            | Enrollment for <%=toCaps%> Schools <%= enrollment.get(0).getCensusYear()%>
                            <% }%></h3>

                        <input type="hidden" id="formID" value="<%= enrollment.get(0).getFormID()%>" />

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
                        <%
                            if (pageTitle.equalsIgnoreCase("approvalAdmin")) {
                        %>
                        <input id ="approvedReport" class="btn btn-flat btn-success" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Approve Report"/>
                        <input class="btn btn-flat btn-danger" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Reject Report" data-toggle="modal" data-target="#rejectModal"/>
                        <%  }
                        %>

                        <div>
                            <form id="approveServlet" action="approvalsEducation" method="post">
                                <input type="hidden" name="sUser" id="sUser" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="sformID" id="sformID" />
                                <input type="hidden" name="decision" value="approve" />
                            </form>

                        </div>
                    </div>
            </div>
        </div>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>
    </body>
</html>
