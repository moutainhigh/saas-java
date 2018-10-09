package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.workFlowData.data.PrdCardRecord;
import com.hq.storeMS.service.workFlowData.data.RecordTypeEnum;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PrdCardAddForm {
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

	public static PrdCardAddForm newInstance() {
		return new PrdCardAddForm();
	}
	
	public PrdCardRecord toPrdCardRecord() {
		PrdCardRecord data = PrdCardRecord.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		if(recordType == RecordTypeEnum.Buy.ordinal()) {
			data.setPrice(BigDecimalUtil.round(oldPrice * discount / ServerConstants.DISCOUNT_NUM, 2));
		}else {
			data.setPrice(oldPrice);
		}
		data.setCost(BigDecimalUtil.round(oldPrice * count, 2));
		data.setPay(BigDecimalUtil.round(oldPrice * count * discount / ServerConstants.DISCOUNT_NUM, 2));
		data.setId(AppUtils.joinByUnderline(prdCardTypeId, recordType));
		return data;
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
}
