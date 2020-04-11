/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking.Models;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;


import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jarturcosta
 */

@Document(collection = "flight_state_messages")
public class FlightStateMessage implements Serializable{
    @Id
    private int time;
    private static List<FlightState> states;
    
    public FlightStateMessage(InputMessage input) {
        this.time = input.getTime();
        this.states = new ArrayList<>();
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
        FlightStateMessage.states = states;
    }
    

    @Override
    public String toString() {
        return "FlightStateMessage{" + "time=" + time + "states=" + states.toString();
    }


    
}

