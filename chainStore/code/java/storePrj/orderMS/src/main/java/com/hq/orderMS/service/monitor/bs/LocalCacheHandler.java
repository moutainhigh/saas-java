package com.hq.orderMS.service.monitor.bs;

import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.wraper.GuavaCacheMgr;
import com.zenmind.dao.redis.wraper.ShardGuavaCacheMgr;
import com.zenmind.dao.redis.wraper.StoreGuavaCacheMgr;

public class LocalCacheHandler {

	public static LocalCacheHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LocalCacheHandler.class);
	}

	public ReqResult<String> clear() {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		try {
			ShardGuavaCacheMgr.getInstance().clear();
			StoreGuavaCacheMgr.getInstance().clear();
			GuavaCacheMgr.getInstance().clear();
			result.setStatus(RespStatus.OK);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Tmp, "LocalCacheHandler[clear]", "", e);
		}
		return result;
	}

}
