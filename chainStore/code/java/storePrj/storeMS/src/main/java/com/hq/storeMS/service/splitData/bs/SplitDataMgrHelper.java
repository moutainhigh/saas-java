package com.hq.storeMS.service.splitData.bs;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.splitData.apiData.SplitDataType;
import com.hq.storeMS.service.splitData.bs.mgrHelper.ISplitDataMgr;
import com.hq.storeMS.service.splitData.bs.mgrHelper.UpdateStoreConfigMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitDataMgrHelper {

	public static SplitDataMgrHelper getInstance() {
		return HotSwap.getInstance().getSingleton(SplitDataMgrHelper.class);
	}

	private Map<SplitDataType, ISplitDataMgr> mapper = new HashMap<SplitDataType, ISplitDataMgr>();

	public SplitDataMgrHelper() {
		mapper.put(SplitDataType.UpdateStoreConfig, new ISplitDataMgr() {
			@Override
			public void splitData(long storeId) {
				UpdateStoreConfigMgr.getInstance().updateConfig(storeId);
			}
		});
	}

	public void splitData(long storeId) {
		SplitDataType[] values = SplitDataType.values();
		for (SplitDataType splitDataType : values) {
			ISplitDataMgr handle = mapper.get(splitDataType);
			if (handle != null) {
				handle.splitData(storeId);
			}
		}
	}
}
