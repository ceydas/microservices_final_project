package com.ceydog.microservices_final_project.plt.service;

import com.ceydog.microservices_final_project.cty.dto.CtyCityCoordinatesDto;
import com.ceydog.microservices_final_project.cty.util.CtyCityUrlUtil;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalDto;
import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.dto.PltConcentrationDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltBaseCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltCOCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltO3CategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltSO2CategoryDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import com.ceydog.microservices_final_project.plt.enums.EnumValidCity;
import com.ceydog.microservices_final_project.plt.service.entityservice.PltCityAirPollutionEntityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DoubleNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static com.ceydog.microservices_final_project.json.converter.JsonConverter.getJSONBodyFromURL;

@Service
@RequiredArgsConstructor
public class PltCityAirPollutionService {

    private final PltCityAirPollutionEntityService pollutionEntityService;

    public PltCityAirPollutionSaveDto save(PltCityAirPollutionSaveDto pollutionSaveDto){
        PltCityAirPollution pltCityAirPollution = new PltCityAirPollution();
        pltCityAirPollution.setCoCategory(pollutionSaveDto.getCoCategory());
        pltCityAirPollution.setSo2Category(pollutionSaveDto.getSo2Category());
        pltCityAirPollution.setO3Category(pollutionSaveDto.getO3Category());
        pltCityAirPollution.setCityName(pollutionSaveDto.getCityName());
        //Convert string date to Date class.
        Date date = DateUtil.convertStringToDate(pollutionSaveDto.getDate());
        pltCityAirPollution.setDate(date);

        //Now this data should be saved as an Entity.
        pollutionEntityService.save(pltCityAirPollution);

        return pollutionSaveDto;

    }
    public List<PltCityAirPollution> findByCityNameAndDateBetween(String cityName, String startDateString, String endDateString){
        if (!pollutionEntityService.existsByCityNameAndDateBetween(cityName, startDateString, endDateString)) {
            throw new RuntimeException("Could not find data belonging to given city and date interval!");
        }
        List<PltCityAirPollution> dataFromCityAndDate = pollutionEntityService.findByCityNameAndDateBetween(cityName,startDateString,endDateString);
        return dataFromCityAndDate;
    }

    public List<PltCityAirPollution> findByCityName(String cityName){
        List<PltCityAirPollution> cityAirPollutionData = pollutionEntityService.findByCityName(cityName);
        if (cityAirPollutionData == null){
            throw new RuntimeException("Any data affiliated with city \"" + cityName + "\" was not found");
        }

        return cityAirPollutionData;
    }

    public List<PltCityAirPollution> findByCityNameAndDate(String cityName, Date date){

        List<PltCityAirPollution> cityAirPollutionData = pollutionEntityService.findByCityNameAndDate(cityName, date);
        if (cityAirPollutionData == null){
            throw new RuntimeException("Any data affiliated with city \"" + cityName + "\" and date \"" + date + "\" was not found");
        }

        return cityAirPollutionData;
    }

    public List<PltCityAirPollution> findByCityNameAndDate(String cityName, LocalDate date){

        List<PltCityAirPollution> cityAirPollutionData = pollutionEntityService.findByCityNameAndLocalDate(cityName, date);
        if (cityAirPollutionData == null){
            throw new RuntimeException("Any data affiliated with city \"" + cityName + "\" and date \"" + date + "\" was not found");
        }

        return cityAirPollutionData;
    }
    public List<PltCityAirPollution> findByCityNameAndDate(String cityName, String date){

        List<PltCityAirPollution> cityAirPollutionData = pollutionEntityService.findByCityNameAndStringDate(cityName, date);
        if (cityAirPollutionData == null){
            throw new RuntimeException("Any data affiliated with city \"" + cityName + "\" and date \"" + date + "\" was not found");
        }

        return cityAirPollutionData;
    }

    public List<PltCityAirPollution> deleteByCityName(String cityName){
        if (!pollutionEntityService.existsByCityName(cityName)){
            throw new RuntimeException("Data for " + cityName + " does not exist!");
        }
        return pollutionEntityService.deleteByCityName(cityName);
    }

