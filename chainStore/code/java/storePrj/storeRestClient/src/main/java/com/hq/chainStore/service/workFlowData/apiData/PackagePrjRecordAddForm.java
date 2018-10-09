package com.hq.chainStore.service.workFlowData.apiData;

public class PackagePrjRecordAddForm {
	// 套餐ID
	private String packagePrjId;
	// 数量
	private int count;
	// 原价
	private float oldPrice;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;

	public static PackagePrjRecordAddForm newInstance() {
		return new PackagePrjRecordAddForm();
	}

	public String getPackagePrjId() {
		return packagePrjId;
	}

	public void setPackagePrjId(String packagePrjId) {
		this.packagePrjId = packagePrjId;
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
