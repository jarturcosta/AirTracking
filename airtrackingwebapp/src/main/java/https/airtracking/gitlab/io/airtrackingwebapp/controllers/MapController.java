package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import com.google.gson.Gson;
import https.airtracking.gitlab.io.airtrackingwebapp.services.FlightStateMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/map")
    public String map(Model model) {
        
        String jsons = "{\n" +
        "    \"time\": 158683823,\n" +
        "    \"states\": [\n" +
        "        {\n" +
        "            \"icao24\": \"a09281\",\n" +
        "            \"origin country\":\"United States\",\n" +
        "            \"time_position\":1586096075,\n" +
        "            \"last_contact\":1586096075,\n" +
        "            \"longitude\":-84.7337,\n" +
        "            \"latitude\":33.9884,\n" +
        "            \"on_ground\":false,\n" +
        "            \"velocity\":48.64,\n" +
        "            \"vertical_rate\":-0.33\n" +
        "        },\n" +
        "        {\n" +
        "            \"icao24\": \"ade18c\",\n" +
        "            \"origin country\":\"United States\",\n" +
        "            \"time_position\":1586096075,\n" +
        "            \"last_contact\":1586099075,\n" +
        "            \"longitude\":-82.7337,\n" +
        "            \"latitude\":34.9884,\n" +
        "            \"on_ground\":false,\n" +
        "            \"velocity\":90.64,\n" +
        "            \"vertical_rate\":-0.23\n" +
        "        }\n" +
        "    ]\n" +
        "}";
        
        FlightStateMessage msg = null;
        Gson gson = new Gson();
        msg = gson.fromJson(jsons, FlightStateMessage.class);
        
        System.out.println(msg.getStates().get(0).getLatitude());
        
        return "worldmap";
    }

}
