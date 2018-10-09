package com.hq.customerMS.service.orderTrack.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackHandler {

	public static OrderTrackHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackHandler.class);
	}

	private final LogModule logModule = LogModule.Order;
	
	public ReqResult<OrderTrack> updateOrderTrack(long storeId, long orderId, OrderTrackUpdateApiForm inputForm) {
		ReqResult<OrderTrack> result = ReqResult.newInstance(false, OrderTrack.class);
		try {
			OrderTrack orderTrack = OrderTrackMgr.getInstance().update(storeId, orderId, inputForm);
			result.setTarget(orderTrack);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderTrackHandler[updateOrderTrack]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<OrderTrack> getOrderTrack(long storeId, long orderId) {
		ReqResult<OrderTrack> result = ReqResult.newInstance(false, OrderTrack.class);
		try {
			OrderTrack orderTrack = OrderTrackMgr.getInstance().get(storeId,orderId);
			result.setTarget(orderTrack);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderTrackHandler[getOrderTrack]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
