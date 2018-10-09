package com.hq.storeMS.service.opLog.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.opLog.apiData.OpLogQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class OpLogDAO extends MongodbDao<OpLog> {

	public static OpLogDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OpLogDAO.class);
	}
	
	public List<OpLog> findByCond(OpLogQueryForm queryForm) {
	    Query query = buildQuery(queryForm);
		return super.find(query);
	}

	private Query buildQuery(OpLogQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		if(queryForm.getMaxTime() > 0){
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		return new Query(criteria);
	}
}
