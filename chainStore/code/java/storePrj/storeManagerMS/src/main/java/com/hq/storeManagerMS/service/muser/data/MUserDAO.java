package com.hq.storeManagerMS.service.muser.data;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class MUserDAO extends MongodbDao<MUser> {

	public static MUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MUserDAO.class);
	}

	public MUser findByAccount(String account){
		Criteria criteria = new Criteria();  
		criteria.and("account").is(account);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
}
