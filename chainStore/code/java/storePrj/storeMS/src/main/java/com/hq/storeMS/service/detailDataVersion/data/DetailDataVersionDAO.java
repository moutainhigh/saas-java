package com.hq.storeMS.service.detailDataVersion.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class DetailDataVersionDAO extends MongodbDao<DetailDataVersion> {

	public static DetailDataVersionDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DetailDataVersionDAO.class);
	}

	
}
