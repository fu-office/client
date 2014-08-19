package com.lbyt.client.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author zhenglianfu
 *
 */
public class DateUtil {
	
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static final Pattern datePattern =  Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}$");  // yyyy-MM-dd
	
	public static final Pattern dateTimePattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}(:\\d{1,2})?$"); // yyyy-MM-dd hh:mm:ss
	
	/**
	 *  parse string to date, as the pattern which it matched
	 *  
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String str) throws ParseException{
		if (str == null || CommUtil.isEmpty(str)) {
			return new Date();
		} else if (datePattern.matcher(str).matches()) {
			return dateFormat.parse(str);
		} else if (dateTimePattern.matcher(str).matches()) {
			return dateTimeFormat.parse(str);
		}
		return null;
	}
	
	public static String date2String(){
		return date2String(new Date());
	}
	
	/**
	 *  format Date as yyyy-MM-dd
	 *  
	 * @param date
	 * @return
	 */
	public static String date2String(Date date){
		return dateFormat.format(date);
	}

	/** 
	 * format Date as yyyy-MM-dd hh:mm:ss
	 * 
	 * @return String 
	 */
	public static String dateTime2String(){
		return dateTime2String(new Date());
	}
	
	/**
	 *  format Date as yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateTime2String(Date date){
		return dateTimeFormat.format(date);
	}
	
	public static Date parseDate(String str) throws ParseException {
		if (null == str) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		str = str.trim();
		str = str.replace("年", "-");
		str = str.replace("月", "-");
		str = str.replace("日", "");
		str = str.replace(".", "-");
		str = str.replace("/", "-");
		str = str.length() > 8 ? str : (calendar.get(Calendar.YEAR) + "-" + str); 
		return string2Date(str);
	}
	
}
