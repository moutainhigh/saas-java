package com.hq.chainStore.service.workFlowData.data;

import java.util.HashSet;
import java.util.Set;

public class GoodsRecord {
	// goodsId_recordType
	private String id;
	// 商品ID
	private String goodsId;
	// 商品数量
	private int count;
	// 商品价格
	private float price;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;
	// 创建时间
	private long createTime;
	// 预存数量
	private int restCount;

	/********************** 遗留字段 **********************/
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();

	public static GoodsRecord newInstance() {
		return new GoodsRecord();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}

}
