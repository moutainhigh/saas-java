package com.hq.chainStore.pressTest.common;

import java.util.concurrent.atomic.AtomicLong;

import com.zenmind.common.StringFormatUtil;

public class PressRecorder {

	private static AtomicLong errorCount = new AtomicLong();
	private static AtomicLong reqCount = new AtomicLong();
	
	private static AtomicLong startTimeFlag = new AtomicLong();
	
	public static void logError(String msg, Throwable e) {
		errorCount.incrementAndGet();
//		System.out.println(msg);
//		e.printStackTrace();		
	}

	public static void logReq(String reMmark){
		if(startTimeFlag.get() == 0L){
			long startTime = System.currentTimeMillis();
			startTimeFlag.addAndGet(startTime);
		}
		reqCount.incrementAndGet();
	}
	
	public static String getInfo(){
		String format = "req:{} with error:{} startTimeFlag:{}";
		return StringFormatUtil.format(format, reqCount.get(), errorCount.get(), startTimeFlag.get());
	}
	
	public static int countTps(){
		long costTime = System.currentTimeMillis() - startTimeFlag.get();
		return (int)(reqCount.get() * 1000 / costTime);
	}
	
	public static void reset(){
		errorCount.set(0);
		reqCount.set(0);
		startTimeFlag.set(0);
	}
	
	
}
