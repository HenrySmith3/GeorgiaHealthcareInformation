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
	        if(criteria.associatedFacilities.get(i)[0] != null)stringBuilder.append( " AND WHERE p1.OTFac" + i + 1 + " == " + criteria.associatedFacilities.get(i)[0]);
	        if(criteria.associatedFacilities.get(i)[1] != null)stringBuilder.append( " AND WHERE p1.OTFac" + i + 1 + "Ph == " + criteria.associatedFacilities.get(i)[1]);
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
        	stringBuilder.append( " AND WHERE p2." + day + "Comm == " + criteria.commentsOnTimes.get(k)));
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
