package com.ceydog.microservices_final_project.plt.dao;

import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
public interface PltCityAirPollutionDao extends JpaRepository<PltCityAirPollution, Long> {
    List<PltCityAirPollution> findPltCityAirPollutionByCityName(String cityName);
    List<PltCityAirPollution> findPltCityAirPollutionByCityNameAndDate(String cityName, Date date);
    List<PltCityAirPollution> findPltCityAirPollutionByCityNameAndDate(String cityName, LocalDate date);
    List<PltCityAirPollution> findPltCityAirPollutionByCityNameAndDateBetweenOrderByDateAsc(String cityName, Date startDate, Date endDate);

    List<PltCityAirPollution> deletePltCityAirPollutionByCityName(String cityName);
    List<PltCityAirPollution> deletePltCityAirPollutionByCityNameAndDateBetween(String cityName, Date startDate, Date endDate);
    List<PltCityAirPollution> deletePltCityAirPollutionByCityNameAndDate(String cityName, Date date);

    boolean existsByCityName(String cityName);
    boolean existsByCityNameAndDate(String cityName, Date date);
    boolean existsByCityNameAndDate(String cityName, LocalDate date);
    boolean existsByCityNameAndDateBetween(String city, Date start, Date end);


}
