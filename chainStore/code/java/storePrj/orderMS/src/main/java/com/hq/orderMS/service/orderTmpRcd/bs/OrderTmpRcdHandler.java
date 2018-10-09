package com.hq.orderMS.service.orderTmpRcd.bs;

import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.service.common.ExceptionInfo;
import com.hq.orderMS.service.common.HandlerHelper;
import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.orderTmpRcd.data.OrderTmpRcd;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTmpRcdHandler {
	public static OrderTmpRcdHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTmpRcdHandler.class);
	}

	private final LogModule logModule = LogModule.OrderTmpRcd;

	public ReqResult<OrderTmpRcd> checkOrderTmpRcd() {
		ReqResult<OrderTmpRcd> result = ReqResult.newInstance(false, OrderTmpRcd.class);
		try {
			OrderTmpRcdMgr.getInstance().checkOrderTmpRcd();
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderTmpRcdHandler[checkOrderTmpRcd]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason("").withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}
