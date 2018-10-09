package com.hq.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils4Client {
	private static final DateFormat MONTH_DF = new SimpleDateFormat("yyyy-MM");

	public static String getMonthStr(Date date) {
		if (date == null) {
			return null;
		}
		return MONTH_DF.format(date);
	}

	public static Date convertStringToDate(String fomatStr, String dateStr) {
		Date date = null;
		try {
			DateFormat tmpdf = new SimpleDateFormat(fomatStr);
			date = (Date) tmpdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	public static String convertDateToString(String formatStr, Date date) {
		DateFormat tmpdf = new SimpleDateFormat(formatStr);
		return tmpdf.format(date);
	}

	/**
	 * 日期操作
	 *
	 * @param date 操作的日期
	 * @param unit 操作的单位 @see日期Calendar.DATE,Calendar
	 * @param count 操作的数量，可为负数
	 * @return 如果取2017-08-01前一天，则unit = Calendar.DATE，count = -1
	 */
	public static Date dateAdd(Date date, int unit, int count) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(unit, count);
		return cal.getTime();
	}

	public static long getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime().getTime();
	}

	public static long getNextMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime().getTime();
	}

	public static void main(String[] args) throws Exception {
		Date date = dateAdd(convertStringToDate("yyyy-MM", "2017-4"), Calendar.MONTH, -1);
		System.out.println(convertDateToString("yyyy-MM-dd HH:mm:ss", date));
		System.out.println(getMonthStr(date));
		System.out.println(convertDateToString("yyyy-MM-dd HH:mm:ss", new Date(getMonthFirstDay(date))));
		System.out.println(convertDateToString("yyyy-MM-dd HH:mm:ss", new Date(getNextMonthFirstDay(date))));
	}

}
