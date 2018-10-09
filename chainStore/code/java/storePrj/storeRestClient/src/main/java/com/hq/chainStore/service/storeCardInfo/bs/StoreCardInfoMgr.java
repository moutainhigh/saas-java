package com.hq.chainStore.service.storeCardInfo.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hq.chainStore.service.storeCardInfo.apiData.AddDiscountCard;
import com.hq.chainStore.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.DelDiscountCard;
import com.hq.chainStore.service.storeCardInfo.apiData.DelMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.DelProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeAddForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeRemoveForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeUpdateForm;
import com.hq.chainStore.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;
import com.hq.chainStore.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdDiscountCard;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.data.CardStatusEnum;
import com.hq.chainStore.service.storeCardInfo.data.DiscountCard;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoMgr {

	public static StoreCardInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoMgr.class);
	}
	
	public StoreCardInfo findSimpleStoreInfo(long storeId) {
		return StoreCardInfoDAO.getInstance().getSimple(storeId);
	}
	
	@Deprecated
	public StoreCardInfo getStoreCardInfo(long storeId) {
		return StoreCardInfoDAO.getInstance().getDetail(storeId);
	}

	public void updateStoreCardInfo(long storeId, StoreCardInfoUpdateApiForm apiForm) {
		StoreCardInfoDAO.getInstance().update(storeId, apiForm);
	}
	
	//=======================具体的修改操作=======================
	/**
	 * @Deprecated 获取店铺已上架的优惠卷列表   获取的的方法 都迁移到XXXholder里面
	 * @param storeId
	 * @return
	 */
	@Deprecated
	public List<DiscountCard> getDiscountCardList(long storeId, CardStatusEnum status){
		StoreCardInfo storeCardInfo = StoreCardInfoDAO.getInstance().get(storeId);
		List<DiscountCard> list = new ArrayList<DiscountCard>();
		Collection<DiscountCard> tmpList = storeCardInfo.getDiscountCardMap().values();
		long nowTime = System.currentTimeMillis();
		for (DiscountCard discountCard : tmpList) {
			if(discountCard.getStatus() == status.ordinal() && nowTime < discountCard.getEffectiveTime()){
				list.add(discountCard);
			}
		}
		return list;
	}
	
	/**
	 * @Deprecated 获取店铺已上架的会员卡列表 获取的的方法 都迁移到XXXholder里面
	 * @param storeId
	 * @return
	 */
	@Deprecated
	public List<MembershipCard> getMembershipCardList(long storeId, CardStatusEnum status){
		StoreCardInfo storeCardInfo = StoreCardInfoDAO.getInstance().get(storeId);
		List<MembershipCard> list = new ArrayList<MembershipCard>();
		Collection<MembershipCard> tmpList = storeCardInfo.getMembershipCardMap().values();
		for (MembershipCard data : tmpList) {
			if(data.getStatus() == status.ordinal()){
				list.add(data);
			}
		}
		return list;
	}
	
	public void addMembershipCard(long storeId, AddMembershipCard param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setAddMembershipCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.AddMembershipCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void delMembershipCard(long storeId, DelMembershipCard param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setDelMembershipCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.DelMembershipCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void updMembershipCard(long storeId, UpdMembershipCard param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdMembershipCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdMembershipCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void updMemberCardState(long storeId, UpdMemberCardStateData updateMemberCardStateData){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdateMemberCardStateData(updateMemberCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdMemberCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void batchUpdMemberCardState(long storeId, BatchUpdMemberCardStateData batchUpdateMemberCardStateData){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setBatchUpdateMemberCardStateData(batchUpdateMemberCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.BatchUpdMemberCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	
	public void addProductCard(long storeId, AddProductCardForm param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setAddProductCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.AddProductCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void delProductCard(long storeId, DelProductCardForm param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setDelProductCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.DelProductCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void updProductCard(long storeId, UpdProductCardForm param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdProductCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdProductCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void updProductCardState(long storeId, UpdProductCardStateData updateProductCardStateData){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdateProductCardStateData(updateProductCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdProductCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void batchUpdProductCardState(long storeId, BatchUpdProductCardStateData batchUpdateProductCardStateData){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setBatchUpdateProductCardStateData(batchUpdateProductCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.BatchUpdProductCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void addDiscountCard(long storeId, AddDiscountCard param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setAddDiscountCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.AddDiscountCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void delDiscountCard(long storeId, DelDiscountCard param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setDelDiscountCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.DelDiscountCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void updDiscountCard(long storeId, UpdDiscountCard param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdDiscountCard(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdDiscountCard.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void addPrdCardType(long storeId, PrdCardTypeAddForm param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setPrdCardTypeAddForm(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.AddPrdCardType.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void delPrdCardType(long storeId, PrdCardTypeRemoveForm param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setPrdCardTypeRemoveForm(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.DelPrdCardType.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}
	
	public void updPrdCardType(long storeId, PrdCardTypeUpdateForm param){
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setPrdCardTypeUpdateForm(param);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdPrdCardType.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}

}
