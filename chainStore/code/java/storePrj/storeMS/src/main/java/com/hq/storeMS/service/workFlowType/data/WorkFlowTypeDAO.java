package com.hq.storeMS.service.workFlowType.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class WorkFlowTypeDAO extends MongodbDao<WorkFlowType> {

	public static WorkFlowTypeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowTypeDAO.class);
	}
	
	public WorkFlowType findByOne(String typeName){
		Criteria criteria = new Criteria();
		criteria.and("wfCompName").is(typeName);
		Query query = new Query(criteria);
		return super.findOne(query);
	}
	
	public List<WorkFlowType> findByCond(QueryWorkFlowTypeForm queryForm) {
		Criteria criteria = new Criteria();
	    Query query = new Query(criteria);
		return super.find(query);
	}
}
