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

    private final int TRUE = 1;
    private final int FALSE = 0;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("test");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/georgiahealthcareinformation", "root", "");
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(getBasicSelectStatement());
            while (resultset.next()) {
                Hospital hospital = getHospitalFromResultSet(resultset);
                writer.append(hospital.toString() + "\n");
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

    private Hospital getHospitalFromResultSet(ResultSet resultSet) throws SQLException{
        Hospital hospital = new Hospital();
        //page 1
        hospital.id = resultSet.getInt("ID");
        hospital.itp = resultSet.getInt("ITP") == TRUE;
        hospital.spanishSpeakingStaff = resultSet.getInt("SPAN") == TRUE;
        hospital.name = resultSet.getString("NameFac");
        hospital.addressLine1 = resultSet.getString("AddFacL1");
        hospital.addressLine2 = resultSet.getString("AddFacL2");
        hospital.city = resultSet.getString("City");
        hospital.county = resultSet.getString("County");
        hospital.zip = resultSet.getInt("ZIPCode");
        hospital.phone = resultSet.getString("Phone");
        hospital.fax = resultSet.getString("Fax");
        hospital.website = resultSet.getString("Website");
        hospital.mainFacility = resultSet.getString("BraONL");
        hospital.branchRefNumber = resultSet.getInt("BraRefNo");
        Dictionary<String, String> associatedFacilities = new Hashtable<String, String>();
        for (int i = 1; i <= 10; i++) {
            String associatedFacilityName = resultSet.getString("OTFac" + i);
            if (associatedFacilityName != null) {
                String associatedFacilityNumber = resultSet.getString("OTFac" + i + "Ph");
                if (associatedFacilityNumber == null) {
                    associatedFacilityNumber = "";
                }
                associatedFacilities.put(associatedFacilityName, associatedFacilityNumber);
            }
        }
        hospital.associatedFacilities = associatedFacilities;//name then phone number.
        hospital.intComm = resultSet.getString("IntComm");

        //page 2
        hospital.open247 = resultSet.getInt("Op247") == TRUE;
        hospital.onCall = resultSet.getInt("OnCall") == TRUE;//is someone on call at all times?
        List<String> openTimes = new ArrayList<String>();
        List<String> closeTimes = new ArrayList<String>();
        List<String> commentsOnTimes = new ArrayList<String>();
        for (String day : Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
            openTimes.add(resultSet.getString(day + "Op"));
            closeTimes.add(resultSet.getString(day + "Cl"));
            commentsOnTimes.add(resultSet.getString(day + "Comm"));
        }
        hospital.openTimes = openTimes;//Sunday first.
        hospital.closeTimes = closeTimes;
        hospital.commentsOnTimes = commentsOnTimes;
        hospital.spanishTimesComment = resultSet.getString("HOpComm");
        hospital.hoursGuide = resultSet.getString("HoursGuide");

        //page 3
        hospital.walkIn = resultSet.getInt("AppWalk");//this needs an enum.
        hospital.walkInComment = resultSet.getString("AppWalkSp");
        hospital.apptGuide = resultSet.getInt("ApptGUIDE");//what does this mean?
        hospital.walkInGuide = resultSet.getInt("Walk-inGUIDE");//what does this mean?
        hospital.parking = resultSet.getInt("park");
        hospital.parkingComment = resultSet.getString("ParkSP");
        hospital.publicTransportation = resultSet.getInt("PubTr");
        hospital.publicTransportationGuide = resultSet.getInt("ParkGTGUIDE") == TRUE;//what does this mean?
        hospital.publicTransportationComment = resultSet.getString("PubTrSp");
        hospital.publicTransportationOther = resultSet.getInt("PubTrOt");
        hospital.publicTransportationOtherComment = resultSet.getString("PubTrOtSp");
        hospital.freeTransport = resultSet.getString("Transportegratis");//not sure about this.
        hospital.spanAdmin = resultSet.getInt("SPANAdm");
        hospital.spanAdminGuide = resultSet.getString("SPANAdmGUIDE");
        hospital.spanNurse = resultSet.getInt("SpanNur");
        hospital.spanNurseGuide = resultSet.getString("SpanNurGUIDE");
        hospital.spanDoc = resultSet.getInt("SpanDoc");
        hospital.spanDocGuide = resultSet.getString("SpanDocGUIDE");
        hospital.spanInterpreter = resultSet.getInt("SpanIntON");
        hospital.spanInterpreterGuide = resultSet.getString("SpanIntONGUIDE");
        hospital.spanPhone = resultSet.getInt("SpanIntPh");
        hospital.spanPhoneGuide = resultSet.getString("SpanIntPhGUIDE");
        hospital.spanFo = resultSet.getInt("SpanFo");
        hospital.spanFoGuide = resultSet.getString("SpanFoGUIDE");
        hospital.insurance = resultSet.getInt("Insur");
        hospital.insuranceComment = resultSet.getString("InsurSp");
        hospital.medicare = resultSet.getInt("MedicareGUIDE") == TRUE;
        hospital.medicaid = resultSet.getInt("MedicaidGUIDE") == TRUE;
        hospital.peachCare = resultSet.getInt("PeachcareGUIDE") == TRUE;
        hospital.pay = resultSet.getInt("Pay");
        hospital.financialAssistance = resultSet.getInt("FinAss");
        hospital.payPlanGuide = resultSet.getInt("PayPlanGUIDE") == TRUE;
        hospital.SlideSc = resultSet.getInt("SlideScGUIDE") == TRUE;//no idea what this is.
        hospital.financialAssistanceComment = resultSet.getString("FinAssSp");
        hospital.finAssPh = resultSet.getString("FinAssPh");
        hospital.finAllPhComment = resultSet.getString("FinAssPhSp");
        hospital.spcFCH = resultSet.getInt("SpcFCH") == TRUE;
        hospital.spcWH = resultSet.getInt("SpcWH") == TRUE;
        hospital.spcMH = resultSet.getInt("SpcMH") == TRUE;
        hospital.spcMHC = resultSet.getInt("SpcMHC") == TRUE;
        hospital.spcDH = resultSet.getInt("SpcDH") == TRUE;
        hospital.spcVH = resultSet.getInt("SpcVH") == TRUE;
        hospital.spcOT = resultSet.getInt("SpcOT") == TRUE;
        hospital.freeLow = resultSet.getInt("FreeLow") == TRUE;
        hospital.spcComment = resultSet.getString("SpcSp");
        hospital.oteSpecial = resultSet.getString("OTEspecial");
        hospital.spcDk = resultSet.getInt("SpcDk");//what is this?
        hospital.age = resultSet.getInt("Age");
        hospital.ageStart = resultSet.getInt("AgeStart");
        hospital.ageEnd = resultSet.getInt("AgeStart");
        hospital.ageOTComment = resultSet.getString("AgeOTSp");
        hospital.childGuide = resultSet.getInt("NinosGUIDE") == TRUE;
        hospital.adolescentGuide = resultSet.getInt("AdolescGUIDE") == TRUE;
        hospital.adultGuide = resultSet.getInt("AdultGUIDE") == TRUE;
        hospital.agesGuide = resultSet.getString("EdadesGUIDE");
        hospital.otServ = resultSet.getInt("OTServ");
        hospital.hivTestGuide = resultSet.getInt("HIVTestGUIDE") == TRUE;
        hospital.abortionGuide = resultSet.getInt("AbortionGUIDE") == TRUE;
        hospital.mhCount = resultSet.getInt("MHCoun");
        hospital.mhCounSG = resultSet.getString("MHCounSG");
        hospital.mhCounOT = resultSet.getString("MHCounOT");
        hospital.subAbGuide = resultSet.getInt("SubAbGUIDE") == TRUE;
        hospital.sexAbGuide = resultSet.getInt("SexAbGUIDE") == TRUE;
        hospital.angManGuide = resultSet.getInt("AngManGUIDE") == TRUE;
        hospital.hivConsGuide = resultSet.getInt("HIVConsGUIDE") == TRUE;
        hospital.lgbtGuide = resultSet.getInt("LGBTGUIDE") == TRUE;
        hospital.suppGGuide = resultSet.getInt("SuppGGUIDE") == TRUE;
        hospital.finalComment = resultSet.getString("FinComm2");
        hospital.notes = resultSet.getString("NotesGUIDE");
        hospital.notesLowFree = resultSet.getString("NotesLowFreeGUIDE");
        hospital.notesLowFree2 = resultSet.getString("NotesLowFree2GUIDE");
        return hospital;
    }
}
