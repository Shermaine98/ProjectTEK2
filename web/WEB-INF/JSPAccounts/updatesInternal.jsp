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
        <title>Update Accounts | Internal</title>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Update Internal Accounts
                    </h1>
                </section>

                <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users_array"); %>
                
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12" style="margin-top: 2%">
                            <div class="box box-solid">
                                
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tbody><tr>
                                                <th>Full Name</th>
                                                <th>Email</th>
                                                <th>Username</th>
                                                <th>Division</th>
                                                <th>Position</th>
                                                <th></th>
                                            </tr>
                                            <% if (users.size() == 0) { %>
                                            <tr>
                                                <td colspan="7" style="text-align:center">There are currently no internal accounts.</td>
                                            </tr>
                                            <% } else {
                                                for (int i = 0; i < users.size(); i++) {%>
                                            <tr>
                                                <td><%= users.get(i).getFirstName()%> <%= users.get(i).getLastName()%></td>
                                                <td><%= users.get(i).getEmail()%></td>
                                                <td><%= users.get(i).getUsername()%></td>
                                                <td><%= users.get(i).getDivision()%></td>
                                                <td><%= users.get(i).getPosition()%></td>
                                                <td>
                                                    <button class="btn btn-default" style="float:right">Update</button>
                                                </td>
                                            </tr>
                                            <% }} %>
                                        </tbody></table>
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
