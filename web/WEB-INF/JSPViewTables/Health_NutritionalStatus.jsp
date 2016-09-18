<%--
    Document   : showTableNutritional
    Created on : Jul 15, 2016, 4:26:40 PM
    Author     : Shermaine
--%>

<%@page import="ModelHealth.NutritionalStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<%@include file="RejectModal_Health.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nutritional Status</title>
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>

        <!--Pace Imports-->
        <script src="Pace/pace.js"></script>
        <link href="Pace/dataurl.css" rel="stylesheet" />
        <style>
            .centerTD{
                text-align:center;
            }
            table td{
                text-align:center;
            }
            .table th {
                vertical-align: bottom!important;
            }
        </style>
    </head>
    <body>

        <div class="wrapper no-print">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <%            ArrayList<NutritionalStatus> NutritionalStatus = (ArrayList<NutritionalStatus>) request.getAttribute("nutritional");
                            String pageTitle = request.getAttribute("clicked").toString();
                        %>

                        <h3 style="text-align:center; margin-left: 2%; margin-right: 2%;">
                            <% if (pageTitle.equals("approvalAdmin")) {%>
                            Report for Approval
                            <% } %>
                            <% if (pageTitle.equals("forView")) {%> Saved Report
                            <% }%>
                            | Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender for <%= NutritionalStatus.get(0).getCensusYear()%></h3>
                        <input type="hidden" id="formID" value="<%= NutritionalStatus.get(0).getFormID()%>" />

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
                                        <th class="centerTD">Severely Wasted</th>
                                        <th class="centerTD">Wasted</th>
                                        <th class="centerTD">Normal</th>
                                        <th class="centerTD">Overweight</th>
                                        <th class="centerTD">Obese</th>
                                    </tr>
                                    <tr class='EditTable'>
                                        <th>Male</th>
                                        <td><input name="totalMale" class="centerTD" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getTotalMale())%>" readonly /></td>
                                        <td><input name="pupilsWeighedMale" class="centerTD" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getPupilsWeighedMale())%>" readonly /></td>
                                            <%for (int y = 0; y < NutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {%>
                                        <td><input name="bmi" class="centerTD" style="width:70px"  type="hidden" value="<%=NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getBMI()%>"  />
                                            <input name="maleCount" class="centerTD" style="width:70px"  type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getMaleCount())%>"  readonly/></td>
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
                                        <td><input name="pupilsWeighedTotal" class="centerTD" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getPupilsWeighedTotal())%>" readonly/></td>
                                            <%for (int y = 0; y < NutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {%>
                                        <td><input name="bTotalCount" class="centerTD" style="width:70px" type="text" value="<%=NutritionalStatus.get(i).getFormatcount(NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getFemaleCount() + NutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getMaleCount())%>"  readonly/></td>
                                            <% } %>
                                    </tr>
                                    <%
                                                }%>
                                </tbody>
                            </table>
                            <%
                                if (pageTitle.equalsIgnoreCase("approvalAdmin")) {
                            %>
                            <input id ="approvedReport" class="btn btn-flat btn-success" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Approve Report"/>
                            <input class="btn btn-flat btn-danger" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Reject Report" data-toggle="modal" data-target="#rejectModal"/>
                            <%  }
                            %>

                        </div>
                        <div>
                            <form id="approveServlet" action="approveReportsHealth" method="post">
                                <input type="hidden" name="sUser" id="sUser" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="sformID" id="sformID" />
                                <input type="hidden" name="decision" value="approve" />
                            </form>

                        </div>
                    </div>
                </section>
            </div>
        </div>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>
    </body>
</html>
