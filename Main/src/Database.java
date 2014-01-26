import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 1/25/14
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Database {
    static String getIDLookupQuery() {
        return "SELECT p1.NameFac, p1.SurvNo from p1";
    }

    static String getBugLookup() {
        return "SELECT bug_report.SurvNo, bug_report.bug, bug_report.bugDesc from bug_report";
    }

    /**
     * Gets the query that will be used to query the database.
     * @param criteria All non-null values in this hospital will be used as the criteria.
     * @param inCounty If true, returns results in the users county, if false, returns all results.
     * @return A query string to be executed.
     */
    static String getQuery(Hospital criteria, boolean inCounty) {
        StringBuilder builder = new StringBuilder();
        //get the basic statement, which is just a select all.
        builder.append(getBasicSelectStatement());
        //add in the where clauses so that only the hospitals with fields matching the criteria are returned.
        builder.append(getWhereClauses(criteria, inCounty));

        return builder.toString();
    }

    /**
     * Returns the where clauses for the hospital.
     * For each non-null value in hospital, this method adds a SQL where clause to restrict
     * the results returned to only those that match the value in hospital.
     * @param criteria The hospital criteria. All values that we aren't looking to match should be null.
     * @param inCounty If true, returns results in the users county, if false, returns all results.
     * @return A string which is appended to the basic sql statement.
     */
    private static String getWhereClauses(Hospital criteria, boolean inCounty) {
        StringBuilder stringBuilder = new StringBuilder();

        //Personal Information
        if(inCounty && criteria.county != null && !criteria.county.equals("")){
            stringBuilder.append( "P1.County = '" + criteria.county + "' AND ");
        }

        if(criteria.publicTransportation != null){
            //TODO how is this formatted in the database? Are we still even doing this?
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

        if(Hospital.TRUE.equals(criteria.spanInterpreter)) {
            stringBuilder.append( "P3_4_5.SpanIntON != 3");
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
        ss = "WHERE " + ss.substring(0, ss.length() - 5) + ";"; //the -5 is to pop off the last AND.
        return ss;
    }

    /**
     * Just initializes the database connection.
     * @return A connection to run SQL statements on.
     */
    static Connection initializeConnection() {
        ResourceBundle resource = ResourceBundle.getBundle("password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/georgiahealthcareinformation", "JLeon3", resource.getString("JLeon3"));
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
    private static String getBasicSelectStatement() {
        return "SELECT * " +
                "FROM (p1 " +
                "JOIN p2 ON p1.SurvNo = p2.SurvNo " +
                "JOIN p3_4_5 ON p1.SurvNo = p3_4_5.SurvNo " +
                "LEFT JOIN additionalSpecialties ON p1.SurvNo = additionalSpecialties.SurvNo";
    }
}
