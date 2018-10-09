package com.hq.customerMS.service.productCardDetail.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.productCardDetail.data.ProductCardDetailCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeClient.service.productCardDetail.bs.ProductCardDetailClientMgr;
import com.hq.storeClient.service.productCardDetail.data.ProductCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailDataHolder {

	public static ProductCardDetailDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailDataHolder.class);
	}
	
	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(ProductCardDetailQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<ProductCardDetail> data = ProductCardDetailClientMgr.getInstance().getProductCardDetailPageInfo(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public ProductCardDetail get(long storeId, String productCardDetailId) {
		ProductCardDetail data = ProductCardDetailCacheDAO.getInstance().get(storeId, productCardDetailId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ProductCardDetailClientMgr.getInstance().getProductCardDetail(storeId, productCardDetailId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}
