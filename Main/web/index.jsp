<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 10/6/13
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<h1>Health Resource Guide Survey</h1>
<p>Located inside the 285 Perimter   Yes/No</p>
<br>
<p>Has Spanish-speaking staff or interpreter services   Yes/No</p>
<br>
<p>Name of Facility Interviewed ___________________</p>
<br>
<p>Person Interviewed _______________</p>
<p>Position at Facility _______________</p>
<br>
<p>Address of facility interviewd</p>
<br>
<p>Line 1 (# & Street) _____________________</p>
<br>
<p>Line 2 (Apt, Suite) _____________________</p>
<br>
<p>City ________</p><p>County _________</p><p>Zip Code _________</p>
<br>
<p>Phone(Main)   (___)__________ Fax ______</p>
<br>
<p> Website _____________________</p>
<br>
<p>Is this the main facility or a brance? (Circle one)          Main facility/Branch</p>
<br>
<p>(If only facility, skip to page 2)</p>
<br>
<p>If branch, refer to main facility survey # (for interviewer only) _______ </p>
<br>
<p>If branch, refer to main faility survey # (for interviewer only) _________ </p>
<br>
<p>Other Associated Facilities: </p>
<br>
<p> 1. __________________ Phone (___) _________ </p>
<br>
<br>
<h2>Hours of Operation</h2>
<br>
<p>Is the facility open 24 hours, 7 days a week?    Yes/No (If yes, skip to page 3)</p>
<br>
<br>
<p>Is there someone on-call after normal business hours?    Yes/No</p>
<br>
<p>Other comments/notes (for interviewer): </p>
<p>Final Notes/Comments:</p>

<form name="input" action="html_form_action.asp" method="get">
    <div>
        <h2>First Section: </h2>
        Textbox: <input type="text" name="Text1" value="Default Text">
    </div>
    <br><br>
    <div>
        <h2>Second Section:</h2>
        <input type="checkbox" name="option" value="option1">Checkbox One<br>
        <input type="checkbox" name="option" value="option2">Checkbox Two<br>
        <br><br>
        <input type="submit" value="Submit">
    </div>
</form>

</body>
</html>