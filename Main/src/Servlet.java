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

    private String max_survno;  //get the max number of survno, when insert a new hospital, make the value be larger than the values currently exist
    private String[] bugs = new String[2]; //the inputs in bug report form
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
            //get the max value of survno 
            ResultSet result_max_survno = statement.executeQuery("SELECT MAX(SurvNo) FROM P1;");
            while(result_max_survno.next()){
            	max_survno = result_max_survno.getString("MAX(SurvNo)"); 
            	System.out.println(Integer.parseInt(max_survno) + 1);
            }
            //create bug report table
            statement.executeUpdate(createBugTable());
            statement.executeUpdate(addBugClauses(bugs[0], bugs[1]));
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
        //edit hospital information in the database
        //builder.append(editHospitalClauses(criteria));
        //add a new hospital
        //builder.append(addHospitalClauses(criteria));
        
        return builder.toString();
    }
    
    private String createBugTable() {
    	return "CREATE TABLE IF NOT EXISTS BUG_REPORT ( " +
    			"ID INT(100) NOT NULL AUTO_INCREMENT, " +
    			"PRIMARY KEY(ID), " + 
    			"bug varchar(20), " +
    			"bugDesc varchar(225));";
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
        
        //Personal Information   
        if(criteria.county != null && !criteria.county.equals("")){
            //TODO are we only returning results from the county they live in? What if they're right on the border?
            stringBuilder.append( "P1.County = '" + criteria.county + "' AND ");
        }
        
        //Transportation
        if(criteria.parking == Hospital.TRUE){
            //TODO are there really hospitals without parking? This seems poorly thought out.
        	stringBuilder.append( "P3_4_5.Park != 0 AND ");
        }
        if(criteria.publicTransportation != null){
            //TODO how is this formatted in the database?
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

            if (parameter.equalsIgnoreCase("address"))
            {
                temp = request.getParameter(parameter);
                criteria.county = temp;
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
            
            
            if (parameter.equalsIgnoreCase("parking"))
            {
                temp = request.getParameter(parameter);
                criteria.parking = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }

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
  /*          if (parameter.equalsIgnoreCase("spanInterpreter"))
            {
                temp = request.getParameter(parameter);
                criteria.spanInterpreter = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
*/
            if (parameter.equalsIgnoreCase("phone"))
            {
                temp = request.getParameter(parameter);
                criteria.spanPhone = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("call"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("y"))
                    criteria.onCall = true;
                else
                    criteria.onCall = false;
            }
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
            if (parameter.equalsIgnoreCase("bugs1"))
            {
                temp = request.getParameter(parameter);
                if(temp.equals("1")) {
                    bugs[0] = "Broken Functionality";
                } else if (temp.equals("2")) {
                	bugs[0] = "Incorrect Information";
                } else if (temp.equals("3")) {
                	bugs[0] = "Typos";
                } else if (temp.equals("4")) {
                	bugs[0] = "Other";
                }
            }
            if (parameter.equalsIgnoreCase("bugs2"))
            {
                temp = request.getParameter(parameter);
                bugs[1] = temp;
            }
        }
        return criteria ;
    }
    //edit hospital information
    private String editHospitalClauses(Hospital criteria) {
        StringBuilder stringBuilder = new StringBuilder();
        String ss = "";
        //page 1
    	if(criteria.county != null && !criteria.county.equals("")){
    		stringBuilder.append( "UPDATE P1 SET County = '" + criteria.county + "' WHERE ID = " + criteria.id + "; ");
        }
    	String s1 = stringBuilder.toString();
    	stringBuilder = new StringBuilder();
    	//page 2
    	if(criteria.onCall != null && criteria.onCall == true){
    		stringBuilder.append( "UPDATE P2 SET OnCall = '" + criteria.onCall + "' WHERE ID = " + criteria.id + "; ");
        }
    	String s2 = stringBuilder.toString();
    	stringBuilder = new StringBuilder();
    	//page3_4_5
    	stringBuilder.append( "UPDATE P3_4_5 SET ");
    	
        //Transportation
        if(criteria.parking == Hospital.TRUE || criteria.parking == Hospital.FALSE){
            //TODO are there really hospitals without parking? This seems poorly thought out.
        	stringBuilder.append( "Park = " + criteria.parking + " AND ");
        }
        if(criteria.publicTransportation != null){
            //TODO how is this formatted in the database?
            stringBuilder.append( "PubTr = " + criteria.publicTransportation + " AND ");
        }

        //Clinic services
        if(criteria.walkIn != null){
            //on 2 it's appointments only, on 1 it's walk in only. 3 is always right.
            stringBuilder.append( "AppWalk = " + criteria.walkIn + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.medicare) || Boolean.FALSE.equals(criteria.medicare)) {
            stringBuilder.append( "MedicareGUIDE = " + criteria.medicare + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.medicaid) || Boolean.FALSE.equals(criteria.medicaid)) {
            stringBuilder.append( "MedicaidGUIDE = " + criteria.medicaid + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.peachCare) || Boolean.FALSE.equals(criteria.peachCare)) {
            stringBuilder.append( "PeachcareGUIDE = " + criteria.peachCare + " AND ");
        }
        
        if (criteria.spanAdmin != null) {
            stringBuilder.append( "SPANAdmGUIDE = " + criteria.spanAdmin + " AND ");
        }
        if (criteria.spanNurse != null) {
            stringBuilder.append( "SPANNurGUIDE = " + criteria.spanNurse + " AND ");
        }
        if (criteria.spanDoc != null) {
            stringBuilder.append( "SPANDocGUIDE = " + criteria.spanDoc + " AND ");
        }
        
        if (criteria.spanFo != null) {
            stringBuilder.append( "SPANFoGUIDE = " + criteria.spanFo + " AND ");
        }
        
        if (criteria.spanPhone != null) {
            stringBuilder.append( "PSPANIntPhGUIDE = " + criteria.spanPhone + " AND ");
        }
       
        
        //Healthcare
        if(Boolean.TRUE.equals(criteria.spcWH) || Boolean.FALSE.equals(criteria.spcWH)) {
            stringBuilder.append( "SpcWH = " + criteria.spcWH + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcMH) || Boolean.FALSE.equals(criteria.spcMH)) {
            stringBuilder.append( "SpcMH = " + criteria.spcMH + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcFCH) || Boolean.FALSE.equals(criteria.spcFCH)) {
            stringBuilder.append( "SpcFCH = " + criteria.spcFCH + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcMHC) || Boolean.FALSE.equals(criteria.spcMHC)) {
            stringBuilder.append( "SpcMHC = " + criteria.spcMHC + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcDH) || Boolean.FALSE.equals(criteria.spcDH)) {
            stringBuilder.append( "SpcDH = " + criteria.spcDH + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.spcVH) || Boolean.FALSE.equals(criteria.spcVH)) {
            stringBuilder.append( "SpcVH = " + criteria.spcVH + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.childGuide) || Boolean.FALSE.equals(criteria.childGuide)){
            stringBuilder.append( "NinosGUIDE = " + criteria.childGuide + " AND ");
        }
        
        if(Boolean.TRUE.equals(criteria.childGuide) && criteria.ageStart != -1){
        	stringBuilder.append( "AgeStart = " + criteria.ageStart + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.childGuide) && criteria.ageEnd != -1){
        	stringBuilder.append( "AgeEnd = " + criteria.ageEnd + " AND ");
        }
        
        if(Boolean.TRUE.equals(criteria.subAbGuide) || Boolean.FALSE.equals(criteria.subAbGuide)) {
            stringBuilder.append( "SubAbGuide = " + criteria.subAbGuide + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.sexAbGuide) || Boolean.FALSE.equals(criteria.sexAbGuide)) {
            stringBuilder.append( "SexAbGuide = " + criteria.sexAbGuide + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.angManGuide) || Boolean.FALSE.equals(criteria.angManGuide)) {
            stringBuilder.append( "AngManGuide = " + criteria.angManGuide + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.hivConsGuide) || Boolean.FALSE.equals(criteria.hivConsGuide)) {
            stringBuilder.append( "HIVConsGUIDE = " + criteria.hivConsGuide + " AND ");
        }
        if(Boolean.TRUE.equals(criteria.lgbtGuide) || Boolean.FALSE.equals(criteria.lgbtGuide)) {
            stringBuilder.append( "LGBTGUIDE = " + criteria.lgbtGuide + " AND ");
        }
        
        String s3 = stringBuilder.toString();
        if (s3.length() <= 20) {
        	ss = s1 + s2;
            return ss;
        }
        ss = s1 + s2 + s3.substring(0, ss.length() - 5); //the -5 is to pop off the last AND.
        ss = ss + "WHERE ID = " + criteria.id + "; ";
        return ss;
    }
    //add new hospital
    private String addHospitalClauses(Hospital criteria){
    	StringBuilder stringBuilder = new StringBuilder();
    	String survno = String.valueOf(Integer.parseInt(max_survno) + 1);
    	String addfacl = "None";
    	
    	//page 1
    	stringBuilder.append("INSERT INTO P1 (SurvNo, AddFacL1, County)");
    	stringBuilder.append("VALUES (" + survno + ", " + addfacl + ", " + criteria.county + ")");
    	//page 2
    	stringBuilder.append("INSERT INTO P2 (SurvNo, OnCall)");
    	stringBuilder.append("VALUES (" + survno + ", " + criteria.onCall + ")");
    	//page3_4_5
    	stringBuilder.append("INSERT INTO P3_4_5 (SurvNo, Park, PubTr, AppWalk, MedicareGuide, MedicaidGuide, " +
    			"PeachcareGuide, SPANAdmGUIDE, SPANNurGUIDE, SPANDocGUIDE, SPANFoGuide, " +
    			"SPANIntPhGUIDE, SpcWH, SpcMH, SpcFCH, SpcMHC," +
    			"SpcDH, SpcVH, NinosGUIDE, AgeStart, " +
    			"AgeEnd, subAbGuide, sexAbGuide, angManGuide, " +
    			"HIVConsGUIDE, LGBTGUIDE)");
    	stringBuilder.append("VALUES (" + survno + ", " + criteria.parking + ", " + criteria.publicTransportation + criteria.walkIn + ", " + criteria.medicare + ", " + criteria.medicaid + ", " + 
    			criteria.peachCare + ", " + criteria.spanAdmin + ", " + criteria.spanNurse + ", " + criteria.spanDoc + ", " + criteria.spanFo + "," + 
    			criteria.spanPhone + ", " + criteria.spcWH + ", " + criteria.spcMH + ", " + criteria.spcFCH + ", " + criteria.spcMHC + ", " + 
    			criteria.spcDH + ", " + criteria.spcVH + ", " + criteria.childGuide + ", " + criteria.ageStart + ", " + 
    			criteria.ageEnd + ", " + criteria.subAbGuide + ", " + criteria.sexAbGuide + ", " + criteria.angManGuide + ", " + 
    			criteria.hivConsGuide + ", " + criteria.lgbtGuide + ")");
        String ss = stringBuilder.toString();
        return ss;
    }
}
