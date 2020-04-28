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
        return "track.html";
    }
    
    @RequestMapping("/trackairport")
    public ModelAndView trackAirport(@RequestParam("country") String countryName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("country", countryName);
        System.out.println(countryName);
        mv.setViewName("trackairport.html");
        return mv;
        //return "trackairport.html";
    }
    
    @GetMapping("/trackflight")
    public String trackFlight(Model model) {
        return "trackflight.html";
    }

}
