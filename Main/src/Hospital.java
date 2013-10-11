import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * This is a class which represents a single hospital or clinic.
 * This is used to return results from the SQL query, or as the criteria.
 * When used as a criteria, every field not being searched on is null, every non-null field is a requirement.
 */
public class Hospital {
    private final static int TRUE = 1;

    //page 1
    public int id;
    public Boolean itp;
    public Boolean spanishSpeakingStaff;
    public String name;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String county;
    public int zip;
    public String phone;
    public String fax;
    public String website;
    public String mainFacility;
    public int branchRefNumber;
    public Dictionary<String, String> associatedFacilities;//name then phone number.
    public String intComm;

    //page 2
    public Boolean open247;
    public Boolean onCall;//is someone on call at all times?
    public List<String> openTimes;//Sunday first.
    public List<String> closeTimes;
    public List<String> commentsOnTimes;
    public String spanishTimesComment;//this is poorly named.
    public String hoursGuide;

    //page 3
    public int walkIn;//this needs an enum.
    public String walkInComment;
    public int apptGuide;//what does this mean?
    public int walkInGuide;//what does this mean?
    public int parking;
    public String parkingComment;
    public int publicTransportation;
    public Boolean publicTransportationGuide;//what does this mean?
    public String publicTransportationComment;
    public int publicTransportationOther;
    public String publicTransportationOtherComment;
    public String freeTransport;//not sure about this.
    public int spanAdmin;
    public String spanAdminGuide;
    public int spanNurse;
    public String spanNurseGuide;
    public int spanDoc;
    public String spanDocGuide;
    public int spanInterpreter;
    public String spanInterpreterGuide;
    public int spanPhone;
    public String spanPhoneGuide;
    public int spanFo;//don't know what this is
    public String spanFoGuide;
    public int insurance;
    public String insuranceComment;
    public Boolean medicare;
    public Boolean medicaid;
    public Boolean peachCare;
    public int pay;
    public int financialAssistance;
    public Boolean payPlanGuide;
    public Boolean SlideSc;//no idea what this is.
    public String financialAssistanceComment;
    public String finAssPh;
    public String finAllPhComment;
    public Boolean spcFCH;
    public Boolean spcWH;
    public Boolean spcMH;
    public Boolean spcMHC;
    public Boolean spcDH;
    public Boolean spcVH;
    public Boolean spcOT;
    public Boolean freeLow;
    public String spcComment;
    public String oteSpecial;
    public int spcDk;//what is this?
    public int age;
    public int ageStart;
    public int ageEnd;
    public String ageOTComment;
    public Boolean childGuide;
    public Boolean adolescentGuide;
    public Boolean adultGuide;
    public String agesGuide;
    public int otServ;
    public Boolean hivTestGuide;
    public Boolean abortionGuide;
    public int mhCount;
    public String mhCounSG;
    public String mhCounOT;
    public Boolean subAbGuide;
    public Boolean sexAbGuide;
    public Boolean angManGuide;
    public Boolean hivConsGuide;
    public Boolean lgbtGuide;
    public Boolean suppGGuide;
    public String finalComment;
    public String notes;
    public String notesLowFree;
    public String notesLowFree2;

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public static Hospital getHospitalFromResultSet(ResultSet resultSet) throws SQLException {
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
