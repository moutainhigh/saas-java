package com.hq.customerMS.service.logger.bs;

import java.util.UUID;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.rabbitmq.LoggerSenderMgr;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.customerMS.service.logger.apiData.LoggerAddApiForm;
import com.hq.customerMS.service.logger.data.LoggerData;
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
			log.setLogFrom(LogFromEnum.CUSTOMERAPP.ordinal());
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
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "LoggerHandler[saveLogger]", reason, e);
		}
		return result;
	}
}
