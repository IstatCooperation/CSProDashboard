package it.istat.cspro.dashboard.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public class GenericReportDao {
    
    @Autowired
    private EntityManager em;
   
    public List<List> findAll(final String table, Object... params) {        
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT * FROM `").append(table).append("`");
        if (params.length > 0) {
            sqlQuery.append(" WHERE ");
            for (int i = 0; i < params.length; i += 2) {
                if (i > 0) {
                    sqlQuery.append(" AND ");
                }
                sqlQuery.append(params[0]).append(" = ").append(params[1]);
            }
        }
        Query query = em.createNativeQuery(sqlQuery.toString());
        return query.getResultList();
    }

}
