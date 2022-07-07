package com.ceydog.microservices_final_project;


import com.ceydog.microservices_final_project.cty.dto.CtyCityCoordinatesDto;
import com.ceydog.microservices_final_project.cty.util.CtyCityUrlUtil;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalDto;
import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.plt.converter.PltCityAirPollutionConverter;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionDto;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.dto.PltConcentrationDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltBaseCategoryDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import com.ceydog.microservices_final_project.plt.service.PltCityAirPollutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ceydog.microservices_final_project.json.converter.JsonConverter.getJSONBodyFromURL;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rest-template")
public class RestTemplateController {

    private final PltCityAirPollutionService pltCityAirPollutionService;

    @PostMapping("" )
    public ResponseEntity getInfoByCityName(@RequestParam(required = true, name = "city") String cityName,
                                    @RequestParam(required = false, name = "start") String startDate,
                                    @RequestParam(required = false, name = "end") String endDate)  {


        DteDateIntervalDto dateInterval;

        if (startDate == null || endDate == null){
            dateInterval = DateUtil.getDefaultDateInterval();
        }
        else {
            dateInterval = DateUtil.convertToDateIntervalDto(startDate, endDate);
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
                saveDto = PltCityAirPollutionSaveDto.builder()
                            .cityName(cityName)
                            .coCategory(concentrationLevelsForDay.get(0).getCategory())
                            .o3Category(concentrationLevelsForDay.get(1).getCategory())
                            .so2Category(concentrationLevelsForDay.get(2).getCategory())
                            .date(currentDayString)
                            .build();
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









}

