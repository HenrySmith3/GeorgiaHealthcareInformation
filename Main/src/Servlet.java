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
     * Currently unused.
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Post method received.");
    }

    /**
     * The main request processing.
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Hospital criteria = populateCriteriaFromRequest(request);
        PrintWriter writer = response.getWriter();
        Connection con = initializeConnection();
        JSONArray hospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getQuery(criteria));
            while (resultset.next()) {
                Hospital hospital = Hospital.getHospitalFromResultSet(resultset);
                hospitals.add(hospital.toJson());
                //writer.append(hospital.toString() + "\n");
            }
            con.close();
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
        
        //page1
        if(criteria.county != null){
            stringBuilder.append( "P1.County = '" + criteria.county + "' AND ");
        }

        //page2
        if(criteria.onCall != null){
            if(criteria.onCall == true)
         	    stringBuilder.append( "P2.OnCall = " + 1 + " AND ");
        }
//        if(criteria.openTimes != null){
//            int k = 0;
//            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
//        	stringBuilder.append( "P2." + day + "Op = " + criteria.openTimes.get(k) + " AND ");
//        	k++;
//            }
//        }
//        if(criteria.closeTimes != null){
//            int k = 0;
//            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
//        	stringBuilder.append( "P2." + day + "Cl = " + criteria.closeTimes.get(k) + " AND ");
//        	k++;
//            }
//        }
//        if(criteria.commentsOnTimes != null){
//            int k = 0;
//            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
//        	stringBuilder.append( "P2." + day + "Comm = " + criteria.commentsOnTimes.get(k) + " AND ");
//        	k++;
//            }
//        }

        //page3_4_5
        if(criteria.walkIn != null){
            //on 2 it's appointments only, on 1 it's walk in only. 3 is always right.
            stringBuilder.append( "(P3_4_5.AppWalk = 3 OR ");
            stringBuilder.append( "P3_4_5.AppWalk = " + criteria.walkIn + ") AND ");
        }
        if(criteria.parking != null){
            //TODO are there really hospitals without parking? This seems poorly thought out.
            stringBuilder.append( "P3_4_5.ParkGTGUIDE = " + criteria.parking + " AND ");
        }
        if(criteria.publicTransportation != null){
            //TODO how is this formatted in the database?
//            stringBuilder.append( "P3_4_5.PubTr = " + criteria.publicTransportation + " AND ");
        }
        if (criteria.spanAdmin != null) {
            stringBuilder.append( "P3_4_5.SPANAdm != 3 AND ");
        }
        if (criteria.spanNurse != null) {
            stringBuilder.append( "P3_4_5.SpanNur != 3 AND ");
        }
        if (criteria.spanDoc != null) {
            stringBuilder.append( "P3_4_5.SpanDoc != 3 AND ");
        }
        if (criteria.spanInterpreter != null) {
            stringBuilder.append( "P3_4_5.SpanIntON != 3 AND ");
        }
        if (criteria.spanPhone != null) {
            stringBuilder.append( "P3_4_5.SpanIntPh != 3 AND ");
        }
        if (criteria.spanFo != null) {
            stringBuilder.append( "P3_4_5.SpanFo != 3 AND ");
        }
        //TODO fix these double if statements
        if(criteria.medicare == true) {
                stringBuilder.append( "P3_4_5.MedicareGUIDE = " + 1 + " AND ");
        }
        if(criteria.medicaid == true) {
                stringBuilder.append( "P3_4_5.MedicaidGUIDE = " + 1 + " AND ");
        }
        if(criteria.peachCare == true) {
                stringBuilder.append( "P3_4_5.PeachcareGUIDE = " + 1 + " AND ");
        }
        if(criteria.spcFCH == true) {
                stringBuilder.append( "P3_4_5.SpcFCH = " + 1 + " AND ");
        }
        if(criteria.spcWH == true) {
                stringBuilder.append( "P3_4_5.SpcWH = " + 1 + " AND ");
        }
        if(criteria.spcMH == true) {
                stringBuilder.append( "P3_4_5.SpcMH = " + 1 + " AND ");
        }
        if(criteria.spcMHC == true) {
                stringBuilder.append( "P3_4_5.SpcMHC = " + 1 + " AND ");
        }
        if(criteria.spcDH == true) {
                stringBuilder.append( "P3_4_5.SpcDH = " + 1 + " AND ");
        }
        if(criteria.spcVH == true) {
                stringBuilder.append( "P3_4_5.SpcVH = " + 1 + " AND ");
        }
        if(criteria.spcOT == true) {
                stringBuilder.append( "P3_4_5.SpcOT = " + 1 + " AND ");
        }
        if(criteria.freeLow == true) {
                stringBuilder.append( "P3_4_5.FreeLow = " + 1 + " AND ");
        }
        if (criteria.spcDk == Hospital.TRUE) {
            stringBuilder.append( "P3_4_5.SpcDk = " + criteria.spcDk + " AND ");
        }
        if (criteria.age == Hospital.TRUE) {
            stringBuilder.append( "P3_4_5.Age = " + criteria.age + " AND ");
        }
        if (criteria.ageStart == Hospital.TRUE) {
            stringBuilder.append( "P3_4_5.AgeStart = " + criteria.ageStart + " AND ");
        }
        if (criteria.ageEnd == Hospital.TRUE) {
            stringBuilder.append( "P3_4_5.AgeEnd = " + criteria.ageEnd + " AND ");
        }
        //TODO this is obviously wrong, but I'm leaving it like this because I don't know what we're doing about children yet.
        if (criteria.childGuide != null) {
            if(criteria.childGuide == true)
                stringBuilder.append( "P3_4_5.NinosGUIDE = " + 1 + " AND ");
            if(criteria.childGuide == false)
                stringBuilder.append( "P3_4_5.NinosGUIDE = " + 0 + " AND ");
        }
        if (criteria.adolescentGuide != null) {
            if(criteria.adolescentGuide == true)
                stringBuilder.append( "P3_4_5.AdolescGUIDE = " + 1 + " AND ");
            if(criteria.adolescentGuide == false)
                stringBuilder.append( "P3_4_5.AdolescGUIDE = " + 0 + " AND ");
        }
        if (criteria.adultGuide != null) {
            if(criteria.adultGuide == true)
                stringBuilder.append( "P3_4_5.AdultGUIDE = " + 1 + " AND ");
            if(criteria.adultGuide == false)
                stringBuilder.append( "P3_4_5.AdultGUIDE = " + 0 + " AND ");
        }
        if (criteria.agesGuide != null) {
            stringBuilder.append( "P3_4_5.EdadesGUIDE = " + criteria.agesGuide + " AND ");
        }
        if (criteria.otServ != null) {
            stringBuilder.append( "P3_4_5.OTServ = " + criteria.otServ + " AND ");
        }
        if(criteria.hivTestGuide == true) {
                stringBuilder.append( "P3_4_5.HIVTestGUIDE = " + 1 + " AND ");
        }
        if(criteria.abortionGuide == true) {
                stringBuilder.append( "P3_4_5.AbortionGUIDE = " + 1 + " AND ");
        }
        if (criteria.mhCount != null) {
            //TODO mental health is more complicated than this in the database.
            stringBuilder.append( "P3_4_5.MHCoun = " + criteria.mhCount + " AND ");
        }
        if (criteria.subAbGuide != null) {
            if(criteria.subAbGuide == true)
                stringBuilder.append( "P3_4_5.SubAbGuide = " + 1 + " AND ");
        }
        if (criteria.sexAbGuide != null) {
            if(criteria.sexAbGuide == true)
                stringBuilder.append( "P3_4_5.SexAbGuide = " + 1 + " AND ");
        }
        if (criteria.angManGuide != null) {
            if(criteria.angManGuide == true)
                stringBuilder.append( "P3_4_5.AngManGuide = " + 1 + " AND ");
        }
        if (criteria.hivConsGuide != null) {
            if(criteria.hivConsGuide == true)
                stringBuilder.append( "P3_4_5.HIVConsGUIDE = " + 1 + " AND ");
        }
        if (criteria.lgbtGuide != null) {
            if(criteria.lgbtGuide == true)
                stringBuilder.append( "P3_4_5.LGBTGUIDE = " + 1 + " AND ");
        }
        if (criteria.suppGGuide != null) {
            if(criteria.suppGGuide == true)
                stringBuilder.append( "P3_4_5.SuppGGUIDE = " + 1 + " AND ");
        }
        String ss = stringBuilder.toString();
        ss = "WHERE " + ss.substring(0, ss.length() - 5); //the -5 is to pop off the last AND.
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

            if (parameter.equalsIgnoreCase("county"))
            {
                temp = request.getParameter(parameter);
                criteria.county = temp;
            }
            if (parameter.equalsIgnoreCase("publicTransportation"))
            {
                temp = request.getParameter(parameter);
                criteria.publicTransportation = (temp == null)? -1 : Integer.parseInt(temp);
            }
            if (parameter.equalsIgnoreCase("parking"))
            {
                temp = request.getParameter(parameter);
                criteria.parking = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }

            if (parameter.equalsIgnoreCase("walkIn"))
            {
                temp = request.getParameter(parameter);
                //if appt was already processed
                if (criteria.walkIn == 2) {
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
            if (parameter.equalsIgnoreCase("appt"))
            {
                temp = request.getParameter(parameter);
                //if walkin was already processed
                if (criteria.walkIn == 1) {
                    if (temp.equalsIgnoreCase("appmt")) {
                        criteria.walkIn = 3;
                    }
                } else {
                    criteria.walkIn = temp.equalsIgnoreCase("appmt") ? Hospital.TRUE : Hospital.FALSE;
                }
            }
            if (parameter.equalsIgnoreCase("medicaid"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.medicaid = true;
                else
                    criteria.medicaid = false;
            }
            if (parameter.equalsIgnoreCase("peachCare"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.peachCare = true;
                else
                    criteria.peachCare = false;
            }
            if (parameter.equalsIgnoreCase("spanishSpeakingStaff"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spanishSpeakingStaff = true;
                else
                    criteria.spanishSpeakingStaff = false;
            }
            if (parameter.equalsIgnoreCase("spanNurse"))
            {
                temp = request.getParameter(parameter);
                criteria.spanNurse = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("spanDoc"))
            {
                temp = request.getParameter(parameter);
                criteria.spanDoc = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("forms"))
            {
                temp = request.getParameter(parameter);
                criteria.spanFo = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("spanInterpreter"))
            {
                temp = request.getParameter(parameter);
                criteria.spanInterpreter = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("phone"))
            {
                temp = request.getParameter(parameter);
                criteria.spanPhone = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("call"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.onCall = true;
                else
                    criteria.onCall = false;
            }
            //Stuff about days would go here, we're ignoring that for now.
            if (parameter.equalsIgnoreCase("spcFCH"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spcFCH = true;
                else
                    criteria.spcFCH = false;
            }
            if (parameter.equalsIgnoreCase("spcWH"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spcWH = true;
                else
                    criteria.spcWH = false;
            }
            if (parameter.equalsIgnoreCase("spcMH"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spcMH = true;
                else
                    criteria.spcMH = false;
            }
            if (parameter.equalsIgnoreCase("spcMHC"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spcMHC = true;
                else
                    criteria.spcMHC = false;
            }
            if (parameter.equalsIgnoreCase("spcDH"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spcDH = true;
                else
                    criteria.spcDH = false;
            }
            if (parameter.equalsIgnoreCase("spcVH"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.spcVH = true;
                else
                    criteria.spcVH = false;
            }
            if (parameter.equalsIgnoreCase("age"))
            {
                temp = request.getParameter(parameter);
                criteria.age = (temp == null)? -1 : Integer.parseInt(temp);
            }
            if (parameter.equalsIgnoreCase("ageStart"))
            {
                temp = request.getParameter(parameter);
                criteria.ageStart = (temp == null)? -1 : Integer.parseInt(temp);
            }
            if (parameter.equalsIgnoreCase("ageEnd"))
            {
                temp = request.getParameter(parameter);
                criteria.ageEnd = (temp == null)? -1 : Integer.parseInt(temp);
            }
            if (parameter.equalsIgnoreCase("childGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.childGuide = true;
                else
                    criteria.childGuide = false;
            }
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
            if (parameter.equalsIgnoreCase("hivTestGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.hivTestGuide = true;
                else
                    criteria.hivTestGuide = false;
            }
            if (parameter.equalsIgnoreCase("subAbGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.subAbGuide = true;
                else
                    criteria.subAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("sexAbGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.sexAbGuide = true;
                else
                    criteria.sexAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("angManGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.angManGuide = true;
                else
                    criteria.angManGuide = false;
            }
            if (parameter.equalsIgnoreCase("hivConsGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.hivConsGuide = true;
                else
                    criteria.hivConsGuide = false;
            }
            if (parameter.equalsIgnoreCase("lgbtGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.lgbtGuide = true;
                else
                    criteria.lgbtGuide = false;
            }
        }
        return criteria ;
    }
}
