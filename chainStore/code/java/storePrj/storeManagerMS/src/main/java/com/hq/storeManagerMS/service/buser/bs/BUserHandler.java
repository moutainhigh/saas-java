package com.hq.storeManagerMS.service.buser.bs;

import com.hq.storeClient.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeManagerMS.common.log.LogHelper;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserHandler {
	private final LogModule logModule = LogModule.BUser;

	public static BUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BUserHandler.class);
	}

	public ReqResult<BUser> get(long buserId) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserMgr.getInstance().get(buserId);
			result.setTarget(buser);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BUserHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(buserId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BUser> findByPhone(long phone) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserMgr.getInstance().findByPhone(phone);
			result.setTarget(buser);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BUserHandler[findByPhone]";
			final String reason = LogHelper.getInstance().exceptionReason(phone);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<BUser> update(long buserId, BUserUpdateApiForm updateForm) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserMgr.getInstance().update(buserId, updateForm);
			result.setTarget(buser);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BUserHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}
