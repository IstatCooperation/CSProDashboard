package it.istat.cspro.dashboard.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Istat Cooperation Unit
 */
@Entity
public class RHouseholdExpectedBase implements Serializable {

    private static final long serialVersionUID = 964092318764339808L;

    @Id
    private int id;
    private String name;
    private Integer field;
    private Integer freshlist;
    private Integer expected;
    private Double field_freshlist;
    private Double field_expected;
    private Double freshlist_expected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getSplitted() {
        List values = new ArrayList(Arrays.asList(name.split("#")));
        values.add(field);
        values.add(freshlist);
        values.add(expected);
        values.add(field_freshlist);
        values.add(field_expected);
        values.add(freshlist_expected);
        return values;
    }

    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public Integer getFreshlist() {
        return freshlist;
    }

    public void setFreshlist(Integer freshlist) {
        this.freshlist = freshlist;
    }

    public Integer getExpected() {
        return expected;
    }

    public void setExpected(Integer expected) {
        this.expected = expected;
    }

    public Double getField_freshlist() {
        return field_freshlist;
    }

    public void setField_freshlist(Double field_freshlist) {
        this.field_freshlist = field_freshlist;
    }

    public Double getField_expected() {
        return field_expected;
    }

    public void setField_expected(Double field_expected) {
        this.field_expected = field_expected;
    }

    public Double getFreshlist_expected() {
        return freshlist_expected;
    }

    public void setFreshlist_expected(Double freshlist_expected) {
        this.freshlist_expected = freshlist_expected;
    }

}
