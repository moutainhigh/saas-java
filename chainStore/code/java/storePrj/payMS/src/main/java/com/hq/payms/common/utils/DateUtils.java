package com.hq.payms.common.utils;

import java.util.TimeZone;

public class DateUtils {
	private static long current;
	private static long zero;
	private static long twelve;
	private static long yesterday;
	static {
//		 current = System.currentTimeMillis();//当前时间毫秒数
//		 zero = current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
//		 twelve = zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
//		 yesterday = System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数

//	    System.out.println(new Timestamp(current));//当前时间
//	    System.out.println(new Timestamp(yesterday));//昨天这一时间点
//	    System.out.println(new Timestamp(zero));//今天零点零分零秒
//	    System.out.println(new Timestamp(twelve));//今天23点59分59秒
	}
	public static long getZeroTime(long currentTime) {
	    return currentTime/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//当天零点零分零秒的毫秒数
	}
	
	public static long getTwelveTime() {
	    return twelve;
	}
	
	public static long getYesterdayTime() {
	    return yesterday;
	}
	
   
    
}
