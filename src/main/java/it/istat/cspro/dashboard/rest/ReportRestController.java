package it.istat.cspro.dashboard.rest;

import it.istat.cspro.dashboard.service.GenericReportService;
import it.istat.cspro.dashboard.service.ProcessReportService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportRestController {

    private static final Pattern HOUSEHOLD_BY_PATTERN = Pattern.compile("^householdBy(.*)$");
    private static final Map<String, String> CACHE = new HashMap<>();

    @Autowired
    private GenericReportService genericReportService;
    @Autowired
    private ProcessReportService processService;

    @RequestMapping(value = "/rest/report/{key}")
    public Object objectReport(@PathVariable("key") String key, @RequestParam(name = "region", required = false) Integer region) {
        try {
            Matcher m = HOUSEHOLD_BY_PATTERN.matcher(key);
            if (m.find()) {
                return processService.getHouseholdExpectedBy(m.group(1));
            } else if (region != null) {
                return genericReportService.getGenericReport(getReportName(key), "REGION", region);
            } else {
                return genericReportService.getGenericReport(getReportName(key));
            }
        } catch (Exception e) {
            return new ArrayList(0);
        }
    }
    
    @RequestMapping(value = "/rest/report/household/search/{keys}")
    public Object objectReport(@PathVariable("keys") Integer[] keys) {
        return processService.getEaExpectedReportBy(keys);
    }
    

    private static String getReportName(String name) {
        if (CACHE.containsKey(name)) {
            return CACHE.get(name);
        }
        String result = "MR_";
        char[] a = name.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 'A' && a[i] <= 'Z') {
                result += "_" + a[i];
            } else {
                result += a[i];
            }
        }
        CACHE.put(name, result);
        return result;
    }

}
