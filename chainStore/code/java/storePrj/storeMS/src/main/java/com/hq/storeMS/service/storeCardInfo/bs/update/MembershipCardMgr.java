package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.membershipCardDetail.bs.MembershipCardDetailMgr;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeMS.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.storeMS.service.storeCardInfo.apiData.DelMembershipCard;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdMembershipCard;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfoBeanHelper;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardMgr {

	public static MembershipCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardMgr.class);
	}

	// 添加会员卡类型
	public OperateTips addMembershipCard(long storeId, AddMembershipCard inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.AddMembershipCard.getMark() + "失败");
		if(StoreVipMgr.getInstance().isMemberCardLimited(storeId)){
			tips.setTips("当前店铺会员数量已达上限");
			return tips;
		}
		
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);

		if (checkNumberExists4Add(inputData.getNumber(), storeCardInfo.getMembershipCardMap().values())) {
			tips.setTips("会员卡编号已存在");
			return tips;
		}

		if (StoreCardInfoBeanHelper.getInstance().addMembershipCard(storeCardInfo, inputData)) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
			
			MembershipCardDetail detail = inputData.toMembershipCardDetail(storeId);
			MembershipCardDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	public OperateTips updMemberCardState(long storeId, UpdMemberCardStateData inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.UpdMemberCardState.getMark() + "失败");

		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if (StoreCardInfoBeanHelper.getInstance().updateMembershipCardState(storeCardInfo, inputData.getId(), inputData.getState())) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
			//将详情信息也更新
			MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(storeId, inputData.getId());
			detail.setStatus(inputData.getState());
			MembershipCardDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	public OperateTips updMembershipCard(long storeId, UpdMembershipCard inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.UpdMembershipCard.getMark() + "失败");
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);

		if (checkNumberExists4Update(inputData.getNumber(), storeCardInfo.getMembershipCardMap().values(), inputData.getId())) {
			tips.setTips("会员卡编号已存在");
			return tips;
		}

		if (StoreCardInfoBeanHelper.getInstance().updMembershipCard(storeCardInfo, inputData)) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
		}
		
		//将详情信息也更新
		MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(storeId, inputData.getId());
		inputData.updateMembershipCardDetail(detail);
		MembershipCardDetailMgr.getInstance().update(detail);
		tips.setSuccess(true);
		return tips;
	}

	public OperateTips delMembershipCard(long storeId, DelMembershipCard inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.DelMembershipCard.getMark() + "失败");

		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if (StoreCardInfoBeanHelper.getInstance().delMembershipCard(storeCardInfo, inputData)) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
			
			//将详情信息也更新
			MembershipCardDetail detail = MembershipCardDetailMgr.getInstance().get(storeId, inputData.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			MembershipCardDetailMgr.getInstance().update(detail);
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkNumberExists4Add(String number, Collection<MembershipCard> membershipCards) {
		return checkNumberExists(number, membershipCards, ServerConstants.ZERO);
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
