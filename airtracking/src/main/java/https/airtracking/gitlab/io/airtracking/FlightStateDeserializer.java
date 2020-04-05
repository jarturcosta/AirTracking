/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import com.google.gson.Gson;
import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import https.airtracking.gitlab.io.airtracking.Models.InputMessage;
import java.math.BigDecimal;
import java.util.List;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;

/**
 *
 * @author jarturcosta
 */
public class FlightStateDeserializer {
    
    public static FlightStateMessage deserialize(String message) {
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        InputMessage input = gson.fromJson(message, InputMessage.class); // deserializes json into target2
        
        System.out.println(input.toString());
        
        FlightStateMessage result = new FlightStateMessage(input);
        List<List<Object>>  states = input.getStates();
        
        
        
        for (int i = 0; i < input.getNStates(); i++) {
            List<Object> currentState = states.get(i);
            
            
            FlightState state = new FlightState(currentState.get(0).toString(),
                                                currentState.get(2).toString(), 
                                                new BigDecimal(String.valueOf(currentState.get(3))).intValue(),
                                                new BigDecimal(String.valueOf(currentState.get(4))).intValue(),
                                                (Double) currentState.get(5), 
                                                (Double) currentState.get(6), 
                                                (Double) currentState.get(9), 
                                                (Double) currentState.get(11), 
                                                (Boolean) currentState.get(8));
            result.addState(state);
        }
        
        return result;
    }
    
}
