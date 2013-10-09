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
    private int id;
    private boolean itp;
    private boolean spanishSpeakingStaff;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county;
    private int zip;
    private String phone;
    private String fax;
    private String website;
    private boolean mainFacility;
    private int branchRefNumber;
    private Dictionary<String, String> associatedFacilities;//name then phone number.

    //page 2
    private boolean open247;
    private boolean onCall;//is someone on call at all times?
    private List<GregorianCalendar> openTimes;//Sunday first.
    private List<GregorianCalendar> closeTimes;
    private List<String> commentsOnTimes;
}
