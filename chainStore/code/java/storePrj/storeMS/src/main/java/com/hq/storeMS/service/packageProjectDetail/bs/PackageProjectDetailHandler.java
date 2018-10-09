package com.hq.storeMS.service.packageProjectDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
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

	public ReqResult<PackageProjectDetail> getStorePackageProjectDetail(long storeId, String id) {
		ReqResult<PackageProjectDetail> result = ReqResult.newInstance(false, PackageProjectDetail.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			PackageProjectDetail packageProjectDetail = PackageProjectDetailMgr.getInstance().get(storeId, id);
			result.setTarget(packageProjectDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PackageProjectDetailHandler[getStorePackageProjectDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
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
