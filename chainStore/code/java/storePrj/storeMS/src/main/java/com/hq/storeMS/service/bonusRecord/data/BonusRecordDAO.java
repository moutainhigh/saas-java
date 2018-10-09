package com.hq.storeMS.service.bonusRecord.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class BonusRecordDAO extends MongodbMTDao<BonusRecord> {

	public static BonusRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BonusRecordDAO.class);
	}

	public List<BonusRecord> findBonusRecordList(long bossId, BonusRecordQueryForm queryForm) {
	    Query query = new Query(buildCriteria(queryForm));
	    return super.find(bossId, query);
	}

	private Criteria buildCriteria(BonusRecordQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("orderTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("orderTime").gte(queryForm.getMinTime());
		}
		
		if(queryForm.getOrderId() > 0) {
			criteria.and("orderId").is(queryForm.getOrderId());
		}else {
			criteria.and("orderId").gte(queryForm.getOrderId());
		}
		return criteria;
	}
	
}
