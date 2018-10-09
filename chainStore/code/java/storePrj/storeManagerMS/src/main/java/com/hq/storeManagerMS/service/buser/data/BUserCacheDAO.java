package com.hq.storeManagerMS.service.buser.data;

import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserCacheDAO {

	public static BUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserCacheDAO.class);
	}

	final private String groupName = "buser";
	
	public BUser getOne(String key) {
		return BUserRedisDAO.getInstance().findByOne(groupName, key);
	}
	
	public BUser get(long id) {
		return BUserRedisDAO.getInstance().get(id);
	}

}
