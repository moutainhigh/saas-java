package com.hq.copyData.copyModule.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hq.chainStore.service.common.EntityState;
import com.hq.chainStore.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.chainStore.service.membershipCardDetail.bs.MembershipCardDetailMgr;
import com.hq.chainStore.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.chainStore.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.chainStore.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.chainStore.service.productCardDetail.data.ProductCardDetail;
import com.hq.chainStore.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeAddForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeRemoveForm;
import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.PrdCardType;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.common.StringUtils4Client;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CopyStoreCardInfo extends AbstractCopyModule{
	
	public static CopyStoreCardInfo newInstance(){
		CopyStoreCardInfo data = new CopyStoreCardInfo();
		return data;
	}
	
	public void copy(){
		//数据准备
		AccessTokenMgr.getInstance().setOpIdTL(getSourceBossId());
		List<PrdCardType> types = getPrdCardTypes();
		List<MembershipCardDetail> membershipCards = getMembershipCards();
		List<ProductCardDetail> productCards = getProductCards();
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		//复制数据
		AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(getTargetStoreId());
		addPrdCardType(storeCardInfo, types);
		addMembershipCard(storeCardInfo, membershipCards);
		addProductCard(storeCardInfo, productCards);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		System.out.println("copy store card info finish");
	}
	
	private List<PrdCardType> getPrdCardTypes(){
		StoreCardInfo sourceStoreCard = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(getSourceStoreId());
		sourceStoreCard.getPrdCardTypeMap().remove("0");
		List<PrdCardType> types = new ArrayList<PrdCardType>(sourceStoreCard.getPrdCardTypeMap().values());
		Collections.sort(types, new Comparator<PrdCardType>() {
			@Override
			public int compare(PrdCardType o1, PrdCardType o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return types;
	}
	
	private List<MembershipCardDetail> getMembershipCards(){
		MembershipCardDetailQueryForm queryForm = MembershipCardDetailQueryForm.newInstance();
		queryForm.setStoreId(getSourceStoreId());
		return MembershipCardDetailMgr.getInstance().getMembershipCardDetailPageInfo(queryForm).getList();
	}
	
	private List<ProductCardDetail> getProductCards(){
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setStoreId(getSourceStoreId());
		return ProductCardDetailMgr.getInstance().getProductCardDetailPageInfo(queryForm).getList();
	}
	
	private void addPrdCardType(StoreCardInfo storeCardInfo, List<PrdCardType> types) {
		long prdCardTypeIndex = storeCardInfo.getPrdCardTypeIndex();
		for (PrdCardType type : types) {
			prdCardTypeIndex++;
			PrdCardTypeAddForm dataForm = PrdCardTypeAddForm.newInstance();
			dataForm.setIndex(prdCardTypeIndex);
			dataForm.setName(type.getName());
			StoreCardInfoMgr.getInstance().addPrdCardType(getTargetStoreId(), dataForm);
			
			if(type.getEntityState() == EntityState.Deleted.ordinal()) {
				PrdCardTypeRemoveForm param = PrdCardTypeRemoveForm.newInstance();
				param.setId(type.getId());
				StoreCardInfoMgr.getInstance().delPrdCardType(getTargetStoreId(), param);
			}
		}
	}
	
	private void addMembershipCard(StoreCardInfo storeCardInfo, List<MembershipCardDetail> membershipCards) {
		int membershipCardIndex = storeCardInfo.getMembershipCardIndex();
		for (MembershipCardDetail tCardInfo : membershipCards) {
			if(tCardInfo.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			membershipCardIndex++;
			AddMembershipCard param = AddMembershipCard.newInstance();
			FastBeanCopyer.getInstance().copy(tCardInfo, param);
			param.setIndex(membershipCardIndex);
			StoreCardInfoMgr.getInstance().addMembershipCard(getTargetStoreId(), param);
		}
	}
	
	private void addProductCard(StoreCardInfo storeCardInfo, List<ProductCardDetail> productCards) {
		int productCardIndex = storeCardInfo.getProductCardIndex();
		for (ProductCardDetail tCardInfo : productCards) {
			if(tCardInfo.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			productCardIndex++;
			AddProductCardForm addProductCardForm = AddProductCardForm.newInstance();
			FastBeanCopyer.getInstance().copy(tCardInfo, addProductCardForm);
			addProductCardForm.setIndex(productCardIndex);
			addProductCardForm.setId(StringUtils4Client.format("_prd_{}_{}", getTargetStoreId(), productCardIndex));
			StoreCardInfoMgr.getInstance().addProductCard(getTargetStoreId(), addProductCardForm);
		}
	}
}
