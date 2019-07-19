package it.istat.cspro.dashboard.rest;

import it.istat.cspro.dashboard.domain.DashboardVariable;
import it.istat.cspro.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import it.istat.cspro.dashboard.service.NotificationService;
import it.istat.cspro.dashboard.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class DictionaryController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "/rest/meta/territory")
    public String[] territoryVariables() {

        String[] territoryVar = null;

        List<DashboardVariable> variables = dashboardService.getTerritoryVariables();

        if (variables != null && !variables.isEmpty()) {
            territoryVar = new String[variables.size()];
            for (int i = 0; i < variables.size(); i++) {
                territoryVar[i] = variables.get(i).getName();
            }
        }

        return territoryVar;
    }

}
