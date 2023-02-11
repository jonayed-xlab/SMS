package com.jontech.smsserver.util;

import org.springframework.cglib.core.Local;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateTime {

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
