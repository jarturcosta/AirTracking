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


/**
 *
 * @author jarturcosta
 */
public interface FlightStateRepository extends MongoRepository<FlightStateMessage, String> {
    
    @Override
    public List<FlightStateMessage> findAll();
    
    public FlightStateMessage findByTime(int time);
    
    public List<FlightState> getFlightStatesByTime(int time);

    public void saveOrUpdateFlightStateMessage(FlightStateMessage message);
    
    
}
