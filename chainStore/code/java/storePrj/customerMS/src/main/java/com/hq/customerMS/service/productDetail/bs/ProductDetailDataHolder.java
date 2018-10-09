package com.hq.customerMS.service.productDetail.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.productDetail.data.ProductDetailCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeClient.service.productDetail.bs.ProductDetailClientMgr;
import com.hq.storeClient.service.productDetail.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailDataHolder {

	public static ProductDetailDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailDataHolder.class);
	}

	public PageResp<ProductDetail> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<ProductDetail> data = ProductDetailClientMgr.getInstance().getProductDetailPageInfo(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public ProductDetail getProductDetail(long storeId, String productId) {
		ProductDetail data = ProductDetailCacheDAO.getInstance().get(storeId, productId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ProductDetailClientMgr.getInstance().getProductDetail(storeId, productId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
