package it.istat.cspro.dashboard.territory.rest;

import it.istat.cspro.dashboard.territory.service.TerritoryService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerritoryRestController {

    @Autowired
    TerritoryService territoryService;

    @RequestMapping(value = "/rest/territory/filter/{keys}")
    public HashMap<String, String> filters(@PathVariable("keys") Integer[] keys) {

        HashMap<String, String> territoryMap = territoryService.getTerritoryfilter(keys);

        return territoryMap;
    }
    
    @RequestMapping(value = "/rest/territory/{keys}")
    public HashMap<String, String> territory(@PathVariable("keys") Integer[] keys) {

        HashMap<String, String> territoryMap = territoryService.getTerritory(keys);

        return territoryMap;
    }

}
