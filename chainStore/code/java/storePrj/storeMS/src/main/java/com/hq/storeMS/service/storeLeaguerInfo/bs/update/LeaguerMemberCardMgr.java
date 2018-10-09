package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataMgr;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerMemberCardMgr{
	
	public static LeaguerMemberCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerMemberCardMgr.class);
	}
	
	//设置会员卡
	public OperateTips updateMemberCard(long storeId, UpdateMemberCardForm inputData){
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.UpdateMemberCard.getMark()+"失败");

		String leaguerId = inputData.getLeaguerId();
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().getSimple(storeId, leaguerId);
		if(leaguer != null){
			StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(leaguer.getStoreId());
			if(storeCard.memberCardIsFromChain(inputData.getCardId())) {//连锁店会员卡
				CuserChainDataMgr.getInstance().updateChainMemberCard(leaguer, inputData);
			}
			LeaguerDetailBeanHelper.getInstance().updateMemberCard(leaguer.getLeaguerMemberCard(), inputData);
			LeaguerDetailMgr.getInstance().updateSimple(leaguer);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, leaguer.getName(), OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.UpdateMemberCard.getMark()));
		}
		
		return tips;
	}
}
