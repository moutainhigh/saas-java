package com.hq.storeMS.service.cuser.data;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserCacheDAO {

	public static CUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(CUserCacheDAO.class);
	}

	final private String suffix = "cuser";

	public CUser get(long id) {
		return CUserRedisDAO.getInstance().get(id);
	}

	public CUser getOne(String key) {
		return CUserRedisDAO.getInstance().findByOne(getGroupName(null), key);
	}

	private String getGroupName(Object groupId) {
		return AppUtils.joinByUnderline(suffix, groupId);
	}
}
