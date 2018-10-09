package com.hq.chainStore.service.schedule.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ScheduleSynDataHolder {

	public static ScheduleSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ScheduleSynDataHolder.class);
	}

	public Schedule getData(String ownerId, String targetId) {
		return ScheduleDAO.getInstance().get(targetId);
	}

}
