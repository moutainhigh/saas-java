package com.hq.chainClient.service.chainGoods.bs;

import com.hq.chainClient.service.chainGoods.apiData.ChainGoodsUpdateForm;
import com.hq.chainClient.service.chainGoods.apiData.ChainGoodsUpdateType;
import com.hq.chainClient.service.chainGoods.apiData.GoodsAddForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsRemoveForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsTypeAddForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsUpdateForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.ChainGoodsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsClientMgr {

	public static ChainGoodsClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainGoodsClientMgr.class);
	}
	
	public ChainGoods get(long chainId) {
		return ChainGoodsDAO.getInstance().get(chainId);
	}
	
	/***********************************商品分类操作***********************************/
	//新增分类
	public void addGoodsType(long chainId, GoodsTypeAddForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsTypeAddForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.AddGoodsType.ordinal());
		update(chainId, updateForm);
	}
	
	//删除分类
	public void removeGoodsType(long chainId, GoodsTypeRemoveForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsTypeRemoveForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.RemoveGoodsType.ordinal());
		update(chainId, updateForm);
	}
	
	//修改分类
	public void updateGoodsType(long chainId, GoodsTypeUpdateForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsTypeUpdateForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.UpdateGoodsType.ordinal());
		update(chainId, updateForm);
	}
	
	/***********************************商品操作***********************************/
	//新增商品
	public void addGoods(long chainId, GoodsAddForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsAddForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.AddGoods.ordinal());
		update(chainId, updateForm);
	}
	
	//删除商品
	public void removeGoods(long chainId, GoodsRemoveForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsRemoveForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.RemoveGoods.ordinal());
		update(chainId, updateForm);
	}
	
	//修改商品
	public void updateGoods(long chainId, GoodsUpdateForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsUpdateForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.UpdateGoods.ordinal());
		update(chainId, updateForm);
	}
	
	//修改商品状态
	public void updateGoodsState(long chainId, GoodsUpdateStateForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsUpdateStateForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.UpdateGoodsState.ordinal());
		update(chainId, updateForm);
	}
	
	//批量修改商品状态
	public void batchUpdateGoodsState(long chainId, GoodsBatchUpdateStateForm inputForm) {
		ChainGoodsUpdateForm updateForm = ChainGoodsUpdateForm.newInstance();
		updateForm.setGoodsBatchUpdateStateForm(inputForm);
		updateForm.setUpdateType(ChainGoodsUpdateType.BatchUpdateGoodsState.ordinal());
		update(chainId, updateForm);
	}
	
	private void update(long chainId, ChainGoodsUpdateForm updateForm) {
		ChainGoodsDAO.getInstance().update(chainId, updateForm);
	}
	
}
