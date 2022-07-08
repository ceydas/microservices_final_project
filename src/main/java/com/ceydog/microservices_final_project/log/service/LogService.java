package com.ceydog.microservices_final_project.log.service;

import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.log.dto.LogTransactionSaveDto;
import com.ceydog.microservices_final_project.log.entity.LogTransaction;
import com.ceydog.microservices_final_project.log.service.entityservice.LogTransactionEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

    private final LogTransactionEntityService logTransactionEntityService;

    public LogTransaction log(LogTransactionSaveDto logTransactionSaveDto){

        LogTransaction logTransaction = new LogTransaction();
        logTransaction.setCityName(logTransactionSaveDto.getCityName());
        logTransaction.setDate(DateUtil.convertStringToDate(logTransactionSaveDto.getDate()));
        logTransaction.setLogDate(new Date());
        logTransaction.setBody(logTransactionSaveDto.getBody());
        logTransaction = logTransactionEntityService.save(logTransaction);

        log.info(logTransactionSaveDto.getBody());

        return logTransaction;
    }
}