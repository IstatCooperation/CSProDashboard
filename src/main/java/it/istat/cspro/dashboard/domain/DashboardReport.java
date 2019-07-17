package it.istat.cspro.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Istat Cooperation Unit
 */
@Data
@Entity
@Table(name = "DASHBOARD_REPORT")
public class DashboardReport implements Serializable {

    private static final long serialVersionUID = -2418767339520755476L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    
    @Column(name = "REPORT_VIEW", length = 1000)
    private String tableName;

    @Column(name = "LIST_ORDER")
    private int order;

    @Column(name = "IS_VISIBLE")
    private int isVisible;

    @ManyToOne
    @JoinColumn(name = "REPORT_TYPE")
    private DashboardReportType type;

}
