import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 10/6/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("test");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("test2");

        PrintWriter writer = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/georgiahealthcareinformation", "root", "");
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getBasicSelectStatement());
            while (resultset.next()) {
                Hospital hospital = getHospitalFromResultSet(resultset);
                writer.append(resultset.getString("NameFac") + "\n");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (request.getParameter("option") != null) {
            writer.append("<HTML><BODY>A checkbox was pressed</BODY></HTML>");
        } else {
            writer.append("<HTML><BODY>A checkbox was not pressed</BODY></HTML>");
        }
    }

    private String getBasicSelectStatement() {
        return "SELECT * " +
                "FROM (p1 " +
                "JOIN p2 ON p1.ID = p2.ID " +
                "JOIN p3_4_5 ON p1.ID = p3_4_5.ID)";
    }

    private Hospital getHospitalFromResultSet(ResultSet resultSet) {
        Hospital hospital = new Hospital();
        return hospital;
    }
}
