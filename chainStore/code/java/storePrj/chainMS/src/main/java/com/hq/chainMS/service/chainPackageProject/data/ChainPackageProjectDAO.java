package com.hq.chainMS.service.chainPackageProject.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainPackageProjectDAO extends MongodbDao<ChainPackageProject> {

	public static ChainPackageProjectDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectDAO.class);
	}
	
}
