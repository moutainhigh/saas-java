package com.hq.chainMS.service.chainPackageProject.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailHandler {

	public static PackageProjectDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailHandler.class);
	}

	private final LogModule logModule = LogModule.PackageProjectDetail;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getPackageProjectDetailPageInfo(PackageProjectDetailQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<PackageProjectDetail> pageResp = PackageProjectDetailMgr.getInstance().getPackageProjectDetailPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PackageProjectDetailHandler[getPackageProjectDetailPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<PackageProjectDetail> getPackageProjectDetail(long chainId, String id) {
		ReqResult<PackageProjectDetail> result = ReqResult.newInstance(false, PackageProjectDetail.class);
		try {
			PackageProjectDetail packageProjectDetail = PackageProjectDetailMgr.getInstance().get(chainId, id);
			result.setTarget(packageProjectDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PackageProjectDetailHandler[getPackageProjectDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
