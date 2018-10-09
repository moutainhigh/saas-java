package com.hq.storeMS.service.storeProductInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.TopFlagEnum;
import com.hq.storeMS.service.productDetail.bs.ProductDetailMgr;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductToTopData;
import com.hq.storeMS.service.storeProductInfo.apiData.CancelProductFromTopData;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StProductTopMgr {
	public static StProductTopMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StProductTopMgr.class);
	}
	
	//置顶
	public OperateTips addToTop(AddProductToTopData inputData){
		return operdateTop(inputData.getStoreId(), StoreProductInfoUpdateType.AddProductToTop, inputData.getProductId());
	}
	
	//取消置顶
	public OperateTips cancelTop(CancelProductFromTopData inputData){
		return operdateTop(inputData.getStoreId(), StoreProductInfoUpdateType.CancelProductFromTop, inputData.getProductId());
	}
	
	private OperateTips operdateTop(long storeId, StoreProductInfoUpdateType updateType, String id) {
		OperateTips tips = OperateTips.newInstance(false, updateType.getDescript()+"失败");
		StoreProductInfo storeData = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		ProductInfo data = storeData.getProductInfoMap().get(id);
		if(data != null) {
			ProductDetail detail = ProductDetailMgr.getInstance().get(storeId, id);
			if(updateType==StoreProductInfoUpdateType.AddProductToTop) {
				data.setTopFlag(TopFlagEnum.Top.ordinal());
				detail.setTopFlag(TopFlagEnum.Top.ordinal());
			}else if(updateType==StoreProductInfoUpdateType.CancelProductFromTop) {
				data.setTopFlag(TopFlagEnum.Normal.ordinal());
				detail.setTopFlag(TopFlagEnum.Normal.ordinal());
			}
			StoreProductInfoMgr.getInstance().update(storeData);
			ProductDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}else {
			tips.setTips("项目不存在");
		}
		return tips;
	}
}
