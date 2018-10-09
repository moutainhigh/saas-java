package com.hq.storeMS.service.storeCardInfo.data;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.storeMS.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.storeMS.service.storeCardInfo.apiData.DelMembershipCard;
import com.hq.storeMS.service.storeCardInfo.apiData.DelProductCardForm;
import com.hq.storeMS.service.storeCardInfo.apiData.PrdCardTypeAddForm;
import com.hq.storeMS.service.storeCardInfo.apiData.PrdCardTypeRemoveForm;
import com.hq.storeMS.service.storeCardInfo.apiData.PrdCardTypeUpdateForm;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdMembershipCard;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdProductCardForm;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoBeanHelper {

	public static StoreCardInfoBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoBeanHelper.class);
	}

	public boolean addPrdCardType(StoreCardInfo storeCardInfo, PrdCardTypeAddForm param) {
		if(storeCardInfo == null){
			return false;
		}
		PrdCardType prdCardType = param.toPrdCardType();
		Map<String, PrdCardType> prdCardTypeMap = storeCardInfo.getPrdCardTypeMap();
		long index = param.getIndex();
		if (!prdCardTypeMap.containsKey(prdCardType.getId()) && storeCardInfo.getPrdCardTypeIndex() + 1 == index) {
			prdCardTypeMap.put(prdCardType.getId(), prdCardType);
			storeCardInfo.setPrdCardTypeIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean delPrdCardType(StoreCardInfo storeCardInfo, PrdCardTypeRemoveForm param) {
		if(storeCardInfo == null){
			return false;
		}
		Map<String, PrdCardType> prdCardTypeMap = storeCardInfo.getPrdCardTypeMap();
		if (prdCardTypeMap.containsKey(param.getId())) {
			PrdCardType prdCardType = prdCardTypeMap.get(param.getId());
			prdCardType.setEntityState(EntityState.Deleted.ordinal());
			prdCardType.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	public boolean updatePrdCardType(StoreCardInfo storeCardInfo, PrdCardTypeUpdateForm param) {
		if(storeCardInfo == null){
			return false;
		}
		Map<String, PrdCardType> prdCardTypeMap = storeCardInfo.getPrdCardTypeMap();
		if (prdCardTypeMap.containsKey(param.getId())) {
			PrdCardType prdCardType = prdCardTypeMap.get(param.getId());
			param.updatePrdCardType(prdCardType);
			prdCardType.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	public boolean addMembershipCard(StoreCardInfo storeCardInfo, AddMembershipCard param) {
		if(storeCardInfo == null){
			return false;
		}
		MembershipCard data = param.toMembershipCard(storeCardInfo.getStoreId());
		Map<String, MembershipCard> membershipCardMap = storeCardInfo.getMembershipCardMap();
		int index = param.getIndex();
		if (!membershipCardMap.containsKey(data.getId()) && storeCardInfo.getMembershipCardIndex() + 1 == index) {
			membershipCardMap.put(data.getId(), data);
			storeCardInfo.setMembershipCardIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean delMembershipCard(StoreCardInfo storeCardInfo, DelMembershipCard param) {
		if(storeCardInfo == null){
			return false;
		}
		param.setId(getLocalId(param.getId()));
		Map<String, MembershipCard> membershipCardMap = storeCardInfo.getMembershipCardMap();
		if (membershipCardMap.containsKey(param.getId())) {
			MembershipCard membershipCard = membershipCardMap.get(param.getId());
			membershipCard.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
	
	public boolean updMembershipCard(StoreCardInfo storeCardInfo, UpdMembershipCard param) {
		if(storeCardInfo == null){
			return false;
		}
		param.setId(getLocalId(param.getId()));
		Map<String, MembershipCard> membershipCardMap = storeCardInfo.getMembershipCardMap();
		if (membershipCardMap.containsKey(param.getId())) {
			MembershipCard oldData = membershipCardMap.get(param.getId());
			MembershipCard newData = AppUtils.copyBeanBySerialize(oldData, MembershipCard.class);
			param.updateMembershipCard(newData);
			if(!newData.equals(oldData)) {
				membershipCardMap.put(newData.getId(), newData);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateMembershipCardState(StoreCardInfo storeCardInfo, String mbCardId, int status){
		if(storeCardInfo == null){
			return false;
		}
		mbCardId = getLocalId(mbCardId);
		Map<String, MembershipCard> membershipCardMap = storeCardInfo.getMembershipCardMap();
		if (membershipCardMap.containsKey(mbCardId)){
			MembershipCard membershipCard = membershipCardMap.get(mbCardId);
			membershipCard.setStatus(status);
			return true;
		}
		return false;
	}
	
	public boolean addProductCard(StoreCardInfo storeCardInfo, AddProductCardForm param) {
		if(storeCardInfo == null){
			return false;
		}
		long storeId = storeCardInfo.getStoreId();
		ProductCard data = param.toProductCard(storeId);
		Map<String, ProductCard> productCardMap = storeCardInfo.getProductCardMap();
		int index = param.getIndex();
		if (!productCardMap.containsKey(data.getId()) && storeCardInfo.getProductCardIndex() + 1 == index) {
			productCardMap.put(data.getId(), data);
			storeCardInfo.setProductCardIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean updProductCard(StoreCardInfo storeCardInfo, UpdProductCardForm param) {
		if(storeCardInfo == null){
			return false;
		}
		param.setId(getLocalId(param.getId()));
		Map<String, ProductCard> productCardMap = storeCardInfo.getProductCardMap();
		if (productCardMap.containsKey(param.getId())){
			ProductCard oldData = productCardMap.get(param.getId());
			ProductCard newData = AppUtils.copyBeanBySerialize(oldData, ProductCard.class);
			param.updateProductCard(newData);
			if(!oldData.equals(newData)) {
				productCardMap.put(newData.getId(), newData);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateProductCardState(StoreCardInfo storeCardInfo,String prdCardId,int status){
		if(storeCardInfo == null){
			return false;
		}
		prdCardId = getLocalId(prdCardId);
		Map<String, ProductCard> ProductCardMap = storeCardInfo.getProductCardMap();
		if (ProductCardMap.containsKey(prdCardId)){
			ProductCard productCard = ProductCardMap.get(prdCardId);
			productCard.setStatus(status);
			return true;
		}
		return false;
	}
	
	private String getLocalId(String id) {
		if(id.contains(ServerConstants.CHAIN_ID_SUFFFIX)) {
			return ServerConstants.CHAIN_ID_SUFFFIX+StringUtils.substringAfter(id, ServerConstants.CHAIN_ID_SUFFFIX);
		}
		return id;
	}
	
	public boolean delProductCard(StoreCardInfo storeCardInfo, DelProductCardForm param) {
		if(storeCardInfo == null){
			return false;
		}
		param.setId(getLocalId(param.getId()));
		Map<String, ProductCard> productCardMap = storeCardInfo.getProductCardMap();
		if (productCardMap.containsKey(param.getId())) {
			ProductCard productCard = productCardMap.get(param.getId());
			productCard.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
}
