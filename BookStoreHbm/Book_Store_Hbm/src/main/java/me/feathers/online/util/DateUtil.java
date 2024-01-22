package me.feathers.online.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	public static boolean isLeapYear(int year){
		return (year%4==0&&year%100!=0)||year%400==0;
	}
	
	public static int getMaxDays(int year,int month){
		int days = -1;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 2:
			days = isLeapYear(year)?29:28;
			break;
		default:
			days = 30;
			break;
		}
		return days;
	}
	
	public static int getDayOfWeek(int year,int month,int date){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date);
		return cal.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	public static Date createDate(int year,int month,int date){
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(year, month-1, date);
		
		return calendar.getTime();
	}
	
	public static String format(Date d,String pattern){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String sdate = null;
		
		if(null!=d && null!=pattern){
			sdate = simpleDateFormat.format(d);
		}
		return sdate;
	}
	
	public static Date parse(String pattern,String sdate){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date d = null;
		
		if(null!=sdate && null!=pattern){
			try {
				d = simpleDateFormat.parse(sdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return d;
	}
}
