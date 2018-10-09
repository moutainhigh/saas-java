package com.hq.storeMS.service.sysConf.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class SysConfDAO extends MongodbDao<SysConf> {

	public static SysConfDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SysConfDAO.class);
	}
}
