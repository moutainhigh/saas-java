package com.hq.storeManagerMS.service.vipLevel.bs;

import org.springframework.web.bind.annotation.RequestBody;

import com.hq.storeManagerMS.common.log.LogHelper;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.vipLevel.apiData.AddVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.UpdateVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.UpdateVipLevelStateForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.VipLevelUpdateForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.VipLevelUpdateType;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelHandler {

	public static VipLevelHandler getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelHandler.class);
	}
	
	final LogModule logModule = LogModule.VipLevel;

	public ReqResult<VipLevel> getVipLevel(long id) {
		ReqResult<VipLevel> result = ReqResult.newInstance(false, VipLevel.class);
		try {
			VipLevel vipLevel = VipLevelMgr.getInstance().get(id);
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

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(QueryVipLevelForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<VipLevel> pageResp = VipLevelMgr.getInstance().findPage(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "VipLevelHandler[findList]", reason, e);
		}
		return result;
	}

	public ReqResult<VipLevel> addVipLevel(@RequestBody AddVipLevelForm addForm) {
		ReqResult<VipLevel> result = ReqResult.newInstance(false, VipLevel.class);
		try {
			VipLevel vipLevel = addForm.toVipLevel();
			VipLevelMgr.getInstance().add(vipLevel);
			result.setTarget(vipLevel);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "VipLevelHandler[addVipLevel]", reason, e);
		}
		return result;
	}

	public ReqResult<VipLevel> updateVipLevel(long id, VipLevelUpdateForm updateForm) {
		ReqResult<VipLevel> result = ReqResult.newInstance(false, VipLevel.class);
		try {
			VipLevel vipLevel = VipLevelMgr.getInstance().get(id);
			if (vipLevel == null) {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
			}

			VipLevelUpdateType updateType = VipLevelUpdateType.valueOf(updateForm.getUpdateType());
			switch (updateType) {
			case UpdateVipLevel:
				UpdateVipLevelForm updateVipLevelForm = updateForm.getUpdateVipLevelForm();
				updateVipLevelForm.updateVipLevel(vipLevel);
				break;
			case UpdateVipLevelState:
				UpdateVipLevelStateForm updateVipLevelStateForm = updateForm.getUpdateVipLevelStateForm();
				vipLevel.setState(updateVipLevelStateForm.getState());
				break;
			default:
				break;
			}
			VipLevelMgr.getInstance().update(vipLevel);
			result.setSuccess(true);
			result.setTarget(vipLevel);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "VipLevelHandler[updateVipLevel]", reason, e);
		}
		return result;
	}
}
