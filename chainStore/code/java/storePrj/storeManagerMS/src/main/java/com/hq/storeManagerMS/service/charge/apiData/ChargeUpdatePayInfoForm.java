package com.hq.storeManagerMS.service.charge.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeManagerMS.service.charge.data.Charge;
import com.hq.storeManagerMS.service.charge.data.ChargePayItem;

public class ChargeUpdatePayInfoForm {
	private long id;
	private int status;
	private List<ChargePayItem> payItems = new ArrayList<ChargePayItem>();

	public static ChargeUpdatePayInfoForm newInstance() {
		ChargeUpdatePayInfoForm instance = new ChargeUpdatePayInfoForm();
		return instance;
	}
	
	public void updateCharge(Charge data) {
		data.setStatus(status);
		data.setPayItems(payItems);
		long money = 0L;
		for (ChargePayItem payItem : payItems) {
			money = money + payItem.getCost();
		}
		data.setMoney(money);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ChargePayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<ChargePayItem> payItems) {
		this.payItems = payItems;
	}

}
