package com.hq.storeMS.service.orderNotes.bs;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.apiData.RevokeContentAddForm;
import com.hq.storeMS.service.orderNotes.bs.check.CheckFilterHelper;
import com.hq.storeMS.service.orderNotes.bs.revoke.RevokeUpdateHandle;
import com.hq.storeMS.service.orderNotes.bs.update.UpdateFilterHelper;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.orderNotes.data.OrderNotesBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderNotesHandler {

	public static OrderNotesHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderNotesHandler.class);
	}

	private final LogModule logModule = LogModule.OrderNotes;
	
	public ReqResult<OrderNotes> revokeOrder(long storeId, long orderId, RevokeContentAddForm inputForm) {
		ReqResult<OrderNotes> result = ReqResult.newInstance(false, OrderNotes.class);
		try {
			Order order = OrderMgr.getInstance().get(storeId, orderId);
			OrderNotes orderNotes = OrderNotesMgr.getInstance().get(storeId, orderId);
			
			OperateTips operateTips = RevokeUpdateHandle.getInstance().check(order);
			if (!operateTips.isSuccess()) {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			if(OrderNotesBeanHelper.getInstance().addRevokeContent(orderNotes, inputForm)) {
				OrderNotesMgr.getInstance().update(orderNotes);
				OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, order.getNumber(), OpLogTypeEnum.Order, "撤单"));
			}else {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("撤单失败");
				return result;
			}
			
			operateTips = RevokeUpdateHandle.getInstance().update(order);
			if (!operateTips.isSuccess()) {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			result.setTarget(orderNotes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderNotesHandler[revokeOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<OrderNotes> addChargeBackRecord(long storeId, long orderId, ChargeBackRecordAddForm inputForm) {
		ReqResult<OrderNotes> result = ReqResult.newInstance(false, OrderNotes.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Order order = OrderMgr.getInstance().get(storeId, orderId);
			OrderNotes orderNotes = OrderNotesMgr.getInstance().get(order.getStoreId(), orderId);
			OperateTips operateTips = CheckFilterHelper.getInstance().check(order, orderNotes, inputForm);
			if (!operateTips.isSuccess()) {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			BUser sessionBUser = BUserAuthUtils.getInstance().getSessionBUser();
			if(sessionBUser == null) {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("用户尚未登录，操作失败");
				return result;
			}
			inputForm.setCreatorId(sessionBUser.getId());
			inputForm.setCreatorName(sessionBUser.getName());
			if(OrderNotesBeanHelper.getInstance().addChargeBackRecord(orderNotes, inputForm, order.getNumber())) {
				OrderNotesMgr.getInstance().update(orderNotes);
				OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, order.getNumber(), OpLogTypeEnum.Order, "退单"));
			}else {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("生成退单记录失败");
				return result;
			}
			
			operateTips = UpdateFilterHelper.getInstance().updateInfo(order, inputForm);
			if (!operateTips.isSuccess()) {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			result.setTarget(orderNotes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderNotesHandler[addChargeBackRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<OrderNotes> getOrderNotes(long storeId, long orderId) {
		ReqResult<OrderNotes> result = ReqResult.newInstance(false, OrderNotes.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			OrderNotes orderNotes = OrderNotesMgr.getInstance().get(storeId, orderId);
			result.setTarget(orderNotes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderNotesHandler[getOrderNotes]";
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
