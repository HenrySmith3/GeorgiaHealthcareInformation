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

    <title><%=resource.getString("tabTitle")%></title>

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
          <a class="navbar-brand" href="/home.jsp/spanish"><%=resource.getString("spanish")%></a>
          <a class="navbar-brand" href="/home.jsp/english"><%=resource.getString("english")%></a>
        </div>
        <div class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" action="/html_form_action.asp">
            <div class="form-group">
              <input type="text" placeholder="<%=resource.getString("email")%>" name="username" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="<%=resource.getString("password")%>" name="password"  class="form-control">
            </div>
            <input type="hidden" value="adminPage" name="action" id="action">
            <button type="submit"><%=resource.getString("login")%></button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primaryCare marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1><%=resource.getString("mainTitle")%></h1>
        <p><br><%=resource.getString("subTitle")%></p>
        <p><a class="btn btn-primary btn-lg" role="button" href="/index.jsp/english"><%=resource.getString("mainButton")%></a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2><%=resource.getString("subTab1")%></h2>
          <p><%=resource.getString("subBody1")%></p>
          <p><a class="btn btn-default" href="/healthinfo.jsp/english" role="button"><%=resource.getString("learnMore")%></a></p>
        </div>
        <div class="col-md-4">
          <h2><%=resource.getString("subTab2")%></h2>
          <p><%=resource.getString("subBody2")%></p>
          <p><a class="btn btn-default" href="#" role="button"><%=resource.getString("learnMore")%></a></p>
       </div>
        <div class="col-md-4">
          <h2><%=resource.getString("subTab3")%></h2>
          <p><%=resource.getString("subBody3")%></p>
          <p><a class="btn btn-default" href="#" role="button"><%=resource.getString("learnMore")%></a></p>
        </div>
      </div>

      <hr>

      <footer>
        <p><%=resource.getString("footer")%></p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
  </body>
</html>
