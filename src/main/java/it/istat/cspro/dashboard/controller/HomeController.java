package it.istat.cspro.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

}
