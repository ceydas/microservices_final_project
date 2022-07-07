package com.ceydog.microservices_final_project.dte.util;

import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalDto;
import com.ceydog.microservices_final_project.dte.dto.DteDateIntervalStringInputDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static void main(String[] args) {
        Date date = convertStringToDate("17-01-2022");
        System.out.println(date);

        LocalDate localDate = convertToLocalDateViaMilisecond(date);
        System.out.println(localDate);

    }

    public static void validateDate (String date) {
        if (date == null)
            return;
        Long epoch = getEpochTimeFromStringDate(date);
        Long now = Instant.now().getEpochSecond();
        //If the date is prior to 24-11-2019
        if ( epoch < 1606424400L || epoch > now  ){
            throw new RuntimeException("Invalid date!");
        }
        return;
    }

    public static Long getEpochTimeFromStringDate(String dateAsString)  {

        Long epoch = null;
        try {
            epoch = new SimpleDateFormat("dd-MM-yyyy").parse(dateAsString).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch;
    }

    public static DteDateIntervalDto getDay(String date){
        Long start = null, end = null;
        try {
            start = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date + " 00:00:00").getTime() / 1000;
            end = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date + " 23:59:59").getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if ( start == null || end == null){
            throw new RuntimeException("Could not parse date!");
        }

        return new DteDateIntervalDto(start, end);
    }

    public static DteDateIntervalDto convertToDateIntervalDto(String start, String end){
        Long startDate = getEpochTimeFromStringDate(start);
        Long endDate = getEpochTimeFromStringDate(end);
        return new DteDateIntervalDto(startDate,endDate);
    }

    public static DteDateIntervalDto convertToDateIntervalDto(DteDateIntervalStringInputDto dateInterval){
        Long startDate = getEpochTimeFromStringDate(dateInterval.getStartDate());
        Long endDate = getEpochTimeFromStringDate(dateInterval.getEndDate());
        return new DteDateIntervalDto(startDate,endDate);
    }

    public static DteDateIntervalStringInputDto getDefaultDateInterval(){

        //Get current date
        Calendar dateNow = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String nowString = new SimpleDateFormat("dd-MM-yyyy" ).format(dateNow.getTime());
        Long now = dateNow.getTimeInMillis() / 1000;

        //Get 1 week ago
        Calendar dateOneWeekAgo = dateNow;
        dateOneWeekAgo.add(Calendar.WEEK_OF_MONTH, -1);
        String oneWeekAgoString = new SimpleDateFormat("dd-MM-yyyy" ).format(dateNow.getTime());
        Long oneWeekAgo = dateOneWeekAgo.getTimeInMillis() / 1000;

        return new DteDateIntervalStringInputDto(oneWeekAgoString, nowString);

        //Long now = Instant.now().getEpochSecond();

    }

    public static long calculateDayDifference( String start, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse(start);
            endDate = simpleDateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());

        //Convert the time difference from millis to days
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    public static Date convertStringToDate(String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null)
            throw new RuntimeException("Date parse failed.");

        return date;
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate = "";
        stringDate = simpleDateFormat.format(date);
        return stringDate;
    }

    public static String convertDateToString(LocalDate date){
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return formattedDate;
    }
    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }



}
