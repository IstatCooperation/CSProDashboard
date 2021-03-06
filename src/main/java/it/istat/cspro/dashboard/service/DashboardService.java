package it.istat.cspro.dashboard.service;

import it.istat.cspro.dashboard.dao.CSPro2SqlErrorDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.istat.cspro.dashboard.dao.DashboardConceptDao;
import it.istat.cspro.dashboard.dao.DashboardInfoDao;
import it.istat.cspro.dashboard.dao.DashboardUnitDao;
import it.istat.cspro.dashboard.dao.DashboardVariableDao;
import it.istat.cspro.dashboard.domain.CSPro2SqlError;
import it.istat.cspro.dashboard.domain.DashboardReport;
import it.istat.cspro.dashboard.domain.DashboardConcept;
import it.istat.cspro.dashboard.domain.DashboardInfo;
import it.istat.cspro.dashboard.domain.DashboardUnit;
import it.istat.cspro.dashboard.domain.DashboardVariable;
import it.istat.cspro.dashboard.dao.DashboardReportDao;
import it.istat.cspro.dashboard.dao.DashboardStatusDao;
import it.istat.cspro.dashboard.domain.DashboardStatus;
import it.istat.cspro.dashboard.forms.ReportUpdateForm;
import it.istat.cspro.dashboard.territory.dao.TerritoryDao;

/**
 *
 * @author Istat Cooperation Unit
 */
@Service
@Transactional
public class DashboardService {

    @Autowired
    private DashboardReportDao dashboardReportDao;
    @Autowired
    private DashboardStatusDao dashboardStatusDao;
    @Autowired
    private CSPro2SqlErrorDao cSPro2SqlErrorDao;
    @Autowired
    private DashboardInfoDao dashboardInfoDao;
    @Autowired
    private DashboardVariableDao dashboardVariableDao;
    @Autowired
    private DashboardUnitDao dashboardUnitDao;
    @Autowired
    private DashboardConceptDao dashboardConceptDao;
    @Autowired
    private TerritoryDao territoryDao;

    public List<DashboardReport> getReports() {
        return dashboardReportDao.findAll();
    }

    public List<CSPro2SqlError> getErrors() {
        return cSPro2SqlErrorDao.findAll();
    }

    public DashboardInfo getDashboardInfo() {
        return dashboardInfoDao.findById(0).orElse(null);
    }

    public DashboardStatus getDashboardStatus(){
        return dashboardStatusDao.findById(1).orElse(null);
    }
    
    public List<DashboardVariable> getVariables() {
        return dashboardVariableDao.findAll();
    }

    public List<DashboardVariable> getTerritoryVariables() {
        return territoryDao.getHouseholdTerritoryVariables();
    }
    
    public List<DashboardUnit> getUnits() {
        return dashboardUnitDao.findAll();
    }

    public List<DashboardConcept> getConcepts() {
        return dashboardConceptDao.findAll();
    }

    public DashboardReport updateReport(ReportUpdateForm form) {

        DashboardReport report = dashboardReportDao.findById(form.getId()).orElse(null);

        if (report != null) {
            report.setName(form.getName());
            report.setIsVisible(form.getDisplay());
            dashboardReportDao.save(report);
        }

        return report;

    }
}
