package com.hq.chainStore.service.workFlowData.apiData.save;

public class DonateItemSaveForm {
	// 购买类型 BuyTypeEnum
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	// 原价
	private float oldPrice;
	// 数量
	private int count;

	public static DonateItemSaveForm newInstance() {
		return new DonateItemSaveForm();
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

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
