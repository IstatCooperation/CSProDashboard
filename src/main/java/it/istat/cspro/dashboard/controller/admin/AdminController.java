package it.istat.cspro.dashboard.controller.admin;

import it.istat.cspro.dashboard.controller.BaseController;
import it.istat.cspro.dashboard.domain.DashboardReport;
import it.istat.cspro.dashboard.service.DashboardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController extends BaseController {
    
     @Autowired
    private DashboardService dashboardService;
    
    @RequestMapping(value = "/admin/{reportPage}")
    public String reportPage(@PathVariable("reportPage") String reportPage) {
        return "admin/" + reportPage;
    }

    @RequestMapping(value = "/admin/settings")
    public String settingsPage(Model model) {
        
        List<DashboardReport> reports = dashboardService.getReports();
        
        model.addAttribute("dashBoardreports", reports);
        
        return "admin/settings";
    }
}
