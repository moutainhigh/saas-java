package com.hq.chainMS.service.chainProduct.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainMS.service.chainProduct.data.ProductDetail;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailHandler {

	public static ProductDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailHandler.class);
	}

	private final LogModule logModule = LogModule.ProductDetail;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<ProductDetail> pageResp = ProductDetailMgr.getInstance().getProductDetailPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ProductDetailHandler[getProductDetailPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<ProductDetail> getProductDetail(long chainId, String id) {
		ReqResult<ProductDetail> result = ReqResult.newInstance(false, ProductDetail.class);
		try {
			ProductDetail productDetail = ProductDetailMgr.getInstance().get(chainId, id);
			result.setTarget(productDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ProductDetailHandler[getProductDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
