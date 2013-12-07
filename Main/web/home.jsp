<%@ page import="java.util.ResourceBundle" %><%
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
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>Hispanic Healthcare Web Directory</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">

  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/home.jsp/spanish">Spanish</a>
          <a class="navbar-brand" href="/home.jsp/english">English</a>
        </div>
        <div class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" action="/html_form_action.asp">
            <div class="form-group">
              <input type="text" placeholder="Email" name="username" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" name="password"  class="form-control">
            </div>
            <input type="hidden" value="adminPage" name="action" id="action">
            <button type="submit">Login</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Hispanic Healthcare Web Directory</h1>
        <p><br>Easily locate the healthcare providers that fit your specific needs.</p>
        <p><a class="btn btn-primary btn-lg" role="button" href="/index.jsp/english">Find a Provider &raquo;</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2>General Health</h2>
          <p>Accurate and trustworthy information about healthcare can be difficult to find, even with the internet. We
            have compiled some information and links concerning maintaining good health and what to ask your healthcare providers.</p>
          <p><a class="btn btn-default" href="/healthinfo.jsp/english" role="button">Learn more &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Clinicians</h2>
          <p>If you are a staff member of a care-providing institution, information about your clinic can be added
          to this site. If information about your clinic already exists but isn't up-to-date, you can also find
          information on to how to update it here.</p>
          <p><a class="btn btn-default" href="#" role="button">Learn more &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h2>Site Navigation</h2>
          <p>Here you can find tools that will explain how to navigate this website. Provided is a video that shows you what this site offers and where you can find it. There is also a text guide with images for those with lower bandwidth.</p>
          <p><a class="btn btn-default" href="#" role="button">Learn more &raquo;</a></p>
        </div>
      </div>

      <hr>

      <footer>
        <p>Provided by members of the Georgia Institute of Technology, Emory School of Public Health, and the Hispanic 
          Healthcare Coalition of Georgia</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
  </body>
</html>
