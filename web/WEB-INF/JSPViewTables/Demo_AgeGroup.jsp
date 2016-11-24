<%--
    Document   : Demo_AgeGroup
    Created on : Jun 21, 2016, 12:14:03 AM
    Author     :  Geraldine, Gian, Shermaine
--%>

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

                        <%                            ArrayList<ByAgeGroupSex> ByAgeGroupSex = (ArrayList<ByAgeGroupSex>) request.getAttribute("ageGroupSexData");
                            String pageTitle = request.getAttribute("clicked").toString();
                        %>

                        <h3 style="text-align:center; margin-left: 2%; margin-right: 2%;">
                            <% if (pageTitle.equals("approvalAdmin")) {%>
                            Report for Approval | Household Population by Age Group and Sex for <%= ByAgeGroupSex.get(0).getYear()%>
                            <% } %>
                            <% if (pageTitle.equals("forView")) {%>
                            Saved Report
                            | Household Population by Age Group and Sex for <%= ByAgeGroupSex.get(0).getYear()%>
                            <% }%></h3>


                        <input type="hidden" id="formID" value="<%= ByAgeGroupSex.get(0).getFormID()%>" />
                        <div class="dataTable-width">
                            <table id="approval-ageGroup" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                <thead class="heading" >
                                    <tr>
                                        <th>Location</th>
                                        <th>Age Group</th>
                                        <th class="centerTD">Both Sex</th>
                                        <th class="centerTD">Male Count</th>
                                        <th class="centerTD">Female Count</th>
                                    </tr>
                                </thead>
                                <tbody class="whitebg" >
                                    <%for (int i = 0; i < ByAgeGroupSex.size(); i++) {%>
                                    <tr>
                                        <td><%=ByAgeGroupSex.get(i).getBarangay()%></td>
                                        <td><%=ByAgeGroupSex.get(i).getAgeGroup()%></td>
                                        <td class="centerTD"><%=ByAgeGroupSex.get(i).getFormatcount(ByAgeGroupSex.get(i).getMaleCount() + ByAgeGroupSex.get(i).getFemaleCount())%></td>
                                        <td class="centerTD"><%=ByAgeGroupSex.get(i).getFormatcount(ByAgeGroupSex.get(i).getMaleCount())%></td>
                                        <td class="centerTD"><%=ByAgeGroupSex.get(i).getFormatcount(ByAgeGroupSex.get(i).getFemaleCount())%></td>
                                    </tr>
                                    <% }%>
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

        <script src="jsDataTables_config/demo_ageGroup.js" type="text/javascript"></script>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>
    </body>
</html>
