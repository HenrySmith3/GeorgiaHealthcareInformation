<%@ page import ="java.sql.*" %><%@ page import ="java.util.*" %><%@ page import ="javax.sql.*" %>
<html>

<body>
<%
  String survno = request.getParameter("SurvNo");  
  session.setAttribute("Survey Number",survno); 
  Class.forName("com.mysql.jdbc.Driver");
  java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/C4G_DB", "root", ""); 
  if (con != null) {
      out.println("connected");
  }
  else
  {
      out.println("connection failed");
  }
  %>
  <br>
  <% 
  Statement st= con.createStatement(); 
  ResultSet rs=st.executeQuery("select * from P1 where survno = '" + request.getParameter("SurvNo")+ "'"); 
  ArrayList<String> arr = new ArrayList<String>();
  while (rs.next()) {
     arr.add(rs.getString("City"));
     arr.add(rs.getString("Interv"));
     arr.add(rs.getString("County"));
  }
  for(int i = 0; i < arr.size(); i++){
     out.println(arr.get(i));
  }
%>
</body>
</html>