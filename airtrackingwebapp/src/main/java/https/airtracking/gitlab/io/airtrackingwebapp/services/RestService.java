/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtrackingwebapp.services;

import com.google.gson.Gson;
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
        String url = "http://192.168.160.103:9069/flightstates/last";
        String jsons = this.restTemplate.getForObject(url, String.class);
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
    
    public String getStats(String flight){
        String url = "http://192.168.160.103:9069/flightstates/getStats/" + flight;
        return this.restTemplate.getForObject(url, String.class);
    }
}

