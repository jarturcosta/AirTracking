package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import com.google.gson.Gson;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightState;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightStateMessage;
import https.airtracking.gitlab.io.airtrackingwebapp.services.RestService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {
    
    private RestService rs = new RestService(new RestTemplateBuilder());
    private FlightStateMessage fsm = null;
    private List<FlightState> listfs = new ArrayList<>();
    private List<String> listcountry = new ArrayList<>();
    
    @GetMapping("/flight")
    public String flight(Model model, @RequestParam(required = false) String flight) throws ParseException {
        
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
            
            /* Entra aqui duas vezes e buga os resultados */
            System.out.println("flight");
            
            String stats = rs.getStats(flight);
            System.out.println(stats);
            
            String max_speed = stats.split(",")[1].split(":")[1];
            String avg_speed = stats.split(",")[2].split(":")[1];
            String avg_vertical = stats.split(",")[3].split(":")[1];
            avg_vertical = avg_vertical.substring(0, avg_vertical.length() - 1);
            
            model.addAttribute("flightslist", listfs);
            model.addAttribute("countrieslist", listcountry);
            
            model.addAttribute("icao24", flight);
            model.addAttribute("max_speed", max_speed);
            model.addAttribute("avg_speed", avg_speed);
            model.addAttribute("avg_vertical_rate", avg_vertical);
            
            flight = null;
        }
        else{
            model.addAttribute("icao24", " ");
            model.addAttribute("max_speed", " ");
            model.addAttribute("avg_speed", " ");
            model.addAttribute("avg_vertical_rate", " ");
        }
        
        return "flight";
    }
}
