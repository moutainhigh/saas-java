package com.hq.storeMS.service.storePackageProject.data;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StorePackageProjectDAO extends MongodbDao<StorePackageProject> {

	public static StorePackageProjectDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectDAO.class);
	}
	
	public StorePackageProject findByOne(long storeId) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(storeId);
		Query query = new Query(criteria);
		return findOne(query);
	}

}
