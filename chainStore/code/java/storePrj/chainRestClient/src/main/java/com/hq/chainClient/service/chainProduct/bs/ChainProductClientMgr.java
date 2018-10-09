package com.hq.chainClient.service.chainProduct.bs;

import com.hq.chainClient.service.chainProduct.apiData.AddProductInfoData;
import com.hq.chainClient.service.chainProduct.apiData.AddProductTypeData;
import com.hq.chainClient.service.chainProduct.apiData.BatchUpdateProductStateData;
import com.hq.chainClient.service.chainProduct.apiData.ChainProductUpdateForm;
import com.hq.chainClient.service.chainProduct.apiData.ChainProductUpdateType;
import com.hq.chainClient.service.chainProduct.apiData.RemoveProductInfoData;
import com.hq.chainClient.service.chainProduct.apiData.RemoveProductTypeData;
import com.hq.chainClient.service.chainProduct.apiData.UpdateProductInfoData;
import com.hq.chainClient.service.chainProduct.apiData.UpdateProductStateData;
import com.hq.chainClient.service.chainProduct.apiData.UpdateProductTypeData;
import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.ChainProductDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductClientMgr {

	public static ChainProductClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainProductClientMgr.class);
	}
	
	public ChainProduct get(long chainId) {
		return ChainProductDAO.getInstance().get(chainId);
	}
	
	/***********************************项目分类操作***********************************/
	//新增分类
	public void addProductType(long chainId, AddProductTypeData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setAddProductTypeData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.AddProductType.ordinal());
		update(chainId, updateForm);
	}
	
	//删除分类
	public void removeProductType(long chainId, RemoveProductTypeData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setRemoveProductTypeData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.RemoveProductType.ordinal());
		update(chainId, updateForm);
	}
	
	//修改分类
	public void updateProductType(long chainId, UpdateProductTypeData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setUpdateProductTypeData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.UpdateProductType.ordinal());
		update(chainId, updateForm);
	}
	
	/***********************************项目操作***********************************/
	//新增项目
	public void addProductInfo(long chainId, AddProductInfoData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setAddProductInfoData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.AddProductInfo.ordinal());
		update(chainId, updateForm);
	}
	
	//删除项目
	public void removeProductInfo(long chainId, RemoveProductInfoData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setRemoveProductInfoData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.RemoveProductInfo.ordinal());
		update(chainId, updateForm);
	}
	
	//修改项目
	public void updateProductInfo(long chainId, UpdateProductInfoData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setUpdateProductInfoData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.UpdateProductInfo.ordinal());
		update(chainId, updateForm);
	}
	
	//修改项目状态
	public void updateProductState(long chainId, UpdateProductStateData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setUpdateProductStateData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.UpdateProductState.ordinal());
		update(chainId, updateForm);
	}
	
	//批量修改项目状态
	public void batchUpdateProductState(long chainId, BatchUpdateProductStateData inputForm) {
		ChainProductUpdateForm updateForm = ChainProductUpdateForm.newInstance();
		updateForm.setBatchUpdateProductStateData(inputForm);
		updateForm.setUpdateType(ChainProductUpdateType.BatchUpdateProductState.ordinal());
		update(chainId, updateForm);
	}
	
	private void update(long chainId, ChainProductUpdateForm updateForm) {
		ChainProductDAO.getInstance().update(chainId, updateForm);
	}
	
}
