/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.istat.cspro.dashboard.territory.dao;

import it.istat.cspro.dashboard.dao.DashboardVariableDao;
import it.istat.cspro.dashboard.domain.DashboardConcept;
import it.istat.cspro.dashboard.domain.DashboardVariable;
import java.util.HashMap;
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

    private static final int TERRITORY_CONCEPT = 4;
    private static final int TERRITORY_ROOT = 0;

    @Autowired
    DashboardVariableDao dashboardVariableDao;

    @Autowired
    private EntityManager em;

    public HashMap<String, String> getTerritoryFilter(Integer[] codes) {

        HashMap<String, String> map = new HashMap();

        DashboardConcept territoryConcept = new DashboardConcept();
        territoryConcept.setId(TERRITORY_CONCEPT);

        List<DashboardVariable> hierarchy = dashboardVariableDao.findByConcept(territoryConcept);

        String queryString = "";
        String whereString = "WHERE ";

        if (codes[0] == TERRITORY_ROOT) { //root level
            queryString = "SELECT distinct " + hierarchy.get(0).getName() + "_NAME, " + hierarchy.get(0).getName() + " FROM dashboard_test.territory";
        } else{
            queryString = "SELECT distinct ";
            for(int i = 0; i < codes.length; i++){
                if(i == codes.length - 1){ //is last
                    whereString += hierarchy.get(i).getName() + " = "+ codes[i] + ";"; 
                } else{
                    whereString += hierarchy.get(i).getName() + " = "+ codes[i] + " and "; 
                }
            }
            queryString = "SELECT distinct " + hierarchy.get(codes.length).getName() + ", " + hierarchy.get(codes.length).getName() + "_NAME FROM dashboard_test.territory " + whereString;
        }
        
        Query query = em.createNativeQuery(queryString);

        List<Object[]> territory = query.getResultList();

        for (Object[] result : territory) {
            map.put(result[0].toString(), result[1].toString());
        }

        return map;

    }
    
    
     public HashMap<String, String> getTerritory(Integer[] codes) {

        HashMap<String, String> map = new HashMap();

        DashboardConcept territoryConcept = new DashboardConcept();
        territoryConcept.setId(TERRITORY_CONCEPT);

        List<DashboardVariable> hierarchy = dashboardVariableDao.findByConcept(territoryConcept);

        String queryString = "";
        String whereString = "WHERE ";

        if (codes[0] == TERRITORY_ROOT) { //root level
            queryString = "SELECT distinct " + hierarchy.get(0).getName() + "_NAME, " + hierarchy.get(0).getName() + " FROM dashboard_test.territory";
        } else{
            queryString = "SELECT distinct ";
            for(int i = 0; i < codes.length; i++){
                if(i == codes.length - 1){ //is last
                    whereString += hierarchy.get(i).getName() + " = "+ codes[i] + ";"; 
                } else{
                    whereString += hierarchy.get(i).getName() + " = "+ codes[i] + " and "; 
                }
            }
            queryString = "SELECT distinct " + hierarchy.get(codes.length).getName() + ", " + hierarchy.get(codes.length).getName() + "_NAME FROM dashboard_test.territory " + whereString;
        }
        
        Query query = em.createNativeQuery(queryString);

        List<Object[]> territory = query.getResultList();

        for (Object[] result : territory) {
            map.put(result[0].toString(), result[1].toString());
        }

        return map;

    }

}
