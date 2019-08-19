package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.DashboardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DashboardStatusDao extends JpaRepository<DashboardStatus, Integer> {
    
}
