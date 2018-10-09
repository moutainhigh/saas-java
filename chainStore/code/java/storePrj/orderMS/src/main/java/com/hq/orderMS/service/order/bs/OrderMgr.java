package com.hq.orderMS.service.order.bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.orderMS.common.constants.ServerConstants;
import com.hq.orderMS.common.util.AppUtils;
import com.hq.orderMS.common.util.PageUtil;
import com.hq.orderMS.service.common.EntityState;
import com.hq.orderMS.service.common.PageResp;
import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.hq.orderMS.service.order.data.BuyItem;
import com.hq.orderMS.service.order.data.Order;
import com.hq.orderMS.service.order.data.OrderCount;
import com.hq.orderMS.service.order.data.OrderDateGroup;
import com.hq.orderMS.service.order.data.OrderQueryFilterHelper;
import com.hq.orderMS.service.order.data.OrderStatusEnum;
import com.hq.orderMS.service.orderTmpRcd.bs.OrderTmpRcdMgr;
import com.hq.orderMS.service.orderTmpRcd.data.OrderTmpRcd;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderMgr {

	public static OrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderMgr.class);
	}
	
	private final DateFormat sdf = new SimpleDateFormat(ServerConstants.df_short);
	
	public OrderCount getOrderCount(OrderQueryForm params) {
		List<Order> list = OrderDataHolder.getInstance().findOrderList(params);
		List<Order> orderList = OrderQueryFilterHelper.getInstance().filterRecord(params, list);
		float orderCost = 0;// 店铺订单总额
		for (Order order : orderList) {
			List<BuyItem> buyItems = order.getBuyItems();
			for (BuyItem buyItem : buyItems) {
				orderCost += buyItem.getPay();
			}
		}
		OrderCount orderCount = OrderCount.newInstance();
		orderCount.setCount(orderList.size());
		orderCount.setOrderCost(orderCost);
		return orderCount;
	}

	public Order get(long storeId, long orderId) {
		return OrderDataHolder.getInstance().get(storeId, orderId);
	}
	
	public void delete(Order target) {
		OrderDataHolder.getInstance().delete(target);
		OrderTmpRcdMgr.getInstance().delete(target.getStoreId(), target.getId());
	}

	public void update(Order target) {
		OrderDataHolder.getInstance().updpate(target);
		orderNpRcdCallback(target);
	}
	
	private void orderNpRcdCallback(Order target) {
		EntityState entityState = EntityState.valueOf(target.getEntityState());
		OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(target.getStatus());
		//订单删除、取消、支付 都删除临时表的信息
		if(entityState== EntityState.Deleted || orderStatusEnum == OrderStatusEnum.CANCEL || orderStatusEnum == OrderStatusEnum.HAS_PAY) {
			OrderTmpRcdMgr.getInstance().delete(target.getStoreId(), target.getId());
		}
	}

	public void addAndReturnId(Order target) {
		OrderDataHolder.getInstance().addAndReturnId(target);
		if(target.getStatus() == OrderStatusEnum.NOT_PAY.ordinal()) {
			OrderTmpRcdMgr.getInstance().addWithId(OrderTmpRcd.newInstance(target));
		}
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		List<Order> list = OrderDataHolder.getInstance().findOrderList(params);
		List<Order> result = OrderQueryFilterHelper.getInstance().filterRecord(params, list);
		Collections.sort(result, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return PageUtil.getInstance().buildPageResp(result, params.getPageNo(), params.getPageItemCount());
	}

	public List<Order> findOrderList(OrderQueryForm params) {
		List<Order> list = OrderDataHolder.getInstance().findOrderList(params);
		return OrderQueryFilterHelper.getInstance().filterRecord(params, list);
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		List<Order> list = OrderDataHolder.getInstance().findOrderList(params);
		List<Order> result = OrderQueryFilterHelper.getInstance().filterRecord(params, list);
		return buildOrderDateGroupList(result);
	}
	
	private List<OrderDateGroup> buildOrderDateGroupList(List<Order> list){
		Map<String, OrderDateGroup> result = new HashMap<String, OrderDateGroup>();
		for (Order data : list) {
			String yyyyMMdd = AppUtils.timeStamp2Str(data.getCreatedTime(), sdf);
			OrderDateGroup group = result.get(yyyyMMdd);
			if(group == null) {
				group = OrderDateGroup.newInstance();
				group.setDate(yyyyMMdd);
				group.setDateTime(AppUtils.dateStr2TimeStamp(yyyyMMdd, sdf));
				result.put(yyyyMMdd, group);
			}
			group.setCount(group.getCount()+1);
		}
		
		List<OrderDateGroup> resultList = new ArrayList<OrderDateGroup>(result.values());
		Collections.sort(resultList, new Comparator<OrderDateGroup>() {
			@Override
			public int compare(OrderDateGroup o1, OrderDateGroup o2) {
				return Long.compare(o1.getDateTime(), o2.getDateTime());
			}
		});
		return resultList;
	}
}
