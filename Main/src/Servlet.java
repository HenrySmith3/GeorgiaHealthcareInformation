import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        } else if (request.getParameter("action").equalsIgnoreCase("populateFields")) {
            populateFields(request, response);
        }

}

    private void adminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("")|| password.equals("")) {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
        Connection con = Database.initializeConnection();
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
        Connection con = Database.initializeConnection();
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

    private void populateFields(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital hospital1 = new Hospital();
        String getThis = hospital1.toJson().toString();
        String id = request.getParameter("survNo");
        Connection con = Database.initializeConnection();
        Statement statement = null;
        try {
            statement = con.createStatement();
            String query = "SELECT * FROM p1 JOIN p2 JOIN p3_4_5 JOIN additionalSpecialties WHERE p1.SurvNo=" + id +
                    " AND p2.SurvNo=" + id + " AND p3_4_5.SurvNo=" + id + " AND additionalSpecialties.SurvNo=" + id;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            Hospital hospital = Hospital.getHospitalFromResultSet(resultSet);
            request.setAttribute("hospital", hospital.toJson());
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void processSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital criteria = CriteriaPopulator.populateCriteriaFromRequest(request);
        Connection con = Database.initializeConnection();
        JSONArray hospitalsInCounty = new JSONArray();
        JSONArray allHospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(Database.getQuery(criteria, true));
            while (resultset.next()) {
                Hospital hospital = Hospital.getHospitalFromResultSet(resultset);
                hospitalsInCounty.add(hospital.toJson());
                //writer.append(hospital.toString() + "\n");
            }
            statement = con.createStatement();
            resultset = statement.executeQuery(Database.getQuery(criteria, false));
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
        Connection con = Database.initializeConnection();
        JSONArray hospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(Database.getIDLookupQuery());
            while (resultset.next()) {
                JSONObject object = new JSONObject();
                object.accumulate("name", resultset.getString("NameFac"));
                object.accumulate("SurvNo", resultset.getString("SurvNo"));
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
        Connection con = Database.initializeConnection();
        JSONArray hospitals = new JSONArray();
        try {
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(Database.getBugLookup());
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

    private void submitBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection con = Database.initializeConnection();
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
        Hospital criteria = CriteriaPopulator.populateCriteriaFromRequest_addHospital(request);
        Connection con = Database.initializeConnection();
        String max_survno1 = "", max_survno2 = "", max_survno3 = "", max_survno4 = "";  //find the max number of survno, when insert a new hospital, make the value be larger than the values currently exist
//        TODO I'm pretty sure SQL does all of this automatically.
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
            result_max_survno = statement.executeQuery("SELECT MAX(SurvNo) FROM additionalSpecialties;");
            while(result_max_survno.next()){
                max_survno4 = result_max_survno.getString("MAX(SurvNo)");
            }
            max_survno = Math.max(Integer.parseInt(max_survno1),  Integer.parseInt(max_survno2));
            max_survno = Math.max(max_survno, Integer.parseInt(max_survno3));
            max_survno = Math.max(max_survno, Integer.parseInt(max_survno4));
            String survno = String.valueOf(max_survno + 1);
            String ss = "INSERT INTO P1 (NameFac, SurvNo, AddFacL1, County, Website, Phone, City, ZIPCode) VALUES ('" + criteria.name + "', '" + survno + "', '" + criteria.addressLine1 + "', '" + criteria.county + "','" + criteria.website + "', '" + criteria.phone + "', '" + criteria.city + "','" + criteria.zip + "'); ";
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

            //now the additional specialties.
            stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO additionalSpecialties (SurvNo, PrimaryCare, Internal, Pediatric, Geriatric, Diabetes," +
                    "Pain, Emergency, Surgery, Radiology, Dermatology, ENT, Allergy, Prenatal)");
            stringBuilder.append("VALUES ('"
            + survno + "', '"
            + (criteria.primaryCare.equals("1") ? "1" : "0") + "', '"
            + (criteria.internal.equals("1") ? "1" : "0") + "', '"
            + (criteria.pediatric.equals("1") ? "1" : "0") + "', '"
            + (criteria.geriatric.equals("1") ? "1" : "0") + "', '"
            + (criteria.diabetes.equals("1") ? "1" : "0") + "', '"
            + (criteria.pain.equals("1") ? "1" : "0") + "', '"
            + (criteria.emergency.equals("1") ? "1" : "0") + "', '"
            + (criteria.surgery.equals("1") ? "1" : "0") + "', '"
            + (criteria.radiology.equals("1") ? "1" : "0") + "', '"
            + (criteria.dermatology.equals("1") ? "1" : "0") + "', '"
            + (criteria.ent.equals("1") ? "1" : "0") + "', '"
            + (criteria.allergy.equals("1") ? "1" : "0") + "', '"
            + (criteria.prenatal.equals("1") ? "1" : "0") + "');");
            ss = stringBuilder.toString();
            statement.executeUpdate(ss);
          con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void editHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Hospital criteria = CriteriaPopulator.populateCriteriaFromRequest_addHospital(request);
        Connection con = Database.initializeConnection();
        String ss = "";
        try {
            Statement statement = con.createStatement();
            criteria.name = "none";
//            TODO this should all be done in one SQL call.
            if(criteria.county != null && !criteria.county.equals("")){
                ss = "UPDATE P1 SET County = '" + criteria.county + "' WHERE SurvNo = " + criteria.survNo + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.city != null && !criteria.city.equals("")){
                ss = "UPDATE P1 SET City = '" + criteria.city + "' WHERE SurvNo = " + criteria.survNo + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.website != null && !criteria.website.equals("")){
                ss = "UPDATE P1 SET Website = '" + criteria.website + "' WHERE SurvNo = " + criteria.survNo + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.phone != null && !criteria.phone.equals("")){
                ss = "UPDATE P1 SET Phone = '" + criteria.city + "' WHERE SurvNo = " + criteria.survNo + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.addressLine1 != null && !criteria.addressLine1.equals("")){
                ss = "UPDATE P1 SET AddFacL1 = '" + criteria.addressLine1 + "' WHERE SurvNo = " + criteria.survNo + "; ";
                statement.executeUpdate(ss);
            }
            if(criteria.zip != null){
                ss = "UPDATE P1 SET ZIPCode = '" + criteria.zip + "' WHERE SurvNo = " + criteria.survNo + "; ";
                statement.executeUpdate(ss);
            }
            String oncall = "0";
            if(criteria.onCall != null && criteria.onCall == true)
            	oncall = "1";
            else
            	oncall = "0";
            ss = "UPDATE P2 SET OnCall = '" + oncall + "' WHERE SurvNo = " + criteria.survNo + "; ";
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
            ss = ss.substring(0, ss.length() - 2) + "WHERE SurvNo = " + criteria.survNo + "; ";
            System.out.println(ss);
            statement.executeUpdate(ss);
          con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

}
