package com.hq.storeMS.service.orderDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.orderDetail.data.OrderDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailHandler {

	public static OrderDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailHandler.class);
	}

	private final LogModule logModule = LogModule.OrderDetail;

	public ReqResult<OrderDetail> getOrderDetail(long storeId, long orderId) {
		ReqResult<OrderDetail> result = ReqResult.newInstance(false, OrderDetail.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			OrderDetail orderDetail = OrderDetailMgr.getInstance().getOrderDetailByOrderId(storeId, orderId);
			result.setTarget(orderDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderDetailHandler[getOrderDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
