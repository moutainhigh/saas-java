package com.hq.storeMS.service.chainGoods.bs;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsHandler {

	public static ChainGoodsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsHandler.class);
	}
	
	private final LogModule logModule = LogModule.ChainGoods;

	public ReqResult<ChainGoods> getChainGoods(long chainId) {
		ReqResult<ChainGoods> result = ReqResult.newInstance(false, ChainGoods.class);
		try {
			ChainGoods chainGoods = ChainGoodsMgr.getInstance().getChainGoods(chainId);
			result.setTarget(chainGoods);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainGoodsHandler[getChainGoods]", reason, e);
		}
		return result;
	}
	
	public ReqResult<GoodsDetail> getGoodsDetail(String goodsId, long chainId) {
		ReqResult<GoodsDetail> result = ReqResult.newInstance(false, GoodsDetail.class);
		try {
			GoodsDetail detail = ChainGoodsMgr.getInstance().getGoodsDetail(goodsId, chainId);
			result.setTarget(detail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(goodsId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainGoodsHandler[getGoodsDetail]", reason, e);
		}
		return result;
	}

}
