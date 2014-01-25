import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;

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
       temp = request.getParameter("parking");
       criteria.parking = 0;
       if(temp != null && temp.equals("yes"))
           criteria.parking = 1;
       criteria.walkIn = 1;
       temp = request.getParameter("appt");
       //if walkin was already processed
       //TODO not sure this is right, this is leftover from when we were looping here.
       if (temp != null && temp.equalsIgnoreCase("walkin")) {
           criteria.walkIn = 2;
       }
       else if (temp != null && temp.equalsIgnoreCase("appointment")){
           criteria.walkIn = 1;
       }
       temp = request.getParameter("insuranceForm");
       criteria.medicare = false;
       criteria.medicaid = false;
       criteria.peachCare = false;
       criteria.otherHealthcare = false;
       if(temp != null && temp.equals("1")) {
           criteria.medicare = true;
       } else if (temp != null && temp.equals("2")) {
           criteria.medicaid = true;
       } else if (temp != null && temp.equals("3")) {
           criteria.peachCare = true;
       } else if (temp != null && temp.equals("4")) {
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
           //TODO are these just 1 and 0? or is there 3 or something?
           criteria.spanInterpreter = 1;
       }
       else
           criteria.spanInterpreter = 0;
       temp = request.getParameter("call");
       if(temp != null && temp.equals("yes"))
           criteria.onCall = true;
       else criteria.onCall = false;
       //Stuff about days would go here, we're ignoring that for now.
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
                //if walkin was already processed
                if (temp.equalsIgnoreCase("appmt")) {
                    criteria.walkIn = 2;
                }
                else if (temp.equalsIgnoreCase("walk")){
                	criteria.walkIn = 1;
                }
            }
            if (parameter.equalsIgnoreCase("insuranceForm"))
            {
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
                if(temp.equalsIgnoreCase("recept"))
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

            if (parameter.equalsIgnoreCase("phone"))
            {
                temp = request.getParameter(parameter);
                criteria.spanPhone = temp.equalsIgnoreCase("y") ? Hospital.TRUE : Hospital.FALSE;
            }
            temp = request.getParameter("call");
            if(temp != null && temp.equals("yes"))
                criteria.onCall = true;
            else criteria.onCall = false;
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
