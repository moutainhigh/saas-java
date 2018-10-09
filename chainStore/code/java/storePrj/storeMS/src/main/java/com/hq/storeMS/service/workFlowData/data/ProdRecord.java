package com.hq.storeMS.service.workFlowData.data;

import java.util.HashSet;
import java.util.Set;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ProdRecord {
	// prodId_recordType
	private String id;
	// 项目ID
	private String prodId;
	// 项目个数
	private int count;
	// 售价
	private float price;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;
	// 创建时间
	private long createTime;
	//预存数量
	private int restCount;

	// 原价
	private float oldPrice;
	// 总价 = 原价 X 个数
	private float cost;
	// 应结 = 售价 X 个数
	private float pay;

	/********************** 遗留字段 **********************/
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();
	// 项目状态 ProdRecordStatusEnum 未完成/已完成
	private int status;

	public static ProdRecord newInstance() {
		ProdRecord prodRecord = new ProdRecord();
		prodRecord.status = ProdRecordStatusEnum.INCOMPLETE.ordinal();
		prodRecord.createTime = System.currentTimeMillis();
		return prodRecord;
	}

	public BuyItem toBuyItem() {
		BuyItem item = BuyItem.newInstance(BuyTypeEnum.PRODUCT.ordinal(), this.prodId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
	}

	public DonationItem toDonationItem() {
		DonationItem item = DonationItem.newInstance(BuyTypeEnum.PRODUCT.ordinal(), this.prodId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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
