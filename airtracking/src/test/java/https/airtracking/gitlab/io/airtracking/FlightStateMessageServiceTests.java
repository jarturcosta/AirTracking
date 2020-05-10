/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

/**
 *
 * @author jarturcosta
 */

import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class FlightStateMessageServiceTests {

    @Autowired
    private FlightStateMessageService flightStateService;


    @Test
    public void createFlightStateMessage() {
       FlightState toCheck = new FlightState("ab172vs", "Portugal", "15273617", "15273617" , "47.123141", "-3.127371", "203.2", "-0.23", "false");
        List<FlightState> states = new ArrayList<>();
        states.add(toCheck);
        FlightStateMessage toSave = new FlightStateMessage(123456, states);
        flightStateService.saveOrUpdateFlightStateMessage(toSave);
        
        assertTrue(flightStateService.findByTime(123456).getStates().get(0).getIcao24().equals("ab172vs"));
        flightStateService.deleteByTime(123456);
    }

}
