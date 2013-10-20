You need to first put JDBC.jar under WEB/WEB_INF/lib
In your phpMyAdmin, create a new database named as C4G_DB
And put the C4G_DB.sql in SQL to create tables. 

I had slightly changed the index.jsp, in order to turn to a new page on clicking the submit button. 
A new page will show up asking to input the survey number. This is designed in "input.jsp".
Input "1" as test. A result page including the city name, the interviewed person name and the county will appear. This part of codes are in dbquery.jsp. You may need to change line 9: 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/C4G_DB", "root", ""); The first part is the local url for mysql and the name of the database, the other two are the username and password when you login the database.
