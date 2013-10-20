<%@ page language="java" import="java.util.*" pageEncoding="US-ASCII"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
</head> 
<body> 
<form name="form1" method="post" action="dbquery.jsp"> 
<h1>Please fill out the form</h1>
<p>Survey Number  
     <input type="text" name="SurvNo"> 
</p> 
<p> 
     <input type="Submit" value="Submit"> 
     <input type="Reset" value="Reset"> 
</p> 
</form> 
</body> 
</html> 