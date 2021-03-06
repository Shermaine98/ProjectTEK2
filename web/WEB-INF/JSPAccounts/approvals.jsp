<%--
    Document   : approvals
    Created on : 10 9, 16, 9:20:39 PM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<%@include file="../JSPViewModal/notifcationModal.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Requests Approval</title>
        <script src="jsImportsITAdmin/ITAdminjsImports.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Account Requests Approval
                    </h1>
                </section>
                <%                    String type = request.getAttribute("type").toString();
                    ArrayList<User> internalUsers = (ArrayList<User>) request.getAttribute("internalUsers"); %>
                <% ArrayList<User> externalUsers = (ArrayList<User>) request.getAttribute("externalUsers"); %>

                <div class="col-md-12" style="margin-top: 2%;">
                    <% String notification = request.getAttribute("notification").toString();
                        if (notification.equalsIgnoreCase("approvedAllExternal")) {%>
                    <div class="callout callout-success">
                        <h4>Success!</h4>
                        <p>You have successfully approved all external users.</p>
                    </div>
                    <% } else if (notification.equalsIgnoreCase("approvedAllInternal")) { %>
                    <div class="callout callout-success">
                        <h4>Success!</h4>
                        <p>You have successfully approved all internal users.</p>
                    </div>
                    <% } %>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <% if (type.equals("default") || type.equals("internal")) { %>
                            <li class="active">
                                <% } else { %>
                            <li>
                                <%}%>
                                <a href="#internal" data-toggle="tab" style="width: 170px;">Internal Accounts <div id="iCount"><span  class="label label-warning" style="margin-left: 5%;"></span></div></a></li>

                            <% if (type.equals("external")) { %>
                            <li class="active">
                                <%} else { %>
                            <li>
                                <%}%>
                                <a href="#external" data-toggle="tab" style="width: 170px;">External Accounts <div id="eCount"> <span class="label label-warning" style="margin-left: 5%;"></span></div></a> </li>
                        </ul>

                        <div class="tab-content">
                            <% if (type.equals("default") || type.equals("internal")) { %>
                            <div class="active tab-pane" id="internal">
                                <%} else {%>
                                <div class="tab-pane" id="internal">
                                    <% } %>
                                    <% if (internalUsers.size() == 0) { %>
                                    <p align="center">There are no new accounts for approval.</p>
                                    <% } else { %>
                                    <form method="post" action="Approvals">
                                        <input hidden name="redirect" value="approveAllInternal"/>
                                        <table class="table table-bordered">
                                            <% for (int i = 0; i < internalUsers.size(); i++) {%>
                                            <tbody>
                                                <tr style="background-color: #454545; color: #fff; font-weight: bold;">
                                                    <td class="Inr" style="display:none;"><%=internalUsers.get(i).getUserID()%></td>
                                                    <td colspan="2" style="border-right:none;"><input hidden name="userID" value="<%=internalUsers.get(i).getUserID()%>"/><%=internalUsers.get(i).getFirstName()%> <%= internalUsers.get(i).getLastName()%></td>
                                                    <td class="buttons_class" colspan="2" style="border-left:none;">
                                                        <button type="button" style="float:right; text-align:right;" id="clickedRejectI"  class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove" style="margin-right: 3%"></span> Reject</button>
                                                        <button type="button" style="float:right; text-align:right; margin-right: 2%;" id="clickedApprovedI" class="btn btn-success btn-sm clickedApproved" disabled><span class="fa fa-check" style="margin-right: 3%"></span> Approve</button></td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td>Division</td>
                                                    <td>Position</td>
                                                    <td>Date of Employment</td>
                                                </tr>
                                                <tr>
                                                    <td width="25%"><%= internalUsers.get(i).getEmail()%></td>
                                                    <td style="display:none;"><%= internalUsers.get(i).getUsername()%></td>
                                                    <td width="35%">
                                                        <select required id="division" name="division" class="form-control divisionClass">
                                                            <option value="" disabled selected>Choose Division</option>
                                                            <option value="Social Development Planning Division">Social Development Planning Division</option>
                                                            <option value="Physical Development Planning Division">Physical Development Planning Division</option>
                                                            <!--<option value="Others">Others Divisions</option>-->
                                                            <option value="Institutional Development Planning Division">Institutional Development Planning Division</option>
                                                        </select>
                                                    </td>
                                                    <td class="select_position_td" width="25%">
                                                        <select required id="position_title"  name="position" class="form-control position_title_class" disabled>
                                                            <option value="" disabled selected>Choose Position</option>
                                                        </select>
                                                    </td>
                                                    <td width="10%">
                                                        <input type="date" class="form-control employmentDate_class" style="padding: 1%;"
                                                               id="employmentDate" name="employmentDate" required/></td>
                                                </tr>
                                            </tbody>
                                            <% }%>

                                        </table>

                                        <input  class="btn btn-success" style="display: block; margin: 0 auto; width: 12%;" type="submit" value="Approve All" />
                                        <% }%>
                                    </form>
                                </div>
                                <!-- /.tab-pane -->
                                <% if (type.equals("external")) { %>
                                <div class="active tab-pane" id="external">
                                    <%} else {%>
                                    <div class="tab-pane" id="external">
                                        <% } %>

                                        <% if (externalUsers.size() == 0) { %>
                                        <p align="center">There are no new accounts for approval.</p>
                                        <% } else {%>
                                        <form method="post" action="Approvals">
                                            <input hidden name="redirect" value="approvalAllExternal"/>
                                            <table class="table">
                                                <thead><tr>
                                                        <th>Full Name</th>
                                                        <th>Email</th>
                                                        <th>Username</th>
                                                        <th>Reason for Access</th>
                                                        <th style="text-align: right;">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (int i = 0; i < externalUsers.size(); i++) {%>
                                                    <tr>
                                                        <td class="nr" style="display:none;"><%= externalUsers.get(i).getUserID()%></td>
                                                        <td><%= externalUsers.get(i).getFirstName()%> <%= externalUsers.get(i).getLastName()%></td>
                                                        <td><%= externalUsers.get(i).getEmail()%></td>
                                                        <td><%= externalUsers.get(i).getUsername()%></td>
                                                        <td><%= externalUsers.get(i).getReason()%></td>
                                                        <td  style="float:right;">
                                                            <button type="button" id="clickedApprovedE" class="btn btn-success btn-sm"><span class="fa fa-check" style="margin-right: 3%;"></span> Approve</button>
                                                            <button type="button" id="clickedRejectE"  class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove" style="margin-right: 3%;"></span> Reject</button></td>
                                                    </tr>
                                                    <% }%>
                                                </tbody>
                                            </table>
                                            <input  class="btn btn-success" style="display: block; margin: 0 auto; width: 12%;" type="submit" value="Approve All" />
                                            <% }%>
                                        </form>
                                    </div>
                                    <!-- /.tab-pane -->
                                </div>
                                <!-- /.tab-content -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>

            $(document).on("change", ".divisionClass", function () {
                var conceptName = $(this).find("option:selected").text();
                var x = $(this).closest('tr').find('.select_position_td').find('#position_title');
                if (conceptName == "Social Development Planning Division") {
                    x.find('option').remove().end()
                            .append('<option disabled selected>Choose Position</option>')
                            .append('<option value="Project Development Officer I">Project Development Officer I</option>')
                            .append('<option value="Project Development Officer III">Project Development Officer III</option>')
                            .append('<option value="Project Development Officer IV">Project Development Officer IV</option>')
                            .append('<option value="Administrative Aide VI">Administrative Aide VI</option>')
                            //                         .val('whatever')
                            ;
                } else if (conceptName == "Physical Development Planning Division") {
                    x.find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Position</option>')
                            .append('<option value="Planning Officer II">Planning Officer II</option>')
                            .append('<option value="Planning Officer III">Planning Officer III</option>')
                            .append('<option value="Planning Officer IV">Planning Officer IV</option>')
                            .append('<option value="Administrative Aide VI">Administrative Aide VI</option>')
                            //                            .val('whatever')
                            ;
                } else if (conceptName == "Institutional Development Planning Division") {
                    x.find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Statistician I">Statistician I</option>')
                            //                            .val('whatever')
                            ;
                }

                $(this).closest('tr').find('.select_position_td').find('#position_title').removeAttr('disabled');
            });


            $(document).on("change", ".employmentDate_class", function () {
                console.log("Date changed: ");

                var x = $(this).closest('tr').find('.select_position_td').find('#position_title').find("option:selected").text();
                var buttons = $(this).closest('tbody').find('tr').find('.buttons_class').find('.clickedApproved');

                if (x != 'Choose Position' && !(!Date.parse($(this).val())))
                    buttons.removeAttr('disabled');
            });
        </script>
    </body>
</html>
