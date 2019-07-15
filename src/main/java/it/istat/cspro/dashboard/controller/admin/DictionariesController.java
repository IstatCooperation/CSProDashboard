package it.istat.cspro.dashboard.controller.admin;

import it.istat.cspro.dashboard.controller.BaseController;
import it.istat.cspro.dashboard.domain.DashboardVariable;
import it.istat.cspro.dashboard.service.DashboardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DictionariesController extends BaseController {
    
    @Autowired
    private DashboardService dashboardService;
    
    @RequestMapping(value = "/admin/dictionaries")
    public String dictionariesPage() {
        return "admin/dictionaries";
    }

    @RequestMapping(value = "/admin/meta")
    public String metadataPage(Model model) {
        
        List<DashboardVariable> variables = dashboardService.getVariables();
        
        model.addAttribute("variables", variables);
        
        return "admin/metadata";
    }
    
}
