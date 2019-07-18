package it.istat.cspro.dashboard.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReportUpdateForm {

    @NotNull
    private Integer id;
    
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    
    @NotNull
    @Size(min = 2, max = 100)
    private String table;   
    
    @NotNull
    private int display;

    public ReportUpdateForm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    
}
