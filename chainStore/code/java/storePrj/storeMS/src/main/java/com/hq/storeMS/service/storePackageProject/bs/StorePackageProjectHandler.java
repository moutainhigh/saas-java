package com.hq.storeMS.service.storePackageProject.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateForm;
import com.hq.storeMS.service.storePackageProject.bs.update.PackageProjectHandlerHelper;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectHandler {

	public static StorePackageProjectHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectHandler.class);
	}

	private final LogModule logModule = LogModule.StorePackageProject;
	
	public ReqResult<StorePackageProject> getStorePackageProject(long storeId) {
		ReqResult<StorePackageProject> result = ReqResult.newInstance(false, StorePackageProject.class);
		try {
			StorePackageProject info = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
			result.setTarget(info);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StorePackageProjectHandler[getStorePackageProject]", reason, e);
		}
		return result;
	}

	public ReqResult<StorePackageProject> updateStorePackageProject(long storeId, StorePackageProjectUpdateForm updateForm) {
		ReqResult<StorePackageProject> result = ReqResult.newInstance(false, StorePackageProject.class);
		try {
			OperateTips operateTips = PackageProjectHandlerHelper.getInstance().update(storeId, updateForm);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "StorePackageProjectHandler[updateStorePackageProject]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
