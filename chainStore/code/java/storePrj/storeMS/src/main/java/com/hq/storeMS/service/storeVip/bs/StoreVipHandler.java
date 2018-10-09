package com.hq.storeMS.service.storeVip.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeVip.data.StoreVip;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreVipHandler {

	public static StoreVipHandler getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipHandler.class);
	}
	
	public ReqResult<StoreVip> checkExpired(long storeId) {
		ReqResult<StoreVip> result = ReqResult.newInstance(false, StoreVip.class);
		try {
			StoreVip storeVip = StoreVip.newInstance();
			if(StoreVipMgr.getInstance().isExpired(storeId)){
				storeVip.setSuccess(false).setTips("会员已过期");
				result.setTarget(storeVip);
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("会员已过期");
			}else{
				storeVip.setSuccess(true);
				result.setTarget(storeVip);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.StoreVip, "StoreVipHandler[checkExpired]", reason, e);
		}

		return result;
	}

}
