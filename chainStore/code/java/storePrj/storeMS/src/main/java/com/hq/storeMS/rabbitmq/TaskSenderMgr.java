package com.hq.storeMS.rabbitmq;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.stream.task.StoreTask;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rabbit.RabbitCfg;
import com.zenmind.dao.rabbit.RabbitCfgBuilder;
import com.zenmind.dao.rabbit.bs.task.RabbitSender4Task;

public class TaskSenderMgr {
	public static TaskSenderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(TaskSenderMgr.class);
	}
	
	private RabbitSender4Task sender;
	private boolean open = false;
	//只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void sendTask(StoreTask task) {
		if(isOpen()){
			sender.send(JsonUtil.getInstance().toJson(task));
		}
	}

	public void init(StoreMSCfg storeMSCfg) {
		try {
			if(isInit.compareAndSet(false, true)){
				if(storeMSCfg.isEventEnabled()){
					RabbitCfg rabbitCfg = RabbitCfgBuilder.newBuilder(storeMSCfg.getTaskHost(), storeMSCfg.getTaskPort())
							.withCredit(storeMSCfg.getTaskUsername(), storeMSCfg.getTaskPassword())
							.withExchange(storeMSCfg.getTaskExchange())
							.withQueue(storeMSCfg.getTaskQueue())
							.withRouteKey(storeMSCfg.getTaskRouteKey())
							.isPersistent(storeMSCfg.isTaskPesistent())
							.build();
					sender = RabbitSender4Task.newInstance(rabbitCfg);
					open = true;
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "TaskSenderMgr[init]", "初始化TaskSenderMgr失败", e);
		}
	}

	public boolean isOpen() {
		return open;
	}
}
