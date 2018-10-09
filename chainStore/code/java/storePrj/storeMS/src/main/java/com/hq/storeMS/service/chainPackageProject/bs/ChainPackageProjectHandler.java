package com.hq.storeMS.service.chainPackageProject.bs;

import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectHandler {

	public static ChainPackageProjectHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectHandler.class);
	}
	
	private final LogModule logModule = LogModule.ChainPackageProject;

	public ReqResult<ChainPackageProject> getChainPackageProject(long chainId) {
		ReqResult<ChainPackageProject> result = ReqResult.newInstance(false, ChainPackageProject.class);
		try {
			ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().getChainPackageProject(chainId);
			result.setTarget(chainPackageProject);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainPackageProjectHandler[getChainPackageProject]", reason, e);
		}
		return result;
	}
	
	public ReqResult<PackageProjectDetail> getPackageProjectDetail(String packageProjectId, long chainId) {
		ReqResult<PackageProjectDetail> result = ReqResult.newInstance(false, PackageProjectDetail.class);
		try {
			PackageProjectDetail detail = ChainPackageProjectMgr.getInstance().getPackageProjectDetail(packageProjectId, chainId);
			result.setTarget(detail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(packageProjectId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainPackageProjectHandler[getPackageProjectDetail]", reason, e);
		}
		return result;
	}

}
