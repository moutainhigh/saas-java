package com.hq.storeMS.service.footprint.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.footprint.apiData.FootprintQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class FootprintDAO extends MongodbDao<Footprint> {

	public static FootprintDAO getInstance() {
		return HotSwap.getInstance().getSingleton(FootprintDAO.class);
	}

	public List<Footprint> findByCond(FootprintQueryForm queryForm) {
		return super.find(buildQuery(queryForm));
	}

	private Query buildQuery(FootprintQueryForm queryForm) {
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
