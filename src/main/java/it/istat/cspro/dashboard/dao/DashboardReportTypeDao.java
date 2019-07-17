package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.DashboardReportType;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DashboardReportTypeDao extends JpaRepository<DashboardReportType, String> {

}
