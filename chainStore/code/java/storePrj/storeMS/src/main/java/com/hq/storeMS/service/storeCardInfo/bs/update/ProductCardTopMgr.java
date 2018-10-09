package com.hq.storeMS.service.storeCardInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.TopFlagEnum;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.storeCardInfo.apiData.AddPrdCardTop;
import com.hq.storeMS.service.storeCardInfo.apiData.CancelPrdCardTop;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardTopMgr {
	
	public static ProductCardTopMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardTopMgr.class);
	}
	
	//置顶
	public OperateTips addToTop(long storeId, AddPrdCardTop inputData){
		return operdateTop(storeId, StoreCardInfoUpdateType.AddPrdCardTop, inputData.getId());
	}
	
	//取消置顶
	public OperateTips cancelTop(long storeId, CancelPrdCardTop inputData){
		return operdateTop(storeId, StoreCardInfoUpdateType.CancelPrdCardTop, inputData.getId());
	}
	
	private OperateTips operdateTop(long storeId, StoreCardInfoUpdateType updateType, String id) {
		OperateTips tips = OperateTips.newInstance(false, updateType.getMark()+"失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		ProductCard data = storeData.getProductCardMap().get(id);
		if(data != null) {
			ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(storeId, id);
			if(updateType == StoreCardInfoUpdateType.AddPrdCardTop) {
				data.setTopFlag(TopFlagEnum.Top.ordinal());
				detail.setTopFlag(TopFlagEnum.Top.ordinal());
			}else if(updateType == StoreCardInfoUpdateType.CancelPrdCardTop){
				data.setTopFlag(TopFlagEnum.Normal.ordinal());
				detail.setTopFlag(TopFlagEnum.Normal.ordinal());
			}
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
			ProductCardDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}else {
			tips.setTips("次卡不存在");
		}
		return tips;
	}
}
