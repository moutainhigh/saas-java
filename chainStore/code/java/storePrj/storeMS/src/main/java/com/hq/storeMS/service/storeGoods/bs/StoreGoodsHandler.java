package com.hq.storeMS.service.storeGoods.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateForm;
import com.hq.storeMS.service.storeGoods.bs.update.GoodsHandlerHelper;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsHandler {

	public static StoreGoodsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsHandler.class);
	}
	
	private final LogModule logModule = LogModule.StoreGoods;

	public ReqResult<StoreGoods> findSimpleStoreInfo(long storeId) {
		ReqResult<StoreGoods> result = ReqResult.newInstance(false, StoreGoods.class);
		try {
			StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeGoods);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreGoods, "StoreGoodsHandler[findSimpleStoreInfo]", reason, e);
		}
		return result;
	}
	
	public ReqResult<StoreGoods> update(long storeId, StoreGoodsUpdateForm formInfo) {
		ReqResult<StoreGoods> result = ReqResult.newInstance(false, StoreGoods.class);
		try {
			OperateTips operateTips = GoodsHandlerHelper.getInstance().update(storeId, formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());//为了批量导入
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "StoreGoodsHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}



}
