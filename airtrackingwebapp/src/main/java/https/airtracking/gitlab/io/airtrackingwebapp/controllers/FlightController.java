package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {

    @GetMapping("/flight")
    public String flight(Model model, @RequestParam(required = false) String flight, @RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        
        if(flight != null){
            model.addAttribute("name", "flight");
            model.addAttribute("maxspeed", "TODO1");
            model.addAttribute("avgspeed", "TODO2");
            model.addAttribute("maxalt", "TODO3");
            model.addAttribute("avgvertical", "TODO4");
            model.addAttribute("number", "TODO5");
        }
        
        model.addAttribute("name", " ");
        model.addAttribute("maxspeed", " ");
        model.addAttribute("avgspeed", " ");
        model.addAttribute("maxalt", " ");
        model.addAttribute("avgvertical", " ");
        model.addAttribute("number", " ");
 
        return "flight";
    }

    /*@GetMapping("/flightdata")
	public String flightData(Model model) {
		return "flightData";
    }*/
}
