package com.hq.storeMS.service.daySnapshot.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotAddForm;
import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.hq.storeMS.service.daySnapshot.data.DaySnapshot;
import com.hq.storeMS.service.daySnapshot.data.PreDaySnapshotData;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class DaySnapshotHandler {

	public static DaySnapshotHandler getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotHandler.class);
	}

	private final LogModule logModule = LogModule.DaySnapshot;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(DaySnapshotQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<DaySnapshot> pageResp = DaySnapshotMgr.getInstance().findPage(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DaySnapshotHandler[findPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<DaySnapshot> get(long id) {
		ReqResult<DaySnapshot> result = ReqResult.newInstance(false, DaySnapshot.class);
		try {
			DaySnapshot target = DaySnapshotMgr.getInstance().get(id);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DaySnapshotHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<PreDaySnapshotData> getPreDaySnapshotData(long minTime, long maxTime, long storeId) {
		ReqResult<PreDaySnapshotData> result = ReqResult.newInstance(false, PreDaySnapshotData.class);
		try {
			BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.DAYSNAPSHOT_ADMIN);
			PreDaySnapshotData target = DaySnapshotMgr.getInstance().getPreDaySnapshotData(minTime, maxTime, storeId);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DaySnapshotHandler[getPreDaySnapshotData]";
			final String reason = LogHelper.getInstance().exceptionReason(minTime, maxTime, storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<DaySnapshot> add(DaySnapshotAddForm addForm) {
		ReqResult<DaySnapshot> result = ReqResult.newInstance(false, DaySnapshot.class);
		try {
			BUserAuthUtils.getInstance().checkPermission(addForm.getStoreId(), StoreAdminPermEnum.DAYSNAPSHOT_ADMIN);
			DaySnapshot target = addForm.toDaySnapshot();
			DaySnapshotMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DaySnapshotHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
