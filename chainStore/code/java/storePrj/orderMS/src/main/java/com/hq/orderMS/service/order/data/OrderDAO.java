package com.hq.orderMS.service.order.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.orderMS.service.common.EntityState;
import com.hq.orderMS.service.common.SplitMarkEnum;
import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class OrderDAO extends MongodbMTDao<Order> {

	public static OrderDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDAO.class);
	}
	
	//用于数据割接时使用
	public List<Order> findOrderList(long bossId, int pageItemCount, int pageNo) {
		Criteria criteria = new Criteria();
		criteria.and("splitMark").ne(SplitMarkEnum.FINISH.ordinal());
		return super.findPage(bossId, new Query(criteria), pageItemCount, pageNo);
	}
	
	public List<Order> findOrderList(long bossId, OrderQueryForm params) {
	    Query query = new Query(buildCriteria(params));
		List<Order> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<Order> filter(List<Order> list){
		List<Order> result = new ArrayList<Order>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Order data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}

	private Criteria buildCriteria(OrderQueryForm params){
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(params.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(params.getMaxTime() > 0) {
			criteria.and("createdTime").gte(params.getMinTime()).lte(params.getMaxTime());
		}else {
			criteria.and("createdTime").gte(params.getMinTime());
		}
		if(params.getMaxPayTime() > 0) {
			criteria.and("payTime").gte(params.getMinPayTime()).lte(params.getMaxPayTime());
		}else {
			criteria.and("payTime").gte(params.getMinPayTime());
		}
		return criteria;
	}

}
