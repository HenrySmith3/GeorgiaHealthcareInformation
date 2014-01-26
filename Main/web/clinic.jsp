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
    <h1>Add Clinic Services</h1>
</div><br>
<br>

<div class="span10">
<form class="form-horizontal form-stepped" action="/html_form_action.asp">


<fieldset class="form-step">
    <input type="hidden" value="addHospital" name="action" id="action">
    <div id="name" class="form-group">
        <label>Name</label>
        <br>
        <textarea id="name" name = "name" rows = "1" cols = "60" placeholder="name"></textarea>

        <br>
    </div>

    <div class="form-group" id="county">
        <label>County</label>
        <!--<input class="form-control" name="address" type="text" placeholder="Enter county"> -->
        <select id="county" name = "county" class="form-control">
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
    <br>
    <div id="city" class="form-group">
    <label>City</label>
    <br>
    <textarea id="city" name = "city" rows = "1" cols = "60" placeholder="city"></textarea>

    <br>
    </div>
    <br>
    <div id="website" class="form-group">
    <label>Website</label>
    <br>
    <textarea id="web" name = "web" rows = "1" cols = "60" placeholder="website"></textarea>
    </div>
    <br>
    <div id="phone" class="form-group">
    <label>Phone</label>
    <br>
    <textarea id="pho" name = "pho" rows = "1" cols = "60" placeholder="phone number"></textarea>

    </div>
    <br>
    <div id="address" class="form-group">
        <label>Address</label>
        <br>
    <textarea id="add" name = "add" rows = "1" cols = "60" placeholder="address"></textarea>

    </div>
    <br>
    <div id="zipCode" class="form-group">
        <label>Zip Code</label>
        <br>
    <textarea id="zip" name = "zip" rows = "1" cols = "60" placeholder="zip code"></textarea>

    <br>
    <br>
    </div>
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
        <%--TODO changed format, being handled?--%>
        <label class="checkbox" for="Medicare">
            <input id="Medicare" type="checkbox" value="Medicare" name = "Medicare">Medicare
        </label>
        <label class="checkbox" for="Medicaid">
            <input id="Medicaid" type="checkbox" value="Medicaid" name = "Medicaid">Medicaid
        </label>
        <label class="checkbox" for="Peachcare">
            <input id="Peachcare" type="checkbox" value="Peachcare" name = "Peachcare">Peachcare
        </label>
        <label class="checkbox" for="Other">
            <input id="OtherHealthcare" type="checkbox" value="OtherHealthcare" name = "OtherHealthcare">Other
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
            <input id="mon" type="checkbox" value="M" name = "Monday">Monday
        </label>
        <label class="checkbox-inline" for="tues">
            <input id="tues" type="checkbox" value="T" name = "Tuesday">Tuesday
        </label>
        <label class="checkbox-inline" for="wed">
            <input id="wed" type="checkbox" value="W" name = "Wednesday">Wednesday
        </label>
        <label class="checkbox-inline" for="thurs">
            <input id="thurs" type="checkbox" value="R" name = "Thursday">Thursday
        </label>
        <label class="checkbox-inline" for="fri">
            <input id="fri" type="checkbox" value="F" name = "Friday">Friday
        </label>
        <label class="checkbox-inline" for="sat">
            <input id="sat" type="checkbox" value="S" name = "Saturday">Saturday
        </label>
        <label class="checkbox-inline" for="sun">
            <input id="sun" type="checkbox" value="Su" name = "Sunday">Sunday
        </label>
        <label class="checkbox-inline" for="all">
            <input id="all" type="checkbox" value="A" name = "AnyDay">All/Any
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
        <%--additional specialties--%>
        <label class="checkbox-inline" for="primary">
            <input id="primary" type="checkbox" value="pri" name="primaryCare" >Primary Care
        </label>
        <label class="checkbox-inline" for="internal">
            <input id="internal" type="checkbox" value="int" name="internal">Internal Medicine
        </label>
        <label class="checkbox-inline" for="child">
            <input id="child" type="checkbox" value="chi" name="child">Pediatric Care
        </label>
        <label class="checkbox-inline" for="elder">
            <input id="elder" type="checkbox" value="eld" name="elder">Geriatric Care
        </label>
        <label class="checkbox-inline" for="diabetes">
            <input id="diabetes" type="checkbox" value="dia" name="diabetes">Diabetes
        </label>
        <label class="checkbox-inline" for="pain">
            <input id="pain" type="checkbox" value="pai" name="pain">Pain Management
        </label>
        <label class="checkbox-inline" for="trauma">
            <input id="trauma" type="checkbox" value="trau" name="trauma">Trauma/Emergency
        </label>
        <label class="checkbox-inline" for="surgery">
            <input id="surgery" type="checkbox" value="sur" name="surgery">General Surgery
        </label>
        <label class="checkbox-inline" for="radio">
            <input id="radio" type="checkbox" value="rad" name="radio">Radiology
        </label>
        <label class="checkbox-inline" for="skin">
            <input id="skin" type="checkbox" value="skin" name="skin">Dermatology
        </label>
        <label class="checkbox-inline" for="ent">
            <input id="ent" type="checkbox" value="ent" name="ent">ENT (Ears/Nose/Throat)
        </label>
        <label class="checkbox-inline" for="allergy">
            <input id="allergy" type="checkbox" value="alle" name="allergy">Allergy/Asthma
        </label>
        <label class="checkbox-inline" for="prenatal">
            <input id="prenatal" type="checkbox" value="prena" name="prenatal">Prenatal Care
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
