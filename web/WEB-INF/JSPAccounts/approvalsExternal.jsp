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

        <title>Approvals | External</title>

    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        External Account Requests Approval
                    </h1>
                </section>

                <!--Actually di ko pala alam, Mali pa to-->
                <% if (request.getAttribute("page").equals("aExternal")) {%>
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
                                                <th>Username</th>
                                                <th>Reason for Access</th>
                                                <th>Actions</th>
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
                                                <td><%= users.get(i).getUsername()%></td>
                                                <td><%= users.get(i).getReason()%></td>
                                                <td style="float:right;"><button id="clickedApproved" class="btn btn-success"><span class="fa fa-check"></span></button>
                                                <button id="clickedReject"  class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button></td>

                                            </tr>
                                            <% } %>
                                            <tr>
                                                <td colspan="7"><a href="${pageContext.request.contextPath}/approvals?redirect=approvalAllExternal"><button class="btn btn-success" style="display:block; margin:auto"> Approve All </button></a></td>
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
    </body>
</html>
