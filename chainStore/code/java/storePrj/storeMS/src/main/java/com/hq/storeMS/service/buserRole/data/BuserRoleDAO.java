package com.hq.storeMS.service.buserRole.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class BuserRoleDAO extends MongodbDao<BuserRole> {

	public static BuserRoleDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleDAO.class);
	}
}
