/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;
import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author jarturcosta
 */
@Repository
public interface FlightStateMessageRepository extends MongoRepository<FlightStateMessage, String> {
    
    
    FlightStateMessage findByTime(int time);
    
    FlightStateMessage findTopByOrderByTimeDesc();

    void deleteByTime(int time);
    
    void deleteAll();
        
    
}
