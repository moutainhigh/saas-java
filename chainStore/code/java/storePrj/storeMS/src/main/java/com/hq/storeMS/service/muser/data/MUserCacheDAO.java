package com.hq.storeMS.service.muser.data;

import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserCacheDAO {
	public static MUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MUserCacheDAO.class);
	}

	final private String groupName = "muser";

	public MUser get(long id) {
		return MUserRedisDAO.getInstance().get(id);
	}

	public MUser getOne(String key) {
		return MUserRedisDAO.getInstance().findByOne(groupName, key);
	}
}
