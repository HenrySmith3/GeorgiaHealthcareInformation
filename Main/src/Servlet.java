import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

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

        Hospital criteria = populateCriteriaFromRequest(request);
        PrintWriter writer = response.getWriter();
        Connection con = initializeConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getQuery(criteria));
            while (resultset.next()) {
                Hospital hospital = Hospital.getHospitalFromResultSet(resultset);
                writer.append(hospital.toString() + "\n");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getQuery(Hospital criteria) {
        StringBuilder builder = new StringBuilder();
        builder.append(getBasicSelectStatement());
        builder.append(getWhereClauses(criteria));
        return builder.toString();
    }

    private String getWhereClauses(Hospital criteria) {
        StringBuilder stringBuilder = new StringBuilder();
        if (criteria.abortionGuide != null) {
            stringBuilder.append( "WHERE p3_4_5.AbortionGUIDE == " + criteria.abortionGuide);
        }
        return stringBuilder.toString();
    }

    private Connection initializeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //This needs to be changed, we need database accounts.
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/georgiahealthcareinformation", "root", "");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getBasicSelectStatement() {
        return "SELECT * " +
                "FROM (p1 " +
                "JOIN p2 ON p1.ID = p2.ID " +
                "JOIN p3_4_5 ON p1.ID = p3_4_5.ID)";
    }

    private Hospital populateCriteriaFromRequest(HttpServletRequest request) {
        Hospital criteria = new Hospital();
        Enumeration parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String parameter = (String)parameterNames.nextElement();
            if (parameter.equalsIgnoreCase("nameOfParameterHere")) {
                //set value in criteria here.
            }
        }
        return criteria;
    }

}
