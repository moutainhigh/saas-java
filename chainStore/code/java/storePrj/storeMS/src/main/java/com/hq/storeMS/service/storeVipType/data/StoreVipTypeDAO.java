package com.hq.storeMS.service.storeVipType.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreVipTypeDAO extends MongodbDao<StoreVipType>{

	public static StoreVipTypeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipTypeDAO.class);
	}
	
	public List<StoreVipType> findPage(int pageItemCount, int pageNo){
		Criteria criteria = new Criteria();
		Query query = new Query(criteria);
		return super.find(query);
	}
	
	public StoreVipType findByLevel(int vipLevel){
		Criteria criteria = new Criteria();
		criteria.and("level").is(vipLevel);
		Query query = new Query(criteria);
		return findOne(query);
	}
	
	public StoreVipType findByName(String vipName){
		Criteria criteria = new Criteria();
		criteria.and("name").is(vipName);
		Query query = new Query(criteria);
		return findOne(query);
	}
	
}
