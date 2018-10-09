package com.hq.zenmind.dao.rest.restSTImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hq.common.restClientLog.IntfLoger;
import com.hq.common.restClientLog.LogModule;
import com.zenmind.common.StringFormatUtil;

public class RestLogerImpl implements IntfLoger {

	private static Logger logger = LoggerFactory.getLogger("mainLog");
	
	public String format(String format, Object... arguments){
			return StringFormatUtil.format(format, arguments);
	}
		
	public void info(LogModule logModule, String id, String info) {
		logger.info("{}|{}|{}", logModule.getName(), id, info);
	}
	public void error(LogModule logModule, String id, String reason) {
		logger.error("{}|{}|{}", logModule.getName(), id, reason, null);
	}
	public void error(LogModule logModule, String id, String reason, Throwable throwable) {
		logger.error("{}|{}|{}", logModule.getName(), id, reason, throwable);
	}


}
