package com.ceydog.microservices_final_project.plt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PltConcentrationDto {
    //Concentration levels for compounds
    Double co;
    Double o3;
    Double so2;
}
