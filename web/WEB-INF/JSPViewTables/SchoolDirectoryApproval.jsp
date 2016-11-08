<%--
    Document   : eKinderPublic
    Created on : Jun 15, 2016, 10:23:41 PM
    Author     : Geraldine, Gian, Shermaine
--%>

<%@page import="model.education.DirectorySchool"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--IMPORTING HTML IMPORTS (bootstrap + scripts)-->
<%@include file="../levelOfAccess.jsp"%>
<%@include file="RejectModal_Education.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Approval | Number of Teachers and Classrooms</title>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>
        <!--AUTO COMPLETE-->
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsEducImports/searchSchoolPrivate.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">


                        <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                            <li class="title">Education</li>
                                <%                                    String classification = (String) request.getAttribute("classification");
                                    if (classification.equalsIgnoreCase("private")) {
                                %>
                            <li class="active title">Number of Teachers and Classrooms for Private Schools</li>
                                <%} else {%>
                            <li class="active title">Number of Teachers and Classrooms for Public Schools</li>
                                <%}%>
                        </ol>

                        <div style=" margin: 0 auto; display:block; text-align: center">
                            <div class="form-inline">
                                <div class="form-group" style="background: transparent;">
                                    <input style="width:  400px" id="schoolNameSearch" type="text" class="form-control" placeholder="Search School Name" onkeypress="autoCompleteSchool()" />
                                    <button class="btn btn-default" onClick="setSchoolData()"><span class="glyphicon glyphicon-search"></span></button>
                                    <button class="btn btn-default" id="viewAll" disabled="disabled" onclick="viewAll()">View All</button>
                                    <!-- Trigger the modal with a button -->
                                </div>
                            </div>
                        </div>
                        <br/>

                        <%
                            ArrayList<DirectorySchool> directorySchool = (ArrayList<DirectorySchool>) request.getAttribute("directorySchool");%>

                        <div class="DT" id="SchoolDirectory">                                <input type="hidden" id="censusYear" value="<%=directorySchool.get(0).getCensusYear()%>"/>
                              <input type="hidden" id="formID" value="<%= directorySchool.get(0).getFormID()%>" />

                            <table id="approved" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                <%
                                    for (int i = 0; i < directorySchool.size(); i++) {
                                %>
                                <tbody>
                                    <tr style = "background-color: #454545; color: #fff" >
                                        <th style="vertical-align: bottom; text-align: left;" >Name of School</th>
                                        <td class="nr" colspan = "9" style="border-right: none; text-align: left;"> <%=directorySchool.get(i).getSchoolName()%></td>

                                    </tr>
                                    <tr>
                                        <th colspan="6">Teachers</th>
                                        <th colspan="2">Classrooms</th>
                                        <th colspan="2">Seats</th>
                                    </tr>
                                    <tr>
                                        <th colspan="3">Kinder</th>
                                        <th colspan="3">Elementary</th>
                                        <th rowspan="2" style="vertical-align: bottom;
                                            border-left: solid;
                                            border-width: thin;
                                            border-color: #d2d6de;
                                            padding: 1%;">Kinder</th>
                                        <th rowspan="2" style="vertical-align: bottom; ">Elementary</th>
                                        <th rowspan="2" style="vertical-align: bottom; ">Kinder</th>
                                        <th rowspan="2" style="vertical-align: bottom; ">Elementary</th>

                                    </tr>
                                    <tr>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                    </tr>
                                    <tr>

                                        <!--Teachers-->
                                        <% int totalKinder = 0;
                                            int totalElem = 0;
                                            for (int y = 0; y < directorySchool.get(i).getTeacher().size(); y++) {%>
                                        <% if (directorySchool.get(i).getTeacher().get(y).getGradeLevel().equals("Kinder")) {%>
                                        <td class="KtMale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount())%></td>
                                        <td class="KtFemale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <td class="KTotal"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <% totalKinder = directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount();
                                            }
                                            if (directorySchool.get(i).getTeacher().get(y).getGradeLevel().equals("Elementary")) {%>
                                        <td class="EtMale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount())%></td>
                                        <td class="EtFemale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <td class="ETotal"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <% totalElem = directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount();
                                                }

                                            }%>

                                        <!--Classrooms-->
                                        <% int totalClassroom = 0;
                                            for (int y = 0; y < directorySchool.get(i).getElemClassrooms().size(); y++) {
                                                if (directorySchool.get(i).getElemClassrooms().get(y).getGradeLevel().equals("Kinder")) {
                                        %>
                                        <td class="KClassroom"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount())%></td>
                                        <% totalClassroom += directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount();  %>
                                        <% }
                                            }
                                            for (int y = 0; y < directorySchool.get(i).getElemClassrooms().size(); y++) {
                                                if (directorySchool.get(i).getElemClassrooms().get(y).getGradeLevel().equals("Elementary")) {%>
                                        <td class="EClassroom"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount())%></td>
                                        <% totalClassroom += directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount();  %>
                                        <% } %>
                                        <% }%>

                                        <!--<td></td>-->

                                        <!--Seats-->
                                        <% int totalSeats = 0;
                                            for (int y = 0; y < directorySchool.get(i).getSeats().size(); y++) {
                                                if (directorySchool.get(i).getSeats().get(y).getGradeLevel().equals("Kinder")) {%>
                                        <td class="Kseats"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getSeats().get(y).getSeatCount())%></td>
                                        <% totalSeats += directorySchool.get(i).getSeats().get(y).getSeatCount();  %>
                                        <% }
                                            }
                                            for (int y = 0; y < directorySchool.get(i).getSeats().size(); y++) {
                                                if (directorySchool.get(i).getSeats().get(y).getGradeLevel().equals("Elementary")) {%>
                                        <td class="Eseats"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getSeats().get(y).getSeatCount())%></td>
                                        <% totalSeats += directorySchool.get(i).getSeats().get(y).getSeatCount();  %>
                                        <% }
                                            }%>
                                        <!--<td></td>-->
                                    </tr>
                                    <tr>
                                        <th colspan="5">Total Teachers</th>
                                        <td><%= directorySchool.get(i).getFormatcount(totalKinder + totalElem)%></td>
                                        <th>Total Classrooms</th>
                                        <td><%= directorySchool.get(i).getFormatcount(totalClassroom)%> </td>
                                        <th>Total Seats</th>
                                        <td><%= directorySchool.get(i).getFormatcount(totalSeats)%></td>
                                    </tr>

                                </tbody>
                                <%
                                    }
                                %>

                            </table>
                            <input id ="approvedReport" class="btn btn-flat btn-success" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Approve Report"/>
                            <input class="btn btn-flat btn-danger" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Reject Report" data-toggle="modal" data-target="#rejectModal"/>
                        </div>
                        <form id="approveServlet" action="ApprovalsEducation" method="post">
                            <input type="hidden" name="sUser" id="sUser" value="<%= user.getUserID()%>" />
                            <input type="hidden" name="sformID" id="sformID" />
                            <input type="hidden" name="decision" value="approve" />
                        </form>

                        <input type="hidden" id= "page" value="schoolDirectory"/>
                        <div class="DT" id="dataHolder">
                        </div>

                    </div>
                </section>
            </div>
        </div>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>

        <script>
                                        function viewAll() {
                                            $('#dataSchool').remove();

                                            document.getElementById('SchoolDirectory').style.display = "block";
                                            $('#viewAll').attr('disabled', 'disabled');
                                        }
        </script>
    </body>
</html>
