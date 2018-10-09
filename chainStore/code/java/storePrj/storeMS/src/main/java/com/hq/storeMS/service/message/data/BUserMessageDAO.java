package com.hq.storeMS.service.message.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.message.apiData.MessageQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class BUserMessageDAO extends MongodbDao<BUserMessage> {

	public static BUserMessageDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageDAO.class);
	}
	
	public List<BUserMessage> findList(MessageQueryForm queryForm){
		Query query = buildQuery(queryForm);
		return super.find(query);
	}
	
	private Query buildQuery(MessageQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("buserId").is(queryForm.getBuserId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}
}
