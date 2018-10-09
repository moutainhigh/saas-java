package com.hq.customerRestClient.service.logger.bs;

import com.hq.customerRestClient.service.logger.apiData.LoggerAddApiForm;
import com.hq.customerRestClient.service.logger.data.LoggerDao;
import com.zenmind.common.hotSwap.HotSwap;

public class LoggerClientMgr {
	public static LoggerClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LoggerClientMgr.class);
	}
	
	public void saveLogger(LoggerAddApiForm addForm) {
		LoggerDao.getInstance().saveLogger(addForm);
	}
}
