package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrackController {

    @GetMapping("/track")
    public String track(Model model) {
        return "track";
    }
    
    @GetMapping("/trackairport")
    public String trackAirport(@RequestParam("country") String countryName, Model model) {
        model.addAttribute("country", countryName.toUpperCase());
        return "trackairport";
    }
    
    @GetMapping("/trackflight")
    public String trackFlight(@RequestParam("flight") String flightName, Model model) {
        model.addAttribute("flight", flightName.toUpperCase());
        return "trackflight";
    }

}
