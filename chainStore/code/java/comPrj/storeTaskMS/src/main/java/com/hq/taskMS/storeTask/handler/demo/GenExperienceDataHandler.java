package com.hq.taskMS.storeTask.handler.demo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hq.stream.task.StoreTask;
import com.hq.stream.task.data.TaskAwakeData;
import com.hq.taskMS.common.log.LogModule;
import com.hq.taskMS.common.log.MainLog;
import com.hq.taskMS.storeTask.IStoreTaskHandler;
import com.hq.taskMS.storeTask.TaskHelper;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.json.JsonUtil;

public class GenExperienceDataHandler implements IStoreTaskHandler {

	private Executor executor = Executors.newSingleThreadExecutor();

	@Override
	public void handle(StoreTask task) {
		TaskAwakeData data = JsonUtil.getInstance().fromJson(task.getJsonData(), TaskAwakeData.class);

		final String jarPath = data.getJarPath();
		final String className = data.getClassName();
		final String methodName = data.getMethodName();

		executor.execute(new Runnable() {
			public void run() {
				try {
					TaskHelper.getInstance().awake(jarPath, className, methodName);
				} catch (Throwable e) {
					String reasonFormat = "invoke do task error,jarPath:{},className:{},method:{}";
					String reason = StringFormatUtil.format(reasonFormat, jarPath, className, methodName);
					MainLog.error(LogModule.GenExperienceData, "GenExperienceDataHandler[handle]", reason, e);
				}
			}
		});
	}
}
