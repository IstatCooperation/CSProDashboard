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
@Table(name = "CSPRO2SQL_REPORT")
public class CSPro2SqlReport implements Serializable {

    private static final long serialVersionUID = -2418767889520755476L;

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "LIST_ORDER")
    private int listOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListOrder() {
        return listOrder;
    }

    public void setListOrder(int listOrder) {
        this.listOrder = listOrder;
    }

}
