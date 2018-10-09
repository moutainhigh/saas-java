package com.hq.chainMS.service.chainPackageProject.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateForm;
import com.hq.chainMS.service.chainPackageProject.bs.updateHandle.PackageProjectHandleHelper;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectHandler {

	public static ChainPackageProjectHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectHandler.class);
	}

	private final LogModule logModule = LogModule.ChainPackageProject;
	
	public ReqResult<ChainPackageProject> getChainPackageProject(long chainId) {
		ReqResult<ChainPackageProject> result = ReqResult.newInstance(false, ChainPackageProject.class);
		try {
			ChainPackageProject info = ChainPackageProjectMgr.getInstance().get(chainId);
			result.setTarget(info);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainPackageProjectHandler[getChainPackageProject]", reason, e);
		}
		return result;
	}

	public ReqResult<ChainPackageProject> updateChainPackageProject(long chainId, ChainPackageProjectUpdateForm updateForm) {
		ReqResult<ChainPackageProject> result = ReqResult.newInstance(false, ChainPackageProject.class);
		try {
			OperateTips operateTips = PackageProjectHandleHelper.getInstance().update(chainId, updateForm);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());//为了批量导入
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "ChainPackageProjectHandler[updateChainPackageProject]";
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
