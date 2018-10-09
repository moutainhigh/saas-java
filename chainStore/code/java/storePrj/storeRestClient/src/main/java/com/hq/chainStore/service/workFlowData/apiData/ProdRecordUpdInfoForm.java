package com.hq.chainStore.service.workFlowData.apiData;

import java.util.HashSet;
import java.util.Set;

public class ProdRecordUpdInfoForm {
	private String id;
	// 项目ID
	private String prodId;
	// 项目个数
	private int count;
	// 原价
	private float oldPrice;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;

	/********************** 遗留字段 **********************/
	// 价格
	private float price;
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();

	public static ProdRecordUpdInfoForm newInstance() {
		return new ProdRecordUpdInfoForm();
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

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
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
