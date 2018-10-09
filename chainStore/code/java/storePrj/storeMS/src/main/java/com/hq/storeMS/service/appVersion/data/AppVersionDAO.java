package com.hq.storeMS.service.appVersion.data;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.appVersion.apiData.QueryAppVersionForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class AppVersionDAO extends MongodbDao<AppVersion> {

	public static AppVersionDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AppVersionDAO.class);
	}
	
	public AppVersion findByOne(String appName){
		Criteria criteria = new Criteria();
		criteria.and("appName").is(appName);
		criteria.and("status").is(AppVersionStatusEnum.VALID.ordinal());
		Query query = new Query(criteria);
		query.with(new Sort(new Order(Direction.DESC, "createdTime")));
		return findOne(query);
	}
	
	public List<AppVersion> findByCond(QueryAppVersionForm queryForm) {
		Criteria criteria = new Criteria();
	    Query query = new Query(criteria);
		return super.find(query);
	}
}
