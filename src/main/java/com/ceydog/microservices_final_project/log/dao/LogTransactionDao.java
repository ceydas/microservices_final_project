package com.ceydog.microservices_final_project.log.dao;

import com.ceydog.microservices_final_project.log.entity.LogTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogTransactionDao extends JpaRepository<LogTransaction, Long> {
}
