<!DOCTYPE html>
<html>

<head>
    <title>Clinic Form</title>
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
            <ul class="nav navbar-nav navbar-right">
        </nav>
    </div>
</header>

<br>
<br>
<br>

<h1>Health Resource Guide Survey</h1>

<form>
    <div id="perimeter">Located inside the 275 perimter?</div>
    <div id="spanish">Has Spanish-speaking staff or interpreter services?</div>
    <div id="name">Name of the Facility Interviewed?</div>
    <div id="address">Address of facility interviewed?</div>
    <div id="phone">Phone(Main)</div>
    <div id="fax">Fax</div>
    <div id="website">Website</div>
    <div id="facility">Is this the main facility or a branch?</div>
    <div id="branch">(If only facility, skip to page 2) If branch, refer to main facility survey...</div>
    <div id="associated">Other associated facilities:</div>
    <div id="hours">Hours of Operation
        <div id="allday">Is the facility open 24 hours, 7 days week? (If yes, skip to page3)</div>
        <div id="oncall">Is there someone on-call after normal business hour?</div>
    </div>

    <h2>Page 2</h2>
    <p>Ask for times on M-Su</p>

    <h2>Page 3</h2>
    <div id="apptmt">Do you accept walk-ins or is service by appointment only? 1.Walkins only 2. appointments only 3.both 4.don'tknow</div>
    <div id="parking">Is there parking avaiable? 1.free on street or lots 2.parking for a fee in garage or street 3. other 4. don't know</div>
    <div id="transportation">Are there any of the following types of public transporation to your facility? 1.MARTA 2. Gwinnet Bus 3. GA Bus Line 4. Other (specify) 5. don't know</div>
    <div id="transportation2">Are there any other forms of transportation available? 1. yes (specify)</div>
    <div id="spanish2">Does your facility have administrative staff that can communicate in Spanish with Spanish-speaking patients about their appointments and/or paperwork? 1.Yes always 2. Yes sometimes 3. no 4. don't know</div>
    <div id="spanish3">Does your facility have mid-level providers and or physicians or dentists that can communicate in Spanish with Spanish-speaking patients about their health care? 1. yes always 2. yes sometimes 3. no 4. don't know</div>
    <div id="interpreters">Does your facility have interpreters on-site taht can communicate in Spanish with Spanish-speaking patients about their health care? 1. yes always 2. yes sometimes 3. yes upon request 4. no 5. don't know</div>

</form>

</html>