package com.hq.storeMS.service.opLog.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.opLog.apiData.OpLogQueryForm;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class OpLogHandler {

	public static OpLogHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OpLogHandler.class);
	}
	
	private final LogModule logModule = LogModule.OpLog;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(OpLogQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			BUserAuthUtils.getInstance().checkPermission(queryForm.getStoreId(), StoreAdminPermEnum.OPLOG_ADMIN);
			PageResp<OpLog> page = OpLogMgr.getInstance().findPage(queryForm);
			result.setTarget(page);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OpLogHandler[findPage]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
