package com.hq.storeMS.service.orderNotes.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.orderNotes.data.ChargeBackItem;
import com.hq.storeMS.service.orderNotes.data.ChargeBackRecord;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChargeBackRecordAddForm {
	// 退款项的数量与金额
	private List<ChargeBackItem> chargeBackItems = new ArrayList<ChargeBackItem>();
	// 退款明细
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 退单备注信息
	private String remark;

	/********************* 后端服务使用的字段 *********************/
	// 操作人
	private long creatorId;
	private String creatorName;

	public static ChargeBackRecordAddForm newInstance() {
		return new ChargeBackRecordAddForm();
	}

	public ChargeBackRecord toChargeBackRecord(int index) {
		long currentTimeMillis = System.currentTimeMillis();
		for (PayItem payItem : payItems) {
			if(payItem.getCreatedTime() == 0l){
				payItem.setCreatedTime(currentTimeMillis);
			}
		}
		
		ChargeBackRecord data = ChargeBackRecord.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(index);
		return data;
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

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
