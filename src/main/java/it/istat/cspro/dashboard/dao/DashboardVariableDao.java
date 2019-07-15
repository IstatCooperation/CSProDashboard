package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.DashboardVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public interface DashboardVariableDao extends JpaRepository<DashboardVariable, Integer>{
    
}
