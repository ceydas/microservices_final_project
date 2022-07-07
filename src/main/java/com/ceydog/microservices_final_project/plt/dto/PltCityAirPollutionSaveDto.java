package com.ceydog.microservices_final_project.plt.dto;

import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PltCityAirPollutionSaveDto {
    private String cityName;
    private String date;
    private EnumPltCategory coCategory;
    private EnumPltCategory o3Category;
    private EnumPltCategory so2Category;
}
