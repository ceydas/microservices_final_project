package com.ceydog.microservices_final_project.dte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DteDateIntervalStringInputDto {
    String startDate;
    String endDate;
}
