package com.hq.customerMS.service.orderDetail.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.service.orderDetail.data.OrderDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailHandler {

	public static OrderDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailHandler.class);
	}

	private final LogModule logModule = LogModule.Order;
	
	public ReqResult<OrderDetail> getOrderDetail(long storeId, long orderId) {
		ReqResult<OrderDetail> result = ReqResult.newInstance(false, OrderDetail.class);
		try {
			OrderDetail orderDetail = OrderDetailMgr.getInstance().get(storeId,orderId);
			result.setTarget(orderDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderDetailHandler[getOrderDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
