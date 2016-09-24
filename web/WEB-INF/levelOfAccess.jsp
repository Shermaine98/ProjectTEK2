<%@page import="model.accounts.User"%>
<%
    User user = (User) session.getAttribute("user");
    String division = user.getDivision();
    String position = user.getPosition().toLowerCase().trim();

    if (session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    } else if (division.equalsIgnoreCase("Social Development Planning Division")) {
        if (position.equalsIgnoreCase("Project Development Officer II")) {
%>          <%@include file="/WEB-INF/JSPImports/importsHead.jsp" %>
<%
} else if (position.equalsIgnoreCase("Project Development Officer III")) {
%>          <%@include file= "/WEB-INF/JSPImports/importsHead.jsp" %>
<%          } else if (position.equalsIgnoreCase("Project Development Officer IV")) {

%>           <%@include file= "/WEB-INF/JSPImports/importsHead.jsp" %>
<%} else if (position.equalsIgnoreCase("Planning Officer II")) {
%>           <%@include file= "/WEB-INF/JSPImports/importsHead.jsp" %>
<%          } else if (position.equalsIgnoreCase("Planning Officer III")) {
%>           <%@include file= "/WEB-INF/JSPImports/importsHead.jsp"%>
<%          } else if (position.equalsIgnoreCase("Planning Officer IV")) {
%>          <%@include file= "/WEB-INF/JSPImports/importsHead.jsp" %>
<%          } else if (position.equalsIgnoreCase("Statistician I")) {
%>           <%@include file="/WEB-INF/JSPImports/importsHead.jsp"%>
<%          } else if (position.equalsIgnoreCase("Project Development Officer I")) {
%>           <%@include file="/WEB-INF/JSPImports/importsHead.jsp"%>
<%          } else if (position.equalsIgnoreCase("Administrative Aide VI")) {
%>           <%@include file="/WEB-INF/JSPImports/importsAide.jsp"%>
<%          } else if (position.equalsIgnoreCase("External Researchers")) {
%>          <%@include file="/WEB-INF/JSPImports/importsOther.jsp"%>
<%          } else if (position.equalsIgnoreCase("Other Planning Officers")) {
%>          <%@include file="/WEB-INF/JSPImports/importsOther.jsp"%>
<%          } else if (position.equalsIgnoreCase("IT Admin")) {
%>          <%@include file="/WEB-INF/JSPImports/importsITAdmin.jsp"%>
<%          }
} else if (division.equalsIgnoreCase("Others")) {
    if (position.equalsIgnoreCase("External Researchers")) {
%>          <%@include file="/WEB-INF/JSPImports/importsOther.jsp"%>
<%          } else if (position.equalsIgnoreCase("Other Planning Officers")) {
%>          <%@include file="/WEB-INF/JSPImports/importsOther.jsp"%>
<%}
} else {
%>            <%@include file="/WEB-INF/JSPImports/importsOtherSectors.jsp"%>
<%    }
//else {
//        response.sendRedirect("index.jsp");
//    }
%>