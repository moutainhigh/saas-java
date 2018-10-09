package com.hq.orderMS.service.order.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.hq.orderMS.service.order.data.Order;
import com.hq.orderMS.service.order.data.OrderCacheDAO;
import com.hq.orderMS.service.order.data.OrderDAO;
import com.hq.orderMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDataHolder {
	
	public static OrderDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(OrderDataHolder.class);
	}
	
	//用于数据割接时使用
	public List<Order> findOrderList(int pageItemCount, int pageNo){
		return OrderDAO.getInstance().findOrderList(getBossId(0L), pageItemCount, pageNo);
	}
	
	public void addAndReturnId(Order target) {
		OrderDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		OrderCacheDAO.getInstance().deleteOrder(target);
	}
	
	public void updpate(Order target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		OrderDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		
		OrderCacheDAO.getInstance().deleteOrder(target);
	}
	
	public void delete(Order target) {
		OrderDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		
		OrderCacheDAO.getInstance().deleteOrder(target);
	}

	public Order get(long storeId, long id) {
		Order target = OrderCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = OrderDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				OrderCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		List<Order> list = OrderCacheDAO.getInstance().getList(params);
		if(CollectionUtils.isEmpty(list)){
			list = OrderDAO.getInstance().findOrderList(getBossId(params.getStoreId()), params);
			if(CollectionUtils.isNotEmpty(list)){
				OrderCacheDAO.getInstance().saveList(params, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}
