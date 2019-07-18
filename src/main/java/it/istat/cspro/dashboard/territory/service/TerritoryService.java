package it.istat.cspro.dashboard.territory.service;

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
   

    public HashMap<String, String> getTerritoryfilter(Integer[] codes) {
        return territoryDao.getTerritoryFilter(codes);
    }

    public HashMap<String, String> getTerritory(Integer[] codes) {
        return territoryDao.getTerritoryFilter(codes);
    }
}