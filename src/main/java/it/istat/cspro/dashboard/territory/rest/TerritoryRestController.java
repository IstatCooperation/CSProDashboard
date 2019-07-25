package it.istat.cspro.dashboard.territory.rest;

import it.istat.cspro.dashboard.territory.service.TerritoryService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerritoryRestController {

    @Autowired
    TerritoryService territoryService;

    @RequestMapping(value = "/rest/territory/filter/{keys}")
    public List<Object[]> filters(@PathVariable("keys") Integer[] keys) {

        List<Object[]> territoryMap = territoryService.getTerritoryfilter(keys);

        return territoryMap;
    }
    
    @RequestMapping(value = "/rest/territory/{keys}")
    public List<Object[]> territory(@PathVariable("keys") Integer[] keys) {

        List<Object[]> territoryMap = territoryService.getTerritory(keys);

        return territoryMap;
    }
    
    @RequestMapping(value = "/rest/territory/unmatch")
    public List<Object[]> nonMatchingHousehold() {

        List<Object[]> territoryMap = territoryService.getNonMatchingHousehold();

        return territoryMap;
    }
    
    @RequestMapping(value = "/rest/territory/unmatchCount")
    public Integer nonMatchingHouseholdCount() {
        return territoryService.getNonMatchingHouseholdCount();
    }

}
