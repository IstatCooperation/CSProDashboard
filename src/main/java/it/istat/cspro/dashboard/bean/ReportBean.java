package it.istat.cspro.dashboard.bean;

import lombok.Data;

@Data
public class ReportBean {

    private Integer id;
    private String name;
    private String tableName;
    private boolean visible;
}
