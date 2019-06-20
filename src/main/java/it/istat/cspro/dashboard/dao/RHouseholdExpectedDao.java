package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.RHouseholdExpectedBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RHouseholdExpectedDao {
    
    @Autowired
    private EntityManager em;

    
    public List<RHouseholdExpectedBase> getExpectedReportBy(final String table) {
        Query query = em.createNativeQuery("SELECT `ID`,`name`,`field`,`freshlist`,`expected`,`field_freshlist`,`field_expected`,`freshlist_expected` FROM `mr_household_expected_by_" + table + "`", RHouseholdExpectedBase.class);        
        return query.getResultList();
    }

}
