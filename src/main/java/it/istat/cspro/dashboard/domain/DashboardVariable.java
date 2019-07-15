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
@Table(name = "DASHBOARD_META_VARIABLE")
public class DashboardVariable implements Serializable {
    
    private static final long serialVersionUID = 0x5ae011eea5ebf5e6L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "NOTE", length = 1000)
    private String note;
    
    @Column(name = "TYPE", length = 45)
    private String type;

    @Column(name = "VAR_ORDER")
    private Integer order;

    @Column(name = "UNIT_ID")
    private Integer unitId;
    
    @ManyToOne
    @JoinColumn(name = "CONCEPT_ID")
    private DashboardConcept concept;
    
}
