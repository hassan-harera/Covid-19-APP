package com.whiteside.covid19.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Data {

    public int cases, deaths, recovered;
    public long updated, todayRecovered, active, critical, population;
    public int casesPerOneMillion, todayCases, todayDeaths, tests;
    public float deathsPerOneMillion, activePerOneMillion, recoveredPerOneMillion, criticalPerOneMillion, testsPerOneMillion;
    public float oneCasePerPeople, oneDeathPerPeople, oneTestPerPeople;

    //    public int affectedCountries;
    //    public Map<Object, Object> countryInfo;
    //    public String country, continent;

    public Map<String, Object> mapping() {
        Map<String, Object> map = new HashMap<>();

        Field[] allFields = getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                map.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}