package com.hq.chainMS.service.sellProduct.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.sellProduct.apiData.SellProductQueryForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductUpdateApiForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductUpdateType;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class SellProductHandler {

	public static SellProductHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SellProductHandler.class);
	}
	
	private final LogModule logModule = LogModule.SellProduct;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getPageInfo(SellProductQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<SellProduct> pageResp = SellProductMgr.getInstance().getPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "SellProductHandler[getPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<SellProduct> updateSellProduct(long chainId, SellProductUpdateApiForm sellProductForm) {
		ReqResult<SellProduct> result = ReqResult.newInstance(false, SellProduct.class);
		try {
			ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
			SellProductUpdateType updateType = sellProductForm.getSellProductUpdateType();
			switch (updateType) {
			case AllotSellProduct:
				SellProductMgr.getInstance().allotSellProduct(chainId, sellProductForm.getAllotSellProductForm());
				break;
			case UpdateSellProductState:
				SellProductMgr.getInstance().updateSellProductState(chainId, sellProductForm.getUpdateStateForm());
				break;
			case BatchAllotSellProduct:
				SellProductMgr.getInstance().batchAllotSellProduct(chainId, sellProductForm.getBatchAllotSellProductForm());
				break;
			case BatchUpdateSellProductState:
				SellProductMgr.getInstance().batchUpdateSellProductState(chainId, sellProductForm.getBatchUpdateStateForm());
				break;
			default:
				break;
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "SellProductHandler[allotSellProduct]";
			final String reason = LogHelper.getInstance().exceptionReason(chainId, sellProductForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
