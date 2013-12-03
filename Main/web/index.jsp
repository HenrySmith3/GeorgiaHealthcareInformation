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
    <title>User Form</title>
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
                <%=resource.getString("home")%>
            </a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li>
                    <a href="index.jsp">
                        <%=resource.getString("search")%>
                    </a>
                </li>
                <li>
                    <a href="clinic.jsp">
                        <%=resource.getString("addition")%>
                    </a>
                </li>
                <li class="active">
                    <a href="../components">
                        <%=resource.getString("editing")%>
                    </a>
                </li>
                <li>
                    <a href="bug.jsp">
                        <%=resource.getString("error")%>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
        </nav>
    </div>
</header>

<br>
<br>
<br>


<div id="title">
    <h1><%=resource.getString("formTitle")%></h1>

    <h4><%=resource.getString("instructions")%></h4>
</div><br>
<br>

<div class="span10">
<form class="form-horizontal form-stepped" id="clinic-search" action="html_form_action.asp">

<div style="margin-left:auto;margin-right:auto;width:640px;">
    <ul class="pagination form-step-control" style="display:none;">
        <li class="active">
            <span><span class="glyphicon glyphicon-user pull-left"></span>&nbsp;<strong><%=resource.getString("step1")%>:</strong><br><%=resource.getString("personalInfo")%></span>
        </li>
        <li class="disabled">
            <span><span class="glyphicon glyphicon-road pull-left"></span>&nbsp;<strong><%=resource.getString("step2")%>:</strong><br><%=resource.getString("transportation")%></span>
        </li>
        <li class="disabled">
                    <span><span class="glyphicon glyphicon-list-alt pull-left"></span>&nbsp;<strong><%=resource.getString("step3")%>:</strong><br><%=resource.getString("clinicServices")%><span>
        </li>
        <li class="disabled">
                    <span><span class="glyphicon glyphicon-heart pull-left"></span>&nbsp;<strong><%=resource.getString("step4")%>:</strong><br><%=resource.getString("healthcare")%><span>
        </li>
    </ul>
</div>

<fieldset class="form-step">
    <legend><%=resource.getString("personalInfo")%></legend>

    <div class="form-group" id="address">
        <label><%=resource.getString("county")%></label>
        <input class="form-control" name="address" type="text" placeholder="Enter county">
    </div>

    <div class="form-group" id="free">
        <label><%=resource.getString("freeServices")%></label><br>
        <%--TODO We need to explain who would qualify for free clinic services--%>
        <label class="radio-inline" for="pickfreeY">
            <input id="pickfreeY" name="pickFY" type="radio" value="freeY">Yes
        </label>
        <label class="radio-inline" for="pickfreeN">
            <input id="pickfreeN" name="pickFN" type="radio" value="freeN">No
        </label>
    </div>

    <!--<div class="form-group" id="sex">
       <label>Sex:</label><br>
       <label class="radio-inline" for="pickSM">
            <input id="pickSM" name="pickS" type="radio" value="male"> Male
       </label>
       <label class="radio-inline" for="pickSF">
          <input id="pickSF" name="pickS" type="radio" value="female"> Female
       </label>
    </div> -->
    <br>
</fieldset>

<fieldset class="form-step">
    <legend><%=resource.getString("transportation")%></legend>

    <div class="form-group" id="trans">
        <label><%=resource.getString("preferredTransportation")%></label>
        <select id="transportation" class="form-control">
            <option value="1"><%=resource.getString("drive")%></option>
            <option value="2">MARTA</option>
            <option value="3"><%=resource.getString("bus")%></option>
            <option value="4"><%=resource.getString("none")%></option>
        </select>
    </div>

    <div class="form-group" id="parking">
        <label>Do you require parking space?</label><br>
        <label class="radio-inline" for="yes">
            <input id="yes" name="parking" type="radio" value="y">Yes
        </label>
        <label class="radio-inline" for="no">
            <input id="no" name="parking" type="radio" value="n">No
        </label>
    </div>
    <br>
</fieldset>

