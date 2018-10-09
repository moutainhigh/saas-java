package com.hq.storeMS.service.message.bs;

import java.util.Date;
import java.util.List;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.message.apiData.BUserMessageAddForm;
import com.hq.storeMS.service.message.apiData.BUserMessageAddListForm;
import com.hq.storeMS.service.message.apiData.BUserMessageBatchUpdateStatusForm;
import com.hq.storeMS.service.message.apiData.BUserMessageUpdateForm;
import com.hq.storeMS.service.message.apiData.BUserMessageUpdateStatusForm;
import com.hq.storeMS.service.message.apiData.BUserMessageUpdateType;
import com.hq.storeMS.service.message.apiData.MessageQueryForm;
import com.hq.storeMS.service.message.data.BUserMessage;
import com.hq.storeMS.service.message.data.MessageStatusEnum;
import com.hq.storeMS.service.message.data.MsgUnReadCount;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageHandler {

	public static BUserMessageHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageHandler.class);
	}

	private final LogModule logModule = LogModule.BUserMessage;

	public ReqResult<MsgUnReadCount> findUnReadCount(MessageQueryForm queryForm) {
		ReqResult<MsgUnReadCount> result = ReqResult.newInstance(false, MsgUnReadCount.class);
		try {
			if(queryForm.getBuserId() == 0) {
				Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
				if(sessionBUserId == null) {
					result.setStatus(RespStatus.INVALID_REQUEST);
					result.setTips("用户尚未登录，操作失败");
					return result;
				}else {
					queryForm.setBuserId(sessionBUserId);
				}
			}
			
			long maxTime = AppUtils.getNextDate(new Date());
			long minTime = maxTime - ServerConstants.ONE_DAY * 7;//7天前
			queryForm.setMaxTime(maxTime).setMinTime(minTime).setStatus(MessageStatusEnum.Unread.ordinal());
			MsgUnReadCount target = BUserMessageMgr.getInstance().findUnReadCount(queryForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "BUserMessageHandler[findUnReadCount]", reason, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(MessageQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			if(queryForm.getBuserId() == 0) {
				Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
				if(sessionBUserId == null) {
					result.setStatus(RespStatus.INVALID_REQUEST);
					result.setTips("用户尚未登录，操作失败");
					return result;
				}else {
					queryForm.setBuserId(sessionBUserId);
				}
			}
			PageResp<BUserMessage> pageResp = BUserMessageMgr.getInstance().getBUserMessagePageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "BUserMessageHandler[findPage]", reason, e);
		}
		return result;
	}

	public ReqResult<BUserMessage> addMessage(BUserMessageAddListForm inputForm) {
		ReqResult<BUserMessage> result = ReqResult.newInstance(false, BUserMessage.class);
		try {
			List<BUserMessageAddForm> list = inputForm.getMessageAddForms();
			for (BUserMessageAddForm form : list) {
				BUserMessageMgr.getInstance().addWithForm(form);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BUserMessageHandler[addMessage]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BUserMessage> updateMessage(long messageId, BUserMessageUpdateForm updateInfo) {
		ReqResult<BUserMessage> result = ReqResult.newInstance(false, BUserMessage.class);
		try {
			BUserMessageUpdateType updateType = updateInfo.getBUserMessageUpdateType();
			OperateTips operateTips = null;
			switch (updateType) {
			case UpdateState:
				operateTips = updateState(messageId, updateInfo.getUpdateStatusData());
				break;
			case BatchUpdateState:
				operateTips = batchUpdateState(updateInfo.getBatchUpdateStatusForm());
				break;
			default:
				break;
			}
			if (operateTips.isSuccess()) {
				BUserMessage target = BUserMessageMgr.getInstance().get(messageId);
				result.setTarget(target);
				result.setSuccess(true);
			} else {
				result.setTips(operateTips.getTips());
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "BUserMessageHandler[updateMessage]";
			final String reason = LogHelper.getInstance().exceptionReason(messageId, updateInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private OperateTips batchUpdateState(BUserMessageBatchUpdateStatusForm inputForm) {
		OperateTips operateTips = OperateTips.newInstance(true);
		List<BUserMessageUpdateStatusForm> list = inputForm.toUpdateStatusForms();
		for (BUserMessageUpdateStatusForm form : list) {
			updateState(form.getMessageId(), form);
		}
		return operateTips;
	}

	private OperateTips updateState(long messageId, BUserMessageUpdateStatusForm inputForm) {
		OperateTips operateTips = OperateTips.newInstance(false);
		BUserMessage message = BUserMessageMgr.getInstance().get(messageId);
		if (message == null) {
			operateTips.setTips("消息不存在，操作失败");
			return operateTips;
		}

		Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
		if (sessionBUserId == null) {
			operateTips.setTips("用户尚未登录，操作失败");
			return operateTips;
		}

		if (message.getBuserId() != sessionBUserId) {
			operateTips.setTips("权限不足，操作失败");
			return operateTips;
		}
		message.setStatus(inputForm.getStatus());
		BUserMessageMgr.getInstance().update(message);
		operateTips.setSuccess(true);
		return operateTips;
	}
}
