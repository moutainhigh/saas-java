package com.hq.storeMS.service.leaguerDetail.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class LeaguerDetailDAO extends MongodbMTDao<LeaguerDetail> {

	public static LeaguerDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailDAO.class);
	}
	
	public List<LeaguerDetail> findLeaguerDetailList(long bossId, LeaguerDetailQueryForm queryForm){
		Query query = buildQuery(queryForm);
		return super.find(bossId, query);
	}
	
	private Query buildQuery(LeaguerDetailQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(queryForm.getStoreId());
		criteria.and("entityState").is(EntityState.Normal.ordinal());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	}

}
