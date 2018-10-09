package com.hq.chainClient.service.chainCard.bs;

import com.hq.chainClient.service.chainCard.apiData.AddMembershipCard;
import com.hq.chainClient.service.chainCard.apiData.AddProductCardForm;
import com.hq.chainClient.service.chainCard.apiData.BatchUpdMemberCardStateData;
import com.hq.chainClient.service.chainCard.apiData.BatchUpdProductCardStateData;
import com.hq.chainClient.service.chainCard.apiData.ChainCardUpdateApiForm;
import com.hq.chainClient.service.chainCard.apiData.ChainCardUpdateType;
import com.hq.chainClient.service.chainCard.apiData.DelMembershipCard;
import com.hq.chainClient.service.chainCard.apiData.DelProductCardForm;
import com.hq.chainClient.service.chainCard.apiData.MemberCardAllotForm;
import com.hq.chainClient.service.chainCard.apiData.MemberCardBatchAllotForm;
import com.hq.chainClient.service.chainCard.apiData.PrdCardTypeAddForm;
import com.hq.chainClient.service.chainCard.apiData.PrdCardTypeRemoveForm;
import com.hq.chainClient.service.chainCard.apiData.PrdCardTypeUpdateForm;
import com.hq.chainClient.service.chainCard.apiData.ProductCardAllotForm;
import com.hq.chainClient.service.chainCard.apiData.ProductCardBatchAllotForm;
import com.hq.chainClient.service.chainCard.apiData.UpdMemberCardStateData;
import com.hq.chainClient.service.chainCard.apiData.UpdMembershipCard;
import com.hq.chainClient.service.chainCard.apiData.UpdProductCardForm;
import com.hq.chainClient.service.chainCard.apiData.UpdProductCardStateData;
import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.ChainCardDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardClientMgr {

	public static ChainCardClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainCardClientMgr.class);
	}
	
	public ChainCard get(long chainId) {
		return ChainCardDAO.getInstance().get(chainId);
	}
	
	/***********************************次卡分类操作***********************************/
	//新增次卡分类
	public void addPrdCardType(long chainId, PrdCardTypeAddForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setPrdCardTypeAddForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.AddPrdCardType.ordinal());
		update(chainId, updateForm);
	}
	
	//删除次卡分类
	public void delPrdCardType(long chainId, PrdCardTypeRemoveForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setPrdCardTypeRemoveForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.DelPrdCardType.ordinal());
		update(chainId, updateForm);
	}
	
	//修改次卡分类
	public void updPrdCardType(long chainId, PrdCardTypeUpdateForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setPrdCardTypeUpdateForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.UpdPrdCardType.ordinal());
		update(chainId, updateForm);
	}
	
	/***********************************次卡操作***********************************/
	//新增次卡
	public void addProductCard(long chainId, AddProductCardForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setAddProductCard(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.AddProductCard.ordinal());
		update(chainId, updateForm);
	}
	
	//删除次卡
	public void delProductCard(long chainId, DelProductCardForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setDelProductCard(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.DelProductCard.ordinal());
		update(chainId, updateForm);
	}
	
	//修改次卡
	public void updProductCard(long chainId, UpdProductCardForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setUpdProductCard(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.UpdProductCard.ordinal());
		update(chainId, updateForm);
	}
	
	//修改次卡状态
	public void updProductCardState(long chainId, UpdProductCardStateData inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setUpdateProductCardStateData(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.UpdProductCardState.ordinal());
		update(chainId, updateForm);
	}
	
	//批量修改次卡状态
	public void batchUpdProductCardState(long chainId, BatchUpdProductCardStateData inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setBatchUpdateProductCardStateData(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.BatchUpdProductCardState.ordinal());
		update(chainId, updateForm);
	}
	
	//分配
	public void allotProductCard(long chainId, ProductCardAllotForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setProductCardAllotForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.ProductCardAllot.ordinal());
		update(chainId, updateForm);
	}
	
	//批量分配
	public void productCardBatchAllot(long chainId, ProductCardBatchAllotForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setProductCardBatchAllotForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.ProductCardBatchAllot.ordinal());
		update(chainId, updateForm);
	}
	
	/***********************************会员卡操作***********************************/
	//新增会员卡
	public void addMembershipCard(long chainId, AddMembershipCard inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setAddMembershipCard(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.AddMembershipCard.ordinal());
		update(chainId, updateForm);
	}
	
	//更新会员卡状态
	public void updMemberCardState(long chainId, UpdMemberCardStateData inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setUpdateMemberCardStateData(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.UpdMemberCardState.ordinal());
		update(chainId, updateForm);
	}
	
	//修改会员卡
	public void updMembershipCard(long chainId, UpdMembershipCard inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setUpdMembershipCard(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.UpdMembershipCard.ordinal());
		update(chainId, updateForm);
	}
	
	//删除会员卡
	public void delMembershipCard(long chainId, DelMembershipCard inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setDelMembershipCard(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.DelMembershipCard.ordinal());
		update(chainId, updateForm);
	}
	
	//批量更新会员卡状态
	public void batchUpdMemberCardState(long chainId, BatchUpdMemberCardStateData inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setBatchUpdateMemberCardStateData(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.BatchUpdMemberCardState.ordinal());
		update(chainId, updateForm);
	}
	
	//分配
	public void allotMemberCard(long chainId, MemberCardAllotForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setMemberCardAllotForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.MemberCardAllot.ordinal());
		update(chainId, updateForm);
	}
	
	//批量分配
	public void memberCardBatchAllot(long chainId, MemberCardBatchAllotForm inputForm) {
		ChainCardUpdateApiForm updateForm = ChainCardUpdateApiForm.newInstance();
		updateForm.setMemberCardBatchAllotForm(inputForm);
		updateForm.setUpdateType(ChainCardUpdateType.MemberCardBatchAllot.ordinal());
		update(chainId, updateForm);
	}
	
	private void update(long chainId, ChainCardUpdateApiForm updateForm) {
		ChainCardDAO.getInstance().update(chainId, updateForm);
	}
	
}
