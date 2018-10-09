package com.hq.storeMS.service.buser.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buser.data.StoreBUser;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBUserHandler {

	public static StoreBUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBUserHandler.class);
	}
	
	public ReqResult<StoreBUser> get(long storeId) {
		ReqResult<StoreBUser> result = ReqResult.newInstance(false, StoreBUser.class);
		try {
			StoreBUser target = StoreBUserMgr.getInstance().get(storeId);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "StoreBUserHandler[get]", reason, e);
		}
		return result;
	}
	
}
