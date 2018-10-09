package com.hq.chainMS.service.chainGoods.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateForm;
import com.hq.chainMS.service.chainGoods.bs.updateHandle.GoodsHandleHelper;
import com.hq.chainMS.service.chainGoods.data.ChainGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsHandler {

	public static ChainGoodsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsHandler.class);
	}
	
	private final LogModule logModule = LogModule.ChainGoods;

	public ReqResult<ChainGoods> get(long chainId) {
		ReqResult<ChainGoods> result = ReqResult.newInstance(false, ChainGoods.class);
		try {
			ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
			result.setTarget(chainGoods);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.ChainGoods, "ChainGoodsHandler[get]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ChainGoods> update(long chainId, ChainGoodsUpdateForm formInfo) {
		ReqResult<ChainGoods> result = ReqResult.newInstance(false, ChainGoods.class);
		try {
			OperateTips operateTips = GoodsHandleHelper.getInstance().update(chainId, formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());//为了批量导入
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "ChainGoodsHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}
