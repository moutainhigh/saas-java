package com.hq.storeMS.service.orderTrack.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackCacheDAO {

	public static OrderTrackCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackCacheDAO.class);
	}

	final private String suffix = "orderTrack";

	public void save(OrderTrack target) {
		OrderTrackRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public OrderTrack get(long storeId, Object id) {
		return OrderTrackRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(OrderTrack target) {
		OrderTrackRedisDAO.getInstance().delete(target.getId());
		OrderTrackRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	public List<OrderTrack> getList(OrderTrackQueryForm queryForm) {
		return OrderTrackRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public void saveList(OrderTrackQueryForm queryForm, List<OrderTrack> list) {
		OrderTrackRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	private String getGroupName(Object targetId) {
		return AppUtils.joinByUnderline(suffix, targetId);
	}
}
