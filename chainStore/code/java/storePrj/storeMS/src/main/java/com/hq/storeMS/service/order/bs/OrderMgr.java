package com.hq.storeMS.service.order.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.hq.orderRestClient.service.order.apiData.OrderAddApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateStatusApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderDateGroup;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.order.apiData.MallOrderQueryForm;
import com.hq.storeMS.service.order.data.MallOrder;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackUpdateStatusForm;
import com.hq.storeMS.service.orderTrack.bs.OrderTrackMgr;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.hq.storeMS.service.orderTrack.data.OrderTrackStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderMgr {

	public static OrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderMgr.class);
	}

	private final DataVersionEnum dataVersionEnum = DataVersionEnum.Order;

	public PageResp<MallOrder> findMallOrderPage(MallOrderQueryForm params) {
		List<Order> list = OrderDataHolder.getInstance().findOrderList(params.toOrderQueryForm());
		Map<Long, OrderTrack> orderTrackMap = OrderTrackMgr.getInstance().getOrderTrackMap(params.toOrderTrackQueryForm());
		List<MallOrder> resultList = filterRecord(params, list, orderTrackMap);
		return PageUtil.getInstance().buildPageResp(resultList, params.getPageNo(), params.getPageItemCount());
	}
	
	private List<MallOrder> filterRecord(MallOrderQueryForm queryForm, List<Order> list, Map<Long, OrderTrack> orderTrackMap){
		List<MallOrder> result = new ArrayList<MallOrder>();
		if(CollectionUtils.isEmpty(list) || MapUtils.isEmpty(orderTrackMap)){
			return result;
		}
		for (Order record : list) {
			OrderTrack orderTrack = orderTrackMap.get(record.getId());
			if(orderTrack!=null) {
				Set<Integer> statusSet = queryForm.getStatus();
				if(CollectionUtils.isEmpty(statusSet) || statusSet.contains(orderTrack.getStatus())){
					result.add(MallOrder.newInstance(record, orderTrack.getStatus(), orderTrack.getType()));
				}
			}
		}
		Collections.sort(result, new Comparator<MallOrder>() {
			@Override
			public int compare(MallOrder o1, MallOrder o2) {
				return Long.compare(o2.getOrder().getCreatedTime(), o1.getOrder().getCreatedTime());
			}
		});
		return result;
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderPageInfo(params);
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderList(params);
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderDateGroupList(params);
	}

	public Order get(long storeId, long orderId) {
		return OrderDataHolder.getInstance().get(storeId, orderId);
	}
	
	public OrderCount getOrderCount(OrderQueryForm params){
		return OrderDataHolder.getInstance().getOrderCount(params);
	}
	
	public void delete(long orderId, long storeId) {
		OrderDataHolder.getInstance().delete(orderId, storeId);
		DetailDataVersionMgr.getInstance().updateByStoreId(storeId, dataVersionEnum);
	}

	public void update(long orderId, OrderUpdateApiForm updateApiForm) {
		OrderDataHolder.getInstance().update(orderId, updateApiForm);
		DetailDataVersionMgr.getInstance().updateByStoreId(updateApiForm.getStoreId(), dataVersionEnum);
		
		cancelOrder(orderId, updateApiForm);
	}
	
	private void cancelOrder(long orderId, OrderUpdateApiForm updateApiForm) {
		if(updateApiForm.getUpdateType() != OrderUpdateType.UpdateState.ordinal()) {
			return ;
		}
		OrderUpdateStatusApiForm updateStatusData = updateApiForm.getUpdateStatusData();
		if(updateStatusData == null) {
			return ;
		}
		if(updateStatusData.getStatus() != OrderStatusEnum.CANCEL.ordinal()) {
			return ;
		}
		
		OrderTrackMgr.getInstance().updateStatus(updateApiForm.getStoreId(), orderId, OrderTrackUpdateStatusForm.newInstance(OrderTrackStatusEnum.Cancel));
	}
	
	public Order addOrder(OrderAddApiForm addApiForm) {
		Order order = OrderDataHolder.getInstance().addOrder(addApiForm);
		DetailDataVersionMgr.getInstance().updateByStoreId(addApiForm.getStoreId(), dataVersionEnum);
		return order;
	}
	
}
