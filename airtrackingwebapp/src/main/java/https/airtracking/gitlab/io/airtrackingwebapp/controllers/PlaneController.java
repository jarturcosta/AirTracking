package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightState;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightStateMessage;
import https.airtracking.gitlab.io.airtrackingwebapp.services.RestService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaneController {

    private RestService rs = new RestService(new RestTemplateBuilder());
    private FlightStateMessage fsm = null;
    private List<FlightState> listfs = new ArrayList<>();
    private List<String> listcountry = new ArrayList<>();
    
    @GetMapping("/plane")
    public String plane(Model model, @RequestParam(required = false) String plane, @RequestParam(required = false) String country) throws ParseException {
        
        if (plane == null){
            
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
        
        if (plane != null){
            
            //String jsons = rs.getStats(flight);
            //System.out.println(jsons);
            
            
            for(FlightState f : listfs){
                if (f.getIcao24().equals(plane)){
                    model.addAttribute("icao24", f.getIcao24());
                    model.addAttribute("country", f.getOrigin_contry());
                    model.addAttribute("latitude", f.getLatitude());
                    model.addAttribute("longitude", f.getLongitude());
                    model.addAttribute("velocity", f.getVelocity());
                    model.addAttribute("verticalrate", f.getVertical_rate());
                }
            }
            
            model.addAttribute("flightslist", listfs);
            model.addAttribute("countrieslist", listcountry);        
        }
        else{
            model.addAttribute("icao24", "");
            model.addAttribute("country", "");
            model.addAttribute("latitude", "");
            model.addAttribute("longitude", "");
            model.addAttribute("velocity", "");
            model.addAttribute("verticalrate", "");
        }
        
        return "plane";
    }
    
}
