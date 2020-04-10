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
import org.springframework.stereotype.Service;

/**
 *
 * @author jarturcosta
 */
@Service
public class FlightStateServiceImpl implements FlightStateService {
    
    @Autowired
    private FlightStateRepository repository;

    @Override
    public List<FlightStateMessage> findAll() {
        return repository.findAll();
    }

    @Override
    public FlightStateMessage findByTime(int time) {
        return repository.findByTime(time);
    }

    @Override
    public List<FlightState> getFlightStatesByTime(int time) {
        return repository.findByTime(time).getStates();
    }

    @Override
    public void saveOrUpdateFlightStateMessage(FlightStateMessage message) {
        repository.save(message);
    }
    
}
