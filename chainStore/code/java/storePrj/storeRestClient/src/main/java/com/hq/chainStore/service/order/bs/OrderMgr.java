package com.hq.chainStore.service.order.bs;

import java.util.List;

import com.hq.chainStore.service.order.apiData.OrderAddApiForm;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateInfoApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateStatusApiForm;
import com.hq.chainStore.service.order.apiData.OrderUpdateType;
import com.hq.chainStore.service.order.apiData.UpdateOrderMaterialForm;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.order.data.OrderDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

/**
 * 
 * @Deprecated 已经被StoreOrderMgr替代
 *
 */
@Deprecated
public class OrderMgr {

	public static OrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderMgr.class);
	}

	/**
	 * findLeaguerOrderList已经可以用findOrderList这个通用接口替代
	 */
	@Deprecated
	public List<Order> findLeaguerOrderList(OrderQueryForm params) {
		final String findPath = "findLeaguerOrderList";
		ReqMap reqMap = params.toReqMap();
		return OrderDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo());
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		final String findPath = "findOrderList";
		ReqMap reqMap = params.toReqMap();
		return OrderDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo());
	}

	public Order getOrder(long orderId) {
		return OrderDAO.getInstance().get(orderId);
	}

	public void updateOrder(long orderId, OrderUpdateApiForm updateForm) {
		OrderDAO.getInstance().update(orderId, updateForm);
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
	
	public void updateMaterial(long orderId, long storeId, UpdateOrderMaterialForm updateData) {
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setUpdateType(OrderUpdateType.UpdateMaterial.ordinal());
		updateForm.setUpdateOrderMaterialData(updateData);
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

	public void deleteOrder(long orderId) {
		OrderDAO.getInstance().delete(orderId);
	}
	
	public Order addOrder(OrderAddApiForm formInfo) {
		return OrderDAO.getInstance().add(formInfo);
	}
}
