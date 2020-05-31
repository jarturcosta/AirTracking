package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightState;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightStateMessage;
import https.airtracking.gitlab.io.airtrackingwebapp.services.RestService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {
    
    private RestService rs = new RestService(new RestTemplateBuilder());
    private FlightStateMessage fsm = null;
    private List<FlightState> listfs = new ArrayList<>();
    private List<String> listcountry = new ArrayList<>();
    
    @GetMapping("/flight")
    public String flight(Model model, @RequestParam(required = false) String flight, @RequestParam(required = false) String country) throws ParseException {
        
        
        if (flight == null){
            
            fsm = rs.getFlightStateMessageObject();
            listfs = fsm.getStates();
            
            for (FlightState fs : listfs){
                if (!listcountry.contains(fs.getOrigin_contry()) && !"".equals(fs.getOrigin_contry())){
                    listcountry.add(fs.getOrigin_contry());
                }
            }
            
            Collections.sort(listcountry);
            
            model.addAttribute("flightslist", listfs);
            model.addAttribute("countrieslist", listcountry);
        }
        
        if (flight != null){
            
            //String jsons = rs.getStats(flight);
            //System.out.println(jsons);
            
            /*
            for (FlightState f : listfs){
                if (f.getIcao24() == flight){
                    //...
                }
            }
            */
            
            model.addAttribute("flightslist", listfs);
            model.addAttribute("countrieslist", listcountry);
            
            model.addAttribute("icao24", flight);
            model.addAttribute("max_speed", "TODO1");
            model.addAttribute("avg_speed", "TODO2");
            model.addAttribute("avg_vertical_rate", "TODO4");
        }
        else{
            model.addAttribute("icao24", " ");
            model.addAttribute("max_speed", " ");
            model.addAttribute("avg_speed", " ");
            model.addAttribute("avg_vertical_rate", " ");
        }
        
        return "flight";
    }

    /*@GetMapping("/flightdata")
	public String flightData(Model model) {
		return "flightData";
    }*/
}
