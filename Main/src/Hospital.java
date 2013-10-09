import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 10/9/13
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Hospital {
    //page 1
    public int id;
    public boolean itp;
    public boolean spanishSpeakingStaff;
    public String name;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String county;
    public int zip;
    public String phone;
    public String fax;
    public String website;
    public boolean mainFacility;
    public int branchRefNumber;
    public Dictionary<String, String> associatedFacilities;//name then phone number.

    //page 2
    public boolean open247;
    public boolean onCall;//is someone on call at all times?
    public List<GregorianCalendar> openTimes;//Sunday first.
    public List<GregorianCalendar> closeTimes;
    public List<String> commentsOnTimes;
    public String spanishHours;
    public String hoursGuide;

    //page 3
    public int walkIn;//this needs an enum.
    public String walkInComment;
    public int apptGuide;//what does this mean?
    public int walkInGuide;//what does this mean?
    public int parking;
    public String parkingComment;
    public int publicTransportation;
    public int publicTransportationGuide;//what does this mean?
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
    public int insurance;
    public String insuranceComment;
    public int medicare;
    public int medicaid;
    public int peachCare;
    public int pay;
    public int financialAssistance;
    public boolean payPlanGuide;
    public boolean SlideSc;//no idea what this is.
    public String financialAssistanceComment;
    public int finAssPh;
    public String finAllPhComment;
    public boolean spcFCH;
    public boolean spcWH;
    public boolean spcMH;
    public boolean spcMHC;
    public boolean spcDH;
    public boolean spcVH;
    public boolean spcOT;
    public boolean freeLow;
    public String spcComment;
    public String oteSpecial;
    public int SpcDk;//what is this?
    public int age;
    public int ageStart;
    public int ageEnd;
    public int ageOTComment;
    public boolean childGuide;
    public boolean adolescentGuide;
    public boolean adultGuide;
    public String agesGuide;
    public int otServ;
    public boolean hivTestGuide;
    public boolean abortionGuide;
    public int mhCount;
    public int mhCounSG;
    public int mhCounOT;
    public boolean subAbGuide;
    public boolean sexAbGuide;
    public boolean angManGuide;
    public boolean hivConsGuide;
    public boolean lgbtGuide;
    public boolean suppGGuide;
    public String finalComment;
    public String notes;
    public String notesLowFree;
    public String notesLowFree2;
}
