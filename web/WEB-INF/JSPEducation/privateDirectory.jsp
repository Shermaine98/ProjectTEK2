<%--
    Document   : eKinderPublic
    Created on : Jun 15, 2016, 10:23:41 PM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page import="model.education.DirectorySchool"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--IMPORTING HTML IMPORTS (bootstrap + scripts)-->
<%@include file="../levelOfAccess.jsp"%>
<%@ include file="directory_modal.jsp" %>
<%@ include file="../JSPViewModal/notifcationModal.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Facility | Number of Teachers and Classrooms for Private Schools</title>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>
        <!--AUTO COMPLETE-->
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsEducImports/searchSchoolPrivate.js" type="text/javascript"></script>
        <!--GEt YEAR-->
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <!--DELETE TABLE-->
        <script src="jsEducImports/directorydelete.js" type="text/javascript"></script>
        <script src="jsImported/directoryChecker.js" type="text/javascript"></script>


    </head>
    <body>

        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">


                        <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                            <li class="title">Education</li>
                            <li class="active title">Number of Teachers and Classrooms for Private Schools</li>
                        </ol>
                        <%//SUCCESS SAVE IN DB WITHOUT ERRORS
                            String temp = (String) request.getAttribute("saveToDB");
                            if (temp.equalsIgnoreCase("successDB")) { %>
                        <div class="callout callout-success" style="margin-bottom: 3%;">

                            <h4>Success! There were no errors found in the report</h4>
                            <p>You have successfully saved a report</p>
                        </div>
                        <%}//NOT SUCCESS SAVE IN DB
                        else if (temp.equalsIgnoreCase("notSuccess")) {%>
                        <div class="callout callout-danger" style="margin-bottom: 3%;">
                            <h4>The report has not been saved</h4>
                            <p>A problem was encountered while saving your file</p>
                        </div>
                        <%}%>
                        <div style=" margin: 0 auto; display:block; text-align: center">
                            <div class="form-inline">
                                <div class="form-group" style="background: transparent;">
                                    <input style="width:  400px" id="schoolNameSearch" type="text" class="form-control" placeholder="Search School Name" onkeypress="autoCompleteSchool()" />
                                    <button class="btn btn-default" onClick="setSchoolData()"><span class="glyphicon glyphicon-search"></span></button>
                                    <button class="btn btn-default" id="viewAll" disabled="disabled" onclick="viewAll()">View All</button>
                                    <!-- Trigger the modal with a button -->
                                    <button class="btn btn-primary" id="addnew" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span> Add New</button>
                                    <form id="submitAll" action="UpdateSchoolDirectory" method="post" class="pull-right">
                                        <input type="hidden" name="redirect" value="submitAll"/>
                                        <input type="hidden" name="censusYear" id= "year"/>
                                        <input type="hidden" name="uploadedBy" value="<%=user.getUserID()%>"/>
                                        <input type="hidden" id= "classification" name="classification" value="private"/>
                                        <input class="btn btn-primary" id="btnsubmit" type="Submit" value="Submit Private Schools Report"/>
                                    </form>
                                </div>
                            </div>

                        </div>


                        <!--MODAL-->
                        <div data-backdrop="static" id="showModalWarning" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Warning!</h4>
                                    </div>
                                    <div class="modal-body" id="modal-body">

                                    </div>
                                    <div class="modal-footer" id="footer">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--END MODAL-->
                        <br/>

                        <%                            ArrayList<DirectorySchool> directorySchool = (ArrayList<DirectorySchool>) request.getAttribute("directory");%>

                        <div class="DT" id="SchoolDirectory">

                            <table id="approved" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                <%
                                    for (int i = 0; i < directorySchool.size(); i++) {
                                %>

                                <tbody>
                                    <tr style = "background-color: #454545; color: #fff" >
                                        <th colspan="2" style="vertical-align: bottom; text-align: left;" >Name of School</th>
                                        <td class="nr" colspan = "7" style="border-right: none; text-align: left;"> <%=directorySchool.get(i).getSchoolName()%></td>
                                        <td style="border-left: none; text-align: right">
                                            <button id="updateDirectory" class="upadateBtn btn btn-success btn-sm" ><span class="fa fa-edit"></span> Edit</button>
                                            <button id="invalidDirectory"  class="deleteBtn btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> Remove</button>
                                        </td>
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
                                        <td class="censusYear" ><input type="hidden" id="censusYear" value="<%=directorySchool.get(i).getCensusYear()%>"/><%= directorySchool.get(i).getFormatcount(totalKinder + totalElem)%></td>
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
                        </div>

                        <input type="hidden" id= "page" value="schoolDirectory"/>

                        <div class="DT" id="dataHolder">

                        </div>
                    </div>
                </section>
            </div>
        </div>

        <script>

                $(document).ready(function () {
                $('#classification').val("Private");
            });
            function viewAll() {
                $('#dataSchool').remove();

                document.getElementById('SchoolDirectory').style.display = "block";
                $('#viewAll').attr('disabled', 'disabled');
            }
        </script>
    </body>
</html>
