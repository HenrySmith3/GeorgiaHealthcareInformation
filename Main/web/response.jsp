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
    <title>Georgia Healthcare Search Results</title>
</head>
<body>
<%--TODO Should we display the search criteria here to?--%>
<%
    JSONArray jsonArray = (JSONArray)request.getAttribute("hospitals");
    ListIterator listIterator = jsonArray.listIterator();
    while (listIterator.hasNext()) {
        out.print("<div>");
        JSONObject jsonObject = (JSONObject)listIterator.next();
        out.print("Name: " + jsonObject.get("name") + "</br>");
        out.print("Phone: " + jsonObject.get("phone") + "</br>");
        if (!jsonObject.get("website").toString().equals("null")) {
            out.print("Website: " + jsonObject.get("website") + "</br>");
        }
        if (jsonObject.get("spanNurseGuide").toString().equals("1")) {
            out.print("Nurse Available Who Speaks Spanish.</br>");
        }
        if (jsonObject.get("spanDocGuide").toString().equals("1")) {
            out.print("Doctor Available Who Speaks Spanish.</br>");
        }
        if (jsonObject.get("spanAdminGuide").toString().equals("1")) {
            out.print("Administrator Available Who Speaks Spanish.</br>");
        }
        if (jsonObject.get("spanPhone").toString().equals("1")) {
            out.print("Phone Interpreter Available Who Speaks Spanish.</br>");
        }
        if (jsonObject.get("spanFo").toString().equals("1")) {
            out.print("Medical Forms Available In Spanish.</br>");
        }
        if (jsonObject.get("onCall").toString().equals("1")) {
            out.print("Person On Call Available After Hours.</br>");
        }

         out.print("</div>");//\n </br></br>");
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