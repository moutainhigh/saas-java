package com.hq.chainStore.pressTest.robot.workFlow;

import com.hq.chainStore.pressTest.robot.StoreLeaguerInfoActor;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.workFlowData.apiData.LeaguerInfoForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataLeaguerInfoMgr;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerActor {

	public static LeaguerActor getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerActor.class);
	}
	
	/**
	 * 添加修改客户
	 * @param storeId
	 * @param bossId
	 */
	public void updateLeaguer(long storeId,long bossId){
		Leaguer randomLeaguer = StoreLeaguerInfoActor.getInstance().getRandomLeaguer(storeId);
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRandomWorkFlow(storeId);
		if(randomWorkFlow !=null){
			LeaguerInfoForm inputForm = LeaguerInfoForm.newInstance();
			inputForm.setFollowUserId(bossId);
			inputForm.setCuserCareId(0L);
			inputForm.setCuserDocId(0L);
			inputForm.setLeaguerId(randomLeaguer.getId());
			WorkFlowDataLeaguerInfoMgr.getInstance().updateLeaguerInfo(randomWorkFlow.getId(), randomLeaguer.getId(), inputForm);
		}
	}
	
}
