<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.sun.xml.internal.bind.v2.TODO" %>
<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 10/22/13
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link rel="stylesheet" href="/css/main.css" type="text/css">--%>
<link href="/bootstrap/css/bootstrap.min.css" media="screen" rel="stylesheet">
<script src="/bootstrap/js/jquery.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/main.js"></script>
<html>
<head>
    <title>Georgia Healthcare Search Results</title>
</head>
<body>
<%--TODO Should we display the search criteria here to?--%>

<%!
    public static void printHospital(JSONObject jsonObject, JspWriter out, int count) throws IOException {

        out.println("<div class=\"panel panel-default\">");
        out.print("<div class=\"panel-heading\"><h4 class=\"panel-title\">");
        out.print("<a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse" + count + "\">");
        out.print(jsonObject.get("name") + "</br>");
        out.print("</a></div></div>");
        out.print("<div id=\"collapse" + count + "\" class=\"panel-collapse collapse\"><div class=\"panel-body\">");
        out.print("Phone: " + jsonObject.get("phone") + "</br>");
        //links work now
        if (!jsonObject.get("addressLine1").toString().equals("null")) {
            out.print("<a href=" + "\"" + "http://maps.google.com/?q="+jsonObject.get("addressLine1") +"\"" + ">" + jsonObject.get("addressLine1") + "</a></br>");
        }
        if (!jsonObject.get("website").toString().equals("null")) {
            out.print("Website:" + "<a href=" +"\"" + jsonObject.get("website") + "\"" + ">" + jsonObject.get("website") + "</a></br>");
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

        out.print("</div></div></div>");
    }
%>

<%-- TODO we could make it say "results in ____ county" to make it clearer--%>
<%
    out.print("<h2>Results in County</h2>");
    JSONArray jsonArray = (JSONArray)request.getAttribute("hospitalsInCounty");
    ListIterator listIterator = jsonArray.listIterator();
    out.println("<div class=\"panel-group\" id=\"accordion\">");
    int count = 0;
    while (listIterator.hasNext()) {
        printHospital((JSONObject)listIterator.next(), out, count);
        count++;
    }
    out.print("<h2>Results in all of Georgia</h2>");
    jsonArray = (JSONArray)request.getAttribute("allHospitals");
    listIterator = jsonArray.listIterator();
    while (listIterator.hasNext()) {
        printHospital((JSONObject)listIterator.next(), out, count);
        count++;
    }
    out.print("</div>");
    out.print("</div>");


%>
    <%--${hospitals}--%>
    <%--<c:foreach var="hospital" items=${hospitals}>--%>
        <%--<div>--%>
            <%--<p>${hospital}</p>--%>
        <%--</div>--%>
    <%--</c:foreach>--%>
</body>
</html>