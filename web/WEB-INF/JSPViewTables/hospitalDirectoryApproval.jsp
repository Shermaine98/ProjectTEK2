<%--
    Document   : hospitalDirectoryApproval
    Created on : Jun 15, 2016, 10:23:41 PM
    Author     : Geraldine
--%>

<%@page import="model.health.DirectoryHealth"%>
<%@page import="model.GlobalRecords"%>
<%@page import="model.Record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<%@include file="RejectModal_Health.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Facility | List of Hospitals</title>
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>
        <script src="jsHealthImports/UpdateInvalidHealthDirectory.js" type="text/javascript"></script>
        <script src="jsImported/ePreventEnter.js" type="text/javascript"></script>
        <script src="jsImported/directoryChecker.js" type="text/javascript"></script>
        <style>
            #map {
                height: 100%;
            }
            .controls {
                margin-top: 10px;
                border: 1px solid transparent;
                border-radius: 2px 0 0 2px;
                box-sizing: border-box;
                -moz-box-sizing: border-box;
                height: 32px;
                outline: none;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
            }

            #pac-input {
                background-color: #fff;
                font-family: Roboto;
                font-size: 15px;
                font-weight: 300;
                margin-left: 12px;
                padding: 0 11px 0 13px;
                text-overflow: ellipsis;
                width: 300px;
            }

            #pac-input:focus {
                border-color: #4d90fe;
            }

            .pac-container {
                font-family: Roboto;
            }

            #type-selector {
                color: #fff;
                background-color: #4d90fe;
                padding: 5px 11px 0px 11px;
            }

            #type-selector label {
                font-family: Roboto;
                font-size: 13px;
                font-weight: 300;
            }
            #target {
                width: 345px;
            }
            .width20{
                width: 30%;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <h3 style="text-align:center; margin-left: 2%; margin-right: 2%; margin-bottom: 3%;">Report for Approval | List of Hospitals</h3>

                        <%                    ArrayList<DirectoryHealth> directoryHealth = (ArrayList<DirectoryHealth>) request.getAttribute("directoryHealth");%>
                        <input type="hidden" id="formID" value="<%= directoryHealth.get(0).getFormID()%>" />

                        <div class="col-md-12">
                            <div class="box box-solid box-archived">
                                <div class="box-header">
                                    <h3 class="box-title">List of <b>Government Hospitals</b></h3>
                                </div>

                                <div class="box-body">
                                    <table id="approved" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                        <thead style="background-color: #454545; color: #fff">
                                            <tr>
                                                <th>Name of Hospital</th>
                                                <th>Address</th>
                                                <th>Telephone/Fax Number</th>
                                                <th>Total No. of Doctors</th>
                                                <th>Total No. of Nurses</th>
                                                <th>Total No. of Midwives</th>
                                                <th>No. of Beds</th>
                                                <th>classification</th>
                                                <th>Category</th>
                                                <th>accreditation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < directoryHealth.size(); i++) {
                                                    if (directoryHealth.get(i).getClassification().equalsIgnoreCase("Government Hospital")) {%>
                                            <tr>
                                                <td class="nr" width="25%" ><%=directoryHealth.get(i).getHospitalName()%></td>
                                                <td class="address" width="25%"><%=directoryHealth.get(i).getAddresss()%></td>
                                                <td class="telephone"><%=directoryHealth.get(i).getTelephone()%></td>
                                                <td class="doctor"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfDoctor())%></td>
                                                <td class="nurses"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfNurses())%></td>
                                                <td class="midwives"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfMidwives())%></td>
                                                <td class="bed"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfBeds())%></td>
                                                <td class="classification"><%=directoryHealth.get(i).getClassification()%></td>
                                                <td class="category"><%=directoryHealth.get(i).getCategory()%></td>
                                                <td class="accreditation"><%=directoryHealth.get(i).isAccreditation()%></td>

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
                                </div>
                                <div class="box-body">

                                    <table id="archived" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                        <thead style="background-color: #454545; color: #fff">
                                            <tr>
                                                <th>Name of Hospital</th>
                                                <th>Address</th>
                                                <th>Telephone/Fax Number</th>
                                                <th>Total No. of Doctors</th>
                                                <th>Total No. of Nurses</th>
                                                <th>Total No. of Midwives</th>
                                                <th>No. of Beds</th>
                                                <th>classification</th>
                                                <th>Category</th>
                                                <th>accreditation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < directoryHealth.size(); i++) {
                                                    if (directoryHealth.get(i).getClassification().equalsIgnoreCase("Private Hospital")) {%>
                                            <tr>
                                                <td class="nr" width="25%" ><%=directoryHealth.get(i).getHospitalName()%></td>
                                                <td class="address" width="25%" ><%=directoryHealth.get(i).getAddresss()%></td>
                                                <td class="telephone"><%=directoryHealth.get(i).getTelephone()%></td>
                                                <td class="doctor"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfDoctor())%></td>
                                                <td class="nurses"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfNurses())%></td>
                                                <td class="midwives"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfMidwives())%></td>
                                                <td class="bed"><%=directoryHealth.get(i).getFormatcount(directoryHealth.get(i).getNumberOfBeds())%></td>
                                                <td class="classification"><%=directoryHealth.get(i).getClassification()%></td>
                                                <td class="category"><%=directoryHealth.get(i).getCategory()%></td>
                                                <td class="accreditation"><%=directoryHealth.get(i).isAccreditation()%></td>
                                            </tr>
                                            <% }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <input id ="approvedReport" class="btn btn-flat btn-success" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Approve Report"/>
                                <input class="btn btn-flat btn-danger" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Reject Report" data-toggle="modal" data-target="#rejectModal"/>
                                <input type="hidden" id= "page" value="directoryApproval"/>
                            </div>
                            <form id="approveServlet" action="ApproveReportsHealth" method="post">
                                <input type="hidden" name="sUser" id="sUser" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="sformID" id="sformID" />
                                <input type="hidden" name="decision" value="approve" />
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>
    </body>
</html>
