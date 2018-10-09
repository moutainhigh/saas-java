package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.service.workFlowData.data.PreStoreCardRecord;

public class PreStoreCardRecordAddForm {
	// 客户次卡ID
	private String preStoreCardId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目、商品、套餐ID
	private String pgId;
	// 抵消次数
	private int count;

	public static PreStoreCardRecordAddForm newInstance() {
		return new PreStoreCardRecordAddForm();
	}

	public PreStoreCardRecord toPreStoreCardRecord() {
		PreStoreCardRecord data = PreStoreCardRecord.newInstance(this.preStoreCardId, this.pgId, this.itemType);
		data.setCount(this.count);
		return data;
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
