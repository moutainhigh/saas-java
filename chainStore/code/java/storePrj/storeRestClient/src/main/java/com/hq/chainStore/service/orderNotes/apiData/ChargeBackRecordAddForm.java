package com.hq.chainStore.service.orderNotes.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.order.data.PayItem;
import com.hq.chainStore.service.orderNotes.data.ChargeBackItem;

public class ChargeBackRecordAddForm {
	// 退款项的数量与金额
	private List<ChargeBackItem> chargeBackItems = new ArrayList<ChargeBackItem>();
	// 退款明细
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 退单备注信息
	private String remark;

	public static ChargeBackRecordAddForm newInstance() {
		return new ChargeBackRecordAddForm();
	}

	public List<ChargeBackItem> getChargeBackItems() {
		return chargeBackItems;
	}

	public void setChargeBackItems(List<ChargeBackItem> chargeBackItems) {
		this.chargeBackItems = chargeBackItems;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}
}
