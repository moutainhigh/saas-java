package com.hq.chainMS.service.chainCard.bs.updateHandle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainCard.apiData.AddMembershipCard;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateType;
import com.hq.chainMS.service.chainCard.apiData.DelMembershipCard;
import com.hq.chainMS.service.chainCard.apiData.MemberCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.UpdMemberCardStateData;
import com.hq.chainMS.service.chainCard.apiData.UpdMembershipCard;
import com.hq.chainMS.service.chainCard.bs.ChainCardMgr;
import com.hq.chainMS.service.chainCard.bs.MembershipCardDetailMgr;
import com.hq.chainMS.service.chainCard.data.CardStatusEnum;
import com.hq.chainMS.service.chainCard.data.ChainCard;
import com.hq.chainMS.service.chainCard.data.ChainCardBeanHelper;
import com.hq.chainMS.service.chainCard.data.MembershipCard;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetail;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.storeChain.bs.StoreChainMgr;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.data.StoreChainItemType;
import com.hq.storeClient.service.storeChain.data.StoreChainStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardHandle {

	public static MembershipCardHandle getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardHandle.class);
	}

	// 添加会员卡类型
	public OperateTips addMembershipCard(long chainId, AddMembershipCard addMembershipCard) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.AddMembershipCard.getMark() + "失败");
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);

		if (checkNumberExists4Add(addMembershipCard.getNumber(), chainCard.getMembershipCardMap().values())) {
			tips.setTips("会员卡编号已存在");
			return tips;
		}

		if (ChainCardBeanHelper.getInstance().addMembershipCard(chainCard, addMembershipCard)) {
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			MembershipCardDetail detail = addMembershipCard.toMembershipCardDetail(chainId);
			MembershipCardDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	public OperateTips updMemberCardState(long chainId, UpdMemberCardStateData inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.UpdMemberCardState.getMark() + "失败");

		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		if (ChainCardBeanHelper.getInstance().updateMembershipCardState(chainCard, inputForm)) {
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			//将详情信息也更新
			MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setStatus(inputForm.getState());
			MembershipCardDetailMgr.getInstance().update(detail);
			
			if(inputForm.getState() == CardStatusEnum.CLOSE.ordinal()) {//下架操作
				updateStoreDataClose(chainId, detail.getApplyStoreIds(), inputForm.getId());
			}
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private void updateStoreDataClose(long chainId, Set<Long> applyStoreIds, String id) {
		try {
			List<StoreChainUpdateStatusForm> updateStatusForms = new ArrayList<StoreChainUpdateStatusForm>();
			for (Long storeId : applyStoreIds) {
				StoreChainUpdateStatusForm form = StoreChainUpdateStatusForm.newInstance();
				form.setStatus(StoreChainStatus.Close.ordinal());
				form.setId(id);
				form.setItemType(StoreChainItemType.MemberCard.ordinal());
				form.setStoreId(storeId);
				updateStatusForms.add(form);
			}
			StoreChainMgr.getInstance().batchUpdateState(String.valueOf(chainId), updateStatusForms);
		} catch (Exception e) {
			MainLog.error(LogModule.ChainCard, "MembershipCardHandle[updateStoreDataClose]", "", e);
		}
	}
	
	
	public OperateTips allotMemberCard(long chainId, MemberCardAllotForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.MemberCardAllot.getMark() + "失败");
		
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		inputForm.getApplyStoreIds().remove(null);
		if (ChainCardBeanHelper.getInstance().allotMemberCard(chainCard, inputForm)) {
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			//将详情信息也更新
			MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setApplyStoreIds(inputForm.getApplyStoreIds());
			MembershipCardDetailMgr.getInstance().update(detail);
			
			tips.setSuccess(true);
		}
		return tips;
	}

	public OperateTips updMembershipCard(long chainId, UpdMembershipCard updMembershipCard) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.UpdMembershipCard.getMark() + "失败");
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);

		if (checkNumberExists4Update(updMembershipCard.getNumber(), chainCard.getMembershipCardMap().values(), updMembershipCard.getId())) {
			tips.setTips("会员卡编号已存在");
			return tips;
		}

		if (ChainCardBeanHelper.getInstance().updMembershipCard(chainCard, updMembershipCard)) {
			ChainCardMgr.getInstance().updateChainCard(chainCard);
		}
		
		//将详情信息也更新
		MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(chainId, updMembershipCard.getId());
		updMembershipCard.updateMembershipCardDetail(detail);
		MembershipCardDetailMgr.getInstance().update(detail);
		tips.setSuccess(true);
		return tips;
	}

	public OperateTips delMembershipCard(long chainId, DelMembershipCard delMembershipCard) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CARD_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.DelMembershipCard.getMark() + "失败");

		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		if (ChainCardBeanHelper.getInstance().delMembershipCard(chainCard, delMembershipCard)) {
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			//将详情信息也更新
			MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(chainId, delMembershipCard.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			MembershipCardDetailMgr.getInstance().update(detail);
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkNumberExists4Add(String number, Collection<MembershipCard> membershipCards) {
		return checkNumberExists(number, membershipCards, "");
	}
	
	private boolean checkNumberExists4Update(String number, Collection<MembershipCard> membershipCards, String id) {
		return checkNumberExists(number, membershipCards, id);
	}
	
	private boolean checkNumberExists(String number, Collection<MembershipCard> membershipCards, String id) {
		if(StringUtils.isBlank(number)) {
			return false;
		}
		if (CollectionUtils.isNotEmpty(membershipCards)) {
			for (MembershipCard info : membershipCards) {
				if (number.equals(info.getNumber()) && info.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(info.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
