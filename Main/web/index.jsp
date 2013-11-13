<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 10/6/13
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.

  Update: 10/29/2013
  User: Priscilla
  Time:11:18
--%>
<!DOCTYPE html>
<html>
  <head>
    <title>User Form</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="boostrap/js/bostrap.min.js"></script>
        <style type="text/css">
            h1 { text-align:center; } 
            h4 { text-align: center; }
            body {
                margin: auto;
                width: 70%;
            }
            .form-control{
                margin-right: 10px;
            }
        </style>
  </head> <!--end of header-->

  <body>
        
    <div id="title">
            <h1>Clinic Search Form</h1>
      <h4>Please fill out this form information based on the individual(s) who need healthcare. </h4>
    </div>

    <br>
    <br>

        <div class="span12">
    <form class="form-horizontal" action="html_form_action.asp" method="get">
      <fieldset>
        <legend> Personal Information </legend>
                
        <div id="address" class="form-horizontal">
          <label>What county does the person who needs healthcare live in?</label>
                    <br>
                    <input type="text" class="form-control" name="county"><br>
                    
        </div>


        <br>
        <br>

        <div id="sex" class="form-inline">
        <label>Sex:</label>
                <br>
        <input type = "radio" class="form-control"
          name = "pickS"
          id = "pickSF"
          value = "female" />
        <label for = "pickSF">Female</label>

        <input type = "radio" class="form-control"
          name = "pickS"
          id = "pickSM"
          value = "male" />
        <label for = "pickSM">Male</label>
    </div>
      </fieldset>

    <br>
    <br>


      <fieldset>
        <legend> Transportation </legend>
        <div id="trans" class = "form-inline">
          <label>What is your preferred method of transportation?</label>
                    <br>
          <select id = "transportation">
            <option value = "1">Drive</option>
            <option value = "2">MARTA</option>
            <option value = "3">Bus</option>
            <option value = "4">Bike</option>
          </select>
        </div>

        <br>
        <br>

        <div id="parking" class="form-inline">
          <label> Do you require parking space? </label>
                    <br>
          <input type = "radio" class="form-control"
            name = "parking"
            id = "parkyes"
            value = "y" />
          <label for = "yes">Yes</label>

          <input type = "radio" class="form-control"
            name = "parking"
            id = "parkno"
            value = "n" />
          <label for = "no">No</label>
        </div>


      </fieldset>

    <br>
    <br>

      <fieldset>
        <legend>Clinic Services</legend>
        <div id="appt" class="form-inline">
        <label>What sort of appointments do you prefer?</label>
                <br>
        <input type = "radio" class="form-control"
          name = "appt"
          id = "walkin"
          value = "walk" />
        <label for = "walkin">Walk-In</label>

        <input type = "radio" class="form-control"
          name = "appt"
          id = "appointment"
          value = "appmt" />
        <label for = "appointment">Appointment</label>
        </div>

        <br>
        <br>

        <div id="insurance" class="form-inline">
          <label>What medical insurance do you have?</label>
                    <br>
            <select id = "medinsurance">
              <option value = "1">Medicare</option>
              <option valie = "2">Medicaid</option>
              <option value = "3">Peachcare</option>
              <option value = "4">None</option>
            </select>
        </div>

        <br>
        <br>

        <div id="language" class="form-inline">
        <p>
          <label>Do you require any of these faculty members to speak Spanish?</label>
                    <br>
          <input type = "checkbox" class="form-control"
            id = "receptionist"
            value = "recept" />
          <label for = "receptionist">Receptionist</label>

          <input type = "checkbox" class="form-control"
            id = "nurses"
            value = "nur" />
          <label for = "nurses">Nurses</label>

          <input type = "checkbox" class="form-control"
            id = "doctor"
            value = "doc" />
          <label for = "doctor">Doctor</label>
        </p>
        </div>

        <br>
        <br>

        <div id="forms" class="form-inline">
          <label>Do the medical forms need to be written in Spanish?</label>
                    <br>
          <input type = "radio" class="form-control"
            name = "forms"
            id = "medformyes"
            value = "y" />
          <label for = "yes">Yes</label>

          <input type = "radio" class="form-control"
            name = "forms"
            id = "medformno"
            value = "n" />
          <label for = "no">No</label>
        </div>

        <br>
        <br>

        <div id="phone" class="form-inline">
          <label>Do you need a phone interpreter?</label>
                    <br>
          <input type = "radio" class="form-control"
            name = "phone"
            id = "phoneyes"
            value = "y" />
          <label for = "yes">Yes</label>

          <input type = "radio" class="form-control"
            name = "forms"
            id = "phoneno"
            value = "n" />
          <label for = "no">No</label>
        </div>

        <br>
        <br>

        <div id="call" class="form-inline">
          <label>Do you require someone to be on-call after the clinic's normal business hours?</label>
                        <br>
            <input type = "radio" class="form-control"
              name = "call"
              id = "yes"
              value = "y" />
            <label for = "yes">Yes</label>

            <input type = "radio" class="form-control"
              name = "call"
              id = "no"
              value = "n" />
            <label for = "no">No</label>
          </div>

        <br>
        <br>

        <div id="date" class="form-inline">
          <label>Which days are best for you to visit the clinic?</label>
          <br>
          <input type = "checkbox" class="form-control"
            id = "mon"
            value = "M" />
          <label for = "mon">Monday</label>

          <input type = "checkbox" class="form-control"
            id = "tues"
            value = "T" />
          <label for = "tues">Tuesday</label>

          <input type = "checkbox" class="form-control"
            id = "wed"
            value = "W" />
          <label for = "wed">Wednesday</label>

          <input type = "checkbox" class="form-control"
            id = "thurs"
            value = "R" />
          <label for = "thurs">Thursday</label>

          <input type = "checkbox" class="form-control"
            id = "fri"
            value = "F" />
          <label for = "fri">Friday</label>

          <input type = "checkbox" class="form-control"
            id = "sat"
            value = "S" />
          <label for = "sat">Saturday</label>

          <input type = "checkbox" class="form-control"
            id = "sun"
            value = "Su" />
          <label for = "sun">Sunday</label>
        </div>


      </fieldset>

    <br>
    <br>

      <fieldset>
        <legend>Healthcare</legend>
        <div id="type" class="form-inline">
          <p>
          <label>Do you need the clinic to handle any of the following?</label>
          <br>
          <input type = "checkbox" class="form-control"
            id = "women"
            value = "wo" />
          <label for = "receptionist">Women's Health</label>

          <input type = "checkbox" class="form-control"
            id = "mens"
            value = "me" />
          <label for = "nurses">Men's Health</label>

          <input type = "checkbox" class="form-control"
            id = "family"
            value = "fam" />
          <label for = "family">Family Health</label>

          <input type = "checkbox" class="form-control"
            id = "mental"
            value = "ment" />
          <label for = "mental">Mental Health</label>

          <input type = "checkbox" class="form-control"
            id = "dental"
            value = "dent" />
          <label for = "dental">Dental Health</label>

          <input type = "checkbox" class="form-control"
            id = "vision"
            value = "vis" />
          <label for = "vision">Vision Health</label>
          </p>
        </div>

        <br>
        <br>

        <div id="children" class="form-inline">
          <label>Do you have children that need medical assistance?</label>
                        <br>
            <input type = "radio" class="form-control"
              name = "child"
              id = "childyes"
              value = "y" />
            <label for = "yes">Yes</label>

            <input type = "radio" class="form-control"
              name = "child"
              id = "childno"
              value = "n" />
            <label for = "no">No</label>
        </div>

        <br>
        <br>

        <div id = "ages">
          <label>If yes, please check all the ages that apply.</label>
                    <label>
            <input type = "checkbox" class="form-control"
              id = "05"
              value = "1" />
            <span>0-5</span>
                    </label>

                    <label>
            <input type = "checkbox" class="form-control"
              id = "610"
              value = "2" />
            <span>6-10</span>
                    </label>

                    <label>
            <input type = "checkbox" class="form-control"
              id = "1115"
              value = "3" />
            <span>11-15</span>
                    </label>

                    <label>
            <input type = "checkbox" class="form-control"
              id = "1617"
              value = "4" />
            <span>16-17</span>
                    </label>
        </div>

        <br>
        <br>

        <div id = "mentalhealth">
          <label>Do you need mental health, counseling, or rehabilitation services in any of the following?</label>
            
                        <label>
            <input type = "checkbox" class="form-control"
              id = "csubstance"
              value = "subt" />
            <span>Substance Abuse</span>
                        </label>

                    <label>
            <input type = "checkbox" class="form-control"
              id = "csex"
              value = "se" />
            <span>Sexual Abuse</span>
                    </label>

                    <label>
            <input type = "checkbox" class="form-control"
              id = "canger"
              value = "ang" />
            <span>Anger Management</span>
                    </label>

        
                    <label>
                    <input type = "checkbox" class="form-control"
              id = "csexHIV"
              value = "HIV" />
            <span>HIV</span>
                    </label>

            
                    <label>
                    <input type = "checkbox" class="form-control"
              id = "sexLGBT"
              value = "LGBT" />
            <span>LGBT</span>
                    </label>
        </div>


      </fieldset>           <div>
        <input type="submit" value="Submit" class="btn btn-primary">
    </div>
        

    <br>
    <br>
    <%--<div>--%>
          <%--<input type="submit" value="Submit" class="btn btn-primary">--%>
      <%--</div>--%>
    </form>
    <%--<button type="button">Submit</button>--%>
        </div>


  </body>
</html>