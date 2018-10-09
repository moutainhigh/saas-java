package com.hq.storeClient.service.workFlowData.apiData.save;

public class PreStoreCardRecordSaveForm {
	// 客户次卡ID
	private String preStoreCardId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目、商品、套餐ID
	private String pgId;
	// 抵消次数
	private int count;

	public static PreStoreCardRecordSaveForm newInstance() {
		return new PreStoreCardRecordSaveForm();
	}

	public String getPreStoreCardId() {
		return preStoreCardId;
	}

	public void setPreStoreCardId(String preStoreCardId) {
		this.preStoreCardId = preStoreCardId;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
