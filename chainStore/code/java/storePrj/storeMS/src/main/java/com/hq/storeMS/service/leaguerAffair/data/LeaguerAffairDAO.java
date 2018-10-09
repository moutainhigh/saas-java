package com.hq.storeMS.service.leaguerAffair.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class LeaguerAffairDAO extends MongodbDao<LeaguerAffair> {

	public static LeaguerAffairDAO getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerAffairDAO.class);
	}
	
}
