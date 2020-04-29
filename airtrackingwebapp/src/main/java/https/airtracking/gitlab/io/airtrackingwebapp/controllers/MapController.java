package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import com.google.gson.Gson;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightState;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightStateMessage;
import https.airtracking.gitlab.io.airtrackingwebapp.services.RestService;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MapController {  
    
    @GetMapping("/map")
    public String map(Model model) {
        
        /*
        RestService restService = new RestService(new RestTemplateBuilder());
        FlightStateMessage fsm = restService.getFlightStateMessageObject();
        System.out.println(fsm.getStates().get(0).getLatitude());
        
        System.out.println("JSJS: " + fsm.getStates());
        
        JSONObject obj = new JSONObject();
        List<JSONObject> planes = new ArrayList<>();

        for (FlightState fs : fsm.getStates()){
            obj.put("icao24", fs.getIcao24());
            obj.put("latitude", Float.parseFloat(fs.getLatitude()));
            obj.put("longitude", Float.parseFloat(fs.getLongitude()));
            planes.add(obj);
        }*/
        
        //System.out.println(planes);
        
        //model.addAttribute("flightstates", planes);

        return "worldmap";
    }
    
    
    public String mapData(Model model){
        
        RestService restService = new RestService(new RestTemplateBuilder());
        FlightStateMessage fsm = restService.getFlightStateMessageObject();
        System.out.println(fsm.getStates().get(0).getLatitude());
        
        System.out.println("JSJS: " + fsm.getStates());
        
        JSONObject obj = new JSONObject();
        List<JSONObject> planes = new ArrayList<>();

        for (FlightState fs : fsm.getStates()){
            obj.put("icao24", fs.getIcao24());
            obj.put("latitude", Float.parseFloat(fs.getLatitude()));
            obj.put("longitude", Float.parseFloat(fs.getLongitude()));
            planes.add(obj);
        }
        
        return "";
    }
}
