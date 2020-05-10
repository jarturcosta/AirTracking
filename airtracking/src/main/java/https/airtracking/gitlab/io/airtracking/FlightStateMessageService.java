/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jarturcosta
 */
@Service
public interface FlightStateMessageService {
    List<FlightStateMessage> findAll();
    
    void deleteByTime(int time);
    
    FlightStateMessage findByTime(int time);
    
    FlightStateMessage findTopByOrderByTimeDesc();
        
    void saveOrUpdateFlightStateMessage(FlightStateMessage message);
    
    
}
