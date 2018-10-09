package com.hq.storeMS.service.clerkSalary.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ClerkSalaryDAO extends MongodbDao<ClerkSalary> {

	public static ClerkSalaryDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ClerkSalaryDAO.class);
	}
	
	public List<ClerkSalary> findByStoreId(long storeId,int pageItemCount,int pageNo) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(storeId);
		Query query = new Query(criteria);
		return super.find(query);
	}
	
}
