package com.hq.customerMS.service.storePackageProject.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
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

	
}
