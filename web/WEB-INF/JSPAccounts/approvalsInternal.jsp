<%--
    Document   : approvalsInternal
    Created on : 07 5, 16, 9:34:28 PM
    Author     : Geraldine
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsImportsITAdmin/ITAdminjsImports.js" type="text/javascript"></script>

        <title>Approvals | Internal</title>

    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Internal Account Requests Approval
                    </h1>
                </section>

                <!--Actually di ko pala alam, Mali pa to-->
                <% if (request.getAttribute("page").equals("ainternal")) {%>
                <div class="alert alert-success alert-dismissible" style="margin-top: 2%; width: 97%; display:block; margin-right: auto; margin-left: auto">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4><i class="icon fa fa-check"></i> Success!</h4>
                    You have successfully approved all requests
                </div>
                <%}%>

                <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users_array"); %>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12" style="margin-top: 1%">
                            <div class="box box-solid">
                                <!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table">
                                        <thead><tr>
                                                <th>Full Name</th>
                                                <th>Email</th>
                                                <!--<th>Username</th>-->
                                                <th>Division</th>
                                                <th>Position</th>
                                                <th>Employment Date</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (users.size() == 0) { %>
                                            <tr>
                                                <td colspan="7" style="text-align:center">There are no new accounts for approval.</td>
                                            </tr>
                                            <% } else {
                                                for (int i = 0; i < users.size(); i++) {%>
                                            <tr>
                                                <td class="nr" style="display:none;"><%= users.get(i).getUserID()%></td>
                                                <td><%= users.get(i).getFirstName()%> <%= users.get(i).getLastName()%></td>
                                                <td><%= users.get(i).getEmail()%></td>
                                                <td style="display:none;"><%= users.get(i).getUsername()%></td>
                                                <td>
                                                    <select id="division" name="division" class="form-control" onchange="updatePosition()">
                                                        <option disabled selected>Choose Division</option>
                                                        <option value="Social">Social Development Planning Division</option>
                                                        <option value="Physical">Physical Development Planning Division</option>
                                                        <option value="Others">Others Divisions</option>
                                                        <option value="Institutional">Institutional Development Planning Division</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="position_title" name="position" class="form-control" onchange="updateButtons()" style="width: 210px;" disabled>
                                                        <option disabled selected>Choose Position</option>
                                                    </select>
                                                </td>
                                                <td><input type="date" class="form-control" style="padding: 1%; width: 130px" id="employmentDate" name="employmentDate" onchange="updateButtons()" /></td>
                                                <td><button id="clickedApproved" class="btn btn-success" disabled><span class="fa fa-check"></span></button></td>
                                                <td><button id="clickedReject"  class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button></td>
                                            </tr>
                                            <% } %>
                                            <tr>
                                                <td colspan="8"><a href="${pageContext.request.contextPath}/approvals?redirect=approvalAllInternal"><button class="btn btn-success" style="display:block; margin:auto; text-alight: right; float:right;"> Approve All </button></a></td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>


                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </section>
                <!-- /.content-wrapper -->
            </div>

        </div>
        <!-- ./wrapper -->
        <script>

            function updatePosition() {
                var conceptName = $('#division').find(":selected").text();
                if (conceptName == "Social Development Planning Division") {
                    $('#position_title')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Position</option>')
                            .append('<option value="PDOI">Project Development Officer I</option>')
                            .append('<option value="PDOIII">Project Development Officer III</option>')
                            .append('<option value="PDOIV">Project Development Officer IV</option>')
                            .append('<option value="AideVI">Administrative Aide VI</option>')
//                            .val('whatever')
                            ;
                } else if (conceptName == "Physical Development Planning Division") {
                    $('#position_title')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Position</option>')
                            .append('<option value="POII">Planning Officer II</option>')
                            .append('<option value="POIII">Planning Officer III</option>')
                            .append('<option value="POIV">Planning Officer IV</option>')
                            .append('<option value="AideVI">Administrative Aide VI</option>')
//                            .val('whatever')
                            ;
                } else if (conceptName == "Institutional Development Planning Division") {
                    $('#position_title')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Statistician">Statistician I</option>')

//                            .val('whatever')
                            ;
                }
                $('#position_title').removeAttr('disabled');
            }
            function updateButtons() {
                if ($('#position_title').find(":selected").text() != 'Choose Position' &&
                       !(!Date.parse(document.getElementById('employmentDate').value ))){
                    $('#clickedApproved').removeAttr('disabled');
                }
            }
        </script>
    </body>
</html>
