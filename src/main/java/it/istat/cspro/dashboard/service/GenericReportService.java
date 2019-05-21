package it.istat.cspro.dashboard.service;

import it.istat.cspro.dashboard.dao.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Istat Cooperation Unit
 */
@Service
public class GenericReportService {

    @Autowired
    private GenericReportDao genericReportDao;

    public List<List> getGenericReport(String report, Object... params) {
        return genericReportDao.findAll(report, params);
    }

}
