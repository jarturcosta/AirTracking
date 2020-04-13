/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author jarturcosta
 */
@RestController
@RequestMapping("/flightstates")
public class FlightStateMessageController {
    
    @Autowired
    private FlightStateMessageService flightStateService;
    
    @GetMapping(value = "/")
    public List<FlightStateMessage> getAllFlightStateMessages() {
        return flightStateService.findAll();
    }
    
    @GetMapping(value = "/{time}")
    public FlightStateMessage getFlightStateMessageByTime(@PathVariable("time") int time) {
        return flightStateService.findByTime(time);
    }
   
    
    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrUpdateFlightStateMessage(@RequestBody FlightStateMessage flightStateMessage) {
        flightStateService.saveOrUpdateFlightStateMessage(flightStateMessage);
        return new ResponseEntity("Flight state added successfully", HttpStatus.OK);
    }
    
    
    
    
}
