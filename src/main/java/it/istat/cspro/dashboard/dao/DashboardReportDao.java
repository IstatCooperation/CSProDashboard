package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.DashboardReport;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DashboardReportDao extends JpaRepository<DashboardReport, Integer> {

}
