package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.DashboardConcept;
import it.istat.cspro.dashboard.domain.DashboardUnit;
import it.istat.cspro.dashboard.domain.DashboardVariable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public interface DashboardVariableDao extends JpaRepository<DashboardVariable, Integer>{
    
    List<DashboardVariable> findByConcept(DashboardConcept concept);
    List<DashboardVariable> findByConceptAndUnit(DashboardConcept concept, DashboardUnit unit);
    
}
