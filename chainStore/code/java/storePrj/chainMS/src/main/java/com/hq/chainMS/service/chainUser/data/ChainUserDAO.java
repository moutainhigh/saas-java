package com.hq.chainMS.service.chainUser.data;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.chainMS.service.chainUser.apiData.ChainUserQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainUserDAO extends MongodbDao<ChainUser> {

	public static ChainUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserDAO.class);
	}
	
	public ChainUser findByPhone(long phone){
		Criteria criteria = new Criteria();  
		criteria.and("phone").is(phone);
	    Query query = new Query(criteria);  
		return findOne(query);
	}

	public List<ChainUser> findByCond(ChainUserQueryForm queryForm) {
		Criteria criteria = new Criteria();
		if(queryForm.getChainId() > 0) {
			criteria.and("chainSUMap."+queryForm.getChainId()+".chainId").is(queryForm.getChainId());
		}
		if(CollectionUtils.isNotEmpty(queryForm.getChainUserIds())) {
			criteria.and("_id").in(queryForm.getChainUserIds());
		}
	    Query query = new Query(criteria);  
		return find(query);
	}
}
