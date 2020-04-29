package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightStateMessage;
import https.airtracking.gitlab.io.airtrackingwebapp.services.RestService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {

    @GetMapping("/flight")
    public String flight(Model model, @RequestParam(required = false) String flight, @RequestParam(required = false) String start, @RequestParam(required = false) String end) throws ParseException {
        
        System.out.println("======================" + start);
        
        boolean dateConfirm = true;
        
        if(start != null && end != null){
            if(!start.equals("") && !end.equals("")){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = sdf.parse(start);
                Date date2 = sdf.parse(end);

                if (date1.compareTo(date2) > 0) {
                    dateConfirm = false;
                    System.out.println("Date1 is after Date2");
                } else if (date1.compareTo(date2) < 0) {
                    System.out.println("Date1 is before Date2");
                } else if (date1.compareTo(date2) == 0) {
                    System.out.println("Date1 is equal to Date2");
                } else {
                    System.out.println("How to get here?");
                }
            }
        }
        
        if(flight != null && dateConfirm){
            
            /* pseudo teste */
            RestService restService = new RestService(new RestTemplateBuilder());
            FlightStateMessage fsm = restService.getFlightStateMessageObject();
            System.out.println(fsm.getStates().get(0).getLatitude());
            
            model.addAttribute("name", flight);
            model.addAttribute("maxspeed", "TODO1");
            model.addAttribute("avgspeed", "TODO2");
            //model.addAttribute("maxalt", "TODO3");
            model.addAttribute("avgvertical", "TODO4");
            //model.addAttribute("number", "TODO5");
        }
        else{
            model.addAttribute("name", " ");
            model.addAttribute("maxspeed", " ");
            model.addAttribute("avgspeed", " ");
            //model.addAttribute("maxalt", " ");
            model.addAttribute("avgvertical", " ");
            //model.addAttribute("number", " ");
        }
        
        return "flight";
    }

    /*@GetMapping("/flightdata")
	public String flightData(Model model) {
		return "flightData";
    }*/
}
