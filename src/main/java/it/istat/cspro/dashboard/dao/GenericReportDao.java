package it.istat.cspro.dashboard.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Istat Cooperation Unit
 */
@Repository
public class GenericReportDao {

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public GenericReportDao(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public List<List> findAll(final String table, Object... params) {
        return (List<List>) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException {
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
                SQLQuery query = sn.createSQLQuery(sqlQuery.toString());
                return query.list();
            }
        });
    }

}
