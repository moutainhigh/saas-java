package com.hq.taskMS.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenmind.common.StringFormatUtil;

public class MainLog {

	private static Logger logger = LoggerFactory.getLogger("mainLog");

	public static String format(String format, Object... arguments) {
		return StringFormatUtil.format(format, arguments);
	}

	public static String join(String... arguments) {
		StringBuilder sb = new StringBuilder();
		for (String tmp : arguments) {
			sb.append(tmp);
		}
		return sb.toString();
	}

	public static void info(LogModule logModule, String id, String info) {
		logger.info("{}|{}|{}", logModule.getName(), id, info);
	}
	
	public static void warn(LogModule logModule, String id, String info) {
		logger.warn("{}|{}|{}", logModule.getName(), id, info);
	}

	public static void error(LogModule logModule, String id, String reason) {
		logger.error("{}|{}|{}", logModule.getName(), id, reason, null);
	}

	public static void error(LogModule logModule, String id, String reason,
			Throwable throwable) {
		logger.error("{}|{}|{}\n{}", logModule.getName(), id, reason, throwable);
	}

}