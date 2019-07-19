package it.istat.cspro.dashboard.territory.controller;

import it.istat.cspro.dashboard.controller.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TerritoryController extends BaseController {

    @RequestMapping("/gis/territory")
    public String territoryHome(Model model) {
        return "gis/territory";
    }
    
    @RequestMapping("/gis/map")
    public String mapHome(Model model) {
        return "gis/map";
    }

}
