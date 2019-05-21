package it.istat.cspro.dashboard.controller.admin;

import it.istat.cspro.dashboard.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController extends BaseController {
    
    @RequestMapping(value = "/admin/{reportPage}")
    public String reportPage(@PathVariable("reportPage") String reportPage) {
        return "admin/" + reportPage;
    }

}
