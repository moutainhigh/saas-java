package com.hq.storeMS.service.storeProductInfo.bs.update;

import java.util.List;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataSynBeanHelper;
import com.hq.storeMS.service.chainProduct.bs.ChainProductMgr;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.productDetail.bs.ProductDetailMgr;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductBatchCancelForm;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductBatchPullForm;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductCancelForm;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductPullForm;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ChProductMgr {
	public static ChProductMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChProductMgr.class);
	}

	public OperateTips batchCancelChainProduct(long storeId, ProductBatchCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.BatchCancelChainProduct.getDescript() + "失败");
		List<ProductCancelForm> cancelForms = inputForm.getCancelForms();
		for (ProductCancelForm form : cancelForms) {
			cancelChainProduct(storeId, form);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips batchAddChainProduct(long storeId, ProductBatchPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.BatchPullProductFromChain.getDescript() + "失败");
		List<ProductPullForm> forms = inputForm.getPullForms();
		
		for (ProductPullForm form : forms) {
			addChainProduct(storeId, form);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips cancelChainProduct(long storeId, ProductCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.CancelChainProduct.getDescript() + "失败");
		StoreProductInfo storeData = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		ProductInfo productInfo = storeData.getProductInfoMap().get(inputForm.getId());
		if(productInfo!=null) {
			productInfo.setEntityState(EntityState.Deleted.ordinal());
			StoreProductInfoMgr.getInstance().update(storeData);
		}
		
		ProductDetail sdetail = ProductDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail!=null) {
			sdetail.setEntityState(EntityState.Deleted.ordinal());
			ProductDetailMgr.getInstance().update(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips addChainProduct(long storeId, ProductPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.PullProductFromChain.getDescript() + "失败");
		StoreProductInfo storeData = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		ChainProduct chainData = ChainProductMgr.getInstance().getChainProduct(inputForm.getChainId());
		ChainDataSynBeanHelper.getInstance().synStoreProduct(storeData, chainData, inputForm.getId());
		
		boolean updFlag = true;
		ProductDetail sdetail = ProductDetailMgr.getInstance().get(storeId, inputForm.getId());
		com.hq.chainClient.service.chainProduct.data.ProductDetail cdetail = ChainProductMgr.getInstance().getProductDetail(inputForm.getId(), inputForm.getChainId());
		if(sdetail == null) {
			sdetail = ProductDetail.newInstance();
			updFlag = false;
		}
		ChainDataSynBeanHelper.getInstance().synProductDetail(storeId, sdetail, cdetail);
		
		StoreProductInfoMgr.getInstance().update(storeData);
		if(updFlag) {
			ProductDetailMgr.getInstance().update(sdetail);
		}else {
			ProductDetailMgr.getInstance().addWithId(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
}
