package com.hq.customerMS.service.order.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeClient.service.order.data.MallOrder;
import com.hq.storeClient.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderHandler {

	public static OrderHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderHandler.class);
	}

	private final LogModule logModule = LogModule.Order;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findMallOrderPage(MallOrderQueryForm params) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<MallOrder> pageResp = OrderMgr.getInstance().findMallOrderPage(params);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[findMallOrderPage]", reason, e);
		}
		return result;
	}
	
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
	
	public ReqResult<Order> updateOrder(long orderId, OrderUpdateApiForm updateInfo) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().update(orderId, updateInfo);
			result.setTarget(order);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[updateOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(updateInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Order> getOrder(long storeId,long orderId) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().get(storeId,orderId);
			if (order != null) {
				result.setTarget(order);
				result.setSuccess(true);
			} else {
				result.setTips("获取订单详情失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[getOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Order> addOrder(PreOrderForCuserAddForm addForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().addOrder(addForm);
			if (order != null) {
				result.setTarget(order);
				result.setSuccess(true);
			} else {
				result.setTips("添加预支付订单失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[addOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Order> payOrder(PayOrderForCuserForm payForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().payOrder(payForm);
			if (order != null) {
				result.setTarget(order);
				result.setSuccess(true);
			} else {
				result.setTips("支付订单失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[payOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(payForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
