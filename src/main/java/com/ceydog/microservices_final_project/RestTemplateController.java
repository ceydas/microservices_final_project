package com.ceydog.microservices_final_project;


import com.ceydog.microservices_final_project.cty.dto.CtyCityCoordinatesDto;
import com.ceydog.microservices_final_project.cty.util.CtyCityUrlUtil;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalDto;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalStringInputDto;
import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.plt.converter.PltCityAirPollutionConverter;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionDto;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltBaseCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltCOCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltO3CategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltSO2CategoryDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import com.ceydog.microservices_final_project.plt.service.PltCityAirPollutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/air-pollution")
public class RestTemplateController {

    private final PltCityAirPollutionService pltCityAirPollutionService;

    @PostMapping("" )
    public ResponseEntity getAirPollutionDataByCityName(@RequestParam(name = "city") String cityName,
                                    @RequestParam(required = false, name = "start") String startDate,
                                    @RequestParam(required = false, name = "end") String endDate)  {

        validateParameters(cityName, startDate, endDate);
        DteDateIntervalStringInputDto dateInterval;

        if (startDate == null && endDate == null){
            dateInterval = DateUtil.getDefaultDateInterval();
            startDate = dateInterval.getStartDate();
            endDate = dateInterval.getEndDate();
        }
        else if (startDate == null || endDate == null ){
            throw new RuntimeException("Invalid request!");
        }

        //For each day in the date interval, return list of "days" and collect
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //convert String to LocalDate
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);

        for (LocalDate date = localStartDate; date.isBefore(localEndDate.plusDays(1)); date = date.plusDays(1)) {
            //For each date in this interval...
            //Check if it exists in DB
            List<PltCityAirPollution> resultsForCityAndDate = pltCityAirPollutionService.findByCityNameAndDate(cityName, date);
            /*If it does not exist...
            1. Get city coordinates
            //TODO : LOG.
            2. Get concentration levels from openweatherapi.
            3. Save.
             */
            PltCityAirPollutionSaveDto saveDto = null;
            if (resultsForCityAndDate.isEmpty()){
                CtyCityCoordinatesDto cityCoordinatesDto = pltCityAirPollutionService.getCityCoordinates(cityName);
                double lat = cityCoordinatesDto.getLat();
                double lon = cityCoordinatesDto.getLon();
                String currentDayString = DateUtil.convertDateToString(date);
                List<PltBaseCategoryDto> concentrationLevelsForDay = pltCityAirPollutionService.getConcentrationLevelsForDay(lat, lon, currentDayString );
                saveDto = setSaveDto(cityName, currentDayString, concentrationLevelsForDay);
                pltCityAirPollutionService.save(saveDto);

                if (saveDto == null){
                    throw new RuntimeException("Data cannot be saved.");
                }
            }
        }//for each date

        //Now that the required data has been found OR added to the database, it should be returned.
        List<PltCityAirPollution> listOfDataForGivenCityAndDateInterval = pltCityAirPollutionService.findByCityNameAndDateBetween(cityName,
                startDate, endDate);
       PltCityAirPollutionDto finalDataForGivenCityAndDateInterval = PltCityAirPollutionConverter.convertToPltCityAirPollutionDto(listOfDataForGivenCityAndDateInterval);

        return ResponseEntity.ok(finalDataForGivenCityAndDateInterval);
    }



    @DeleteMapping("/delete")
    public ResponseEntity deleteAirPollutionDataByCityNameAndDate(@RequestParam(name = "city") String cityName,
                                                                  @RequestParam(required = false, name = "start") String startDate,
                                                                  @RequestParam(required = false, name = "end") String endDate){

        validateParameters(cityName, startDate, endDate);
        DteDateIntervalStringInputDto dateInterval;

        if (endDate == null && startDate == null){
            //Simply delete all records for city.
            if (!pltCityAirPollutionService.existsByCityName(cityName)){
                throw new RuntimeException("Data does not exist!");
            }
            List<PltCityAirPollution> cityData = pltCityAirPollutionService.deleteByCityName(cityName);
            return ResponseEntity.ok("All records for city "+ cityName + " were deleted.\n");
        }
        else if (endDate == null){
            endDate = startDate;
        }

        //For each day in the date interval, return list of "days" and collect
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //convert String to LocalDate
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);

        String datesDeleted = "";
        for (LocalDate date = localStartDate; date.isBefore(localEndDate.plusDays(1)); date = date.plusDays(1)) {

            String currentDayString = DateUtil.convertDateToString(date);
            if (!pltCityAirPollutionService.existsByCityNameAndDate(cityName, date)){
                continue;
            }
            //If data does exist, delete it from the db and extract dto.
            if (!datesDeleted.isBlank())
                datesDeleted = datesDeleted + ", ";
            datesDeleted = datesDeleted + currentDayString;

            List<PltCityAirPollution> cityData = pltCityAirPollutionService.deletePltCityAirPollutionByCityNameAndDate(cityName, currentDayString);

        }
        if (datesDeleted.isBlank()){
            throw new RuntimeException("Deletion failed.");
        }
        return ResponseEntity.ok("Records from city "+ cityName + " and date " + datesDeleted + " were deleted.\n");



    }

    private PltCityAirPollutionSaveDto setSaveDto(String cityName, String currentDayString, List<PltBaseCategoryDto> concentrationLevelsForDay) {
        PltCityAirPollutionSaveDto saveDto;
        saveDto = PltCityAirPollutionSaveDto.builder()
                .cityName(cityName)
                .coCategory(((PltCOCategoryDto) concentrationLevelsForDay.get(0)).getCO())
                .o3Category(((PltO3CategoryDto) concentrationLevelsForDay.get(1)).getO3())
                .so2Category(((PltSO2CategoryDto) concentrationLevelsForDay.get(2)).getSO2())
                .date(currentDayString)
                .build();
        return saveDto;
    }

    private void validateParameters(String cityName, String startDate, String endDate) {
        pltCityAirPollutionService.validateCity(cityName);
        DateUtil.validateDate(startDate);
        DateUtil.validateDate(endDate);
    }









}

