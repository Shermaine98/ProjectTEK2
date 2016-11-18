<%--
    Document   : ProfileModal
    Created on : Jul 21, 2016, 8:17:35 PM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>
<%@page import="model.accounts.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="profileModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header marooon">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">My Profile</h4>
                    </div>
                    <form action="ChangePassword" method="post">
                        <div class="modal-body" id="modal-body2">
                            <% User chck2 = (User) session.getAttribute("user");%>
                            <input hidden name="username" value="<%=chck2.getUsername()%>" />
                            <div style="margin: 0 auto; width: 70%">
                                <div class="form-inline" style="margin-bottom: 2%;">
                                    <label for="oldPassword" style="margin-right: 41px;">Old Password: </label>
                                    <input type="password" name="oldPassword" class="form-control"/>
                                </div>
                                <div class="form-inline" style="margin-bottom: 2%;">
                                    <label for="newPassword" style="margin-right: 35px;">New Password: </label>
                                    <input type="password" name="newPassword" class="form-control" />
                                </div>
                                <div class="form-inline" style="margin-bottom: 2%;">
                                    <label for="newPassword1" style="margin-right: 13px;">Confirm Password: </label>
                                    <input type="password" name="newPassword1" class="form-control" />
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" id="modal-footer2">
                            <button type="submit" class="btn btn-success" >Save</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
