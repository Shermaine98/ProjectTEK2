<%--
    Document   : updateAccounts
    Created on : 10 12, 16, 9:57:39 PM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Accounts</title>
        <script>
            $(document).ready(function () {
                $('#internalTable').DataTable();
            });
            $(document).ready(function () {
                $('#externalTable').DataTable();
            });
        </script>
        <link href="cssImported/uikit.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Update Accounts
                    </h1>
                </section>
                <% ArrayList<User> internalUsers = (ArrayList<User>) request.getAttribute("internalUsers"); %>
                <% ArrayList<User> externalUsers = (ArrayList<User>) request.getAttribute("externalUsers");%>

                <section class="content">
                    <div class="row">
                        <div class="col-md-12" style="margin-top: 2%;">

                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#internal" data-toggle="tab" style="width: 170px;">Internal Accounts<span class="label label-default" style="margin-left: 5%;"><%=internalUsers.size()%></span></a></li>
                                    <li><a href="#external" data-toggle="tab" style="width: 170px;">External Accounts<span class="label label-default" style="margin-left: 5%;"><%=externalUsers.size()%></span></a></li>
                                </ul>

                                <div class="tab-content">
                                    <div class="active tab-pane" id="internal">
                                        <form>
                                        <table class="uk-table uk-table-hover uk-table-striped" cellspacing="0" width="100%" id="internalTable">
                                            <thead>
                                                <tr>
                                                    <th>Full Name</th>
                                                    <th>Email</th>
                                                    <th>Username</th>
                                                    <th>Division</th>
                                                    <th>Position</th>
                                                    <th style="text-align:right;">For Your Action</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <% if (internalUsers.size() == 0) { %>
                                                <tr>
                                                    <td colspan="7" style="text-align:center">There are currently no internal accounts.</td>
                                                </tr>
                                                <% } else {
                                                    for (int i = 0; i < internalUsers.size(); i++) {%>
                                                <tr>
                                                    <td><input required name="name" class="form-control edit" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= internalUsers.get(i).getFirstName()%> <%= internalUsers.get(i).getLastName()%>"/></td>
                                                    <td><input required name="email" class="form-control edit" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= internalUsers.get(i).getEmail()%>"/></td>
                                                    <td><input required name="username" class="form-control edit" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= internalUsers.get(i).getUsername()%>"/></td>
                                                    <td><input required name="division" class="form-control edit" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= internalUsers.get(i).getDivision()%>"/></td>
                                                    <td><input required name="position" class="form-control edit" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= internalUsers.get(i).getPosition()%>"/></td>
                                                    <td>
                                                        <button type="button" class="btn btn-success" style="float:right">Update</button>
                                                    </td>
                                                </tr>
                                                <% }
                                                    }%>
                                            </tbody>
                                       </table> </form>
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="external">
                                        <table class="uk-table uk-table-hover uk-table-striped" cellspacing="0" width="100%" id="externalTable">
                                            <thead>
                                                <tr>
                                                    <th>Full Name</th>
                                                    <th>Email</th>
                                                    <th>Username</th>
                                                    <th style="text-align:right;">For Your Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% if (externalUsers.size() == 0) { %>
                                                <tr>
                                                    <td colspan="4" style="text-align:center">There are currently no external accounts.</td>
                                                </tr>
                                                <% } else {
                                                    for (int i = 0; i < externalUsers.size(); i++) {%>
                                                <tr>
                                                    <td><input name="name" class="form-control" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= externalUsers.get(i).getFirstName()%> <%= externalUsers.get(i).getLastName()%>"/></td>
                                                    <td><input name="email" class="form-control" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= externalUsers.get(i).getEmail()%>"/></td>
                                                    <td><input name="username" class="form-control" style="width:100%; background: none; border: none;" readonly
                                                               value="<%= externalUsers.get(i).getUsername()%>"/></td>
                                                    <td>
                                                        <button class="btn btn-success" style="float:right">Update</button>
                                                    </td>
                                                </tr>
                                                <% }
                                                    }%>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.tab-pane -->
                                </div>
                                <!-- /.tab-content -->
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
