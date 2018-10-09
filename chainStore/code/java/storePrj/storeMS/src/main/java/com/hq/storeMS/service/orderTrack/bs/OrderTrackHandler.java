package com.hq.storeMS.service.orderTrack.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackUpdateType;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackHandler {

	public static OrderTrackHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackHandler.class);
	}

	private final LogModule logModule = LogModule.Order;
	
	public ReqResult<OrderTrack> update(long storeId, long orderId, OrderTrackUpdateApiForm inputForm) {
		ReqResult<OrderTrack> result = ReqResult.newInstance(false, OrderTrack.class);
		try {
			OperateTips tips = OperateTips.newInstance(false);
			OrderTrackUpdateType orderTrackUpdateType = inputForm.getOrderTrackUpdateType();
			switch (orderTrackUpdateType) {
			case UpdateStatus:
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.ORDER_ADMIN);
				tips = OrderTrackMgr.getInstance().updateStatus(storeId, orderId, inputForm.getTrackUpdateStatusForm());
				break;
			default:
				break;
			}
			
			if (tips.isSuccess()) {
				OrderTrack target = OrderTrackMgr.getInstance().get(storeId, orderId);
				result.setSuccess(true);
				result.setTarget(target);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, tips);
			}
		} catch (Exception e) {
			final String logId = "OrderTrackHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<OrderTrack> get(long storeId, long orderId) {
		ReqResult<OrderTrack> result = ReqResult.newInstance(false, OrderTrack.class);
		try {
			OrderTrack orderTrack = OrderTrackMgr.getInstance().get(storeId, orderId);
			result.setTarget(orderTrack);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderTrackHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
