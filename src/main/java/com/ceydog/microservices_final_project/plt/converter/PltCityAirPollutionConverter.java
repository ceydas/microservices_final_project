package com.ceydog.microservices_final_project.plt.converter;

import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.plt.dto.PltAirPollutionByDateDto;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionDto;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltBaseCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltCOCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltO3CategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltSO2CategoryDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PltCityAirPollutionConverter {

    /**
        Converts a general save request Dto to an acceptable RestResponse format, PltCityAirPollutionDto.
     This is NOT intended for data saving purposes.
     */

    public static PltCityAirPollutionDto convertToPltCityAirPollutionDto(List<PltCityAirPollution> cityAirPolutionDataList){

        PltCityAirPollutionDto airPollutionDto = new PltCityAirPollutionDto();
        List<PltAirPollutionByDateDto> results = new ArrayList<>();
        //List of city air pollution data ----> city air pollution dto ( restresponse format )
        for (PltCityAirPollution cityAirPollutionData : cityAirPolutionDataList){ //For each data
            //Create a new list of PltBaseCategoryDto, that is the list of CO, O3 and SO2 concentration Dto's.
            airPollutionDto.setCity(cityAirPollutionData.getCityName());
            List<PltBaseCategoryDto> categories = new ArrayList<>();
            categories.add(new PltCOCategoryDto(cityAirPollutionData.getCoCategory()));
            categories.add(new PltO3CategoryDto(cityAirPollutionData.getO3Category()));
            categories.add(new PltSO2CategoryDto(cityAirPollutionData.getSo2Category()));

            //The list "categories" will be added to a PltAirPollutionByDateDto.
            String dateString = DateUtil.convertDateToString(cityAirPollutionData.getDate());
            PltAirPollutionByDateDto result = new PltAirPollutionByDateDto(dateString, categories);
            //Add each result to the results list.
            results.add(result);
            //Update results.
            airPollutionDto.setResults(results);
        }

        //If there are no results, throw an exception.
        if ( results.isEmpty()){
            throw new RuntimeException("There are no results!");
        }
        return airPollutionDto;

    }

    public static PltCityAirPollutionSaveDto convertToPltCityAirPollutionSaveDto(
                                                PltCityAirPollution pltCityAirPollution){

        String date = DateUtil.convertDateToString(pltCityAirPollution.getDate());
        PltCityAirPollutionSaveDto saveDto = PltCityAirPollutionSaveDto.builder()
                                                .cityName(pltCityAirPollution.getCityName())
                                                .date(date)
                                                .coCategory(pltCityAirPollution.getCoCategory())
                                                .o3Category(pltCityAirPollution.getO3Category())
                                                .so2Category(pltCityAirPollution.getSo2Category())
                                                .build();

        return saveDto;
    }

}
