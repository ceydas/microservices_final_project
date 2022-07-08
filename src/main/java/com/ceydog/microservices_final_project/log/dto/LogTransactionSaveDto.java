package com.ceydog.microservices_final_project.log.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class LogTransactionSaveDto {

    private String cityName;
    private String date;
    private String body;
}
