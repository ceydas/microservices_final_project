package com.ceydog.microservices_final_project.log.entity;

import com.ceydog.microservices_final_project.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOG_TRANSACTION")
@Getter
@Setter
public class LogTransaction extends BaseEntity {

    @Id
    @SequenceGenerator(name = "LogTransaction", sequenceName = "LOG_TRANSACTION_ID_SEQ")
    @GeneratedValue(generator = "LogTransaction")
    private Long id;

    @Column(name = "CITY_NAME_PLT_CITY")
    private String cityName;

    @Column(name = "DATE_PLT_CITY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "BODY", length = 4000)
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_DATE")
    private Date logDate;
}
