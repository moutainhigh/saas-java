package com.hq.common.restClientLog;

public class MainLog {

	private static IntfLoger logger;
	
	public static void init(IntfLoger loggerImpl){
		logger = loggerImpl;
	}
	
	public static void info(LogModule logModule, String id, String info) {
		logger.info(logModule, id, info);
	}
	public static void error(LogModule logModule, String id, String reason) {
		logger.error(logModule, id, reason);
	}
	public static void error(LogModule logModule, String id, String reason, Throwable throwable) {
		logger.error(logModule, id, reason, throwable);
	}


}