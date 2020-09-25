package com.example.ratingsurvey.DateManagment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateManagment {
    public static String getStringDate(String str){
        Date date = new Date();
        date.setTime(Long.parseLong(str)-21600000);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return df.format(date);
    }

    public static String getStringDateHour(String str){
        Date date = new Date();
        date.setTime(Long.parseLong(str)-21600000);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(date);
    }

    private static String getOffset(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("Z");

        return df.format(date);
    }
}
