/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
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


public class FlightStateMessage{
    private int time;
    private List<FlightState> states;
    
    public FlightStateMessage(InputMessage input) {
        this.time = input.getTime();
        this.states = new ArrayList<>();
    }

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
    
    public String toString2() {
        String res = this.getTime() + ": {\n";
        int cnt = 0;
        for (FlightState state : states) {
            if (cnt % 5 == 0) {
                res+="\n\t";
            }
            cnt+=1;
            res +=  state.getIcao24() + ", ";
            
            
        }
        res += "}";
        return res;
    }
    


    
}
