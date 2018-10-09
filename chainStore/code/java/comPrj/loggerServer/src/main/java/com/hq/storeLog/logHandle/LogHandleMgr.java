package com.hq.storeLog.logHandle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.storeLog.logHandle.customer.CustomerMSLogHandler;
import com.hq.storeLog.logHandle.customerApp.CustomerAppLogHandler;
import com.hq.storeLog.logHandle.order.OrderMSLogHandler;
import com.hq.storeLog.logHandle.storeApp.StoreAppLogHandler;
import com.hq.storeLog.logHandle.storeManager.StoreManagerMSLogHandler;
import com.hq.storeLog.logHandle.storefile.StoreFileMSLogHandler;
import com.hq.storeLog.logHandle.storems.StoreMSLogHandler;
import com.hq.storeLog.logHandle.storeweb.StoreWebLogHandler;
import com.hq.storeLog.logHandle.thirdparty.ThirdParyMSLogHandler;
import com.hq.stream.log.LogFromEnum;
import com.hq.stream.log.StoreLog;
import com.zenmind.common.hotSwap.HotSwap;

public class LogHandleMgr {

	public static LogHandleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LogHandleMgr.class);
	}

	private Map<LogFromEnum, Set<IStoreLogHandler>> handleMapper = new HashMap<LogFromEnum, Set<IStoreLogHandler>>();

	public LogHandleMgr() {
		handleMapper.put(LogFromEnum.CUSTOMERMS, getIStoreLogHandlerSet(new CustomerMSLogHandler()));
		handleMapper.put(LogFromEnum.ORDERMS, getIStoreLogHandlerSet(new OrderMSLogHandler()));
		handleMapper.put(LogFromEnum.STOREFILEMS, getIStoreLogHandlerSet(new StoreFileMSLogHandler()));
		handleMapper.put(LogFromEnum.STOREMS, getIStoreLogHandlerSet(new StoreMSLogHandler()));
		handleMapper.put(LogFromEnum.STOREWEB, getIStoreLogHandlerSet(new StoreWebLogHandler()));
		handleMapper.put(LogFromEnum.THIRDPARTYMS, getIStoreLogHandlerSet(new ThirdParyMSLogHandler()));
		handleMapper.put(LogFromEnum.STOREAPP, getIStoreLogHandlerSet(new StoreAppLogHandler()));
		handleMapper.put(LogFromEnum.CUSTOMERAPP, getIStoreLogHandlerSet(new CustomerAppLogHandler()));
		handleMapper.put(LogFromEnum.STOREMANAGERMS, getIStoreLogHandlerSet(new StoreManagerMSLogHandler()));
	}
	
	private Set<IStoreLogHandler> getIStoreLogHandlerSet(IStoreLogHandler iStoreLogHandler){
		Set<IStoreLogHandler> logHandlerSet = new HashSet<IStoreLogHandler>();
		logHandlerSet.add(iStoreLogHandler);
		return logHandlerSet;
	}

	public void handle(StoreLog storeLog) {
		LogFromEnum logFromEnum = storeLog.getLogFromEnum();
		Set<IStoreLogHandler> handleSet = handleMapper.get(logFromEnum);
		if (!handleSet.isEmpty()) {
			for (IStoreLogHandler storeLogHandlerTmp : handleSet) {
				storeLogHandlerTmp.handle(storeLog);
			}
		}
	}
}
