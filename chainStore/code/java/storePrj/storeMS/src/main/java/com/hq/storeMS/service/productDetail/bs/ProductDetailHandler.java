package com.hq.storeMS.service.productDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
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

	public ReqResult<ProductDetail> getStoreProductDetail(long storeId, String id) {
		ReqResult<ProductDetail> result = ReqResult.newInstance(false, ProductDetail.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			ProductDetail productDetail = ProductDetailMgr.getInstance().get(storeId, id);
			result.setTarget(productDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ProductDetailHandler[getStoreProductDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
