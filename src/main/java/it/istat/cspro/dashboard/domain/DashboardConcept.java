package it.istat.cspro.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DASHBOARD_META_CONCEPT")
public class DashboardConcept implements Serializable {

    private static final long serialVersionUID = 0x5ae066eea5ebf5e6L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "NOTE", length = 1000)
    private String note;

    @Column(name = "VAR_ORDER")
    private Integer order;

}
