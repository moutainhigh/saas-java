package com.hq.customerMS.service.productDetail.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeClient.service.productDetail.data.ProductDetail;
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
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ProductDetailHandler[getProductDetailPageInfo]", reason, e);
		}
		return result;
	}

	public ReqResult<ProductDetail> getStoreProductDetail(long storeId, String id) {
		ReqResult<ProductDetail> result = ReqResult.newInstance(false, ProductDetail.class);
		try {
			ProductDetail productDetail = ProductDetailMgr.getInstance().getProductDetail(storeId, id);
			result.setTarget(productDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ProductDetailHandler[getStoreProductDetail]", reason, e);
		}
		return result;
	}
}
