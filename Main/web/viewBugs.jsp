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
<%@ page import="java.util.ResourceBundle" %><%
    ResourceBundle resource;
    String language;
    if (request.getRequestURL().toString().contains("english")) {
        resource = ResourceBundle.getBundle("english");
        language = "english";
    } else {
        resource = ResourceBundle.getBundle("spanish");
        language = "spanish";
    }
%>
<html>
<head>
    <title>Current Submitted Bugs</title>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="/bootstrap/js/jquery.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/main.js"></script>
    <style type="text/css">
        h1 { text-align:center; }
        h2 { text-align: center; }
        h4 { text-align: center; }
        #accordion { text-align: center; }
        .panel-collapse { text-align: center; }
        #body {margin-top: 3.5%;}
        body {
            margin: auto;
            margin-top: 5%;
            width: 70%;
        }
        .form-control {
            margin-right: 10px;
        }
        .pagination>li>a, .pagination>li>span {

            width:160px;
        }
    </style>
</head>
<body>

<!--NAVBAT!!!!-->
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-target=".bs-navbar-collapse" data-toggle="collapse" type="button">
          <span class="sr-only">
            Toggle navigation
          </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home.jsp/<%out.print(language);%>">
                <%=resource.getString("form")%>
            </a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/index.jsp/<%out.print(language);%>">
                        Clinic Search Form
                    </a>
                </li>
                <li>
                    <a href="/clinic.jsp/<%out.print(language);%>">
                        Clinic Addition Form
                    </a>
                </li>
                <li>
                    <a href="/edit.jsp/<%out.print(language);%>">
                        Clinic Editing Form
                    </a>
                </li>
                <li>
                    <a href="/bug.jsp/<%out.print(language);%>">
                        Error Report
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right"></ul>
        </nav>
    </div>
</header>


<div id="body">
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
</div>

</body>
</html>