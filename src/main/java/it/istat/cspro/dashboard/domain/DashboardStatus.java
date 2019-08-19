package it.istat.cspro.dashboard.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author Istat Cooperation Unit
 */
@Data
@Entity
@Table(name = "DASHBOARD_STATUS")
public class DashboardStatus implements Serializable {

    private static final long serialVersionUID = -2418767339520755476L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "START_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "LAST_UPDATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Column(name = "IS_RUNNING")
    private int isRunning;

}
