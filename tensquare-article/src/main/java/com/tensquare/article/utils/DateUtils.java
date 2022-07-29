package com.tensquare.article.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date parse(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date parse(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
