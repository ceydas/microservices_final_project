package com.ceydog.microservices_final_project.plt.entity;

import com.ceydog.microservices_final_project.gen.entity.BaseEntity;
import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLT_CITY")
@Getter
@Setter
public class PltCityAirPollution extends BaseEntity {

    @Id
    @SequenceGenerator(name = "PltCityAirPollution", sequenceName = "PLT_CITY_ID_SEQ")
    @GeneratedValue(generator = "PltCityAirPollution")
    private Long id;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "DATE_OF_DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "CO_CATEGORY", length = 30)
    private EnumPltCategory coCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "O3_CATEGORY", length = 30)
    private EnumPltCategory o3Category;

    @Enumerated(EnumType.STRING)
    @Column(name = "SO2_CATEGORY", length = 30)
    private EnumPltCategory so2Category;

    @Override
    public Long getId() {
        return id;
    }
}
