<%--
    Document   : RejectModal
    Created on : 07 19, 16, 12:50:08 AM
    Author     : Geraldine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            textarea{
                resize: none;
                width: 100%;
            }
            .boooox{
                margin:0 auto;
                display:block;
                width: 70%;
            }
            .marooon{
                background-color: #962121;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <div id="rejectModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <form id="rejectServlet" action="ApproveDemoServlet" method="post">
                    <div class="modal-content">
                        <div class="modal-header marooon">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Are you sure you want to reject this report?</h4>
                        </div>
                        <div class="modal-body" id="modal-body">

                            <div class='boooox'>
                                <p align='center'>You have chosen to reject this report. <br/>
                                    Please select the reason for rejection of the report below:</p>
                                <select class="form-control" name="comments" id='textReason'>
                                    <option disabled selected>Choose reason for rejection</option>
                                    <option value="The uploaded report contains outliers">The uploaded report contains outliers</option>
                                    <option value="The uploaded report is the same as the report in the past years">The uploaded report is the same as the report in the past years</option>
                                    <option value="There is an updated version of the report">There is an updated version of the report</option>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer" id="modal-footer">
                            <button type="button" class="btn btn-success" data-dismiss="modal" id="rejectReport" >Submit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <input type="hidden" name="sUser" id="rUser"/>
                    <input type="hidden" name="sformID" id="rformID" />
                    <input type="hidden" name="decision" value="reject" />
                </form>
            </div>
        </div>
        <script>

          

                $('#rejectReport').click(function () {
                    var fromID = $('#formID').val();
                    var rUser = $('#sUser').val();
                    document.getElementById('rformID').setAttribute('value', fromID);
                    document.getElementById('rUser').setAttribute('value', rUser);
                    document.getElementById('rejectServlet').submit();
                });
        </script>
    </body>
</html>
