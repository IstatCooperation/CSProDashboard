package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.DashboardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public interface DashboardInfoDao extends JpaRepository<DashboardInfo, Integer> {

}
