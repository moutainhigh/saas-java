package com.hq.storeMS.service.daySnapshot.data;

import com.zenmind.common.hotSwap.HotSwap;

public class DaySnapshotBeanHelper {
	public static DaySnapshotBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotBeanHelper.class);
	}
}
