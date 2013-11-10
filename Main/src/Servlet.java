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
        if(String.valueOf(criteria.id) != null){
            stringBuilder.append( " WHERE p1.ID == " + criteria.id);
        }
        if(criteria.itp != null){
            stringBuilder.append( " WHERE p1.ITP == " + criteria.itp);
        }
        if(criteria.spanishSpeakingStaff != null){
            if(criteria.spanishSpeakingStaff == true)
        	stringBuilder.append( " AND WHERE p1.SPAN == " + 1);
            else if(criteria.spanishSpeakingStaff == false)
        	stringBuilder.append( " AND WHERE p1.SPAN == " + 2);
        }
        if(criteria.name != null){
            stringBuilder.append( " AND WHERE p1.NameFac == " + criteria.name);
        }
        if(criteria.addressLine1 != null){
            stringBuilder.append( " AND WHERE p1.AddFacL1 == " + criteria.addressLine1);
        }
        if(criteria.addressLine2 != null){
            stringBuilder.append( " AND WHERE p1.AddFacL2 == " + criteria.addressLine2);
        }
        if(criteria.city != null){
            stringBuilder.append( " AND WHERE p1.City == " + criteria.city);
        }
        if(criteria.county != null){
            stringBuilder.append( " AND WHERE p1.County == " + criteria.county);
        }
        if(String.valueOf(criteria.zip) != null){
            stringBuilder.append( " AND WHERE p1.ZIPCode == " + criteria.zip);
        }
        if(criteria.phone != null){
            stringBuilder.append( " AND WHERE p1.Phone == " + criteria.phone);
        }
        if(criteria.fax != null){
            stringBuilder.append( " AND WHERE p1.Fax == " + criteria.fax);
        }
        if(criteria.website != null){
            stringBuilder.append( " AND WHERE p1.Website == " + criteria.website);
        }
        if(criteria.mainFacility != null){
            stringBuilder.append( " AND WHERE p1.BraONL == " + criteria.mainFacility);
        }
        if(String.valueOf(criteria.branchRefNumber) != null){
            stringBuilder.append( " AND WHERE p1.BraRefNo == " + criteria.branchRefNumber);
        }
        if(criteria.associatedFacilities != null){
	    for(int i = 0; i < criteria.associatedFacilities.size(); i++){
//	        if(criteria.associatedFacilities.get(i)[0] != null)stringBuilder.append( " AND WHERE p1.OTFac" + i + 1 + " == " + criteria.associatedFacilities.get(i)[0]);
//	        if(criteria.associatedFacilities.get(i)[1] != null)stringBuilder.append( " AND WHERE p1.OTFac" + i + 1 + "Ph == " + criteria.associatedFacilities.get(i)[1]);
	    }      	
        }
        if(criteria.intComm != null){
            stringBuilder.append( " AND WHERE p1.IntComm == " + criteria.intComm);
        }
        
        //page2
        if(criteria.open247 != null){
            if(criteria.open247 == true)
                stringBuilder.append( " AND WHERE p2.Op247 == " + 1);
            else if(criteria.spanishSpeakingStaff == false)
               	stringBuilder.append( " AND WHERE p2.Op247 == " + 0);
        }
        if(criteria.onCall != null){
            if(criteria.open247 == true)
         	stringBuilder.append( " AND WHERE p2.OnCall == " + 1);
            else if(criteria.spanishSpeakingStaff == false)
        	stringBuilder.append( " AND WHERE p2.OnCall == " + 0);
        }
        if(criteria.openTimes != null){
            int k = 0;
            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
        	stringBuilder.append( " AND WHERE p2." + day + "Op == " + criteria.openTimes.get(k));
        	k++;
            }
        }
        if(criteria.closeTimes != null){
            int k = 0;
            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
        	stringBuilder.append( " AND WHERE p2." + day + "Cl == " + criteria.closeTimes.get(k));
        	k++;
            }
        }
        if(criteria.commentsOnTimes != null){
            int k = 0;
            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
        	stringBuilder.append( " AND WHERE p2." + day + "Comm == " + criteria.commentsOnTimes.get(k));
        	k++;
            }
        }
        if(criteria.spanishTimesComment != null){
            stringBuilder.append( " AND WHERE p2.HOpComm == " + criteria.spanishTimesComment);
        }
        if(criteria.hoursGuide != null){
            stringBuilder.append( " AND WHERE p2.HoursGuide == " + criteria.hoursGuide);
        }
        
        //page3_4_5
        if(String.valueOf(criteria.walkIn) != null){
            stringBuilder.append( " AND WHERE p3_4_5.AppWalk == " + criteria.walkIn);
        }
        if(criteria.walkInComment != null){
            stringBuilder.append( " AND WHERE p3_4_5.AppWalkSp == " + criteria.walkInComment);
        }
        if(String.valueOf(criteria.apptGuide) != null){
            stringBuilder.append( " AND WHERE p3_4_5.ApptGUIDE == " + criteria.apptGuide);
        }
        if(String.valueOf(criteria.walkInGuide) != null){
            stringBuilder.append( " AND WHERE p3_4_5.Walk-inGUIDE == " + criteria.walkInGuide);
        }
        if(String.valueOf(criteria.parking) != null){
            stringBuilder.append( " AND WHERE p3_4_5.park == " + criteria.parking);
        }
        if(criteria.parkingComment != null){
            stringBuilder.append( " AND WHERE p3_4_5.ParkSP == " + criteria.parkingComment);
        }
        if(String.valueOf(criteria.publicTransportation) != null){
            stringBuilder.append( " AND WHERE p3_4_5.PubTr == " + criteria.publicTransportation);
        }
        if(criteria.publicTransportationGuide != null){
            if(criteria.publicTransportationGuide == true)
        	stringBuilder.append( " AND WHERE p3_4_5.ParkGTGUIDE == " + 1);
            else if(criteria.publicTransportationGuide == false)
        	stringBuilder.append( " AND WHERE p3_4_5.ParkGTGUIDE == " + 0);
        }
        if(criteria.publicTransportationComment != null){
            stringBuilder.append( " AND WHERE p3_4_5.PubTrSp == " + criteria.publicTransportationComment);
        }
        if(String.valueOf(criteria.publicTransportationOther) != null){
            stringBuilder.append( " AND WHERE p3_4_5.PubTrOt == " + criteria.publicTransportationOther);
        }
        if(criteria.publicTransportationOtherComment != null){
            stringBuilder.append( " AND WHERE p3_4_5.PubTrOtSp == " + criteria.publicTransportationOtherComment);
        }
        if(criteria.freeTransport != null){
            stringBuilder.append( " AND WHERE p3_4_5.Transportegratis == " + criteria.freeTransport);
        }
        if (String.valueOf(criteria.spanAdmin) != null) {
            stringBuilder.append( " WHERE p3_4_5.SPANAdm == " + criteria.spanAdmin);
        }
        if (criteria.spanAdminGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.SPANAdmGUIDE == " + criteria.spanAdminGuide);
        }
        if (String.valueOf(criteria.spanNurse) != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanNur == " + criteria.spanNurse);
        }
        if (criteria.spanNurseGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanNurGUIDE == " + criteria.spanNurseGuide);
        }
        if (String.valueOf(criteria.spanDoc) != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanDoc == " + criteria.spanDoc);
        }
        if (criteria.spanDocGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanDocGUIDE == " + criteria.spanDocGuide);
        }
        if (String.valueOf(criteria.spanInterpreter) != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanIntON == " + criteria.spanInterpreter);
        }
        if (criteria.spanInterpreterGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanIntONGUIDE == " + criteria.spanInterpreterGuide);
        }
        if (String.valueOf(criteria.spanPhone) != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanIntPh == " + criteria.spanPhone);
        }
        if (criteria.spanPhoneGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanIntPhGUIDE == " + criteria.spanPhoneGuide);
        }
        if (String.valueOf(criteria.spanFo) != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanFo == " + criteria.spanFo);
        }
        if (criteria.spanFoGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.SpanFoGUIDE == " + criteria.spanFoGuide);
        }
        if (String.valueOf(criteria.insurance) != null) {
            stringBuilder.append( " WHERE p3_4_5.Insur == " + criteria.insurance);
        }
        if (criteria.insuranceComment != null) {
            stringBuilder.append( " WHERE p3_4_5.InsurSp == " + criteria.insuranceComment);
        }
        if (criteria.medicare != null) {
            if(criteria.medicare == true)
                stringBuilder.append( " WHERE p3_4_5.MedicareGUIDE == " + 1);
            if(criteria.medicare == false)
                stringBuilder.append( " WHERE p3_4_5.MedicareGUIDE == " + 0);
        }
        if (criteria.medicaid != null) {
            if(criteria.medicaid == true)
                stringBuilder.append( " WHERE p3_4_5.MedicaidGUIDE == " + 1);
            if(criteria.medicare == false)
                stringBuilder.append( " WHERE p3_4_5.MedicaidGUIDE == " + 0);
        }
        if (criteria.peachCare != null) {
            if(criteria.peachCare == true)
                stringBuilder.append( " WHERE p3_4_5.PeachcareGUIDE == " + 1);
            if(criteria.peachCare == false)
                stringBuilder.append( " WHERE p3_4_5.PeachcareGUIDE == " + 0);
        }
        if (String.valueOf(criteria.pay) != null) {
            stringBuilder.append( " WHERE p3_4_5.Pay == " + criteria.pay);
        }
        if (String.valueOf(criteria.financialAssistance) != null) {
            stringBuilder.append( " WHERE p3_4_5.FinAss == " + criteria.financialAssistance);
        }
        if (criteria.payPlanGuide != null) {
            if(criteria.payPlanGuide == true)
                stringBuilder.append( " WHERE p3_4_5.PayPlanGUIDE == " + 1);
            if(criteria.payPlanGuide == false)
                stringBuilder.append( " WHERE p3_4_5.PayPlanGUIDE == " + 0);
        }
        if (criteria.SlideSc != null) {
            if(criteria.SlideSc == true)
                stringBuilder.append( " WHERE p3_4_5.SlideScGUIDE == " + 1);
            if(criteria.SlideSc == false)
                stringBuilder.append( " WHERE p3_4_5.SlideScGUIDE == " + 0);
        }
        if (criteria.financialAssistanceComment != null) {
            stringBuilder.append( " WHERE p3_4_5.FinAssSp == " + criteria.financialAssistanceComment);
        }
        if (criteria.finAssPh != null) {
            stringBuilder.append( " WHERE p3_4_5.FinAssPh == " + criteria.finAssPh);
        }
        if (criteria.finAllPhComment != null) {
            stringBuilder.append( " WHERE p3_4_5.FinAssPhSp == " + criteria.finAllPhComment);
        }
        if (criteria.spcFCH != null) {
            if(criteria.spcFCH == true)
                stringBuilder.append( " WHERE p3_4_5.SpcFCH == " + 1);
            if(criteria.spcFCH == false)
                stringBuilder.append( " WHERE p3_4_5.SpcFCH == " + 0);
        }
        if (criteria.spcWH != null) {
            if(criteria.spcWH == true)
                stringBuilder.append( " WHERE p3_4_5.SpcWH == " + 1);
            if(criteria.spcWH == false)
                stringBuilder.append( " WHERE p3_4_5.SpcWH == " + 0);
        }
        if (criteria.spcMH != null) {
            if(criteria.spcMH == true)
                stringBuilder.append( " WHERE p3_4_5.SpcMH == " + 1);
            if(criteria.spcMH == false)
                stringBuilder.append( " WHERE p3_4_5.SpcMH == " + 0);
        }
        if (criteria.spcMHC != null) {
            if(criteria.spcMHC == true)
                stringBuilder.append( " WHERE p3_4_5.SpcMHC == " + 1);
            if(criteria.spcMHC == false)
                stringBuilder.append( " WHERE p3_4_5.SpcMHC == " + 0);
        }
        if (criteria.spcDH != null) {
            if(criteria.spcDH == true)
                stringBuilder.append( " WHERE p3_4_5.SpcDH == " + 1);
            if(criteria.spcDH == false)
                stringBuilder.append( " WHERE p3_4_5.SpcDH == " + 0);
        }
        if (criteria.spcVH != null) {
            if(criteria.spcVH == true)
                stringBuilder.append( " WHERE p3_4_5.SpcVH == " + 1);
       	    if(criteria.spcVH == false)
                stringBuilder.append( " WHERE p3_4_5.SpcVH == " + 0);
        }
        if (criteria.spcOT != null) {
            if(criteria.spcOT == true)
                stringBuilder.append( " WHERE p3_4_5.SpcOT == " + 1);
            if(criteria.spcOT == false)
                stringBuilder.append( " WHERE p3_4_5.SpcOT == " + 0);
        }
        if (criteria.freeLow != null) {
            if(criteria.freeLow == true)
                stringBuilder.append( " WHERE p3_4_5.FreeLow == " + 1);
            if(criteria.freeLow == false)
                stringBuilder.append( " WHERE p3_4_5.FreeLow == " + 0);
        }
        if (criteria.spcComment != null) {
            stringBuilder.append( " WHERE p3_4_5.SpcSp == " + criteria.spcComment);
        }
        if (criteria.oteSpecial != null) {
            stringBuilder.append( " WHERE p3_4_5.OTEspecial == " + criteria.oteSpecial);
        }
        if (String.valueOf(criteria.spcDk) != null) {
            stringBuilder.append( " WHERE p3_4_5.SpcDk == " + criteria.spcDk);
        }
        if (String.valueOf(criteria.age) != null) {
            stringBuilder.append( " WHERE p3_4_5.Age == " + criteria.age);
        }
        if (String.valueOf(criteria.ageStart) != null) {
            stringBuilder.append( " WHERE p3_4_5.AgeStart == " + criteria.ageStart);
        }
        if (String.valueOf(criteria.ageEnd) != null) {
            stringBuilder.append( " WHERE p3_4_5.AgeEnd == " + criteria.ageEnd);
        }
        if (criteria.ageOTComment != null) {
            stringBuilder.append( " WHERE p3_4_5.AgeOTSp == " + criteria.ageOTComment);
        }
        if (criteria.childGuide != null) {
            if(criteria.childGuide == true)
                stringBuilder.append( " WHERE p3_4_5.NinosGUIDE == " + 1);
            if(criteria.childGuide == false)
                stringBuilder.append( " WHERE p3_4_5.NinosGUIDE == " + 0);
        }
        if (criteria.adolescentGuide != null) {
            if(criteria.adolescentGuide == true)
                stringBuilder.append( " WHERE p3_4_5.AdolescGUIDE == " + 1);
            if(criteria.adolescentGuide == false)
                stringBuilder.append( " WHERE p3_4_5.AdolescGUIDE == " + 0);
        }
        if (criteria.adultGuide != null) {
            if(criteria.adultGuide == true)
                stringBuilder.append( " WHERE p3_4_5.AdultGUIDE == " + 1);
            if(criteria.adultGuide == false)
                stringBuilder.append( " WHERE p3_4_5.AdultGUIDE == " + 0);
        }
        if (criteria.agesGuide != null) {
            stringBuilder.append( " WHERE p3_4_5.EdadesGUIDE == " + criteria.agesGuide);
        }
        if (String.valueOf(criteria.otServ) != null) {
            stringBuilder.append( " WHERE p3_4_5.OTServ == " + criteria.otServ);
        }
        if (criteria.hivTestGuide != null) {
            if(criteria.hivTestGuide == true)
                stringBuilder.append( " WHERE p3_4_5.HIVTestGUIDE == " + 1);
            if(criteria.hivTestGuide == false)
                stringBuilder.append( " WHERE p3_4_5.HIVTestGUIDE == " + 0);
        }
        if (criteria.abortionGuide != null) {
            if(criteria.abortionGuide == true)
                stringBuilder.append( " WHERE p3_4_5.AbortionGUIDE == " + 1);
            if(criteria.abortionGuide == false)
                stringBuilder.append( " WHERE p3_4_5.AbortionGUIDE == " + 0);
        }
        if (String.valueOf(criteria.mhCount) != null) {
            stringBuilder.append( " WHERE p3_4_5.MHCoun == " + criteria.mhCount);
        }
        if (criteria.mhCounSG != null) {
            stringBuilder.append( " WHERE p3_4_5.MHCounSG == " + criteria.mhCounSG);
        }
        if (criteria.mhCounOT != null) {
            stringBuilder.append( " WHERE p3_4_5.MHCounOT == " + criteria.mhCounOT);
        }
        if (criteria.subAbGuide != null) {
            if(criteria.subAbGuide == true)
                stringBuilder.append( " WHERE p3_4_5.SubAbGuide == " + 1);
            if(criteria.subAbGuide == false)
                stringBuilder.append( " WHERE p3_4_5.SubAbGuide == " + 0);
        }
        if (criteria.sexAbGuide != null) {
            if(criteria.sexAbGuide == true)
                stringBuilder.append( " WHERE p3_4_5.SexAbGuide == " + 1);
            if(criteria.sexAbGuide == false)
                stringBuilder.append( " WHERE p3_4_5.SexAbGuide == " + 0);
        }
        if (criteria.angManGuide != null) {
            if(criteria.angManGuide == true)
                stringBuilder.append( " WHERE p3_4_5.AngManGuide == " + 1);
            if(criteria.angManGuide == false)
                stringBuilder.append( " WHERE p3_4_5.AngManGuide == " + 0);
        }
        if (criteria.hivConsGuide != null) {
            if(criteria.hivConsGuide == true)
                stringBuilder.append( " WHERE p3_4_5.HIVConsGUIDE == " + 1);
            if(criteria.hivConsGuide == false)
                stringBuilder.append( " WHERE p3_4_5.HIVConsGUIDE == " + 0);
        }
        if (criteria.lgbtGuide != null) {
            if(criteria.lgbtGuide == true)
                stringBuilder.append( " WHERE p3_4_5.LGBTGUIDE == " + 1);
            if(criteria.lgbtGuide == false)
                stringBuilder.append( " WHERE p3_4_5.LGBTGUIDE == " + 0);
        }
        if (criteria.suppGGuide != null) {
            if(criteria.suppGGuide == true)
                stringBuilder.append( " WHERE p3_4_5.SuppGGUIDE == " + 1);
            if(criteria.suppGGuide == false)
                stringBuilder.append( " WHERE p3_4_5.SuppGGUIDE == " + 0);
        }
        if (criteria.finalComment != null) {
            stringBuilder.append( " WHERE p3_4_5.FinComm2 == " + criteria.finalComment);
        }
        if (criteria.notes != null) {
            stringBuilder.append( " WHERE p3_4_5.NotesGUIDE == " + criteria.notes);
        }
        if (criteria.notesLowFree != null) {
            stringBuilder.append( " WHERE p3_4_5.NotesLowFreeGUIDE == " + criteria.notesLowFree);
        }
        if (criteria.notesLowFree2 != null) {
            stringBuilder.append( " WHERE p3_4_5.NotesLowFree2GUIDE == " + criteria.notesLowFree2);
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
        String[] temp;

        while (parameterNames.hasMoreElements()) 
        {
            String parameter = (String)parameterNames.nextElement();

            if (parameter.equalsIgnoreCase("id"))
            {
                temp = request.getParameterValues(parameter);
                criteria.id = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("itp"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.itp = true;
                else
                    criteria.itp = false;
            }
            if (parameter.equalsIgnoreCase("spanishSpeakingStaff"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spanishSpeakingStaff = true;
                else
                    criteria.spanishSpeakingStaff = false;
            }
            if (parameter.equalsIgnoreCase("name"))
            {
                temp = request.getParameterValues(parameter);
                criteria.name = temp[0];
            }
            if (parameter.equalsIgnoreCase("addressLine1"))
            {
                temp = request.getParameterValues(parameter);
                criteria.addressLine1 = temp[0];
            }
            if (parameter.equalsIgnoreCase("addressLine2"))
            {
                temp = request.getParameterValues(parameter);
                criteria.addressLine2 = temp[0];
            }
            if (parameter.equalsIgnoreCase("city"))
            {
                temp = request.getParameterValues(parameter);
                criteria.city = temp[0];
            }
            if (parameter.equalsIgnoreCase("county"))
            {
                temp = request.getParameterValues(parameter);
                criteria.county = temp[0];
            }
            if (parameter.equalsIgnoreCase("zip"))
            {
                temp = request.getParameterValues(parameter);
                criteria.zip = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("fax"))
            {
                temp = request.getParameterValues(parameter);
                criteria.fax = temp[0];
            }
            if (parameter.equalsIgnoreCase("website"))
            {
                temp = request.getParameterValues(parameter);
                criteria.website = temp[0];
            }
            if (parameter.equalsIgnoreCase("mainFacility"))
            {
                temp = request.getParameterValues(parameter);
                criteria.mainFacility = temp[0];
            }
            if (parameter.equalsIgnoreCase("branchRefNumber"))
            {
                temp = request.getParameterValues(parameter);
                criteria.branchRefNumber = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("open247"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.open247 = true;
                else
                    criteria.open247 = false;
            }
            //Is this what we're trying to do?
            if (parameter.equalsIgnoreCase("associatedFacilities"))
            {
                temp = request.getParameterValues(parameter);
                criteria.associatedFacilities.put(parameter, temp[0]);
            }
            //

            if (parameter.equalsIgnoreCase("intComm"))
            {
                temp = request.getParameterValues(parameter);
                criteria.intComm = temp[0];
            }
            if (parameter.equalsIgnoreCase("onCall"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.onCall = true;
                else
                    criteria.onCall = false;
            }

            //Is this what we're trying to do?
            if (parameter.equalsIgnoreCase("openTimes"))
            {
                temp = request.getParameterValues(parameter);
                criteria.openTimes.add(temp[0]);
            }
            if (parameter.equalsIgnoreCase("closeTimes"))
            {
                temp = request.getParameterValues(parameter);
                criteria.closeTimes.add(temp[0]);
            }
            if (parameter.equalsIgnoreCase("commentsOnTimes"))
            {
                temp = request.getParameterValues(parameter);
                criteria.commentsOnTimes.add(temp[0]);
            }
            //

            if (parameter.equalsIgnoreCase("spanishTimesComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanishTimesComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("hoursGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.hoursGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("walkIn"))
            {
                temp = request.getParameterValues(parameter);
                criteria.walkIn = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("walkInComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.walkInComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("apptGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.apptGuide = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("walkInGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.walkInGuide = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("parking"))
            {
                temp = request.getParameterValues(parameter);
                criteria.parking = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("parkingComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.parkingComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("publicTransportation"))
            {
                temp = request.getParameterValues(parameter);
                criteria.publicTransportation = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("publicTransportationGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.publicTransportationGuide = true;
                else
                    criteria.publicTransportationGuide = false;
            }
            if (parameter.equalsIgnoreCase("publicTransportationComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.publicTransportationComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("publicTransportationOther"))
            {
                temp = request.getParameterValues(parameter);
                criteria.publicTransportationOther = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("publicTransportationOtherComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.publicTransportationOtherComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("freeTransport"))
            {
                temp = request.getParameterValues(parameter);
                criteria.freeTransport = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanAdmin"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanAdmin = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanAdminGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanAdminGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanNurse"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanNurse = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanNurseGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanNurseGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanDoc"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanDoc = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanDocGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanDocGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanInterpreter"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanInterpreter = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanInterpreterGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanInterpreterGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanPhone"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanPhone = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanPhoneGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanPhoneGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanFo"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanFo = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanFoGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanFoGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("insurance"))
            {
                temp = request.getParameterValues(parameter);
                criteria.insurance = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("insuranceComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.insuranceComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("medicaid"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.medicaid = true;
                else
                    criteria.medicaid = false;
            }
            if (parameter.equalsIgnoreCase("peachCare"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.peachCare = true;
                else
                    criteria.peachCare = false;
            }
            if (parameter.equalsIgnoreCase("pay"))
            {
                temp = request.getParameterValues(parameter);
                criteria.pay = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("financialAssistance"))
            {
                temp = request.getParameterValues(parameter);
                criteria.financialAssistance = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("payPlanGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.payPlanGuide = true;
                else
                    criteria.payPlanGuide = false;
            }
            if (parameter.equalsIgnoreCase("SlideSc"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.SlideSc = true;
                else
                    criteria.SlideSc = false;
            }
            if (parameter.equalsIgnoreCase("financialAssistanceComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.financialAssistanceComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("finAssPh"))
            {
                temp = request.getParameterValues(parameter);
                criteria.finAssPh = temp[0];
            }
            if (parameter.equalsIgnoreCase("finAllPhComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.finAllPhComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("spcFCH"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcFCH = true;
                else
                    criteria.spcFCH = false;
            }
            if (parameter.equalsIgnoreCase("spcWH"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcWH = true;
                else
                    criteria.spcWH = false;
            }
            if (parameter.equalsIgnoreCase("spcMH"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcMH = true;
                else
                    criteria.spcMH = false;
            }
            if (parameter.equalsIgnoreCase("spcMHC"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcMHC = true;
                else
                    criteria.spcMHC = false;
            }
            if (parameter.equalsIgnoreCase("spcDH"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcDH = true;
                else
                    criteria.spcDH = false;
            }
            if (parameter.equalsIgnoreCase("spcVH"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcVH = true;
                else
                    criteria.spcVH = false;
            }
            if (parameter.equalsIgnoreCase("spcOT"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spcOT = true;
                else
                    criteria.spcOT = false;
            }
            if (parameter.equalsIgnoreCase("freeLow"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.freeLow = true;
                else
                    criteria.freeLow = false;
            }
            if (parameter.equalsIgnoreCase("spcComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spcComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("oteSpecial"))
            {
                temp = request.getParameterValues(parameter);
                criteria.oteSpecial = temp[0];
            }
            if (parameter.equalsIgnoreCase("spcDk"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spcDk = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("age"))
            {
                temp = request.getParameterValues(parameter);
                criteria.age = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("ageStart"))
            {
                temp = request.getParameterValues(parameter);
                criteria.ageStart = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("ageEnd"))
            {
                temp = request.getParameterValues(parameter);
                criteria.ageEnd = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("ageOTComment "))
            {
                temp = request.getParameterValues(parameter);
                criteria.ageOTComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("childGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.childGuide = true;
                else
                    criteria.childGuide = false;
            }
            if (parameter.equalsIgnoreCase("adolescentGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.adolescentGuide = true;
                else
                    criteria.adolescentGuide = false;
            }
            if (parameter.equalsIgnoreCase("adultGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.adultGuide = true;
                else
                    criteria.adultGuide = false;
            }
            if (parameter.equalsIgnoreCase("agesGuide"))
            {
                temp = request.getParameterValues(parameter);
                criteria.agesGuide = temp[0];
            }
            if (parameter.equalsIgnoreCase("otServ"))
            {
                temp = request.getParameterValues(parameter);
                criteria.otServ = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("hivTestGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.hivTestGuide = true;
                else
                    criteria.hivTestGuide = false;
            }
            if (parameter.equalsIgnoreCase("abortionGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.abortionGuide = true;
                else
                    criteria.abortionGuide = false;
            }
            if (parameter.equalsIgnoreCase("mhCount"))
            {
                temp = request.getParameterValues(parameter);
                criteria.mhCount = Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("mhCounSG"))
            {
                temp = request.getParameterValues(parameter);
                criteria.mhCounSG = temp[0];
            }
            if (parameter.equalsIgnoreCase("mhCounOT"))
            {
                temp = request.getParameterValues(parameter);
                criteria.mhCounOT = temp[0];
            }
            if (parameter.equalsIgnoreCase("subAbGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.subAbGuide = true;
                else
                    criteria.subAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("sexAbGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.sexAbGuide = true;
                else
                    criteria.sexAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("angManGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.angManGuide = true;
                else
                    criteria.angManGuide = false;
            }
            if (parameter.equalsIgnoreCase("hivConsGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.hivConsGuide = true;
                else
                    criteria.hivConsGuide = false;
            }
            if (parameter.equalsIgnoreCase("lgbtGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.lgbtGuide = true;
                else
                    criteria.lgbtGuide = false;
            }
            if (parameter.equalsIgnoreCase("suppGGuide"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.suppGGuide = true;
                else
                    criteria.suppGGuide = false;
            }
            if (parameter.equalsIgnoreCase("finalComment"))
            {
                temp = request.getParameterValues(parameter);
                criteria.finalComment = temp[0];
            }
            if (parameter.equalsIgnoreCase("notes"))
            {
                temp = request.getParameterValues(parameter);
                criteria.notes = temp[0];
            }
            if (parameter.equalsIgnoreCase("notesLowFree"))
            {
                temp = request.getParameterValues(parameter);
                criteria.notesLowFree = temp[0];
            }
            if(parameter.equalsIgnoreCase("notesLowFree2"))
            {
                temp = request.getParameterValues(parameter);
                criteria.notesLowFree2 = temp[0];
            }
        }
        return criteria ;
    }
}
