package com.hq.storeClient.service.workFlowData.data;

import java.util.HashMap;
import java.util.Map;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BonusInfo {
	// 提成ID prdCardPayType_buyType_pgId_leaguerPrdCardId
	private String bonusId;
	// 是否划卡结算 对应 PrdCardPayEnum
	private int prdCardPayType;
	// 消费购买的类型 BuyTypeEnum 次卡、项目、商品、套餐
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	// 客户的次卡ID
	private String leaguerPrdCardId;
	// 医美师提成
	private Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();

	public static BonusInfo newInstance() {
		return new BonusInfo();
	}

	public String getBonusId() {
		return bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getPrdCardPayType() {
		return prdCardPayType;
	}

	public void setPrdCardPayType(int prdCardPayType) {
		this.prdCardPayType = prdCardPayType;
	}

	public Map<Long, UserBonus> getUserBonusMap() {
		return userBonusMap;
	}

	public void setUserBonusMap(Map<Long, UserBonus> userBonusMap) {
		this.userBonusMap = userBonusMap;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

}
