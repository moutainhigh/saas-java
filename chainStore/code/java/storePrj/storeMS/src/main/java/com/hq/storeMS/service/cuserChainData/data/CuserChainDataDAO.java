package com.hq.storeMS.service.cuserChainData.data;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class CuserChainDataDAO extends MongodbDao<CuserChainData> {

	public static CuserChainDataDAO getInstance() {
		return HotSwap.getInstance().getSingleton(CuserChainDataDAO.class);
	}
	
	public List<CuserChainData> getListByIds(Collection<Long> ids) {
		Criteria criteria = new Criteria();  
		criteria.and("_id").in(ids);
	    Query query = new Query(criteria);  
		return super.find(query);
	}
}
