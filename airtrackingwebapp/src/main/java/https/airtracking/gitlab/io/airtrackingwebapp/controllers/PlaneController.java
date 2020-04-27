package https.airtracking.gitlab.io.airtrackingwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaneController {

    @GetMapping("/plane")
    public String plane(Model model) {
        return "plane.html";
    }
    
}