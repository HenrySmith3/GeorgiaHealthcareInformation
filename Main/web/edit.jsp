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
<%@ page import="net.sf.json.JSONObject" %>
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
    JSONObject hospital = (JSONObject)request.getAttribute("hospital");
    if (hospital == null) {
        hospital = JSONObject.fromObject("{\"FALSE\":0,\"id\":'',\"itp\":'',\"spanishSpeakingStaff\":'',\"name\":'',\"addressLine1\":'',\"addressLine2\":'',\"city\":'',\"county\":'',\"zip\":'',\"phone\":'',\"fax\":'',\"website\":'',\"mainFacility\":'',\"branchRefNumber\":'',\"associatedFacilities\":'',\"intComm\":'',\"open247\":'',\"onCall\":'',\"openTimes\":'',\"closeTimes\":'',\"commentsOnTimes\":'',\"spanishTimesComment\":'',\"hoursGuide\":'',\"walkIn\":'',\"walkInComment\":'',\"apptGuide\":'',\"walkInGuide\":'',\"parking\":'',\"parkingComment\":'',\"publicTransportation\":'',\"publicTransportationGuide\":'',\"publicTransportationComment\":'',\"publicTransportationOther\":'',\"publicTransportationOtherComment\":'',\"freeTransport\":'',\"spanAdmin\":'',\"spanAdminGuide\":'',\"spanNurse\":'',\"spanNurseGuide\":'',\"spanDoc\":'',\"spanDocGuide\":'',\"spanInterpreter\":'',\"spanInterpreterGuide\":'',\"spanPhone\":'',\"spanPhoneGuide\":'',\"spanFo\":'',\"spanFoGuide\":'',\"insurance\":'',\"insuranceComment\":'',\"medicare\":'',\"medicaid\":'',\"peachCare\":'',\"pay\":'',\"financialAssistance\":'',\"payPlanGuide\":'',\"SlideSc\":'',\"financialAssistanceComment\":'',\"finAssPh\":'',\"finAllPhComment\":'',\"spcFCH\":'',\"spcWH\":'',\"spcMH\":'',\"spcMHC\":'',\"spcDH\":'',\"spcVH\":'',\"spcOT\":'',\"freeLow\":'',\"spcComment\":'',\"oteSpecial\":'',\"spcDk\":'',\"age\":'',\"ageStart\":'',\"ageEnd\":'',\"ageOTComment\":'',\"childGuide\":'',\"adolescentGuide\":'',\"adultGuide\":'',\"agesGuide\":'',\"otServ\":'',\"hivTestGuide\":'',\"abortionGuide\":'',\"mhCount\":'',\"mhCounSG\":'',\"mhCounOT\":'',\"subAbGuide\":'',\"sexAbGuide\":'',\"angManGuide\":'',\"hivConsGuide\":'',\"lgbtGuide\":'',\"suppGGuide\":'',\"finalComment\":'',\"notes\":'',\"notesLowFree\":'',\"notesLowFree2\":''}");
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
    <input type="hidden" value="lookupIds" name="action" id="action">
    <button type="submit">Lookup Your Hospital's ID</button>
</form>
<br>

<div class="form-group" id="selectclinic">
    <label>Input the ID of the hospital you wish to edit</label><br>
    <form action="/html_form_action.asp">
        <textarea id="id" name="id" rows="1" cols="20" placeholder="hospital ID"><%out.write(hospital.getString("id"));%></textarea>
        <input type="hidden" value="populateFields" name="action" id="action">
        <button type="submit">Populate Fields</button>
    </form>
</div>

<div class="span10">
<form class="form-horizontal form-stepped" action="/html_form_action.asp">


<fieldset class="form-step">
    <input type="hidden" value="editHospital" name="action" id="action">

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
    <div class="form-group" id="city" class="form-inline">
    <label>City</label>
    <br>
    <textarea id="city" name = "city" rows = "1" cols = "60" placeholder="city"><%out.write(hospital.getString("city"));%></textarea>
    <br>
    </div>
    <div class="form-group" id="website" class="form-inline">
    <label>Website</label>
    <br>
    <textarea id="web" name = "web" rows = "1" cols = "60" placeholder="website"><%out.write(hospital.getString("website"));%></textarea>
    </div>
    <div class="form-group" id="phone" class="form-inline">
    <label>Phone</label>
    <br>
    <%--TODO Make sure that the input is the right format for this. Parenthesis, spaces, etc.--%>
    <textarea id="pho" name = "pho" rows = "1" cols = "60" placeholder="phone number"><%out.write(hospital.getString("phone"));%></textarea>
    </div>
    <div class="form-group" id="address" class="form-inline">
        <label>Address</label>
        <br>
        <textarea id="add" name = "add" rows = "1" cols = "60" placeholder="address"><%out.write(hospital.getString("addressLine1"));%></textarea>
    </div>
    <div class="form-group" id="zipCode" class="form-inline">
        <label>Zip Code</label>
        <br>
        <textarea id="zip" name = "zip" rows = "1" cols = "60" placeholder="zip code"><%out.write(hospital.getString("zip"));%></textarea>
    </div>
    <br>
    <div class="form-group" id="appt">
        <label>What kind of appointments can be made?</label><br>
        <label class="radio-inline" for="walkin">
            <input id="walkin" name="appt" type="radio" value="walkin" <%if (hospital.getString("walkIn").equals("1")) out.write("checked='true'");%>>Walk-In
        </label>
        <label class="radio-inline" for="appointment">
            <input id="appointment" name="appt" type="radio" value="appointment" <%if (hospital.getString("walkIn").equals("2")) out.write("checked='true'");%>>Appointment
        </label>
        <%--TODO this option is new--%>
        <label class="radio-inline" for="bothWalkApp">
            <input id="bothWalkApp" name="appt" type="radio" value="bothWalkApp" <%if (hospital.getString("walkIn").equals("3")) out.write("checked='true'");%>>Both
        </label>
    </div>
    <div class="form-group" id="insurance">
        <label>Which medical insurance can be used?</label>
        <label class="checkbox" for="Medicare">
            <input id="Medicare" type="checkbox" value="Medicare" name = "days" <%if (hospital.getString("medicare").equals("true")) out.write("checked='true'");%>>Medicare
        </label>
        <label class="checkbox" for="Medicaid">
            <input id="Medicaid" type="checkbox" value="Medicaid" name = "days" <%if (hospital.getString("medicaid").equals("true")) out.write("checked='true'");%>>Medicaid
        </label>
        <label class="checkbox" for="Peachcare">
            <input id="Peachcare" type="checkbox" value="Peachcare" name = "days" <%if (hospital.getString("peachCare").equals("true")) out.write("checked='true'");%>>Peachcare
        </label>
        <label class="checkbox" for="Other">
            <input id="Other" type="checkbox" value="Other" name = "days">Other
        </label>
    </div>

    <div class="form-group" id="interpreter">
        <%--TODO this was added--%>
        <label>Is there a Spanish interpreter/translator?</label><br>
        <label class="radio-inline" for="interpreteryes">
            <input id="interpreteryes" name="interpreter" type="radio" value="interpY" <%if (hospital.getString("spanInterpreter").equals("3")) out.write("checked='true'");%>>Yes
        </label>
        <label class="radio-inline" for="interpreterno">
            <input id="interpreterno" name="interpreter" type="radio" value="interpN" <%if (hospital.getString("spanInterpreter").equals("1")) out.write("checked='true'");%>>No
        </label>
    </div>

    <div class="form-group" id="forms">
        <label>Do you provide medical forms that are written in Spanish?</label><br>
        <label class="radio-inline" for="formyes">
            <input id="formyes" name="forms" type="radio" value="yes" <%if (hospital.getString("spanFo").equals("1")) out.write("checked='true'");%>>Yes
        </label>
        <label class="radio-inline" for="formno">
            <input id="formno" name="forms" type="radio" value="no" <%if (hospital.getString("spanFo").equals("0")) out.write("checked='true'");%>>No
        </label>
    </div>

    <div class="form-group" id="phone">
        <label>Do you have a Spanish speaking receptionist on the phone?</label><br>
        <label class="radio-inline" for="phoneyes">
            <input id="phoneyes" name="reception" type="radio" value="yes" <%if (hospital.getString("spanPhone").equals("0")) out.write("checked='true'");%>>Yes
        </label>
        <label class="radio-inline" for="phoneno">
            <input id="phoneno" name="reception" type="radio" value="no" <%if (hospital.getString("spanPhone").equals("0")) out.write("checked='true'");%>>No
        </label>
    </div>

    <div class="form-group" id="call">
        <label>Do you have someone available on the phone after the clinic's normal business hours?</label><br>
        <label class="radio-inline" for="afterphoneyes">
            <input id="afterphoneyes" name="call" type="radio" value="yes" <%if (hospital.getString("onCall").equals("true")) out.write("checked='true'");%>>Yes
        </label>
        <label class="radio-inline" for="afterphoneno">
            <input id="afterphoneno" name="call" type="radio" value="no" <%if (hospital.getString("onCall").equals("false")) out.write("checked='true'");%>>No
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
            <input id="women" type="checkbox" value="wo" name = "womens" <%if (hospital.getString("spcWH").equals("true")) out.write("checked='true'");%>>Womens
        </label>
        <label class="checkbox-inline" for="mens">
            <input id="mens" type="checkbox" value="me" name = "mens" <%if (hospital.getString("spcMH").equals("true")) out.write("checked='true'");%>>Mens
        </label>
        <label class="checkbox-inline" for="family">
            <input id="family" type="checkbox" value="fam" name = "family" <%if (hospital.getString("spcFCH").equals("true")) out.write("checked='true'");%>>Family
        </label>
        <label class="checkbox-inline" for="mental">
            <input id="mental" type="checkbox" value="ment" name = "mental" <%if (hospital.getString("spcMHC").equals("true")) out.write("checked='true'");%>>Mental
        </label>
        <label class="checkbox-inline" for="dental">
            <input id="dental" type="checkbox" value="dent" name = "dental" <%if (hospital.getString("spcDH").equals("true")) out.write("checked='true'");%>>Dental
        </label>
        <label class="checkbox-inline" for="vision">
            <input id="vision" type="checkbox" value="vis" name = "vision" <%if (hospital.getString("spcVH").equals("true")) out.write("checked='true'");%>>Vision
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
            <input id="substance" type="checkbox" value="subt" name = "mentalhealth" <%if (hospital.getString("subAbGuide").equals("true")) out.write("checked='true'");%>>Substance
        </label>
        <label class="checkbox">
            <input id="sex" type="checkbox" value="se" name = "mentalhealth1" <%if (hospital.getString("sexAbGuide").equals("true")) out.write("checked='true'");%>>Sexual
        </label>
        <label class="checkbox">
            <input id="anger" type="checkbox" value="ang" name = "mentalhealth2" <%if (hospital.getString("angManGuide").equals("true")) out.write("checked='true'");%>>Anger
        </label>
        <label class="checkbox">
            <input id="sexHIV" type="checkbox" value="HIV" name = "mentalhealth3" <%if (hospital.getString("hivConsGuide").equals("true")) out.write("checked='true'");%>>HIV
        </label>
        <label class="checkbox">
            <input id="sexLGBT" type="checkbox" value="LGBT" name = "mentalhealth4" <%if (hospital.getString("lgbtGuide").equals("true")) out.write("checked='true'");%>>LGBT
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
