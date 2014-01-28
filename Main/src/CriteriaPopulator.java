import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 1/25/14
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class CriteriaPopulator {
    static Hospital populateCriteriaFromRequest_addHospital(HttpServletRequest request)
   {
       Hospital criteria = new Hospital();
       String temp;

       temp = request.getParameter("SurvNo");
       criteria.survNo = 1;
       if(temp != null && temp != "")
           criteria.survNo = Integer.parseInt(temp);
       temp = request.getParameter("name");
       criteria.name = "";
       if(temp != null && temp != "")
           criteria.name = temp;
       criteria.county = request.getParameter("county");
       criteria.walkIn = 1;
       temp = request.getParameter("appt");
       if (temp != null && temp.equalsIgnoreCase("appointment")) {
           criteria.walkIn = 2;
       }
       temp = request.getParameter("walk");
       if (temp != null && temp.equalsIgnoreCase("walkin")){
           criteria.walkIn = 1;
       }
       temp = request.getParameter("bothappt");
       if (temp != null && temp.equalsIgnoreCase("bothWalkApp")){
           criteria.walkIn = 1;
       }
       criteria.medicare = false;
       criteria.medicaid = false;
       criteria.peachCare = false;
       criteria.otherHealthcare = false;
       temp = request.getParameter("Medicare");
       if(temp != null && temp.equals("Medicare")) {
           criteria.medicare = true;
       }
       temp = request.getParameter("Medicaid");
       if (temp != null && temp.equals("Medicaid")) {
           criteria.medicaid = true;
       }
       temp = request.getParameter("Peachcare");
       if (temp != null && temp.equals("Peachcare")) {
           criteria.peachCare = true;
       }
       temp = request.getParameter("OtherHealthcare");
       if (temp != null && temp.equals("OtherHealthcare")) {
           criteria.otherHealthcare = true;
       }
       criteria.spanFo = 0;
       temp = request.getParameter("forms");
       if(temp != null && temp.equals("yes")) {
           criteria.spanFo = 1;
       }
       temp = request.getParameter("zip");
       if(temp != null) {
           criteria.zip = new Integer(temp);
       }
       temp = request.getParameter("reception");
       if(temp != null && temp.equals("yes")) {
           criteria.spanAdmin = 1;
       } else{
           criteria.spanAdmin = 0;
       }
       temp = request.getParameter("interpreter");
       if (temp != null && temp.equalsIgnoreCase("interpY")) {
           //1, 2, and 3 are just yes, 4 is no. Yes, it's a terrible system, but we were given a terrible database.
           criteria.spanInterpreter = 1;
       } else {
           criteria.spanInterpreter = 0;
       }
       temp = request.getParameter("call");
       if(temp != null && temp.equals("yes"))
           criteria.onCall = true;
       else criteria.onCall = false;
       //This is a stupid system, but it technically works. The open times are whether it's open on that day or not.
       //zero is not open, one is open, as strings. Yeah, it's dumb.
       if (criteria.openTimes == null || criteria.openTimes.size() < 7) {
           List<String> openTimes = new ArrayList<String>();
           for (int i = 0; i < 7; i++) {
               openTimes.add(i, "0");
           }
           criteria.openTimes = openTimes;
       }
       temp = request.getParameter("Sunday");
       if (temp != null && temp.equalsIgnoreCase("Su")) {
           criteria.openTimes.remove(0);
           criteria.openTimes.add(0, "1");
       } else {
           criteria.openTimes.remove(0);
           criteria.openTimes.add(0, "0");
       }
       temp = request.getParameter("Monday");
       if (temp != null && temp.equalsIgnoreCase("M")) {
           criteria.openTimes.remove(1);
           criteria.openTimes.add(1, "1");
       } else {
           criteria.openTimes.remove(1);
           criteria.openTimes.add(1, "0");
       }
       temp = request.getParameter("Tuesday");
       if (temp != null && temp.equalsIgnoreCase("T")) {
           criteria.openTimes.remove(2);
           criteria.openTimes.add(2, "1");
       } else {
           criteria.openTimes.remove(2);
           criteria.openTimes.add(2, "0");
       }
       temp = request.getParameter("Wednesday");
       if (temp != null && temp.equalsIgnoreCase("W")) {
           criteria.openTimes.remove(3);
           criteria.openTimes.add(3, "1");
       } else {
           criteria.openTimes.remove(3);
           criteria.openTimes.add(3, "0");
       }
       temp = request.getParameter("Thursday");
       if (temp != null && temp.equalsIgnoreCase("R")) {
           criteria.openTimes.remove(4);
           criteria.openTimes.add(4, "1");
       } else {
           criteria.openTimes.remove(4);
           criteria.openTimes.add(4, "0");
       }
       temp = request.getParameter("Friday");
       if (temp != null && temp.equalsIgnoreCase("F")) {
           criteria.openTimes.remove(5);
           criteria.openTimes.add(5, "1");
       } else {
           criteria.openTimes.remove(5);
           criteria.openTimes.add(5, "0");
       }
       temp = request.getParameter("Saturday");
       if (temp != null && temp.equalsIgnoreCase("S")) {
           criteria.openTimes.remove(6);
           criteria.openTimes.add(6, "1");
       } else {
           criteria.openTimes.remove(6);
           criteria.openTimes.add(6, "0");
       }
       temp = request.getParameter("AnyDay");
       if (temp != null && temp.equalsIgnoreCase("A")) {
           for (int i = 0; i < criteria.openTimes.size(); i++) {
               criteria.openTimes.remove(i);
               criteria.openTimes.add(i, "1");
           }
       }
       temp = request.getParameter("family");
       if( temp != null && temp.equalsIgnoreCase("fam"))
           criteria.spcFCH = true;
       else
           criteria.spcFCH = false;
       temp = request.getParameter("womens");
       if(temp != null && temp.equals("wo"))
           criteria.spcWH = true;
       else criteria.spcWH = false;
       temp = request.getParameter("mens");
       if(temp != null && temp.equals("me"))
           criteria.spcMH = true;
       else criteria.spcMH = false;
       temp = request.getParameter("mental");
       if(temp != null && temp.equals("ment"))
           criteria.spcMHC = true;
       else criteria.spcMHC = false;
       temp = request.getParameter("dental");
       if(temp != null && temp.equals("dent"))
           criteria.spcDH = true;
       else criteria.spcDH = false;
       temp = request.getParameter("vision");
       if(temp != null && temp.equals("vis"))
           criteria.spcVH = true;
       else criteria.spcVH = false;
       temp = request.getParameter("child");
       if(temp != null && temp.equals("y"))
           criteria.childGuide = true;
       else criteria.childGuide = false;
       temp = request.getParameter("mentalhealth");
       if(temp != null && temp.equals("subt"))
           criteria.subAbGuide = true;
       else criteria.subAbGuide = false;
       temp = request.getParameter("mentalhealth1");
       if(temp != null && temp.equals("se"))
           criteria.sexAbGuide = true;
       else criteria.sexAbGuide = false;
       temp = request.getParameter("mentalhealth2");
       if(temp != null && temp.equals("ang"))
           criteria.angManGuide = true;
       else criteria.angManGuide = false;
       temp = request.getParameter("mentalhealth3");
       if(temp != null && temp.equals("HIV"))
           criteria.hivConsGuide = true;
       else criteria.hivConsGuide = false;
       temp = request.getParameter("mentalhealth4");
       if(temp != null && temp.equals("LGBT"))
           criteria.lgbtGuide = true;
       else criteria.lgbtGuide = false;
       criteria.childGuide = false;
       temp = request.getParameter("child");
       if(temp != null && temp.equals("yes"))
           criteria.childGuide = true;
       temp = request.getParameter("web");
       criteria.website = temp;
       temp = request.getParameter("pho");
       criteria.phone = temp;
       temp = request.getParameter("add");
       criteria.addressLine1 = temp;
       temp = request.getParameter("city");
       criteria.city = temp;

       //additional specialties

       criteria.primaryCare = "0";
       criteria.internal = "0";
       criteria.pediatric = "0";
       criteria.geriatric = "0";
       criteria.diabetes = "0";
       criteria.pain = "0";
       criteria.emergency = "0";
       criteria.surgery = "0";
       criteria.radiology = "0";
       criteria.dermatology = "0";
       criteria.ent = "0";
       criteria.allergy = "0";
       criteria.prenatal = "0";

       temp = request.getParameter("primaryCare");
       if (temp != null && temp.equalsIgnoreCase("pri")) {
           criteria.primaryCare = "1";
       }

       temp = request.getParameter("internal");
       if (temp != null && temp.equalsIgnoreCase("int")) {
           criteria.internal = "1";
       }

       temp = request.getParameter("child");
       if (temp != null && temp.equalsIgnoreCase("chi")) {
           criteria.pediatric = "1";
       }

       temp = request.getParameter("elder");
       if (temp != null && temp.equalsIgnoreCase("eld")) {
           criteria.geriatric = "1";
       }

       temp = request.getParameter("diabetes");
       if (temp != null && temp.equalsIgnoreCase("dia")) {
           criteria.diabetes = "1";
       }

       temp = request.getParameter("pain");
       if (temp != null && temp.equalsIgnoreCase("pai")) {
           criteria.pain = "1";
       }

       temp = request.getParameter("trauma");
       if (temp != null && temp.equalsIgnoreCase("trau")) {
           criteria.emergency = "1";
       }

       temp = request.getParameter("surgery");
       if (temp != null && temp.equalsIgnoreCase("sur")) {
           criteria.surgery = "1";
       }

       temp = request.getParameter("radio");
       if (temp != null && temp.equalsIgnoreCase("rad")) {
           criteria.radiology = "1";
       }

       temp = request.getParameter("skin");
       if (temp != null && temp.equalsIgnoreCase("skin")) {
           criteria.dermatology = "1";
       }

       temp = request.getParameter("ent");
       if (temp != null && temp.equalsIgnoreCase("ent")) {
           criteria.ent = "1";
       }

       temp = request.getParameter("allergy");
       if (temp != null && temp.equalsIgnoreCase("alle")) {
           criteria.allergy = "1";
       }

       temp = request.getParameter("prenatal");
       if (temp != null && temp.equalsIgnoreCase("prena")) {
           criteria.prenatal = "1";
       }

       return criteria ;
   }

    /**
     * Takes a request and then makes a hospital based on the input in the request.
     * Every input element from the form should be represented here, and should be mapped to a field in the hospital.
     * @param request The incoming request which contains the form data.
     * @return A hospital which represents the search criteria.
     */
    static Hospital populateCriteriaFromRequest(HttpServletRequest request)
    {
        Hospital criteria = new Hospital();
        Enumeration parameterNames = request.getParameterNames();
        String temp;
        //TODO the looping here seems inefficient
        while (parameterNames.hasMoreElements())
        {
            String parameter = (String)parameterNames.nextElement();

            criteria.county = request.getParameter("county");
            if (parameter.equalsIgnoreCase("transportationForm")) {
                //TODO What are we doing if they say drive, bus, or bike? Nothing?
                temp = request.getParameter(parameter);
                if(temp.equals("1")) {
                    //Drive
                } else if (temp.equals("2")) {
                    //Marta
                    criteria.publicTransportation = Hospital.TRUE;
                } else if (temp.equals("3")) {
                    //Bus
                } else if (temp.equals("4")) {
                    //Bike
                }
            }


//            if (parameter.equalsIgnoreCase("parking"))
//            {
//                temp = request.getParameter(parameter);
//                criteria.parking = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
//            }

 /*           if (parameter.equalsIgnoreCase("walkIn"))
            {
                temp = request.getParameter(parameter);
                //if appt was already processed
                if (criteria.walkIn != null && criteria.walkIn == 2) {
                    if (temp.equalsIgnoreCase("y")) {
                        criteria.walkIn = 3;
                    }
                } else {
                    criteria.walkIn = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
                }
            }

           if (parameter.equalsIgnoreCase("walkInComment"))
            {
                temp = request.getParameter(parameter);
                criteria.walkInComment = temp;
            }
 */
            if (parameter.equalsIgnoreCase("appt"))
            {
                temp = request.getParameter(parameter);
                if (temp.equalsIgnoreCase("appointment")) {
                    criteria.walkIn = 2;
                }
            }
            if (parameter.equalsIgnoreCase("walkin"))
            {
                temp = request.getParameter(parameter);
                if (temp.equalsIgnoreCase("walkin")) {
                    criteria.walkIn = 1;
                }
            }
            if (parameter.equalsIgnoreCase("bothappt"))
            {
                temp = request.getParameter(parameter);
                if (temp.equalsIgnoreCase("bothWalkApp")) {
                    criteria.walkIn = 3;
                }
            }
            if (parameter.equalsIgnoreCase("insuranceForm"))
            {
                //TODO this is different than in adding a hospital. Is it right?
                temp = request.getParameter(parameter);
                if(temp.equals("1")) {
                    criteria.medicare = true;
                } else if (temp.equals("2")) {
                    criteria.medicaid = true;
                } else if (temp.equals("3")) {
                    criteria.peachCare = true;
                }
            }
            if (parameter.equalsIgnoreCase("receptionist"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("yes"))
                    criteria.spanAdmin = Hospital.TRUE;
            }
            if (parameter.equalsIgnoreCase("nurses"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("nur"))
                    criteria.spanNurse = Hospital.TRUE;
            }
            if (parameter.equalsIgnoreCase("doctor"))
            {
                temp = request.getParameter(parameter);
                criteria.spanDoc = temp.equalsIgnoreCase("doc") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("forms"))
            {
                temp = request.getParameter(parameter);
                criteria.spanFo = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            if (parameter.equalsIgnoreCase("spanInterpreter"))
            {
                temp = request.getParameter(parameter);
                criteria.spanInterpreter = temp.equalsIgnoreCase("interpY") ? Hospital.TRUE : Hospital.FALSE;
            }

            if (parameter.equalsIgnoreCase("reception"))
            {
                temp = request.getParameter(parameter);
                criteria.spanPhone = temp.equalsIgnoreCase("yes") ? Hospital.TRUE : Hospital.FALSE;
            }
            temp = request.getParameter("call");
            if(temp != null && temp.equals("yes")) {
                criteria.onCall = true;
            } else {
                criteria.onCall = false;
            }
            //Stuff about days would go here, we're ignoring that for now.

            if (parameter.equalsIgnoreCase("family"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("fam"))
                    criteria.spcFCH = true;
                else
                    criteria.spcFCH = false;
            }
            if (parameter.equalsIgnoreCase("womens"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("wo"))
                    criteria.spcWH = true;
                else
                    criteria.spcWH = false;
            }
            if (parameter.equalsIgnoreCase("mens"))
            {
                //TODO Make sure that spcMH is actually mens health and not mental health. I might have these backwards.
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("me"))
                    criteria.spcMH = true;
                else
                    criteria.spcMH = false;
            }
            if (parameter.equalsIgnoreCase("mental"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("ment"))
                    criteria.spcMHC = true;
                else
                    criteria.spcMHC = false;
            }
            if (parameter.equalsIgnoreCase("dental"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("dent"))
                    criteria.spcDH = true;
                else
                    criteria.spcDH = false;
            }
            if (parameter.equalsIgnoreCase("vision"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("vis"))
                    criteria.spcVH = true;
                else
                    criteria.spcVH = false;
            }
            //find the age range of the children
            ArrayList<Integer> start = new ArrayList<Integer>();
            ArrayList<Integer> end = new ArrayList<Integer>();
            if (parameter.equalsIgnoreCase("age1"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("1")){
                    start.add(0);
                    end.add(5);
            	}
            }
            if (parameter.equalsIgnoreCase("age2"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("2")){
                    start.add(6);
                    end.add(10);
            	}
            }
            if (parameter.equalsIgnoreCase("age3"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("3")){
                    start.add(11);
                    end.add(15);
            	}
            }
            if (parameter.equalsIgnoreCase("age4"))
            {
            	temp = request.getParameter(parameter);
            	if(temp.equalsIgnoreCase("4"))
            	{
                    start.add(16);
                    end.add(17);
            	}
            }
            int agestart = (start.size() != 0)? start.get(0) : -1;
            int ageend = (end.size() != 0)? end.get(0) : -1;
            for(int i = 0; i < start.size(); i++){
            	if(start.get(i) < agestart)
            		agestart = start.get(i);
                if(end.get(i) > ageend)
                	ageend = end.get(i);
            }
            criteria.ageStart = agestart;
            criteria.ageEnd = ageend;


            if (parameter.equalsIgnoreCase("child"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("y"))
                    criteria.childGuide = true;
                else
                    criteria.childGuide = false;
            }
 /*
           if (parameter.equalsIgnoreCase("adolescentGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.adolescentGuide = true;
                else
                    criteria.adolescentGuide = false;
            }
            if (parameter.equalsIgnoreCase("adultGuide"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("True"))
                    criteria.adultGuide = true;
                else
                    criteria.adultGuide = false;
            }
            if (parameter.equalsIgnoreCase("agesGuide"))
            {
                temp = request.getParameter(parameter);
                criteria.agesGuide = temp;
            }
 */
            if (parameter.equalsIgnoreCase("csexHIV"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("HIV"))
                    criteria.hivTestGuide = true;
                else
                    criteria.hivTestGuide = false;
            }

            if (parameter.equalsIgnoreCase("csubstance"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("subt"))
                    criteria.subAbGuide = true;
                else
                    criteria.subAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("csex"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("se"))
                    criteria.sexAbGuide = true;
                else
                    criteria.sexAbGuide = false;
            }
            if (parameter.equalsIgnoreCase("canger"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("ang"))
                    criteria.angManGuide = true;
                else
                    criteria.angManGuide = false;
            }
            if (parameter.equalsIgnoreCase("sexLGBT"))
            {
                temp = request.getParameter(parameter);
                if(temp.equalsIgnoreCase("LGBT"))
                    criteria.lgbtGuide = true;
                else
                    criteria.lgbtGuide = false;
            }
        }
        return criteria ;
    }
}
