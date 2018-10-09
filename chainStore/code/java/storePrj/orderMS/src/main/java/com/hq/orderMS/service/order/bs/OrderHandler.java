package com.hq.orderMS.service.order.bs;

import java.util.List;

import com.hq.orderMS.common.log.LogHelper;
import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.service.common.EntityState;
import com.hq.orderMS.service.common.ExceptionInfo;
import com.hq.orderMS.service.common.HandlerHelper;
import com.hq.orderMS.service.common.OperateTips;
import com.hq.orderMS.service.common.PageResp;
import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.common.RespStatus;
import com.hq.orderMS.service.order.apiData.OrderAddApiForm;
import com.hq.orderMS.service.order.apiData.OrderDeleteForm;
import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.hq.orderMS.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderMS.service.order.apiData.OrderUpdateChargeBackForm;
import com.hq.orderMS.service.order.apiData.OrderUpdateInfoApiForm;
import com.hq.orderMS.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.orderMS.service.order.apiData.OrderUpdateStatusApiForm;
import com.hq.orderMS.service.order.apiData.OrderUpdateType;
import com.hq.orderMS.service.order.apiData.PayOrderWithNoteApiForm;
import com.hq.orderMS.service.order.data.Order;
import com.hq.orderMS.service.order.data.OrderBeanHelper;
import com.hq.orderMS.service.order.data.OrderCount;
import com.hq.orderMS.service.order.data.OrderDataTypeEnum;
import com.hq.orderMS.service.order.data.OrderDateGroup;
import com.hq.orderMS.service.order.data.OrderStatusEnum;
import com.hq.orderMS.service.order.data.PayItem;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderHandler {

	public static OrderHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderHandler.class);
	}
	
	private final LogModule logModule = LogModule.Order;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findOrderPageInfo(OrderQueryForm params) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Order> pageResp = OrderMgr.getInstance().findOrderPageInfo(params);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[findOrderPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(params);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<Order> findOrderList(OrderQueryForm params) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			List<Order> orders = OrderMgr.getInstance().findOrderList(params);
			result.setTargetList(orders);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[findOrderList]";
			final String reason = LogHelper.getInstance().exceptionReason(params);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		ReqResult<OrderDateGroup> result = ReqResult.newInstance(false, OrderDateGroup.class);
		try {
			List<OrderDateGroup> orders = OrderMgr.getInstance().findOrderDateGroupList(params);
			result.setTargetList(orders);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[findOrderDateGroupList]";
			final String reason = LogHelper.getInstance().exceptionReason(params);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<OrderCount> getOrderCount(OrderQueryForm params) {
		ReqResult<OrderCount> result = ReqResult.newInstance(false, OrderCount.class);
		try {
			OrderCount orderCount = OrderMgr.getInstance().getOrderCount(params);
			result.setTarget(orderCount);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[countOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(params);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Order> getOrder(long storeId, long orderId) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().get(storeId, orderId);
			result.setTarget(order);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<Order> updateOrder(long orderId, OrderUpdateApiForm updateInfo) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			OrderUpdateType updateType = OrderUpdateType.valueOf(updateInfo.getUpdateType());
			OperateTips operateTips = OperateTips.newInstance(false, "更新失败");
			switch (updateType) {
				case UpdateInfo:
					operateTips = updateInfo(updateInfo.getStoreId(), orderId, updateInfo.getUpdateInfoData());
					break;
				case UpdateState:
					operateTips = updateState(updateInfo.getStoreId(), orderId, updateInfo.getUpdateStatusData());
					break;
				case UpdatePayItem:
					operateTips = updatePayItem(updateInfo.getStoreId(), orderId, updateInfo.getUpdatePayItemApiForm());
					break;
				case DeleteOrder:
					operateTips = deleteOrder(updateInfo.getStoreId(), orderId, updateInfo.getOrderDeleteForm());
					break;
				case UpdateChargeBackCost:
					operateTips = updateChargeBackCost(updateInfo.getStoreId(), orderId, updateInfo.getOrderUpdateChargeBackForm());
					break;
				case PayOrderWithNote:
					operateTips = payOrderWithNote(updateInfo.getStoreId(), orderId, updateInfo.getPayOrderWithNoteApiForm());
					break;
				default:
					break;
			}
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[updateOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId, updateInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private OperateTips updateInfo(long storeId, long orderId, OrderUpdateInfoApiForm updateData) {
		OperateTips operateTips = OperateTips.newInstance(false, "更新订单基本信息失败");
		Order order = OrderMgr.getInstance().get(storeId, orderId);
		if(order!=null){
			if(order.getStatus() == OrderStatusEnum.NOT_PAY.ordinal()){
				FastBeanCopyer.getInstance().copy(updateData, order);
				OrderMgr.getInstance().update(order);
				operateTips.setSuccess(true);
			}else{
				operateTips.setTips("订单已支付，不能更改信息");
			}
		}else{
			operateTips.setTips("订单不存在，更新失败");
		}
		return operateTips;
	}

	private OperateTips updateState(long storeId, long orderId, OrderUpdateStatusApiForm updateData) {
		OperateTips operateTips = OperateTips.newInstance(false, "更新订单状态信息失败");
		Order order = OrderMgr.getInstance().get(storeId, orderId);
		if(order!=null){
			FastBeanCopyer.getInstance().copy(updateData, order);
			OrderMgr.getInstance().update(order);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("订单不存在，更新失败");
		}
		return operateTips;
	}
	
	private OperateTips updatePayItem(long storeId, long orderId, OrderUpdatePayItemApiForm updateData) {
		OperateTips operateTips = OperateTips.newInstance(false, "订单支付失败");
		Order order = OrderMgr.getInstance().get(storeId, orderId);
		if(order!=null){
			OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(order.getStatus());
			if(orderStatusEnum == OrderStatusEnum.NOT_PAY){
				FastBeanCopyer.getInstance().copy(updateData, order);
				//只更新支付信息 
//				order.setStatus(OrderStatusEnum.HAS_PAY.ordinal());
//				order.setPayTime(System.currentTimeMillis());
//				order.setRealPay(updateData.getRealPay());
				OrderMgr.getInstance().update(order);
				operateTips.setSuccess(true);
			}else{
				operateTips.setTips("订单"+orderStatusEnum.getMark());
			}
		}else{
			operateTips.setTips("订单不存在，支付失败");
		}
		return operateTips;
	}
	
	private OperateTips payOrderWithNote(long storeId, long orderId, PayOrderWithNoteApiForm updateData) {
		OperateTips operateTips = OperateTips.newInstance(false, "订单支付失败");
		Order order = OrderMgr.getInstance().get(storeId, orderId);
		if(order!=null){
			OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(order.getStatus());
			if(orderStatusEnum == OrderStatusEnum.NOT_PAY){
				FastBeanCopyer.getInstance().copy(updateData, order);
				order.setStatus(OrderStatusEnum.HAS_PAY.ordinal());
				long currentTimeMillis = System.currentTimeMillis();
				if(order.getRecordType() == OrderDataTypeEnum.OLD_RCD.ordinal()) {//补单 付款时间与创建时间一样
					order.setPayTime(order.getCreatedTime());
				}else {
					order.setPayTime(currentTimeMillis);
				}
				order.setRealPay(updateData.getRealPay());
				List<PayItem> payItems = order.getPayItems();
				for (PayItem payItem : payItems) {
					if(payItem.getCreatedTime() == 0L){
						if(order.getRecordType() == OrderDataTypeEnum.OLD_RCD.ordinal()) {
							payItem.setCreatedTime(order.getCreatedTime());
						}else {
							payItem.setCreatedTime(currentTimeMillis);
						}
					}
				}
				OrderMgr.getInstance().update(order);
				operateTips.setSuccess(true);
			}else{
				operateTips.setTips("订单"+orderStatusEnum.getMark());
			}
		}else{
			operateTips.setTips("订单不存在，支付失败");
		}
		return operateTips;
	}
	
	private OperateTips deleteOrder(long storeId, long orderId, OrderDeleteForm deleteForm) {
		OperateTips operateTips = OperateTips.newInstance(false, "删除订单失败");
		Order order = OrderMgr.getInstance().get(storeId, orderId);
		if(order!=null){
			order.setEntityState(EntityState.Deleted.ordinal());
			OrderMgr.getInstance().update(order);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("订单不存在，删除失败");
		}
		return operateTips;
	}
	
	private OperateTips updateChargeBackCost(long storeId, long orderId, OrderUpdateChargeBackForm inputForm) {
		OperateTips operateTips = OperateTips.newInstance(false, "更新订单退款金额失败");
		Order order = OrderMgr.getInstance().get(storeId, orderId);
		if(order!=null){
			order.setChargeBackCost(BigDecimalUtil.round(order.getChargeBackCost()+inputForm.getChargeBackCost(), 2));
			OrderMgr.getInstance().update(order);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("订单不存在，更新失败");
		}
		return operateTips;
	}
	
	public ReqResult<Order> deleteOrder(long storeId, long orderId) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().get(storeId, orderId);
			if(order!=null){
				OrderMgr.getInstance().delete(order);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除的订单不存在");
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[deleteOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Order> addOrder(OrderAddApiForm addForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = addForm.toOrder();
			order.setCuserId(Long.valueOf(addForm.getLeaguerId().split("_")[1]));
			order.setNumber(OrderBeanHelper.getInstance().genOrderNumber(order));
			OrderMgr.getInstance().addAndReturnId(order);
			result.setTarget(order);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[addOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
}
