package com.hq.storeMS.service.orderTrack.data;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.orderTrack.apiData.OrderTrackQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class OrderTrackDAO extends MongodbMTDao<OrderTrack> {

	public static OrderTrackDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackDAO.class);
	}

	public List<OrderTrack> findOrderTrackList(long routeId, OrderTrackQueryForm queryForm) {
		return find(routeId, buildQuery(queryForm));
	}

	private Query buildQuery(OrderTrackQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		// 强制使用时间的条件 可以强制走索引查询 minTime与maxTime默认值是0
		if (queryForm.getMaxTime() > 0) {
			criteria.and("createTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		} else {
			criteria.and("createTime").gte(queryForm.getMinTime());
		}
		if(CollectionUtils.isNotEmpty(queryForm.getOrderIds())) {
			criteria.and("_id").in(queryForm.getOrderIds());
		}
		return new Query(criteria);
	}

}
