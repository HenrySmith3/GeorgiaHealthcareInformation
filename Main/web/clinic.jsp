<!DOCTYPE html>
<html>

    <head>
        <title>Clinic Form</title>
        <input type="hidden" value="addHospital" name="action" id="action">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link href="bootstrap/css/bootstrap.min.css" media="screen" rel="stylesheet">
        <script src="bootstrap/js/jquery.js"></script> 
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="main.js"></script>
        <style type="text/css">
      h1 { text-align:center; } 
      body {
          margin: auto;
          width: 70%;
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
        <a class="navbar-brand" href="home.jsp">
          Home
        </a>
      </div>
      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
        <ul class="nav navbar-nav">
          <li>
            <a href="form.jsp">
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
    
    <h1>Clinic Addition Form</h1>
    
    <form>
        <div class="form-group" id="perimeter">
          <label>Is your clinic located inside the Atlanta perimeter?</label><br>
          <label class="radio-inline" for="perimeteryes">
            <input id="perimeteryes" name="perimeter" type="radio" value="pyes">Yes
          </label>
          <label class="radio-inline" for="perimeterno">
            <input id="perimeterno" name="perimeter" type="radio" value="pno">No
          </label>
        </div>


        <div class="form-group" id="spanish">
          <label>Does your clinic have Spanish-speaking staff or interpreter services?</label><br>
          <label class="radio-inline" for="spanishyes">
            <input id="spanishyes" name="spanish" type="radio" value="syes">Yes
          </label>
          <label class="radio-inline" for="spanishno">
            <input id="spanishno" name="spanish" type="radio" value="sno">No
          </label>
        </div>

        <div class="form-group" id="name">
          <label>Name of the facility Interviewed</label><br>
          <input type="text" name="name">
        </div>


        <div class="form-group" id="address">
          <label>Address of facility interviewed</label><br>
          <input type="text" name="address">
        </div>


        <div class="form-group" id="phone">
          <label>Facility's phone number:</label><br>
          <input type="text" name="phone">
        </div>

        <div class="form-group" id="spphone">
          <label>Spanish extension for phone number (if you have one):</label><br>
          <input type="text" name="spphone">
        </div>

        <div class="form-group" id="fax">
          <label>Facility's fax number:</label><br>
          <input type="text" name="address">
        </div>

        <div class="form-group" id="website">
          <label>Facility's website URL</label><br>
          <input type="text" name="website">
        </div>

        <div class="form-group" id="facility">
          <label>Is this the main facility or a branch?</label><br>
          <label class="radio-inline" for="facilityyes">
            <input id="facilityyes" name="facility" type="radio" value="facyes">Yes
          </label>
          <label class="radio-inline" for="facilityno">
           <input id="facilityno" name="facility" type="radio" value="facno">No
         </label>
        </div>

        <div id="branch">(If only facility, skip to page 2) If branch, refer to main facility survey...</div>
        <br><br>


        <div id="associated">Other associated facilities:</div>
        <div id="hours">Hours of Operation

        <div class="form-group" id="allday">
          <label>Is the facility open 24 hours, 7 days week? (If yes, skip to page3)</label><br>
          <label class="radio-inline" for="alldayyes">
            <input id="alldayyes" name="allday" type="radio" value="alldayY">Yes
          </label>
          <label class="radio-inline" for="alldayno">
            <input id="alldayno" name="allday" type="radio" value="alldayN">No
          </label>
        </div>


        <div class="form-group" id="oncall">
          <label>Is there someone on-call after normal business hour?</label><br>
          <label class="radio-inline" for="oncallyes">
            <input id="oncallyes" name="oncall" type="radio" value="oncallY">Yes
          </label>
          <label class="radio-inline" for="oncallno">
            <input id="oncallno" name="oncall" type="radio" value="oncallN">No
          </label>
        </div>
        

        </div>
        
        <h2>Page 2</h2>
        <div class="form-group" id="daysopen">
          <label>Which days are you open?</label><br>
          <label class="checkbox-inline" for="monday">
            <input id="monday" type="checkbox" value="Monday">Monday
          </label>
          <label class="checkbox-inline" for="tuesday">
            <input id="tuesday" type="checkbox" value="Tuesday">Tuesday
          </label>
          <label class="checkbox-inline" for="wednesday">
            <input id="wednesday" type="checkbox" value="Wednesday">Wednesday
          </label>
          <label class="checkbox-inline" for="thursday">
            <input id="thursday" type="checkbox" value="Thursday">Thursday
          </label>
          <label class="checkbox-inline" for="friday">
            <input id="friday" type="checkbox" value="Friday">Friday
          </label>
          <label class="checkbox-inline" for="saturday">
            <input id="saturday" type="checkbox" value="Saturday">Saturday
          </label>
          <label class="checkbox-inline" for="sunday">
            <input id="sunday" type="checkbox" value="Sunday">Sunday
          </label>
        </div>

        
        <h2>Page 3</h2>
        <div class="form-group" id="apptmt">
          <label>Do you accept walk-ins or is service by appointment only?</label><br>
          <label class="radio-inline" for="appointmentwalk">
            <input id="appointmentwalk" name="apptmt" type="radio" value="walkin">Walkins Only
          </label>

          <label class="radio-inline" for="appointmentappts">
            <input id="appointmentappts" name="apptmt" type="radio" value="appointments">Appointments Only
          </label>

          <label class="radio-inline" for="appointmentboth">
            <input id="appointmentboth" name="apptmt" type="radio" value="dontknow">Don't Know
          </label>
        </div>


        <div class="form-group" id="bus">
          <label>Are you within a mile of a MARTA bus stop?</label><br>
          <label class="radio-inline" for="busyes">
            <input id="busyes" name="bus" type="radio" value="busYes">Yes
          </label>

          <label class="radio-inline" for="busno">
            <input id="busno" name="bus" type="radio" value="busNo">No
          </label>
        </div>


        <div class="form-group" id="whichbus">
          <label>If yes, which MARTA bus stop?</label><br>
          <input type="text" name="martaBus">
        </div>

        <div class="form-group" id="station">
          <label>Are you within a mile of a MARTA rail station?</label><br>
          <label class="radio-inline" for="stationyes">
            <input id="stationyes" name="station" type="radio" value="stationYes">Yes
          </label>

          <label class="radio-inline" for="stationno">
            <input id="stationno" name="station" type="radio" value="stationNo">No
          </label>
        </div>

        <div class="form-group" id="whichstation">
          <label>If yes, which MARTA station stop?</label><br>
          <input type="text" name="martaStation">
        </div>


        <div class="form-group" id="parking">
          <label>Which of the following parking options are available?</label><br>
          <label class="checkbox-inline" for="parkingfree">
            <input id="parkingfree" type="checkbox" value="parkFree">Free Parking
          </label>
          <label class="checkbox-inline" for="parkingfee">
            <input id="parkingfee" type="checkbox" value="parkFee">Parking for a Fee
          </label>
          <label class="checkbox-inline" for="parkingother">
            <input id="parkingother" type="checkbox" value="parkOther">Other
          </label>
          <label class="checkbox-inline" for="parkingdont">
            <input id="parkingdont" type="checkbox" value="parkDont">Don't Know
          </label>
        </div>

        <div class="form-group" id="specother">
          <label>If you selected other, specify.</label><br>
          <input type="text" name="specifyOtherParking">
        </div>


        <div class="form-group" id="spanishstaff">
            <label>Which of the following kinds of staff members can speak Spanish at your facility?</label><br>
            <label class="checkbox-inline" for="spanishdoc">
              <input id="spanishdoc" type="checkbox" value="spanishDoc">Doctors
            </label>
            <label class="checkbox-inline" for="spanishnur">
              <input id="spanishnur" type="checkbox" value="spanishNur">Nurses
            </label>
            <label class="checkbox-inline" for="spanishrecep">
              <input id="spanishrecep" type="checkbox" value="spanishRecep">Receptionist
            </label>
        </div>

        <div class="form-group" id="spanishdocs">
          <label>Does your facility provide documents and forms in Spanish?</label><br>

          <label class="radio-inline" for="docsyes">
            <input id="docsyes" name="spanishdocs" type="radio" value="docsYes">Yes
          </label>

          <label class="radio-inline" for="docsno">
            <input id="docsno" name="spanishdocs" type="radio" value="docsNo">No
          </label>
        </div>

        <div class="form-group" id="interpreters">
          <label>Does your facility have on-site interpreters (for the hard-of-hearing or other languages) that can communicate in Spanish with Spanish-speaking patients about their health care?</label><br>

          <label class="radio-inline" for="interpyes">
            <input id="interpyes" name="interpreters" type="radio" value="interpYes">Yes
          </label>

          <label class="radio-inline" for="interpno">
            <input id="interpno" name="interpreters" type="radio" value="interpNo">No
          </label>
        </div>


        <div class="form-group" id="children">
          <label>Does your facility provide services for children in any of the following age groups?</label><br>
          <label class="checkbox-inline" for="chiltwelvemo">
              <input id="chiltwelvemo" type="checkbox" value="twelvemo">Under 12 months
            </label>
            <label class="checkbox-inline" for="chiltoddlers">
              <input id="chiltoddlers" type="checkbox" value="toddlers">1-3 years old
            </label>
            <label class="checkbox-inline" for="chilyoung">
              <input id="chilyoung" type="checkbox" value="youngchild">4-10 years old
            </label>
            <label class="checkbox-inline" for="chilpreteen">
              <input id="chilpreteen" type="checkbox" value="preteen">11-14 years old
            </label>
            <label class="checkbox-inline" for="chilteen">
              <input id="chilteen" type="checkbox" value="teen">15-18 years old
            </label>
        </div>

        <div class="form-group" id="daycare">
          <label>Does your facility provide any daycare services?</label><br>

          <label class="radio-inline" for="daycareyes">
            <input id="daycareyes" name="daycare" type="radio" value="daycareYes">Yes
          </label>

          <label class="radio-inline" for="daycareno">
            <input id="daycareno" name="daycare" type="radio" value="daycareNo">No
          </label>
        </div>


        <div class="form-group" id="education">
          <label>Do you provide any health education or have a health ed program?</label><br>

          <label class="radio-inline" for="healthedyes">
            <input id="healthedyes" name="education" type="radio" value="educationYes">Yes
          </label>

          <label class="radio-inline" for="healthedno">
            <input id="healthedno" name="education" type="radio" value="educationNo">No
          </label>
        </div>

        <div class="form-group" id="mental">
          <label>Do you treat for mental health such as anxiety and depression?</label><br>

          <label class="radio-inline" for="mentalyes">
            <input id="mentalyes" name="mental" type="radio" value="mentalYes">Yes
          </label>

          <label class="radio-inline" for="mentalno">
            <input id="mentalno" name="mental" type="radio" value="mentalNo">No
          </label>
        </div>

        <div class="form-group" id="countiesonly">
          <label>Is your facility limited to treating people only from certain counties?</label><br>

          <label class="radio-inline" for="countiesonlyyes">
            <input id="countiesonlyyes" name="countiesonly" type="radio" value="countiesonlyYes">Yes
          </label>

          <label class="radio-inline" for="countiesonlyno">
            <input id="countiesonlyno" name="countiesonly" type="radio" value="countiesonlyNo">No
          </label>
        </div>

        <div class="form-group" id="whichcounties">
          <label>If yes, which counties?</label><br>
          <input type="text" name="whichCounties">
        </div>

        <div class="form-group" id="waittime">
          <label>What is your approximate wait time in the waiting room?</label><br>
          <input type="text" name="waitTime">
        </div>

        <div class="form-group" id="appointmentwait">
          <label>How long on average does it take for a patient to get an appointment?</label><br>
          <input type="text" name="appointmentWait">
        </div>

        <br><br><br>
    <div>
        <input type="submit" value="Submit" class="btn btn-primary">
    </div>
    </form>
    </body>

</html>
