package it.istat.cspro.dashboard.territory.service;

import it.istat.cspro.dashboard.territory.bean.SpatialPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.istat.cspro.dashboard.territory.dao.TerritoryDao;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Istat Cooperation Unit
 */
@Service
@Transactional
public class TerritoryService {

    @Autowired
    private TerritoryDao territoryDao;
   

    public List<Object[]> getTerritoryfilter(Integer[] codes) {
        return territoryDao.getTerritoryFilter(codes);
    }

    public List<Object[]> getTerritory(Integer[] codes) {
        return territoryDao.getTerritory(codes);
    }
    
    public List<Object[]> getNonMatchingHousehold() {
        return territoryDao.getNonMatchingHousehold();
    }
    
    public Integer getNonMatchingHouseholdCount() {
        return territoryDao.getNonMatchingHouseholdCount();
    }
    
    public List<SpatialPoint> getSpatialPoints(){
        return territoryDao.getSpatialPoints();
    }
}
