package com.hq.storeMS.service.bonusRecord.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.workFlowData.data.UserBonus;

public class BonusRecordForm {
	// 店铺ID
	private long storeId;
	// 订单ID
	private long orderId;

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
	// 划卡的次卡ID
	private String productCardId;

	public static BonusRecordForm newInstance() {
		return new BonusRecordForm();
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}
}
