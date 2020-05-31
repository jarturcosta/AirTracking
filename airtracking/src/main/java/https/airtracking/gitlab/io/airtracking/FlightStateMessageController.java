/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import https.airtracking.gitlab.io.airtracking.Models.FlightStats;
import https.airtracking.gitlab.io.airtracking.kafka.KafkaIcao24Producer;
import https.airtracking.gitlab.io.airtracking.kafka.KafkaStatsConsumer;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 *
 * @author jarturcosta
 */
@RestController
@RequestMapping("/flightstates")
@CrossOrigin(origins = "http://localhost:8080")
public class FlightStateMessageController {
    
    @Autowired
    private FlightStateMessageService flightStateService;
    
    private KafkaIcao24Producer producer = new KafkaIcao24Producer("STATS_REQ", Boolean.TRUE);
    private KafkaStatsConsumer consumer = new KafkaStatsConsumer();
    private int statRequestCount = 0;
    
    @GetMapping(value = "/")
    public List<FlightStateMessage> getAllFlightStateMessages() {
        return flightStateService.findAll();
    }
    
    @GetMapping(value = "byTime/{time}")
    public FlightStateMessage getFlightStateMessageByTime(@PathVariable("time") int time) {
        return flightStateService.findByTime(time);
    }
   
    
    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrUpdateFlightStateMessage(@RequestBody FlightStateMessage flightStateMessage) {
        flightStateService.saveOrUpdateFlightStateMessage(flightStateMessage);
        return new ResponseEntity("Flight state added successfully", HttpStatus.OK);
    }
    
    @GetMapping(value = "/last")
    public FlightStateMessage getLastFlightStateMessage() {
        return flightStateService.findTopByOrderByTimeDesc();
    }
    
    @DeleteMapping(value = "byTime/{time}")
    public ResponseEntity<?>  deleteByTime(@PathVariable("time") int time) {
        flightStateService.deleteByTime(time);
        return new ResponseEntity("Flight state deleted successfully", HttpStatus.OK);
    }
    
    @GetMapping(value = "getStats/{icao24}")
    public FlightStats getFlightStatsByIcao24(@PathVariable("icao24") String icao24) throws InterruptedException, ExecutionException {

        producer.sendMessage(icao24,statRequestCount);
        statRequestCount++;
        System.out.println(statRequestCount);
        consumer.run();

        return consumer.getLastStats();
        
    }

    
    
    
}