<fieldset class="form-step">
    <legend><%=resource.getString("clinicServices")%></legend>

    <div class="form-group" id="appt">
        <label><%=resource.getString("kindAppointments")%></label><br>
        <label class="radio-inline" for="walkin">
            <input id="walkin" name="appt" type="radio" value="walk">Walk-In
        </label>
        <label class="radio-inline" for="appointment">
            <input id="appointment" name="appt" type="radio" value="appmt">Appointment
        </label>
    </div>
    <div class="form-group" id="insurance">
        <label><%=resource.getString("insurance")%></label>
        <select class="form-control" id="insurancetype">
            <option value="1">Medicare</option>
            <option value="2">Medicaid</option>
            <option value="3">Peachcare</option>
            <option value="4"><%=resource.getString("none")%></option>
        </select>
    </div>

    <%--TODO this is now just shown on the results page, this can all be removed.--%>
    <div class="form-group" id="language">
        <label><%=resource.getString("facultyMembers")%></label><br>
        <label class="checkbox-inline" for="receptionist">
            <input id="receptionist" type="checkbox" value="recept"><%=resource.getString("receptionist")%>
        </label>
        <label class="checkbox-inline" for="nurses">
            <input id="nurses" type="checkbox" value="nur"><%=resource.getString("nurse")%>
        </label>
        <label class="checkbox-inline" for="doctor">
            <input id="doctor" type="checkbox" value="doc"><%=resource.getString("doctor")%>
        </label>
    </div>

    <div class="form-group" id="forms">
        <label><%=resource.getString("medicalForms")%></label><br>
        <label class="radio-inline" for="yes">
            <input id="phoneyes" name="forms" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="no">
            <input id="phoneno" name="forms" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>

    <div class="form-group" id="phone">
        <label><%=resource.getString("spanishReceptionist")%></label><br>
        <label class="radio-inline" for="yes">
            <input id="phoneyes" name="phone" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="no">
            <input id="phoneno" name="forms" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>

    <div class="form-group" id="call">
        <label><%=resource.getString("onCall")%></label><br>
        <label class="radio-inline" for="yes">
            <input id="callyes" name="call" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="no">
            <input id="callno" name="call" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>
    <%--TODO are we even asking these anymore?--%>
    <div class="form-group" id="date">
        <label>Which days are best for you to visit the clinic?</label><br>
        <label class="checkbox-inline" for="mon">
            <input id="mon" type="checkbox" value="M">Monday
        </label>
        <label class="checkbox-inline" for="tues">
            <input id="tues" type="checkbox" value="T">Tuesday
        </label>
        <label class="checkbox-inline" for="wed">
            <input id="wed" type="checkbox" value="W">Wednesday
        </label>
        <label class="checkbox-inline" for="thurs">
            <input id="thurs" type="checkbox" value="R">Thursday
        </label>
        <label class="checkbox-inline" for="fri">
            <input id="fri" type="checkbox" value="F">Friday
        </label>
        <label class="checkbox-inline" for="sat">
            <input id="sat" type="checkbox" value="S">Saturday
        </label>
        <label class="checkbox-inline" for="sun">
            <input id="sun" type="checkbox" value="Su">Sunday
        </label>
    </div>
    <br>
</fieldset>

<fieldset class="form-step">
    <legend><%=resource.getString("healthcare")%></legend>

    <div class="form-group" id="type">
        <label><%=resource.getString("following")%></label><br>
        <label class="checkbox-inline" for="women">
            <input id="women" type="checkbox" value="wo"><%=resource.getString("womens")%>
        </label>
        <label class="checkbox-inline" for="mens">
            <input id="mens" type="checkbox" value="me"><%=resource.getString("mens")%>
        </label>
        <label class="checkbox-inline" for="family">
            <input id="family" type="checkbox" value="fam"><%=resource.getString("family")%>
        </label>
        <label class="checkbox-inline" for="mental">
            <input id="mental" type="checkbox" value="ment"><%=resource.getString("mental")%>
        </label>
        <label class="checkbox-inline" for="dental">
            <input id="dental" type="checkbox" value="dent"><%=resource.getString("dental")%>
        </label>
        <label class="checkbox-inline" for="vision">
            <input id="vision" type="checkbox" value="vis"><%=resource.getString("vision")%>
        </label>
    </div>

    <div class="form-group" id="children">
        <label><%=resource.getString("children")%></label><br>
        <label class="radio-inline" for="yes">
            <input id="chilyes" name="chil" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="no">
            <input id="chilno" name="child" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>

    <!--<div class="form-group" id="ages">
       <label>If yes, please check all the ages that apply.</label>
       <label class="checkbox">
           <input id="05" type="checkbox" value="1">0-5
       </label>
       <label class="checkbox">
           <input id="610" type="checkbox" value="2">6-10
       </label>
       <label class="checkbox">
           <input id="1115" type="checkbox" value="3">11-15
       </label>
       <label class="checkbox">
           <input id="1617" type="checkbox" value="4">16-17
       </label>
    </div> -->

    <div class="form-group" id="mentalhealth">
        <label><%=resource.getString("mentalFollowing")%></label>
        <label class="checkbox">
            <input id="substance" type="checkbox" value="subt"><%=resource.getString("substance")%>
        </label>
        <label class="checkbox">
            <input id="sex" type="checkbox" value="se"><%=resource.getString("sexual")%>
        </label>
        <label class="checkbox">
            <input id="anger" type="checkbox" value="ang"><%=resource.getString("anger")%>
        </label>
        <label class="checkbox">
            <input id="sexHIV" type="checkbox" value="HIV"><%=resource.getString("hiv")%>
        </label>
        <label class="checkbox">
            <input id="sexLGBT" type="checkbox" value="LGBT"><%=resource.getString("lgbt")%>
        </label>
    </div>
    <br>
</fieldset>

<div class="step-control">
    <button class="btn btn-default" style="display:none;"><%=resource.getString("goBack")%></button>
    <button class="btn btn-primary" type="submit"><%=resource.getString("submit")%></button>
    <button class="btn btn-default" style="display:none;"><%=resource.getString("continue")%></button>
</div>

</form>
</div>
<br>
<br>
<div class="footer">
    <hr>
    <%=resource.getString("header")%>
</div>
</body>
</html>
