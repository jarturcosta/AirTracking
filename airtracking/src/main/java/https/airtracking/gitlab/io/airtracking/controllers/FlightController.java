/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking.controllers;

import https.airtracking.gitlab.io.airtracking.FlightStateService;
import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/flight")
public class FlightController {
    
        @Autowired
        private FlightStateService fService;
        
        @GetMapping(value = "/")
        public List<FlightStateMessage> findAll(){
            return fService.findAll();         
        }
    
        @GetMapping(value = "/{time}")
        public FlightStateMessage findByTime(@PathVariable("time") int time){
            return fService.findByTime(time);
        }
    
        @GetMapping(value = "/states/{time}")
        public List<FlightState> getFlightStatesByTime(@PathVariable("time") int time){
            return fService.getFlightStatesByTime(time);
        }

        @PostMapping(value = "/")
        public ResponseEntity<?> saveOrUpdateFlightStateMessage(@RequestBody FlightStateMessage message){
            fService.saveOrUpdateFlightStateMessage(message);
            return new ResponseEntity("Flight added successfully", HttpStatus.OK); 
        }

	/*@GetMapping("/flight")
	public String flight(Model model) {
		return "flight.html";
	}
        
        @GetMapping("/flight/data")
	public String flightData(Model model) {
		return "flightData.html";
	}*/

}