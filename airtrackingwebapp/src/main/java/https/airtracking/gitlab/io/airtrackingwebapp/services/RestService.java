/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtrackingwebapp.services;

import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Utilizador
 */
@Service
public class RestService {

    private final RestTemplate restTemplate;
    
    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getFlightStateMessagePlainJSON() {
        String url = "http://192.168.160.103:9069/flightstates/last";
        return this.restTemplate.getForObject(url, String.class);
    }
    
    public String getAllFlightStateMessagePlainJSON() {
        String url = "http://192.168.160.103:9069/flightstates";
        return this.restTemplate.getForObject(url, String.class);
    }
    
    public FlightStateMessage getFlightStateMessageObject() {
        /*String url = "http://192.168.160.103:9069/flightstates/last";
        String jsons = this.restTemplate.getForObject(url, String.class);*/
        
        /* test string */
        // flight1
        // long: -84.7337
        // lat: 33.9884
        // flight2
        // long: -82.7337
        // lat: 34.9884
        
        String jsons = "{\n" +
        "    \"time\": 158683823,\n" +
        "    \"states\": [\n" +
        "        {\n" +
        "            \"icao24\": \"a09281\",\n" +
        "            \"origin country\":\"United States\",\n" +
        "            \"time_position\":1586096075,\n" +
        "            \"last_contact\":1586096075,\n" +
        "            \"longitude\":-8.6109900,\n" +
        "            \"latitude\":41.1496100,\n" +
        "            \"on_ground\":false,\n" +
        "            \"velocity\":48.64,\n" +
        "            \"vertical_rate\":-0.33\n" +
        "        },\n" +
        "        {\n" +
        "            \"icao24\": \"ade18c\",\n" +
        "            \"origin country\":\"United States\",\n" +
        "            \"time_position\":1586096075,\n" +
        "            \"last_contact\":1586099075,\n" +
        "            \"longitude\":-9.1333300,\n" +
        "            \"latitude\":38.7166700,\n" +
        "            \"on_ground\":false,\n" +
        "            \"velocity\":90.64,\n" +
        "            \"vertical_rate\":-0.23\n" +
        "        }\n" +
        "    ]\n" +
        "}";
        
        FlightStateMessage fsm = null;
        Gson gson = new Gson();
        fsm = gson.fromJson(jsons, FlightStateMessage.class);
        return fsm;
    }
    
    public FlightStateMessage getAllFlightStateMessageObject() {
        String url = "http://192.168.160.103:9069/flightstates";
        String jsons = this.restTemplate.getForObject(url, String.class);
        FlightStateMessage fsm = null;
        Gson gson = new Gson();
        fsm = gson.fromJson(jsons, FlightStateMessage.class);
        return fsm; 
    }
}