    public List<PltCityAirPollution> deleteByCityNameAndDateBetween(String cityName, String startDate, String endDate){
        if (!pollutionEntityService.existsByCityNameAndDateBetween(cityName, startDate, endDate)){
            throw new RuntimeException("Data does not exist!");
        }

        return pollutionEntityService.findByCityNameAndDateBetween(cityName,startDate,endDate);

    }

    public List<PltCityAirPollution> deletePltCityAirPollutionByCityNameAndDate(String cityName, String dateString){
        if (!pollutionEntityService.existsByCityNameAndDate(cityName, dateString)){
            throw new RuntimeException("Data does not exist!");
        }
        return pollutionEntityService.deletePltCityAirPollutionByCityNameAndDate(cityName, dateString);
    }

    public boolean existsByCityName(String cityName) {

        if (cityName.isBlank())
            throw new RuntimeException("Parameter cannot be blank!");

        return pollutionEntityService.existsByCityName(cityName);
    }

    public boolean existsByCityNameAndDate(String cityName, String date){
        return pollutionEntityService.existsByCityNameAndDate(cityName,date);
    }

    public boolean existsByCityNameAndDate(String cityName, LocalDate date){
        return pollutionEntityService.existsByCityNameAndDate(cityName,date);
    }



    public CtyCityCoordinatesDto getCityCoordinates(String cityName){
        String urlForCityGeoMapping = CtyCityUrlUtil.getUrlForCityGeoMapping(cityName);

        String cityResponseString = getJSONBodyFromURL(urlForCityGeoMapping);

        CtyCityCoordinatesDto cityCoordinatesDto = getCityCoordinatesDto(cityResponseString);

        return cityCoordinatesDto;

    }

    public List<PltBaseCategoryDto> getConcentrationLevelsForDay(double lat, double lon, String dayDate ){

        List<PltBaseCategoryDto> allConcentrationLevelsInDay = new ArrayList<>();
        //Get url for the start date
        DteDateIntervalDto startDateInterval = DateUtil.getDay(dayDate);

        //In the following code;
        // getStartDate and getEndDate actually return the beginning and the end of the same day, respectively.
        String urlForAirPollution = CtyCityUrlUtil.getAirPollutionUrl(lat, lon, startDateInterval.getStartDate(), startDateInterval.getEndDate());
        String airPollutionResponseString = getJSONBodyFromURL(urlForAirPollution);
        PltConcentrationDto concentrationLevels = getConcentrationLevelsFromJson(airPollutionResponseString);

        //Initialize CO, SO2, and O3 Dto's.
        PltCOCategoryDto CO_concentrationLevelsForDay = new PltCOCategoryDto(concentrationLevels.getCategoryForCO());
        PltO3CategoryDto O3_concentrationLevelsForDay = new PltO3CategoryDto(concentrationLevels.getCategoryForO3());
        PltSO2CategoryDto SO2_concentrationLevelsForDay = new PltSO2CategoryDto(concentrationLevels.getCategoryForSO2());
        // Add to the final list, A CO, SO2, O3 Category DTO.
        allConcentrationLevelsInDay.add(0, CO_concentrationLevelsForDay);
        allConcentrationLevelsInDay.add(1, O3_concentrationLevelsForDay);
        allConcentrationLevelsInDay.add(2, SO2_concentrationLevelsForDay);
        return allConcentrationLevelsInDay;

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

    private PltConcentrationDto getConcentrationLevelsFromJson(String airPollutionResponseString){
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = null;
        try {
            node = mapper.readTree(airPollutionResponseString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Double co = (Double) node.path("list").get(0).get("components").get("co").doubleValue();
        Double so2 = (Double) node.path("list").get(0).get("components").get("so2").doubleValue();
        Double o3 = (Double) node.path("list").get(0).get("components").get("o3").doubleValue();

        return new PltConcentrationDto(co, o3, so2);
    }

    public void validateCity(String cityNameInput){

        String validCities = "";
        for (EnumValidCity cityName : EnumValidCity.values()){
            validCities = validCities + " " + cityName.getCityName();
            if (cityNameInput.compareToIgnoreCase(String.valueOf(cityName)) == 0){
                return;
            }
        }
        throw new RuntimeException(cityNameInput + " is not a valid city! Please only use " + validCities);
    }



}
