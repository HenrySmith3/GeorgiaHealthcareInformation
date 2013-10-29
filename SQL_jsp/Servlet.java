import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

/**
 * The main servlet which controls the back end.
 */
public class Servlet extends javax.servlet.http.HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Currently unused.
     */
	private String city;
	private String county;
	private String pubTr;
	private String appt;
	private String call;
	
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Post method received.");
        city = request.getParameter("city");
        county = request.getParameter("county");
        pubTr = request.getParameter("pubTr");
        appt = request.getParameter("appt") == "walk"? "1" : "2";
        call = request.getParameter("call") == "y"? "1" : "0";
        PrintWriter out = response.getWriter();
        out.println("The city:"+city);
        doGet(request, response);
    }

    /**
     * The main request processing.
     */
    
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	Hospital criteria = populateCriteriaFromRequest(request);
        Connection conn = initializeConnection();
        JSONArray hospitals = new JSONArray();
        PrintWriter writer = response.getWriter();
        try {
        	Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(getQuery(criteria));
            while (resultset.next()) {
                Hospital hospital = Hospital.getHospitalFromResultSet(resultset);
                hospitals.add(hospital.toJson());
            }
            writer.print("</body>");
            writer.print("</html>");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        request.setAttribute("hospitals", hospitals);
        request.getRequestDispatcher("/response.jsp").forward(request, response);
    }
    /**
     * Gets the query that will be used to query the database.
     * @param criteria All non-null values in this hospital will be used as the criteria.
     * @return A query string to be executed.
     */
    private String getQuery(Hospital criteria) {
        StringBuilder builder = new StringBuilder();
        //get the basic statement, which is just a select all.
        builder.append(getBasicSelectStatement());
        //add in the where clauses so that only the hospitals with fields matching the criteria are returned.
        builder.append(getWhereClauses(criteria));
        return builder.toString();
    }

    /**
     * Returns the where clauses for the hospital.
     * For each non-null value in hospital, this method adds a SQL where clause to restrict
     * the results returned to only those that match the value in hospital.
     * @param criteria The hospital criteria. All values that we aren't looking to match should be null.
     * @return A string which is appended to the basic sql statement.
     */
    private String getWhereClauses(Hospital criteria) {
        StringBuilder stringBuilder = new StringBuilder();
        if (criteria.abortionGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.AbortionGUIDE == " + criteria.abortionGuide);
        }
        return stringBuilder.toString();
    }

    /**
     * Just initializes the database connection. In the future, the username/password should not just be "root"
     * @return A connection to run SQL statements on.
     */
    private Connection initializeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //This needs to be changed, we need database accounts.
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/C4G_DB", "root", "");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The basic SQL statement which just selects all. Append the where clauses to this.
     * @return A string which should be passed to the SQL server with the where clauses attached.
     */
    private String getBasicSelectStatement() {
        return "SELECT * " +
                "FROM (p1 " +
                "JOIN p2 ON p1.ID = p2.ID " +
                "JOIN p3_4_5 ON p1.ID = p3_4_5.ID)" + 
                "WHERE p1.City = '" + city+ "'" + 
                "AND P1.County = '" + county+ "'" + 
                "AND P3_4_5.PubTr = '" + pubTr+ "'" +
                "AND P3_4_5.AppWalk = '" + appt+ "'" + 
                "AND P2.OnCall = '" + call+ "'";
    }
  
    /**
     * Takes a request and then makes a hospital based on the input in the request.
     * Every input element from the form should be represented here, and should be mapped to a field in the hospital.
     * @param request The incoming request which contains the form data.
     * @return A hospital which represents the search criteria.
     */
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
