package com.hq.storeMS.common.scheduleTask;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 异步生成店铺二维码和小程序二维码
 * @author kevin
 *
 */
public class StoreTaskMgr {
	
	public static StoreTaskMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreTaskMgr.class);
	}
	
	private AsynExecutor<Store> operator;
	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);
	private boolean open = false;
	
	public void init() {
		try {
			if (isInit.compareAndSet(false, true)) {
				final int nThreadsP = 1;
				final int queueSize = 1024 * 10;

				IntfAsynTask<Store> task = new IntfAsynTask<Store>() {
					@Override
					public void doTask(Store data) {
						StoreMgr.getInstance().genQrcode(data);
					}
				};

				IntfErrorHandler<Store> errorHandlerP = new IntfErrorHandler<Store>() {
					@Override
					public void handle(Store target, String msg, Throwable e) {
						MainLog.error(LogModule.Tmp, "StoreTaskMgr[storeDataCallBack]", "店铺初始化回调失败", e);
					}
				};

				operator = AsynExecutor.newInstance(task, errorHandlerP, queueSize, nThreadsP);
				operator.init();
				
				open = true;
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "StoreTaskMgr[init]", "初始化StoreTaskMgr失败", e);
		}
	}

	public void storeDataCallBack(Store data) {
		if(isOpen()) {
			operator.addData(data);
		}
	}

	public boolean isOpen() {
		return open;
	}
}
