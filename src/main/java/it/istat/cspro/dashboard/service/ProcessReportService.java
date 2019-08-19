package it.istat.cspro.dashboard.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.istat.cspro.dashboard.dao.RHouseholdExpectedDao;
import it.istat.cspro.dashboard.domain.RHouseholdExpectedBase;

/**
 *
 * @author Istat Cooperation Unit
 */
@Service
public class ProcessReportService {

    @Autowired
    private RHouseholdExpectedDao rHouseholdExpectedDao;

    public List<RHouseholdExpectedBase> getHouseholdExpectedBy(String table) {
        return rHouseholdExpectedDao.getExpectedReportBy(table);
    }
    
    public List<RHouseholdExpectedBase> getFilteredExpectedReportBy(Integer[] codes) {
        return rHouseholdExpectedDao.getFilteredExpectedReportBy(codes);
    }
    
    public List<RHouseholdExpectedBase> getEaExpectedReportBy(Integer[] codes) {
        return rHouseholdExpectedDao.getEaExpectedReportBy(codes);
    }

}
