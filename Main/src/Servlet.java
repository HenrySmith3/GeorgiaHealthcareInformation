import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

/**
 * The main servlet which controls the back end.
 */
public class Servlet extends javax.servlet.http.HttpServlet {

    /**
     * Currently unused.
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Post method received.");
    }

    /**
     * The main request processing.
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (request.getParameter("action").equalsIgnoreCase("search")) {
            processSearch(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("bug")) {
            submitBug(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("addHospital")) {
            addHospital(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("editHospital")) {
            editHospital(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("lookupIds")) {
            lookupIds(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("viewBugs")) {
            viewBugs(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("adminPage")) {
            adminPage(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("deleteBug")) {
            deleteBug(request, response);
        }

}

    private void adminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("")|| password.equals("")) {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
        Connection con = initializeConnection();
        Statement statement = null;
        try {
            statement = con.createStatement();
            String query = "SELECT * FROM USERS WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            }  else {
                request.getRequestDispatcher("/home.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void deleteBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteID = request.getParameter("Delete");
        Connection con = initializeConnection();
        Statement statement = null;
        try {
            statement = con.createStatement();
            String query = "DELETE FROM bug_report WHERE ID=" + deleteID;
            statement.executeUpdate(query);
            //TODO we should think about where all of these go when they're done. We should have confirmation messages for things like this.
//            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void processSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital criteria = populateCriteriaFromRequest(request);
        Connection con = initializeConnection();
        JSONArray hospitalsInCounty = new JSONArray();
        JSONArray allHospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getQuery(criteria, true));
            while (resultset.next()) {
                Hospital hospital = Hospital.getHospitalFromResultSet(resultset);
                hospitalsInCounty.add(hospital.toJson());
                //writer.append(hospital.toString() + "\n");
            }
            statement = con.createStatement();
            resultset = statement.executeQuery(getQuery(criteria, false));
            while (resultset.next()) {
                Hospital hospital = Hospital.getHospitalFromResultSet(resultset);
                allHospitals.add(hospital.toJson());
                //writer.append(hospital.toString() + "\n");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("hospitalsInCounty", hospitalsInCounty);
        request.setAttribute("allHospitals", allHospitals);
        if (request.getRequestURL().toString().contains("english")) {
            request.getRequestDispatcher("/response.jsp/english").forward(request, response);
        } else {
            request.getRequestDispatcher("/response.jsp/spanish").forward(request, response);
        }
    }

    private void lookupIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = initializeConnection();
        JSONArray hospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getIDLookupQuery());
            while (resultset.next()) {
                JSONObject object = new JSONObject();
                object.accumulate("name", resultset.getString("NameFac"));
                object.accumulate("ID", resultset.getString("ID"));
                hospitals.add(object);
                //writer.append(hospital.toString() + "\n");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("hospitals", hospitals);
        request.getRequestDispatcher("/idLookup.jsp").forward(request, response);
    }

    private void viewBugs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = initializeConnection();
        JSONArray hospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getBugLookup());
            while (resultset.next()) {
                JSONObject object = new JSONObject();
                object.accumulate("ID", resultset.getString("ID"));
                object.accumulate("bug", resultset.getString("bug"));
                object.accumulate("bugDesc", resultset.getString("bugDesc"));
                hospitals.add(object);
                //writer.append(hospital.toString() + "\n");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("bugs", hospitals);
        request.getRequestDispatcher("/viewBugs.jsp").forward(request, response);
    }

    private String getIDLookupQuery() {
        return "SELECT p1.NameFac, p1.ID from p1";
    }

    private String getBugLookup() {
        return "SELECT bug_report.ID, bug_report.bug, bug_report.bugDesc from bug_report";
    }

    private void submitBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection con = initializeConnection();
        JSONArray hospitals = new JSONArray();
        Enumeration parameterNames = request.getParameterNames();
        String bug1 = null, bug2 = null, ss;

        while (parameterNames.hasMoreElements()) 
        {
            String parameter = (String)parameterNames.nextElement();

            String temp = request.getParameter("bug1");
            if(temp.equals("1")) {
                bug1 = "Broken Functionality";
            } else if (temp.equals("2")) {
                bug1 = "Incorrect Information";
            } else if (temp.equals("3")) {
               	bug1 = "Typos";
            } else if (temp.equals("4")) {
               	bug1 = "Other";
            } 
            temp = request.getParameter("bug2");
            bug2 = temp;
        }
    	StringBuilder stringBuilder = new StringBuilder();
    	if(bug1 == null && bug2 == null)
    		ss = "";
    	else {
    		stringBuilder.append("INSERT INTO BUG_REPORT (bug, bugDesc) VALUES ('" + bug1 + "', '" + bug2 + "');");
            ss = stringBuilder.toString();
    	}
        try {
        	Statement statement = con.createStatement();
            statement.executeUpdate(ss);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void addHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital criteria = populateCriteriaFromRequest_addHospital(request);
        Connection con = initializeConnection();
        String max_survno1 = "", max_survno2 = "", max_survno3 = "";  //find the max number of survno, when insert a new hospital, make the value be larger than the values currently exist
        int max_survno;
        try {
            Statement statement = con.createStatement();
            ResultSet result_max_survno = statement.executeQuery("SELECT MAX(SurvNo) FROM P1;");
            while(result_max_survno.next()){
                max_survno1 = result_max_survno.getString("MAX(SurvNo)"); 
            }
            result_max_survno = statement.executeQuery("SELECT MAX(SurvNo) FROM P2;");
            while(result_max_survno.next()){
                max_survno2 = result_max_survno.getString("MAX(SurvNo)"); 
            }
            result_max_survno = statement.executeQuery("SELECT MAX(SurvNo) FROM P3_4_5;");
            while(result_max_survno.next()){
                max_survno3 = result_max_survno.getString("MAX(SurvNo)"); 
            }
            max_survno = Math.max(Integer.parseInt(max_survno1),  Integer.parseInt(max_survno2));
            max_survno = Math.max(max_survno, Integer.parseInt(max_survno3));
            String survno = String.valueOf(max_survno + 1);
            criteria.name = "none";
            String ss = "INSERT INTO P1 (SurvNo, AddFacL1, County, Website, Phone, City) VALUES ('" + survno + "', '" + criteria.addressLine1 + "', '" + criteria.county + "','" + criteria.website + "', '" + criteria.phone + "', '" + criteria.city + "'); ";
            statement.executeUpdate(ss);
            String oncall = (criteria.onCall == true)? "1" : "0";
            ss = "INSERT INTO P2 (SurvNo, OnCall) VALUES ('" + survno + "', '" + oncall + "'); ";
            statement.executeUpdate(ss);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO P3_4_5 (SurvNo, AppWalk, MedicareGuide, MedicaidGuide, " +
                    "PeachcareGuide, SPANAdmGUIDE,SPANFoGuide, " +
                    "SpcWH, SpcMH, SpcFCH, SpcMHC," +
                    "SpcDH, SpcVH, NinosGUIDE," +
                    "subAbGuide, sexAbGuide, angManGuide, " +
                    "HIVConsGUIDE, LGBTGUIDE, SPANIntON)");
            String medicare = "0", medicaid = "0", peachcare = "0", childguide = "0";
            String subab = "0", sexab = "0", angman = "0", hivcons = "0", lgbt = "0";
            String spcwh = "0", spcmh = "0", spcfch = "0", spcmhc = "0", spcdh = "0", spcvh = "0";
            String interpreter = "3";
            if(criteria.medicare != null && criteria.medicare == true)
            	medicare = "1";
            if(criteria.medicaid != null && criteria.medicaid == true)
            	medicaid = "1";
            if(criteria.peachCare != null && criteria.peachCare == true)
            	peachcare = "1";
            if(criteria.childGuide != null && criteria.childGuide == true)
            	childguide = "1";
            if(criteria.subAbGuide != null && criteria.subAbGuide == true)
            	subab = "1";
            if(criteria.sexAbGuide != null && criteria.sexAbGuide == true)
            	sexab = "1";
            if(criteria.angManGuide != null && criteria.angManGuide == true)
            	angman = "1";
            if(criteria.hivConsGuide != null && criteria.hivConsGuide == true)
            	hivcons = "1";
            if(criteria.lgbtGuide != null && criteria.lgbtGuide == true)
            	lgbt = "1";
            if(criteria.spcWH != null && criteria.spcWH == true)
            	spcwh = "1";
            if(criteria.spcMH != null && criteria.spcMH == true)
            	spcmh = "1";
            if(criteria.spcFCH != null && criteria.spcFCH == true)
            	spcfch = "1";
            if(criteria.spcMHC != null && criteria.spcMHC == true)
            	spcmhc = "1";
            if(criteria.spcDH != null && criteria.spcDH == true)
            	spcdh = "1";
            if(criteria.spcVH != null && criteria.spcVH == true)
            	spcvh = "1";
            if(!new Integer(3).equals(criteria.spanInterpreter))
                interpreter = "1";
           stringBuilder.append("VALUES ('" + survno + "', '" +  + criteria.walkIn + "', '" + medicare + "', '" + medicaid + "', '" + 
                    peachcare + "', '" + criteria.spanAdmin + "', '" + criteria.spanFo + "', '" + 
                    spcwh + "', '" + spcmh + "', '" + spcfch + "', '" + spcmhc+ "', '" + 
                    spcdh + "', '" + spcvh + "', '" + childguide + "', '" + subab + "', '" + sexab + "', '" + angman + "', '" + 
                    hivcons + "', '" + lgbt + "', '" + interpreter + "'); ");
            ss = stringBuilder.toString();
            statement.executeUpdate(ss);
          con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void editHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Hospital criteria = populateCriteriaFromRequest_addHospital(request);
        Connection con = initializeConnection();
        String ss = "";
        try {
            Statement statement = con.createStatement();
            criteria.name = "none";
            if(criteria.county != null && !criteria.county.equals("")){
                ss = "UPDATE P1 SET County = '" + criteria.county + "' WHERE ID = " + criteria.id + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.city != null && !criteria.city.equals("")){
                ss = "UPDATE P1 SET City = '" + criteria.city + "' WHERE ID = " + criteria.id + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.website != null && !criteria.website.equals("")){
                ss = "UPDATE P1 SET Website = '" + criteria.website + "' WHERE ID = " + criteria.id + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.phone != null && !criteria.phone.equals("")){
                ss = "UPDATE P1 SET Phone = '" + criteria.city + "' WHERE ID = " + criteria.id + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.addressLine1 != null && !criteria.addressLine1.equals("")){
                ss = "UPDATE P1 SET AddFacL1 = '" + criteria.addressLine1 + "' WHERE ID = " + criteria.id + "; ";
                statement.executeUpdate(ss);
            }
            String oncall = "0";
            if(criteria.onCall != null && criteria.onCall == true)
            	oncall = "1";
            else
            	oncall = "0";
            ss = "UPDATE P2 SET OnCall = '" + oncall + "' WHERE ID = " + criteria.id + "; ";
            statement.executeUpdate(ss);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append( "UPDATE P3_4_5 SET ");
            
            //Clinic services
            if(criteria.walkIn != null){
                //on 2 it's appointments only, on 1 it's walk in only. 3 is always right.
                stringBuilder.append( "AppWalk = " + criteria.walkIn + ", ");
            }
            if(criteria.medicare != null){
            	String medi = (criteria.medicare == true)? "1" : "0";
                stringBuilder.append( "MedicareGUIDE = '" + medi + "', ");
            }
            if(criteria.medicaid != null){
            	String medicaid = (criteria.medicaid == true)? "1" : "0";
                stringBuilder.append( "MedicaidGUIDE = '" + medicaid + "', ");
            }
            if(criteria.peachCare != null){
            	String peachcare = (criteria.peachCare == true)? "1" : "0";
                stringBuilder.append( "PeachcareGUIDE = " + peachcare + ", ");
            }
            if (criteria.spanAdmin != null) {
                stringBuilder.append( "SPANAdmGUIDE = " + criteria.spanAdmin + ", ");
            }            
            if (criteria.spanFo != null) {
                stringBuilder.append( "SPANFoGUIDE = '" + criteria.spanFo + "', ");
            }
            
            //Healthcare
            if(criteria.spcWH != null){
            	String spcwh = (criteria.spcWH == true)? "1" : "0";
                stringBuilder.append( "SpcWH = '" + spcwh + "', ");
            }
            if(criteria.spcMH != null){
            	String spcmh = (criteria.spcMH == true)? "1" : "0";
                stringBuilder.append( "SpcMH = '" + spcmh + "', ");
            }
            if(criteria.spcFCH != null){
            	String spcfch = (criteria.spcFCH == true)? "1" : "0";
                stringBuilder.append( "SpcFCH = '" + spcfch + "', ");
            }
            if(criteria.spcMHC != null){
            	String spcmhc = (criteria.spcMHC == true)? "1" : "0";
                stringBuilder.append( "SpcMHC = '" + spcmhc + "', ");
            }
            if(criteria.spcDH != null){
            	String spcdh = (criteria.spcDH == true)? "1" : "0";
                stringBuilder.append( "SpcDH = '" + spcdh + "', ");
            }
            if(criteria.spcVH != null){
            	String spcvh = (criteria.spcVH == true)? "1" : "0";
                stringBuilder.append( "SpcVH = '" + spcvh + "', ");
            }
            if(criteria.childGuide != null){
            	String child = (criteria.childGuide == true)? "1" : "0";
                stringBuilder.append( "NinosGUIDE = '" + child + "', ");
            }
            if(criteria.subAbGuide != null){
            	String subab = (criteria.subAbGuide == true)? "1" : "0";
                stringBuilder.append( "SubAbGUIDE = '" + subab + "', ");
            }
            if(criteria.sexAbGuide != null)
            {
            	String sexab = (criteria.sexAbGuide == true)? "1" : "0";
            	stringBuilder.append( "SexAbGUIDE = '" + sexab + "', ");
            }
            if(criteria.angManGuide != null){
            	String angman = (criteria.angManGuide == true)? "1" : "0";
                stringBuilder.append( "AngManGUIDE = '" + angman + "', ");
            }
            if(criteria.hivConsGuide != null){
            	String hivcons = (criteria.hivConsGuide == true)? "1" : "0";
            	stringBuilder.append( "HIVConsGUIDE = '" + hivcons + "', ");
            }
            if(criteria.lgbtGuide != null){
            	String lgbt = (criteria.lgbtGuide == true)? "1" : "0";
                stringBuilder.append( "LGBTGUIDE = '" + lgbt + "', ");
            }
            if(criteria.spanInterpreter != null) {
                String interpreter = (criteria.spanInterpreter.equals(3))? "3" : "1";
                stringBuilder.append( "SpanIntON = '" + interpreter + "', ");
            }
            ss = stringBuilder.toString();
            ss = ss.substring(0, ss.length() - 2) + "WHERE ID = " + criteria.id + "; ";
            System.out.println(ss);
            statement.executeUpdate(ss);
          con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    /**
     * Gets the query that will be used to query the database.
     * @param criteria All non-null values in this hospital will be used as the criteria.
     * @param inCounty If true, returns results in the users county, if false, returns all results.
     * @return A query string to be executed.
     */
    private String getQuery(Hospital criteria, boolean inCounty) {
        StringBuilder builder = new StringBuilder();
        //get the basic statement, which is just a select all.
        builder.append(getBasicSelectStatement());
        //add in the where clauses so that only the hospitals with fields matching the criteria are returned.
        builder.append(getWhereClauses(criteria, inCounty));
        
        return builder.toString();
    }

