<%--
    Document   : home
    Created on : Jun 8, 2016, 10:13:59 PM
    Author     : Geraldine Atayan
--%>
<%@page import="model.TaskModel"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--IMPORTING HTML IMPORTS (bootstrap + scripts)-->
<%@include file="levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Project TEK | Home </title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!--        <link rel="stylesheet" href="AdminLTE/plugins/fullcalendar/fullcalendar.min.css">
                <link rel="stylesheet" href="AdminLTE/plugins/fullcalendar/fullcalendar.print.css" media="print">-->
        <script src="jsImported/list.min.js" type="text/javascript"></script>
        <style>
            a:hover{
                /*text-decoration: underline;*/
                font-weight: bolder;
            }
            td{
                vertical-align: central;
            }
            .expand {
                text-overflow: ellipsis;
                /* Required for text-overflow to do anything */
                white-space: nowrap;
                overflow: hidden;
            }
            p{
                margin-left: 12%;
            }
            .smallButtonStyle{
                height: 18px; font-size: 13px; padding-top: 0; float:left;
            }
            a{
                cursor: pointer; cursor: hand;
            }
            .span__LABEL{
                width: 80px; display:block;
            }
        </style>
    </head>
    <body>
        <div class ="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content">
                    <div class="row">

                        <% ArrayList<TaskModel> arrTask = ((ArrayList<TaskModel>) request.getAttribute("tasks")); %>

                        <div class="col-md-8">
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Tasks For The Year</h3>
                                </div>
                                <!-- /.box-header -->
                                <div id="pending" class="box-body">
                                    <input class="search form-control" placeholder="Search Report Name" style="margin: 0 auto; width: 50%;" />

                                    <table id="taskTable" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th width="40%">Task</th>
                                                <th><a class="sort" data-sort="sector">Sector <span class="fa fa-sort" style="margin-left: 5%"></span></a></th>
                                                <th><a class="sort" data-sort="date">Due Date <span class="fa fa-sort" style="margin-left: 5%"></span></a></th>
                                                <th><a class="sort" data-sort="status">Status <span class="fa fa-sort" style="margin-left: 5%"></span></a></th>
                                                <th style="text-align:right;">For Your Action</th>
                                            </tr>
                                        </thead>
                                        <tbody class="list" >
                                            <% if (arrTask.size() == 0) { %>
                                            <tr>
                                                <td colspan="4" style="text-align:center;">All tasks have been completed</td>
                                            </tr>
                                            <% } else { %>
                                            <% for (int i = 0; i < arrTask.size(); i++) {%>
                                            <tr>
                                                <td class="ts name"><%= arrTask.get(i).getReportName()%></td>
                                                <td class="sector"><%= arrTask.get(i).getSector()%></td>
                                                <td class="date"><%= arrTask.get(i).getSduedate()%></td>
                                                <% if (arrTask.get(i).getStatus().equalsIgnoreCase("delayed")) {%>
                                                <td class="status"><span  class="label label-danger span__LABEL"><%= arrTask.get(i).getStatus()%></span></td>
                                                 <td style="float:right"><input type="button" style="width: 98px;" id="uploadData" class="btn btn-sm btn-primary" value="Upload Report" /></td>
                                                    <% } else if (arrTask.get(i).getStatus().equalsIgnoreCase("For Approval")) {%>
                                                <td class="status"><span class="label label-info span__LABEL"><%= arrTask.get(i).getStatus()%></span></td>
                                                 <td style="float:right"><input type="button" style="width: 98px;"  id="uploadData" class="btn btn-sm btn-primary" value="Edit Report"/></td>
                                                    <% } else if (arrTask.get(i).getStatus().equalsIgnoreCase("Incomplete")) {%>
                                                <td class="status"><span class="label label-warning span__LABEL"><%= arrTask.get(i).getStatus()%></span></td>
                                                  <td style="float:right"><input type="button" style="width: 98px;"  id="uploadData" class="btn btn-sm btn-primary" value="Edit Report" /></td>
                                                    <% } else if (arrTask.get(i).getStatus().equalsIgnoreCase("rejected")) {%>
                                                <td class="status"><span class="label label-danger span__LABEL"><%= arrTask.get(i).getStatus()%></span></td>
                                                  <td style="float:right"><input type="button" style="width: 98px;"  id="uploadData" class="btn btn-sm btn-primary" value="Upload Report" /></td>
                                                    <% } else if (arrTask.get(i).getStatus().equalsIgnoreCase("pending")) {%>
                                                <td class="status"><span class="label label-default span__LABEL"><%= arrTask.get(i).getStatus()%></span></td>
                                                  <td style="float:right"><input type="button" style="width: 98px;"  id="uploadData" class="btn btn-sm btn-primary" value="Upload Report" /></td>
                                                    <% } else {%>
                                                <td><span class="label label-success span__LABEL"><%= arrTask.get(i).getStatus()%></span></td>
                                                <td style="float:right"><input type="button" style="width: 98px;" disabled id="uploadData" class="btn btn-sm btn-default" value="Disabled" /></td>
                                                    <% }%>
                                            </tr>
                                            <%}
                                                }%>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>


                        <%
                            //DEMOGRAPHICS INCOMPLETE
                            int iAgeGroup = (int) request.getAttribute("iDemoAgeGroup");
                            int iMarital = (int) request.getAttribute("iDemoMarital");
                            int iHighest = (int) request.getAttribute("iDemoHighest");
                            //HEALTH INCOMPLETE
                            int iNutritionalStatus = (int) request.getAttribute("iHealthNutritionalStatus");
                            int iHospital = (int) request.getAttribute("iHealthHospital");
                            //EDUCATION INCOMPLETE
                            int iPublicEnrollment = (int) request.getAttribute("iEducPublicEnrollment");
                            int iPublicDirectory = (int) request.getAttribute("iEducPublicDirectory");
                            int iPrivateEnrollment = (int) request.getAttribute("iEducPrivateEnrollment");
                            int iPrivateDirectory = (int) request.getAttribute("iEducPrivateDirectory");

                        %>

                        <div class="col-md-4" style="float:right;">
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Incomplete Reports</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <% if (iAgeGroup == 0 && iMarital == 0 && iHighest == 0
                                                && iNutritionalStatus == 0 && iHospital == 0
                                                && iPublicEnrollment == 0 && iPublicDirectory == 0 && iPrivateEnrollment == 0 && iPrivateDirectory == 0) { %>
                                    <p>There are no incomplete reports</p>
                                    <% } else { %>
                                    <% if (iPublicEnrollment >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=ePublic">
                                        <button class="btn btn-warning smallButtonStyle"><%=iPublicEnrollment%></button></a>
                                    <p class="expand">Enrollment in Public Schools</p>
                                    <% }
                                                if (iPrivateEnrollment >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=ePrivate">
                                        <button class="btn btn-warning smallButtonStyle"><%=iPrivateEnrollment%></button></a>
                                    <p class="expand">Enrollment in Private Schools</p>
                                    <% }
                                                if (iPublicDirectory >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=publicDirectory">
                                        <button class="btn btn-warning smallButtonStyle"><%=iPublicDirectory%></button></a>
                                    <p class="expand" id='directoryPublic' onclick="expand('directoryPublic');">Number of Teachers and Classrooms in Public Schools</p>
                                    <% }
                                                if (iPrivateDirectory >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=privateDirectory">
                                        <button class="btn btn-warning smallButtonStyle"><%=iPrivateDirectory%></button></a>
                                    <p class="expand" id='directoryPrivate' onclick="expand('directoryPrivate');">Number of Teachers and Classrooms in Public Schools</p>
                                    <% }
                                                if (iAgeGroup >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=byAgeGroupSex">
                                        <button class="btn btn-warning smallButtonStyle"><%=iAgeGroup%></button></a>
                                    <p class="expand">Household Population by Age Group and Sex</p>
                                    <% }
                                                if (iHighest >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=HighestCompleted">
                                        <button class="btn btn-warning smallButtonStyle"><%=iHighest%></button></a>
                                    <p class="expand" id='highest' onclick="expand('highest')">Household Population 5 Years Old and Over by Highest Grade/Year Completed, Age Group and Sex</p>
                                    <% }
                                                if (iMarital >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=maritalStatus">
                                        <button class="btn btn-warning smallButtonStyle"><%=iMarital%></button></a>
                                    <p class="expand" id='marital' onclick="expand('marital')">Household Population 10 Years Old and Over by Age Group, Sex, and Marital Status</p>
                                    <% }
                                                if (iNutritionalStatus >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=percentageDist">
                                        <button class="btn btn-warning smallButtonStyle"><%=iNutritionalStatus%></button></a>
                                    <p class="expand" id='nutrition' onclick='expand("nutrition")'>Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender</p>
                                    <% }
                                                if (iHospital >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=directoryHosptial">
                                        <button class="btn btn-warning smallButtonStyle"><%=iHospital%></button></a>
                                    <p class="expand">List of Hospitals</p>
                                    <% }
                                                } //END OF ELSE %>

                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->

                            <%
                                //DEMOGRAPHICS APPROVAL
                                int aAgeGroup = (int) request.getAttribute("aDemoAgeGroup");
                                int aMarital = (int) request.getAttribute("aDemoMarital");
                                int aHighest = (int) request.getAttribute("aDemoHighest");
                                //HEALTH APPROVAL
                                int aNutritionalStatus = (int) request.getAttribute("aHealthNutritionalStatus");
                                int aHospital = (int) request.getAttribute("aHealthHospital");
                                //EDUCATION APPROVAL
                                int aPublicEnrollment = (int) request.getAttribute("aEducPublicEnrollment");
                                int aPublicDirectory = (int) request.getAttribute("aEducPublicDirectory");
                                int aPrivateEnrollment = (int) request.getAttribute("aEducPrivateEnrollment");
                                int aPrivateDirectory = (int) request.getAttribute("aEducPrivateDirectory");
                            %>
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Pending for Approval</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <% if (aAgeGroup == 0 && aMarital == 0 && aHighest == 0
                                                && aNutritionalStatus == 0 && aHospital == 0
                                                && aPublicEnrollment == 0 && aPublicDirectory == 0 && aPrivateEnrollment == 0 && aPrivateDirectory == 0) { %>
                                    <p>There are no reports pending for approval</p>
                                    <% } else { %>
                                    <% if (aPublicEnrollment >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=ePublic">
                                        <button class="btn btn-warning smallButtonStyle"><%=aPublicEnrollment%></button></a>
                                    <p class="expand">Enrollment in Public Schools</p>
                                    <% }
                                                if (aPrivateEnrollment >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=ePrivate">
                                        <button class="btn btn-warning smallButtonStyle"><%=aPrivateEnrollment%></button></a>
                                    <p class="expand">Enrollment in Private Schools</p>
                                    <% }
                                                if (aPublicDirectory >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=publicDirectory">
                                        <button class="btn btn-warning smallButtonStyle"><%=aPublicDirectory%></button></a>
                                    <p class="expand" id='directoryPublic' onclick="expand('directoryPublic');">Number of Teachers and Classrooms in Public Schools</p>
                                    <% }
                                                if (aPrivateDirectory >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=privateDirectory">
                                        <button class="btn btn-warning smallButtonStyle"><%=aPrivateDirectory%></button></a>
                                    <p class="expand" id='directoryPrivate' onclick="expand('directoryPrivate');">Number of Teachers and Classrooms in Public Schools</p>
                                    <% }
                                                if (aAgeGroup >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=byAgeGroupSex">
                                        <button class="btn btn-warning smallButtonStyle"><%=aAgeGroup%></button></a>
                                    <p class="expand">Household Population by Age Group and Sex</p>
                                    <% }
                                                if (aHighest >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=HighestCompleted">
                                        <button class="btn btn-warning smallButtonStyle"><%=aHighest%></button></a>
                                    <p class="expand" id='ahighest' onclick="expand('ahighest')">Household Population 5 Years Old and Over by Highest Grade/Year Completed, Age Group and Sex</p>
                                    <% }
                                                if (aMarital >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=maritalStatus">
                                        <button class="btn btn-warning smallButtonStyle"><%=aMarital%></button></a>
                                    <p class="expand" id='amarital' onclick="expand('amarital')">Household Population 10 Years Old and Over by Age Group, Sex, and Marital Status</p>
                                    <% }
                                                if (aNutritionalStatus >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=percentageDist">
                                        <button class="btn btn-warning smallButtonStyle"><%=aNutritionalStatus%></button></a>
                                    <p class="expand" id='anutrition' onclick='expand("anutrition")'>Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender</p>
                                    <% }
                                                if (aHospital >= 1) {%>
                                    <a href="${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=directoryHosptial"><button class="btn btn-warning smallButtonStyle"><%=aHospital%></button></a> <p class="expand">List of Hospitals</p>
                                        <% }
                                                } //END OF ELSE %>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- /.content-wrapper -->

        <!-- ./wrapper -->
        <script>
            function expand(x) {
                document.getElementById(x).classList.remove("expand");
            }
        </script>
        <script src="AdminLTE/plugins/fullcalendar/fullcalendar.min.js"></script>
        <script>
            $(document).ready(function () {
                var summary = {
                    valueNames: ['name', 'status', 'sector', 'date']
                };
                var summaryList = new List('pending', summary);
            });

            $(document).on("click", "#uploadData", function () {

                var formName = $(this).closest("tr").find(".ts").text();
                if (formName === "Enrollment in Public School") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=ePublic");
                } else if (formName === "Enrollment in Private School") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=ePrivate");
                } else if (formName === "Number of Teachers and Classrooms for Public Schools") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=publicDirectory");
                } else if (formName === "Number of Teachers and Classrooms for Private Schools") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataEducationServlet?redirect=privateDirectory");
                } else if (formName === "Household Population by Age Group and Sex") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
                } else if (formName === "Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=HighestCompleted");
                } else if (formName === "Household Population 10 years old & over by Age Group, Sex and Marital Status") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataDemoServlet?redirect=maritalStatus");
                } else if (formName === "Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=percentageDist");
                } else if (formName === "List of Hospitals") {
                    window.location.replace("${pageContext.request.contextPath}/RetrieveDataHealthServlet?redirect=directoryHosptial");
                }

            });
        </script>

    </body>
</html>
