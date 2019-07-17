package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.DashboardReport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DashboardReportDao extends JpaRepository<DashboardReport, String> {

}
