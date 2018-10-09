package com.hq.chainMS.service.chainCard.data;

import java.util.Map;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainCard.apiData.AddMembershipCard;
import com.hq.chainMS.service.chainCard.apiData.AddProductCardForm;
import com.hq.chainMS.service.chainCard.apiData.DelMembershipCard;
import com.hq.chainMS.service.chainCard.apiData.DelProductCardForm;
import com.hq.chainMS.service.chainCard.apiData.MemberCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.PrdCardTypeAddForm;
import com.hq.chainMS.service.chainCard.apiData.PrdCardTypeRemoveForm;
import com.hq.chainMS.service.chainCard.apiData.PrdCardTypeUpdateForm;
import com.hq.chainMS.service.chainCard.apiData.ProductCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.UpdMemberCardStateData;
import com.hq.chainMS.service.chainCard.apiData.UpdMembershipCard;
import com.hq.chainMS.service.chainCard.apiData.UpdProductCardForm;
import com.hq.chainMS.service.chainCard.apiData.UpdProductCardStateData;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardBeanHelper {

	public static ChainCardBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardBeanHelper.class);
	}

	public boolean addPrdCardType(ChainCard chainCard, PrdCardTypeAddForm param) {
		if(chainCard == null){
			return false;
		}
		PrdCardType prdCardType = param.toPrdCardType(chainCard.getChainId());
		Map<String, PrdCardType> prdCardTypeMap = chainCard.getPrdCardTypeMap();
		long index = param.getIndex();
		if (!prdCardTypeMap.containsKey(prdCardType.getId()) && chainCard.getPrdCardTypeIndex() + 1 == index) {
			prdCardTypeMap.put(prdCardType.getId(), prdCardType);
			chainCard.setPrdCardTypeIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean delPrdCardType(ChainCard chainCard, PrdCardTypeRemoveForm param) {
		if(chainCard == null){
			return false;
		}
		Map<String, PrdCardType> prdCardTypeMap = chainCard.getPrdCardTypeMap();
		if (prdCardTypeMap.containsKey(param.getId())) {
			PrdCardType prdCardType = prdCardTypeMap.get(param.getId());
			prdCardType.setEntityState(EntityState.Deleted.ordinal());
			prdCardType.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	public boolean updatePrdCardType(ChainCard chainCard, PrdCardTypeUpdateForm param) {
		if(chainCard == null){
			return false;
		}
		Map<String, PrdCardType> prdCardTypeMap = chainCard.getPrdCardTypeMap();
		if (prdCardTypeMap.containsKey(param.getId())) {
			PrdCardType prdCardType = prdCardTypeMap.get(param.getId());
			param.updatePrdCardType(prdCardType);
			prdCardType.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	public boolean addMembershipCard(ChainCard chainCard, AddMembershipCard param) {
		if(chainCard == null){
			return false;
		}
		MembershipCard data = param.toMembershipCard(chainCard.getChainId());
		Map<String, MembershipCard> membershipCardMap = chainCard.getMembershipCardMap();
		int index = param.getIndex();
		if (!membershipCardMap.containsKey(data.getId()) && chainCard.getMembershipCardIndex() + 1 == index) {
			membershipCardMap.put(data.getId(), data);
			chainCard.setMembershipCardIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean delMembershipCard(ChainCard chainCard, DelMembershipCard param) {
		if(chainCard == null){
			return false;
		}
		Map<String, MembershipCard> membershipCardMap = chainCard.getMembershipCardMap();
		if (membershipCardMap.containsKey(param.getId())) {
			MembershipCard membershipCard = membershipCardMap.get(param.getId());
			membershipCard.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
	
	public boolean updMembershipCard(ChainCard chainCard, UpdMembershipCard param) {
		if(chainCard == null){
			return false;
		}
		Map<String, MembershipCard> membershipCardMap = chainCard.getMembershipCardMap();
		if (membershipCardMap.containsKey(param.getId())) {
			MembershipCard oldTarget = membershipCardMap.get(param.getId());
			MembershipCard newTarget = AppUtils.copyBeanBySerialize(oldTarget, MembershipCard.class);
			param.updateMembershipCard(newTarget);
			if(!newTarget.equals(oldTarget)) {
				membershipCardMap.put(newTarget.getId(), newTarget);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateMembershipCardState(ChainCard chainCard, UpdMemberCardStateData param){
		if(chainCard == null){
			return false;
		}
		Map<String, MembershipCard> membershipCardMap = chainCard.getMembershipCardMap();
		if (membershipCardMap.containsKey(param.getId())){
			MembershipCard membershipCard = membershipCardMap.get(param.getId());
			membershipCard.setStatus(param.getState());
			return true;
		}
		return false;
	}
	
	public boolean allotMemberCard(ChainCard chainCard, MemberCardAllotForm param){
		if(chainCard == null){
			return false;
		}
		Map<String, MembershipCard> membershipCardMap = chainCard.getMembershipCardMap();
		if (membershipCardMap.containsKey(param.getId())){
			MembershipCard membershipCard = membershipCardMap.get(param.getId());
			membershipCard.setApplyStoreIds(param.getApplyStoreIds());
			return true;
		}
		return false;
	}
	
	public boolean addProductCard(ChainCard chainCard, AddProductCardForm param) {
		if(chainCard == null){
			return false;
		}
		long chainId = chainCard.getChainId();
		ProductCard data = param.toProductCard(chainId);
		Map<String, ProductCard> productCardMap = chainCard.getProductCardMap();
		int index = param.getIndex();
		if (!productCardMap.containsKey(data.getId()) && chainCard.getProductCardIndex() + 1 == index) {
			productCardMap.put(data.getId(), data);
			chainCard.setProductCardIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean updProductCard(ChainCard chainCard, UpdProductCardForm param) {
		if(chainCard == null){
			return false;
		}
		Map<String, ProductCard> productCardMap = chainCard.getProductCardMap();
		if (productCardMap.containsKey(param.getId())){
			ProductCard oldTarget = productCardMap.get(param.getId());
			ProductCard newTarget = AppUtils.copyBeanBySerialize(oldTarget, ProductCard.class);
			param.updateProductCard(newTarget);
			if(!newTarget.equals(oldTarget)) {
				productCardMap.put(newTarget.getId(), newTarget);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateProductCardState(ChainCard chainCard, UpdProductCardStateData param){
		if(chainCard == null){
			return false;
		}
		Map<String, ProductCard> ProductCardMap = chainCard.getProductCardMap();
		if (ProductCardMap.containsKey(param.getId())){
			ProductCard productCard = ProductCardMap.get(param.getId());
			productCard.setStatus(param.getState());
			return true;
		}
		return false;
	}
	
	public boolean allotProductCard(ChainCard chainCard, ProductCardAllotForm param){
		if(chainCard == null){
			return false;
		}
		Map<String, ProductCard> ProductCardMap = chainCard.getProductCardMap();
		if (ProductCardMap.containsKey(param.getId())){
			ProductCard productCard = ProductCardMap.get(param.getId());
			productCard.setApplyStoreIds(param.getApplyStoreIds());
			return true;
		}
		return false;
	}
	
	public boolean delProductCard(ChainCard chainCard, DelProductCardForm param) {
		if(chainCard == null){
			return false;
		}
		Map<String, ProductCard> productCardMap = chainCard.getProductCardMap();
		if (productCardMap.containsKey(param.getId())) {
			ProductCard productCard = productCardMap.get(param.getId());
			productCard.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
}
