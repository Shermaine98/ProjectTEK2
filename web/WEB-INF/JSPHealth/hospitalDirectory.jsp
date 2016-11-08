<%--
    Document   : eKinderPublic
    Created on : Jun 15, 2016, 10:23:41 PM
    Author     : Geraldine
--%>

<%@page import="model.health.DirectoryHealth"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Facility | List of Hospitals</title>
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>
        <script src="jsHealthImports/UpdateInvalidHealthDirectory.js" type="text/javascript"></script>
        <script src="jsImported/ePreventEnter.js" type="text/javascript"></script>
        <script src="jsImported/directoryChecker.js" type="text/javascript"></script>

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
                    <li class="title">Health</li>
                    <li class="active title">List of Hospitals</li>
                </ol>
                <%//SUCCESS SAVE IN DB WITHOUT ERRORS
                    String temp = (String) request.getAttribute("saveToDB");
                    if (temp.equalsIgnoreCase("successDB")) { %>
                <div class="callout callout-success">

                    <h4>Success! There were no errors found in the report</h4>
                    <p>You have successfully saved a report</p>
                </div>
                <%}//NOT SUCCESS SAVE IN DB
                else if (temp.equalsIgnoreCase("notSuccess")) {%>
                <div class="callout callout-danger">
                    <h4>The report has not been saved</h4>
                    <p>A problem was encountered while saving your file</p>
                </div>
                <%}%>
                <section class="content">
                    <div class="row">
                        <!-- ADD  NEW Modal -->
                        <div id="myModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <form action="UpdateHealthDirectory" method="post">
                                        <input type="hidden" name="redirect" value="addNew">
                                        <input type="hidden" name="uploadedBy" value="<%=user.getUserID()%>"/>
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Add New Hospital</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Please input hospital details below:</p>
                                            <div class="form-inline" style="margin-top:3%;">
                                                <label class="width20">Classification: </label>
                                                <select required class="form-control" style="width: 65%" name="classification">
                                                    <option value=""></option>
                                                    <option value="Government Hospital">Government Hospital</option>
                                                    <option value="Private Hospital">Private Hospital</option>
                                                </select><br/><br/>
                                                <label class="width20">Category </label>
                                                <select required class="form-control" style="width: 65%" name="category">
                                                    <option value=""></option>
                                                    <option value="Level 1">Level 1</option>
                                                    <option value="Level 2">Level 2</option>
                                                    <option value="Level 3">Level 3</option>
                                                </select><br/><br/>
                                                <label class="width20">Name of Hospital: </label> <input name="hospitalName" type="text" class="form-control" style="width: 65%" required  /><br/><br/>
                                                <label class="width20">Address: </label> <input type="text" class="form-control" name="address" id="inputAddress" style="width: 65%" required/><br/><br/>
                                                <label class="width20">Telephone/Fax Number: </label> <input type="text" name="tel" class="form-control" style="width: 65%" required /><br/><br/>
                                            </div>
                                            <table id="addhospitalT" class="table">
                                                <thead>
                                                <th>Total No. of Doctors</th>
                                                <th>Total No. of Nurses</th>
                                                <th>Total No. of Midwives</th>
                                                <th>No. of Beds</th>
                                                </thead>
                                                <tbody>
                                                <td><input type="text" class="form-control" name="doctors" id="doctors"  onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>
                                                <td><input type="text" class="form-control" name="nurses"  id="nurses" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>
                                                <td><input type="text" class="form-control" name="midwives" id="midwives" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>
                                                <td><input type="text" class="form-control" name="beds" id="beds" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required  /></td>
                                                </tbody>
                                            </table>
                                            <!--<div class="form-inline" style="margin-top:3%;">-->
                                            <div style="float:left">
                                                <label for="accredited">Accreditation: </label>
                                                <input type="radio" name="accredited" value="true"> True<br>
                                            </div>
                                            <input style="display: inline; margin-left: 3%;" type="radio" name="accredited" value="false"> False<br>
                                            <!--</div>-->
                                            <div class="form-inline" style="margin-top:3%;">
                                                <input style="margin-right: 3%;" class="form-control" name="lat" id="lat" type="hidden" value="0"  />
                                                <input class="form-control" name="long" id="long" type="hidden" value="0"   />
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button style="float: left;" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <input style="margin:0; float:right;" class="btn btn-primary" id="btnsubmit" type="Submit" value="Submit" />
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                        <!-- END ADD  NEW Modal -->

                        <!--MODAL UPDATE-->
                        <div id="UpdateModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content" style="width: 550px;">
                                    <form action="UpdateHealthDirectory" method="post">
                                        <div class="modal-header">
                                            <input type="hidden" name="redirect" value="updateData">
                                            <input type="hidden" name="uploadedBy" value="<%=user.getUserID()%>"/>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Update Hospital Details</h4>
                                        </div>
                                        <div class="modal-body" id="updateBody">

                                            <table id="addhospitalT" class="table table-bordered" style="margin-top:2%;">
                                                <thead style="background-color: #454545; color: #fff">
                                                    <tr>
                                                        <th>Total No. of Doctors</th>
                                                        <th>Total No. of Nurses</th>
                                                        <th>Total No. of Midwives</th>
                                                        <th>No. of Beds</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tableUpdateBody">

                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <input class="btn btn-primary" id="btnsubmit" type="Submit" value="Submit" />
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                        <!--END MODAL UPDATE-->


                        <div id="showModalWarning" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Warning!</h4>
                                    </div>
                                    <div class="modal-body" id="modal-body">

                                    </div>
                                    <div class="modal-footer" id="footer">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Trigger the modal with a button -->
                        <!--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="text-align:center; display: block; margin: 0 auto 1% auto"><span class="glyphicon glyphicon-plus"></span> Add New</button>-->


                        <%                    ArrayList<DirectoryHealth> directoryHealth = (ArrayList<DirectoryHealth>) request.getAttribute("directoryHealth");%>

                        <div class="col-md-12">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">List of <b>Government Hospitals</b></h3>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right;"><span class="glyphicon glyphicon-plus"></span> Add New</button>
                                </div>

                                <div class="box-body">
                                    <table id="approved" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                        <thead style="background-color: #454545; color: #fff">
                                            <tr>
                                                <th style="vertical-align:top;">Name of Hospital</th>
                                                <th style="vertical-align:top;">Address</th>
                                                <th style="vertical-align:top;">Telephone/Fax Number</th>
                                                <th style="vertical-align:top;">Total No. of Doctors</th>
                                                <th style="vertical-align:top;">Total No. of Nurses</th>
                                                <th style="vertical-align:top;">Total No. of Midwives</th>
                                                <th style="vertical-align:top;">No. of Beds</th>
                                                <th style="display:none;">classification</th>
                                                <th style="vertical-align:top;">Category</th>
                                                <th style="vertical-align:top;">Accreditation</th>
                                                <th style="vertical-align:top;">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < directoryHealth.size(); i++) {
                                                    if (directoryHealth.get(i).getClassification().equalsIgnoreCase("Government Hospital")) {%>
                                            <tr>
                                                <td class="nr" width="20%"><%=directoryHealth.get(i).getHospitalName()%></td>
                                                <td class="address" width="15%" ><%=directoryHealth.get(i).getAddresss()%></td>
                                                <td class="telephone"><%=directoryHealth.get(i).getTelephone()%></td>
                                                <td class="doctor" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfDoctor())%></td>
                                                <td class="nurses" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfNurses())%></td>
                                                <td class="midwives" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfMidwives())%></td>
                                                <td class="bed" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfBeds())%></td>
                                                <td style="display:none;" class="classification"><%=directoryHealth.get(i).getClassification()%></td>
                                                <td class="category"><%=directoryHealth.get(i).getCategory()%></td>
                                                <td class="accreditation"><%=directoryHealth.get(i).isAccreditation()%></td>
                                                <td width="15%">
                                                    <input type="hidden" id="censusYear" value="<%=directoryHealth.get(i).getYear()%>"/>
                                                    <button id="invalidDirectory"  class="btn btn-danger btn-flat btn-sm pull-right" style="width:50%;"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                                                    <button id="edit"  class="btn btn-success btn-flat btn-sm pull-right" style="width:50%;"><span class="fa fa-edit"></span> Edit</button></td>
                                            </tr>
                                            <% }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">List of <b>Private Hospitals</b></h3>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right;"><span class="glyphicon glyphicon-plus"></span> Add New</button>
                                </div>
                                <div class="box-body">

                                    <table id="archived" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                        <thead style="background-color: #454545; color: #fff">
                                            <tr>
                                                <th style="vertical-align:top;">Name of Hospital</th>
                                                <th style="vertical-align:top;">Address</th>
                                                <th style="vertical-align:top;">Telephone/Fax Number</th>
                                                <th style="vertical-align:top;">Total No. of Doctors</th>
                                                <th style="vertical-align:top;">Total No. of Nurses</th>
                                                <th style="vertical-align:top;">Total No. of Midwives</th>
                                                <th style="vertical-align:top;">No. of Beds</th>
                                                <th style="display:none;">classification</th>
                                                <th style="vertical-align:top;">Category</th>
                                                <th style="vertical-align:top;">Accreditation</th>
                                                <th style="vertical-align:top;">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < directoryHealth.size(); i++) {
                                                    if (directoryHealth.get(i).getClassification().equalsIgnoreCase("Private Hospital")) {%>
                                            <tr>
                                                <td class="nr" width="20%"><%=directoryHealth.get(i).getHospitalName()%></td>
                                                <td class="address" width="15%" ><%=directoryHealth.get(i).getAddresss()%></td>
                                                <td class="telephone"><%=directoryHealth.get(i).getTelephone()%></td>
                                                <td class="doctor" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfDoctor())%></td>
                                                <td class="nurses" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfNurses())%></td>
                                                <td class="midwives" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfMidwives())%></td>
                                                <td class="bed" style="text-align:center;"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfBeds())%></td>
                                                <td style="display:none;" class="classification"><%=directoryHealth.get(i).getClassification()%></td>
                                                <td class="category"><%=directoryHealth.get(i).getCategory()%></td>
                                                <td class="accreditation"><%=directoryHealth.get(i).isAccreditation()%></td>
                                                <td width="15%">
                                                    <input type="hidden" id="censusYear" value="<%=directoryHealth.get(i).getYear()%>"/>
                                                    <button id="invalidDirectory"  class="btn btn-danger btn-flat btn-sm pull-right" style="width:50%;"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                                                    <button id="edit"  class="btn btn-success btn-flat btn-sm pull-right" style="width:50%;"><span class="fa fa-edit"></span> Edit</button></td>
                                            </tr>
                                            <% }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                    <input type="hidden" id= "page" value="hospital"/>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div align="center" style="margin-bottom: 2%;">
                        <form id="submitAll" action="UpdateHealthDirectory" method="post">
                            <input class="btn btn-primary" id="btnsubmit" type="Submit" value="Submit List of Hospitals Report" />
                            <input type="hidden" name="redirect" value="submitAll"/>
                            <input type="hidden" name="uploadedBy" value="<%=user.getUserID()%>"/>
                            <input type="hidden" id= "classification" name="classification" value="hospital"/>
                        </form>
                    </div>
                </section>

            </div>
        </div>
    </body>
</html>
