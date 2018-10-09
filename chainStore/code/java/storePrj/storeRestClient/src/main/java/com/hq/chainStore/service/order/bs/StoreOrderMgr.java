package com.hq.chainStore.service.order.bs;

import java.util.List;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.order.apiData.OrderAddByWorkflowDataIdForm;
import com.hq.chainStore.service.order.apiData.OrderDeleteForm;
import com.hq.chainStore.service.order.apiData.OrderItemAddForm;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateInfoApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateStatusApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateType;
import com.hq.chainStore.service.order.apiData.PayOrderWithNoteApiForm;
import com.hq.chainStore.service.order.apiData.PreOrderAddForm;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.order.data.OrderCount;
import com.hq.chainStore.service.order.data.OrderDateGroup;
import com.hq.chainStore.service.order.data.StoreOrderDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreOrderMgr {

	public static StoreOrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreOrderMgr.class);
	}
	
	public Order addPreOrder(PreOrderAddForm addForm) {
		final String path = "addPreOrder";
		return StoreOrderDAO.getInstance().addPreOrder(path, addForm);
	}

	public Order addOrderByWorkflowDataId(OrderAddByWorkflowDataIdForm addForm) {
		final String path = "addOrderByWorkflowDataId";
		return StoreOrderDAO.getInstance().addOrderByWorkflowDataId(path, addForm);
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		final String findPath = "findOrderPageInfo";
		return StoreOrderDAO.getInstance().findOrderPageInfo(findPath, params);
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		final String findPath = "findOrderList";
		return StoreOrderDAO.getInstance().findWithReqParam(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		final String findPath = "findOrderDateGroupList";
		return StoreOrderDAO.getInstance().findOrderDateGroupList(findPath, params);
	}
	
	public OrderCount getOrderCount(OrderQueryForm params) {
		final String findPath = "getOrderCount";
		return StoreOrderDAO.getInstance().getOrderCount(findPath, params);
	}

	@Deprecated
	public Order getOrder(long orderId) {
		return getOrder(0L, orderId);
	}
	
	public Order getOrder(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return StoreOrderDAO.getInstance().get(id);
	}
	
	@Deprecated
	public void deleteOrder(long orderId) {
		deleteOrder(0L, orderId);
	}
	
	public void deleteOrder(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		StoreOrderDAO.getInstance().delete(id);
	}
	
	public Order addOrder(OrderItemAddForm formInfo) {
		return StoreOrderDAO.getInstance().add(formInfo);
	}
	
	public void updateOrder(long orderId, OrderUpdateApiForm updateForm) {
		StoreOrderDAO.getInstance().update(orderId, updateForm);
	}
	
	public void updateOrderInfo(long orderId, long storeId, OrderUpdateInfoApiForm updateInfo) {
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setUpdateType(OrderUpdateType.UpdateInfo.ordinal());
		updateForm.setUpdateInfoData(updateInfo);
		updateForm.setStoreId(storeId);
		
		updateOrder(orderId, updateForm);
	}
	
	public void updateOrderStatus(long orderId, long storeId, OrderUpdateStatusApiForm updateStatus) {
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setUpdateType(OrderUpdateType.UpdateState.ordinal());
		updateForm.setUpdateStatusData(updateStatus);
		updateForm.setStoreId(storeId);
		
		updateOrder(orderId, updateForm);
	}
	
	public void updatePayItem(long orderId, long storeId, OrderUpdatePayItemApiForm updateData) {
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setUpdateType(OrderUpdateType.UpdatePayItem.ordinal());
		updateForm.setUpdatePayItemApiForm(updateData);
		updateForm.setStoreId(storeId);
		
		updateOrder(orderId, updateForm);
	}
	
	public void deleteOrder(long orderId, long storeId, OrderDeleteForm orderDeleteForm) {
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setUpdateType(OrderUpdateType.DeleteOrder.ordinal());
		updateForm.setOrderDeleteForm(orderDeleteForm);
		updateForm.setStoreId(storeId);
		
		updateOrder(orderId, updateForm);
	}
	
	public void payOrderWithNote(long orderId, long storeId, PayOrderWithNoteApiForm updateData) {
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setUpdateType(OrderUpdateType.PayOrderWithNote.ordinal());
		updateForm.setPayOrderWithNoteApiForm(updateData);
		updateForm.setStoreId(storeId);
		
		updateOrder(orderId, updateForm);
	}
}
