package com.hq.storeMS.service.buserRole.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.buserRole.apiData.BatchPermForm;
import com.hq.storeMS.service.buserRole.apiData.BuserRoleUpdateApiForm;
import com.hq.storeMS.service.buserRole.apiData.BuserRoleUpdateType;
import com.hq.storeMS.service.buserRole.data.BuserRole;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleHandler {

	public static BuserRoleHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleHandler.class);
	}

	private final LogModule logModule = LogModule.BuserRole;

	public ReqResult<BuserRole> getBuserRole(long buserId) {
		ReqResult<BuserRole> result = ReqResult.newInstance(false, BuserRole.class);
		try {
			BuserRole buserRole = BuserRoleMgr.getInstance().getSimple(buserId);
			result.setTarget(buserRole);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BuserRoleHandler[getBuserRole]";
			final String reason = LogHelper.getInstance().exceptionReason(buserId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BuserRole> updateBuserRole(long buserId, BuserRoleUpdateApiForm updateForm) {
		ReqResult<BuserRole> result = ReqResult.newInstance(false, BuserRole.class);
		try {
			BuserRole buserRole = BuserRoleMgr.getInstance().getSimple(buserId);
			if(buserRole == null) {
				result.setTips("用户不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			
			BuserRoleUpdateType updateTypeEnum = updateForm.getUpdateTypeEnum();
			switch (updateTypeEnum) {
			case UpdateInfo:
				updateForm.getUpdateInfoData().updateBuserRole(buserRole);
				BuserRoleMgr.getInstance().update(buserRole);
				break;
			default:
				break;
			}
			
			result.setTarget(buserRole);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BuserRoleHandler[updateBuserRole]";
			final String reason = LogHelper.getInstance().exceptionReason(buserId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<BuserRole> batchPerm(BatchPermForm inputForm) {
		ReqResult<BuserRole> result = ReqResult.newInstance(false, BuserRole.class);
		try {
			BuserRoleMgr.getInstance().batchPerm(inputForm);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BuserRoleHandler[batchAddPerm]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
