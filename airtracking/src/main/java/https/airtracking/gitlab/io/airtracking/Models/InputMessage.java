/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking.Models;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author jarturcosta
 */
public class InputMessage{

    private int time;
    private List<List<Object>> states;

    public InputMessage(int time, List<List<Object>>  states) {
        this.time = time;
        this.states = states;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<List<Object>>  getStates() {
        return states;
    }

    public void setStates(List<List<Object>>  states) {
        this.states = states;
    }

    public int getNStates() {
        return states.size();
    }

    @Override
    public String toString() {
        return "InputMessage{" + "time=" + time + ", states=" + states + '}';
    }
    
    
    
   
}
