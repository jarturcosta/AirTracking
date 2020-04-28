package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
