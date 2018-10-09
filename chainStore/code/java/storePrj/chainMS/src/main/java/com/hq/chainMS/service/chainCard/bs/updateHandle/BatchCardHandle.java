package com.hq.chainMS.service.chainCard.bs.updateHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainCard.apiData.BatchUpdMemberCardStateData;
import com.hq.chainMS.service.chainCard.apiData.BatchUpdProductCardStateData;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateType;
import com.hq.chainMS.service.chainCard.apiData.MemberCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.MemberCardBatchAllotForm;
import com.hq.chainMS.service.chainCard.apiData.ProductCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.ProductCardBatchAllotForm;
import com.hq.chainMS.service.chainCard.apiData.UpdMemberCardStateData;
import com.hq.chainMS.service.chainCard.apiData.UpdProductCardStateData;
import com.hq.chainMS.service.chainCard.bs.ChainCardMgr;
import com.hq.chainMS.service.chainCard.data.MembershipCard;
import com.hq.chainMS.service.chainCard.data.ProductCard;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchCardHandle {

	public static BatchCardHandle getInstance() {
		return HotSwap.getInstance().getSingleton(BatchCardHandle.class);
	}

	//批量修改会员卡的状态
	public OperateTips batchUpdMemberCardState(long chainId, BatchUpdMemberCardStateData batchUpdateMemberCardStateData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.BatchUpdMemberCardState.getMark() + "失败");
		
		Map<String, MembershipCard> cardMap = ChainCardMgr.getInstance().get(chainId).getMembershipCardMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<UpdMemberCardStateData> formList = batchUpdateMemberCardStateData.toUpdMemberCardStateDataList();
		for (UpdMemberCardStateData form : formList) {
			OperateTips optips = MembershipCardHandle.getInstance().updMemberCardState(chainId, form);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(cardMap.get(form.getId()).getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下会员卡更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	//批量分配会员卡
	public OperateTips memberCardBatchAllot(long chainId, MemberCardBatchAllotForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.MemberCardBatchAllot.getMark() + "失败");
		
		Map<String, MembershipCard> cardMap = ChainCardMgr.getInstance().get(chainId).getMembershipCardMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<MemberCardAllotForm> formList = inputForm.getMemberCardAllotForms();
		for (MemberCardAllotForm form : formList) {
			//批量分配  将现有的和原来的storeIds合并
			MembershipCard data = cardMap.get(form.getId());
			if(data==null) {
				continue;
			}
			form.getApplyStoreIds().addAll(data.getApplyStoreIds());
			OperateTips optips = MembershipCardHandle.getInstance().allotMemberCard(chainId, form);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(data.getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下会员卡分配失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	//批量修改次卡的状态
	public OperateTips batchUpdProductCardState(long chainId, BatchUpdProductCardStateData batchUpdateProductCardStateData){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainCardUpdateType.BatchUpdProductCardState.getMark()+"失败");
		
		Map<String, ProductCard> cardMap = ChainCardMgr.getInstance().get(chainId).getProductCardMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<UpdProductCardStateData> formList = batchUpdateProductCardStateData.toUpdProductCardStateDataList();
		for (UpdProductCardStateData form : formList) {
			OperateTips optips = ProductCardHandle.getInstance().updProductCardState(chainId, form);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(cardMap.get(form.getId()).getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下次卡更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	//批量分配次卡
	public OperateTips productCardBatchAllot(long chainId, ProductCardBatchAllotForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainCardUpdateType.ProductCardBatchAllot.getMark()+"失败");
		
		Map<String, ProductCard> cardMap = ChainCardMgr.getInstance().get(chainId).getProductCardMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<ProductCardAllotForm> formList = inputForm.getProductCardAllotForms();
		for (ProductCardAllotForm form : formList) {
			//批量分配  将现有的和原来的storeIds合并
			ProductCard data = cardMap.get(form.getId());
			if(data==null) {
				continue;
			}
			form.getApplyStoreIds().addAll(data.getApplyStoreIds());
			OperateTips optips = ProductCardHandle.getInstance().allotProductCard(chainId, form);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(data.getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下次卡分配失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
}
