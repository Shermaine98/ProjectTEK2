<%--
    Document   : notifcationModal
    Created on : Oct 9, 2016, 2:33:42 PM
    Author     : Shermaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="notificationModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div id="modalHeader" class="modal-header info">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 id="notificationHeader" class="notification-header"></h4>
                    </div>
                    <div id="notificationBodyModal" class="notification_body_modal">
                        <!-- Modal content-->
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
