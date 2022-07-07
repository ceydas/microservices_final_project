package com.ceydog.microservices_final_project.plt.dto;

import com.ceydog.microservices_final_project.plt.dto.category.PltBaseCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltCOCategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltO3CategoryDto;
import com.ceydog.microservices_final_project.plt.dto.category.PltSO2CategoryDto;
import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PltAirPollutionByDateDto {
    private String date;
    private List<PltBaseCategoryDto> categories;


}
