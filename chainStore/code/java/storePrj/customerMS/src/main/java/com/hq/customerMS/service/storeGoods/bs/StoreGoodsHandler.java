package com.hq.customerMS.service.storeGoods.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsHandler {

	public static StoreGoodsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsHandler.class);
	}

	private final LogModule logModule = LogModule.StoreGoods;

	public ReqResult<StoreGoods> get(long storeId) {
		ReqResult<StoreGoods> result = ReqResult.newInstance(false, StoreGoods.class);
		try {
			StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeGoods);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreGoodsHandler[get]", reason, e);
		}
		return result;
	}
}
