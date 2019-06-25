package it.istat.cspro.dashboard.controller.admin;

import it.istat.cspro.dashboard.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DictionariesController extends BaseController {
    
    @RequestMapping(value = "/admin/dictionaries")
    public String dictionariesPage() {
        return "admin/dictionaries";
    }

}
