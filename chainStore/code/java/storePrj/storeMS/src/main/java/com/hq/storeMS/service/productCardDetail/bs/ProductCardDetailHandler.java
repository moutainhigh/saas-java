package com.hq.storeMS.service.productCardDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
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

	public ReqResult<ProductCardDetail> getStoreProductCardDetail(long storeId, String id) {
		ReqResult<ProductCardDetail> result = ReqResult.newInstance(false, ProductCardDetail.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			ProductCardDetail productCardDetail = ProductCardDetailMgr.getInstance().get(storeId, id);
			result.setTarget(productCardDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ProductCardDetailHandler[getProductCardDetail]";
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
