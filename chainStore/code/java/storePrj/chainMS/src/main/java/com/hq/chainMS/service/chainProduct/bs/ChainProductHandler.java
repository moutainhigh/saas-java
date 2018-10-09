package com.hq.chainMS.service.chainProduct.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateForm;
import com.hq.chainMS.service.chainProduct.bs.updateHandle.ProductHandleHelper;
import com.hq.chainMS.service.chainProduct.data.ChainProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductHandler {

	public static ChainProductHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductHandler.class);
	}

	public ReqResult<ChainProduct> getChainProduct(long chainId) {
		ReqResult<ChainProduct> result = ReqResult.newInstance(false, ChainProduct.class);
		try {
			ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
			result.setTarget(chainProduct);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.ChainProduct, "ChainProductHandler[findSimpleChainInfo]", reason, e);
		}
		return result;
	}

	public ReqResult<ChainProduct> update(long chainId, ChainProductUpdateForm formInfo) {
		ReqResult<ChainProduct> result = ReqResult.newInstance(false, ChainProduct.class);
		try {
			OperateTips operateTips = ProductHandleHelper.getInstance().update(chainId, formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.ChainProduct;
			final String logId = "ChainProductHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
