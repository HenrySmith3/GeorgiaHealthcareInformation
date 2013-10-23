<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.util.ListIterator" %>
<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 10/22/13
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<html>
<head>
    <title>This is a response</title>
</head>
<body>
<%
    JSONArray jsonArray = (JSONArray)request.getAttribute("hospitals");
    ListIterator listIterator = jsonArray.listIterator();
    while (listIterator.hasNext()) {
        out.print("<div>");
        JSONObject jsonObject = (JSONObject)listIterator.next();
        out.print("Name: " + jsonObject.get("name") + "</br>");
        out.print("Phone: " + jsonObject.get("phone") + "</br>");
        out.print("Website: " + jsonObject.get("website") + "</br>");
        out.print("</div>\n </br></br>");
    }
%>
    <%--${hospitals}--%>
    <%--<c:foreach var="hospital" items=${hospitals}>--%>
        <%--<div>--%>
            <%--<p>${hospital}</p>--%>
        <%--</div>--%>
    <%--</c:foreach>--%>
</body>
</html>