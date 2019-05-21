package it.istat.cspro.dashboard.dao;

import it.istat.cspro.dashboard.domain.RHouseholdExpectedBase;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RHouseholdExpectedDao {

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public RHouseholdExpectedDao(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public List<RHouseholdExpectedBase> getExpectedReportBy(final String table) {
        return (List<RHouseholdExpectedBase>) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException {
                SQLQuery query = sn.createSQLQuery("SELECT `ID`,`name`,`field`,`freshlist`,`expected`,`field_freshlist`,`field_expected`,`freshlist_expected` FROM `mr_household_expected_by_" + table + "`");
                query.addScalar("id", StandardBasicTypes.INTEGER)
                        .addScalar("name", StandardBasicTypes.STRING)
                        .addScalar("field", StandardBasicTypes.INTEGER)
                        .addScalar("freshlist", StandardBasicTypes.INTEGER)
                        .addScalar("expected", StandardBasicTypes.INTEGER)
                        .addScalar("field_freshlist", StandardBasicTypes.DOUBLE)
                        .addScalar("field_expected", StandardBasicTypes.DOUBLE)
                        .addScalar("freshlist_expected", StandardBasicTypes.DOUBLE)
                        .setResultTransformer(Transformers.aliasToBean(RHouseholdExpectedBase.class));
                return query.list();
            }
        });
    }

}
