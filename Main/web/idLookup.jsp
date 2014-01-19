<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="net.sf.json.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 12/7/13
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hospital IDs</title>
</head>
<body>
<%
    JSONArray jsonArray = (JSONArray)request.getAttribute("hospitals");
    ListIterator listIterator = jsonArray.listIterator();
    out.println("<table style=\"border: 1px solid black\">");
    out.println("<tr><td>ID</td><td>Hospital Name</td></tr>");
    while (listIterator.hasNext()) {
        out.println("<tr><td>");
        JSONObject object = (JSONObject)listIterator.next();
        out.print(object.get("SurvNo"));
        out.print("</td><td>");
        out.print(object.get("name"));
        out.print("</td></tr>");
    }
    out.print("</table>");


%>

</body>
</html>