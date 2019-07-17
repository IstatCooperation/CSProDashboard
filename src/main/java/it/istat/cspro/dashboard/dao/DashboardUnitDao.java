package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.DashboardUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public interface DashboardUnitDao extends JpaRepository<DashboardUnit, Integer>{
    
}
