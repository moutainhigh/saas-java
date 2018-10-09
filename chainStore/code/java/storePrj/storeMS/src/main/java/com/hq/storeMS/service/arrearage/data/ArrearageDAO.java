package com.hq.storeMS.service.arrearage.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class ArrearageDAO extends MongodbMTDao<Arrearage> {

	public static ArrearageDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageDAO.class);
	}
	
	public Arrearage findArrearageByOrderId(long bossId, long orderId){
		Criteria criteria = new Criteria();  
		criteria.and("orderId").is(orderId);
		return super.findOne(bossId, new Query(criteria));
	}
	
	public List<Arrearage> findArrearageList(long bossId, ArrearageQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    return super.find(bossId, query);
	}
	
	private Query buildQuery(ArrearageQueryForm queryForm){
		Criteria criteria = new Criteria();  

		criteria.and("storeId").is(queryForm.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	}

}
