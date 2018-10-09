package com.hq.storeMS.service.workFlowData.data;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PackagePrjRecord {
	// packagePrjId_recordType
	private String id;
	// 套餐ID
	private String packagePrjId;
	// 数量
	private int count;
	// 价格
	private float price;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;
	// 创建时间
	private long createTime;
	// 预存数量
	private int restCount;

	// 原价
	private float oldPrice;
	// 总价 = 原价 X 个数
	private float cost;
	// 应结 = 售价 X 个数
	private float pay;

	public static PackagePrjRecord newInstance() {
		PackagePrjRecord data = new PackagePrjRecord();
		data.createTime = System.currentTimeMillis();
		return data;
	}

	public BuyItem toBuyItem() {
		BuyItem item = BuyItem.newInstance(BuyTypeEnum.PACKAGE.ordinal(), this.packagePrjId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
	}

	public DonationItem toDonationItem() {
		DonationItem item = DonationItem.newInstance(BuyTypeEnum.PACKAGE.ordinal(), this.packagePrjId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
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

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}

}
