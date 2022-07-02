package com.ceydog.microservices_final_project.dte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DteDateIntervalDto {
    Long startDate;
    Long endDate;
}
