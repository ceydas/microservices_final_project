package com.ceydog.microservices_final_project.dte.util;

import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalDto;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalStringInputDto;
import jdk.jfr.Unsigned;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtil {

    public static boolean isValid (String date) {
        Long epoch = getEpochTimeFromStringDate(date);
        Long now = Instant.now().getEpochSecond();
        //If the date is prior to 24-11-2019
        if ( epoch < 1606424400L || epoch > now  ){
            return false;
        }
        return false;
    }

    public static Long getEpochTimeFromStringDate(String dateAsString)  {

        Long epoch = null;
        try {
            epoch = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(dateAsString).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch;
    }

    public static DteDateIntervalDto convertToDateIntervalDto(String start, String end){
        Long startDate = getEpochTimeFromStringDate(start);
        Long endDate = getEpochTimeFromStringDate(end);
        return new DteDateIntervalDto(startDate,endDate);
    }

    public static DteDateIntervalDto getDefaultDateInterval(){

        //Get current date
        Calendar dateNow = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Long now = dateNow.getTimeInMillis() / 1000;

        //Get 1 week ago
        Calendar dateOneWeekAgo = dateNow;
        dateOneWeekAgo.add(Calendar.WEEK_OF_MONTH, -1);
        Long oneWeekAgo = dateOneWeekAgo.getTimeInMillis() / 1000;

        return new DteDateIntervalDto(oneWeekAgo, now);

        //Long now = Instant.now().getEpochSecond();

    }


}
