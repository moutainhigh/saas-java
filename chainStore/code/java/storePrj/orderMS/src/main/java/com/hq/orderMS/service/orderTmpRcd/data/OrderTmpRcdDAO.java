package com.hq.orderMS.service.orderTmpRcd.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.orderMS.service.orderTmpRcd.apiData.OrderTmpRcdQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class OrderTmpRcdDAO extends MongodbDao<OrderTmpRcd> {

	public static OrderTmpRcdDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTmpRcdDAO.class);
	}

	public List<OrderTmpRcd> findList(OrderTmpRcdQueryForm queryForm) {
		return super.find(buildQuery(queryForm));
	}

	private Query buildQuery(OrderTmpRcdQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("createTime").lte(queryForm.getMinTime());
		criteria.and("origin").is(queryForm.getOrigin());
		return new Query(criteria);
	}

}
