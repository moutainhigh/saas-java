package com.hq.storeMS.service.vipLevel.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.vipLevel.apiData.StoreVipLevelQueryForm;
import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelHandler {

	public static VipLevelHandler getInstance(){
		return HotSwap.getInstance().getSingleton(VipLevelHandler.class);
	}
	
	final LogModule logModule = LogModule.VipLevel;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(StoreVipLevelQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			VipLevel vipLevel = VipLevelMgr.getInstance().getRO(queryForm.getVipType());
			PageResp<VipLevel> page = VipLevelMgr.getInstance().findPage(queryForm.toQueryVipLevelForm(vipLevel));
			result.setTarget(page);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "VipLevelHandler[findPage]", reason, e);
		}
		return result;
	}
	
	public ReqResult<VipLevel> getVipLevel(long id) {
		ReqResult<VipLevel> result = ReqResult.newInstance(false, VipLevel.class);
		try {
			VipLevel vipLevel = VipLevelMgr.getInstance().getRO(id);
			result.setTarget(vipLevel);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "VipLevelHandler[getVipLevel]", reason, e);
		}
		return result;
	}

}
