package com.hq.storeMS.service.detailDataVersion.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersion;
import com.zenmind.common.hotSwap.HotSwap;

public class DetailDataVersionHandler {

	public static DetailDataVersionHandler getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionHandler.class);
	}

	public ReqResult<DetailDataVersion> get(long storeId) {
		ReqResult<DetailDataVersion> result = ReqResult.newInstance(false, DetailDataVersion.class);
		try {
			DetailDataVersion detailDataVersion = DetailDataVersionMgr.getInstance().get(storeId);
			if (detailDataVersion != null) {
				result.setTarget(detailDataVersion);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} else {
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.DetailDataVersion, "DetailDataVersionHandler[get]", reason, e);
		}
		return result;
	}

}
