package com.hq.chainStore.service.logger.bs;

import com.hq.chainStore.service.logger.apiData.LoggerAddApiForm;
import com.hq.chainStore.service.logger.data.LoggerDao;
import com.zenmind.common.hotSwap.HotSwap;

public class LoggerMgr {
	public static LoggerMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LoggerMgr.class);
	}
	
	public void saveLogger(LoggerAddApiForm addForm) {
		LoggerDao.getInstance().saveLogger(addForm);
	}
}
