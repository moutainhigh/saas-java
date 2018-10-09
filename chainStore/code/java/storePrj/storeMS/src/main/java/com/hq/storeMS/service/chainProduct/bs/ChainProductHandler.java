package com.hq.storeMS.service.chainProduct.bs;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductHandler {

	public static ChainProductHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductHandler.class);
	}
	
	private final LogModule logModule = LogModule.ChainProduct;

	public ReqResult<ChainProduct> getChainProduct(long chainId) {
		ReqResult<ChainProduct> result = ReqResult.newInstance(false, ChainProduct.class);
		try {
			ChainProduct chainProduct = ChainProductMgr.getInstance().getChainProduct(chainId);
			result.setTarget(chainProduct);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainProductHandler[getChainProduct]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ProductDetail> getProductDetail(String productId, long chainId) {
		ReqResult<ProductDetail> result = ReqResult.newInstance(false, ProductDetail.class);
		try {
			ProductDetail detail = ChainProductMgr.getInstance().getProductDetail(productId, chainId);
			result.setTarget(detail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(productId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainProductHandler[getProductDetail]", reason, e);
		}
		return result;
	}

}
