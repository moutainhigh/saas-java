package com.hq.storeMS.service.spreadStatis.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class SpreadStatisDAO extends MongodbDao<SpreadStatis> {

	public static SpreadStatisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisDAO.class);
	}

	public List<SpreadStatis> findByCond(SpreadStatisQueryForm queryForm) {
		Criteria criteria = buildCriteria(queryForm);
		Query query = new Query(criteria);
		return super.find(query);
	}

	private Criteria buildCriteria(SpreadStatisQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		if (queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		} else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		return criteria;
	}
}
