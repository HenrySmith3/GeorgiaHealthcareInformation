<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 10/6/13
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		
	</head>

	<body>
		<div id="title">
			<h1>Please fill out the form as accurately as you can </h1>
		</div>

		<br>
		<br>

	    <form name="form1" method="post" action="Servlet"> 
	    <fieldset>
	    <legend> Personal Information </legend>
	    <p>What is your address? <input type="text" name="address"></p>
        <p>City  <input type="text" name="city"> </p>  
        <p>County  <input type="text" name="county"> </p> 
        Sex:</label>
				<input type = "radio"
					name = "pickS"
					id = "pickSF"
					value = "female" />
				<label for = "pickSF">Female</label>

				<input type = "radio"
					name = "pickS"
					id = "pickSM"
					value = "male" />
				<label for = "pickSM">Male</label>
        </fieldset>
        <br>
        <br>
        <fieldset>
				<legend> Transportation </legend>
				<div id="trans">
					<label>What is your preferred method of transportation?</label>
					<select id = "transportation" name = "pubTr">
						<option value = "1">Drive</option>
						<option value = "2">MARTA</option>
						<option value = "3">Bus</option>
						<option value = "4">Bike</option>
					</select>
				</div>

				<br>
				<br>

				<div id="parking">
					<label> Do you require parking space? </label>
					<input type = "radio"
						name = "parking"
						id = "yes"
						value = "y" />
					<label for = "yes">Yes</label>

					<input type = "radio"
						name = "parking"
						id = "no"
						value = "n" />
					<label for = "no">No</label>
				</div>


			</fieldset>
	

		<br>
		<br>

			<fieldset>
				<legend>Clinic Services</legend>
				<div id="appt">
				<label>What sort of appointments do you prefer?</label>
				<input type = "radio"
					name = "appt"
					id = "walkin"
					value = "walk" />
				<label for = "walkin">Walk-In</label>

				<input type = "radio"
					name = "appt"
					id = "appointment"
					value = "appo" />
				<label for = "appointment">Appointment</label>
				</div>

				<br>
				<br>

				<div id="insurance">
					<label>What medical insurance do you have?</label>
						<select id = "insurance">
							<option value = "1">Medicare</option>
							<option valie = "2">Medicaid</option>
							<option value = "3">Peachcare</option>
							<option value = "4">None</option>
						</select>
				</div>

				<br>
				<br>

				<div id="language">
				<p>
					<label>Do you require any of these faculty members to speak Spanish?</label>
					<input type = "checkbox"
						id = "receptionist"
						value = "recept" />
					<label for = "receptionist">Receptionist</label>

					<input type = "checkbox"
						id = "nurses"
						value = "nur" />
					<label for = "nurses">Nurses</label>

					<input type = "checkbox"
						id = "doctor"
						value = "doc" />
					<label for = "doctor">Doctor</label>
				</p>
				</div>

				<br>
				<br>

				<div id="forms">
					<label>Do the medical forms need to be written in Spanish?</label>
					<input type = "radio"
						name = "forms"
						id = "yes"
						value = "y" />
					<label for = "yes">Yes</label>

					<input type = "radio"
						name = "forms"
						id = "no"
						value = "n" />
					<label for = "no">No</label>
				</div>

				<br>
				<br>

				<div id="phone">
					<label>Do you need a phone interpreter?</label>
					<input type = "radio"
						name = "phone"
						id = "yes"
						value = "y" />
					<label for = "yes">Yes</label>

					<input type = "radio"
						name = "forms"
						id = "no"
						value = "n" />
					<label for = "no">No</label>
				</div>

				<br>
				<br>

				<div id="call">
					<label>Do you require someone to be on-call after the clinic's normal business hours?</label>
						<input type = "radio"
							name = "call"
							id = "yes"
							value = "y" />
						<label for = "yes">Yes</label>

						<input type = "radio"
							name = "call"
							id = "no"
							value = "n" />
						<label for = "no">No</label>
					</div>

				<br>
				<br>

				<div id="date">
					<label>Which days are best for you to visit the clinic?</label>
					<br>
					<input type = "checkbox"
						id = "mon"
						value = "M" />
					<label for = "mon">Monday</label>

					<input type = "checkbox"
						id = "tues"
						value = "T" />
					<label for = "tues">Tuesday</label>

					<input type = "checkbox"
						id = "wed"
						value = "W" />
					<label for = "wed">Wednesday</label>

					<input type = "checkbox"
						id = "thurs"
						value = "R" />
					<label for = "thurs">Thursday</label>

					<input type = "checkbox"
						id = "fri"
						value = "F" />
					<label for = "fri">Friday</label>

					<input type = "checkbox"
						id = "sat"
						value = "S" />
					<label for = "sat">Saturday</label>

					<input type = "checkbox"
						id = "sun"
						value = "Su" />
					<label for = "sun">Sunday</label>
				</div>


			</fieldset>
			<br>
			<br>
			<fieldset>
				<legend>Healthcare</legend>
				<div id="type">
					<p>
					<label>Do you need the clinic to handle any of the following?</label>
					<br>
					<input type = "checkbox"
						id = "women"
						value = "wo" />
					<label for = "receptionist">Women's Health</label>

					<input type = "checkbox"
						id = "mens"
						value = "me" />
					<label for = "nurses">Men's Health</label>

					<input type = "checkbox"
						id = "family"
						value = "fam" />
					<label for = "family">Family Health</label>

					<input type = "checkbox"
						id = "mental"
						value = "ment" />
					<label for = "mental">Mental Health</label>

					<input type = "checkbox"
						id = "dental"
						value = "dent" />
					<label for = "dental">Dental Health</label>

					<input type = "checkbox"
						id = "vision"
						value = "vis" />
					<label for = "vision">Vision Health</label>
					</p>
				</div>

				<br>
				<br>

				<div id="children">
					<label>Do you have children that need medical assistance?</label>
						<input type = "radio"
							name = "chil"
							id = "yes"
							value = "y" />
						<label for = "yes">Yes</label>

						<input type = "radio"
							name = "child"
							id = "no"
							value = "n" />
						<label for = "no">No</label>
				</div>

				<br>
				<br>

				<div id = "ages">
					<label>If yes, please check all the ages that apply.</label>
						<input type = "checkbox"
							id = "05"
							value = "1" />
						<label for = "05">0-5</label>

						<input type = "checkbox"
							id = "610"
							value = "2" />
						<label for = "610">6-10</label>

						<input type = "checkbox"
							id = "1115"
							value = "3" />
						<label for = "1115">11-15</label>

						<input type = "checkbox"
							id = "1617"
							value = "4" />
						<label for = "1617">16-17</label>
				</div>

				<br>
				<br>

				<div id = "mentalhealth">
					<label>Do you need mental health, counseling, or rehabilitation services in any of the following?</label>
						<br>
						<input type = "checkbox"
							id = "substance"
							value = "subt" />
						<label for = "substance">Substance Abuse</label>

						<input type = "checkbox"
							id = "sex"
							value = "se" />
						<label for = "sex">Sexual Abuse</label>

						<input type = "checkbox"
							id = "anger"
							value = "ang" />
						<label for = "anger">Anger Management</label>

						<input type = "checkbox"
							id = "sexHIV"
							value = "HIV" />
						<label for = "sexHIV">HIV</label>

						<input type = "checkbox"
							id = "sexLGBT"
							value = "LGBT" />
						<label for = "sexLGBT">LGBT</label>
				</div>

				<br>
				<br>

				<div id="abortion">
					<label>Do you need abortion servies?</label>
						<input type = "radio"
							name = "abortion"
							id = "yes"
							value = "y" />
						<label for = "yes">Yes</label>

						<input type = "radio"
							name = "abortion"
							id = "no"
							value = "n" />
						<label for = "no">No</label>
					</div>
			</fieldset>
        <button type="submit" onclick="location.href='Servlet'">Submit</button>
        </form> 
        <p>
	</body>
</html>