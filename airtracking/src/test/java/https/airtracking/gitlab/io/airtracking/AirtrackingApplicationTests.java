package https.airtracking.gitlab.io.airtracking;
import com.google.gson.Gson;
import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import https.airtracking.gitlab.io.airtracking.Models.FlightStats;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@SpringBootTest

public class AirtrackingApplicationTests{
    @Autowired
    private FlightStateMessageController controller;
    
    private static FlightStateMessage toSave;
    private static FlightState toCheck;
    private static List<FlightStateMessage> statsTestCases = new ArrayList<>();

    @BeforeAll
    public static void initialize() {
            
            // Insertion initialization
            toCheck = new FlightState("ab172vs", "Portugal", "15273617", "15273617" , "47.123141", "-3.127371", "203.2", "-0.23", "false");
            List<FlightState> states = new ArrayList<>();
            states.add(toCheck);
            toSave = new FlightStateMessage(123456, states);
            
            
            // Stats initialization
            FlightState statState_1 = new FlightState("123TestMayDay", "Portugal", "123", "123" , "47.123141", "-3.127371", "52.7", "-0.23", "false");
            FlightState statState_2 = new FlightState("123TestMayDay", "Portugal", "123", "123" , "47.123141", "-3.127371", "60.2", "-0.23", "false");
            FlightState statState_3 = new FlightState("123TestMayDay", "Portugal", "123", "123" , "47.123141", "-3.127371", "56.9", "-0.23", "false");
            
            // The average speed should be 56.6
            
            List<FlightState> stats_states_1 = new ArrayList<>(); stats_states_1.add(statState_1);
            List<FlightState> stats_states_2 = new ArrayList<>(); stats_states_2.add(statState_2);
            List<FlightState> stats_states_3 = new ArrayList<>(); stats_states_3.add(statState_3);
            
            statsTestCases.add(new FlightStateMessage(100, stats_states_1));
            statsTestCases.add(new FlightStateMessage(101, stats_states_2));
            statsTestCases.add(new FlightStateMessage(102, stats_states_3));

            
            
            
    }
    
    @Test
    public void testInsertion() throws Exception {
        
        controller.saveOrUpdateFlightStateMessage(toSave);
        
        FlightStateMessage fsm = controller.getFlightStateMessageByTime(123456);
        
        assertTrue(fsm.getStates().get(0).equals(toCheck));
    }
    
    @Test
    public void testStats() throws Exception {
        
        for(FlightStateMessage message : statsTestCases) {
            controller.saveOrUpdateFlightStateMessage(message);
        }
        
        //Thread.sleep(5000);
        
        //FlightStats stats = controller.getFlightStatsByIcao24("123TestMayDay");
                
        //assertTrue(stats.avg_speed == 56.6);
    }
    
    @After
    public void remove() {
        controller.deleteByTime(123456);
        controller.deleteByTime(100);
        controller.deleteByTime(101);
        controller.deleteByTime(102);
        
    }
        

}
