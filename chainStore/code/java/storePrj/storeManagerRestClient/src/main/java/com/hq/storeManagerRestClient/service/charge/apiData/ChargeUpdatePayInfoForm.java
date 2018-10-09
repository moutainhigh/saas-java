package com.hq.storeManagerRestClient.service.charge.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeManagerRestClient.service.charge.data.ChargePayItem;

public class ChargeUpdatePayInfoForm {
	private long id;
	private int status;
	private List<ChargePayItem> payItems = new ArrayList<ChargePayItem>();

	public static ChargeUpdatePayInfoForm newInstance() {
		ChargeUpdatePayInfoForm data = new ChargeUpdatePayInfoForm();
		return data;
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
