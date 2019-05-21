package it.istat.cspro.dashboard.controller.report;

import it.istat.cspro.dashboard.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController extends BaseController {

    @RequestMapping(value = "/report/{reportPage}")
    public String reportPage(@PathVariable("reportPage") String reportPage) {
        return "report/" + reportPage;
    }

    @RequestMapping(value = "/report/household/{reportType}")
    public String reportHouseholdPage(@PathVariable("reportType") String reportType) {
        return "report/household";
    }

    @RequestMapping(value = "/gis/{gisPage}")
    public String gisPage(@PathVariable("gisPage") String gisPage) {
        return "gis/" + gisPage;
    }

}
