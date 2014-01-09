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
            <a class="navbar-brand" href="/home.jsp/<%out.print(language);%>">
                <%=resource.getString("form")%>
            </a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/index.jsp/<%out.print(language);%>">
                        <%=resource.getString("search")%>
                    </a>
                </li>
                <li>
                    <a href="/clinic.jsp/<%out.print(language);%>">
                        <%=resource.getString("addition")%>
                    </a>
                </li>
                <li>
                    <a href="/edit.jsp/<%out.print(language);%>">
                        <%=resource.getString("editing")%>
                    </a>
                </li>
                <li>
                    <a href="/bug.jsp/<%out.print(language);%>">
                        <%=resource.getString("error")%>
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
    <h1><%=resource.getString("formTitle")%></h1>

    <h4><%=resource.getString("instructions")%></h4>
</div><br>
<br>

<div class="span10">
<form class="form-horizontal form-stepped" id="clinic-search" action="/html_form_action.asp/<%out.print(language);%>">

<div style="margin-left:auto;margin-right:auto;width:640px;">
    <ul class="pagination form-step-control" style="display:none;">
        <li class="active">
            <span><span class="glyphicon glyphicon-user pull-left"></span>&nbsp;<strong><%=resource.getString("step1")%>:</strong><br><%=resource.getString("personalInfo")%></span>
        </li>
        <li class="disabled">
            <span><span class="glyphicon glyphicon-road pull-left"></span>&nbsp;<strong><%=resource.getString("step2")%>:</strong><br><%=resource.getString("transportation")%></span>
        </li>
        <li class="disabled">
                    <span><span class="glyphicon glyphicon-list-alt pull-left"></span>&nbsp;<strong><%=resource.getString("step3")%>:</strong><br><%=resource.getString("clinicServices")%></span>
        </li>
        <li class="disabled">
                    <span><span class="glyphicon glyphicon-heart pull-left"></span>&nbsp;<strong><%=resource.getString("step4")%>:</strong><br><%=resource.getString("healthcare")%></span>
        </li>
    </ul>
</div>

