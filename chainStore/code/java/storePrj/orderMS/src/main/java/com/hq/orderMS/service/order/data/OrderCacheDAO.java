package com.hq.orderMS.service.order.data;

import java.util.List;

import com.hq.orderMS.common.util.AppUtils;
import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderCacheDAO {

	public static OrderCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderCacheDAO.class);
	}

	final private String suffix = "order";

	public void saveList(OrderQueryForm queryForm, List<Order> list) {
		OrderRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<Order> getList(OrderQueryForm queryForm) {
		return OrderRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(Order target) {
		OrderRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public Order get(long storeId, long id) {
		return OrderRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void deleteOrder(Order target) {
		OrderRedisDAO.getInstance().delete(target.getId());
		OrderRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
