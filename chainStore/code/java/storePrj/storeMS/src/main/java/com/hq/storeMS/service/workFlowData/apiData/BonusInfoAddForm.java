package com.hq.storeMS.service.workFlowData.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordForm;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.UserBonus;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BonusInfoAddForm {
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

	/****************************** 遗留字段 ******************************/
	// 次卡ID
	private String productCardId;

	public static BonusInfoAddForm newInstance() {
		return new BonusInfoAddForm();
	}

	public BonusRecordForm toBonusRecordForm(long storeIdP, long orderIdP) {
		BonusRecordForm data = BonusRecordForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setStoreId(storeIdP);
		data.setOrderId(orderIdP);
		return data;
	}

	public BonusInfo toBonusInfo() {
		BonusInfo data = BonusInfo.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		String bonusId = BonusInfo.genBonusId(prdCardPayType, buyType, pgId, leaguerPrdCardId);
		data.setBonusId(bonusId);
		return data;
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

	public String getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(String productCardId) {
		this.productCardId = productCardId;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}
}
