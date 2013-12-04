<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 10/6/13
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.

  Update: 11/15/2013
  User: Priscilla
  Time:12:54
--%>
<%@ page import = "java.util.ResourceBundle" %>
<%@ page import="java.util.Enumeration" %>
<%
    ResourceBundle resource;
    if (request.getRequestURL().toString().contains("english")) {
        resource = ResourceBundle.getBundle("english");
    } else {
        resource = ResourceBundle.getBundle("spanish");
    }
%>
<!DOCTYPE html>



<html>
<head>
    <title>Bug Form</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="bootstrap/css/bootstrap.min.css" media="screen" rel="stylesheet">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="main.js"></script>
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
            <a class="navbar-brand" href="../">
                Home
            </a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li>
                    <a href="index.jsp">
                        Clinic Search Form
                    </a>
                </li>
                <li>
                    <a href="clinic.jsp">
                        Clinic Addition Form
                    </a>
                </li>
                <li class="active">
                    <a href="components">
                        Clinic Editting Form
                    </a>
                </li>
                <li>
                    <a href="bug.jsp">
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
    <h1>Bug Report Form</h1>
</div><br>
<br>
<div class="span10">
<form class="form-horizontal form-stepped" action="html_form_action.asp">

<fieldset class="form-step">
    <input type="hidden" value="bug" name="action" id="action">

    <div class="form-group" id="bug1">
        <label>What sort of bug do you want to report?</label>
        <%--TODO this needs to be automated--%>
        <!--<input class="form-control" name="address" type="text" placeholder="Enter county"> -->
        <select id="bug1" name = "bug1" class="form-control">
            <option value = "1">Broken Functionality</option>
            <option value = "2">Incorrect Information</option>
            <option value = "3">Typos</option>
            <option value = "4">Other</option>
        </select>
    </div>

    <div id="bug2" class="form-inline">
    <label>Please write a short description of the error you have found.</label>
    <br>
    <textarea id="bug2" name = "bug2" rows = "3" cols = "90" placeholder="Error Description"></textarea>

    </div>
    
</fieldset>
<br>
<br>
    <div>
    <button class="btn btn-primary" type="submit">Submit</button>
    </div>



</form>
</div>
<br>
<br>
</body>
</html>
