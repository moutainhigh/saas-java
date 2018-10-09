package com.hq.storeMS.service.workFlowData.apiData.save;

import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordAddForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class DelimitCardRecordSaveForm {
	// 客户次卡ID
	private String leaguerPrdCardId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目、商品、套餐ID
	private String pgId;
	// 抵消次数
	private int count;

	public static DelimitCardRecordSaveForm newInstance() {
		return new DelimitCardRecordSaveForm();
	}

	public DelimitCardRecordAddForm toDelimitCardRecordAddForm() {
		DelimitCardRecordAddForm data = DelimitCardRecordAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
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
