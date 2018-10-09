package com.hq.storeMS.service.storeMaterialInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreMaterialInfoDAO extends MongodbDao<StoreMaterialInfo> {

	public static StoreMaterialInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoDAO.class);
	}
	
}
