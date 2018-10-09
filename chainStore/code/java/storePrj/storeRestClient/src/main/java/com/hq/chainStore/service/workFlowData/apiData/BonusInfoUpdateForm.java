package com.hq.chainStore.service.workFlowData.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainStore.service.workFlowData.data.UserBonus;

public class BonusInfoUpdateForm {
	// 提成ID prdCardPayType_buyType_pgId
	private String bonusId;
	// 医美师提成
	private Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();

	public static BonusInfoUpdateForm newInstance() {
		return new BonusInfoUpdateForm();
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
