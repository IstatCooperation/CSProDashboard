package it.istat.cspro.dashboard.territory.dao;

import it.istat.cspro.dashboard.territory.bean.SpatialPoint;
import it.istat.cspro.dashboard.dao.DashboardUnitDao;
import it.istat.cspro.dashboard.dao.DashboardVariableDao;
import it.istat.cspro.dashboard.domain.DashboardConcept;
import it.istat.cspro.dashboard.domain.DashboardUnit;
import it.istat.cspro.dashboard.domain.DashboardVariable;
import it.istat.cspro.dashboard.utils.Utility;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    DashboardVariableDao dashboardVariableDao;

    @Autowired
    DashboardUnitDao dashboardUnitDao;

    @Autowired
    private EntityManager em;

    public List<Object[]> getTerritoryFilter(Integer[] codes) {

        List<DashboardVariable> hierarchy = getHouseholdTerritoryVariables();

        String queryString;
        String whereCondition = "WHERE ";

        if (codes[0] == Utility.TERRITORY_ROOT) { //root level
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

        return em.createNativeQuery(queryString).getResultList();

    }

    public List<Object[]> getTerritory(Integer[] codes) {

        DashboardConcept territoryConcept = new DashboardConcept();
        territoryConcept.setId(Utility.CONCEPT_TERRITORY_ID);

        List<DashboardVariable> hierarchy = dashboardVariableDao.findByConcept(territoryConcept);

        String fields = "";

        for (DashboardVariable var : hierarchy) {
            fields += var.getName() + "_NAME, " + var.getName() + ", ";
        }

        fields = fields.substring(0, fields.lastIndexOf(",")); //remove last comma

        String queryString;
        String whereCondition = "";

        if (codes[0] != Utility.TERRITORY_ROOT) {
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

        return em.createNativeQuery(queryString).getResultList();

    }

    public List<Object[]> getNonMatchingHousehold() {

        DashboardConcept householdConcept = new DashboardConcept();
        householdConcept.setId(Utility.CONCEPT_HOUSEHOLD_ID);
        DashboardUnit householdUnit = dashboardUnitDao.findByConcept(householdConcept).get(0);

        String queryString = "SELECT @FIELDS FROM " + householdUnit.getTableName() + " h NATURAL LEFT JOIN territory WHERE @ROOT_TERRITORY_NAME IS NULL;";
        String fields = "h.ID";
        String root_territory_name = "";
        List<DashboardVariable> hierarchy = getHouseholdTerritoryVariables();

        boolean isFirst = true;
        for (DashboardVariable variable : hierarchy) {
            if (isFirst) {
                root_territory_name = variable.getName() + "_NAME";
                isFirst = false;
            }
            fields += ", " + variable.getName();
        }
        queryString = queryString.replace("@FIELDS", fields);
        queryString = queryString.replace("@ROOT_TERRITORY_NAME", root_territory_name);

        Query query = em.createNativeQuery(queryString);

        return query.getResultList();

    }

    public Integer getNonMatchingHouseholdCount() {
        BigInteger count;
        DashboardConcept householdConcept = new DashboardConcept();
        householdConcept.setId(Utility.CONCEPT_HOUSEHOLD_ID);
        DashboardUnit householdUnit = dashboardUnitDao.findByConcept(householdConcept).get(0);

        String queryString = "SELECT COUNT(*) FROM " + householdUnit.getTableName() + " h NATURAL LEFT JOIN territory WHERE @ROOT_TERRITORY_NAME IS NULL;";

        List<DashboardVariable> hierarchy = getHouseholdTerritoryVariables();
        queryString = queryString.replace("@ROOT_TERRITORY_NAME", hierarchy.get(0).getName() + "_NAME");

        Query query = em.createNativeQuery(queryString);

        count = (BigInteger) query.getSingleResult();

        return count.intValue();

    }

    public List<DashboardVariable> getHouseholdTerritoryVariables() {
        DashboardConcept territoryConcept = new DashboardConcept();
        territoryConcept.setId(Utility.CONCEPT_TERRITORY_ID);
        DashboardConcept householdConcept = new DashboardConcept();
        householdConcept.setId(Utility.CONCEPT_HOUSEHOLD_ID);
        DashboardUnit householdUnit = dashboardUnitDao.findByConcept(householdConcept).get(0);

        return dashboardVariableDao.findByConceptAndUnit(territoryConcept, householdUnit);

    }

    public List<DashboardVariable> getLatitudeLongitudeVariables() {
        List<DashboardVariable> variables = new ArrayList<>();
        DashboardConcept latitudeConcept = new DashboardConcept();
        latitudeConcept.setId(Utility.CONCEPT_LATITUDE_ID);
        DashboardConcept longitudeConcept = new DashboardConcept();
        longitudeConcept.setId(Utility.CONCEPT_LONGITUDE_ID);
        DashboardVariable latitudeVar = dashboardVariableDao.findByConcept(latitudeConcept).get(0);
        DashboardVariable longitudeVar = dashboardVariableDao.findByConcept(longitudeConcept).get(0);
        variables.add(latitudeVar);
        variables.add(longitudeVar);
        return variables;

    }

    public List<SpatialPoint> getSpatialPoints() {
        List<SpatialPoint> points = new ArrayList<>();
        List<DashboardVariable> variables = getLatitudeLongitudeVariables();

        String latitude = "", longitude = "", table = "";
        if (!variables.isEmpty()) {
            latitude = variables.get(0).getName();
            longitude = variables.get(1).getName();
            table = variables.get(0).getUnit().getTableName();
        }

        String queryString = "SELECT " + latitude + ", " + longitude + " FROM " + table;
        BigDecimal lat, lon;
        List<Object[]> results = em.createNativeQuery(queryString).getResultList();
        for (Object[] result : results) {
            lat = (BigDecimal) result[0];
            lon = (BigDecimal) result[1];
            points.add(new SpatialPoint(lat.doubleValue(), lon.doubleValue()));
        }

        return points;
    }

}
