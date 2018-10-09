package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.workFlowData.data.PrdCardRecord;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PrdCardUpdateForm {
	private String id;
	// 店铺次卡类型的ID
	private String prdCardTypeId;
	// 次卡个数
	private int count;
	// 原价
	private float oldPrice;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;

	public static PrdCardUpdateForm newInstance() {
		return new PrdCardUpdateForm();
	}
	
	public void updatePrdCardRecord(PrdCardRecord data) {
		FastBeanCopyer.getInstance().copy(this, data);
		data.setPrice(BigDecimalUtil.round(oldPrice * discount / ServerConstants.DISCOUNT_NUM, 2));
		data.setCost(BigDecimalUtil.round(oldPrice * count, 2));
		data.setPay(BigDecimalUtil.round(oldPrice * count * discount / ServerConstants.DISCOUNT_NUM, 2));
	}

	public String getPrdCardTypeId() {
		return prdCardTypeId;
	}

	public void setPrdCardTypeId(String prdCardTypeId) {
		this.prdCardTypeId = prdCardTypeId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
