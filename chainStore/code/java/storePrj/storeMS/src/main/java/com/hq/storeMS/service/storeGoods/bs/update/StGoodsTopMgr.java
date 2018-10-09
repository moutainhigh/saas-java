package com.hq.storeMS.service.storeGoods.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.TopFlagEnum;
import com.hq.storeMS.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.storeGoods.apiData.GoodsAddToTopForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsCancelTopForm;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StGoodsTopMgr {
	public static StGoodsTopMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StGoodsTopMgr.class);
	}
	
	//置顶
	public OperateTips addToTop(long storeId, GoodsAddToTopForm inputData){
		return operdateTop(storeId, StoreGoodsUpdateType.AddGoodsToTop, inputData.getGoodsId());
	}
	
	//取消置顶
	public OperateTips cancelTop(long storeId, GoodsCancelTopForm inputData){
		return operdateTop(storeId, StoreGoodsUpdateType.CancelGoodsFromTop, inputData.getGoodsId());
	}
	
	private OperateTips operdateTop(long storeId, StoreGoodsUpdateType updateType, String id) {
		OperateTips tips = OperateTips.newInstance(false, updateType.getDescript()+"失败");
		StoreGoods storeData = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		Goods data = storeData.getGoodsMap().get(id);
		if(data != null) {
			GoodsDetail detail = GoodsDetailMgr.getInstance().get(storeId, id);
			if(updateType == StoreGoodsUpdateType.AddGoodsToTop) {
				data.setTopFlag(TopFlagEnum.Top.ordinal());
				detail.setTopFlag(TopFlagEnum.Top.ordinal());
			}else if(updateType == StoreGoodsUpdateType.CancelGoodsFromTop){
				data.setTopFlag(TopFlagEnum.Normal.ordinal());
				detail.setTopFlag(TopFlagEnum.Top.ordinal());
			}
			StoreGoodsMgr.getInstance().update(storeData);
			GoodsDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}else {
			tips.setTips("商品不存在");
		}
		return tips;
	}
}
