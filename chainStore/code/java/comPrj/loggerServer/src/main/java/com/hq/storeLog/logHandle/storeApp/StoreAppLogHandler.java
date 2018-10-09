package com.hq.storeLog.logHandle.storeApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hq.storeLog.logHandle.IStoreLogHandler;
import com.hq.stream.log.LogLevelEnum;
import com.hq.stream.log.StoreLog;
import com.hq.stream.log.data.LogData;
import com.zenmind.common.json.JsonUtil;

public class StoreAppLogHandler implements IStoreLogHandler {
	private static Logger logger = LoggerFactory.getLogger(StoreAppLogHandler.class);

	@Override
	public void handle(StoreLog log) {
		LogData dataItem = JsonUtil.getInstance().fromJson(log.getJsonData(), LogData.class);
		LogLevelEnum logLevel = LogLevelEnum.valueOf(log.getLogLevel());
		switch (logLevel) {
		case INFO:
			logger.info("{}|{}|{}", dataItem.getModuleName(), dataItem.getId(), dataItem.getContent());
			break;
		case WARN:
			logger.warn("{}|{}|{}", dataItem.getModuleName(), dataItem.getId(), dataItem.getContent());
			break;
		case ERROR:
			logger.error("{}|{}|{}\n{}", dataItem.getModuleName(), dataItem.getId(), dataItem.getContent(), dataItem.getThrowable());
			break;
		default:
			break;
		}
	}

}
