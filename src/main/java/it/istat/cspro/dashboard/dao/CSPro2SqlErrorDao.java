package it.istat.cspro.dashboard.dao;

import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.CSPro2SqlError;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CSPro2SqlErrorDao extends JpaRepository<CSPro2SqlError, String> {

}
