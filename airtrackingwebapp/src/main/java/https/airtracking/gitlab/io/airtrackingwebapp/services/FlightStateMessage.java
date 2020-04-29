/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtrackingwebapp.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarturcosta
 */
public class FlightStateMessage implements Serializable{
    private int time;
    private List<FlightState> states;

    public FlightStateMessage() {
    }
     
    public void addState(FlightState state) {
        states.add(state);
    }
    
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<FlightState> getStates() {
        return states;
    }
    
    public void setStates(List<FlightState> states) {
        this.states = states;
    }
    
    @Override
    public String toString() {
      ObjectMapper mapper = new ObjectMapper();
      
      String jsonString = "";
    try {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      jsonString = mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    
      return jsonString;
    }
}

