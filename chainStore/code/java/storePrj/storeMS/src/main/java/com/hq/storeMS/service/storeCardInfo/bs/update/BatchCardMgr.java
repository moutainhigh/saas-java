package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.storeMS.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.storeMS.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchCardMgr {

	public static BatchCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BatchCardMgr.class);
	}

	//批量导入会员卡
	public OperateTips addMembershipCardList(long storeId, List<AddMembershipCard> inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.AddMembershipCardList.getMark() + "失败");

		// 只要不抛出异常，则都算批量导入成功。
		List<String> numbers = new ArrayList<String>();
		boolean flag = false;
		
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		int idIndex = storeData.getMembershipCardIndex();
		for (AddMembershipCard addMembershipCard : inputData) {
			OperateTips optips = OperateTips.newInstance(false);
			try {
				idIndex++;
				addMembershipCard.setIndex(idIndex);
				optips = MembershipCardMgr.getInstance().addMembershipCard(storeId, addMembershipCard);
			} catch (Exception e) {
				idIndex--;
				MainLog.error(LogModule.StoreCardInfo, "BatchCardHandle[addMembershipCardList]", "addMembershipCardList failure", e);
			}
			if (!optips.isSuccess()) {
				numbers.add(addMembershipCard.getNumber());
				flag = true;
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下会员卡导入失败：[" + StringUtils.join(numbers, ",") + "]");
		}
		return tips;

	}

	//批量修改会员卡的状态
	public OperateTips batchUpdMemberCardState(long storeId, BatchUpdMemberCardStateData inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.BatchUpdMemberCardState.getMark() + "失败");
		
		Map<String, MembershipCard> cardMap = StoreCardInfoMgr.getInstance().getByStoreId(storeId).getMembershipCardMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<UpdMemberCardStateData> formList = inputData.toUpdMemberCardStateDataList();
		for (UpdMemberCardStateData form : formList) {
			OperateTips optips = MembershipCardMgr.getInstance().updMemberCardState(storeId, form);
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
	
	//批量修改次卡的状态
	public OperateTips batchUpdProductCardState(long storeId, BatchUpdProductCardStateData inputData){
		OperateTips tips = OperateTips.newInstance(false,StoreCardInfoUpdateType.BatchUpdProductCardState.getMark()+"失败");
		
		Map<String, ProductCard> cardMap = StoreCardInfoMgr.getInstance().getByStoreId(storeId).getProductCardMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<UpdProductCardStateData> formList = inputData.toUpdProductCardStateDataList();
		for (UpdProductCardStateData form : formList) {
			OperateTips optips = ProductCardMgr.getInstance().updProductCardState(storeId, form);
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
}