<fieldset class="form-step">
    <input type="hidden" value="search" name="action" id="action">
    <legend><%=resource.getString("personalInfo")%></legend>

    <div class="form-group" id="address">
        <label><%=resource.getString("county")%></label>
        <!--<input class="form-control" name="address" type="text" placeholder="Enter county"> -->
        <select id="county" name= "county" class="form-control">
            <option value="Appling">Appling</option>
            <option value="Atkinson">Atkinson</option>
            <option value="Bacon">Bacon</option>
            <option value="Baker">Baker</option>
            <option value="Baldwin">Baldwin</option>
            <option value="Banks">Banks</option>
            <option value="Barrow">Barrow</option>
            <option value="Bartow">Bartow</option>
            <option value="Ben Hill">Ben Hill</option>
            <option value="Berrien">Berrien</option>
            <option value="Bibb">Bibb</option>
            <option value="Bleckley">Bleckley</option>
            <option value="Brantley">Brantley</option>
            <option value="Brooks">Brooks</option>
            <option value="Bryan">Bryan</option>
            <option value="Bulloch">Bulloch</option>
            <option value="Burke">Burke</option>
            <option value="Butts">Butts</option>
            <option value="Calhoun">Calhoun</option>
            <option value="Camden">Camden</option>
            <option value="Candler">Candler</option>
            <option value="Carroll">Carroll</option>
            <option value="Catoosa">Catoosa</option>
            <option value="Charlton">Charlton</option>
            <option value="Chatham">Chatham</option>
            <option value="Chattahoochee">Chattahoochee</option>
            <option value="Chattooga">Chattooga</option>
            <option value="Cherokee">Cherokee</option>
            <option value="Clarke">Clarke</option>
            <option value="Clay">Clay</option>
            <option value="Clayton">Clayton</option>
            <option value="Clinch">Clinch</option>
            <option value="Cobb">Cobb</option>
            <option value="Coffee">Coffee</option>
            <option value="Colquitt">Colquitt</option>
            <option value="Columbia">Columbia</option>
            <option value="Cook">Cook</option>
            <option value="Coweta">Coweta</option>
            <option value="Crawford">Crawford</option>
            <option value="Crisp">Crisp</option>
            <option value="Dade">Dade</option>
            <option value="Dawson">Dawson</option>
            <option value="Decatur">Decatur</option>
            <option value="DeKalb">DeKalb</option>
            <option value="Dodge">Dodge</option>
            <option value="Dooly">Dooly</option>
            <option value="Dougherty">Dougherty</option>
            <option value="Douglas">Douglas</option>
            <option value="Early">Early</option>
            <option value="Echols">Echols</option>
            <option value="Effingham">Effingham</option>
            <option value="Elbert">Elbert</option>
            <option value="Emanuel">Emanuel</option>
            <option value="Evans">Evans</option>
            <option value="Fannin">Fannin</option>
            <option value="Fayette">Fayette</option>
            <option value="Floyd">Floyd</option>
            <option value="Forsyth">Forsyth</option>
            <option value="Franklin">Franklin</option>
            <option value="Fulton">Fulton</option>
            <option value="Gilmer">Gilmer</option>
            <option value="Glascock">Glascock</option>
            <option value="Glynn">Glynn</option>
            <option value="Gordon">Gordon</option>
            <option value="Grady">Grady</option>
            <option value="Greene">Greene</option>
            <option value="Gwinnett">Gwinnett</option>
            <option value="Habersham">Habersham</option>
            <option value="Hall">Hall</option>
            <option value="Hancock">Hancock</option>
            <option value="Haralson">Haralson</option>
            <option value="Harris">Harris</option>
            <option value="Hart">Hart</option>
            <option value="Heard">Heard</option>
            <option value="Henry">Henry</option>
            <option value="Houston">Houston</option>
            <option value="Irwin">Irwin</option>
            <option value="Jackson">Jackson</option>
            <option value="Jasper">Jasper</option>
            <option value="Jeff Davis">Jeff Davis</option>
            <option value="Jefferson">Jefferson</option>
            <option value="Jenkins">Jenkins</option>
            <option value="Johnson">Johnson</option>
            <option value="Jones">Jones</option>
            <option value="Lamar">Lamar</option>
            <option value="Lanier">Lanier</option>
            <option value="Laurens">Laurens</option>
            <option value="Lee">Lee</option>
            <option value="Liberty">Liberty</option>
            <option value="Lincoln">Lincoln</option>
            <option value="Long">Long</option>
            <option value="Lowndes">Lowndes</option>
            <option value="Lumpkin">Lumpkin</option>
            <option value="Macon">Macon</option>
            <option value="Madison">Madison</option>
            <option value="Marion">Marion</option>
            <option value="McDuffie">McDuffie</option>
            <option value="McIntosh">McIntosh</option>
            <option value="Meriwether">Meriwether</option>
            <option value="Miller">Miller</option>
            <option value="Mitchell">Mitchell</option>
            <option value="Monroe">Monroe</option>
            <option value="Montgomery">Montgomery</option>
            <option value="Morgan">Morgan</option>
            <option value="Murray">Murray</option>
            <option value="Muscogee">Muscogee</option>
            <option value="Newton">Newton</option>
            <option value="Oconee">Oconee</option>
            <option value="Oglethorpe">Oglethorpe</option>
            <option value="Paulding">Paulding</option>
            <option value="Peach">Peach</option>
            <option value="Pickens">Pickens</option>
            <option value="Pierce">Pierce</option>
            <option value="Pike">Pike</option>
            <option value="Polk">Polk</option>
            <option value="Pulaski">Pulaski</option>
            <option value="Putnam">Putnam</option>
            <option value="Quitman">Quitman</option>
            <option value="Rabun">Rabun</option>
            <option value="Randolph">Randolph</option>
            <option value="Richmond">Richmond</option>
            <option value="Rockdale">Rockdale</option>
            <option value="Schley">Schley</option>
            <option value="Screven">Screven</option>
            <option value="Seminole">Seminole</option>
            <option value="Spalding">Spalding</option>
            <option value="Stephens">Stephens</option>
            <option value="Stewart">Stewart</option>
            <option value="Sumter">Sumter</option>
            <option value="Talbot">Talbot</option>
            <option value="Taliaferro">Taliaferro</option>
            <option value="Tattnall">Tattnall</option>
            <option value="Taylor">Taylor</option>
            <option value="Telfair">Telfair</option>
            <option value="Terrell">Terrell</option>
            <option value="Thomas">Thomas</option>
            <option value="Tift">Tift</option>
            <option value="Toombs">Toombs</option>
            <option value="Towns">Towns</option>
            <option value="Treutlen">Treutlen</option>
            <option value="Troup">Troup</option>
            <option value="Turner">Turner</option>
            <option value="Twiggs">Twiggs</option>
            <option value="Union">Union</option>
            <option value="Upson">Upson</option>
            <option value="Walker">Walker</option>
            <option value="Walton">Walton</option>
            <option value="Ware">Ware</option>
            <option value="Warren">Warren</option>
            <option value="Washington">Washington</option>
            <option value="Wayne">Wayne</option>
            <option value="Webster">Webster</option>
            <option value="Wheeler">Wheeler</option>
            <option value="White">White</option>
            <option value="Whitfield">Whitfield</option>
            <option value="Wilcox">Wilcox</option>
            <option value="Wilkes">Wilkes</option>
            <option value="Wilkinson">Wilkinson</option>
            <option value="Worth">Worth</option>
        </select>
    </div>

    <div class="form-group" id="free">
        <label><%=resource.getString("freeServices")%></label><br>
        <%--TODO We need to explain who would qualify for free clinic services--%>
        <label class="radio-inline" for="pickfreeY">
            <input id="pickfreeY" name="pickFY" type="radio" value="freeY"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="pickfreeN">
            <input id="pickfreeN" name="pickFN" type="radio" value="freeN"><%=resource.getString("no")%>
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
            <option value="2">MARTA Rail Service</option>
            <option value="3">MARTA <%=resource.getString("bus")%></option>
            <option value="4"><%=resource.getString("none")%></option>
        </select>
    </div>

    <%--<div class="form-group" id="parking">--%>
        <%--<label>Do you require parking space?</label><br>--%>
        <%--<label class="radio-inline" for="yes">--%>
            <%--<input id="yes" name="parking" type="radio" value="y"><%=resource.getString("yes")%>--%>
        <%--</label>--%>
        <%--<label class="radio-inline" for="no">--%>
            <%--<input id="no" name="parking" type="radio" value="n"><%=resource.getString("no")%>--%>
        <%--</label>--%>
    <%--</div>--%>
    <br>
