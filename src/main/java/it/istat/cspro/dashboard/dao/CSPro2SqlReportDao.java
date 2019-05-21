package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.CSPro2SqlReport;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CSPro2SqlReportDao extends JpaRepository<CSPro2SqlReport, String> {

}
