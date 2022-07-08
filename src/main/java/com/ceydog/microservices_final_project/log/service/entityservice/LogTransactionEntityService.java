package com.ceydog.microservices_final_project.log.service.entityservice;

import com.ceydog.microservices_final_project.gen.service.entityservice.BaseEntityService;
import com.ceydog.microservices_final_project.log.dao.LogTransactionDao;
import com.ceydog.microservices_final_project.log.entity.LogTransaction;
import org.springframework.stereotype.Service;

@Service
public class LogTransactionEntityService extends BaseEntityService<LogTransaction, LogTransactionDao> {

    public LogTransactionEntityService(LogTransactionDao dao) {
        super(dao);
    }
}