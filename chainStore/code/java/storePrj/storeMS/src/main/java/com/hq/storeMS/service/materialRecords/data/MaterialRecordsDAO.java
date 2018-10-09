package com.hq.storeMS.service.materialRecords.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class MaterialRecordsDAO extends MongodbDao<MaterialRecords> {

	public static MaterialRecordsDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MaterialRecordsDAO.class);
	}
	
	public List<MaterialRecords> findByStoreId(long storeId,long maxTime,long minTime,int pageItemCount, int pageNo) {
		Criteria criteria = buildCriteria(storeId, maxTime, minTime);
	    Query query = new Query(criteria);  
		return super.find(query);
	}

	private Criteria buildCriteria(long storeId, long maxTime, long minTime) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(storeId);
		//时间段判断
		if(minTime > 0L && maxTime > 0L){
			criteria.and("createdTime").gte(minTime).lte(maxTime);
		}else if(maxTime > 0L){
			criteria.and("createdTime").lte(maxTime);
		}else if(minTime > 0L){
			criteria.and("createdTime").gte(minTime);
		}
		return criteria;
	}
	
	public List<MaterialRecords> findByMaterialId(String materialId, int pageItemCount, int pageNo) {
		Criteria criteria = new Criteria();
		criteria.and("materialId").is(materialId);
	    Query query = new Query(criteria);  
		return super.find(query);
	}
}
