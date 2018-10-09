package com.hq.chainMS.service.detailDataVersion.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.detailDataVersion.data.DetailDataVersion;
import com.zenmind.common.hotSwap.HotSwap;

public class DetailDataVersionHandler {

	public static DetailDataVersionHandler getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionHandler.class);
	}

	public ReqResult<DetailDataVersion> get(long chainId) {
		ReqResult<DetailDataVersion> result = ReqResult.newInstance(false, DetailDataVersion.class);
		try {
			DetailDataVersion detailDataVersion = DetailDataVersionMgr.getInstance().get(chainId);
			if (detailDataVersion != null) {
				result.setTarget(detailDataVersion);
				result.setSuccess(true);
			} else {
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.DetailDataVersion, "DetailDataVersionHandler[get]", reason, e);
		}
		return result;
	}

}
