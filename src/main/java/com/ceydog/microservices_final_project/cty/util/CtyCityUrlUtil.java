package com.ceydog.microservices_final_project.cty.util;

import com.ceydog.microservices_final_project.gen.util.BaseUrlUtil;

public class CtyCityUrlUtil extends BaseUrlUtil {

    public static String getUrlForCityGeoMapping(String cityName){
        return "http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&limit=1&appid=" + API_KEY;
    }

    public static String getAirPollutionUrl(double lat, double lon, Long startDateUnix, Long endDateUnix){
       return "http://api.openweathermap.org/data/2.5/air_pollution/history?lat=" + lat +
               "&lon="+ lon + "&start=" + startDateUnix + "&end=" + endDateUnix + "&appid=" + API_KEY;

    }
}
