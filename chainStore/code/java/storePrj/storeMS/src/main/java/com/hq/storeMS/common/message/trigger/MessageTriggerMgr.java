package com.hq.storeMS.common.message.trigger;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.message.bs.BUserMessageMgr;
import com.hq.storeMS.service.message.data.BUserMessage;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.push.PushProxy;
import com.zenmind.umeng.MessagePushParam;

public class MessageTriggerMgr {
	public static MessageTriggerMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MessageTriggerMgr.class);
	}

	private AsynExecutor<TriggerForm> trigger;
	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);
	private boolean open = false;

	public void init() {
		try {
			if (isInit.compareAndSet(false, true)) {
				final int nThreadsP = 5;
				final int queueSize = 1024 * 10;

				IntfAsynTask<TriggerForm> task = new IntfAsynTask<TriggerForm>() {
					@Override
					public void doTask(TriggerForm data) {
						BUserMessage buserMessage = BUserMessageMgr.getInstance().addBUserMessageByTriggerForm(data);
						String tJsonP = "";
						MessagePushParam result = MessagePushParam.newInstance(buserMessage.getBuserId(), buserMessage.getStoreName(), buserMessage.getMessageBody(), tJsonP);
						MainLog.info(LogModule.Tmp, "MessageTriggerMgr[triggerMessage]", JsonUtil.getInstance().toJson(result));
						//推送给安卓端
						PushProxy.getInstance().send(result.toAndroidPushParams().toPushRequestParams());
						//推送给IOS端
						PushProxy.getInstance().send(result.toIOSPushParams().toPushRequestParams());
					}
				};

				IntfErrorHandler<TriggerForm> errorHandlerP = new IntfErrorHandler<TriggerForm>() {
					@Override
					public void handle(TriggerForm target, String msg, Throwable e) {
						MainLog.error(LogModule.Tmp, "MessageTriggerMgr[triggerMessage]", "触发消息推送失败", e);
					}
				};

				trigger = AsynExecutor.newInstance(task, errorHandlerP, queueSize, nThreadsP);
				trigger.init();
				
				open = true;
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "MessageTriggerMgr[init]", "初始化MessageTriggerMgr失败", e);
		}
	}

	public void triggerMessage(TriggerForm data) {
		if(isOpen()) {
			trigger.addData(data);
		}
	}

	public boolean isOpen() {
		return open;
	}
}
