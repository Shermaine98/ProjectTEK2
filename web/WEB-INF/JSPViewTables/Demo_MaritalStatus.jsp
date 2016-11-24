<%--
    Document   : Demo_MaritalStatus
    Created on : Jun 21, 2016, 12:14:03 AM
    Author     : Geraldine, Gian, Shermaine
--%>

<%@page import="model.demo.MaritalStatus"%>
<%@page import="model.demo.ByAgeGroupSex"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<%@include file="RejectModal_Demo.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Archived Report</title>
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>

     <!--Pace Imports-->
        <script src="AdminLTE/plugins/pace/pace.js" type="text/javascript"></script>
        <link href="AdminLTE/plugins/pace/dataurl.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <div class="wrapper">
            <!--Content Wrapper. Contains page content-->
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">

                        <%                            ArrayList<MaritalStatus> MaritalStatus = (ArrayList<MaritalStatus>) request.getAttribute("maritalStatusData");
                            String pageTitle = request.getAttribute("clicked").toString();
                        %>

                        <h3 style="text-align:center; margin-left: 2%; margin-right: 2%;">
                            <% if (pageTitle.equals("approvalAdmin")) {%>
                            Report for Approval | Household Population 10 years old & over by Age group, Sex and Marital Status for <%= MaritalStatus.get(0).getYear()%>
                            <% } %>
                            <% if (pageTitle.equals("forView")) {%>
                            Saved Report
                            | Household Population 10 years old & over by Age group, Sex and Marital Status for <%= MaritalStatus.get(0).getYear()%>
                            <% }%></h3>


                        <input type="hidden" id="formID" value="<%= MaritalStatus.get(0).getFormID()%>" />

                        <div class="dataTable-width" style="width: 95%;">
                            <table id="viewmarital_status" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                <thead class="heading">
                                    <tr>
                                        <th>Location</th>
                                        <th>Sex</th>
                                        <th>Age Group</th>
                                        <th class="centerTD">Single</th>
                                        <th class="centerTD">Married</th>
                                        <th class="centerTD">Widowed</th>
                                        <th class="centerTD">Divorced/Separated</th>
                                        <th class="centerTD">Common Law/ Live-in</th>
                                        <th class="centerTD">Unknown</th>
                                        <th class="centerTD">Total</th>
                                    </tr>
                                </thead>
                                <tbody class="whitebg">
                                    <%
                                        for (int i = 0; i < MaritalStatus.size(); i++) {

                                    %>
                                    <tr>
                                        <td><input type="text" name="location" value="<%=MaritalStatus.get(i).getLocation()%>" readonly /></td>
                                        <td><input type="text" name="sex" value="<%=MaritalStatus.get(i).getSex()%>" readonly /></td>
                                        <td><input type="text" name="ageGroup" value="<%=MaritalStatus.get(i).getAgeGroup()%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="single" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getSingle())%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="married" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getMarried())%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="windowed" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getWidowed())%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="divorcedSeparated" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getDivorcedSeparated())%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="commonLawLiveIn" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getCommonLawLiveIn())%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="unknown" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getUnknown())%>" readonly /></td>
                                        <td><input type="text" class="centerTD" name="total" value="<%=MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getTotal())%>" readonly /></td>
                                    </tr>
                                    <%
                                        }
                                    %>
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
                            <form id="approveServlet" action="ApproveDemoServlet" method="post">
                                 <input type="hidden" name="sUser" id="sUser" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="sformID" id="sformID" />
                                <input type="hidden" name="decision" value="approve" />
                            </form>

                        </div>
                    </div>
                </section>
            </div>
        </div>

        <script src="jsDataTables_config/demo_maritalStatus.js" type="text/javascript"></script>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>
    </body>
</html>
