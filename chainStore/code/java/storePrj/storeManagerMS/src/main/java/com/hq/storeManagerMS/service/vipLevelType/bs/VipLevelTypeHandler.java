package com.hq.storeManagerMS.service.vipLevelType.bs;

import org.springframework.web.bind.annotation.RequestBody;

import com.hq.storeManagerMS.common.log.LogHelper;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.common.EntityState;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.vipLevelType.apiData.AddVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.UpdateVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.UpdateVipLevelTypeStateForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.VipLevelTypeUpdateForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.VipLevelTypeUpdateType;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelTypeHandler {

	public static VipLevelTypeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeHandler.class);
	}
	
	final LogModule logModule = LogModule.VipLevelType;

	public ReqResult<VipLevelType> getVipLevelType(int id) {
		ReqResult<VipLevelType> result = ReqResult.newInstance(false, VipLevelType.class);
		try {
			VipLevelType vipLevelType = VipLevelTypeMgr.getInstance().get(id);
			result.setTarget(vipLevelType);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "VipLevelTypeHandler[getVipLevelType]", reason, e);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(QueryVipLevelTypeForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<VipLevelType> pageResp = VipLevelTypeMgr.getInstance().findPage(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "VipLevelTypeHandler[findList]", reason, e);
		}
		return result;
	}

	public ReqResult<VipLevelType> addVipLevelType(@RequestBody AddVipLevelTypeForm addForm) {
		ReqResult<VipLevelType> result = ReqResult.newInstance(false, VipLevelType.class);
		try {
			VipLevelType vipLevelType = VipLevelType.newInstance();
			vipLevelType.setName(addForm.getName());
			VipLevelTypeMgr.getInstance().add(vipLevelType);
			result.setTarget(vipLevelType);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "VipLevelTypeHandler[addVipLevelType]", reason, e);
		}
		return result;
	}

	public ReqResult<VipLevelType> updateVipLevelType(int id, VipLevelTypeUpdateForm updateForm) {
		ReqResult<VipLevelType> result = ReqResult.newInstance(false, VipLevelType.class);
		try {
			VipLevelType vipLevelType = VipLevelTypeMgr.getInstance().get(id);
			if (vipLevelType == null) {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
			}

			VipLevelTypeUpdateType updateType = VipLevelTypeUpdateType.valueOf(updateForm.getUpdateType());
			switch (updateType) {
			case UpdateVipLevelType:
				UpdateVipLevelTypeForm updateVipLevelTypeForm = updateForm.getUpdateVipLevelTypeForm();
				updateVipLevelTypeForm.updateVipLevelType(vipLevelType);
				break;
			case UpdateVipLevelTypeState:
				UpdateVipLevelTypeStateForm updateVipLevelTypeStateForm = updateForm.getUpdateVipLevelTypeStateForm();
				if(vipLevelType.getState() != updateVipLevelTypeStateForm.getState()){
					vipLevelType.setState(updateVipLevelTypeStateForm.getState());
				}
				break;
			case RemoveVipLevelType:
				if(vipLevelType.getEntityState() == EntityState.Normal.ordinal()){
					vipLevelType.setEntityState(EntityState.Deleted.ordinal());
				}
				break;
			default:
				break;
			}
			VipLevelTypeMgr.getInstance().update(vipLevelType);
			result.setSuccess(true);
			result.setTarget(vipLevelType);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "VipLevelTypeHandler[updateVipLevelType]", reason, e);
		}
		return result;
	}
}
