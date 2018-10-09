package com.hq.taskMS.storeTask;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.taskMS.common.config.StoreTaskMSCfg;
import com.hq.taskMS.common.config.StoreTaskMSCfgMgr;
import com.hq.taskMS.common.log.LogModule;
import com.hq.taskMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rabbit.RabbitCfg;
import com.zenmind.dao.rabbit.RabbitCfgBuilder;
import com.zenmind.dao.rabbit.RabbitMsgHandler;
import com.zenmind.dao.rabbit.bs.task.RabbitReceiver4Task;

public class StoreTaskRabbitReceiver4TextMgr {

	public static StoreTaskRabbitReceiver4TextMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreTaskRabbitReceiver4TextMgr.class);
	}

	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);

	public void init() {
		if (isInit.compareAndSet(false, true)) {
			StoreTaskMSCfg prop = StoreTaskMSCfgMgr.getProp();
			RabbitCfg rabbitCfg = RabbitCfgBuilder.newBuilder(prop.getTaskHost(), prop.getTaskPort())
					.withCredit(prop.getTaskUsername(), prop.getTaskPassword()).withExchange(prop.getTaskExchange())
					.withQueue(prop.getTaskQueue()).withRouteKey(prop.getTaskRouteKey())
					.isPersistent(prop.isTaskPesistent()).build();

			RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>() {
				@Override
				public boolean handle(String msg) {
					try {
						System.out.println(msg);
						// StoreTask storeTask = JsonUtil.getInstance().fromJson(msg, StoreTask.class);
						// StoreTaskHandleMgr.getInstance().handle(storeTask);
					} catch (Exception e) {
						MainLog.error(LogModule.Tmp, "StoreTaskRabbitReceiver4TextMgr[init]", "has Exception", e);
					}
					return true;
				}
			};
			RabbitReceiver4Task.newInstance(rabbitCfg, handlerP);
		}
	}

}
