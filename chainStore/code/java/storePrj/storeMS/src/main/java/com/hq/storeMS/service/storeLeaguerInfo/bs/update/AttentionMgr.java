package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.AddAttention;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.RemoveAttention;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.storeMS.service.storeLeaguerInfo.data.AttentionTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class AttentionMgr {
	
	public static AttentionMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AttentionMgr.class);
	}
	
	public OperateTips addAttention(long storeId, AddAttention addAttention){
		OperateTips tips = OperateTips.newInstance(false,StoreLeaguerInfoUpdateType.AddAttention.getMark()+"失败");
		LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().getSimple(storeId, addAttention.getLeaguerId());
		if(leaguerDetail != null){
			leaguerDetail.setAttention(AttentionTypeEnum.STAR.ordinal());
			LeaguerDetailMgr.getInstance().updateSimple(leaguerDetail);
			tips.setSuccess(true);
		}else {
			tips.setTips("客户不存在，添加标星客户失败");
		}
		return tips;
	}
	
	public OperateTips removeAttention(long storeId, RemoveAttention removeAttention){
		OperateTips tips = OperateTips.newInstance(false,StoreLeaguerInfoUpdateType.RemoveAttention.getMark()+"失败");
		LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().getSimple(storeId, removeAttention.getLeaguerId());
		if(leaguerDetail != null){
			leaguerDetail.setAttention(AttentionTypeEnum.UNKNOW.ordinal());
			LeaguerDetailMgr.getInstance().updateSimple(leaguerDetail);
			tips.setSuccess(true);
		}else {
			tips.setTips("客户不存在，移除标星客户失败");
		}
		return tips;
	}
}
