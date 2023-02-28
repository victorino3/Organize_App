package com.victorino.organize.helper;

import java.text.SimpleDateFormat;

public class DateCustom {
    public static String dateUtil(){
        long date = System.currentTimeMillis();
        SimpleDateFormat currentFormat = new SimpleDateFormat("d/M/yyy");
        String dataString = currentFormat.format(date);
        return  dataString;
    }
    public static String splitDate(String dateToSplit){
        String currentDate[] = dateToSplit.split("/");
        String month = currentDate[1];
        String years = currentDate[2];
        return String.format("%s%s", month, years);
    }
}