    /**
     * Returns the where clauses for the hospital.
     * For each non-null value in hospital, this method adds a SQL where clause to restrict
     * the results returned to only those that match the value in hospital.
     * @param criteria The hospital criteria. All values that we aren't looking to match should be null.
     * @param inCounty If true, returns results in the users county, if false, returns all results.
     * @return A string which is appended to the basic sql statement.
     */
    private String getWhereClauses(Hospital criteria, boolean inCounty) {
        StringBuilder stringBuilder = new StringBuilder();
        
        //Personal Information   
        if(inCounty && criteria.county != null && !criteria.county.equals("")){
            stringBuilder.append( "P1.County = '" + criteria.county + "' AND ");
        }

        if(criteria.publicTransportation != null){
            //TODO how is this formatted in the database? Are we still even doing this?
//            stringBuilder.append( "P3_4_5.PubTr = " + criteria.publicTransportation + " AND ");
        }

        //Clinic services
        if(criteria.walkIn != null){
            //on 2 it's appointments only, on 1 it's walk in only. 3 is always right.
            stringBuilder.append( "(P3_4_5.AppWalk = 3 OR ");
            stringBuilder.append( "P3_4_5.AppWalk = " + criteria.walkIn + ") AND ");
        }
        if(Boolean.TRUE.equals(criteria.medicare)) {
            stringBuilder.append( "P3_4_5.MedicareGUIDE = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.medicaid)) {
            stringBuilder.append( "P3_4_5.MedicaidGUIDE = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.peachCare)) {
            stringBuilder.append( "P3_4_5.PeachcareGUIDE = " + 1 + " AND ");
        }

        if(Hospital.TRUE.equals(criteria.spanInterpreter)) {
            stringBuilder.append( "P3_4_5.SpanIntON != 3");
        }
//These are just in the results page now.
//        if (criteria.spanAdmin != null && criteria.spanAdmin == Hospital.TRUE) {
//            stringBuilder.append( "P3_4_5.SPANAdmGUIDE = 1 AND ");
//        }
//        if (criteria.spanNurse != null && criteria.spanNurse == Hospital.TRUE) {
//            stringBuilder.append( "P3_4_5.SPANNurGUIDE = 1 AND ");
//        }
//        if (criteria.spanDoc != null && criteria.spanDoc == Hospital.TRUE) {
//            stringBuilder.append( "P3_4_5.SPANDocGUIDE = 1 AND ");
//        }
//
//        if (criteria.spanFo != null && criteria.spanFo == Hospital.TRUE) {
//            stringBuilder.append( "P3_4_5.SPANFoGUIDE = 1 AND ");
//        }
//
//        if (criteria.spanPhone != null && criteria.spanPhone == Hospital.TRUE) {
//            stringBuilder.append( "P3_4_5.SPANIntPhGUIDE = 1 AND ");
//        }
//
//        if(criteria.onCall != null && criteria.onCall == true){
//         	stringBuilder.append( "P2.OnCall = " + 1 + " AND ");
//        }
        
        //Healthcare
        if(Boolean.TRUE.equals(criteria.spcWH)) {
            stringBuilder.append( "P3_4_5.SpcWH = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcMH)) {
            stringBuilder.append( "P3_4_5.SpcMH = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcFCH)) {
            stringBuilder.append( "P3_4_5.SpcFCH = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcMHC)) {
            stringBuilder.append( "P3_4_5.SpcMHC = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcDH)) {
            stringBuilder.append( "P3_4_5.SpcDH = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcVH)) {
            stringBuilder.append( "P3_4_5.SpcVH = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.childGuide)){
                stringBuilder.append( "P3_4_5.NinosGUIDE = " + 1 + " AND ");
        }
        
        if(Boolean.TRUE.equals(criteria.childGuide) && criteria.ageStart != -1){
        	stringBuilder.append( "P3_4_5.AgeStart <= " + criteria.ageStart + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.childGuide) && criteria.ageEnd != -1){
        	stringBuilder.append( "P3_4_5.AgeEnd >= " + criteria.ageEnd + " AND ");
        }
        
        if(Boolean.TRUE.equals(criteria.subAbGuide)) {
            stringBuilder.append( "P3_4_5.SubAbGuide = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.sexAbGuide)) {
            stringBuilder.append( "P3_4_5.SexAbGuide = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.angManGuide)) {
            stringBuilder.append( "P3_4_5.AngManGuide = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.hivConsGuide)) {
            stringBuilder.append( "P3_4_5.HIVConsGUIDE = " + 1 + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.lgbtGuide)) {
            stringBuilder.append( "P3_4_5.LGBTGUIDE = " + 1 + " AND ");
        }
        
        String ss = stringBuilder.toString();
        if (ss.length() <= 5) {
            return "";
        }
        ss = "WHERE " + ss.substring(0, ss.length() - 5) + ";"; //the -5 is to pop off the last AND.
        return ss;
    }

    /**
     * Just initializes the database connection. In the future, the username/password should not just be "root"
     * @return A connection to run SQL statements on.
     */
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

    /**
     * The basic SQL statement which just selects all. Append the where clauses to this.
     * @return A string which should be passed to the SQL server with the where clauses attached.
     */
    private String getBasicSelectStatement() {
        return "SELECT * " +
                "FROM (p1 " +
                "JOIN p2 ON p1.ID = p2.ID " +
                "JOIN p3_4_5 ON p1.ID = p3_4_5.ID)";
    }

    /**
     * Takes a request and then makes a hospital based on the input in the request.
     * Every input element from the form should be represented here, and should be mapped to a field in the hospital.
     * @param request The incoming request which contains the form data.
     * @return A hospital which represents the search criteria.
     */
    private Hospital populateCriteriaFromRequest(HttpServletRequest request)
    {
        Hospital criteria = new Hospital();
        Enumeration parameterNames = request.getParameterNames();
        String temp;

        while (parameterNames.hasMoreElements()) 
        {
            String parameter = (String)parameterNames.nextElement();

            temp = request.getParameter("county");
            if(temp.equals("1")) {
                criteria.county = "Clayton";
            } else if (temp.equals("2")) {
                criteria.county = "Cobb";
            } else if (temp.equals("3")) {
               	criteria.county = "DeKalb";
            } else if (temp.equals("4")) {
               	criteria.county = "Fulton";
            } else if (temp.equals("5")) {
                criteria.county = "Gwinnett";
            }
            if (parameter.equalsIgnoreCase("transportationForm")) {
                //TODO What are we doing if they say drive, bus, or bike? Nothing?
                temp = request.getParameter(parameter);
                if(temp.equals("1")) {
                    //Drive
                } else if (temp.equals("2")) {
                    //Marta
                    criteria.publicTransportation = Hospital.TRUE;
                } else if (temp.equals("3")) {
                    //Bus
                } else if (temp.equals("4")) {
                    //Bike
                }
            }
            
            
//            if (parameter.equalsIgnoreCase("parking"))
//            {
//                temp = request.getParameter(parameter);
//                criteria.parking = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
//            }

 /*           if (parameter.equalsIgnoreCase("walkIn"))
            {
                temp = request.getParameter(parameter);
                //if appt was already processed
                if (criteria.walkIn != null && criteria.walkIn == 2) {
                    if (temp.equalsIgnoreCase("y")) {
                        criteria.walkIn = 3;
                    }
                } else {
                    criteria.walkIn = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
                }
            }
 
           if (parameter.equalsIgnoreCase("walkInComment"))
            {
                temp = request.getParameter(parameter);
                criteria.walkInComment = temp;
            }
 */
            if (parameter.equalsIgnoreCase("appt"))
            {
                temp = request.getParameter(parameter);
                //if walkin was already processed
                if (temp.equalsIgnoreCase("appmt")) {
                    criteria.walkIn = 2;
                } 
                else if (temp.equalsIgnoreCase("walk")){
                	criteria.walkIn = 1;
                }
            }
            if (parameter.equalsIgnoreCase("insuranceForm"))
            {
                temp = request.getParameter(parameter);
                if(temp.equals("1")) {
                    criteria.medicare = true;
                } else if (temp.equals("2")) {
                    criteria.medicaid = true;
                } else if (temp.equals("3")) {
                    criteria.peachCare = true;
                }
            }
            if (parameter.equalsIgnoreCase("receptionist"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("recept"))
                    criteria.spanAdmin = Hospital.TRUE;
            }
            if (parameter.equalsIgnoreCase("nurses"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("nur"))
                    criteria.spanNurse = Hospital.TRUE;
            }
            if (parameter.equalsIgnoreCase("doctor"))
            {
                temp = request.getParameter(parameter);
                criteria.spanDoc = temp.equalsIgnoreCase("doc") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("forms"))
            {
                temp = request.getParameter(parameter);
                criteria.spanFo = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("spanInterpreter"))
            {
                temp = request.getParameter(parameter);
                criteria.spanInterpreter = temp.equalsIgnoreCase("interpY") ? Hospital.TRUE : Hospital.FALSE;
            }

            if (parameter.equalsIgnoreCase("phone"))
            {
                temp = request.getParameter(parameter);
                criteria.spanPhone = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            temp = request.getParameter("call");
            if(temp != null && temp.equals("yes"))
                criteria.onCall = true;
            else criteria.onCall = false;
            //Stuff about days would go here, we're ignoring that for now.
            if (parameter.equalsIgnoreCase("family"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("fam"))
                    criteria.spcFCH = true;
                else
                    criteria.spcFCH = false;
            }
            if (parameter.equalsIgnoreCase("womens"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("wo"))
                    criteria.spcWH = true;
                else
                    criteria.spcWH = false;
            }
            if (parameter.equalsIgnoreCase("mens"))
            {
                //TODO Make sure that spcMH is actually mens health and not mental health. I might have these backwards.
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("me"))
                    criteria.spcMH = true;
                else
                    criteria.spcMH = false;
            }
            if (parameter.equalsIgnoreCase("mental"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("ment"))
                    criteria.spcMHC = true;
                else
                    criteria.spcMHC = false;
            }
            if (parameter.equalsIgnoreCase("dental"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("dent"))
                    criteria.spcDH = true;
                else
                    criteria.spcDH = false;
            }
            if (parameter.equalsIgnoreCase("vision"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("vis"))
                    criteria.spcVH = true;
                else
                    criteria.spcVH = false;
            }
            //find the age range of the children
            ArrayList<Integer> start = new ArrayList<Integer>();
            ArrayList<Integer> end = new ArrayList<Integer>();
            if (parameter.equalsIgnoreCase("age1"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("1")){
                    start.add(0);
                    end.add(5);
            	}
            }
            if (parameter.equalsIgnoreCase("age2"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("2")){
                    start.add(6);
                    end.add(10);
            	}
            }
            if (parameter.equalsIgnoreCase("age3"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("3")){
                    start.add(11);
                    end.add(15);
            	}
            }
            if (parameter.equalsIgnoreCase("age4"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("4"))
            	{
                    start.add(16);
                    end.add(17);
            	}
            }
            int agestart = (start.size() != 0)? start.get(0) : -1;
            int ageend = (end.size() != 0)? end.get(0) : -1;
            for(int i = 0; i < start.size(); i++){
            	if(start.get(i) < agestart)
            		agestart = start.get(i);
                if(end.get(i) > ageend)
                	ageend = end.get(i);
            }
            criteria.ageStart = agestart;
            criteria.ageEnd = ageend;
            
            
            if (parameter.equalsIgnoreCase("child"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("y"))
                    criteria.childGuide = true;
                else
                    criteria.childGuide = false;
            }
 /*
           if (parameter.equalsIgnoreCase("adolescentGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.adolescentGuide = true;
                else
                    criteria.adolescentGuide = false;
            }
            if (parameter.equalsIgnoreCase("adultGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.adultGuide = true;
                else
                    criteria.adultGuide = false;
            }
            if (parameter.equalsIgnoreCase("agesGuide"))
            {
                temp = request.getParameter(parameter);
                criteria.agesGuide = temp;
            }
 */ 
            if (parameter.equalsIgnoreCase("csexHIV"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("HIV"))
                    criteria.hivTestGuide = true;
                else
                    criteria.hivTestGuide = false;
            }

            if (parameter.equalsIgnoreCase("csubstance"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("subt"))
                    criteria.subAbGuide = true;
                else
                    criteria.subAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("csex"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("se"))
                    criteria.sexAbGuide = true;
                else
                    criteria.sexAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("canger"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("ang"))
                    criteria.angManGuide = true;
                else
                    criteria.angManGuide = false;
            }
            if (parameter.equalsIgnoreCase("sexLGBT"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("LGBT"))
                    criteria.lgbtGuide = true;
                else
                    criteria.lgbtGuide = false;
            }
        }
        return criteria ;
    }
   
     private Hospital populateCriteriaFromRequest_addHospital(HttpServletRequest request)
    {
        Hospital criteria = new Hospital();
        Enumeration parameterNames = request.getParameterNames();
        String temp;

        while (parameterNames.hasMoreElements()) 
        {
            String parameter = (String)parameterNames.nextElement();
            temp = request.getParameter("id");
            criteria.id = 1;
            if(temp != null && temp != "")
                criteria.id = Integer.parseInt(temp);
            temp = request.getParameter("county");
            if(temp.equals("1")) {
                criteria.county = "Clayton";
            } else if (temp.equals("2")) {
                criteria.county = "Cobb";
            } else if (temp.equals("3")) {
                       criteria.county = "DeKalb";
            } else if (temp.equals("4")) {
                       criteria.county = "Fulton";
            } else if (temp.equals("5")) {
                criteria.county = "Gwinnett";
            }
            temp = request.getParameter("parking");
            criteria.parking = 0;
            if(temp != null && temp.equals("yes"))
                criteria.parking = 1;
            criteria.walkIn = 1;
            if (parameter.equalsIgnoreCase("appt"))
            {
                temp = request.getParameter(parameter);
                //if walkin was already processed
                if (temp != null && temp.equalsIgnoreCase("walkin")) {
                    criteria.walkIn = 2;
                } 
                else if (temp != null && temp.equalsIgnoreCase("appointment")){
                    criteria.walkIn = 1;
                }
            }
            temp = request.getParameter("insuranceForm");
            criteria.medicare = false;
            criteria.medicaid = false;
            criteria.peachCare = false;
            if(temp != null && temp.equals("1")) {
                criteria.medicare = true;
            } else if (temp != null && temp.equals("2")) {
                criteria.medicaid = true;
            } else if (temp != null && temp.equals("3")) {
                criteria.peachCare = true;
            }
            criteria.spanFo = 0;
            temp = request.getParameter("forms");
            if(temp != null && temp.equals("yes")) {
                criteria.spanFo = 1;
            } 
            temp = request.getParameter("reception");
            if(temp != null && temp.equals("yes")) {
                criteria.spanAdmin = 1;
            } else{ 
                criteria.spanAdmin = 0;
            } 
            if (parameter.equalsIgnoreCase("interpreter"))
            {
                temp = request.getParameter(parameter);
                if (temp != null && temp.equalsIgnoreCase("interpY")) {
                	criteria.spanInterpreter = 1;
                } 
                else 
                	criteria.spanInterpreter = 0;
            }
            temp = request.getParameter("call");
            if(temp != null && temp.equals("yes"))
                criteria.onCall = true;
            else criteria.onCall = false;
            //Stuff about days would go here, we're ignoring that for now.
            if (parameter.equalsIgnoreCase("family"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("fam"))
                    criteria.spcFCH = true;
                else
                    criteria.spcFCH = false;
            }
            temp = request.getParameter("womens");
            if(temp != null && temp.equals("wo"))
                criteria.spcWH = true;
            else criteria.spcWH = false;
            temp = request.getParameter("mens");
            if(temp != null && temp.equals("me"))
                criteria.spcMH = true;
            else criteria.spcMH = false;
            temp = request.getParameter("mental");
            if(temp != null && temp.equals("ment"))
                criteria.spcMHC = true;
            else criteria.spcMHC = false;
            temp = request.getParameter("dental");
            if(temp != null && temp.equals("dent"))
                criteria.spcDH = true;
            else criteria.spcDH = false;
            temp = request.getParameter("vision");
            if(temp != null && temp.equals("vis"))
                criteria.spcVH = true;
            else criteria.spcVH = false;
            temp = request.getParameter("child");
            if(temp != null && temp.equals("y"))
                criteria.childGuide = true;
            else criteria.childGuide = false;
            temp = request.getParameter("mentalhealth");
            if(temp != null && temp.equals("subt"))
                criteria.subAbGuide = true;
            else criteria.subAbGuide = false;
            temp = request.getParameter("mentalhealth1");
            if(temp != null && temp.equals("se"))
                criteria.sexAbGuide = true;
            else criteria.sexAbGuide = false;
            temp = request.getParameter("mentalhealth2");
            if(temp != null && temp.equals("ang"))
                criteria.angManGuide = true;
            else criteria.angManGuide = false;
            temp = request.getParameter("mentalhealth3");
            if(temp != null && temp.equals("HIV"))
                criteria.hivConsGuide = true;
            else criteria.hivConsGuide = false;
            temp = request.getParameter("mentalhealth4");
            if(temp != null && temp.equals("LGBT"))
                criteria.lgbtGuide = true;
            else criteria.lgbtGuide = false;
            criteria.childGuide = false;
            temp = request.getParameter("child");
            if(temp != null && temp.equals("yes"))
                criteria.childGuide = true;
            temp = request.getParameter("web");
            criteria.website = temp;
            temp = request.getParameter("pho");
            criteria.phone = temp;
            temp = request.getParameter("add");
            criteria.addressLine1 = temp;
            temp = request.getParameter("city");
            criteria.city = temp;
        }
        return criteria ;
    }
}
