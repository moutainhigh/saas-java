package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.service.workFlowData.data.DelimitCardRecord;

public class DelimitCardRecordAddForm {
	// 客户次卡ID
	private String leaguerPrdCardId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目、商品、套餐ID
	private String pgId;
	// 抵消次数
	private int count;

	public static DelimitCardRecordAddForm newInstance() {
		return new DelimitCardRecordAddForm();
	}

	public DelimitCardRecord toDelimitCardRecord() {
		DelimitCardRecord data = DelimitCardRecord.newInstance(this.leaguerPrdCardId, this.pgId, this.itemType);
		data.setCount(this.count);
		return data;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
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
