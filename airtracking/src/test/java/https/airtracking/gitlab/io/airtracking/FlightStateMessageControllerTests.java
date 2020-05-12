/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jarturcosta
 */
@SpringBootTest
public class FlightStateMessageControllerTests {
    
    public FlightStateMessageControllerTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllFlightStateMessages method, of class FlightStateMessageController.
     */
    @Test
    public void testGetAllFlightStateMessages() {
        System.out.println("getAllFlightStateMessages");
        FlightStateMessageController instance = new FlightStateMessageController();
        List<FlightStateMessage> expResult = null;
        List<FlightStateMessage> result = instance.getAllFlightStateMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlightStateMessageByTime method, of class FlightStateMessageController.
     */
    @Test
    public void testGetFlightStateMessageByTime() {
        System.out.println("getFlightStateMessageByTime");
        int time = 0;
        FlightStateMessageController instance = new FlightStateMessageController();
        FlightStateMessage expResult = null;
        FlightStateMessage result = instance.getFlightStateMessageByTime(time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateFlightStateMessage method, of class FlightStateMessageController.
     */
    @Test
    public void testSaveOrUpdateFlightStateMessage() {
        FlightState toCheck = new FlightState("ab172vs", "Portugal", "15273617", "15273617" , "47.123141", "-3.127371", "203.2", "-0.23", "false");
        List<FlightState> states = new ArrayList<>();
        states.add(toCheck);
        // given
        FlightStateMessage toSave = new FlightStateMessage(123456, states);
        
        
    }

    /**
     * Test of getLastFlightStateMessage method, of class FlightStateMessageController.
     */
    @Test
    public void testGetLastFlightStateMessage() {
        System.out.println("getLastFlightStateMessage");
        FlightStateMessageController instance = new FlightStateMessageController();
        FlightStateMessage expResult = null;
        FlightStateMessage result = instance.getLastFlightStateMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
