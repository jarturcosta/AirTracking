/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import com.google.gson.Gson;
import com.jayway.jsonpath.Criteria;
import com.mongodb.client.MongoClients;
import static com.mongodb.client.model.Filters.type;
import static com.mongodb.client.model.Filters.type;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import https.airtracking.gitlab.io.airtracking.Models.FlightStats;
import https.airtracking.gitlab.io.airtracking.kafka.KafkaIcao24Producer;
import https.airtracking.gitlab.io.airtracking.kafka.KafkaStatsConsumer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;


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
    private int statRequestCount = 0, insertRequestCount = 0 ;
    private Gson gson = new Gson();
    
    @GetMapping(value = "/")
    public List<FlightStateMessage> getAllFlightStateMessages() {
        Logger.getLogger(KafkaIcao24Producer.class.getName()).log(Level.INFO, "/flightstates/");
        return flightStateService.findAll();
    }
    
    @GetMapping(value = "/byTime/{time}")
    public FlightStateMessage getFlightStateMessageByTime(@PathVariable("time") int time) {
        return flightStateService.findByTime(time);
    }
   
    
    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrUpdateFlightStateMessage(@RequestBody FlightStateMessage flightStateMessage) {
        flightStateService.saveOrUpdateFlightStateMessage(flightStateMessage);
        insertRequestCount += 1;
        if (insertRequestCount >= 720) {
            clearFlightStates();
         }
        
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
    
    @DeleteMapping(value = "clearFlightStates")
    public ResponseEntity<?>  clearFlightStates() {
        flightStateService.deleteAll();
        return new ResponseEntity("All flight states deleted successfully", HttpStatus.OK);
    }
 
    
    

    
    
    
}
