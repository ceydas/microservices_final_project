package com.ceydog.microservices_final_project.plt.dto;

import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PltCityAirPollutionDto {
    private String city;
    private List<PltAirPollutionByDateDto> results;


}
