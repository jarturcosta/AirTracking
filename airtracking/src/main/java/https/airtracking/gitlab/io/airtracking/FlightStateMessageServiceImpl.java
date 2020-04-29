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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jarturcosta
 */
@Service
public class FlightStateMessageServiceImpl implements FlightStateMessageService {
    
    @Autowired
    private FlightStateMessageRepository flightStateMessageRepository;

    @Override
    public List<FlightStateMessage> findAll() {
        return flightStateMessageRepository.findAll();
    }

    @Override
    public FlightStateMessage findByTime(int time) {
        return flightStateMessageRepository.findByTime(time);
    }

    @Override
    @Transactional
    public void saveOrUpdateFlightStateMessage(FlightStateMessage message) {
        flightStateMessageRepository.save(message);
    }

    @Override
    public FlightStateMessage findTopByOrderByCreatedDesc() {
        return flightStateMessageRepository.findTopByOrderByCreatedDesc();
    }
    
}
