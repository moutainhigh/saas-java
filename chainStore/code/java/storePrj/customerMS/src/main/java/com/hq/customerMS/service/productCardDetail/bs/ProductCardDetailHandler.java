package com.hq.customerMS.service.productCardDetail.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.productCardDetail.data.ProductCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailHandler {

	public static ProductCardDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailHandler.class);
	}

	private final LogModule logModule = LogModule.ProductCardDetail;

	public ReqResult<ProductCardDetail> getProductCardDetail(long storeId, String id) {
		ReqResult<ProductCardDetail> result = ReqResult.newInstance(false, ProductCardDetail.class);
		try {
			ProductCardDetail productCardDetail = ProductCardDetailMgr.getInstance().get(storeId, id);
			result.setTarget(productCardDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ProductCardDetailHandler[getProductCardDetail]", reason, e);
		}
		return result;
	}
}
