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
    String language;
    if (request.getRequestURL().toString().contains("english")) {
        resource = ResourceBundle.getBundle("english");
        language = "english";
    } else {
        resource = ResourceBundle.getBundle("spanish");
        language = "spanish";
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
    <h1>Clinic Edit Form</h1>
    <h4>Change or add information about your clinic as needed. The marked answers is the data that we currently have concerning your clinic.</h4>
</div><br>
<br>
<form action="/html_form_action.asp">
    <input type="hidden" value="viewBugs" name="action" id="action">
    <button type="submit">View Current Bugs (Move this button later)</button>
</form>
<form action="/html_form_action.asp">
    <input type="hidden" value="lookupIds" name="action" id="action">
    <button type="submit">Lookup Your Hospital's ID</button>
</form>
<br>

<div class="span10">
<form class="form-horizontal form-stepped" action="/html_form_action.asp">


<fieldset class="form-step">
    <input type="hidden" value="editHospital" name="action" id="action">

    <div class="form-group" id="selectclinic">
    <label>Input the ID of the hospital you wish to edit</label><br>
    <textarea id="id" name="id" rows="1" cols="20" placeholder="hospital ID"/></textarea>
    </div>

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
    <div class="form-group" id="city" class="form-inline">
    <label>City</label>
    <br>
    <textarea id="city" name = "city" rows = "1" cols = "60" placeholder="city"></textarea>

    </div>
    <div class="form-group" id="website" class="form-inline">
    <label>Website</label>
    <br>
    <textarea id="web" name = "web" rows = "1" cols = "60" placeholder="website"></textarea>
    </div>
    <div class="form-group" id="phone" class="form-inline">
    <label>Phone</label>
    <br>
    <textarea id="pho" name = "pho" rows = "1" cols = "60" placeholder="phone number"></textarea>
    </div>
    <div class="form-group" id="address" class="form-inline">
    <label>Address</label>
    <br>
    <textarea id="add" name = "add" rows = "1" cols = "60" placeholder="address"></textarea>
    </div>
    <br>
    <div class="form-group" id="appt">
        <label>What kind of appointments can be made?</label><br>
        <label class="radio-inline" for="walkin">
            <input id="walkin" name="appt" type="radio" value="walkin">Walk-In
        </label>
        <label class="radio-inline" for="appointment">
            <input id="appointment" name="appt" type="radio" value="appointment">Appointment
        </label>
        <%--TODO this option is new--%>
        <label class="radio-inline" for="bothWalkApp">
            <input id="bothWalkApp" name="appt" type="radio" value="bothWalkApp">Both
        </label>
    </div>
    <div class="form-group" id="insurance">
        <label>Which medical insurance can be used?</label>
        <label class="checkbox" for="Medicare">
            <input id="Medicare" type="checkbox" value="Medicare" name = "days">Medicare
        </label>
        <label class="checkbox" for="Medicaid">
            <input id="Medicaid" type="checkbox" value="Medicaid" name = "days">Medicaid
        </label>
        <label class="checkbox" for="Peachcare">
            <input id="Peachcare" type="checkbox" value="Peachcare" name = "days">Peachcare
        </label>
        <label class="checkbox" for="Other">
            <input id="Other" type="checkbox" value="Other" name = "days">Other
        </label>
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
        <%--TODO all the options from here forward are new and aren't being handles --%>
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
        <label class="checkbox-inline" for="prenatal">
            <input id="prenatal" type="checkbox" value="prena">Prenatal Care
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
            <input id="substance" type="checkbox" value="subt" name = "mentalhealth">Substance
        </label>
        <label class="checkbox">
            <input id="sex" type="checkbox" value="se" name = "mentalhealth1">Sexual
        </label>
        <label class="checkbox">
            <input id="anger" type="checkbox" value="ang" name = "mentalhealth2">Anger
        </label>
        <label class="checkbox">
            <input id="sexHIV" type="checkbox" value="HIV" name = "mentalhealth3">HIV
        </label>
        <label class="checkbox">
            <input id="sexLGBT" type="checkbox" value="LGBT" name = "mentalhealth4">LGBT
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
