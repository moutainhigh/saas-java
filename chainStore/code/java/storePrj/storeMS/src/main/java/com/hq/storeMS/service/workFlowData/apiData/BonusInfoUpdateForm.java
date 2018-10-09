package com.hq.storeMS.service.workFlowData.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.UserBonus;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BonusInfoUpdateForm {
	// 提成ID cardFlag_buyType_pgId_cardId
	private String bonusId;
	// 医美师提成
	private Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();

	public static BonusInfoUpdateForm newInstance() {
		return new BonusInfoUpdateForm();
	}
	
	public void updateBonusInfo(BonusInfo data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public String getBonusId() {
		return bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public Map<Long, UserBonus> getUserBonusMap() {
		return userBonusMap;
	}

	public void setUserBonusMap(Map<Long, UserBonus> userBonusMap) {
		this.userBonusMap = userBonusMap;
	}

}
