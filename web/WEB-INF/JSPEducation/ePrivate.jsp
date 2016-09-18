

<%@page import="Model.globalRecords"%>
<%@page import="Model.record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Upload Facility | Enrollment in Private Elementary Schools</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1><i class="fa fa-upload"></i> Upload Facility</h1>
                </section>

                <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                    <li class="title">Education</li>
                    <li class="title">Private</li>
                    <li class="active title">Enrollment in Private Elementary Schools</li>
                </ol>

                <%//SUCCESS SAVE IN DB WITHOUT ERRORS
                    String temp = (String) request.getAttribute("saveToDB");
                    if (temp.equalsIgnoreCase("successDB") || temp.equalsIgnoreCase("SaveWithError")) { %>
                <div class="callout callout-success">
                    <% if (temp.equalsIgnoreCase("successDB")) { %>

                    <h4>Success! There were no errors found in the report</h4>
                    <p>You have successfully saved a report</p>
                    <%
                    } //SUCCESS SAVE IN DB WITH ERRORS
                    else if (temp.equalsIgnoreCase("SaveWithError")) {
                    %>
                    <h4>Success! The report has been temporarily saved</h4>
                    <p>Kindly edit them later when you have already contacted the involved organization</p>
                    <%} %>
                </div>
                <%}//NOT SUCCESS SAVE IN DB WITH ERRORS
                else if (temp.equalsIgnoreCase("notSuccess") || temp.equalsIgnoreCase("ErrorMore") || temp.equalsIgnoreCase("ErrorGrand")) { %>
                <div class="callout callout-danger">
                    <% if (temp.equalsIgnoreCase("notSuccess")) {%>
                    <h4>The report has not been saved</h4>
                    <p>A problem was encountered while uploading your file</p>
                    <% } //NOT SUCCESS SAVE IN DB WITH MORE THAN 6 ERRORS
                    else if (temp.equalsIgnoreCase("ErrorMore")) {%>
                    <h4>There are errors in the uploaded file</h4>
                    <p>There are more errors than the System can handle. Kindly request for a new file</p>
                    <%} //NOT SUCCESS SAVE IN DB WITH MORE THAN 6 ERRORS
                    else if (temp.equalsIgnoreCase("ErrorGrand")) { %>
                    <h4>There are errors in the uploaded file</h4>
                    <p>The overall total is incorrect</p>
                    <%}%>
                </div>
                <%}else if (temp.equalsIgnoreCase("UploadError")) { %>
                    <div class="callout callout-danger">
                        <h4>The file uploaded is incorrect</h4>
                        <p>Kindly upload the correct file</p>
                    </div>
                    <%}%>

                <!--MODAL-->
                <div data-backdrop="static" id="showModalWarning" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Warning!</h4>
                            </div>
                            <div class="modal-body" id="modal-body">

                            </div>
                            <div class="modal-footer" id="modal-footer">

                            </div>
                        </div>

                    </div>
                </div>
                <!--END MODAL-->
                <!-- Main content -->

                <section class="content">
                    <div class="row">
                        <div class="col-md-5">

                            <div class="box box-solid" style="padding-bottom: 5%;">
                                <div class="box-header">
                                    <h3 class="box-title">Upload A New Report For The Year <input type="text" name="year" class="year" id="year" readonly /></h3>

                                </div>
                                <div class="box-body">
                                    <form id="UploadFileExcelEnrollment" action="UploadToDatabaseEducation" method="post" enctype="multipart/form-data">
                                        <br>
                                        <label for="file" id="filename" class="custom-file-upload btn btn-block btn-default">
                                            <i class="fa fa-cloud-upload"></i> Upload File
                                        </label>
                                        <input id="file" name="file" type="file" />
                                        <input  name="UploadFile" id= "UplaodFilePage" type="hidden" value="ELEMEnrollment" />
                                        <input  name="classification" id= "classification" type="hidden" value="ePrivate" />
                                        <input class="btn btn-flat btn-primary button-submit" id="btnsubmit" type="Submit" value="Submit" style="display:none"/>
                                    </form>
                                    <!--/form>-->
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!--End of LEFT COLUMN-->



                        <%
                            ArrayList<globalRecords> ErrRecords = (ArrayList<globalRecords>) request.getAttribute("IncompleteRecords");%>

                        <div class="col-md-7">
                            <div class="box box-solid">
                                <div class="box-header">
                                    <h3 class="box-title">Incomplete Reports</h3>
                                </div>
                                <div class="box-body">
                                    <form id="ErrorPage" action="" method="post">
                                        <table id="incomplete" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th>Missing Fields</th>
                                                    <th>Uploaded By</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (int i = 0; i < ErrRecords.size(); i++) {
                                                %>
                                                <tr>
                                                    <td class="nr" ><%=ErrRecords.get(i).getFormID()%></td>
                                                    <td><%=ErrRecords.get(i).getCensusYear()%></td>
                                                    <td><%=ErrRecords.get(i).getErrorLines()%></td>
                                                    <td><%=ErrRecords.get(i).getUploadedByByName()%></td>
                                                    <td><input align="center" id ="clickedMissing" class="btn btn-flat btn-primary" style="margin: 0 auto 5% auto; float:right;" type="button" value="Update"/></td>
                                                </tr>
                                                <% }

                                                %>
                                            </tbody>

                                        </table>
                                        <input id="page" name="page" type="hidden"  value="editErrorbyeClassroomFurnitures"/>
                                        <input id="iformID" name="formID" type="hidden" />
                                    </form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>

                        <!--End of RIGHT Box-->
                        <%                            ArrayList<record> records = (ArrayList<record>) request.getAttribute("validatedRecords");%>

                        <div class="col-md-12">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title"><b>Pending Reports for Approval:</b> Enrollment in Private Elementary Schools</h3>
                                </div>
                                <form id="archivedView" action="ViewReportsForApprovalEduc" method="post">
                                    <div class="box-body">
                                        <table id="approved" class="table table-striped dataTable" role="grid" aria-describedby="incomplete_info">
                                            <thead>
                                                <tr>
                                                    <th>Form ID</th>
                                                    <th>Census Year</th>
                                                    <th>Report Status</th>
                                                    <th>Uploaded By</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%

                                                    for (int i = 0; i < records.size(); i++) {%>
                                                <tr>
                                                    <td class="nr" ><%=records.get(i).getFormID()%></td>
                                                    <td><%=records.get(i).getCensusYear()%></td>
                                                    <td>For Approval</td>
                                                    <td><%=records.get(i).getUploadedByByName()%></td>
                                                    <td><input align="center" id ="clickedViewApproval" class="btn btn-flat btn-primary" style="margin: 0 auto 5% auto; float:right;" type="button" value="View Table"/></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                        <input id="page" name="page" type="hidden"  value="enrollment"/>
                                        <input id="aformID" name="formID" type="hidden" />
                                        <input  name="classification" value="Private" type="hidden" />

                                    </div>

                                </form>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                </section>
            </div>
        </div>
        <!-- ./wrapper -->
        <script src="jsImported/uploadJSP.js" type="text/javascript"></script>
        <script src="jsImported/jspDataTables_main.js" type="text/javascript"></script>
    </body>
</html>
