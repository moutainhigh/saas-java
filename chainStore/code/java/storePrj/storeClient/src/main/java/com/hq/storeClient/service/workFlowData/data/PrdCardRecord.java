package com.hq.storeClient.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PrdCardRecord {
	// prdCardTypeId_recordType
	private String id;
	// 店铺次卡类型的ID
	private String prdCardTypeId;
	// 次卡个数
	private int count;
	// 价格
	private float price;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;
	// 创建时间
	private long createTime;

	// 原价
	private float oldPrice;
	// 总价 = 原价 X 个数
	private float cost;
	// 应结 = 售价 X 个数
	private float pay;

	public static PrdCardRecord newInstance() {
		PrdCardRecord prodRecord = new PrdCardRecord();
		return prodRecord;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
