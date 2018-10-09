package com.hq.storeMS.service.specialData.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class SpecialDataDAO extends MongodbDao<SpecialData> {

	public static SpecialDataDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataDAO.class);
	}

}
