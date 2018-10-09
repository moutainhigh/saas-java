package com.hq.storeMS.service.storeCardInfo.data;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreCardInfoDAO extends MongodbDao<StoreCardInfo> {

	public static StoreCardInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoDAO.class);
	}
	
	public StoreCardInfo findByOne(long storeId) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(storeId);
		Query query = new Query(criteria);
		return findOne(query);
	}
}
