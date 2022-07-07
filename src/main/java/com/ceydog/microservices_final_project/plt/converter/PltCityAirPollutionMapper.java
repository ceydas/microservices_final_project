package com.ceydog.microservices_final_project.plt.converter;

import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionDto;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PltCityAirPollutionMapper {
    PltCityAirPollutionMapper INSTANCE = Mappers.getMapper(PltCityAirPollutionMapper.class);

    PltCityAirPollution convertToCityAirPollution(PltCityAirPollutionSaveDto airPollutionSaveDto);


}
