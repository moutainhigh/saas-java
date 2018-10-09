package com.hq.chainStore.pressTest.robot.workFlow;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.workFlowData.apiData.MemCardInfoForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataMemCardInfoMgr;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class MemberCardActor {

	public static MemberCardActor getInstance(){
		return HotSwap.getInstance().getSingleton(MemberCardActor.class);
	}
	
	/**
	 * 添加修改会员充值信息
	 * @param storeId
	 */
	public void updateMemberCard(long storeId){
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRechargeRandomWorkFlow(storeId);
		if(randomWorkFlow !=null){
			MemCardInfoForm inputForm = MemCardInfoForm.newInstance();
			inputForm.setLargess(RandomUtils.nextFloat(100f, 200f));
			inputForm.setCost(RandomUtils.nextFloat(1000f, 2000f));
			WorkFlowDataMemCardInfoMgr.getInstance().addMemCardInfo(randomWorkFlow.getId(), inputForm);
		}
	}
	
}
