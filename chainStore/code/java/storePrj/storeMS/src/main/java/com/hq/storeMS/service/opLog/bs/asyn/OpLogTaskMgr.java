package com.hq.storeMS.service.opLog.bs.asyn;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.opLog.bs.OpLogMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;

public class OpLogTaskMgr {
	
	public static OpLogTaskMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OpLogTaskMgr.class);
	}
	
	private AsynExecutor<OpLog> operator;
	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);
	private boolean open = false;
	
	public void init() {
		try {
			if (isInit.compareAndSet(false, true)) {
				final int nThreadsP = 1;
				final int queueSize = 1024 * 10;

				IntfAsynTask<OpLog> task = new IntfAsynTask<OpLog>() {
					@Override
					public void doTask(OpLog data) {
						OpLogMgr.getInstance().addAndReturnId(data);
					}
				};

				IntfErrorHandler<OpLog> errorHandlerP = new IntfErrorHandler<OpLog>() {
					@Override
					public void handle(OpLog target, String msg, Throwable e) {
						MainLog.error(LogModule.Tmp, "OpLogTaskMgr[storeDataCallBack]", "录入系统操作日志失败", e);
					}
				};

				operator = AsynExecutor.newInstance(task, errorHandlerP, queueSize, nThreadsP);
				operator.init();
				
				open = true;
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "OpLogTaskMgr[init]", "初始化OpLogTaskMgr失败", e);
		}
	}

	public void add(OpLog data) {
		if(isOpen()) {
			BUser sessionBUser = BUserAuthUtils.getInstance().getSessionBUser();
			if(sessionBUser == null) {
				return;
			}
			data.setBuserName(sessionBUser.getName());
			operator.addData(data);
		}
	}

	public boolean isOpen() {
		return open;
	}
}
