package com.hq.storeMS.service.arrearage.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.storeMS.service.arrearage.apiData.ArrearageUpdateApiForm;
import com.hq.storeMS.service.arrearage.apiData.ArrearageUpdateType;
import com.hq.storeMS.service.arrearage.data.ArrearageGroup;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageHandler {

	public static ArrearageHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageHandler.class);
	}
	
	private final LogModule logModule = LogModule.Arrearage;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getArrearageGroupPageInfo(ArrearageQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<ArrearageGroup> pageResp = ArrearageMgr.getInstance().getArrearageGroupPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Arrearage, "ArrearageHandler[getArrearageGroupPageInfo]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ArrearageGroup> findArrearageGroupList(ArrearageQueryForm queryForm) {
		ReqResult<ArrearageGroup> result = ReqResult.newInstance(false, ArrearageGroup.class);
		try {
			List<ArrearageGroup> arrearage = ArrearageMgr.getInstance().findArrearageGroupList(queryForm);
			result.setTargetList(arrearage);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Arrearage, "ArrearageHandler[findArrearageGroupList]", reason, e);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getArrearagePageInfo(ArrearageQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Arrearage> pageResp = ArrearageMgr.getInstance().getArrearagePageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Arrearage, "ArrearageHandler[getArrearagePageInfo]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Arrearage> findArrearageList(ArrearageQueryForm queryForm) {
		ReqResult<Arrearage> result = ReqResult.newInstance(false, Arrearage.class);
		try {
			List<Arrearage> arrearage = ArrearageMgr.getInstance().findArrearageList(queryForm);
			result.setTargetList(arrearage);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Arrearage, "ArrearageHandler[findArrearageList]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Arrearage> getArrearage(long storeId, long id) {
		ReqResult<Arrearage> result = ReqResult.newInstance(false, Arrearage.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Arrearage arrearage = ArrearageMgr.getInstance().get(storeId, id);
			result.setTarget(arrearage);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Arrearage, "ArrearageHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<Arrearage> updateArrearage(long arrearageId, ArrearageUpdateApiForm updateInfo) {
		ReqResult<Arrearage> result = ReqResult.newInstance(false, Arrearage.class);
		try {
			long storeId = updateInfo.getStoreId();
			BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.ARREARAGE_ADMIN);
			ArrearageUpdateType updateType = updateInfo.getUpdateTypeEnum();
			OperateTips operateTips = null;
			switch (updateType) {
				case AddPaymentHistory:
					operateTips = ArrearageMgr.getInstance().addPaymentHistory(storeId, arrearageId, updateInfo.getAddPaymentHistoryApiForm());
					break;
				default:
					break;
			}
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				result.setTips(operateTips.getTips());
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "ArrearageHandler[updateArrearage]";
			final String reason = LogHelper.getInstance().exceptionReason(arrearageId, updateInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
