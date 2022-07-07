package com.ceydog.microservices_final_project.plt.dto;

import com.ceydog.microservices_final_project.plt.dto.category.PltCategoryUtil;
import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PltConcentrationDto {
    //Concentration levels for compounds
    private Double co;
    private Double o3;
    private Double so2;

    public EnumPltCategory getCategoryForCO(){

        return PltCategoryUtil.getCategoryForCO(co);
    }

    public EnumPltCategory getCategoryForO3(){

        return PltCategoryUtil.getCategoryForO3(o3);
    }

    public EnumPltCategory getCategoryForSO2(){

       return PltCategoryUtil.getCategoryForSO2(so2);
    }


}

