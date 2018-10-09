package com.hq.taskMS.storeTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.stream.task.StoreTask;
import com.hq.stream.task.StoreTaskType;
import com.hq.taskMS.storeTask.handler.demo.GenExperienceDataHandler;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreTaskHandleMgr {

	public static StoreTaskHandleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreTaskHandleMgr.class);
	}

	private Map<StoreTaskType, Set<IStoreTaskHandler>> handleMapper = new HashMap<StoreTaskType, Set<IStoreTaskHandler>>();

	public StoreTaskHandleMgr() {
		Set<IStoreTaskHandler> storeTaskSet = new HashSet<IStoreTaskHandler>();
		storeTaskSet.add(new GenExperienceDataHandler());
		handleMapper.put(StoreTaskType.GenExperienceData, storeTaskSet);

	}

	public void handle(StoreTask storeTask) {
		StoreTaskType taskTypeEnum = storeTask.getTaskTypeEnum();
		Set<IStoreTaskHandler> handleSet = handleMapper.get(taskTypeEnum);
		if (!handleSet.isEmpty()) {
			for (IStoreTaskHandler storeLogHandlerTmp : handleSet) {
				storeLogHandlerTmp.handle(storeTask);
			}
		}
	}
}
