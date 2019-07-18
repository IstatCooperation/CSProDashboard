package it.istat.cspro.dashboard.controller;

import it.istat.cspro.dashboard.bean.ReportBean;
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
    private static final Integer REPORT_PROGRESS = 1;
    private static final Integer REPORT_ANALYSIS = 2;
    private static final Integer REPORT_GIS = 3;

    @Autowired
    private DashboardService service;

    @ModelAttribute("reports")
    public Set<String> getReports() {
        Set<String> reports = new HashSet<>();
        for (DashboardReport r : service.getReports()) {
            reports.add(r.getTableName());
        }
        return reports;
    }

    @ModelAttribute("analysisReports")
    public Set<ReportBean> getAnalysisReports() {
        Set<ReportBean> reports = new HashSet<>();
        ReportBean report;
        for (DashboardReport r : service.getReports()) {

            if (r.getType().getId() == REPORT_ANALYSIS) {
                report = new ReportBean();
                report.setName(r.getName());
                report.setTableName(r.getTableName());
                report.setVisible((r.getIsVisible() == 1));
                reports.add(report);
            }

        }
        return reports;
    }

    @ModelAttribute("householdReports")
    public List<ReportBean> getHouseholdReports() {
        List<ReportBean> reports = new LinkedList<>();
        ReportBean report;
        for (DashboardReport r : service.getReports()) {
            if (r.getType().getId() == REPORT_PROGRESS) {
                report = new ReportBean();
                report.setName(r.getName());
                Matcher m = HOUSEHOLD_BY_PATTERN.matcher(r.getTableName());
                if (m.find()) {
                    report.setTableName(m.group(1));
                }
                report.setVisible((r.getIsVisible() == 1));
                reports.add(report);
            }
        }
        return reports;
    }

    @ModelAttribute("dashboardInfo")
    public DashboardInfo getDashboardInfo() {
        return service.getDashboardInfo();
    }

}
