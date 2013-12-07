<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="net.sf.json.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 12/7/13
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current Submitted Bugs</title>
</head>
<body>
<%
    JSONArray jsonArray = (JSONArray)request.getAttribute("bugs");
    ListIterator listIterator = jsonArray.listIterator();
    out.println("<table style=\"border: 1px solid black\">");
    out.println("<tr><td>ID</td><td>Bug Type</td><td>Bug Description</td></tr>");
    while (listIterator.hasNext()) {
        out.println("<tr><td>");
        JSONObject object = (JSONObject)listIterator.next();
        out.print(object.get("ID"));
        out.print("</td><td>");
        out.print(object.get("bug"));
        out.print("</td><td>");
        out.print(object.get("bugDesc"));
        out.print("</td></tr>");
    }
    out.print("</table>");


%>

</body>
</html>