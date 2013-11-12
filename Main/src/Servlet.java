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
        if(criteria.id != -1){
            stringBuilder.append( "P1.ID = " + criteria.id + " AND ");
        }
        if(criteria.itp != null){
            stringBuilder.append( "P1.ITP = " + criteria.itp + " AND ");
        }
        if(criteria.spanishSpeakingStaff != null){
            if(criteria.spanishSpeakingStaff == true)
        	    stringBuilder.append( "P1.SPAN = " + 1 + " AND ");
            else if(criteria.spanishSpeakingStaff == false)
        	    stringBuilder.append( "P1.SPAN = " + 2 + " AND ");
        }
        if(criteria.name != null){
            stringBuilder.append( "P1.NameFac = " + criteria.name + " AND ");
        }
        if(criteria.addressLine1 != null){
            stringBuilder.append( "P1.AddFacL1 = " + criteria.addressLine1 + " AND ");
        }
        if(criteria.addressLine2 != null){
            stringBuilder.append( "P1.AddFacL2 = " + criteria.addressLine2 + " AND ");
        }
        if(criteria.city != null){
            stringBuilder.append( "P1.City = " + criteria.city + " AND ");
        }
        if(criteria.county != null){
            stringBuilder.append( "P1.County = " + criteria.county + " AND ");
        }
        if(criteria.zip != -1){
            stringBuilder.append( "P1.ZIPCode = " + criteria.zip + " AND ");
        }
        if(criteria.phone != null){
            stringBuilder.append( "P1.Phone = " + criteria.phone + " AND ");
        }
        if(criteria.fax != null){
            stringBuilder.append( "P1.Fax = " + criteria.fax + " AND ");
        }
        if(criteria.website != null){
            stringBuilder.append( "P1.Website = " + criteria.website + " AND ");
        }
        if(criteria.mainFacility != null){
            stringBuilder.append( "P1.BraONL = " + criteria.mainFacility + " AND ");
        }
        if(criteria.branchRefNumber != -1){
            stringBuilder.append( "P1.BraRefNo = " + criteria.branchRefNumber + " AND ");
        }
        if(criteria.associatedFacilities != null){
	    for(int i = 0; i < criteria.associatedFacilities.size(); i++){
	        if(criteria.associatedFacilities.get(i)[0] != null)stringBuilder.append( "P1.OTFac" + i + 1 + " = " + criteria.associatedFacilities.get(i)[0]);
	        if(criteria.associatedFacilities.get(i)[1] != null)stringBuilder.append( "P1.OTFac" + i + 1 + "Ph = " + criteria.associatedFacilities.get(i)[1]);
	    }      	
        }
        if(criteria.intComm != null){
            stringBuilder.append( "P1.IntComm = " + criteria.intComm + " AND ");
        }
        
        //page2
        if(criteria.open247 != null){
            if(criteria.open247 == true)
                stringBuilder.append( "P2.Op247 = " + 1 + " AND ");
            else if(criteria.spanishSpeakingStaff == false)
               	stringBuilder.append( "P2.Op247 = " + 0 + " AND ");
        }
        if(criteria.onCall != null){
            if(criteria.open247 == true)
         	stringBuilder.append( "P2.OnCall = " + 1 + " AND ");
            else if(criteria.spanishSpeakingStaff == false)
        	stringBuilder.append( "P2.OnCall = " + 0 + " AND ");
        }
        if(criteria.openTimes != null){
            int k = 0;
            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
        	stringBuilder.append( "P2." + day + "Op = " + criteria.openTimes.get(k) + " AND ");
        	k++;
            }
        }
        if(criteria.closeTimes != null){
            int k = 0;
            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
        	stringBuilder.append( "P2." + day + "Cl = " + criteria.closeTimes.get(k) + " AND ");
        	k++;
            }
        }
        if(criteria.commentsOnTimes != null){
            int k = 0;
            for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
        	stringBuilder.append( "P2." + day + "Comm = " + criteria.commentsOnTimes.get(k) + " AND ");
        	k++;
            }
        }
        if(criteria.spanishTimesComment != null){
            stringBuilder.append( "P2.HOpComm = " + criteria.spanishTimesComment + " AND ");
        }
        if(criteria.hoursGuide != null){
            stringBuilder.append( "P2.HoursGuide = " + criteria.hoursGuide + " AND ");
        }
        
        //page3_4_5
        if(criteria.walkIn != -1){
            stringBuilder.append( "P3_4_5.AppWalk = " + criteria.walkIn + " AND ");
        }
        if(criteria.walkInComment != null){
            stringBuilder.append( "P3_4_5.AppWalkSp = " + criteria.walkInComment + " AND ");
        }
        if(criteria.apptGuide != -1){
            stringBuilder.append( "P3_4_5.ApptGUIDE = " + criteria.apptGuide + " AND ");
        }
        if(criteria.walkInGuide != -1){
            stringBuilder.append( "P3_4_5.Walk-inGUIDE = " + criteria.walkInGuide + " AND ");
        }
        if(criteria.parking != -1){
            stringBuilder.append( "P3_4_5.park = " + criteria.parking + " AND ");
        }
        if(criteria.parkingComment != null){
            stringBuilder.append( "P3_4_5.ParkSP = " + criteria.parkingComment + " AND ");
        }
        if(criteria.publicTransportation != -1){
            stringBuilder.append( "P3_4_5.PubTr = " + criteria.publicTransportation + " AND ");
        }
        if(criteria.publicTransportationGuide != null){
            if(criteria.publicTransportationGuide == true)
        	stringBuilder.append( "P3_4_5.ParkGTGUIDE = " + 1 + " AND ");
            else if(criteria.publicTransportationGuide == false)
        	stringBuilder.append( "P3_4_5.ParkGTGUIDE = " + 0 + " AND ");
        }
        if(criteria.publicTransportationComment != null){
            stringBuilder.append( "P3_4_5.PubTrSp = " + criteria.publicTransportationComment + " AND ");
        }
        if (criteria.publicTransportationOther != -1){
            stringBuilder.append( "P3_4_5.PubTrOt = " + criteria.publicTransportationOther + " AND ");
        }
        if (criteria.publicTransportationOtherComment != null){
            stringBuilder.append( "P3_4_5.PubTrOtSp = " + criteria.publicTransportationOtherComment + " AND ");
        }
        if (criteria.freeTransport != null){
            stringBuilder.append( "P3_4_5.Transportegratis = " + criteria.freeTransport + " AND ");
        }
        if (criteria.spanAdmin != -1) {
            stringBuilder.append( "P3_4_5.SPANAdm = " + criteria.spanAdmin + " AND ");
        }
        if (criteria.spanAdminGuide != null) {
            stringBuilder.append( "P3_4_5.SPANAdmGUIDE = " + criteria.spanAdminGuide + " AND ");
        }
        if (criteria.spanNurse != -1) {
            stringBuilder.append( "P3_4_5.SpanNur = " + criteria.spanNurse + " AND ");
        }
        if (criteria.spanNurseGuide != null) {
            stringBuilder.append( "P3_4_5.SpanNurGUIDE = " + criteria.spanNurseGuide + " AND ");
        }
        if (criteria.spanDoc != -1) {
            stringBuilder.append( "P3_4_5.SpanDoc = " + criteria.spanDoc + " AND ");
        }
        if (criteria.spanDocGuide != null) {
            stringBuilder.append( "P3_4_5.SpanDocGUIDE = " + criteria.spanDocGuide + " AND ");
        }
        if (criteria.spanInterpreter != -1) {
            stringBuilder.append( "P3_4_5.SpanIntON = " + criteria.spanInterpreter + " AND ");
        }
        if (criteria.spanInterpreterGuide != null) {
            stringBuilder.append( "P3_4_5.SpanIntONGUIDE = " + criteria.spanInterpreterGuide + " AND ");
        }
        if (criteria.spanPhone != -1) {
            stringBuilder.append( "P3_4_5.SpanIntPh = " + criteria.spanPhone + " AND ");
        }
        if (criteria.spanPhoneGuide != null) {
            stringBuilder.append( "P3_4_5.SpanIntPhGUIDE = " + criteria.spanPhoneGuide + " AND ");
        }
        if (criteria.spanFo != -1) {
            stringBuilder.append( "P3_4_5.SpanFo = " + criteria.spanFo + " AND ");
        }
        if (criteria.spanFoGuide != null) {
            stringBuilder.append( "P3_4_5.SpanFoGUIDE = " + criteria.spanFoGuide + " AND ");
        }
        if (criteria.insurance != -1) {
            stringBuilder.append( "P3_4_5.Insur = " + criteria.insurance + " AND ");
        }
        if (criteria.insuranceComment != null) {
            stringBuilder.append( "P3_4_5.InsurSp = " + criteria.insuranceComment + " AND ");
        }
        if (criteria.medicare != null) {
            if(criteria.medicare == true)
                stringBuilder.append( "P3_4_5.MedicareGUIDE = " + 1 + " AND ");
            if(criteria.medicare == false)
                stringBuilder.append( "P3_4_5.MedicareGUIDE = " + 0 + " AND ");
        }
        if (criteria.medicaid != null) {
            if(criteria.medicaid == true)
                stringBuilder.append( "P3_4_5.MedicaidGUIDE = " + 1 + " AND ");
            if(criteria.medicare == false)
                stringBuilder.append( "P3_4_5.MedicaidGUIDE = " + 0 + " AND ");
        }
        if (criteria.peachCare != null) {
            if(criteria.peachCare == true)
                stringBuilder.append( "P3_4_5.PeachcareGUIDE = " + 1 + " AND ");
            if(criteria.peachCare == false)
                stringBuilder.append( "P3_4_5.PeachcareGUIDE = " + 0 + " AND ");
        }
        if (criteria.pay != -1) {
            stringBuilder.append( "P3_4_5.Pay = " + criteria.pay + " AND ");
        }
        if (criteria.financialAssistance != -1) {
            stringBuilder.append( "P3_4_5.FinAss = " + criteria.financialAssistance + " AND ");
        }
        if (criteria.payPlanGuide != null) {
            if(criteria.payPlanGuide == true)
                stringBuilder.append( "P3_4_5.PayPlanGUIDE = " + 1 + " AND ");
            if(criteria.payPlanGuide == false)
                stringBuilder.append( "P3_4_5.PayPlanGUIDE = " + 0 + " AND ");
        }
        if (criteria.SlideSc != null) {
            if(criteria.SlideSc == true)
                stringBuilder.append( "P3_4_5.SlideScGUIDE = " + 1 + " AND ");
            if(criteria.SlideSc == false)
                stringBuilder.append( "P3_4_5.SlideScGUIDE = " + 0 + " AND ");
        }
        if (criteria.financialAssistanceComment != null) {
            stringBuilder.append( "P3_4_5.FinAssSp = " + criteria.financialAssistanceComment + " AND ");
        }
        if (criteria.finAssPh != null) {
            stringBuilder.append( "P3_4_5.FinAssPh = " + criteria.finAssPh + " AND ");
        }
        if (criteria.finAllPhComment != null) {
            stringBuilder.append( "P3_4_5.FinAssPhSp = " + criteria.finAllPhComment + " AND ");
        }
        if (criteria.spcFCH != null) {
            if(criteria.spcFCH == true)
                stringBuilder.append( "P3_4_5.SpcFCH = " + 1 + " AND ");
            if(criteria.spcFCH == false)
                stringBuilder.append( "P3_4_5.SpcFCH = " + 0 + " AND ");
        }
        if (criteria.spcWH != null) {
            if(criteria.spcWH == true)
                stringBuilder.append( "P3_4_5.SpcWH = " + 1 + " AND ");
            if(criteria.spcWH == false)
                stringBuilder.append( "P3_4_5.SpcWH = " + 0 + " AND ");
        }
        if (criteria.spcMH != null) {
            if(criteria.spcMH == true)
                stringBuilder.append( "P3_4_5.SpcMH = " + 1 + " AND ");
            if(criteria.spcMH == false)
                stringBuilder.append( "P3_4_5.SpcMH = " + 0 + " AND ");
        }
        if (criteria.spcMHC != null) {
            if(criteria.spcMHC == true)
                stringBuilder.append( "P3_4_5.SpcMHC = " + 1 + " AND ");
            if(criteria.spcMHC == false)
                stringBuilder.append( "P3_4_5.SpcMHC = " + 0 + " AND ");
        }
        if (criteria.spcDH != null) {
            if(criteria.spcDH == true)
                stringBuilder.append( "P3_4_5.SpcDH = " + 1 + " AND ");
            if(criteria.spcDH == false)
                stringBuilder.append( "P3_4_5.SpcDH = " + 0 + " AND ");
        }
        if (criteria.spcVH != null) {
            if(criteria.spcVH == true)
                stringBuilder.append( "P3_4_5.SpcVH = " + 1 + " AND ");
       	    if(criteria.spcVH == false)
                stringBuilder.append( "P3_4_5.SpcVH = " + 0 + " AND ");
        }
        if (criteria.spcOT != null) {
            if(criteria.spcOT == true)
                stringBuilder.append( "P3_4_5.SpcOT = " + 1 + " AND ");
            if(criteria.spcOT == false)
                stringBuilder.append( "P3_4_5.SpcOT = " + 0 + " AND ");
        }
        if (criteria.freeLow != null) {
            if(criteria.freeLow == true)
                stringBuilder.append( "P3_4_5.FreeLow = " + 1 + " AND ");
            if(criteria.freeLow == false)
                stringBuilder.append( "P3_4_5.FreeLow = " + 0 + " AND ");
        }
        if (criteria.spcComment != null) {
            stringBuilder.append( "P3_4_5.SpcSp = " + criteria.spcComment + " AND ");
        }
        if (criteria.oteSpecial != null) {
            stringBuilder.append( "P3_4_5.OTEspecial = " + criteria.oteSpecial + " AND ");
        }
        if (criteria.spcDk != -1) {
            stringBuilder.append( "P3_4_5.SpcDk = " + criteria.spcDk + " AND ");
        }
        if (criteria.age != -1) {
            stringBuilder.append( "P3_4_5.Age = " + criteria.age + " AND ");
        }
        if (criteria.ageStart != -1) {
            stringBuilder.append( "P3_4_5.AgeStart = " + criteria.ageStart + " AND ");
        }
        if (criteria.ageEnd != -1) {
            stringBuilder.append( "P3_4_5.AgeEnd = " + criteria.ageEnd + " AND ");
        }
        if (criteria.ageOTComment != null) {
            stringBuilder.append( "P3_4_5.AgeOTSp = " + criteria.ageOTComment + " AND ");
        }
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
        if (criteria.otServ != -1) {
            stringBuilder.append( "P3_4_5.OTServ = " + criteria.otServ + " AND ");
        }
        if (criteria.hivTestGuide != null) {
            if(criteria.hivTestGuide == true)
                stringBuilder.append( "P3_4_5.HIVTestGUIDE = " + 1 + " AND ");
            if(criteria.hivTestGuide == false)
                stringBuilder.append( "P3_4_5.HIVTestGUIDE = " + 0 + " AND ");
        }
        if (criteria.abortionGuide != null) {
            if(criteria.abortionGuide == true)
                stringBuilder.append( "P3_4_5.AbortionGUIDE = " + 1 + " AND ");
            if(criteria.abortionGuide == false)
                stringBuilder.append( "P3_4_5.AbortionGUIDE = " + 0 + " AND ");
        }
        if (criteria.mhCount != -1) {
            stringBuilder.append( "P3_4_5.MHCoun = " + criteria.mhCount + " AND ");
        }
        if (criteria.mhCounSG != null) {
            stringBuilder.append( "P3_4_5.MHCounSG = " + criteria.mhCounSG + " AND ");
        }
        if (criteria.mhCounOT != null) {
            stringBuilder.append( "P3_4_5.MHCounOT = " + criteria.mhCounOT + " AND ");
        }
        if (criteria.subAbGuide != null) {
            if(criteria.subAbGuide == true)
                stringBuilder.append( "P3_4_5.SubAbGuide = " + 1 + " AND ");
            if(criteria.subAbGuide == false)
                stringBuilder.append( "P3_4_5.SubAbGuide = " + 0 + " AND ");
        }
        if (criteria.sexAbGuide != null) {
            if(criteria.sexAbGuide == true)
                stringBuilder.append( "P3_4_5.SexAbGuide = " + 1 + " AND ");
            if(criteria.sexAbGuide == false)
                stringBuilder.append( "P3_4_5.SexAbGuide = " + 0 + " AND ");
        }
        if (criteria.angManGuide != null) {
            if(criteria.angManGuide == true)
                stringBuilder.append( "P3_4_5.AngManGuide = " + 1 + " AND ");
            if(criteria.angManGuide == false)
                stringBuilder.append( "P3_4_5.AngManGuide = " + 0 + " AND ");
        }
        if (criteria.hivConsGuide != null) {
            if(criteria.hivConsGuide == true)
                stringBuilder.append( "P3_4_5.HIVConsGUIDE = " + 1 + " AND ");
            if(criteria.hivConsGuide == false)
                stringBuilder.append( " P3_4_5.HIVConsGUIDE = " + 0 + " AND ");
        }
        if (criteria.lgbtGuide != null) {
            if(criteria.lgbtGuide == true)
                stringBuilder.append( "P3_4_5.LGBTGUIDE = " + 1 + " AND ");
            if(criteria.lgbtGuide == false)
                stringBuilder.append( "P3_4_5.LGBTGUIDE = " + 0 + " AND ");
        }
        if (criteria.suppGGuide != null) {
            if(criteria.suppGGuide == true)
                stringBuilder.append( "P3_4_5.SuppGGUIDE = " + 1 + " AND ");
            if(criteria.suppGGuide == false)
                stringBuilder.append( "P3_4_5.SuppGGUIDE = " + 0 + " AND ");
        }
        if (criteria.finalComment != null) {
            stringBuilder.append( "P3_4_5.FinComm2 = " + criteria.finalComment + " AND ");
        }
        if (criteria.notes != null) {
            stringBuilder.append( "P3_4_5.NotesGUIDE = " + criteria.notes + " AND ");
        }
        if (criteria.notesLowFree != null) {
            stringBuilder.append( "P3_4_5.NotesLowFreeGUIDE = " + criteria.notesLowFree + " AND ");
        }
        if (criteria.notesLowFree2 != null) {
            stringBuilder.append( "P3_4_5.NotesLowFree2GUIDE = " + criteria.notesLowFree2 + " AND ");
        }
        
        String ss = stringBuilder.toString();
        ss = "WHERE " + ss.substring(0, ss.length() - 5);
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
        String[] temp;

        while (parameterNames.hasMoreElements()) 
        {
            String parameter = (String)parameterNames.nextElement();

            if (parameter.equalsIgnoreCase("county"))
            {
                temp = request.getParameterValues(parameter);
                criteria.county = temp[0];
            }
            if (parameter.equalsIgnoreCase("spanishSpeakingStaff"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.spanishSpeakingStaff = true;
                else
                    criteria.spanishSpeakingStaff = false;
            }
            if (parameter.equalsIgnoreCase("onCall"))
            {
                temp = request.getParameterValues(parameter);
                if(temp[0].equalsIgnoreCase("True"))
                    criteria.onCall = true;
                else
                    criteria.onCall = false;
            }

            if (parameter.equalsIgnoreCase("walkIn"))
            {
                temp = request.getParameterValues(parameter);
                criteria.walkIn = (temp == null)? -1 : Integer.parseInt(temp[0]);
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
                criteria.parking = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("publicTransportation"))
            {
                temp = request.getParameterValues(parameter);
                criteria.publicTransportation = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanNurse"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanNurse = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanDoc"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanDoc = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanInterpreter"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanInterpreter = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("spanPhone"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spanPhone = (temp == null)? -1 : Integer.parseInt(temp[0]);
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
            if (parameter.equalsIgnoreCase("spcDk"))
            {
                temp = request.getParameterValues(parameter);
                criteria.spcDk = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("age"))
            {
                temp = request.getParameterValues(parameter);
                criteria.age = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("ageStart"))
            {
                temp = request.getParameterValues(parameter);
                criteria.ageStart = (temp == null)? -1 : Integer.parseInt(temp[0]);
            }
            if (parameter.equalsIgnoreCase("ageEnd"))
            {
                temp = request.getParameterValues(parameter);
                criteria.ageEnd = (temp == null)? -1 : Integer.parseInt(temp[0]);
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
                criteria.otServ = (temp == null)? -1 : Integer.parseInt(temp[0]);
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
        }
        return criteria ;
    }
}
