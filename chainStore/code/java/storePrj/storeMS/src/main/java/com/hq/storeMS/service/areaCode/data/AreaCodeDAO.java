package com.hq.storeMS.service.areaCode.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class AreaCodeDAO extends MongodbDao<AreaCode> {

	public static AreaCodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeDAO.class);
	}
	
	public List<AreaCode> findList(AreaCodeQueryForm queryForm) {
		Criteria criteria = new Criteria();
	    Query query = new Query(criteria);
		return super.find(query);
	}
}
