package com.hq.chainStore.service.storeGoods.bs;

import com.hq.chainStore.service.storeGoods.apiData.GoodsAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsAddToTopForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsBatchCancelForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsBatchPullForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsCancelForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsCancelTopForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsPullForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsRemoveForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsUpdateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainStore.service.storeGoods.apiData.StoreGoodsUpdateForm;
import com.hq.chainStore.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.chainStore.service.storeGoods.data.StoreGoodsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsMgr {

	public static StoreGoodsMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreGoodsMgr.class);
	}

	public StoreGoods findSimpleStoreInfo(long storeId) {
		return StoreGoodsDAO.getInstance().getSimple(storeId);
	}
	
	@Deprecated
	public StoreGoods getStoreGoods(long storeId) {
		return StoreGoodsDAO.getInstance().getDetail(storeId);
	}
	
	public void update(long storeId, StoreGoodsUpdateForm updateForm) {
		StoreGoodsDAO.getInstance().update(storeId, updateForm);
	}
	
	public void addGoods(long storeId, GoodsAddForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.AddGoods.ordinal());
		updateForm.setGoodsAddForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void updateGoods(long storeId, GoodsUpdateForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.UpdateGoods.ordinal());
		updateForm.setGoodsUpdateForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void removeGoods(long storeId, GoodsRemoveForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.RemoveGoods.ordinal());
		updateForm.setGoodsRemoveForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void updateGoodsState(long storeId, GoodsUpdateStateForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.UpdateGoodsState.ordinal());
		updateForm.setGoodsUpdateStateForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void batchUpdateGoodsState(long storeId, GoodsBatchUpdateStateForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.BatchUpdateGoodsState.ordinal());
		updateForm.setGoodsBatchUpdateStateForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void addGoodsType(long storeId, GoodsTypeAddForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.AddGoodsType.ordinal());
		updateForm.setGoodsTypeAddForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void removeGoodsType(long storeId, GoodsTypeRemoveForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.RemoveGoodsType.ordinal());
		updateForm.setGoodsTypeRemoveForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void updateGoodsType(long storeId, GoodsTypeUpdateForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.UpdateGoodsType.ordinal());
		updateForm.setGoodsTypeUpdateForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void addGoodsToTop(long storeId, GoodsAddToTopForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.AddGoodsToTop.ordinal());
		updateForm.setGoodsAddToTopForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void cancelGoodsFromTop(long storeId, GoodsCancelTopForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.CancelGoodsFromTop.ordinal());
		updateForm.setGoodsCancelTopForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void batchCancelChainGoods(long storeId, GoodsBatchCancelForm inputForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.BatchCancelChainGoods.ordinal());
		updateForm.setGoodsBatchCancelForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void batchAddChainGoods(long storeId, GoodsBatchPullForm inputForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.BatchPullGoodsFromChain.ordinal());
		updateForm.setGoodsBatchPullForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void cancelChainGoods(long storeId, GoodsCancelForm inputForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.CancelChainGoods.ordinal());
		updateForm.setGoodsCancelForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void addChainGoods(long storeId, GoodsPullForm inputForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.PullGoodsFromChain.ordinal());
		updateForm.setGoodsPullForm(inputForm);
		update(storeId, updateForm);
	}
	
}
