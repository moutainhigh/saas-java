package com.hq.chainMS.service.chainCard.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.data.ProductCardDetail;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailHandler {

	public static ProductCardDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailHandler.class);
	}

	private final LogModule logModule = LogModule.ProductCardDetail;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getProductCardDetailPageInfo(ProductCardDetailQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<ProductCardDetail> pageResp = ProductCardDetailMgr.getInstance().getProductCardDetailPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ProductCardDetailHandler[getProductCardDetailPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<ProductCardDetail> getProductCardDetail(long chainId, String id) {
		ReqResult<ProductCardDetail> result = ReqResult.newInstance(false, ProductCardDetail.class);
		try {
			ProductCardDetail productCardDetail = ProductCardDetailMgr.getInstance().get(chainId, id);
			result.setTarget(productCardDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ProductCardDetailHandler[getProductCardDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
