package it.istat.cspro.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Istat Cooperation Unit
 */
@Data
@Entity
@Table(name = "DASHBOARD_REPORT_TYPE")
public class DashboardReportType implements Serializable {

    private static final long serialVersionUID = -2418767889544755476L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

}
