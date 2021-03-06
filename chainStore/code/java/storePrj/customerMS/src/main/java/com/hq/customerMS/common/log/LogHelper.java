package com.hq.customerMS.common.log;

import java.util.UUID;

import com.hq.customerMS.rabbitmq.LoggerSenderMgr;
import com.hq.stream.log.LogFromEnum;
import com.hq.stream.log.LogLevelEnum;
import com.hq.stream.log.StoreLog;
import com.hq.stream.log.data.LogData;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class LogHelper {

	public static LogHelper getInstance() {
		return HotSwap.getInstance().getSingleton(LogHelper.class);
	}
	
	private final String reason = "Exception happens. params:{}";
	
	public String exceptionReason(Object... params) {
		StringBuffer sb = new StringBuffer();
		for (Object param : params) {
			if (param instanceof Number || param instanceof String) {
				sb.append(String.valueOf(param));
			} else {
				sb.append(JsonUtil.getInstance().toJson(param));
			}
			sb.append("-");
		}
		return StringFormatUtil.format(reason, sb.toString());
	}

	public void info(LogModule logModule, String id, String info) {
		StoreLog log = new StoreLog();
		log.setLogFrom(LogFromEnum.CUSTOMERMS.ordinal());
		log.setTid(UUID.randomUUID().toString());
		log.setLogLevel(LogLevelEnum.INFO.ordinal());

		LogData data = new LogData();
		data.setContent(info);
		data.setId(id);
		data.setModuleName(logModule.getName());

		log.setJsonData(JsonUtil.getInstance().toJson(data));

		send(log);
	}
	
	public void warn(String logModuleName, String id, String reason) {
		StoreLog log = new StoreLog();
		log.setLogFrom(LogFromEnum.CUSTOMERMS.ordinal());
		log.setTid(UUID.randomUUID().toString());
		log.setLogLevel(LogLevelEnum.WARN.ordinal());

		LogData data = new LogData();
		data.setContent(reason);
		data.setId(id);
		data.setModuleName(logModuleName);

		log.setJsonData(JsonUtil.getInstance().toJson(data));

		send(log);
	}

	public void error(LogModule logModule, String id, String reason,
			String throwable) {
		StoreLog log = new StoreLog();
		log.setLogFrom(LogFromEnum.CUSTOMERMS.ordinal());
		log.setTid(UUID.randomUUID().toString());
		log.setLogLevel(LogLevelEnum.ERROR.ordinal());

		LogData data = new LogData();
		data.setContent(reason);
		data.setId(id);
		data.setModuleName(logModule.getName());
		data.setThrowable(throwable);

		log.setJsonData(JsonUtil.getInstance().toJson(data));

		send(log);
	}
	
	private void send(StoreLog log){
		if(LoggerSenderMgr.getInstance().isOpen()){
			LoggerSenderMgr.getInstance().sendLogger(log);
		}
	}
}