package com.hq.chainStore.pressTest.common;

import java.util.concurrent.atomic.AtomicLong;

import com.zenmind.common.StringFormatUtil;

public class ThreadRecorder {

	private static AtomicLong threadCount = new AtomicLong();
	

	public static void logReq(String reMmark){
		threadCount.incrementAndGet();
	}
	
	public static void logFinish(String reMmark){
		threadCount.decrementAndGet();
	}
	
	public static String getInfo(){
		String format = "thread:{}";
		return StringFormatUtil.format(format, threadCount.get());
	}	
}
