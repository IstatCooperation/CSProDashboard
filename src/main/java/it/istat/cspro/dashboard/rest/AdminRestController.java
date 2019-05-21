package it.istat.cspro.dashboard.rest;

import it.istat.cspro.dashboard.service.DashboardService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "/rest/admin/list/{key}")
    public List listReport(@PathVariable("key") String key) {
        switch (key) {
            case "errors":
                return dashboardService.getErrors();
        }
        return new ArrayList(0);
    }

    @RequestMapping(value = "/rest/admin/{key}")
    public Object objectReport(@PathVariable("key") String key) {
        switch (key) {
        }
        return new ArrayList(0);
    }

}
