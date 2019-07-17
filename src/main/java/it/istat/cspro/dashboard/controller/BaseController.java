package it.istat.cspro.dashboard.controller;

import it.istat.cspro.dashboard.domain.DashboardReport;
import it.istat.cspro.dashboard.domain.DashboardInfo;
import it.istat.cspro.dashboard.service.DashboardService;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Istat Cooperation Unit
 */
public class BaseController {

    private static final Pattern HOUSEHOLD_BY_PATTERN = Pattern.compile("^r_household_expected_by_(.*)$");

    @Autowired
    private DashboardService service;

    @ModelAttribute("reports")
    public Set<String> getReports() {
        Set<String> reports = new HashSet<>();
        for (DashboardReport r : service.getReports()) {
            reports.add(r.getName());
        }
        return reports;
    }

    @ModelAttribute("householdReports")
    public List<String> getHouseholdReports() {
        List<String> reports = new LinkedList<>();
        for (DashboardReport r : service.getReports()) {
            Matcher m = HOUSEHOLD_BY_PATTERN.matcher(r.getName());
            if (m.find()) {
                reports.add(m.group(1));
            }
        }
        return reports;
    }

    @ModelAttribute("dashboardInfo")
    public DashboardInfo getDashboardInfo() {
        return service.getDashboardInfo();
    }

}
