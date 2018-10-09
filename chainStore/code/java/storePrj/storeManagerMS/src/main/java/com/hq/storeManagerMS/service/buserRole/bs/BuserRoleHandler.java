package com.hq.storeManagerMS.service.buserRole.bs;

import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateApiForm;
import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateType;
import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.hq.storeManagerMS.common.log.LogHelper;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleHandler {

	public static BuserRoleHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleHandler.class);
	}

	private final LogModule logModule = LogModule.BuserRole;

	public ReqResult<BuserRole> getBuserRole(long buserId) {
		ReqResult<BuserRole> result = ReqResult.newInstance(false, BuserRole.class);
		try {
			BuserRole buserRole = BuserRoleMgr.getInstance().get(buserId);
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
			BuserRole buserRole = BuserRoleMgr.getInstance().get(buserId);
			if(buserRole == null) {
				result.setTips("用户不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			
			BuserRoleUpdateType updateTypeEnum = updateForm.getUpdateTypeEnum();
			switch (updateTypeEnum) {
			case UpdateInfo:
				BuserRoleMgr.getInstance().update(buserId,updateForm.getUpdateInfoData());
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
}
