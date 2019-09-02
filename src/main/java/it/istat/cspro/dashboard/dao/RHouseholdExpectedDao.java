package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.DashboardReport;
import it.istat.cspro.dashboard.domain.DashboardReportType;
import it.istat.cspro.dashboard.domain.DashboardVariable;
import it.istat.cspro.dashboard.domain.RHouseholdExpectedBase;
import it.istat.cspro.dashboard.territory.dao.TerritoryDao;
import it.istat.cspro.dashboard.utils.Utility;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RHouseholdExpectedDao {

    @Autowired
    TerritoryDao territoryDao;

    @Autowired
    DashboardReportDao dashboardReportDao;

    @Autowired
    private EntityManager em;

    public List<RHouseholdExpectedBase> getExpectedReportBy(final String table) {

        Query query = em.createNativeQuery("SELECT `ID`,`name`,`field`,`freshlist`,`expected` FROM `mr_household_expected_by_" + table + "`");
        return getExpectedReports(query.getResultList());

    }

    public List<RHouseholdExpectedBase> getFilteredExpectedReportBy(Integer[] codes) {

        List<DashboardVariable> hierarchy = territoryDao.getHouseholdTerritoryVariables();

        DashboardReportType reportType = new DashboardReportType();
        reportType.setId(Utility.REPORT_PROGRESS_ID);
        DashboardReport report = dashboardReportDao.findByTypeAndTerritoryLevel(reportType, codes.length);

        String queryString = "SELECT `ID`,`name`,`field`,`freshlist`,`expected` FROM `" + Utility.TABLE_PREFIX_MATERIALIZED + report.getTableName() + "`";
        String whereCondition = "WHERE field is null or ";

        for (int i = 0; i < codes.length; i++) {
            whereCondition += hierarchy.get(i).getName() + " = " + codes[i];
            if (i == codes.length - 1) { //is last
                whereCondition += ";";
            } else {
                whereCondition += " and ";
            }
        }
        queryString += whereCondition;

        Query query = em.createNativeQuery(queryString);
        return getExpectedReports(query.getResultList());
    }

    public List<RHouseholdExpectedBase> getEaExpectedReportBy(Integer[] codes) {

        List<DashboardVariable> hierarchy = territoryDao.getHouseholdTerritoryVariables();

        DashboardReportType reportType = new DashboardReportType();
        reportType.setId(Utility.REPORT_PROGRESS_ID);
        List<DashboardReport> reports = dashboardReportDao.findByTypeOrderByTerritoryLevel(reportType);

        String queryString = "SELECT `ID`,`name`,`field`,`freshlist`,`expected` FROM `"
                + Utility.TABLE_PREFIX_MATERIALIZED + reports.get(reports.size() - 1).getTableName() + "`"; //GET LOWER TERRITORY LEVEL REPORT (EA LEVEL)
        String whereCondition = "WHERE field is null or ";

        for (int i = 0; i < codes.length; i++) {
            whereCondition += hierarchy.get(i).getName() + " = " + codes[i];
            if (i == codes.length - 1) { //is last
                whereCondition += ";";
            } else {
                whereCondition += " and ";
            }
        }
        queryString += whereCondition;

        Query query = em.createNativeQuery(queryString);
        return getExpectedReports(query.getResultList());
    }

    private List<RHouseholdExpectedBase> getExpectedReports(List<Object[]> results) {
        int field, freshList, expected;
        List<RHouseholdExpectedBase> households = new ArrayList<>();

        for (Object[] result : results) {
            RHouseholdExpectedBase household = new RHouseholdExpectedBase();
            household.setId((Integer) result[0]);
            household.setName((String) result[1]);

            field = (result[2] != null) ? ((BigDecimal) result[2]).intValue() : -1;
            freshList = (result[3] != null) ? ((BigDecimal) result[3]).intValue() : -1;
            expected = (result[4] != null) ? ((BigDecimal) result[4]).intValue() : -1;

            household.setField(field);
            household.setFreshlist(freshList);
            household.setExpected(expected);

            if (freshList > 0) {
                household.setField_freshlist((double) field / freshList * 100);
            } else {
                household.setField_freshlist(new Double(0));
            }
            if (expected > 0) {
                household.setField_expected((double) field / expected * 100);
                household.setFreshlist_expected((double) freshList / expected * 100);
            } else {
                household.setField_expected(new Double(0));
                household.setFreshlist_expected(new Double(0));
            }
            households.add(household);
        }

        return households;
    }

}
