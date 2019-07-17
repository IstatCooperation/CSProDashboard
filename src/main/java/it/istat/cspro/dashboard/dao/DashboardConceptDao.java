package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.DashboardConcept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public interface DashboardConceptDao extends JpaRepository<DashboardConcept, Integer>{
    
}
