/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flight")
public class FlightController {

	@GetMapping("/flight")
	public String flight(Model model) {
		return "flight.html";
	}
        
        /*@GetMapping("/flight/data")
	public String flightData(Model model) {
		return "flightData.html";
	}*/

}