</fieldset>

<fieldset class="form-step">
    <legend><%=resource.getString("clinicServices")%></legend>

    <div class="form-group" id="appt">
        <label><%=resource.getString("kindAppointments")%></label><br>
        <label class="radio-inline" for="walkin">
            <input id="walkin" name="appt" type="radio" value="walk"><%=resource.getString("walkIn")%>
        </label>
        <label class="radio-inline" for="appointment">
            <input id="appointment" name="appt" type="radio" value="appmt"><%=resource.getString("appointment")%>
        </label>
    </div>
    <div class="form-group" id="insurance">
        <label><%=resource.getString("insurance")%></label>
        <select class="form-control" id="insurancetype">
            <option value="1">Medicare</option>
            <option value="2">Medicaid</option>
            <option value="3">Peachcare</option>
            <option value="4">Other</option>
            <%--TODO is this being handled right? 5 was added--%>
            <option value="5"><%=resource.getString("none")%></option>
        </select>
    </div>

    <div class="form-group" id="interpreter">
        <%--TODO this was added--%>
        <label><%=resource.getString("interpreterTranslator")%></label><br>
        <label class="radio-inline" for="interpreteryes">
            <input id="interpreteryes" name="interpreter" type="radio" value="interpYes"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="interpreterno">
            <input id="interpreterno" name="interpreter" type="radio" value="interpNo"><%=resource.getString("no")%>
        </label>
    </div>

    <div class="form-group" id="forms">
        <label><%=resource.getString("medicalForms")%></label><br>
        <label class="radio-inline" for="formsyes">
            <input id="formsyes" name="forms" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="formsno">
            <input id="formsno" name="forms" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>

    <div class="form-group" id="phone">
        <label><%=resource.getString("spanishReceptionist")%></label><br>
        <label class="radio-inline" for="phoneyes">
            <input id="phoneyes" name="phone" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="phoneno">
            <input id="phoneno" name="phone" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>

    <div class="form-group" id="call">
        <label><%=resource.getString("onCall")%></label><br>
        <label class="radio-inline" for="callyes">
            <input id="callyes" name="call" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="callno">
            <input id="callno" name="call" type="radio" value="n"><%=resource.getString("no")%>
        </label>
    </div>
    <!--<div class="form-group" id="date">
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
        <label class="checkbox-inline" for="all">
            <input id="all" type="checkbox" value="A">All/Any
        </label>
    </div> -->
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
        <label class="radio-inline" for="chilyes">
            <input id="chilyes" name="chil" type="radio" value="y"><%=resource.getString("yes")%>
        </label>
        <label class="radio-inline" for="chilno">
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
