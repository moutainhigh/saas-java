package com.hq.storeMS.service.bonusRecord.apiData;

import java.util.ArrayList;
import java.util.List;

public class BonusRecordListForm {
	private long storeId;
	
	private long orderId;
	private List<BonusRecordForm> bonusRecordForms = new ArrayList<BonusRecordForm>();

	public static BonusRecordListForm newInstance(){
		return new BonusRecordListForm();
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<BonusRecordForm> getBonusRecordForms() {
		return bonusRecordForms;
	}

	public void setBonusRecordForms(List<BonusRecordForm> bonusRecordForms) {
		this.bonusRecordForms = bonusRecordForms;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
}
