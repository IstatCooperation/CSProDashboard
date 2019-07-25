/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.istat.cspro.dashboard.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Istat Cooperation Unit
 */
public class Utility {

    //cspro2sql concepts
    public static final String CONCEPT_HOUSEHOLD = "household";
    public static final String CONCEPT_LISTING = "listing";
    public static final String CONCEPT_EXPECTED = "expected";
    public static final String CONCEPT_TERRITORY = "territory";
    public static final String CONCEPT_INDIVIDUAL = "individual";
    public static final String CONCEPT_AGE = "age";
    public static final String CONCEPT_SEX = "sex";
    public static final String CONCEPT_RELIGION = "religion";
    public static final String CONCEPT_LATITUDE = "latitude";
    public static final String CONCEPT_LONGITUDE = "longitude";
    public static final String CONCEPT_EXPECTED_QUEST = "expectedQuest";

    public static final Integer CONCEPT_HOUSEHOLD_ID = 1;
    public static final Integer CONCEPT_LISTING_ID = 2;
    public static final Integer CONCEPT_EXPECTED_ID = 3;
    public static final Integer CONCEPT_TERRITORY_ID = 4;
    public static final Integer CONCEPT_INDIVIDUAL_ID = 5;
    public static final Integer CONCEPT_AGE_ID = 6;
    public static final Integer CONCEPT_SEX_ID = 7;
    public static final Integer CONCEPT_RELIGION_ID = 8;
    public static final Integer CONCEPT_LATITUDE_ID = 9;
    public static final Integer CONCEPT_LONGITUDE_ID = 10;
    public static final Integer CONCEPT_EXPECTED_QUEST_ID = 11;
    
    public static final Integer TERRITORY_ROOT = 0;
    
    public static final Map<Integer, String> conceptMap = getConcepts();

    private static Map<Integer, String> getConcepts() {
        Map<Integer, String> tmpMap = new HashMap<>();

        tmpMap.put(CONCEPT_HOUSEHOLD_ID, CONCEPT_HOUSEHOLD);
        tmpMap.put(CONCEPT_LISTING_ID, CONCEPT_LISTING);
        tmpMap.put(CONCEPT_EXPECTED_ID, CONCEPT_EXPECTED);
        tmpMap.put(CONCEPT_TERRITORY_ID, CONCEPT_TERRITORY);
        tmpMap.put(CONCEPT_INDIVIDUAL_ID, CONCEPT_INDIVIDUAL);
        tmpMap.put(CONCEPT_AGE_ID, CONCEPT_AGE);
        tmpMap.put(CONCEPT_SEX_ID, CONCEPT_SEX);
        tmpMap.put(CONCEPT_RELIGION_ID, CONCEPT_RELIGION);
        tmpMap.put(CONCEPT_LATITUDE_ID, CONCEPT_LATITUDE);
        tmpMap.put(CONCEPT_LONGITUDE_ID, CONCEPT_LONGITUDE);
        tmpMap.put(CONCEPT_EXPECTED_QUEST_ID, CONCEPT_EXPECTED_QUEST);

        return tmpMap;
    }

}
