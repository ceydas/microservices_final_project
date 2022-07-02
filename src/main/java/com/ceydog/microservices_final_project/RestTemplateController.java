package com.ceydog.microservices_final_project;


import com.ceydog.microservices_final_project.cty.dto.CtyCityCoordinatesDto;
import com.ceydog.microservices_final_project.cty.util.CtyCityUrlUtil;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalDto;
import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.plt.dto.PltConcentrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DoubleNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rest-template")
public class RestTemplateController {



    @GetMapping("" )
    public String getInfoByCityName(@RequestParam(required = true, name = "city") String cityName,
                                    @RequestParam(required = false, name = "start") String startDate,
                                    @RequestParam(required = false, name = "end") String endDate)  {

        String urlForCityGeoMapping = CtyCityUrlUtil.getUrlForCityGeoMapping(cityName);

        String cityResponseString = getJSONBodyFromURL(urlForCityGeoMapping);

        CtyCityCoordinatesDto cityCoordinatesDto = getCityCoordinatesDto(cityResponseString);

        double lat = cityCoordinatesDto.getLat();
        double lon = cityCoordinatesDto.getLon();

        DteDateIntervalDto dateInterval;

        if (startDate == null || endDate == null){
            dateInterval = DateUtil.getDefaultDateInterval();
        }
        else {
            dateInterval = DateUtil.convertToDateIntervalDto(startDate, endDate);
        }

        String urlForAirPollution = CtyCityUrlUtil.getAirPollutionUrl(lat, lon, dateInterval.getStartDate(), dateInterval.getEndDate());
        String airPollutionResponseString = getJSONBodyFromURL(urlForAirPollution);
        PltConcentrationDto concentrationLevels = getConcentrationLevels(airPollutionResponseString);



        return "concentration: " + concentrationLevels.toString();

    }

    private String getJSONBodyFromURL(String url){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseString = responseEntity.getBody();

        return responseString;
    }

    private CtyCityCoordinatesDto getCityCoordinatesDto(String cityResponseString){

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = null;
        try {
            node = mapper.readTree(cityResponseString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (node == null){
            throw new RuntimeException("Invalid access!");
        }
        //Read latitude and longtitude
        double lat = (Double) ((DoubleNode) node.get(0).get("lat")).doubleValue();
        double lon = (Double) ((DoubleNode) node.get(0).get("lon")).doubleValue();

        CtyCityCoordinatesDto cityCoordinatesDto = new CtyCityCoordinatesDto();
        cityCoordinatesDto.setLat(lat);
        cityCoordinatesDto.setLon(lon);

        return cityCoordinatesDto;
    }

    private PltConcentrationDto getConcentrationLevels(String airPollutionResponseString){
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = null;
        try {
            node = mapper.readTree(airPollutionResponseString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Double co = (Double) ((DoubleNode) node.path("list").get(0).get("components").get("co")).doubleValue();
        Double so2 = (Double) ((DoubleNode) node.path("list").get(0).get("components").get("so2")).doubleValue();
        Double o3 = (Double) ((DoubleNode) node.path("list").get(0).get("components").get("o3")).doubleValue();

        return new PltConcentrationDto(co, o3, so2);
    }



}

