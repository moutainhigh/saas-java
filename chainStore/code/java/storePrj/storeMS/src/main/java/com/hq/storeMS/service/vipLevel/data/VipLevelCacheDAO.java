package com.hq.storeMS.service.vipLevel.data;

import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelCacheDAO {

	public static VipLevelCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelCacheDAO.class);
	}
	
	public VipLevel get(long id) {
		return VipLevelRedisDAO.getInstance().get(id);
	}
}
