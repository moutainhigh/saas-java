package com.hq.storeMS.service.logger.bs;

import java.util.UUID;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.rabbitmq.LoggerSenderMgr;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.logger.apiData.LoggerAddApiForm;
import com.hq.storeMS.service.logger.data.LoggerData;
import com.hq.stream.log.LogFromEnum;
import com.hq.stream.log.StoreLog;
import com.hq.stream.log.data.LogData;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class LoggerHandler {
	public static LoggerHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LoggerHandler.class);
	}
	
	private final LogModule logModule = LogModule.Logger;
	
	public ReqResult<LoggerData> saveLogger(LoggerAddApiForm addForm) {
		ReqResult<LoggerData> result = ReqResult.newInstance(false, LoggerData.class);
		try {
			StoreLog log = new StoreLog();
			log.setLogFrom(LogFromEnum.STOREAPP.ordinal());
			log.setTid(UUID.randomUUID().toString());
			log.setLogLevel(addForm.getLogLevel());

			LogData data = new LogData();
			data.setContent(addForm.getContent());
			data.setId(addForm.getId());
			data.setModuleName(addForm.getModuleName());
			data.setThrowable(addForm.getThrowable());

			log.setJsonData(JsonUtil.getInstance().toJson(data));

			if(LoggerSenderMgr.getInstance().isOpen()){
				LoggerSenderMgr.getInstance().sendLogger(log);
			}
			
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LoggerHandler[saveLogger]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
