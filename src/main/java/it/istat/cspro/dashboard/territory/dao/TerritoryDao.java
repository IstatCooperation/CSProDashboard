package it.istat.cspro.dashboard.territory.dao;

import it.istat.cspro.dashboard.dao.DashboardVariableDao;
import it.istat.cspro.dashboard.domain.DashboardConcept;
import it.istat.cspro.dashboard.domain.DashboardVariable;
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
public class TerritoryDao {

    public static final int TERRITORY_CONCEPT = 4;
    private static final int TERRITORY_ROOT = 0;

    @Autowired
    DashboardVariableDao dashboardVariableDao;

    @Autowired
    private EntityManager em;

    public List<Object[]> getTerritoryFilter(Integer[] codes) {

        DashboardConcept territoryConcept = new DashboardConcept();
        territoryConcept.setId(TERRITORY_CONCEPT);

        List<DashboardVariable> hierarchy = dashboardVariableDao.findByConcept(territoryConcept);

        String queryString;
        String whereCondition = "WHERE ";

        if (codes[0] == TERRITORY_ROOT) { //root level
            queryString = "SELECT distinct " + hierarchy.get(0).getName() + "_NAME, " + hierarchy.get(0).getName() + " FROM territory";
        } else {
            for (int i = 0; i < codes.length; i++) {
                whereCondition += hierarchy.get(i).getName() + " = " + codes[i];
                if (i == codes.length - 1) { //is last
                    whereCondition += ";";
                } else {
                    whereCondition += " and ";
                }
            }
            queryString = "SELECT distinct " + hierarchy.get(codes.length).getName() + "_NAME, "
                    + hierarchy.get(codes.length).getName() + " FROM territory " + whereCondition;
        }

        Query query = em.createNativeQuery(queryString);

        return query.getResultList();

    }

    public List<Object[]> getTerritory(Integer[] codes) {

        DashboardConcept territoryConcept = new DashboardConcept();
        territoryConcept.setId(TERRITORY_CONCEPT);

        List<DashboardVariable> hierarchy = dashboardVariableDao.findByConcept(territoryConcept);

        String fields = "";

        for (DashboardVariable var : hierarchy) {
            fields += var.getName() + "_NAME, " + var.getName() + ", ";
        }

        fields = fields.substring(0, fields.lastIndexOf(",")); //remove last comma

        String queryString;
        String whereCondition = "";

        if (codes[0] != TERRITORY_ROOT) {
            whereCondition = "WHERE ";
            for (int i = 0; i < codes.length; i++) {
                if (i == codes.length - 1) { //is last
                    whereCondition += hierarchy.get(i).getName() + " = " + codes[i] + ";";
                } else {
                    whereCondition += hierarchy.get(i).getName() + " = " + codes[i] + " and ";
                }
            }
        } 

        queryString = "SELECT " + fields + " FROM territory " + whereCondition;

        Query query = em.createNativeQuery(queryString);

        return query.getResultList();

    }

}
