package com.xinze.xinze.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author lxf
 */
public class DateUtils {
	
	public static String getDate(){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy.MM.dd", Locale.SIMPLIFIED_CHINESE);
		Date date=new Date();
		return dateFormater.format(date);
	}

	public static String getDate(long time){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy.MM.dd", Locale.SIMPLIFIED_CHINESE);
		Date date=new Date(time);
		return dateFormater.format(date);
	}

	public static String getDate(String time){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy.MM.dd", Locale.SIMPLIFIED_CHINESE);
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
        Date date = null;
        try {
            date = dateFormater2.parse(time);
            return dateFormater.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return "";
	}

}
