package com.hq.storeMS.service.billRecord.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.billRecord.apiData.BillRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class BillRecordDAO extends MongodbMTDao<BillRecord>{

	public static BillRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BillRecordDAO.class);
	}
	
	public List<BillRecord> findList(long bossId, BillRecordQueryForm queryForm) {
	    Query query = new Query(buildCriteria(queryForm));
	    return super.find(bossId, query);
	}
	
	public BillRecord getByOutTradeNo(long bossId, String outTradeNo) {
		Criteria criteria = new Criteria();
		criteria.and("outTradeNo").is(outTradeNo);
	    Query query = new Query(criteria);
	    return super.findOne(bossId, query);
	}

	private Criteria buildCriteria(BillRecordQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		
		if(queryForm.getOrderId() > 0) {
			criteria.and("orderId").is(queryForm.getOrderId());
		}else {
			criteria.and("orderId").gte(queryForm.getOrderId());
		}
		return criteria;
	}
	
}
