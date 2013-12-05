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
            <a class="navbar-brand" href="../">
                <%=resource.getString("form")%>
            </a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
          <li>
            <a href="english">
              Clinic Search Form
            </a>
          </li>
          <li>
            <a href="clinic.jsp">
              Clinic Addition Form
            </a>
          </li>
          <li>
            <a href="edit.jsp">
              Clinic Editing Form
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
    <h1>Add Clinic Services</h1>
</div><br>
<br>

<div class="span10">
<form class="form-horizontal form-stepped" action="html_form_action.asp">


<fieldset class="form-step">
    <input type="hidden" value="addHospital" name="action" id="action">

    <div class="form-group" id="county">
        <label>County</label>
        <%--TODO this needs to be automated--%>
        <!--<input class="form-control" name="address" type="text" placeholder="Enter county"> -->
        <select id="county" name = "county" class="form-control">
            <option value="1">Clayton</option>
            <option value="2">Cobb</option>
            <option value="3">DeKalb</option>
            <option value="4">Fulton</option>
            <option value="5">Gwinnett</option>
        </select>
    </div>

    <div class="form-group" id="appt">
        <label>What kind of appointments can be made?</label><br>
        <label class="radio-inline" for="walkin">
            <input id="walkin" name="appt" type="radio" value="walkin">Walk-In
        </label>
        <label class="radio-inline" for="appointment">
            <input id="appointment" name="appt" type="radio" value="appointment">Appointment
        </label>
    </div>
    <div class="form-group" id="insurance">
        <label>Which medical insurance can be used?</label>
        <select class="form-control" id="insurancetype" name = "insuranceForm">
            <option value="1">Medicare</option>
            <option value="2">Medicaid</option>
            <option value="3">Peachcare</option>
            <%--TODO is this being handled right? 5 was added--%>
            <option value="4">Other</option>
            <option value="5"><%=resource.getString("none")%></option>
        </select>
    </div>

    <div class="form-group" id="interpreter">
        <%--TODO this was added--%>
        <label>Is there a Spanish interpreter/translator?</label><br>
        <label class="radio-inline" for="interpreteryes">
            <input id="interpreteryes" name="interpreter" type="radio" value="interpY">Yes
        </label>
        <label class="radio-inline" for="interpreterno">
            <input id="interpreterno" name="interpreter" type="radio" value="interpN">No
        </label>
    </div>

    <div class="form-group" id="forms">
        <label>Do you provide medical forms that are written in Spanish?</label><br>
        <label class="radio-inline" for="formyes">
            <input id="formyes" name="forms" type="radio" value="yes">Yes
        </label>
        <label class="radio-inline" for="formno">
            <input id="formno" name="forms" type="radio" value="no">No
        </label>
    </div>

    <div class="form-group" id="phone">
        <label>Do you have a Spanish speaking receptionist on the phone?</label><br>
        <label class="radio-inline" for="phoneyes">
            <input id="phoneyes" name="reception" type="radio" value="yes">Yes
        </label>
        <label class="radio-inline" for="phoneno">
            <input id="phoneno" name="reception" type="radio" value="no">No
        </label>
    </div>

    <div class="form-group" id="call">
        <label>Do you have someone available on the phone after the clinic's normal business hours?</label><br>
        <label class="radio-inline" for="afterphoneyes">
            <input id="afterphoneyes" name="call" type="radio" value="yes">Yes
        </label>
        <label class="radio-inline" for="afterphoneno">
            <input id="afterphoneno" name="call" type="radio" value="no">No
        </label>
    </div>
    <%--TODO are we even asking these anymore?--%>
    <div class="form-group" id="date">
        <label>On which days do you open?</label><br>
        <label class="checkbox-inline" for="mon">
            <input id="mon" type="checkbox" value="M" name = "days">Monday
        </label>
        <label class="checkbox-inline" for="tues">
            <input id="tues" type="checkbox" value="T" name = "days">Tuesday
        </label>
        <label class="checkbox-inline" for="wed">
            <input id="wed" type="checkbox" value="W" name = "days">Wednesday
        </label>
        <label class="checkbox-inline" for="thurs">
            <input id="thurs" type="checkbox" value="R" name = "days">Thursday
        </label>
        <label class="checkbox-inline" for="fri">
            <input id="fri" type="checkbox" value="F" name = "days">Friday
        </label>
        <label class="checkbox-inline" for="sat">
            <input id="sat" type="checkbox" value="S" name = "days">Saturday
        </label>
        <label class="checkbox-inline" for="sun">
            <input id="sun" type="checkbox" value="Su" name = "days">Sunday
        </label>
        <label class="checkbox-inline" for="all">
            <input id="all" type="checkbox" value="A" name = "days">All/Any
        </label>
    </div>
    <br>

    <div class="form-group" id="type">
        <label>Do you have the clinic to handle any of the following? (Check all that applies)?</label><br>
        <label class="checkbox-inline" for="women">
            <input id="women" type="checkbox" value="wo" name = "womens">Womens
        </label>
        <label class="checkbox-inline" for="mens">
            <input id="mens" type="checkbox" value="me" name = "mens">Mens
        </label>
        <label class="checkbox-inline" for="family">
            <input id="family" type="checkbox" value="fam" name = "family">Family
        </label>
        <label class="checkbox-inline" for="mental">
            <input id="mental" type="checkbox" value="ment" name = "mental">Mental
        </label>
        <label class="checkbox-inline" for="dental">
            <input id="dental" type="checkbox" value="dent" name = "dental">Dental
        </label>
        <label class="checkbox-inline" for="vision">
            <input id="vision" type="checkbox" value="vis" name = "vision">Vision
        </label>
        <label class="checkbox-inline" for="primary">
            <input id="primary" type="checkbox" value="pri">Primary Care
        </label>
        <label class="checkbox-inline" for="internal">
            <input id="internal" type="checkbox" value="int">Internal Medicine
        </label>
        <label class="checkbox-inline" for="child">
            <input id="child" type="checkbox" value="chi">Pediatric Care
        </label>
        <label class="checkbox-inline" for="elder">
            <input id="elder" type="checkbox" value="eld">Geriatric Care
        </label>
        <label class="checkbox-inline" for="diabetes">
            <input id="diabetes" type="checkbox" value="dia">Diabetes
        </label>
        <label class="checkbox-inline" for="pain">
            <input id="pain" type="checkbox" value="pai">Pain Management
        </label>
        <label class="checkbox-inline" for="trauma">
            <input id="trauma" type="checkbox" value="trau">Trauma/Emergency
        </label>
        <label class="checkbox-inline" for="surgery">
            <input id="surgery" type="checkbox" value="sur">General Surgery
        </label>
        <label class="checkbox-inline" for="radio">
            <input id="radio" type="checkbox" value="rad">Radiology
        </label>
        <label class="checkbox-inline" for="skin">
            <input id="skin" type="checkbox" value="skin">Dermatology
        </label>
        <label class="checkbox-inline" for="ent">
            <input id="ent" type="checkbox" value="ent">ENT (Ears/Nose/Throat)
        </label>
        <label class="checkbox-inline" for="allergy">
            <input id="allergy" type="checkbox" value="alle">Allergy/Asthma
        </label>
    </div>

    <div class="form-group" id="children">
        <label>Do you have pediatrics? (Ages 0-24) **Add to the specialties in the previous question*</label><br>
        <label class="radio-inline" for="childrenyes">
            <input id="childrenyes" name="child" type="radio" value="y">Yes
        </label>
        <label class="radio-inline" for="childrenno">
            <input id="childrenno" name="child" type="radio" value="n">No
        </label>
    </div>

    <%--TODO this was added--%>
    <div class="form-group" id="prenatal"> <!--add as a specialty-->
        <label>Do you have pre-natal care?</label><br>
        <label class="radio-inline" for="prenatalyes">
            <input id="prenatalyes" name="prenatal" type="radio" value="prenatalcareyes">Yes
        </label>
        <label class="radio-inline" for="prenatalno">
            <input id="prenatalno" name="prenatal" type="radio" value="prenatalcareno">No
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
        <label>Do you have mental health services, counseling, or rehabilitation services in any of the following? (Check all that applies)</label>
        <label class="checkbox">
            <input id="substance" type="checkbox" value="subt" name = "mentalhealth"><%=resource.getString("substance")%>
        </label>
        <label class="checkbox">
            <input id="sex" type="checkbox" value="se" name = "mentalhealth1">Sexual
        </label>
        <label class="checkbox">
            <input id="anger" type="checkbox" value="ang" name = "mentalhealth2">Anger
        </label>
        <label class="checkbox">
            <input id="sexHIV" type="checkbox" value="HIV" name = "mentalhealth3">Hiv
        </label>
        <label class="checkbox">
            <input id="sexLGBT" type="checkbox" value="LGBT" name = "mentalhealth4">Lgbt
        </label>
    </div>
    <br>
</fieldset>

 <div>
    <button class="btn btn-primary" type="submit">Submit</button>
    </div>

</form>
</div>
<br>
<br>
</body>
</html>
