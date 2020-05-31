/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarturcosta
 */
public class MongoFSM {
    private int _id;
    private List<FlightState> states;
    

    public MongoFSM() {
    }

    public MongoFSM(int _id, List<FlightState> states) {
        this._id = _id;
        this.states = states;
    }
    
    
    
    
    public void addState(FlightState state) {
        states.add(state);
    }
    
    public int getTime() {
        return _id;
    }

    public void setTime(int time) {
        this._id = time;
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
