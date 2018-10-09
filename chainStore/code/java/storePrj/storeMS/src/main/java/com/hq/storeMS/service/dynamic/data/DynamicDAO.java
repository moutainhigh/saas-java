package com.hq.storeMS.service.dynamic.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.dynamic.apiData.DynamicQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class DynamicDAO extends MongodbDao<Dynamic> {

	public static DynamicDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicDAO.class);
	}

	public List<Dynamic> findByCond(DynamicQueryForm queryForm) {
		return super.find(buildQuery(queryForm));
	}

	private Query buildQuery(DynamicQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("buserId").is(queryForm.getBuserId());
		if (queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		} else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		return new Query(criteria);

	}
}
