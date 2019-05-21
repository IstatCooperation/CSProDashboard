package it.istat.cspro.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Istat Cooperation Unit
 */
@Entity
@Table(name = "DASHBOARD_INFO")
public class DashboardInfo implements Serializable {
    
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "LISTING")
    private boolean listing;
    @Column(name = "EXPECTED")
    private boolean expected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isListing() {
        return listing;
    }

    public void setListing(boolean listing) {
        this.listing = listing;
    }

    public boolean isExpected() {
        return expected;
    }

    public void setExpected(boolean expected) {
        this.expected = expected;
    }
    
}
