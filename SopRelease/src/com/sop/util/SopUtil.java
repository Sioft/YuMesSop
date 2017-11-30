package com.sop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SopUtil {

	public static String createSysImageName(){
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		//System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second); 
		String sysName = year+""+formDate(month)+""+formDate(date)+""+formDate(hour)+formDate(minute)+""+formDate(second);
		return sysName;	
	}
	
	public static String formDate(int date){
		if(date>=10){
			return date+"";
		}else{
			return "0"+date;
		}
	}
	
	public static String dateFormat(String time) throws ParseException{
		//String validTime = "2017/04/06 00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = sdf.parse(time);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String validTimeNew = sdf2.format(date);
		return validTimeNew;
	}
	
	public static String dateFormat2(Date time) throws ParseException{
		//String validTime = "2017/04/06 00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String validTimeNew = sdf.format(time);
		return validTimeNew;
	}
	
	public static String dateFormat3(Date time) throws ParseException{
		//String validTime = "2017/04/06 00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String validTimeNew = sdf.format(time);
		return validTimeNew;
	}
}
