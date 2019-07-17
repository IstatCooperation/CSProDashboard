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
    private CSPro2SqlErrorDao cSPro2SqlErrorDao;
    @Autowired
    private DashboardInfoDao dashboardInfoDao;
    @Autowired
    private DashboardVariableDao dashboardVariableDao;
    @Autowired
    private DashboardUnitDao dashboardUnitDao;
    @Autowired
    private DashboardConceptDao dashboardConceptDao;


    public List<DashboardReport> getReports() {
        return dashboardReportDao.findAll();
    }

    public List<CSPro2SqlError> getErrors() {
        return cSPro2SqlErrorDao.findAll();
    }

    public DashboardInfo getDashboardInfo() {
        return dashboardInfoDao.findById(0).orElse(null);
    }

    public List<DashboardVariable> getVariables() {
        return dashboardVariableDao.findAll();
    }

    public List<DashboardUnit> getUnits() {
        return dashboardUnitDao.findAll();
    }
    
    public List<DashboardConcept> getConcepts() {
        return dashboardConceptDao.findAll();
    }
    
}
