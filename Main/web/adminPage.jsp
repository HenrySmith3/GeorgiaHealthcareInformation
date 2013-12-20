<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 12/7/13
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.util.ResourceBundle" %>
<%
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
    <title>Admin Page</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="/bootstrap/css/bootstrap.min.css" media="screen" rel="stylesheet">
    <script src="/bootstrap/js/jquery.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/main.js"></script>
    <style type="text/css">
        h1 { text-align:center; }
        h4 { text-align: center; }
        body {
            margin: auto;
            width: 70%;
        }
        .form-control {
            margin-right: 10px;
        }
        .pagination>li>a, .pagination>li>span {

            width:160px;
        }
    </style>
</head><!--end of header-->
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

<br>
<br>
<br>


<div id="title">
    <h1>Administrator Page</h1>
</div><br>

<form action="/html_form_action.asp">
    <input type="hidden" value="viewBugs" name="action" id="action">
    <button type="submit">View Current Bugs</button>
</form>
</body>
</html>