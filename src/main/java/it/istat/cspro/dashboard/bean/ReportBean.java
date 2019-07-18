/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.istat.cspro.dashboard.bean;

import lombok.Data;

@Data
public class ReportBean {

    private Integer id;
    private String name;
    private String tableName;
    private boolean visible;
}
