package com.ceydog.microservices_final_project.plt.dto.category;

import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import lombok.Data;


@Data
public class PltCOCategoryDto implements PltBaseCategoryDto {
   private final EnumPltCategory CO;

}
