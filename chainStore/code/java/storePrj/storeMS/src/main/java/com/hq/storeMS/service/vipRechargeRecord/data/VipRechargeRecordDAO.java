package com.hq.storeMS.service.vipRechargeRecord.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class VipRechargeRecordDAO extends MongodbDao<VipRechargeRecord>{

	public static VipRechargeRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(VipRechargeRecordDAO.class);
	}
	
	public List<VipRechargeRecord> findPage(long phone, long minTime, long maxTime, int pageItemCount, int pageNo){
		Criteria criteria = buildCriteria(phone, minTime, maxTime);
		Query query = new Query(criteria);
		return super.find(query);
	}

	private Criteria buildCriteria(long phone, long minTime, long maxTime) {
		Criteria criteria = new Criteria();
		if(phone > 0l){
			criteria.and("phone").is(phone);
		}
		
		if(maxTime > 0) {
			criteria.and("createdTime").gte(minTime).lte(maxTime);
		}else {
			criteria.and("createdTime").gte(minTime);
		}
		return criteria;
	}
	
}
