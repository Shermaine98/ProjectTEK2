<%--
    Document   : showTables
    Created on : Jun 21, 2016, 12:14:03 AM
    Author     : Geraldine, Gian, Shermaine
--%>

<%@page import="model.demo.HighestCompleted"%>
<%@page import="model.demo.ByAgeGroupSex"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<%@include file="RejectModal_Demo.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Archived Report</title>
        <link href="cssImported/ValidateCSS.css" rel="stylesheet" type="text/css"/>

         <!--Pace Imports-->
        <script src="AdminLTE/plugins/pace/pace.js" type="text/javascript"></script>
        <link href="AdminLTE/plugins/pace/dataurl.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <div class="wrapper">
            <!--Content Wrapper. Contains page content-->
            <div id="top" class="content-wrapper">
                <section class="content">
                    <div class="row">

                        <%                            ArrayList<HighestCompleted> highest = (ArrayList<HighestCompleted>) request.getAttribute("highestAttaintmentData");
                            String pageTitle = request.getAttribute("clicked").toString();
                        %>

                        <h3 style="text-align:center; margin-left: 2%; margin-right: 2%;">
                            <% if (pageTitle.equals("approvalAdmin")) {%>
                            Report for Approval | Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex for <%= highest.get(0).getYear()%>
                            <% } %>
                            <% if (pageTitle.equals("forView")) {%>
                            Saved Report | Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex for <%= highest.get(0).getYear()%>
                            <% }%>
                        </h3>

                        <input type="hidden" id="formID" value="<%= highest.get(0).getFormID()%>" />

                        <div class="DT">
                            <table class="table table-bordered">
                                <tbody>
                                    <% for (int i = 0; i < highest.size(); i++) {%>
                                    <tr style="background-color: #454545; color: #fff">
                                        <th>Location</th>
                                        <td colspan="3"><input type="text" name="location" readonly value="<%=highest.get(i).getLocation()%>" /></td>
                                    </tr>
                                    <tr style="background-color: #454545; color: #fff">
                                        <th>Sex</th>
                                        <td><input type="text" name="sex" readonly value="<%=highest.get(i).getSex()%>" /></td>
                                        <th>Age Group</th>
                                        <td><input type="text" name="ageGroup" readonly value="<%=highest.get(i).getageGroup()%>" /></td>
                                    </tr>
                                    <tr>
                                        <th>Highest Grade Completed</th>
                                        <th class="centerTD">Total Count</th>
                                        <th>Highest Grade Completed</th>
                                        <th class="centerTD">Total Count</th>
                                    </tr>
                                    <%for (int y = 0; y < highest.get(i).getHighestCompletedAgeGroup().size(); y += 2) {%>

                                    <tr>
                                        <th><input type="text" name="highestCompleted" readonly value="<%= highest.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted()%>"/></th>
                                        <td><input type="text" class="centerTD" name="count" readonly value="<%=highest.get(i).getFormatCount(highest.get(i).getHighestCompletedAgeGroup().get(y).getCount())%>"/></td>
                                        <th><input type="text" name="highestCompleted" readonly value="<%= highest.get(i).getHighestCompletedAgeGroup().get(y + 1).gethighestCompleted()%>"/></th>
                                        <td><input type="text" class="centerTD" name="count" readonly value="<%=highest.get(i).getFormatCount(highest.get(i).getHighestCompletedAgeGroup().get(y + 1).getCount())%>"/></td>
                                    </tr>
                                    <% }%>

                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th>Total</th>
                                        <td><input type="text" class="centerTD" name="total" style="float:right;" readonly value="<%=highest.get(i).getFormatCount(highest.get(i).getTotal())%>" /></td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                        <%
                            if (pageTitle.equalsIgnoreCase("approvalAdmin")) {
                        %>
                        <input id ="approvedReport" class="btn btn-flat btn-success" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Approve Report"/>
                        <input class="btn btn-flat btn-danger" style="margin-top: 1%; margin-right: 2%; float: right;" type="button" value="Reject Report" data-toggle="modal" data-target="#rejectModal"/>
                        <%  }
                        %>

                        <div>
                            <form id="approveServlet" action="ApproveDemoServlet" method="post">
                                <input type="hidden" name="sUser" id="sUser" value="<%= user.getUserID()%>" />
                                <input type="hidden" name="sformID" id="sformID" />
                                <input type="hidden" name="decision" value="approve" />
                            </form>

                        </div>
                    </div>

                </section>
            </div>
        </div>
        <div id="bottom"></div>
        <a href="#bottom"><div id="_bottom" class="hidden"  title="Scroll to Button"
                               style="position: fixed; top:40px; right: 5px; opacity: 0.5; cursor: pointer;">
                <img src="img/arrowdown.png" style='width:70px; height:70px; margin-top:40%'
                     alt="Scroll to Button"/></div></a>
        <a href="#top"><div id="_top" class="hidden" title="Scroll to Top"
                            style="position: fixed; bottom: 50px; right: 5px; opacity: 0.5; cursor: pointer;">
                <img src="img/arrowup.png" style='width:70px; height:70px; margin-top:40%'
                     alt="Scroll to Top"/></div></a>
        <script src="jsImported/approveReject.js" type="text/javascript"></script>
        <script>
            $(window).scroll(function () {
                if ($(window).scrollTop() + $(window).height() > $(document).height() - 50000) {
                    document.getElementById("_top").className = "";
                    document.getElementById("_bottom").className = "hidden";
                }
                else {
                    document.getElementById("_bottom").className = "";
                    document.getElementById("_top").className = "hidden";
                }
            });

            $("a[href='#top']").click(function () {
                $("html, body").animate({scrollTop: 0}, "slow");
                return false;
            });

            $("a[href='#bottom']").click(function () {
                $('html, body').animate({
                    scrollTop: $(document).height()
                },
                        1500);
                return false;
            });

        </script>
    </body>
</html>
