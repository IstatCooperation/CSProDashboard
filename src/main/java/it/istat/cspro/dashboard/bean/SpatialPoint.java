package it.istat.cspro.dashboard.bean;

import lombok.Data;

@Data
public class SpatialPoint {   
    private Double lat;
    private Double lon;
    
    public SpatialPoint(Double lat, Double lon){
        this.lat = lat;
        this.lon = lon;
    }
}
