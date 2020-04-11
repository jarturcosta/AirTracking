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
public class FlightStateController {
    
    @Autowired
    private FlightStateService flightStateService;
    
    @GetMapping(value = "/")
    public List<FlightStateMessage> getAllMessages() {
        return flightStateService.findAll();
    }
    
    @GetMapping(value = "/{time}")
    public FlightStateMessage getMessageByTime(@PathVariable("time") int time) {
        return flightStateService.findByTime(time);
    }
    
    @GetMapping(value = "/states/{time}")
    public List<FlightState> getStatestByTime(@PathVariable("time") int time) {
        return flightStateService.getFlightStatesByTime(time);
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrUpdateStudent(@RequestBody FlightStateMessage message) {
        flightStateService.saveOrUpdateFlightStateMessage(message);
        return new ResponseEntity("Flight state added successfully", HttpStatus.OK);
    }
    
    
    
    
}
