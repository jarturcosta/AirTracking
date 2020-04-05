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

/**
 *
 * @author jarturcosta
 */
public class FlightStateMessage implements Serializable{
    
    private int time;
    private static List<FlightState> states;
    
    public FlightStateMessage(InputMessage input) {
        this.time = input.getTime();
        this.states = new ArrayList<>();
    }
    
    public void addState(FlightState state) {
        states.add(state);
    }

    @Override
    public String toString() {
        return "FlightStateMessage{" + "time=" + time + "states=" + states.toString();
    }
    
    
}

