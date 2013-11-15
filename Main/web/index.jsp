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
                    <a href="../components">
                        Clinic Editting Form
                    </a>
                </li>
                <li>
                    <a href="bug.jsp">
                        Error Report
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
    <h1>Clinic Search Form</h1>

    <h4>Please fill out this form information based on the individual(s) who need healthcare.</h4>
</div><br>
<br>

<div class="span10">
<form class="form-horizontal form-stepped" id="clinic-search">

<div style="margin-left:auto;margin-right:auto;width:640px;">
    <ul class="pagination form-step-control" style="display:none;">
        <li class="active">
            <span><span class="glyphicon glyphicon-user pull-left"></span>&nbsp;<strong>Step 1:</strong><br>Personal Information</span>
        </li>
        <li class="disabled">
            <span><span class="glyphicon glyphicon-road pull-left"></span>&nbsp;<strong>Step 2:</strong><br>Transportation</span>
        </li>
        <li class="disabled">
                    <span><span class="glyphicon glyphicon-list-alt pull-left"></span>&nbsp;<strong>Step 3:</strong><br>Clinic Services<span>
        </li>
        <li class="disabled">
                    <span><span class="glyphicon glyphicon-heart pull-left"></span>&nbsp;<strong>Step 4:</strong><br>Healthcare<span>
        </li>
    </ul>
</div>

<fieldset class="form-step">
    <legend>Personal Information</legend>

    <div class="form-group" id="address">
        <label>What county does the person who needs healthcare live in?</label>
        <input class="form-control" name="address" type="text" placeholder="Enter county">
    </div>

    <div class="form-group" id="free">
        <label>Do you qualify for free clinic services?</label><br>
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
    <legend>Transportation</legend>

    <div class="form-group" id="trans">
        <label>What is your preferred method of transportation?</label>
        <select id="transportation" class="form-control">
            <option value="1">Drive</option>
            <option value="2">MARTA</option>
            <option value="3">Bus</option>
            <option value="4"></option>
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
    <legend>Clinic Services</legend>

    <div class="form-group" id="appt">
        <label>Which kind of appointments do you prefer?</label><br>
        <label class="radio-inline" for="walkin">
            <input id="walkin" name="appt" type="radio" value="walk">Walk-In
        </label>
        <label class="radio-inline" for="appointment">
            <input id="appointment" name="appt" type="radio" value="appmt">Appointment
        </label>
    </div>
    <div class="form-group" id="insurance">
        <label>What medical insurance do you have?</label>
        <select class="form-control" id="insurancetype">
            <option value="1">Medicare</option>
            <option value="2">Medicaid</option>
            <option value="3">Peachcare</option>
            <option value="4">None</option>
        </select>
    </div>

    <div class="form-group" id="language">
        <label>Do you require any of the following faculty members to speak Spanish?</label><br>
        <label class="checkbox-inline" for="receptionist">
            <input id="receptionist" type="checkbox" value="recept">Receptionist
        </label>
        <label class="checkbox-inline" for="nurses">
            <input id="nurses" type="checkbox" value="nur">Nurses
        </label>
        <label class="checkbox-inline" for="doctor">
            <input id="doctor" type="checkbox" value="doc">Doctor
        </label>
    </div>

    <div class="form-group" id="forms">
        <label>Do the medical forms need to be written in Spanish?</label><br>
        <label class="radio-inline" for="yes">
            <input id="phoneyes" name="forms" type="radio" value="y">Yes
        </label>
        <label class="radio-inline" for="no">
            <input id="phoneno" name="forms" type="radio" value="n">No
        </label>
    </div>

    <div class="form-group" id="phone">
        <label>Do you need a Spanish speaking receptionist on the phone?</label><br>
        <label class="radio-inline" for="yes">
            <input id="phoneyes" name="phone" type="radio" value="y">Yes
        </label>
        <label class="radio-inline" for="no">
            <input id="phoneno" name="forms" type="radio" value="n">No
        </label>
    </div>

    <div class="form-group" id="call">
        <label>Do you require someone to be available on-call after the clinic's normal business hours?</label><br>
        <label class="radio-inline" for="yes">
            <input id="callyes" name="call" type="radio" value="y">Yes
        </label>
        <label class="radio-inline" for="no">
            <input id="callno" name="call" type="radio" value="n">No
        </label>
    </div>

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
    <legend>Healthcare</legend>

    <div class="form-group" id="type">
        <label>Do you need the clinic to handle any of the following?</label><br>
        <label class="checkbox-inline" for="women">
            <input id="women" type="checkbox" value="wo">Women's Health
        </label>
        <label class="checkbox-inline" for="mens">
            <input id="mens" type="checkbox" value="me">Men's Health
        </label>
        <label class="checkbox-inline" for="family">
            <input id="family" type="checkbox" value="fam">Family Health
        </label>
        <label class="checkbox-inline" for="mental">
            <input id="mental" type="checkbox" value="ment">Mental Health
        </label>
        <label class="checkbox-inline" for="dental">
            <input id="dental" type="checkbox" value="dent">Dental Health
        </label>
        <label class="checkbox-inline" for="vision">
            <input id="vision" type="checkbox" value="vis">Vision Health
        </label>
    </div>

    <div class="form-group" id="children">
        <label>Do you have children that need medical assistance?</label><br>
        <label class="radio-inline" for="yes">
            <input id="chilyes" name="chil" type="radio" value="y">Yes
        </label>
        <label class="radio-inline" for="no">
            <input id="chilno" name="child" type="radio" value="n">No
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
        <label>Do you need mental health services, counseling, or rehabilitation services in any of the following?</label>
        <label class="checkbox">
            <input id="substance" type="checkbox" value="subt">Substance Abuse
        </label>
        <label class="checkbox">
            <input id="sex" type="checkbox" value="se">Sexual Abuse
        </label>
        <label class="checkbox">
            <input id="anger" type="checkbox" value="ang">Anger Management
        </label>
        <label class="checkbox">
            <input id="sexHIV" type="checkbox" value="HIV">HIV
        </label>
        <label class="checkbox">
            <input id="sexLGBT" type="checkbox" value="LGBT">LGBT
        </label>
    </div>
    <br>
</fieldset>

<div class="step-control">
    <button class="btn btn-default" style="display:none;">&larr; Go Back</button>
    <button class="btn btn-primary" type="submit">Submit</button>
    <button class="btn btn-default" style="display:none;">Continue &rarr;</button>
</div>

</form>
</div>
<br>
<br>
<div class="footer">
    <hr>
    *filler header* For the Emory School of Public Health and Hispanic Health Coalition of Georgia. Designed by &copy; Health Coders and Hamilton Evans Cahill
</div>
</body>
</html>
