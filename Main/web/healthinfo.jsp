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

    <title>Off Canvas Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="offcanvas.css" rel="stylesheet">

    <!--container style-->
    <style>
        #pagebody { margin-top: 3.5%;}
    </style>


    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
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
            <a class="navbar-brand" href="/home.jsp/<%out.print(language);%>">
                Home
            </a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/index.jsp/<%out.print(language);%>">
                        <%=resource.getString("search")%>
                    </a>
                </li>
                <li>
                    <a href="/clinic.jsp/<%out.print(language);%>">
                        <%=resource.getString("addition")%>
                    </a>
                </li>
                <li class="active">
                    <a href="/edit.jsp/<%out.print(language);%>">
                        <%=resource.getString("editing")%>
                    </a>
                </li>
                <li>
                    <a href="/bug.jsp/<%out.print(language);%>">
                        <%=resource.getString("error")%>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right"></ul>
        </nav>
    </div>
</header>

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>Health Information</h1>
        <p>There are certain services that you may want to consider when looking for a healthcare provider. The information here as well as the links provided give information that you may want to ask your health provider about if they are right for you.</p>
    </div>
</div>
<!--end jumbotron header-->

<div class="container" id="pagebody">
    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                        Screening for Men
                    </a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse">
                <div class="panel-body">
                    One should receive a blood pressure test every two years if at a normal blood pressure and more often if in an abnormal range. Cholesterol tests should be received regularly starting at age 35 and before that if at an increased risk for heart disease. Colorectal cancer screening should be received starting at age 50 through 75. Screening for diabetes should begin based on family history, blood pressure, and other identifiable risk factors. Sexual health test (HIV, syphilis) should be conducted if you are at an increased risk. Check with your doctor for other tests if a smoker. See a doctor if you are unsure about any of these screening recommendations.
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                        Screening for Women
                    </a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse">
                <div class="panel-body">
                    Unless otherwise advised or at risk, regular mammographies and colorectal screening exams should begin starting at age 50. Screening for diabetes should begin based on family history, blood pressure, and other identifiable risk factors. One should receive a blood pressure test every two years if at a normal blood pressure and more often if in an abnormal range. Cholesterol tests should be received regularly if at an increased risk for heart disease. HPV tests and Pap tests should be obtained for women between 30-65 every 3-5 years. Sexual health test recommendations vary, but generally should be conducted if sexually active, at an increased risk, and/or pregnant. Unless otherwise advised, 65 is the recommended age for osteoperosis screening. See a doctor if you are unsure about any of these screening recommendations.
                    <br>
                    <a href="http://womenshealth.gov/nwhw/health-resources/screening-tool/">Learn More</a>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                        Prenatal (Newborn Baby) Care
                    </a>
                </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse">
                <div class="panel-body">
                    Obtaining early health care is an important part of a healthy pregnancy and ensuring the health of an unborn baby. Before a woman even begins planning to become pregnant, she must give herself at least three months as a recommendation to prepare herself for having. This part of prenatal health care is called preconception health, and it is vital. During the preconception health period, a woman should take a constant amount of folic acid (about 0.4 to 0.8 mg), eat healthy, and exercise. It is important to see a doctor as soon as you may suspect that you may be pregnant. The earlier and more frequently you see your doctor the higher the chance to correct any complications that may occur. For the 4th to 28th weeks, one should see their doctor about once a month. For the 28th to 36th weeks, one should see their doctor twice a month. For the final weeks 36th to birth, a woman should see her doctor weekly to watch the progress of her unborn baby.
                </div>
            </div>
        </div>
    </div>

</div><!--/.container-->



<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="offcanvas.js"></script>
</body>
</html>
