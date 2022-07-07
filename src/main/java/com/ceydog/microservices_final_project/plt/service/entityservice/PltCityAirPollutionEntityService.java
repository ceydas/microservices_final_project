package com.ceydog.microservices_final_project.plt.service.entityservice;

import com.ceydog.microservices_final_project.dte.util.DateUtil;
import com.ceydog.microservices_final_project.gen.service.entityservice.BaseEntityService;
import com.ceydog.microservices_final_project.plt.dao.PltCityAirPollutionDao;
import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PltCityAirPollutionEntityService extends BaseEntityService<PltCityAirPollution, PltCityAirPollutionDao> {

    public PltCityAirPollutionEntityService(PltCityAirPollutionDao dao) {
        super(dao);
    }

    public List<PltCityAirPollution> findByCityNameAndDateBetween(String cityName, String startDateString, String endDateString){
        Date startDate = DateUtil.convertStringToDate(startDateString);
        Date endDate = DateUtil.convertStringToDate(endDateString);
        return getDao().findPltCityAirPollutionByCityNameAndDateBetweenOrderByDateAsc(cityName, startDate, endDate);
    }

    public boolean existsByCityNameAndDateBetween(String cityName, String startDateString, String endDateString){
        Date startDate = DateUtil.convertStringToDate(startDateString);
        Date endDate = DateUtil.convertStringToDate(endDateString);
        return getDao().existsByCityNameAndDateBetween(cityName, startDate, endDate);
    }

    public List<PltCityAirPollution> findByCityName(String cityName){
        return getDao().findPltCityAirPollutionByCityName(cityName);
    }

    public List<PltCityAirPollution> findByCityNameAndDate(String cityName, Date date){

        return getDao().findPltCityAirPollutionByCityNameAndDate(cityName, date);
    }

    public List<PltCityAirPollution> findByCityNameAndLocalDate(String cityName, LocalDate localDate){

        Date date = DateUtil.convertToDateViaInstant(localDate);
        return getDao().findPltCityAirPollutionByCityNameAndDate(cityName, date);
    }

    public List<PltCityAirPollution> findByCityNameAndStringDate(String cityName, String dateString){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null){
            throw new RuntimeException("Date parse error!");
        }
        return getDao().findPltCityAirPollutionByCityNameAndDate(cityName, date);
    }

    public boolean existsByCityName(String cityName){
        return getDao().existsByCityName(cityName);
    }


    public boolean existsByCityNameAndDate(String cityName, String dateString){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null){
            throw new RuntimeException("Date parse error!");
        }
        return getDao().existsByCityNameAndDate(cityName, date);
    }

    public boolean existsByCityNameAndDate(String cityName, LocalDate date){
        return getDao().existsByCityNameAndDate(cityName, date);
    }




}
