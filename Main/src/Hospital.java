import java.lang.reflect.Field;
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
    public String mainFacility;
    public int branchRefNumber;
    public Dictionary<String, String> associatedFacilities;//name then phone number.
    public String intComm;

    //page 2
    public boolean open247;
    public boolean onCall;//is someone on call at all times?
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
    public boolean publicTransportationGuide;//what does this mean?
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
    public boolean medicare;
    public boolean medicaid;
    public boolean peachCare;
    public int pay;
    public int financialAssistance;
    public boolean payPlanGuide;
    public boolean SlideSc;//no idea what this is.
    public String financialAssistanceComment;
    public String finAssPh;
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
    public int spcDk;//what is this?
    public int age;
    public int ageStart;
    public int ageEnd;
    public String ageOTComment;
    public boolean childGuide;
    public boolean adolescentGuide;
    public boolean adultGuide;
    public String agesGuide;
    public int otServ;
    public boolean hivTestGuide;
    public boolean abortionGuide;
    public int mhCount;
    public String mhCounSG;
    public String mhCounOT;
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
}
