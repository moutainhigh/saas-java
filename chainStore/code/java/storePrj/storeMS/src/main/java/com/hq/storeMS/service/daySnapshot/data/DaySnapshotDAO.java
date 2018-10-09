package com.hq.storeMS.service.daySnapshot.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class DaySnapshotDAO extends MongodbDao<DaySnapshot> {

	public static DaySnapshotDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotDAO.class);
	}
	
	public List<DaySnapshot> findList(DaySnapshotQueryForm queryForm){
		Query query = buildQuery(queryForm);
		return super.find(query);
	}
	
	private Query buildQuery(DaySnapshotQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(queryForm.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("startTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("startTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}

}
