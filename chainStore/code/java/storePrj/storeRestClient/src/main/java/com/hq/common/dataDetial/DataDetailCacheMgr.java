package com.hq.common.dataDetial;

import com.hq.common.cache.RestCacheMgr;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.hq.common.dataDetial.info.OwnerDataDetailInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class DataDetailCacheMgr {

	public static DataDetailCacheMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DataDetailCacheMgr.class);
	}

	private final String cacheName = "dataDetailCache";

	public void putData(String ownerId, DataVersionEnum dataVersionEnum, Object data, Long ver, String id) {
		getOwnerDetailInfo(ownerId).putData(dataVersionEnum, ver, id, data);
	}

	public <T> T getData(String ownerId, DataVersionEnum dataVersionEnum, Long ver, String id) {
		return getOwnerDetailInfo(ownerId).getData(dataVersionEnum, ver, id);
	}
	
	public void removeTableCache(String ownerId, DataVersionEnum dataVersionEnum) {
		getOwnerDetailInfo(ownerId).removeTableCache(dataVersionEnum);
	}
	
	public void clear(String ownerId) {
		getOwnerDetailInfo(ownerId).clear();
	}
	
	private OwnerDataDetailInfo getOwnerDetailInfo(String ownerId) {
		OwnerDataDetailInfo info = RestCacheMgr.getInstance().get(cacheName, ownerId);
		if (info == null) {
			info = OwnerDataDetailInfo.newInstance(ownerId);
			RestCacheMgr.getInstance().put(cacheName, ownerId, info);
		}
		return info;
	}
}
