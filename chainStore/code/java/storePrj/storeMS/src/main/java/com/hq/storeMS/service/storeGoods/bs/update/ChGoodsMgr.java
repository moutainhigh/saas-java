package com.hq.storeMS.service.storeGoods.bs.update;

import java.util.List;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataSynBeanHelper;
import com.hq.storeMS.service.chainGoods.bs.ChainGoodsMgr;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.storeGoods.apiData.GoodsBatchCancelForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsBatchPullForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsCancelForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsPullForm;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class ChGoodsMgr {
	public static ChGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChGoodsMgr.class);
	}

	public OperateTips batchCancelChainGoods(long storeId, GoodsBatchCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.BatchCancelChainGoods.getDescript() + "失败");
		List<GoodsCancelForm> cancelForms = inputForm.getCancelForms();
		for (GoodsCancelForm form : cancelForms) {
			cancelChainGoods(storeId, form);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips batchAddChainGoods(long storeId, GoodsBatchPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.BatchPullGoodsFromChain.getDescript() + "失败");
		List<GoodsPullForm> forms = inputForm.getGoodsPullForms();
		for (GoodsPullForm form : forms) {
			addChainGoods(storeId, form);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips cancelChainGoods(long storeId, GoodsCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.CancelChainGoods.getDescript() + "失败");
		StoreGoods storeData = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		Goods goods = storeData.getGoodsMap().get(inputForm.getId());
		if(goods!=null) {
			goods.setEntityState(EntityState.Deleted.ordinal());
			StoreGoodsMgr.getInstance().update(storeData);
		}
		
		GoodsDetail sdetail = GoodsDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail!=null) {
			sdetail.setEntityState(EntityState.Deleted.ordinal());
			GoodsDetailMgr.getInstance().update(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips addChainGoods(long storeId, GoodsPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.PullGoodsFromChain.getDescript() + "失败");
		StoreGoods storeData = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		ChainGoods chainData = ChainGoodsMgr.getInstance().getChainGoods(inputForm.getChainId());
		ChainDataSynBeanHelper.getInstance().synStoreGoods(storeData, chainData, inputForm.getId());
		
		boolean updFlag = true;
		GoodsDetail sdetail = GoodsDetailMgr.getInstance().get(storeId, inputForm.getId());
		com.hq.chainClient.service.chainGoods.data.GoodsDetail cdetail = ChainGoodsMgr.getInstance().getGoodsDetail(inputForm.getId(), inputForm.getChainId());
		if(sdetail == null) {
			sdetail = GoodsDetail.newInstance();
			updFlag = false;
		}
		ChainDataSynBeanHelper.getInstance().synGoodsDetail(storeId, sdetail, cdetail);
		
		StoreGoodsMgr.getInstance().update(storeData);
		if(updFlag) {
			GoodsDetailMgr.getInstance().update(sdetail);
		}else {
			GoodsDetailMgr.getInstance().addWithId(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
}
