<!DOCTYPE html>
<html>
    <head>
        <title>Bug Form</title>
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
    <h1>Bug Form</h1>
    
    <div id="type" class="form-inline">
		<label>What sort of bug do you want to report?</label>
                <br>
			<select id = "insurance">
				<option value = "1">Broken Functionality</option>
				<option valie = "2">Incorrect Information</option>
				<option value = "3">Typos</option>
				<option value = "4">Other</option>
			</select>
	</div>
    
    <br>
    <br>
    
    <div id="desc" class="form-inline">
        <label>Please write a short description of the error you have found.</label>
        <br>
        <textarea id="bugdescription" rows = "3" cols = "80" placeholder="Error Description"></textarea>
        
    </div>

    <br>
    <br>
    
    <div>
        <input type="submit" value="Submit" class="btn btn-primary">
    </div>


    </body>
    
</html>
