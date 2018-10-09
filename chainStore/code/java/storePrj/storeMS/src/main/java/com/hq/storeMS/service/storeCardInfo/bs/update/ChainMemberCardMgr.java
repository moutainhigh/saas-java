package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.List;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.storeMS.service.chainCard.bs.ChainCardMgr;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataSynBeanHelper;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.membershipCardDetail.bs.MembershipCardDetailMgr;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeMS.service.storeCardInfo.apiData.MemberCardBatchCancelForm;
import com.hq.storeMS.service.storeCardInfo.apiData.MemberCardBatchPullForm;
import com.hq.storeMS.service.storeCardInfo.apiData.MemberCardCancelForm;
import com.hq.storeMS.service.storeCardInfo.apiData.MemberCardPullForm;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainMemberCardMgr {
	public static ChainMemberCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainMemberCardMgr.class);
	}

	public OperateTips batchCancelChainMemberCard(long storeId, MemberCardBatchCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.BatchCancelChainMemberCard.getMark() + "失败");
		List<MemberCardCancelForm> cancelForms = inputForm.getCancelForms();
		for (MemberCardCancelForm form : cancelForms) {
			cancelChainMemberCard(storeId, form);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips batchAddChainMemberCard(long storeId, MemberCardBatchPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.BatchPullMemberCardFromChain.getMark() + "失败");
		List<MemberCardPullForm> forms = inputForm.getPullForms();
		for (MemberCardPullForm form : forms) {
			addChainMemberCard(storeId, form);
		}
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips cancelChainMemberCard(long storeId, MemberCardCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.CancelChainMemberCard.getMark() + "失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		MembershipCard membershipCard = storeData.getMembershipCardMap().get(inputForm.getId());
		if(membershipCard!=null) {
			membershipCard.setEntityState(EntityState.Deleted.ordinal());
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		}
		
		MembershipCardDetail sdetail = MembershipCardDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail!=null) {
			sdetail.setEntityState(EntityState.Deleted.ordinal());
			MembershipCardDetailMgr.getInstance().update(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips addChainMemberCard(long storeId, MemberCardPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.PullMemberCardFromChain.getMark() + "失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		ChainCard chainData = ChainCardMgr.getInstance().getChainCard(inputForm.getChainId());
		ChainDataSynBeanHelper.getInstance().synStoreMemberCard(storeData, chainData, inputForm.getId());
		
		boolean updFlag = true;
		MembershipCardDetail sdetail = MembershipCardDetailMgr.getInstance().get(storeId, inputForm.getId());
		com.hq.chainClient.service.chainCard.data.MembershipCardDetail cdetail = ChainCardMgr.getInstance().getMembershipCardDetail(inputForm.getId(), inputForm.getChainId());
		if(sdetail == null) {
			sdetail = MembershipCardDetail.newInstance();
			updFlag = false;
		}
		ChainDataSynBeanHelper.getInstance().synMemberCardDetail(storeId, sdetail, cdetail);
		
		StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		if(updFlag) {
			MembershipCardDetailMgr.getInstance().update(sdetail);
		}else {
			MembershipCardDetailMgr.getInstance().addWithId(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
}
